package com.google.tagmanager;

import com.google.analytics.containertag.common.FunctionType;
import com.google.analytics.containertag.common.Key;
import com.google.analytics.midtier.proto.containertag.TypeSystem.Value;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

class RegexPredicate extends StringPredicate {
    private static final String ID = FunctionType.REGEX.toString();
    private static final String IGNORE_CASE = Key.IGNORE_CASE.toString();

    public static String getFunctionId() {
        return ID;
    }

    public static String getIgnoreCaseKey() {
        return IGNORE_CASE;
    }

    public RegexPredicate() {
        super(ID);
    }

    protected boolean evaluateString(String str, String str2, Map<String, Value> map) {
        int i;
        if (Types.valueToBoolean((Value) map.get(IGNORE_CASE)).booleanValue()) {
            i = 66;
        } else {
            i = 64;
        }
        try {
            return Pattern.compile(str2, i).matcher(str).find();
        } catch (PatternSyntaxException e) {
            return false;
        }
    }
}
