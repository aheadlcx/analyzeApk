package com.google.tagmanager;

import com.google.analytics.midtier.proto.containertag.TypeSystem.Value;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

class ValueEscapeUtil {
    private ValueEscapeUtil() {
    }

    static ObjectAndStatic<Value> applyEscapings(ObjectAndStatic<Value> objectAndStatic, int... iArr) {
        for (int applyEscaping : iArr) {
            objectAndStatic = applyEscaping(objectAndStatic, applyEscaping);
        }
        return objectAndStatic;
    }

    static String urlEncode(String str) throws UnsupportedEncodingException {
        return URLEncoder.encode(str, "UTF-8").replaceAll("\\+", "%20");
    }

    private static ObjectAndStatic<Value> applyEscaping(ObjectAndStatic<Value> objectAndStatic, int i) {
        if (isValidStringType((Value) objectAndStatic.getObject())) {
            switch (i) {
                case 12:
                    return escapeUri(objectAndStatic);
                default:
                    Log.e("Unsupported Value Escaping: " + i);
                    return objectAndStatic;
            }
        }
        Log.e("Escaping can only be applied to strings.");
        return objectAndStatic;
    }

    private static ObjectAndStatic<Value> escapeUri(ObjectAndStatic<Value> objectAndStatic) {
        try {
            return new ObjectAndStatic(Types.objectToValue(urlEncode(Types.valueToString((Value) objectAndStatic.getObject()))), objectAndStatic.isStatic());
        } catch (Throwable e) {
            Log.e("Escape URI: unsupported encoding", e);
            return objectAndStatic;
        }
    }

    private static boolean isValidStringType(Value value) {
        return Types.valueToObject(value) instanceof String;
    }
}
