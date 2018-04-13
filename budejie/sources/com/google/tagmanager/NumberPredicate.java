package com.google.tagmanager;

import com.google.analytics.midtier.proto.containertag.TypeSystem.Value;
import java.util.Map;

abstract class NumberPredicate extends Predicate {
    protected abstract boolean evaluateNumber(TypedNumber typedNumber, TypedNumber typedNumber2, Map<String, Value> map);

    public NumberPredicate(String str) {
        super(str);
    }

    protected boolean evaluateNoDefaultValues(Value value, Value value2, Map<String, Value> map) {
        TypedNumber valueToNumber = Types.valueToNumber(value);
        TypedNumber valueToNumber2 = Types.valueToNumber(value2);
        return (valueToNumber == Types.getDefaultNumber() || valueToNumber2 == Types.getDefaultNumber()) ? false : evaluateNumber(valueToNumber, valueToNumber2, map);
    }
}
