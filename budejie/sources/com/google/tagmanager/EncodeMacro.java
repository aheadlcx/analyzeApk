package com.google.tagmanager;

import com.google.analytics.containertag.common.FunctionType;
import com.google.analytics.containertag.common.Key;
import com.google.analytics.midtier.proto.containertag.TypeSystem.Value;
import java.util.Map;

class EncodeMacro extends FunctionCallImplementation {
    private static final String ARG0 = Key.ARG0.toString();
    private static final String DEFAULT_INPUT_FORMAT = "text";
    private static final String DEFAULT_OUTPUT_FORMAT = "base16";
    private static final String ID = FunctionType.ENCODE.toString();
    private static final String INPUT_FORMAT = Key.INPUT_FORMAT.toString();
    private static final String NO_PADDING = Key.NO_PADDING.toString();
    private static final String OUTPUT_FORMAT = Key.OUTPUT_FORMAT.toString();

    public static String getFunctionId() {
        return ID;
    }

    public EncodeMacro() {
        super(ID, ARG0);
    }

    public boolean isCacheable() {
        return true;
    }

    public Value evaluate(Map<String, Value> map) {
        Value value = (Value) map.get(ARG0);
        if (value == null || value == Types.getDefaultValue()) {
            return Types.getDefaultValue();
        }
        int i;
        String valueToString = Types.valueToString(value);
        value = (Value) map.get(INPUT_FORMAT);
        String valueToString2 = value == null ? DEFAULT_INPUT_FORMAT : Types.valueToString(value);
        value = (Value) map.get(OUTPUT_FORMAT);
        String valueToString3 = value == null ? DEFAULT_OUTPUT_FORMAT : Types.valueToString(value);
        value = (Value) map.get(INPUT_FORMAT);
        value = (Value) map.get(NO_PADDING);
        if (value == null || !Types.valueToBoolean(value).booleanValue()) {
            i = 0;
        } else {
            i = 1;
        }
        try {
            byte[] bytes;
            Object encode;
            if (DEFAULT_INPUT_FORMAT.equals(valueToString2)) {
                bytes = valueToString.getBytes();
            } else if (DEFAULT_OUTPUT_FORMAT.equals(valueToString2)) {
                bytes = Base16.decode(valueToString);
            } else if ("base64".equals(valueToString2)) {
                bytes = Base64Encoder.decode(valueToString, i);
            } else if ("base64url".equals(valueToString2)) {
                bytes = Base64Encoder.decode(valueToString, i | 2);
            } else {
                Log.e("Encode: unknown input format: " + valueToString2);
                return Types.getDefaultValue();
            }
            if (DEFAULT_OUTPUT_FORMAT.equals(valueToString3)) {
                encode = Base16.encode(bytes);
            } else if ("base64".equals(valueToString3)) {
                encode = Base64Encoder.encodeToString(bytes, i);
            } else if ("base64url".equals(valueToString3)) {
                encode = Base64Encoder.encodeToString(bytes, i | 2);
            } else {
                Log.e("Encode: unknown output format: " + valueToString3);
                return Types.getDefaultValue();
            }
            return Types.objectToValue(encode);
        } catch (IllegalArgumentException e) {
            Log.e("Encode: invalid input:");
            return Types.getDefaultValue();
        }
    }
}
