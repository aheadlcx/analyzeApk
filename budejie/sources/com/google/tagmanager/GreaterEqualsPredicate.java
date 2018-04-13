package com.google.tagmanager;

import com.google.analytics.containertag.common.FunctionType;
import com.google.analytics.midtier.proto.containertag.TypeSystem.Value;
import java.util.Map;

class GreaterEqualsPredicate extends NumberPredicate {
    private static final String ID = FunctionType.GREATER_EQUALS.toString();

    public static String getFunctionId() {
        return ID;
    }

    public GreaterEqualsPredicate() {
        super(ID);
    }

    protected boolean evaluateNumber(TypedNumber typedNumber, TypedNumber typedNumber2, Map<String, Value> map) {
        return typedNumber.compareTo(typedNumber2) >= 0;
    }
}
