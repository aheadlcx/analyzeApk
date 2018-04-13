package com.ali.auth.third.core.util;

import android.util.Base64;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JSONUtils {
    public static Integer optInteger(JSONObject jSONObject, String str) {
        return jSONObject.has(str) ? Integer.valueOf(jSONObject.optInt(str)) : null;
    }

    public static String optString(JSONObject jSONObject, String str) {
        return jSONObject.has(str) ? jSONObject.optString(str) : null;
    }

    public static Long optLong(JSONObject jSONObject, String str) {
        return jSONObject.has(str) ? Long.valueOf(jSONObject.optLong(str)) : null;
    }

    public static Boolean optBoolean(JSONObject jSONObject, String str) {
        return Boolean.valueOf(jSONObject.has(str) ? jSONObject.optBoolean(str) : false);
    }

    public static String toJson(Map<String, Object> map) {
        return toJsonObject(map).toString();
    }

    public static String toJsonString(Map<String, String> map) {
        return toJsonObject(map).toString();
    }

    public static JSONObject toJsonObject(Map<String, ? extends Object> map) {
        JSONObject jSONObject = new JSONObject();
        try {
            for (Entry entry : map.entrySet()) {
                Object value = entry.getValue();
                if (value != null) {
                    if (value instanceof Map) {
                        jSONObject.put((String) entry.getKey(), toJsonObject((Map) value));
                    } else if (value instanceof List) {
                        jSONObject.put((String) entry.getKey(), toJsonArray((List) value));
                    } else if (value.getClass().isArray()) {
                        jSONObject.put((String) entry.getKey(), toJsonArray((Object[]) value));
                    } else {
                        jSONObject.put((String) entry.getKey(), value);
                    }
                }
            }
            return jSONObject;
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    public static JSONArray toJsonArray(Object[] objArr) {
        JSONArray jSONArray = new JSONArray();
        for (Object obj : objArr) {
            if (obj instanceof Map) {
                jSONArray.put(toJsonObject((Map) obj));
            } else {
                jSONArray.put(obj);
            }
        }
        return jSONArray;
    }

    public static JSONArray toJsonArray(List<Object> list) {
        JSONArray jSONArray = new JSONArray();
        for (Object next : list) {
            if (next instanceof Map) {
                jSONArray.put(toJsonObject((Map) next));
            } else {
                jSONArray.put(next);
            }
        }
        return jSONArray;
    }

    public static Map<String, Object> toMap(JSONObject jSONObject) throws JSONException {
        Map<String, Object> hashMap = new HashMap();
        if (jSONObject == null) {
            return hashMap;
        }
        Iterator keys = jSONObject.keys();
        while (keys.hasNext()) {
            String str = (String) keys.next();
            Object opt = jSONObject.opt(str);
            if (opt instanceof JSONObject) {
                hashMap.put(str, toMap((JSONObject) opt));
            } else if (opt instanceof JSONArray) {
                hashMap.put(str, toList((JSONArray) opt));
            } else {
                hashMap.put(str, opt);
            }
        }
        return hashMap;
    }

    public static List<Object> toList(JSONArray jSONArray) throws JSONException {
        if (jSONArray == null) {
            return Collections.emptyList();
        }
        List<Object> arrayList = new ArrayList(jSONArray.length());
        int length = jSONArray.length();
        for (int i = 0; i < length; i++) {
            Object obj = jSONArray.get(i);
            if (obj instanceof JSONObject) {
                arrayList.add(toMap((JSONObject) obj));
            } else if (obj instanceof JSONArray) {
                arrayList.add(toList((JSONArray) obj));
            } else {
                arrayList.add(jSONArray.get(i));
            }
        }
        return arrayList;
    }

    public static String[] toStringArray(JSONArray jSONArray) throws JSONException {
        int i = 0;
        if (jSONArray == null) {
            return new String[0];
        }
        String[] strArr = new String[jSONArray.length()];
        int length = jSONArray.length();
        while (i < length) {
            strArr[i] = jSONArray.optString(i);
            i++;
        }
        return strArr;
    }

    public static <T> T parseStringValue(String str, Class<T> cls) {
        int i = 0;
        if (str == null || cls == null) {
            return null;
        }
        if (String.class.equals(cls)) {
            return str;
        }
        if (Short.TYPE.equals(cls) || Short.class.equals(cls)) {
            return Short.valueOf(str);
        }
        if (Integer.TYPE.equals(cls) || Integer.class.equals(cls)) {
            return Integer.valueOf(str);
        }
        if (Long.TYPE.equals(cls) || Long.class.equals(cls)) {
            return Long.valueOf(str);
        }
        if (Boolean.TYPE.equals(cls) || Boolean.class.equals(cls)) {
            return Boolean.valueOf(str);
        }
        if (Float.TYPE.equals(cls) || Float.class.equals(cls)) {
            return Float.valueOf(str);
        }
        if (Double.TYPE.equals(cls) || Double.class.equals(cls)) {
            return Double.valueOf(str);
        }
        if (Byte.TYPE.equals(cls) || Byte.class.equals(cls)) {
            return Byte.valueOf(str);
        }
        if (Character.TYPE.equals(cls) || Character.class.equals(cls)) {
            return Character.valueOf(str.charAt(0));
        }
        if (Date.class.isAssignableFrom(cls)) {
            try {
                return new SimpleDateFormat("yyyyMMddHHmmssSSSZ", Locale.US).parse(str);
            } catch (Throwable e) {
                throw new RuntimeException("Parse Date error", e);
            }
        }
        char charAt = str.charAt(0);
        if (cls.isArray()) {
            Class componentType = cls.getComponentType();
            if (charAt == '[') {
                try {
                    return toPOJOArray(new JSONArray(str), componentType);
                } catch (Throwable e2) {
                    throw new RuntimeException(e2);
                }
            } else if (String.class.equals(componentType)) {
                return str.split(",");
            } else {
                if (Character.TYPE.equals(componentType)) {
                    return str.toCharArray();
                }
                if (Character.class.equals(componentType)) {
                    char[] toCharArray = str.toCharArray();
                    str = new Character[toCharArray.length];
                    while (i < str.length) {
                        str[i] = Character.valueOf(toCharArray[i]);
                        i++;
                    }
                    return str;
                } else if (Byte.TYPE.equals(componentType)) {
                    return Base64.decode(str, 0);
                } else {
                    if (!Byte.class.equals(componentType)) {
                        return null;
                    }
                    byte[] decode = Base64.decode(str, 0);
                    str = new Byte[decode.length];
                    while (i < str.length) {
                        str[i] = Byte.valueOf(decode[i]);
                        i++;
                    }
                    return str;
                }
            }
        } else if (charAt == '{') {
            try {
                JSONObject jSONObject = new JSONObject(str);
                if (Map.class.isAssignableFrom(cls)) {
                    return toMap(jSONObject);
                }
                return toPOJO(jSONObject, cls);
            } catch (Throwable e22) {
                throw new RuntimeException(e22);
            }
        } else if (cls.isAssignableFrom(String.class)) {
            return str;
        } else {
            return null;
        }
    }

    public static <T> T toPOJO(JSONObject jSONObject, Class<T> cls) {
        if (jSONObject == null || cls == null || cls == Void.TYPE) {
            return null;
        }
        try {
            T newInstance = cls.newInstance();
            for (Field field : cls.getFields()) {
                Class type = field.getType();
                String name = field.getName();
                if (jSONObject.has(name)) {
                    if (!type.isPrimitive()) {
                        Object string;
                        if (type == String.class) {
                            string = jSONObject.getString(name);
                        } else if (type == Boolean.class || type == Integer.class || type == Short.class || type == Long.class || type == Double.class) {
                            string = jSONObject.get(name);
                        } else if (type.isArray()) {
                            string = toPOJOArray(jSONObject.getJSONArray(name), type.getComponentType());
                        } else if (Map.class.isAssignableFrom(type)) {
                            string = toMap(jSONObject.getJSONObject(name));
                        } else {
                            string = toPOJO(jSONObject.getJSONObject(name), type);
                        }
                        field.set(newInstance, string);
                    } else if (type == Boolean.TYPE) {
                        field.setBoolean(newInstance, jSONObject.getBoolean(name));
                    } else if (type == Byte.TYPE) {
                        field.setByte(newInstance, (byte) jSONObject.getInt(name));
                    } else if (type == Character.TYPE) {
                        String string2 = jSONObject.getString(name);
                        char charAt = (string2 == null || string2.length() == 0) ? '\u0000' : string2.charAt(0);
                        field.setChar(newInstance, charAt);
                    } else if (type == Short.TYPE) {
                        field.setShort(newInstance, (short) jSONObject.getInt(name));
                    } else if (type == Integer.TYPE) {
                        field.setInt(newInstance, jSONObject.getInt(name));
                    } else if (type == Long.TYPE) {
                        field.setLong(newInstance, jSONObject.getLong(name));
                    } else if (type == Float.TYPE) {
                        field.setFloat(newInstance, (float) jSONObject.getDouble(name));
                    } else if (type == Double.TYPE) {
                        field.setDouble(newInstance, jSONObject.getDouble(name));
                    }
                }
            }
            return newInstance;
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T[] toPOJOArray(JSONArray jSONArray, Class<T> cls) {
        if (jSONArray == null || cls == null || cls == Void.TYPE) {
            return null;
        }
        Object newInstance = Array.newInstance(cls, jSONArray.length());
        int i = 0;
        while (i < jSONArray.length()) {
            try {
                if (!cls.isPrimitive()) {
                    Object string;
                    if (cls == String.class) {
                        string = jSONArray.getString(i);
                    } else if (cls == Boolean.class || cls == Integer.class || cls == Short.class || cls == Long.class || cls == Double.class) {
                        string = jSONArray.get(i);
                    } else if (cls.isArray()) {
                        string = toPOJOArray(jSONArray.getJSONArray(i), cls.getComponentType());
                    } else if (Map.class.isAssignableFrom(cls)) {
                        string = toMap(jSONArray.getJSONObject(i));
                    } else {
                        string = toPOJO(jSONArray.getJSONObject(i), cls);
                    }
                    Array.set(newInstance, i, string);
                } else if (cls == Boolean.TYPE) {
                    Array.setBoolean(newInstance, i, jSONArray.getBoolean(i));
                } else if (cls == Byte.TYPE) {
                    Array.setByte(newInstance, i, (byte) jSONArray.getInt(i));
                } else if (cls == Character.TYPE) {
                    String string2 = jSONArray.getString(i);
                    char charAt = (string2 == null || string2.length() == 0) ? '\u0000' : string2.charAt(0);
                    Array.setChar(newInstance, i, charAt);
                } else if (cls == Short.TYPE) {
                    Array.setShort(newInstance, i, (short) jSONArray.getInt(i));
                } else if (cls == Integer.TYPE) {
                    Array.setInt(newInstance, i, jSONArray.getInt(i));
                } else if (cls == Long.TYPE) {
                    Array.setLong(newInstance, i, jSONArray.getLong(i));
                } else if (cls == Float.TYPE) {
                    Array.setFloat(newInstance, i, (float) jSONArray.getDouble(i));
                } else if (cls == Double.TYPE) {
                    Array.setDouble(newInstance, i, jSONArray.getDouble(i));
                }
                i++;
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
        }
        return (Object[]) newInstance;
    }
}
