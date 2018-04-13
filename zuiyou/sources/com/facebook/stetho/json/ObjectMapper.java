package com.facebook.stetho.json;

import com.facebook.stetho.common.ExceptionUtil;
import com.facebook.stetho.json.annotation.JsonProperty;
import com.facebook.stetho.json.annotation.JsonValue;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ObjectMapper {
    @GuardedBy
    private final Map<Class<?>, Method> mJsonValueMethodCache = new IdentityHashMap();

    public <T> T convertValue(Object obj, Class<T> cls) throws IllegalArgumentException {
        if (obj == null) {
            return null;
        }
        if (cls != Object.class && cls.isAssignableFrom(obj.getClass())) {
            return obj;
        }
        try {
            if (obj instanceof JSONObject) {
                return _convertFromJSONObject((JSONObject) obj, cls);
            }
            if (cls == JSONObject.class) {
                return _convertToJSONObject(obj);
            }
            throw new IllegalArgumentException("Expecting either fromValue or toValueType to be a JSONObject");
        } catch (Throwable e) {
            throw new IllegalArgumentException(e);
        } catch (Throwable e2) {
            throw new IllegalArgumentException(e2);
        } catch (Throwable e22) {
            throw new IllegalArgumentException(e22);
        } catch (Throwable e222) {
            throw new IllegalArgumentException(e222);
        } catch (InvocationTargetException e3) {
            throw ExceptionUtil.propagate(e3.getCause());
        }
    }

    private <T> T _convertFromJSONObject(JSONObject jSONObject, Class<T> cls) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, JSONException {
        Constructor declaredConstructor = cls.getDeclaredConstructor((Class[]) null);
        declaredConstructor.setAccessible(true);
        T newInstance = declaredConstructor.newInstance(new Object[0]);
        Field[] fields = cls.getFields();
        for (Field field : fields) {
            if (!Modifier.isStatic(field.getModifiers())) {
                Object valueForField = getValueForField(field, jSONObject.opt(field.getName()));
                try {
                    field.set(newInstance, valueForField);
                } catch (Throwable e) {
                    throw new IllegalArgumentException("Class: " + cls.getSimpleName() + " Field: " + field.getName() + " type " + (valueForField != null ? valueForField.getClass().getName() : "null"), e);
                }
            }
        }
        return newInstance;
    }

    private Object getValueForField(Field field, Object obj) throws JSONException {
        if (obj == null) {
            return obj;
        }
        try {
            if (obj == JSONObject.NULL) {
                return null;
            }
            if (obj.getClass() == field.getType()) {
                return obj;
            }
            if (obj instanceof JSONObject) {
                return convertValue(obj, field.getType());
            }
            if (field.getType().isEnum()) {
                return getEnumValue((String) obj, field.getType().asSubclass(Enum.class));
            }
            if (obj instanceof JSONArray) {
                return convertArrayToList(field, (JSONArray) obj);
            }
            if (!(obj instanceof Number)) {
                return obj;
            }
            Number number = (Number) obj;
            Class type = field.getType();
            if (type == Integer.class || type == Integer.TYPE) {
                return Integer.valueOf(number.intValue());
            }
            if (type == Long.class || type == Long.TYPE) {
                return Long.valueOf(number.longValue());
            }
            if (type == Double.class || type == Double.TYPE) {
                return Double.valueOf(number.doubleValue());
            }
            if (type == Float.class || type == Float.TYPE) {
                return Float.valueOf(number.floatValue());
            }
            if (type == Byte.class || type == Byte.TYPE) {
                return Byte.valueOf(number.byteValue());
            }
            if (type == Short.class || type == Short.TYPE) {
                return Short.valueOf(number.shortValue());
            }
            throw new IllegalArgumentException("Not setup to handle class " + type.getName());
        } catch (Throwable e) {
            throw new IllegalArgumentException("Unable to set value for field " + field.getName(), e);
        }
    }

    private Enum getEnumValue(String str, Class<? extends Enum> cls) {
        Method jsonValueMethod = getJsonValueMethod(cls);
        if (jsonValueMethod != null) {
            return getEnumByMethod(str, cls, jsonValueMethod);
        }
        return Enum.valueOf(cls, str);
    }

    private Enum getEnumByMethod(String str, Class<? extends Enum> cls, Method method) {
        int i = 0;
        Enum[] enumArr = (Enum[]) cls.getEnumConstants();
        while (i < enumArr.length) {
            Enum enumR = enumArr[i];
            try {
                Object invoke = method.invoke(enumR, new Object[0]);
                if (invoke != null && invoke.toString().equals(str)) {
                    return enumR;
                }
                i++;
            } catch (Throwable e) {
                throw new IllegalArgumentException(e);
            }
        }
        throw new IllegalArgumentException("No enum constant " + cls.getName() + "." + str);
    }

    private List<Object> convertArrayToList(Field field, JSONArray jSONArray) throws IllegalAccessException, JSONException {
        int i = 0;
        if (List.class.isAssignableFrom(field.getType())) {
            Type[] actualTypeArguments = ((ParameterizedType) field.getGenericType()).getActualTypeArguments();
            if (actualTypeArguments.length != 1) {
                throw new IllegalArgumentException("Only able to handle a single type in a list " + field.getName());
            }
            Class cls = (Class) actualTypeArguments[0];
            List<Object> arrayList = new ArrayList();
            while (i < jSONArray.length()) {
                if (cls.isEnum()) {
                    arrayList.add(getEnumValue(jSONArray.getString(i), cls));
                } else if (canDirectlySerializeClass(cls)) {
                    arrayList.add(jSONArray.get(i));
                } else {
                    JSONObject jSONObject = jSONArray.getJSONObject(i);
                    if (jSONObject == null) {
                        arrayList.add(null);
                    } else {
                        arrayList.add(convertValue(jSONObject, cls));
                    }
                }
                i++;
            }
            return arrayList;
        }
        throw new IllegalArgumentException("only know how to deserialize List<?> on field " + field.getName());
    }

    private JSONObject _convertToJSONObject(Object obj) throws JSONException, InvocationTargetException, IllegalAccessException {
        JSONObject jSONObject = new JSONObject();
        Field[] fields = obj.getClass().getFields();
        for (Field field : fields) {
            if (!Modifier.isStatic(field.getModifiers())) {
                JsonProperty jsonProperty = (JsonProperty) field.getAnnotation(JsonProperty.class);
                if (jsonProperty != null) {
                    Object obj2;
                    Object obj3 = field.get(obj);
                    Class type = field.getType();
                    if (obj3 != null) {
                        type = obj3.getClass();
                    }
                    String name = field.getName();
                    if (jsonProperty.required() && obj3 == null) {
                        obj2 = JSONObject.NULL;
                    } else if (obj3 == JSONObject.NULL) {
                        obj2 = obj3;
                    } else {
                        obj2 = getJsonValue(obj3, type, field);
                    }
                    jSONObject.put(name, obj2);
                }
            }
        }
        return jSONObject;
    }

    private Object getJsonValue(Object obj, Class<?> cls, Field field) throws InvocationTargetException, IllegalAccessException {
        if (obj == null) {
            return null;
        }
        if (List.class.isAssignableFrom(cls)) {
            return convertListToJsonArray(obj);
        }
        Method jsonValueMethod = getJsonValueMethod(cls);
        if (jsonValueMethod != null) {
            return jsonValueMethod.invoke(obj, new Object[0]);
        }
        if (!canDirectlySerializeClass(cls)) {
            return convertValue(obj, JSONObject.class);
        }
        if (!cls.equals(Double.class) && !cls.equals(Float.class)) {
            return obj;
        }
        double doubleValue = ((Number) obj).doubleValue();
        if (Double.isNaN(doubleValue)) {
            return "NaN";
        }
        if (doubleValue == Double.POSITIVE_INFINITY) {
            return "Infinity";
        }
        if (doubleValue == Double.NEGATIVE_INFINITY) {
            return "-Infinity";
        }
        return obj;
    }

    private JSONArray convertListToJsonArray(Object obj) throws InvocationTargetException, IllegalAccessException {
        JSONArray jSONArray = new JSONArray();
        for (Object next : (List) obj) {
            jSONArray.put(next != null ? getJsonValue(next, next.getClass(), null) : null);
        }
        return jSONArray;
    }

    @Nullable
    private Method getJsonValueMethod(Class<?> cls) {
        Method method;
        synchronized (this.mJsonValueMethodCache) {
            method = (Method) this.mJsonValueMethodCache.get(cls);
            if (method == null && !this.mJsonValueMethodCache.containsKey(cls)) {
                method = getJsonValueMethodImpl(cls);
                this.mJsonValueMethodCache.put(cls, method);
            }
        }
        return method;
    }

    @Nullable
    private static Method getJsonValueMethodImpl(Class<?> cls) {
        Method[] methods = cls.getMethods();
        for (int i = 0; i < methods.length; i++) {
            if (methods[i].getAnnotation(JsonValue.class) != null) {
                return methods[i];
            }
        }
        return null;
    }

    private static boolean canDirectlySerializeClass(Class cls) {
        return isWrapperOrPrimitiveType(cls) || cls.equals(String.class);
    }

    private static boolean isWrapperOrPrimitiveType(Class<?> cls) {
        if (cls.isPrimitive() || cls.equals(Boolean.class) || cls.equals(Integer.class) || cls.equals(Character.class) || cls.equals(Byte.class) || cls.equals(Short.class) || cls.equals(Double.class) || cls.equals(Long.class) || cls.equals(Float.class)) {
            return true;
        }
        return false;
    }
}
