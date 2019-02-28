package coliseum.service;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;

import coliseum.jpa.Filter;
import coliseum.jpa.Operator;

public interface ColiseumInterface {

	default void createEqualFilter(List<Filter> filters, String fieldName, List<Object> values) {
		if (CollectionUtils.isNotEmpty(values)) {
			if (values.size() == 1) {
				filters.add(new Filter(fieldName).by(Operator.EQUALS).with(values.get(0)));
			} else {
				filters.add(new Filter(fieldName).by(Operator.IN).with(values));
			}
		}
	}

	default void createEqualFilter(List<Filter> filters, String fieldName, Object value) {
		if (value != null) {
			filters.add(new Filter(fieldName).by(Operator.EQUALS).with(value));
		}
	}

}
