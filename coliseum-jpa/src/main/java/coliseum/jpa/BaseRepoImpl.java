package coliseum.jpa;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.Tuple;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Fetch;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;

import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import coliseum.jpa.Filter;
import coliseum.jpa.Operator;
import coliseum.jpa.repo.BaseRepo;

@SuppressWarnings(value = { "unchecked", "rawtypes" })
//@Repository
public class BaseRepoImpl<T, ID extends Serializable> extends SimpleJpaRepository<T, ID> implements BaseRepo<T, ID> {

	private static final String DOT_SEPARATOR = "\\.";
	private static final String DOT = ".";
	private final JpaEntityInformation<T, ?> entityInformation;
	private final EntityManager em;

	public BaseRepoImpl(JpaEntityInformation entityInformation, EntityManager entityManager) {
		super(entityInformation, entityManager);
		this.entityInformation = entityInformation;
		this.em = entityManager;
	}

	public List<T> search(SearchObject searchObject) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<T> criteriaQuery = builder.createQuery(getDomainClass());
		Root<T> root = criteriaQuery.from(entityInformation.getJavaType());

		this.applyFilters(root, criteriaQuery, searchObject.getFilters(), builder);
		this.applySorts(root, criteriaQuery, searchObject.getSortOrders(), builder);
		this.applyAssociations(root, criteriaQuery, searchObject.getAssociations());

		Query query = applyPagination(searchObject, criteriaQuery);
		return query.getResultList();
	}

	public List<Tuple> search(SearchObject searchObject, List<String> selections) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<T> criteriaQuery = builder.createQuery(getDomainClass());
		Root<T> root = criteriaQuery.from(entityInformation.getJavaType());

		this.applyFilters(root, criteriaQuery, searchObject.getFilters(), builder);
		this.applySorts(root, criteriaQuery, searchObject.getSortOrders(), builder);
		this.applyAssociations(root, criteriaQuery, searchObject.getAssociations());
		this.applySelections(root, criteriaQuery, selections);

		Query query = applyPagination(searchObject, criteriaQuery);
		return query.getResultList();
	}

	private void applySelections(Root root, CriteriaQuery criteriaQuery, List<String> selections) {
		if (selections != null) {
			List<Selection> selects = new ArrayList<Selection>();
			for (String selection : selections) {
				selects.add(root.get(selection));
			}
			criteriaQuery.multiselect(selects.toArray(new Selection[selects.size()]));
		}
	}

	private Query applyPagination(SearchObject searchObject, CriteriaQuery criteriaQuery) {
		Query query = em.createQuery(criteriaQuery);
		if (searchObject.getPageIndex() != null && searchObject.getRecordstoFetch() != null) {
			query.setFirstResult((searchObject.getPageIndex() - 1) * searchObject.getRecordstoFetch());
			query.setMaxResults(searchObject.getRecordstoFetch());
		}
		return query;
	}

	private void applyAssociations(Root root, CriteriaQuery criteriaQuery, List<Association> associations) {
		if (associations != null) {
			for (Association association : associations) {
				JoinType joinType = JoinType.LEFT;
				if (association.isChildless()) {
					joinType = JoinType.INNER;
				}

				String fieldName = association.getFieldName();
				if (fieldName.contains(DOT)) {
					String[] names = fieldName.split(DOT_SEPARATOR);
					Fetch fetch = root.fetch(names[0], joinType);
					for (int i = 1; i < names.length; i++) {
						fetch = fetch.fetch(names[i], joinType);
					}
				} else {
					root.fetch(fieldName, joinType);
				}
			}
		}
	}

	private void applySorts(Root root, CriteriaQuery criteriaQuery, List<SortOrder> sortOrders,
			CriteriaBuilder builder) {
		if (sortOrders != null) {
			for (SortOrder sortOrder : sortOrders) {
				Path expression = this.getPath(root, sortOrder.getFieldName());
				if (sortOrder.isAscending()) {
					criteriaQuery = criteriaQuery.orderBy(builder.asc(expression));
				} else {
					criteriaQuery = criteriaQuery.orderBy(builder.desc(expression));
				}
			}
		}
	}

	private Path getPath(Root root, String fieldName) {
		Path expression = null;
		if (fieldName.contains(DOT)) {
			String[] names = fieldName.split(DOT_SEPARATOR);
			expression = root.get(names[0]);
			for (int i = 1; i < names.length; i++) {
				expression = expression.get(names[i]);
			}
		} else {
			expression = root.get(fieldName);
		}
		return expression;
	}

	private void applyFilters(Root root, CriteriaQuery criteriaQuery, List<Filter> filters, CriteriaBuilder builder) {
		if (filters != null) {
			List<Predicate> predicates = new ArrayList<Predicate>();
			for (Filter filter : filters) {
				predicates.add(this.constructFilter(root, criteriaQuery, filter, builder));
			}
			criteriaQuery.where(predicates.toArray(new Predicate[predicates.size()]));
		}
	}

	private Predicate constructFilter(Root root, CriteriaQuery criteriaQuery, Filter filter, CriteriaBuilder builder) {
		Predicate predicate = null;
		String fieldName = filter.getFieldName();
		Object fieldValue = filter.getFieldValue();
		Object fromValue = filter.getFromValue();
		Object toValue = filter.getToValue();

		if (filter.getFilters() != null) {
			List<Predicate> predicates = new ArrayList<Predicate>();
			for (Filter fils : filter.getFilters()) {
				predicates.add(this.constructFilter(root, criteriaQuery, fils, builder));
			}
			if (Operator.OR.equals(filter.getOperator())) {
				predicate = builder.or(predicates.toArray(new Predicate[predicates.size()]));
			} else {
				predicate = builder.and(predicates.toArray(new Predicate[predicates.size()]));
			}
		} else {
			Path expression = this.getPath(root, fieldName);

			switch (filter.getOperator()) {
			case EQUALS:
				predicate = builder.equal(expression, fieldValue);
				break;
			case NOT_EQUALS:
				predicate = builder.notEqual(expression, fieldValue);
				break;
			case GREATER_THAN:
				predicate = builder.greaterThan(expression, getComparable(fieldValue));
				break;
			case GREATER_THAN_OR_EQUALS:
				predicate = builder.greaterThanOrEqualTo(expression, getComparable(fieldValue));
				break;
			case LESS_THAN:
				predicate = builder.lessThan(expression, getComparable(fieldValue));
				break;
			case LESS_THAN_OR_EQUALS:
				predicate = builder.lessThanOrEqualTo(expression, getComparable(fieldValue));
				break;
			case IN:
				predicate = expression.in((List) fieldValue);
				break;
			case NOT_IN:
				predicate = expression.in((List) fieldValue).not();
				break;
			case IS_NULL:
				predicate = builder.isNull(expression);
				break;
			case IS_NOT_NULL:
				predicate = builder.isNotNull(expression);
				break;
			case BETWEEN:
				predicate = builder.between(expression, getComparable(fromValue), getComparable(toValue));
				break;
			default:
				break;
			}
		}
		return predicate;
	}

	public static Comparable getComparable(Object fieldValue) {
		if (fieldValue != null && !(fieldValue instanceof Comparable))
			throw new IllegalArgumentException(fieldValue.toString());
		return (Comparable) fieldValue;
	}
}
