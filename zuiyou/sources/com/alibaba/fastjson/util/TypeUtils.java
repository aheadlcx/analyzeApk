package com.alibaba.fastjson.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.PropertyNamingStrategy;
import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.annotation.JSONType;
import com.alibaba.fastjson.parser.JSONLexer;
import com.alibaba.fastjson.parser.JavaBeanDeserializer;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.iflytek.speech.VoiceWakeuperAidl;
import java.lang.annotation.Annotation;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.security.AccessControlException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Currency;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class TypeUtils {
    public static boolean compatibleWithJavaBean = false;
    private static volatile Map<Class, String[]> kotlinIgnores;
    private static volatile boolean kotlinIgnores_error;
    private static volatile boolean kotlin_class_klass_error;
    private static volatile boolean kotlin_error;
    private static volatile Constructor kotlin_kclass_constructor;
    private static volatile Method kotlin_kclass_getConstructors;
    private static volatile Method kotlin_kfunction_getParameters;
    private static volatile Method kotlin_kparameter_getName;
    private static volatile Class kotlin_metadata;
    private static volatile boolean kotlin_metadata_error;
    private static final ConcurrentMap<String, Class<?>> mappings = new ConcurrentHashMap(36, 0.75f, 1);
    private static boolean setAccessibleEnable = true;

    static {
        mappings.put("byte", Byte.TYPE);
        mappings.put("short", Short.TYPE);
        mappings.put("int", Integer.TYPE);
        mappings.put("long", Long.TYPE);
        mappings.put("float", Float.TYPE);
        mappings.put("double", Double.TYPE);
        mappings.put("boolean", Boolean.TYPE);
        mappings.put("char", Character.TYPE);
        mappings.put("[byte", byte[].class);
        mappings.put("[short", short[].class);
        mappings.put("[int", int[].class);
        mappings.put("[long", long[].class);
        mappings.put("[float", float[].class);
        mappings.put("[double", double[].class);
        mappings.put("[boolean", boolean[].class);
        mappings.put("[char", char[].class);
        mappings.put("[B", byte[].class);
        mappings.put("[S", short[].class);
        mappings.put("[I", int[].class);
        mappings.put("[J", long[].class);
        mappings.put("[F", float[].class);
        mappings.put("[D", double[].class);
        mappings.put("[C", char[].class);
        mappings.put("[Z", boolean[].class);
        mappings.put("java.util.HashMap", HashMap.class);
        mappings.put("java.util.TreeMap", TreeMap.class);
        mappings.put("java.util.Date", Date.class);
        mappings.put("com.alibaba.fastjson.JSONObject", JSONObject.class);
        mappings.put("java.util.concurrent.ConcurrentHashMap", ConcurrentHashMap.class);
        mappings.put("java.text.SimpleDateFormat", SimpleDateFormat.class);
        mappings.put("java.lang.StackTraceElement", StackTraceElement.class);
        mappings.put("java.lang.RuntimeException", RuntimeException.class);
    }

    public static boolean isKotlin(Class cls) {
        if (kotlin_metadata == null && !kotlin_metadata_error) {
            try {
                kotlin_metadata = Class.forName("kotlin.Metadata");
            } catch (Throwable th) {
                kotlin_metadata_error = true;
            }
        }
        if (kotlin_metadata == null) {
            return false;
        }
        return cls.isAnnotationPresent(kotlin_metadata);
    }

    private static boolean isKotlinIgnore(Class cls, String str) {
        if (kotlinIgnores == null && !kotlinIgnores_error) {
            try {
                Map hashMap = new HashMap();
                hashMap.put(Class.forName("kotlin.ranges.CharRange"), new String[]{"getEndInclusive", "isEmpty"});
                hashMap.put(Class.forName("kotlin.ranges.IntRange"), new String[]{"getEndInclusive", "isEmpty"});
                hashMap.put(Class.forName("kotlin.ranges.LongRange"), new String[]{"getEndInclusive", "isEmpty"});
                hashMap.put(Class.forName("kotlin.ranges.ClosedFloatRange"), new String[]{"getEndInclusive", "isEmpty"});
                hashMap.put(Class.forName("kotlin.ranges.ClosedDoubleRange"), new String[]{"getEndInclusive", "isEmpty"});
                kotlinIgnores = hashMap;
            } catch (Throwable th) {
                kotlinIgnores_error = true;
            }
        }
        if (kotlinIgnores == null) {
            return false;
        }
        String[] strArr = (String[]) kotlinIgnores.get(cls);
        if (strArr == null) {
            return false;
        }
        return Arrays.binarySearch(strArr, str) >= 0;
    }

    public static String[] getKoltinConstructorParameters(Class cls) {
        if (kotlin_kclass_constructor == null && !kotlin_class_klass_error) {
            try {
                Class cls2 = Class.forName("kotlin.reflect.jvm.internal.KClassImpl");
                kotlin_kclass_constructor = cls2.getConstructor(new Class[]{Class.class});
                kotlin_kclass_getConstructors = cls2.getMethod("getConstructors", new Class[0]);
                kotlin_kfunction_getParameters = Class.forName("kotlin.reflect.KFunction").getMethod("getParameters", new Class[0]);
                kotlin_kparameter_getName = Class.forName("kotlin.reflect.KParameter").getMethod("getName", new Class[0]);
            } catch (Throwable th) {
                kotlin_class_klass_error = true;
            }
        }
        if (kotlin_kclass_constructor == null) {
            return null;
        }
        if (kotlin_error) {
            return null;
        }
        try {
            List list;
            Iterator it = ((Iterable) kotlin_kclass_getConstructors.invoke(kotlin_kclass_constructor.newInstance(new Object[]{cls}), new Object[0])).iterator();
            Object obj = null;
            while (it.hasNext()) {
                Object obj2;
                Object next = it.next();
                list = (List) kotlin_kfunction_getParameters.invoke(next, new Object[0]);
                if (obj == null || list.size() != 0) {
                    obj2 = next;
                } else {
                    obj2 = obj;
                }
                it.hasNext();
                obj = obj2;
            }
            list = (List) kotlin_kfunction_getParameters.invoke(obj, new Object[0]);
            String[] strArr = new String[list.size()];
            for (int i = 0; i < list.size(); i++) {
                strArr[i] = (String) kotlin_kparameter_getName.invoke(list.get(i), new Object[0]);
            }
            return strArr;
        } catch (Throwable th2) {
            kotlin_error = true;
            return null;
        }
    }

    public static final String castToString(Object obj) {
        if (obj == null) {
            return null;
        }
        return obj.toString();
    }

    public static final Byte castToByte(Object obj) {
        if (obj == null) {
            return null;
        }
        if (obj instanceof Number) {
            return Byte.valueOf(((Number) obj).byteValue());
        }
        if (obj instanceof String) {
            String str = (String) obj;
            if (str.length() == 0 || "null".equals(str)) {
                return null;
            }
            return Byte.valueOf(Byte.parseByte(str));
        }
        throw new JSONException("can not cast to byte, value : " + obj);
    }

    public static final Character castToChar(Object obj) {
        if (obj == null) {
            return null;
        }
        if (obj instanceof Character) {
            return (Character) obj;
        }
        if (obj instanceof String) {
            String str = (String) obj;
            if (str.length() == 0) {
                return null;
            }
            if (str.length() == 1) {
                return Character.valueOf(str.charAt(0));
            }
            throw new JSONException("can not cast to byte, value : " + obj);
        }
        throw new JSONException("can not cast to byte, value : " + obj);
    }

    public static final Short castToShort(Object obj) {
        if (obj == null) {
            return null;
        }
        if (obj instanceof Number) {
            return Short.valueOf(((Number) obj).shortValue());
        }
        if (obj instanceof String) {
            String str = (String) obj;
            if (str.length() == 0 || "null".equals(str)) {
                return null;
            }
            return Short.valueOf(Short.parseShort(str));
        }
        throw new JSONException("can not cast to short, value : " + obj);
    }

    public static final BigDecimal castToBigDecimal(Object obj) {
        if (obj == null) {
            return null;
        }
        if (obj instanceof BigDecimal) {
            return (BigDecimal) obj;
        }
        if (obj instanceof BigInteger) {
            return new BigDecimal((BigInteger) obj);
        }
        String obj2 = obj.toString();
        if (obj2.length() == 0 || "null".equals(obj2)) {
            return null;
        }
        return new BigDecimal(obj2);
    }

    public static final BigInteger castToBigInteger(Object obj) {
        if (obj == null) {
            return null;
        }
        if (obj instanceof BigInteger) {
            return (BigInteger) obj;
        }
        if ((obj instanceof Float) || (obj instanceof Double)) {
            return BigInteger.valueOf(((Number) obj).longValue());
        }
        String obj2 = obj.toString();
        if (obj2.length() == 0 || "null".equals(obj2)) {
            return null;
        }
        return new BigInteger(obj2);
    }

    public static final Float castToFloat(Object obj) {
        if (obj == null) {
            return null;
        }
        if (obj instanceof Number) {
            return Float.valueOf(((Number) obj).floatValue());
        }
        if (obj instanceof String) {
            String obj2 = obj.toString();
            if (obj2.length() == 0 || "null".equals(obj2)) {
                return null;
            }
            return Float.valueOf(Float.parseFloat(obj2));
        }
        throw new JSONException("can not cast to float, value : " + obj);
    }

    public static final Double castToDouble(Object obj) {
        if (obj == null) {
            return null;
        }
        if (obj instanceof Number) {
            return Double.valueOf(((Number) obj).doubleValue());
        }
        if (obj instanceof String) {
            String obj2 = obj.toString();
            if (obj2.length() == 0 || "null".equals(obj2)) {
                return null;
            }
            return Double.valueOf(Double.parseDouble(obj2));
        }
        throw new JSONException("can not cast to double, value : " + obj);
    }

    public static final Date castToDate(Object obj) {
        if (obj == null) {
            return null;
        }
        if (obj instanceof Calendar) {
            return ((Calendar) obj).getTime();
        }
        if (obj instanceof Date) {
            return (Date) obj;
        }
        long j = -1;
        if (obj instanceof Number) {
            j = ((Number) obj).longValue();
        }
        if (obj instanceof String) {
            String str = (String) obj;
            if (str.indexOf(45) != -1) {
                String str2;
                if (str.length() == JSON.DEFFAULT_DATE_FORMAT.length()) {
                    str2 = JSON.DEFFAULT_DATE_FORMAT;
                } else if (str.length() == 10) {
                    str2 = "yyyy-MM-dd";
                } else if (str.length() == "yyyy-MM-dd HH:mm:ss".length()) {
                    str2 = "yyyy-MM-dd HH:mm:ss";
                } else if (str.length() == 29 && str.charAt(26) == ':' && str.charAt(28) == '0') {
                    str2 = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX";
                } else {
                    str2 = "yyyy-MM-dd HH:mm:ss.SSS";
                }
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(str2, JSON.defaultLocale);
                simpleDateFormat.setTimeZone(JSON.defaultTimeZone);
                try {
                    return simpleDateFormat.parse(str);
                } catch (ParseException e) {
                    throw new JSONException("can not cast to Date, value : " + str);
                }
            } else if (str.length() == 0 || "null".equals(str)) {
                return null;
            } else {
                j = Long.parseLong(str);
            }
        }
        if (j >= 0) {
            return new Date(j);
        }
        throw new JSONException("can not cast to Date, value : " + obj);
    }

    public static final Long castToLong(Object obj) {
        Calendar calendar;
        Calendar calendar2 = null;
        if (obj == null) {
            return calendar2;
        }
        if (obj instanceof Number) {
            return Long.valueOf(((Number) obj).longValue());
        }
        if (obj instanceof String) {
            String str = (String) obj;
            if (str.length() == 0 || "null".equals(str)) {
                return calendar2;
            }
            try {
                return Long.valueOf(Long.parseLong(str));
            } catch (NumberFormatException e) {
                JSONLexer jSONLexer = new JSONLexer(str);
                if (jSONLexer.scanISO8601DateIfMatch(false)) {
                    calendar = jSONLexer.calendar;
                } else {
                    calendar = calendar2;
                }
                jSONLexer.close();
                if (calendar != null) {
                    return Long.valueOf(calendar.getTimeInMillis());
                }
            }
        }
        throw new JSONException("can not cast to long, value : " + obj);
    }

    public static final Integer castToInt(Object obj) {
        if (obj == null) {
            return null;
        }
        if (obj instanceof Integer) {
            return (Integer) obj;
        }
        if (obj instanceof Number) {
            return Integer.valueOf(((Number) obj).intValue());
        }
        if (obj instanceof String) {
            String str = (String) obj;
            if (str.length() == 0 || "null".equals(str)) {
                return null;
            }
            return Integer.valueOf(Integer.parseInt(str));
        }
        throw new JSONException("can not cast to int, value : " + obj);
    }

    public static final byte[] castToBytes(Object obj) {
        if (obj instanceof byte[]) {
            return (byte[]) obj;
        }
        if (obj instanceof String) {
            String str = (String) obj;
            return JSONLexer.decodeFast(str, 0, str.length());
        }
        throw new JSONException("can not cast to int, value : " + obj);
    }

    public static final Boolean castToBoolean(Object obj) {
        boolean z = true;
        if (obj == null) {
            return null;
        }
        if (obj instanceof Boolean) {
            return (Boolean) obj;
        }
        if (obj instanceof Number) {
            if (((Number) obj).intValue() != 1) {
                z = false;
            }
            return Boolean.valueOf(z);
        }
        if (obj instanceof String) {
            String str = (String) obj;
            if (str.length() == 0 || "null".equals(str)) {
                return null;
            }
            if ("true".equalsIgnoreCase(str) || "1".equals(str)) {
                return Boolean.TRUE;
            }
            if ("false".equalsIgnoreCase(str) || "0".equals(str)) {
                return Boolean.FALSE;
            }
        }
        throw new JSONException("can not cast to int, value : " + obj);
    }

    public static final <T> T castToJavaBean(Object obj, Class<T> cls) {
        return cast(obj, (Class) cls, ParserConfig.global);
    }

    public static final <T> T cast(Object obj, Class<T> cls, ParserConfig parserConfig) {
        return cast(obj, cls, parserConfig, 0);
    }

    public static final <T> T cast(Object obj, Class<T> cls, ParserConfig parserConfig, int i) {
        if (obj == null) {
            return null;
        }
        if (cls == null) {
            throw new IllegalArgumentException("clazz is null");
        } else if (cls == obj.getClass()) {
            return obj;
        } else {
            if (!(obj instanceof Map)) {
                if (cls.isArray()) {
                    if (obj instanceof Collection) {
                        Collection<Object> collection = (Collection) obj;
                        Object newInstance = Array.newInstance(cls.getComponentType(), collection.size());
                        int i2 = 0;
                        for (Object cast : collection) {
                            Array.set(newInstance, i2, cast(cast, cls.getComponentType(), parserConfig));
                            i2++;
                        }
                        return newInstance;
                    } else if (cls == byte[].class) {
                        return castToBytes(obj);
                    }
                }
                if (cls.isAssignableFrom(obj.getClass())) {
                    return obj;
                }
                if (cls == Boolean.TYPE || cls == Boolean.class) {
                    return castToBoolean(obj);
                }
                if (cls == Byte.TYPE || cls == Byte.class) {
                    return castToByte(obj);
                }
                if ((cls == Character.TYPE || cls == Character.class) && (obj instanceof String)) {
                    String str = (String) obj;
                    if (str.length() == 1) {
                        return Character.valueOf(str.charAt(0));
                    }
                }
                if (cls == Short.TYPE || cls == Short.class) {
                    return castToShort(obj);
                }
                if (cls == Integer.TYPE || cls == Integer.class) {
                    return castToInt(obj);
                }
                if (cls == Long.TYPE || cls == Long.class) {
                    return castToLong(obj);
                }
                if (cls == Float.TYPE || cls == Float.class) {
                    return castToFloat(obj);
                }
                if (cls == Double.TYPE || cls == Double.class) {
                    return castToDouble(obj);
                }
                if (cls == String.class) {
                    return castToString(obj);
                }
                if (cls == BigDecimal.class) {
                    return castToBigDecimal(obj);
                }
                if (cls == BigInteger.class) {
                    return castToBigInteger(obj);
                }
                if (cls == Date.class) {
                    return castToDate(obj);
                }
                if (cls.isEnum()) {
                    return castToEnum(obj, cls, parserConfig);
                }
                if (Calendar.class.isAssignableFrom(cls)) {
                    Calendar instance;
                    Date castToDate = castToDate(obj);
                    if (cls == Calendar.class) {
                        instance = Calendar.getInstance(JSON.defaultTimeZone, JSON.defaultLocale);
                    } else {
                        try {
                            instance = (Calendar) cls.newInstance();
                        } catch (Throwable e) {
                            throw new JSONException("can not cast to : " + cls.getName(), e);
                        }
                    }
                    instance.setTime(castToDate);
                    return instance;
                }
                if (obj instanceof String) {
                    String str2 = (String) obj;
                    if (str2.length() == 0 || "null".equals(str2)) {
                        return null;
                    }
                    if (cls == Currency.class) {
                        return Currency.getInstance(str2);
                    }
                }
                throw new JSONException("can not cast to : " + cls.getName());
            } else if (cls == Map.class) {
                return obj;
            } else {
                Map map = (Map) obj;
                if (cls != Object.class || map.containsKey(JSON.DEFAULT_TYPE_KEY)) {
                    return castToJavaBean((Map) obj, cls, parserConfig, i);
                }
                return obj;
            }
        }
    }

    public static final <T> T castToEnum(Object obj, Class<T> cls, ParserConfig parserConfig) {
        try {
            if (obj instanceof String) {
                String str = (String) obj;
                if (str.length() == 0) {
                    return null;
                }
                return Enum.valueOf(cls, str);
            }
            if (obj instanceof Number) {
                int intValue = ((Number) obj).intValue();
                Object[] enumConstants = cls.getEnumConstants();
                if (intValue < enumConstants.length) {
                    return enumConstants[intValue];
                }
            }
            throw new JSONException("can not cast to : " + cls.getName());
        } catch (Throwable e) {
            throw new JSONException("can not cast to : " + cls.getName(), e);
        }
    }

    public static final <T> T cast(Object obj, Type type, ParserConfig parserConfig) {
        if (obj == null) {
            return null;
        }
        if (type instanceof Class) {
            return cast(obj, (Class) type, parserConfig);
        }
        if (type instanceof ParameterizedType) {
            return cast(obj, (ParameterizedType) type, parserConfig);
        }
        if (obj instanceof String) {
            String str = (String) obj;
            if (str.length() == 0 || "null".equals(str)) {
                return null;
            }
        }
        if (type instanceof TypeVariable) {
            return obj;
        }
        throw new JSONException("can not cast to : " + type);
    }

    public static final <T> T cast(Object obj, ParameterizedType parameterizedType, ParserConfig parserConfig) {
        Type type;
        Type rawType = parameterizedType.getRawType();
        if (rawType == Set.class || rawType == HashSet.class || rawType == TreeSet.class || rawType == List.class || rawType == ArrayList.class) {
            type = parameterizedType.getActualTypeArguments()[0];
            if (obj instanceof Iterable) {
                T hashSet;
                if (rawType == Set.class || rawType == HashSet.class) {
                    hashSet = new HashSet();
                } else if (rawType == TreeSet.class) {
                    hashSet = new TreeSet();
                } else {
                    hashSet = new ArrayList();
                }
                for (Object cast : (Iterable) obj) {
                    hashSet.add(cast(cast, type, parserConfig));
                }
                return hashSet;
            }
        }
        if (rawType == Map.class || rawType == HashMap.class) {
            type = parameterizedType.getActualTypeArguments()[0];
            Type type2 = parameterizedType.getActualTypeArguments()[1];
            if (obj instanceof Map) {
                T hashMap = new HashMap();
                for (Entry entry : ((Map) obj).entrySet()) {
                    hashMap.put(cast(entry.getKey(), type, parserConfig), cast(entry.getValue(), type2, parserConfig));
                }
                return hashMap;
            }
        }
        if (obj instanceof String) {
            String str = (String) obj;
            if (str.length() == 0 || "null".equals(str)) {
                return null;
            }
        }
        if (parameterizedType.getActualTypeArguments().length == 1 && (parameterizedType.getActualTypeArguments()[0] instanceof WildcardType)) {
            return cast(obj, rawType, parserConfig);
        }
        throw new JSONException("can not cast to : " + parameterizedType);
    }

    public static final <T> T castToJavaBean(Map<String, Object> map, Class<T> cls, ParserConfig parserConfig) {
        return castToJavaBean(map, cls, parserConfig, 0);
    }

    public static final <T> T castToJavaBean(Map<String, Object> map, Class<T> cls, ParserConfig parserConfig, int i) {
        int i2 = 0;
        try {
            String str;
            if (cls == StackTraceElement.class) {
                str = (String) map.get("className");
                String str2 = (String) map.get("methodName");
                String str3 = (String) map.get("fileName");
                Number number = (Number) map.get("lineNumber");
                if (number != null) {
                    i2 = number.intValue();
                }
                return new StackTraceElement(str, str2, str3, i2);
            }
            Object obj = map.get(JSON.DEFAULT_TYPE_KEY);
            if (obj instanceof String) {
                str = (String) obj;
                if (parserConfig == null) {
                    parserConfig = ParserConfig.global;
                }
                Class checkAutoType = parserConfig.checkAutoType(str, null, i);
                if (checkAutoType == null) {
                    throw new ClassNotFoundException(str + " not found");
                } else if (!checkAutoType.equals(cls)) {
                    return castToJavaBean(map, checkAutoType, parserConfig, i);
                }
            }
            ParserConfig parserConfig2 = parserConfig;
            if (cls.isInterface()) {
                if (map instanceof JSONObject) {
                    map = (JSONObject) map;
                } else {
                    Object jSONObject = new JSONObject((Map) map);
                }
                if (parserConfig2 == null) {
                    parserConfig2 = ParserConfig.getGlobalInstance();
                }
                if (parserConfig2.getDeserializer(cls) != null) {
                    return JSON.parseObject(JSON.toJSONString(map), cls);
                }
                return Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class[]{cls}, map);
            } else if (cls == String.class && (map instanceof JSONObject)) {
                return map.toString();
            } else {
                ParserConfig parserConfig3;
                JavaBeanDeserializer javaBeanDeserializer;
                if (parserConfig2 == null) {
                    parserConfig3 = ParserConfig.global;
                } else {
                    parserConfig3 = parserConfig2;
                }
                ObjectDeserializer deserializer = parserConfig3.getDeserializer(cls);
                if (deserializer instanceof JavaBeanDeserializer) {
                    javaBeanDeserializer = (JavaBeanDeserializer) deserializer;
                } else {
                    javaBeanDeserializer = null;
                }
                if (javaBeanDeserializer != null) {
                    return javaBeanDeserializer.createInstance((Map) map, parserConfig3);
                }
                throw new JSONException("can not get javaBeanDeserializer");
            }
        } catch (Throwable e) {
            throw new JSONException(e.getMessage(), e);
        }
    }

    public static Class<?> getClassFromMapping(String str) {
        return (Class) mappings.get(str);
    }

    public static Class<?> loadClass(String str, ClassLoader classLoader) {
        return loadClass(str, classLoader, true);
    }

    public static Class<?> loadClass(String str, ClassLoader classLoader, boolean z) {
        Exception exception;
        Exception e;
        Class<?> cls;
        Exception exception2;
        if (str == null || str.length() == 0) {
            return null;
        }
        if (str.length() >= 256) {
            throw new JSONException("className too long. " + str);
        }
        Class<?> cls2 = (Class) mappings.get(str);
        if (cls2 != null) {
            return cls2;
        }
        if (str.charAt(0) == '[') {
            return Array.newInstance(loadClass(str.substring(1), classLoader), 0).getClass();
        }
        if (str.startsWith("L") && str.endsWith(VoiceWakeuperAidl.PARAMS_SEPARATE)) {
            return loadClass(str.substring(1, str.length() - 1), classLoader);
        }
        if (classLoader != null) {
            try {
                cls2 = classLoader.loadClass(str);
                if (!z) {
                    return cls2;
                }
                mappings.put(str, cls2);
                return cls2;
            } catch (Exception e2) {
                exception = e2;
                cls = cls2;
                exception.printStackTrace();
            }
        } else {
            cls = cls2;
            try {
                ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
                if (!(contextClassLoader == null || contextClassLoader == classLoader)) {
                    cls2 = contextClassLoader.loadClass(str);
                    if (!z) {
                        return cls2;
                    }
                    try {
                        mappings.put(str, cls2);
                        return cls2;
                    } catch (Exception e22) {
                        exception = e22;
                        cls = cls2;
                        exception2 = exception;
                        exception2.printStackTrace();
                        cls2 = Class.forName(str);
                        mappings.put(str, cls2);
                        return cls2;
                    }
                }
            } catch (Exception e3) {
                exception2 = e3;
                exception2.printStackTrace();
                cls2 = Class.forName(str);
                mappings.put(str, cls2);
                return cls2;
            }
            try {
                cls2 = Class.forName(str);
                try {
                    mappings.put(str, cls2);
                    return cls2;
                } catch (Exception e4) {
                    e22 = e4;
                    e22.printStackTrace();
                    return cls2;
                }
            } catch (Exception exception22) {
                exception = exception22;
                cls2 = cls;
                e22 = exception;
                e22.printStackTrace();
                return cls2;
            }
        }
    }

    public static List<FieldInfo> computeGetters(Class<?> cls, int i, boolean z, JSONType jSONType, Map<String, String> map, boolean z2, boolean z3, boolean z4, PropertyNamingStrategy propertyNamingStrategy) {
        int i2;
        int modifiers;
        int i3;
        String name;
        String str;
        Field field;
        JSONField jSONField;
        Object obj;
        Map linkedHashMap = new LinkedHashMap();
        HashMap hashMap = new HashMap();
        Field[] declaredFields = cls.getDeclaredFields();
        if (!z) {
            boolean isKotlin = isKotlin(cls);
            List<Method> arrayList = new ArrayList();
            Class cls2 = cls;
            while (cls2 != null && cls2 != Object.class) {
                for (Method method : cls2.getDeclaredMethods()) {
                    modifiers = method.getModifiers();
                    if ((modifiers & 8) == 0 && (modifiers & 2) == 0 && (modifiers & 256) == 0 && (modifiers & 4) == 0 && !method.getReturnType().equals(Void.TYPE) && method.getParameterTypes().length == 0 && method.getReturnType() != ClassLoader.class && method.getDeclaringClass() != Object.class) {
                        arrayList.add(method);
                    }
                }
                cls2 = cls2.getSuperclass();
            }
            Annotation[][] annotationArr = (Annotation[][]) null;
            short[] sArr = null;
            Annotation[][] annotationArr2 = annotationArr;
            Constructor[] constructorArr = null;
            Object[] objArr = null;
            for (Method method2 : arrayList) {
                String name2 = method2.getName();
                modifiers = 0;
                i3 = 0;
                if (!name2.equals("getMetaClass") || !method2.getReturnType().getName().equals("groovy.lang.MetaClass")) {
                    JSONField supperMethodAnnotation;
                    JSONField jSONField2 = z3 ? (JSONField) method2.getAnnotation(JSONField.class) : null;
                    if (jSONField2 == null && z3) {
                        supperMethodAnnotation = getSupperMethodAnnotation(cls, method2);
                    } else {
                        supperMethodAnnotation = jSONField2;
                    }
                    if (!isKotlin || !isKotlinIgnore(cls, name2)) {
                        Object obj2;
                        short[] sArr2;
                        Object[] objArr2;
                        Annotation[][] annotationArr3;
                        Constructor[] constructorArr2;
                        char charAt;
                        if (supperMethodAnnotation == null && isKotlin) {
                            if (constructorArr == null) {
                                Constructor[] declaredConstructors = cls.getDeclaredConstructors();
                                if (declaredConstructors.length == 1) {
                                    Annotation[][] parameterAnnotations = declaredConstructors[0].getParameterAnnotations();
                                    Object koltinConstructorParameters = getKoltinConstructorParameters(cls);
                                    if (koltinConstructorParameters != null) {
                                        objArr = new String[koltinConstructorParameters.length];
                                        System.arraycopy(koltinConstructorParameters, 0, objArr, 0, koltinConstructorParameters.length);
                                        Arrays.sort(objArr);
                                        sArr = new short[koltinConstructorParameters.length];
                                        for (short s = (short) 0; s < koltinConstructorParameters.length; s = (short) (s + 1)) {
                                            sArr[Arrays.binarySearch(objArr, koltinConstructorParameters[s])] = s;
                                        }
                                        annotationArr2 = parameterAnnotations;
                                        constructorArr = declaredConstructors;
                                    } else {
                                        obj2 = koltinConstructorParameters;
                                        annotationArr2 = parameterAnnotations;
                                        constructorArr = declaredConstructors;
                                    }
                                } else {
                                    constructorArr = declaredConstructors;
                                }
                            }
                            if (!(objArr == null || sArr == null)) {
                                if (name2.startsWith("get")) {
                                    String decapitalize = decapitalize(name2.substring(3));
                                    int binarySearch = Arrays.binarySearch(objArr, decapitalize);
                                    if (binarySearch < 0) {
                                        i2 = 0;
                                        while (i2 < objArr.length) {
                                            if (decapitalize.equalsIgnoreCase(objArr[i2])) {
                                                break;
                                            }
                                            i2++;
                                        }
                                    }
                                    i2 = binarySearch;
                                    if (i2 >= 0) {
                                        Annotation[] annotationArr4 = annotationArr2[sArr[i2]];
                                        if (annotationArr4 != null) {
                                            for (Annotation annotation : annotationArr4) {
                                                if (annotation instanceof JSONField) {
                                                    supperMethodAnnotation = (JSONField) annotation;
                                                    sArr2 = sArr;
                                                    objArr2 = objArr;
                                                    annotationArr3 = annotationArr2;
                                                    constructorArr2 = constructorArr;
                                                    break;
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        sArr2 = sArr;
                        objArr2 = objArr;
                        annotationArr3 = annotationArr2;
                        constructorArr2 = constructorArr;
                        if (supperMethodAnnotation != null) {
                            if (supperMethodAnnotation.serialize()) {
                                modifiers = supperMethodAnnotation.ordinal();
                                i3 = SerializerFeature.of(supperMethodAnnotation.serialzeFeatures());
                                if (supperMethodAnnotation.name().length() != 0) {
                                    name = supperMethodAnnotation.name();
                                    if (map != null) {
                                        str = (String) map.get(name);
                                        if (str == null) {
                                            sArr = sArr2;
                                            objArr = objArr2;
                                            annotationArr2 = annotationArr3;
                                            constructorArr = constructorArr2;
                                        } else {
                                            name = str;
                                        }
                                    }
                                    setAccessible(cls, method2, i);
                                    linkedHashMap.put(name, new FieldInfo(name, method2, null, cls, null, modifiers, i3, supperMethodAnnotation, null, true));
                                    sArr = sArr2;
                                    objArr = objArr2;
                                    annotationArr2 = annotationArr3;
                                    constructorArr = constructorArr2;
                                }
                            } else {
                                sArr = sArr2;
                                objArr = objArr2;
                                annotationArr2 = annotationArr3;
                                constructorArr = constructorArr2;
                            }
                        }
                        if (name2.startsWith("get")) {
                            if (name2.length() >= 4) {
                                if (name2.equals("getClass")) {
                                    sArr = sArr2;
                                    objArr = objArr2;
                                    annotationArr2 = annotationArr3;
                                    constructorArr = constructorArr2;
                                } else {
                                    charAt = name2.charAt(3);
                                    if (Character.isUpperCase(charAt)) {
                                        if (compatibleWithJavaBean) {
                                            name = decapitalize(name2.substring(3));
                                        } else {
                                            name = Character.toLowerCase(name2.charAt(3)) + name2.substring(4);
                                        }
                                    } else if (charAt == '_') {
                                        name = name2.substring(4);
                                    } else if (charAt == 'f') {
                                        name = name2.substring(3);
                                    } else if (name2.length() >= 5 && Character.isUpperCase(name2.charAt(4))) {
                                        name = decapitalize(name2.substring(3));
                                    }
                                    if (isJSONTypeIgnore(cls, jSONType, name)) {
                                        sArr = sArr2;
                                        objArr = objArr2;
                                        annotationArr2 = annotationArr3;
                                        constructorArr = constructorArr2;
                                    } else {
                                        field = getField(cls, name, declaredFields, hashMap);
                                        jSONField = null;
                                        if (field != null) {
                                            jSONField = z3 ? (JSONField) field.getAnnotation(JSONField.class) : null;
                                            if (jSONField != null) {
                                                if (jSONField.serialize()) {
                                                    modifiers = jSONField.ordinal();
                                                    i3 = SerializerFeature.of(jSONField.serialzeFeatures());
                                                    if (jSONField.name().length() != 0) {
                                                        name = jSONField.name();
                                                        if (map != null) {
                                                            str = (String) map.get(name);
                                                            if (str == null) {
                                                                sArr = sArr2;
                                                                objArr = objArr2;
                                                                annotationArr2 = annotationArr3;
                                                                constructorArr = constructorArr2;
                                                            } else {
                                                                name = str;
                                                            }
                                                        }
                                                    }
                                                } else {
                                                    sArr = sArr2;
                                                    objArr = objArr2;
                                                    annotationArr2 = annotationArr3;
                                                    constructorArr = constructorArr2;
                                                }
                                            }
                                        }
                                        if (propertyNamingStrategy != null) {
                                            name = propertyNamingStrategy.translate(name);
                                        }
                                        if (map != null) {
                                            str = (String) map.get(name);
                                            if (str == null) {
                                                sArr = sArr2;
                                                objArr = objArr2;
                                                annotationArr2 = annotationArr3;
                                                constructorArr = constructorArr2;
                                            } else {
                                                name = str;
                                            }
                                        }
                                        setAccessible(cls, method2, i);
                                        linkedHashMap.put(name, new FieldInfo(name, method2, field, cls, null, modifiers, i3, supperMethodAnnotation, jSONField, z4));
                                    }
                                }
                            }
                            sArr = sArr2;
                            objArr = objArr2;
                            annotationArr2 = annotationArr3;
                            constructorArr = constructorArr2;
                        }
                        if (name2.startsWith("is")) {
                            if (name2.length() < 3) {
                                sArr = sArr2;
                                objArr = objArr2;
                                annotationArr2 = annotationArr3;
                                constructorArr = constructorArr2;
                            } else {
                                charAt = name2.charAt(2);
                                if (Character.isUpperCase(charAt)) {
                                    if (compatibleWithJavaBean) {
                                        name = decapitalize(name2.substring(2));
                                    } else {
                                        name = Character.toLowerCase(name2.charAt(2)) + name2.substring(3);
                                    }
                                } else if (charAt == '_') {
                                    name = name2.substring(3);
                                } else {
                                    if (charAt == 'f') {
                                        name = name2.substring(2);
                                    }
                                    sArr = sArr2;
                                    objArr = objArr2;
                                    annotationArr2 = annotationArr3;
                                    constructorArr = constructorArr2;
                                }
                                if (isJSONTypeIgnore(cls, jSONType, name)) {
                                    sArr = sArr2;
                                    objArr = objArr2;
                                    annotationArr2 = annotationArr3;
                                    constructorArr = constructorArr2;
                                } else {
                                    obj2 = getField(cls, name, declaredFields, hashMap);
                                    if (obj2 == null) {
                                        obj2 = getField(cls, name2, declaredFields, hashMap);
                                    }
                                    jSONField = null;
                                    if (obj2 != null) {
                                        jSONField = z3 ? (JSONField) obj2.getAnnotation(JSONField.class) : null;
                                        if (jSONField != null) {
                                            if (jSONField.serialize()) {
                                                modifiers = jSONField.ordinal();
                                                i3 = SerializerFeature.of(jSONField.serialzeFeatures());
                                                if (jSONField.name().length() != 0) {
                                                    name = jSONField.name();
                                                    if (map != null) {
                                                        str = (String) map.get(name);
                                                        if (str == null) {
                                                            sArr = sArr2;
                                                            objArr = objArr2;
                                                            annotationArr2 = annotationArr3;
                                                            constructorArr = constructorArr2;
                                                        } else {
                                                            name = str;
                                                        }
                                                    }
                                                }
                                            } else {
                                                sArr = sArr2;
                                                objArr = objArr2;
                                                annotationArr2 = annotationArr3;
                                                constructorArr = constructorArr2;
                                            }
                                        }
                                    }
                                    if (propertyNamingStrategy != null) {
                                        name = propertyNamingStrategy.translate(name);
                                    }
                                    if (map != null) {
                                        str = (String) map.get(name);
                                        if (str == null) {
                                            sArr = sArr2;
                                            objArr = objArr2;
                                            annotationArr2 = annotationArr3;
                                            constructorArr = constructorArr2;
                                        } else {
                                            name = str;
                                        }
                                    }
                                    setAccessible(cls, obj2, i);
                                    setAccessible(cls, method2, i);
                                    linkedHashMap.put(name, new FieldInfo(name, method2, obj2, cls, null, modifiers, i3, supperMethodAnnotation, jSONField, z4));
                                }
                            }
                        }
                        sArr = sArr2;
                        objArr = objArr2;
                        annotationArr2 = annotationArr3;
                        constructorArr = constructorArr2;
                    }
                }
            }
        }
        List<Field> arrayList2 = new ArrayList(declaredFields.length);
        for (Field field2 : declaredFields) {
            if (!((field2.getModifiers() & 8) != 0 || field2.getName().equals("this$0") || (field2.getModifiers() & 1) == 0)) {
                arrayList2.add(field2);
            }
        }
        Class superclass = cls.getSuperclass();
        while (superclass != null && superclass != Object.class) {
            for (Field field3 : superclass.getDeclaredFields()) {
                if ((field3.getModifiers() & 8) == 0 && (field3.getModifiers() & 1) != 0) {
                    arrayList2.add(field3);
                }
            }
            superclass = superclass.getSuperclass();
        }
        for (Field field22 : arrayList2) {
            jSONField = z3 ? (JSONField) field22.getAnnotation(JSONField.class) : null;
            modifiers = 0;
            i3 = 0;
            name = field22.getName();
            if (jSONField != null) {
                if (jSONField.serialize()) {
                    modifiers = jSONField.ordinal();
                    i3 = SerializerFeature.of(jSONField.serialzeFeatures());
                    if (jSONField.name().length() != 0) {
                        name = jSONField.name();
                    }
                }
            }
            if (map != null) {
                str = (String) map.get(name);
                if (str != null) {
                    name = str;
                }
            }
            if (propertyNamingStrategy != null) {
                name = propertyNamingStrategy.translate(name);
            }
            if (!linkedHashMap.containsKey(name)) {
                setAccessible(cls, field22, i);
                linkedHashMap.put(name, new FieldInfo(name, null, field22, cls, null, modifiers, i3, null, jSONField, z4));
            }
        }
        List<FieldInfo> arrayList3 = new ArrayList();
        String[] strArr;
        if (jSONType != null) {
            String[] orders = jSONType.orders();
            if (orders == null || orders.length != linkedHashMap.size()) {
                obj = null;
                strArr = orders;
            } else {
                obj = 1;
                for (Object containsKey : orders) {
                    if (!linkedHashMap.containsKey(containsKey)) {
                        obj = null;
                        break;
                    }
                }
                strArr = orders;
            }
        } else {
            strArr = null;
            obj = null;
        }
        if (obj != null) {
            for (Object obj3 : r6) {
                arrayList3.add((FieldInfo) linkedHashMap.get(obj3));
            }
        } else {
            for (FieldInfo add : linkedHashMap.values()) {
                arrayList3.add(add);
            }
            if (z2) {
                Collections.sort(arrayList3);
            }
        }
        return arrayList3;
    }

    public static JSONField getSupperMethodAnnotation(Class<?> cls, Method method) {
        for (Class methods : cls.getInterfaces()) {
            for (Method method2 : methods.getMethods()) {
                int i;
                Object obj;
                JSONField jSONField;
                if (method2.getName().equals(method.getName())) {
                    Class[] parameterTypes = method2.getParameterTypes();
                    Class[] parameterTypes2 = method.getParameterTypes();
                    if (parameterTypes.length == parameterTypes2.length) {
                        for (i = 0; i < parameterTypes.length; i++) {
                            if (!parameterTypes[i].equals(parameterTypes2[i])) {
                                obj = null;
                                break;
                            }
                        }
                        i = 1;
                        if (obj != null) {
                            jSONField = (JSONField) method2.getAnnotation(JSONField.class);
                            if (jSONField != null) {
                                return jSONField;
                            }
                        } else {
                            continue;
                        }
                    } else {
                        continue;
                    }
                }
            }
        }
        Class superclass = cls.getSuperclass();
        if (superclass == null) {
            return null;
        }
        if (Modifier.isAbstract(superclass.getModifiers())) {
            Class[] parameterTypes3 = method.getParameterTypes();
            for (Method method3 : superclass.getMethods()) {
                Class[] parameterTypes4 = method3.getParameterTypes();
                if (parameterTypes4.length == parameterTypes3.length && method3.getName().equals(method.getName())) {
                    for (i = 0; i < parameterTypes3.length; i++) {
                        if (!parameterTypes4[i].equals(parameterTypes3[i])) {
                            obj = null;
                            break;
                        }
                    }
                    i = 1;
                    if (obj != null) {
                        jSONField = (JSONField) method3.getAnnotation(JSONField.class);
                        if (jSONField != null) {
                            return jSONField;
                        }
                    } else {
                        continue;
                    }
                }
            }
        }
        return null;
    }

    private static boolean isJSONTypeIgnore(Class<?> cls, JSONType jSONType, String str) {
        boolean z;
        if (!(jSONType == null || jSONType.ignores() == null)) {
            for (String equalsIgnoreCase : jSONType.ignores()) {
                if (str.equalsIgnoreCase(equalsIgnoreCase)) {
                    return true;
                }
            }
        }
        Class superclass = cls.getSuperclass();
        if (superclass == Object.class || superclass == null || !isJSONTypeIgnore(superclass, (JSONType) superclass.getAnnotation(JSONType.class), str)) {
            z = false;
        } else {
            z = true;
        }
        return z;
    }

    public static boolean isGenericParamType(Type type) {
        if (type instanceof ParameterizedType) {
            return true;
        }
        if (!(type instanceof Class)) {
            return false;
        }
        Type genericSuperclass = ((Class) type).getGenericSuperclass();
        if (genericSuperclass == Object.class || !isGenericParamType(genericSuperclass)) {
            return false;
        }
        return true;
    }

    public static Type getGenericParamType(Type type) {
        if (type instanceof Class) {
            return getGenericParamType(((Class) type).getGenericSuperclass());
        }
        return type;
    }

    public static Class<?> getClass(Type type) {
        if (type.getClass() == Class.class) {
            return (Class) type;
        }
        if (type instanceof ParameterizedType) {
            return getClass(((ParameterizedType) type).getRawType());
        }
        if (type instanceof TypeVariable) {
            return (Class) ((TypeVariable) type).getBounds()[0];
        }
        if (type instanceof WildcardType) {
            Type[] upperBounds = ((WildcardType) type).getUpperBounds();
            if (upperBounds.length == 1) {
                return getClass(upperBounds[0]);
            }
        }
        return Object.class;
    }

    public static String decapitalize(String str) {
        if (str == null || str.length() == 0) {
            return str;
        }
        if (str.length() > 1 && Character.isUpperCase(str.charAt(1)) && Character.isUpperCase(str.charAt(0))) {
            return str;
        }
        char[] toCharArray = str.toCharArray();
        toCharArray[0] = Character.toLowerCase(toCharArray[0]);
        return new String(toCharArray);
    }

    public static boolean setAccessible(Class<?> cls, Member member, int i) {
        if (member == null || !setAccessibleEnable) {
            return false;
        }
        Class superclass = cls.getSuperclass();
        if ((superclass == null || superclass == Object.class) && (member.getModifiers() & 1) != 0 && (i & 1) != 0) {
            return false;
        }
        try {
            ((AccessibleObject) member).setAccessible(true);
            return true;
        } catch (AccessControlException e) {
            setAccessibleEnable = false;
            return false;
        }
    }

    public static Field getField(Class<?> cls, String str, Field[] fieldArr) {
        return getField(cls, str, fieldArr, null);
    }

    public static Field getField(Class<?> cls, String str, Field[] fieldArr, Map<Class<?>, Field[]> map) {
        Field field0 = getField0(cls, str, fieldArr, map);
        if (field0 == null) {
            field0 = getField0(cls, "_" + str, fieldArr, map);
        }
        if (field0 == null) {
            field0 = getField0(cls, "m_" + str, fieldArr, map);
        }
        if (field0 == null) {
            return getField0(cls, "m" + str.substring(0, 1).toUpperCase() + str.substring(1), fieldArr, map);
        }
        return field0;
    }

    private static Field getField0(Class<?> cls, String str, Field[] fieldArr, Map<Class<?>, Field[]> map) {
        for (Field field : fieldArr) {
            String name = field.getName();
            if (str.equals(name)) {
                return field;
            }
            if (str.length() > 2) {
                char charAt = str.charAt(0);
                if (charAt >= 'a' && charAt <= 'z') {
                    charAt = str.charAt(1);
                    if (charAt >= 'A' && charAt <= 'Z' && str.equalsIgnoreCase(name)) {
                        return field;
                    }
                }
            }
        }
        Class superclass = cls.getSuperclass();
        if (superclass == null || superclass == Object.class) {
            return null;
        }
        Field[] fieldArr2 = map != null ? (Field[]) map.get(superclass) : null;
        if (fieldArr2 == null) {
            fieldArr2 = superclass.getDeclaredFields();
            if (map != null) {
                map.put(superclass, fieldArr2);
            }
        }
        return getField(superclass, str, fieldArr2, map);
    }

    public static Type getCollectionItemType(Type type) {
        Type type2 = null;
        if (type instanceof ParameterizedType) {
            Type type3 = ((ParameterizedType) type).getActualTypeArguments()[0];
            if (type3 instanceof WildcardType) {
                Type[] upperBounds = ((WildcardType) type3).getUpperBounds();
                if (upperBounds.length == 1) {
                    type3 = upperBounds[0];
                }
            }
            type2 = type3;
        } else if (type instanceof Class) {
            Class cls = (Class) type;
            if (!cls.getName().startsWith("java.")) {
                type2 = getCollectionItemType(cls.getGenericSuperclass());
            }
        }
        if (type2 == null) {
            return Object.class;
        }
        return type2;
    }

    public static Object defaultValue(Class<?> cls) {
        if (cls == Byte.TYPE) {
            return Byte.valueOf((byte) 0);
        }
        if (cls == Short.TYPE) {
            return Short.valueOf((short) 0);
        }
        if (cls == Integer.TYPE) {
            return Integer.valueOf(0);
        }
        if (cls == Long.TYPE) {
            return Long.valueOf(0);
        }
        if (cls == Float.TYPE) {
            return Float.valueOf(0.0f);
        }
        if (cls == Double.TYPE) {
            return Double.valueOf(0.0d);
        }
        if (cls == Boolean.TYPE) {
            return Boolean.FALSE;
        }
        if (cls == Character.TYPE) {
            return Character.valueOf('0');
        }
        return null;
    }

    public static boolean getArgument(Type[] typeArr, TypeVariable[] typeVariableArr, Type[] typeArr2) {
        if (typeArr2 == null || typeVariableArr.length == 0) {
            return false;
        }
        boolean z = false;
        for (int i = 0; i < typeArr.length; i++) {
            Object obj = typeArr[i];
            if (obj instanceof ParameterizedType) {
                ParameterizedType parameterizedType = (ParameterizedType) obj;
                Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
                if (getArgument(actualTypeArguments, typeVariableArr, typeArr2)) {
                    typeArr[i] = new ParameterizedTypeImpl(actualTypeArguments, parameterizedType.getOwnerType(), parameterizedType.getRawType());
                    z = true;
                }
            } else if (obj instanceof TypeVariable) {
                boolean z2 = z;
                for (int i2 = 0; i2 < typeVariableArr.length; i2++) {
                    if (obj.equals(typeVariableArr[i2])) {
                        typeArr[i] = typeArr2[i2];
                        z2 = true;
                    }
                }
                z = z2;
            }
        }
        return z;
    }

    public static long fnv_64_lower(String str) {
        long j = -3750763034362895579L;
        for (int i = 0; i < str.length(); i++) {
            int charAt = str.charAt(i);
            if (!(charAt == 95 || charAt == 45)) {
                if (charAt >= 65 && charAt <= 90) {
                    charAt = (char) (charAt + 32);
                }
                j = (j ^ ((long) charAt)) * 1099511628211L;
            }
        }
        return j;
    }
}
