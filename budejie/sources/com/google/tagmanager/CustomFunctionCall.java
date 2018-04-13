package com.google.tagmanager;

import com.google.analytics.containertag.common.FunctionType;
import com.google.analytics.containertag.common.Key;
import com.google.analytics.midtier.proto.containertag.TypeSystem.Value;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

class CustomFunctionCall extends FunctionCallImplementation {
    private static final String ADDITIONAL_PARAMS = Key.ADDITIONAL_PARAMS.toString();
    private static final String FUNCTION_CALL_NAME = Key.FUNCTION_CALL_NAME.toString();
    private static final String ID = FunctionType.FUNCTION_CALL.toString();
    private final CustomEvaluator mFunctionCallEvaluator;

    public interface CustomEvaluator {
        Object evaluate(String str, Map<String, Object> map);
    }

    public static String getFunctionId() {
        return ID;
    }

    public static String getFunctionCallNameKey() {
        return FUNCTION_CALL_NAME;
    }

    public static String getAdditionalParamsKey() {
        return ADDITIONAL_PARAMS;
    }

    public CustomFunctionCall(CustomEvaluator customEvaluator) {
        super(ID, FUNCTION_CALL_NAME);
        this.mFunctionCallEvaluator = customEvaluator;
    }

    public boolean isCacheable() {
        return false;
    }

    public Value evaluate(Map<String, Value> map) {
        String valueToString = Types.valueToString((Value) map.get(FUNCTION_CALL_NAME));
        Map hashMap = new HashMap();
        Value value = (Value) map.get(ADDITIONAL_PARAMS);
        if (value != null) {
            Object valueToObject = Types.valueToObject(value);
            if (valueToObject instanceof Map) {
                for (Entry entry : ((Map) valueToObject).entrySet()) {
                    hashMap.put(entry.getKey().toString(), entry.getValue());
                }
            } else {
                Log.w("FunctionCallMacro: expected ADDITIONAL_PARAMS to be a map.");
                return Types.getDefaultValue();
            }
        }
        try {
            return Types.objectToValue(this.mFunctionCallEvaluator.evaluate(valueToString, hashMap));
        } catch (Exception e) {
            Log.w("Custom macro/tag " + valueToString + " threw exception " + e.getMessage());
            return Types.getDefaultValue();
        }
    }
}
