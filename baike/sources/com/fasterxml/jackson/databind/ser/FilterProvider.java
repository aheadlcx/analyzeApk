package com.fasterxml.jackson.databind.ser;

import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;

public abstract class FilterProvider {
    @Deprecated
    public abstract BeanPropertyFilter findFilter(Object obj);

    public PropertyFilter findPropertyFilter(Object obj, Object obj2) {
        BeanPropertyFilter findFilter = findFilter(obj);
        if (findFilter == null) {
            return null;
        }
        return SimpleBeanPropertyFilter.from(findFilter);
    }
}
