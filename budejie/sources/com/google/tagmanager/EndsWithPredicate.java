package com.google.tagmanager;

import com.google.analytics.containertag.common.FunctionType;
import com.google.analytics.midtier.proto.containertag.TypeSystem.Value;
import java.util.Map;

class EndsWithPredicate extends StringPredicate {
    private static final String ID = FunctionType.ENDS_WITH.toString();

    public static String getFunctionId() {
        return ID;
    }

    public EndsWithPredicate() {
        super(ID);
    }

    protected boolean evaluateString(String str, String str2, Map<String, Value> map) {
        return str.endsWith(str2);
    }
}
