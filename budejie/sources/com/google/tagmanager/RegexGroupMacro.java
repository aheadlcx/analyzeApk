package com.google.tagmanager;

import com.google.analytics.containertag.common.FunctionType;
import com.google.analytics.containertag.common.Key;
import com.google.analytics.midtier.proto.containertag.TypeSystem.Value;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

class RegexGroupMacro extends FunctionCallImplementation {
    private static final String GROUP = Key.GROUP.toString();
    private static final String ID = FunctionType.REGEX_GROUP.toString();
    private static final String IGNORE_CASE = Key.IGNORE_CASE.toString();
    private static final String REGEX = Key.ARG1.toString();
    private static final String TO_MATCH = Key.ARG0.toString();

    public static String getFunctionId() {
        return ID;
    }

    public RegexGroupMacro() {
        super(ID, TO_MATCH, REGEX);
    }

    public boolean isCacheable() {
        return true;
    }

    public Value evaluate(Map<String, Value> map) {
        Value value = (Value) map.get(TO_MATCH);
        Value value2 = (Value) map.get(REGEX);
        if (value == null || value == Types.getDefaultValue() || value2 == null || value2 == Types.getDefaultValue()) {
            return Types.getDefaultValue();
        }
        int intValue;
        int i = 64;
        if (Types.valueToBoolean((Value) map.get(IGNORE_CASE)).booleanValue()) {
            i = 66;
        }
        Value value3 = (Value) map.get(GROUP);
        if (value3 != null) {
            Long valueToInt64 = Types.valueToInt64(value3);
            if (valueToInt64 == Types.getDefaultInt64()) {
                return Types.getDefaultValue();
            }
            intValue = valueToInt64.intValue();
            if (intValue < 0) {
                return Types.getDefaultValue();
            }
        }
        intValue = 1;
        try {
            CharSequence valueToString = Types.valueToString(value);
            Object obj = null;
            Matcher matcher = Pattern.compile(Types.valueToString(value2), i).matcher(valueToString);
            if (matcher.find() && matcher.groupCount() >= intValue) {
                obj = matcher.group(intValue);
            }
            return obj == null ? Types.getDefaultValue() : Types.objectToValue(obj);
        } catch (PatternSyntaxException e) {
            return Types.getDefaultValue();
        }
    }
}
