package coliseum.service;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;

import coliseum.jpa.Filter;
import coliseum.jpa.Operator;

public class FilterUtils {
    public static void createEqualFilter(List<Filter> filters, String fieldName, List<Object> values) {
        if (CollectionUtils.isNotEmpty(values)) {
            if (values.size() == 1) {
                filters.add(new Filter(fieldName).by(Operator.EQUALS).with(values.get(0)));
            } else {
                filters.add(new Filter(fieldName).by(Operator.IN).with(values));
            }
        }
    }

    public static void createEqualFilter(List<Filter> filters, String fieldName, Object value) {
        if (value != null) {
            if (value instanceof List) {
                createEqualFilter(filters, fieldName, (List) value);
            } else {
                filters.add(new Filter(fieldName).by(Operator.EQUALS).with(value));
            }
        }
    }
}
