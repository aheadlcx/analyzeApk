package com.google.tagmanager;

import com.google.analytics.containertag.common.FunctionType;
import com.google.analytics.containertag.common.Key;
import com.google.analytics.midtier.proto.containertag.TypeSystem.Value;
import java.util.Map;

class RandomMacro extends FunctionCallImplementation {
    private static final String ID = FunctionType.RANDOM.toString();
    private static final String MAX = Key.MAX.toString();
    private static final String MIN = Key.MIN.toString();

    public static String getFunctionId() {
        return ID;
    }

    public RandomMacro() {
        super(ID, new String[0]);
    }

    public boolean isCacheable() {
        return false;
    }

    public Value evaluate(Map<String, Value> map) {
        double doubleValue;
        double d;
        Value value = (Value) map.get(MIN);
        Value value2 = (Value) map.get(MAX);
        if (!(value == null || value == Types.getDefaultValue() || value2 == null || value2 == Types.getDefaultValue())) {
            TypedNumber valueToNumber = Types.valueToNumber(value);
            TypedNumber valueToNumber2 = Types.valueToNumber(value2);
            if (!(valueToNumber == Types.getDefaultNumber() || valueToNumber2 == Types.getDefaultNumber())) {
                double doubleValue2 = valueToNumber.doubleValue();
                doubleValue = valueToNumber2.doubleValue();
                if (doubleValue2 <= doubleValue) {
                    d = doubleValue2;
                    return Types.objectToValue(Long.valueOf(Math.round(((doubleValue - d) * Math.random()) + d)));
                }
            }
        }
        doubleValue = 2.147483647E9d;
        d = 0.0d;
        return Types.objectToValue(Long.valueOf(Math.round(((doubleValue - d) * Math.random()) + d)));
    }
}
