package com.google.tagmanager;

import com.google.analytics.containertag.common.Key;
import com.google.analytics.midtier.proto.containertag.TypeSystem.Value;
import java.util.Map;

abstract class Predicate extends FunctionCallImplementation {
    private static final String ARG0 = Key.ARG0.toString();
    private static final String ARG1 = Key.ARG1.toString();

    protected abstract boolean evaluateNoDefaultValues(Value value, Value value2, Map<String, Value> map);

    public static String getArg0Key() {
        return ARG0;
    }

    public static String getArg1Key() {
        return ARG1;
    }

    public Predicate(String str) {
        super(str, ARG0, ARG1);
    }

    public Value evaluate(Map<String, Value> map) {
        for (Value value : map.values()) {
            if (value == Types.getDefaultValue()) {
                return Types.objectToValue(Boolean.valueOf(false));
            }
        }
        Value value2 = (Value) map.get(ARG0);
        Value value3 = (Value) map.get(ARG1);
        boolean evaluateNoDefaultValues = (value2 == null || value3 == null) ? false : evaluateNoDefaultValues(value2, value3, map);
        return Types.objectToValue(Boolean.valueOf(evaluateNoDefaultValues));
    }

    public boolean isCacheable() {
        return true;
    }
}
