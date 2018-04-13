package com.alibaba.fastjson.util;

import com.ali.auth.third.core.model.Constants;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.annotation.JSONType;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.parser.JSONScanner;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.parser.deserializer.ASMJavaBeanDeserializer;
import com.alibaba.fastjson.parser.deserializer.JavaBeanDeserializer;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alipay.sdk.util.h;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.GenericArrayType;
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
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Currency;
import java.util.Date;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import org.apache.commons.httpclient.HttpState;

public class TypeUtils {
    public static boolean compatibleWithJavaBean;
    private static ConcurrentMap<String, Class<?>> mappings = new ConcurrentHashMap();
    private static Class<?> optionalClass;
    private static boolean optionalClassInited = false;
    private static Method oracleDateMethod;
    private static boolean oracleDateMethodInited = false;
    private static Method oracleTimestampMethod;
    private static boolean oracleTimestampMethodInited = false;
    private static boolean setAccessibleEnable = true;

    static {
        compatibleWithJavaBean = false;
        try {
            String property = System.getProperty("fastjson.compatibleWithJavaBean");
            if (Constants.SERVICE_SCOPE_FLAG_VALUE.equals(property)) {
                compatibleWithJavaBean = true;
            } else if (HttpState.PREEMPTIVE_DEFAULT.equals(property)) {
                compatibleWithJavaBean = false;
            }
        } catch (Throwable th) {
        }
        addBaseClassMappings();
    }

    public static String castToString(Object obj) {
        if (obj == null) {
            return null;
        }
        return obj.toString();
    }

    public static Byte castToByte(Object obj) {
        if (obj == null) {
            return null;
        }
        if (obj instanceof Number) {
            return Byte.valueOf(((Number) obj).byteValue());
        }
        if (obj instanceof String) {
            String str = (String) obj;
            if (str.length() == 0 || "null".equals(str) || "NULL".equals(str)) {
                return null;
            }
            return Byte.valueOf(Byte.parseByte(str));
        }
        throw new JSONException("can not cast to byte, value : " + obj);
    }

    public static Character castToChar(Object obj) {
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
            throw new JSONException("can not cast to char, value : " + obj);
        }
        throw new JSONException("can not cast to char, value : " + obj);
    }

    public static Short castToShort(Object obj) {
        if (obj == null) {
            return null;
        }
        if (obj instanceof Number) {
            return Short.valueOf(((Number) obj).shortValue());
        }
        if (obj instanceof String) {
            String str = (String) obj;
            if (str.length() == 0 || "null".equals(str) || "NULL".equals(str)) {
                return null;
            }
            return Short.valueOf(Short.parseShort(str));
        }
        throw new JSONException("can not cast to short, value : " + obj);
    }

    public static BigDecimal castToBigDecimal(Object obj) {
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
        if (obj2.length() == 0) {
            return null;
        }
        return new BigDecimal(obj2);
    }

    public static BigInteger castToBigInteger(Object obj) {
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
        if (obj2.length() == 0 || "null".equals(obj2) || "NULL".equals(obj2)) {
            return null;
        }
        return new BigInteger(obj2);
    }

    public static Float castToFloat(Object obj) {
        if (obj == null) {
            return null;
        }
        if (obj instanceof Number) {
            return Float.valueOf(((Number) obj).floatValue());
        }
        if (obj instanceof String) {
            String obj2 = obj.toString();
            if (obj2.length() == 0 || "null".equals(obj2) || "NULL".equals(obj2)) {
                return null;
            }
            if (obj2.indexOf(44) != 0) {
                obj2 = obj2.replaceAll(",", "");
            }
            return Float.valueOf(Float.parseFloat(obj2));
        }
        throw new JSONException("can not cast to float, value : " + obj);
    }

    public static Double castToDouble(Object obj) {
        if (obj == null) {
            return null;
        }
        if (obj instanceof Number) {
            return Double.valueOf(((Number) obj).doubleValue());
        }
        if (obj instanceof String) {
            String obj2 = obj.toString();
            if (obj2.length() == 0 || "null".equals(obj2) || "NULL".equals(obj2)) {
                return null;
            }
            if (obj2.indexOf(44) != 0) {
                obj2 = obj2.replaceAll(",", "");
            }
            return Double.valueOf(Double.parseDouble(obj2));
        }
        throw new JSONException("can not cast to double, value : " + obj);
    }

    public static Date castToDate(Object obj) {
        if (obj == null) {
            return null;
        }
        if (obj instanceof Date) {
            return (Date) obj;
        }
        if (obj instanceof Calendar) {
            return ((Calendar) obj).getTime();
        }
        long j = -1;
        if (obj instanceof Number) {
            return new Date(((Number) obj).longValue());
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
            } else if (str.length() == 0) {
                return null;
            } else {
                j = Long.parseLong(str);
            }
        }
        if (j >= 0) {
            return new Date(j);
        }
        Class cls = obj.getClass();
        if ("oracle.sql.TIMESTAMP".equals(cls.getName())) {
            if (oracleTimestampMethod == null && !oracleTimestampMethodInited) {
                try {
                    oracleTimestampMethod = cls.getMethod("toJdbc", new Class[0]);
                } catch (NoSuchMethodException e2) {
                } finally {
                    oracleTimestampMethodInited = true;
                }
            }
            try {
                return (Date) oracleTimestampMethod.invoke(obj, new Object[0]);
            } catch (Throwable e3) {
                throw new JSONException("can not cast oracle.sql.TIMESTAMP to Date", e3);
            }
        } else if ("oracle.sql.DATE".equals(cls.getName())) {
            if (oracleDateMethod == null && !oracleDateMethodInited) {
                try {
                    oracleDateMethod = cls.getMethod("toJdbc", new Class[0]);
                } catch (NoSuchMethodException e4) {
                } finally {
                    oracleDateMethodInited = true;
                }
            }
            try {
                return (Date) oracleDateMethod.invoke(obj, new Object[0]);
            } catch (Throwable e32) {
                throw new JSONException("can not cast oracle.sql.DATE to Date", e32);
            }
        } else {
            throw new JSONException("can not cast to Date, value : " + obj);
        }
    }

    public static java.sql.Date castToSqlDate(Object obj) {
        if (obj == null) {
            return null;
        }
        if (obj instanceof java.sql.Date) {
            return (java.sql.Date) obj;
        }
        if (obj instanceof Date) {
            return new java.sql.Date(((Date) obj).getTime());
        }
        if (obj instanceof Calendar) {
            return new java.sql.Date(((Calendar) obj).getTimeInMillis());
        }
        long longValue;
        if (obj instanceof Number) {
            longValue = ((Number) obj).longValue();
        } else {
            longValue = 0;
        }
        if (obj instanceof String) {
            String str = (String) obj;
            if (str.length() == 0 || "null".equals(str) || "NULL".equals(str)) {
                return null;
            }
            longValue = Long.parseLong(str);
        }
        if (longValue > 0) {
            return new java.sql.Date(longValue);
        }
        throw new JSONException("can not cast to Date, value : " + obj);
    }

    public static Timestamp castToTimestamp(Object obj) {
        if (obj == null) {
            return null;
        }
        if (obj instanceof Calendar) {
            return new Timestamp(((Calendar) obj).getTimeInMillis());
        }
        if (obj instanceof Timestamp) {
            return (Timestamp) obj;
        }
        if (obj instanceof Date) {
            return new Timestamp(((Date) obj).getTime());
        }
        long longValue;
        if (obj instanceof Number) {
            longValue = ((Number) obj).longValue();
        } else {
            longValue = 0;
        }
        if (obj instanceof String) {
            String str = (String) obj;
            if (str.length() == 0 || "null".equals(str) || "NULL".equals(str)) {
                return null;
            }
            longValue = Long.parseLong(str);
        }
        if (longValue > 0) {
            return new Timestamp(longValue);
        }
        throw new JSONException("can not cast to Date, value : " + obj);
    }

    public static Long castToLong(Object obj) {
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
            if (str.length() == 0 || "null".equals(str) || "NULL".equals(str)) {
                return calendar2;
            }
            if (str.indexOf(44) != 0) {
                str = str.replaceAll(",", "");
            }
            try {
                return Long.valueOf(Long.parseLong(str));
            } catch (NumberFormatException e) {
                JSONScanner jSONScanner = new JSONScanner(str);
                if (jSONScanner.scanISO8601DateIfMatch(false)) {
                    calendar = jSONScanner.getCalendar();
                } else {
                    calendar = calendar2;
                }
                jSONScanner.close();
                if (calendar != null) {
                    return Long.valueOf(calendar.getTimeInMillis());
                }
            }
        }
        throw new JSONException("can not cast to long, value : " + obj);
    }

    public static Integer castToInt(Object obj) {
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
            obj = (String) obj;
            if (obj.length() == 0 || "null".equals(obj) || "NULL".equals(obj)) {
                return null;
            }
            if (obj.indexOf(44) != 0) {
                obj = obj.replaceAll(",", "");
            }
            return Integer.valueOf(Integer.parseInt(obj));
        } else if (obj instanceof Boolean) {
            return Integer.valueOf(((Boolean) obj).booleanValue() ? 1 : 0);
        } else {
            throw new JSONException("can not cast to int, value : " + obj);
        }
    }

    public static byte[] castToBytes(Object obj) {
        if (obj instanceof byte[]) {
            return (byte[]) obj;
        }
        if (obj instanceof String) {
            return IOUtils.decodeFast((String) obj);
        }
        throw new JSONException("can not cast to int, value : " + obj);
    }

    public static Boolean castToBoolean(Object obj) {
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
            if (str.length() == 0 || "null".equals(str) || "NULL".equals(str)) {
                return null;
            }
            if (Constants.SERVICE_SCOPE_FLAG_VALUE.equalsIgnoreCase(str) || "1".equals(str)) {
                return Boolean.TRUE;
            }
            if (HttpState.PREEMPTIVE_DEFAULT.equalsIgnoreCase(str) || "0".equals(str)) {
                return Boolean.FALSE;
            }
        }
        throw new JSONException("can not cast to boolean, value : " + obj);
    }

    public static <T> T castToJavaBean(Object obj, Class<T> cls) {
        return cast(obj, (Class) cls, ParserConfig.getGlobalInstance());
    }

    public static <T> T cast(Object obj, Class<T> cls, ParserConfig parserConfig) {
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
                        int i = 0;
                        Object newInstance = Array.newInstance(cls.getComponentType(), collection.size());
                        for (Object cast : collection) {
                            Array.set(newInstance, i, cast(cast, cls.getComponentType(), parserConfig));
                            i++;
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
                if (cls == java.sql.Date.class) {
                    return castToSqlDate(obj);
                }
                if (cls == Timestamp.class) {
                    return castToTimestamp(obj);
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
                    String str = (String) obj;
                    if (str.length() == 0 || "null".equals(str) || "NULL".equals(str)) {
                        return null;
                    }
                    if (cls == Currency.class) {
                        return Currency.getInstance(str);
                    }
                }
                throw new JSONException("can not cast to : " + cls.getName());
            } else if (cls == Map.class) {
                return obj;
            } else {
                Map map = (Map) obj;
                if (cls != Object.class || map.containsKey(JSON.DEFAULT_TYPE_KEY)) {
                    return castToJavaBean((Map) obj, cls, parserConfig);
                }
                return obj;
            }
        }
    }

    public static <T> T castToEnum(Object obj, Class<T> cls, ParserConfig parserConfig) {
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

    public static <T> T cast(Object obj, Type type, ParserConfig parserConfig) {
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
            if (str.length() == 0 || "null".equals(str) || "NULL".equals(str)) {
                return null;
            }
        }
        if (type instanceof TypeVariable) {
            return obj;
        }
        throw new JSONException("can not cast to : " + type);
    }

    public static <T> T cast(Object obj, ParameterizedType parameterizedType, ParserConfig parserConfig) {
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
        if ((obj instanceof String) && ((String) obj).length() == 0) {
            return null;
        }
        if (parameterizedType.getActualTypeArguments().length == 1 && (parameterizedType.getActualTypeArguments()[0] instanceof WildcardType)) {
            return cast(obj, rawType, parserConfig);
        }
        throw new JSONException("can not cast to : " + parameterizedType);
    }

    public static <T> T castToJavaBean(Map<String, Object> map, Class<T> cls, ParserConfig parserConfig) {
        int i = 0;
        try {
            String str;
            if (cls == StackTraceElement.class) {
                str = (String) map.get("className");
                String str2 = (String) map.get("methodName");
                String str3 = (String) map.get("fileName");
                Number number = (Number) map.get("lineNumber");
                if (number != null) {
                    i = number.intValue();
                }
                return new StackTraceElement(str, str2, str3, i);
            }
            Object obj = map.get(JSON.DEFAULT_TYPE_KEY);
            if (obj instanceof String) {
                str = (String) obj;
                Class loadClass = loadClass(str);
                if (loadClass == null) {
                    throw new ClassNotFoundException(str + " not found");
                } else if (!loadClass.equals(cls)) {
                    return castToJavaBean(map, loadClass, parserConfig);
                }
            }
            if (cls.isInterface()) {
                if (map instanceof JSONObject) {
                    map = (JSONObject) map;
                } else {
                    Object jSONObject = new JSONObject(map);
                }
                return Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class[]{cls}, map);
            }
            JavaBeanDeserializer javaBeanDeserializer;
            if (parserConfig == null) {
                parserConfig = ParserConfig.getGlobalInstance();
            }
            ObjectDeserializer deserializer = parserConfig.getDeserializer((Type) cls);
            if (deserializer instanceof JavaBeanDeserializer) {
                javaBeanDeserializer = (JavaBeanDeserializer) deserializer;
            } else if (deserializer instanceof ASMJavaBeanDeserializer) {
                javaBeanDeserializer = ((ASMJavaBeanDeserializer) deserializer).getInnterSerializer();
            } else {
                javaBeanDeserializer = null;
            }
            if (javaBeanDeserializer != null) {
                return javaBeanDeserializer.createInstance((Map) map, parserConfig);
            }
            throw new JSONException("can not get javaBeanDeserializer");
        } catch (Throwable e) {
            throw new JSONException(e.getMessage(), e);
        }
    }

    private static void addBaseClassMappings() {
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
        mappings.put(HashMap.class.getName(), HashMap.class);
    }

    public static void clearClassMapping() {
        mappings.clear();
        addBaseClassMappings();
    }

    public static Class<?> loadClass(String str) {
        return loadClass(str, null);
    }

    public static Class<?> loadClass(String str, ClassLoader classLoader) {
        if (str == null || str.length() == 0) {
            return null;
        }
        Class<?> cls = (Class) mappings.get(str);
        if (cls != null) {
            return cls;
        }
        if (str.charAt(0) == '[') {
            return Array.newInstance(loadClass(str.substring(1), classLoader), 0).getClass();
        }
        if (str.startsWith("L") && str.endsWith(h.b)) {
            return loadClass(str.substring(1, str.length() - 1), classLoader);
        }
        if (classLoader != null) {
            try {
                cls = classLoader.loadClass(str);
                mappings.put(str, cls);
                return cls;
            } catch (Throwable th) {
                Throwable th2 = th;
                Class<?> cls2 = cls;
                th2.printStackTrace();
                cls = cls2;
            }
        }
        try {
            ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
            if (contextClassLoader != null) {
                cls = contextClassLoader.loadClass(str);
                mappings.put(str, cls);
                return cls;
            }
        } catch (Throwable th3) {
        }
        try {
            cls = Class.forName(str);
            mappings.put(str, cls);
            return cls;
        } catch (Throwable th4) {
            return cls;
        }
    }

    public static List<FieldInfo> computeGetters(Class<?> cls, JSONType jSONType, Map<String, String> map, boolean z) {
        Object obj;
        Map linkedHashMap = new LinkedHashMap();
        for (Method method : cls.getMethods()) {
            String name;
            String str;
            Field field;
            JSONField jSONField;
            String name2 = method.getName();
            int i = 0;
            int i2 = 0;
            String str2 = null;
            if (!(Modifier.isStatic(method.getModifiers()) || method.getReturnType().equals(Void.TYPE) || method.getParameterTypes().length != 0 || method.getReturnType() == ClassLoader.class || (method.getName().equals("getMetaClass") && method.getReturnType().getName().equals("groovy.lang.MetaClass")))) {
                JSONField supperMethodAnnotation;
                char charAt;
                JSONField jSONField2 = (JSONField) method.getAnnotation(JSONField.class);
                if (jSONField2 == null) {
                    supperMethodAnnotation = getSupperMethodAnnotation(cls, method);
                } else {
                    supperMethodAnnotation = jSONField2;
                }
                if (supperMethodAnnotation != null) {
                    if (supperMethodAnnotation.serialize()) {
                        i = supperMethodAnnotation.ordinal();
                        i2 = SerializerFeature.of(supperMethodAnnotation.serialzeFeatures());
                        if (supperMethodAnnotation.name().length() != 0) {
                            name = supperMethodAnnotation.name();
                            if (map != null) {
                                str = (String) map.get(name);
                                if (str != null) {
                                    name = str;
                                }
                            }
                            linkedHashMap.put(name, new FieldInfo(name, method, null, cls, null, i, i2, supperMethodAnnotation, null, null));
                        } else if (supperMethodAnnotation.label().length() != 0) {
                            str2 = supperMethodAnnotation.label();
                        }
                    }
                }
                if (name2.startsWith("get")) {
                    if (name2.length() >= 4) {
                        if (!name2.equals("getClass")) {
                            charAt = name2.charAt(3);
                            if (Character.isUpperCase(charAt) || charAt > 'Ȁ') {
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
                            if (!isJSONTypeIgnore(cls, name)) {
                                field = ParserConfig.getField(cls, name);
                                if (field != null) {
                                    jSONField2 = (JSONField) field.getAnnotation(JSONField.class);
                                    if (jSONField2 != null) {
                                        if (jSONField2.serialize()) {
                                            i = jSONField2.ordinal();
                                            i2 = SerializerFeature.of(jSONField2.serialzeFeatures());
                                            if (jSONField2.name().length() != 0) {
                                                name = jSONField2.name();
                                                if (map != null) {
                                                    name = (String) map.get(name);
                                                    if (name == null) {
                                                    }
                                                }
                                            }
                                            if (jSONField2.label().length() != 0) {
                                                str2 = jSONField2.label();
                                                jSONField = jSONField2;
                                            }
                                        }
                                    }
                                    jSONField = jSONField2;
                                } else {
                                    jSONField = null;
                                }
                                if (map != null) {
                                    str = (String) map.get(name);
                                    if (str != null) {
                                        name = str;
                                    }
                                }
                                linkedHashMap.put(name, new FieldInfo(name, method, field, cls, null, i, i2, supperMethodAnnotation, jSONField, str2));
                            }
                        }
                    }
                }
                if (name2.startsWith("is") && name2.length() >= 3) {
                    charAt = name2.charAt(2);
                    if (Character.isUpperCase(charAt)) {
                        if (compatibleWithJavaBean) {
                            name = decapitalize(name2.substring(2));
                        } else {
                            name = Character.toLowerCase(name2.charAt(2)) + name2.substring(3);
                        }
                    } else if (charAt == '_') {
                        name = name2.substring(3);
                    } else if (charAt == 'f') {
                        name = name2.substring(2);
                    }
                    field = ParserConfig.getField(cls, name);
                    if (field == null) {
                        field = ParserConfig.getField(cls, name2);
                    }
                    if (field != null) {
                        jSONField2 = (JSONField) field.getAnnotation(JSONField.class);
                        if (jSONField2 != null) {
                            if (jSONField2.serialize()) {
                                i = jSONField2.ordinal();
                                i2 = SerializerFeature.of(jSONField2.serialzeFeatures());
                                if (jSONField2.name().length() != 0) {
                                    name = jSONField2.name();
                                    if (map != null) {
                                        name = (String) map.get(name);
                                        if (name == null) {
                                        }
                                    }
                                }
                                if (jSONField2.label().length() != 0) {
                                    str2 = jSONField2.label();
                                    jSONField = jSONField2;
                                }
                            }
                        }
                        jSONField = jSONField2;
                    } else {
                        jSONField = null;
                    }
                    if (map != null) {
                        str = (String) map.get(name);
                        if (str != null) {
                            name = str;
                        }
                    }
                    linkedHashMap.put(name, new FieldInfo(name, method, field, cls, null, i, i2, supperMethodAnnotation, jSONField, str2));
                }
            }
        }
        for (Field field2 : cls.getFields()) {
            if (!Modifier.isStatic(field2.getModifiers())) {
                jSONField = (JSONField) field2.getAnnotation(JSONField.class);
                i = 0;
                i2 = 0;
                str = field2.getName();
                str2 = null;
                if (jSONField != null) {
                    if (jSONField.serialize()) {
                        i = jSONField.ordinal();
                        i2 = SerializerFeature.of(jSONField.serialzeFeatures());
                        if (jSONField.name().length() != 0) {
                            str = jSONField.name();
                        }
                        if (jSONField.label().length() != 0) {
                            str2 = jSONField.label();
                            name = str;
                            if (map != null) {
                                str = (String) map.get(name);
                                if (str != null) {
                                    name = str;
                                }
                            }
                            if (!linkedHashMap.containsKey(name)) {
                                linkedHashMap.put(name, new FieldInfo(name, null, field2, cls, null, i, i2, null, jSONField, str2));
                            }
                        }
                    }
                }
                name = str;
                if (map != null) {
                    str = (String) map.get(name);
                    if (str != null) {
                        name = str;
                    }
                }
                if (!linkedHashMap.containsKey(name)) {
                    linkedHashMap.put(name, new FieldInfo(name, null, field2, cls, null, i, i2, null, jSONField, str2));
                }
            }
        }
        List<FieldInfo> arrayList = new ArrayList();
        JSONType jSONType2 = (JSONType) cls.getAnnotation(JSONType.class);
        String[] strArr;
        if (jSONType2 != null) {
            String[] orders = jSONType2.orders();
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
            obj = null;
            strArr = null;
        }
        if (obj != null) {
            for (Object obj2 : r4) {
                arrayList.add((FieldInfo) linkedHashMap.get(obj2));
            }
        } else {
            for (FieldInfo add : linkedHashMap.values()) {
                arrayList.add(add);
            }
            if (z) {
                Collections.sort(arrayList);
            }
        }
        return arrayList;
    }

    public static JSONField getSupperMethodAnnotation(Class<?> cls, Method method) {
        Class[] interfaces = cls.getInterfaces();
        if (interfaces.length > 0) {
            Class[] parameterTypes = method.getParameterTypes();
            for (Class methods : interfaces) {
                for (Method method2 : methods.getMethods()) {
                    Class[] parameterTypes2 = method2.getParameterTypes();
                    if (parameterTypes2.length == parameterTypes.length && method2.getName().equals(method.getName())) {
                        int i;
                        Object obj;
                        for (i = 0; i < parameterTypes.length; i++) {
                            if (!parameterTypes2[i].equals(parameterTypes[i])) {
                                obj = null;
                                break;
                            }
                        }
                        i = 1;
                        if (obj != null) {
                            JSONField jSONField = (JSONField) method2.getAnnotation(JSONField.class);
                            if (jSONField != null) {
                                return jSONField;
                            }
                        } else {
                            continue;
                        }
                    }
                }
            }
        }
        return null;
    }

    private static boolean isJSONTypeIgnore(Class<?> cls, String str) {
        JSONType jSONType = (JSONType) cls.getAnnotation(JSONType.class);
        if (jSONType != null) {
            String[] includes = jSONType.includes();
            if (includes.length > 0) {
                for (Object equals : includes) {
                    if (str.equals(equals)) {
                        return false;
                    }
                }
                return true;
            }
            includes = jSONType.ignores();
            for (Object equals2 : includes) {
                if (str.equals(equals2)) {
                    return true;
                }
            }
        }
        if (cls.getSuperclass() == Object.class || cls.getSuperclass() == null || !isJSONTypeIgnore(cls.getSuperclass(), str)) {
            return false;
        }
        return true;
    }

    public static boolean isGenericParamType(Type type) {
        if (type instanceof ParameterizedType) {
            return true;
        }
        if (!(type instanceof Class)) {
            return false;
        }
        Type genericSuperclass = ((Class) type).getGenericSuperclass();
        if (genericSuperclass != Object.class) {
            return isGenericParamType(genericSuperclass);
        }
        return false;
    }

    public static Type getGenericParamType(Type type) {
        if (!(type instanceof ParameterizedType) && (type instanceof Class)) {
            return getGenericParamType(((Class) type).getGenericSuperclass());
        }
        return type;
    }

    public static Type unwrap(Type type) {
        if (!(type instanceof GenericArrayType)) {
            return type;
        }
        Type genericComponentType = ((GenericArrayType) type).getGenericComponentType();
        if (genericComponentType == Byte.TYPE) {
            return byte[].class;
        }
        if (genericComponentType == Character.TYPE) {
            return char[].class;
        }
        return type;
    }

    public static Type unwrapOptional(Type type) {
        if (!optionalClassInited) {
            try {
                optionalClass = Class.forName("java.util.Optional");
            } catch (Exception e) {
            } finally {
                optionalClassInited = true;
            }
        }
        if (!(type instanceof ParameterizedType)) {
            return type;
        }
        ParameterizedType parameterizedType = (ParameterizedType) type;
        if (parameterizedType.getRawType() == optionalClass) {
            return parameterizedType.getActualTypeArguments()[0];
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
        return Object.class;
    }

    public static Field getField(Class<?> cls, String str, Field[] fieldArr) {
        for (Field field : fieldArr) {
            if (str.equals(field.getName())) {
                return field;
            }
        }
        Class superclass = cls.getSuperclass();
        if (superclass == null || superclass == Object.class) {
            return null;
        }
        return getField(superclass, str, superclass.getDeclaredFields());
    }

    public static JSONType getJSONType(Class<?> cls) {
        return (JSONType) cls.getAnnotation(JSONType.class);
    }

    public static int getSerializeFeatures(Class<?> cls) {
        JSONType jSONType = (JSONType) cls.getAnnotation(JSONType.class);
        if (jSONType == null) {
            return 0;
        }
        return SerializerFeature.of(jSONType.serialzeFeatures());
    }

    public static int getParserFeatures(Class<?> cls) {
        JSONType jSONType = (JSONType) cls.getAnnotation(JSONType.class);
        if (jSONType == null) {
            return 0;
        }
        return Feature.of(jSONType.parseFeatures());
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

    static void setAccessible(AccessibleObject accessibleObject) {
        if (setAccessibleEnable && !accessibleObject.isAccessible()) {
            try {
                accessibleObject.setAccessible(true);
            } catch (AccessControlException e) {
                setAccessibleEnable = false;
            }
        }
    }

    public static Class<?> getCollectionItemClass(Type type) {
        if (!(type instanceof ParameterizedType)) {
            return Object.class;
        }
        Type type2 = ((ParameterizedType) type).getActualTypeArguments()[0];
        if (type2 instanceof Class) {
            Class<?> cls = (Class) type2;
            if (Modifier.isPublic(cls.getModifiers())) {
                return cls;
            }
            throw new JSONException("can not create ASMParser");
        }
        throw new JSONException("can not create ASMParser");
    }

    public static Collection createCollection(Type type) {
        Class rawClass = getRawClass(type);
        if (rawClass == AbstractCollection.class || rawClass == Collection.class) {
            return new ArrayList();
        }
        if (rawClass.isAssignableFrom(HashSet.class)) {
            return new HashSet();
        }
        if (rawClass.isAssignableFrom(LinkedHashSet.class)) {
            return new LinkedHashSet();
        }
        if (rawClass.isAssignableFrom(TreeSet.class)) {
            return new TreeSet();
        }
        if (rawClass.isAssignableFrom(ArrayList.class)) {
            return new ArrayList();
        }
        if (rawClass.isAssignableFrom(EnumSet.class)) {
            Class cls;
            if (type instanceof ParameterizedType) {
                cls = ((ParameterizedType) type).getActualTypeArguments()[0];
            } else {
                cls = Object.class;
            }
            return EnumSet.noneOf(cls);
        }
        try {
            return (Collection) rawClass.newInstance();
        } catch (Exception e) {
            throw new JSONException("create instane error, class " + rawClass.getName());
        }
    }

    public static Class<?> getRawClass(Type type) {
        if (type instanceof Class) {
            return (Class) type;
        }
        if (type instanceof ParameterizedType) {
            return getRawClass(((ParameterizedType) type).getRawType());
        }
        throw new JSONException("TODO");
    }
}
