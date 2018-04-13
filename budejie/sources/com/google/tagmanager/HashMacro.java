package com.google.tagmanager;

import com.google.analytics.containertag.common.FunctionType;
import com.google.analytics.containertag.common.Key;
import com.google.analytics.midtier.proto.containertag.TypeSystem.Value;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

class HashMacro extends FunctionCallImplementation {
    private static final String ALGORITHM = Key.ALGORITHM.toString();
    private static final String ARG0 = Key.ARG0.toString();
    private static final String DEFAULT_ALGORITHM = "MD5";
    private static final String DEFAULT_INPUT_FORMAT = "text";
    private static final String ID = FunctionType.HASH.toString();
    private static final String INPUT_FORMAT = Key.INPUT_FORMAT.toString();

    public static String getFunctionId() {
        return ID;
    }

    public HashMacro() {
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
        byte[] bytes;
        String valueToString = Types.valueToString(value);
        value = (Value) map.get(ALGORITHM);
        String valueToString2 = value == null ? DEFAULT_ALGORITHM : Types.valueToString(value);
        value = (Value) map.get(INPUT_FORMAT);
        String valueToString3 = value == null ? DEFAULT_INPUT_FORMAT : Types.valueToString(value);
        if (DEFAULT_INPUT_FORMAT.equals(valueToString3)) {
            bytes = valueToString.getBytes();
        } else if ("base16".equals(valueToString3)) {
            bytes = Base16.decode(valueToString);
        } else {
            Log.e("Hash: unknown input format: " + valueToString3);
            return Types.getDefaultValue();
        }
        try {
            return Types.objectToValue(Base16.encode(hash(valueToString2, bytes)));
        } catch (NoSuchAlgorithmException e) {
            Log.e("Hash: unknown algorithm: " + valueToString2);
            return Types.getDefaultValue();
        }
    }

    private byte[] hash(String str, byte[] bArr) throws NoSuchAlgorithmException {
        MessageDigest instance = MessageDigest.getInstance(str);
        instance.update(bArr);
        return instance.digest();
    }
}
