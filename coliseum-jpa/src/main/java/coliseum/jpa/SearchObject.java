package coliseum.jpa;

import java.util.List;

import coliseum.jpa.Filter;

public class SearchObject {

	private List<SortOrder> sortOrders;

	private List<Association> associations;

	private List<Filter> filters;

	private Integer recordstoFetch;

	private Integer pageIndex;

	public List<SortOrder> getSortOrders() {
		return sortOrders;
	}

	public List<Association> getAssociations() {
		return associations;
	}

	public List<Filter> getFilters() {
		return filters;
	}

	public Integer getRecordstoFetch() {
		return recordstoFetch;
	}

	public Integer getPageIndex() {
		return pageIndex;
	}

	public void setSortOrders(List<SortOrder> sortOrders) {
		this.sortOrders = sortOrders;
	}

	public void setAssociations(List<Association> associations) {
		this.associations = associations;
	}

	public void setFilters(List<Filter> filters) {
		this.filters = filters;
	}

	public void setRecordstoFetch(Integer recordstoFetch) {
		this.recordstoFetch = recordstoFetch;
	}

	public void setPageIndex(Integer pageIndex) {
		this.pageIndex = pageIndex;
	}
}
