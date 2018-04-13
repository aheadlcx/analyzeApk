package com.google.tagmanager;

import com.google.analytics.midtier.proto.containertag.TypeSystem.Value;
import java.util.Map;

abstract class StringPredicate extends Predicate {
    protected abstract boolean evaluateString(String str, String str2, Map<String, Value> map);

    public StringPredicate(String str) {
        super(str);
    }

    protected boolean evaluateNoDefaultValues(Value value, Value value2, Map<String, Value> map) {
        String valueToString = Types.valueToString(value);
        String valueToString2 = Types.valueToString(value2);
        return (valueToString == Types.getDefaultString() || valueToString2 == Types.getDefaultString()) ? false : evaluateString(valueToString, valueToString2, map);
    }
}
