package com.alibaba.fastjson.util;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.annotation.JSONCreator;
import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.annotation.JSONPOJOBuilder;
import com.alibaba.fastjson.annotation.JSONType;
import com.alibaba.fastjson.serializer.SerializerFeature;
import java.lang.annotation.Annotation;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class JavaBeanInfo {
    public final Method buildMethod;
    public final Class<?> builderClass;
    public final Class<?> clazz;
    public final Constructor<?> creatorConstructor;
    public final Constructor<?> defaultConstructor;
    public final int defaultConstructorParameterSize;
    public final Method factoryMethod;
    public final FieldInfo[] fields;
    public final JSONType jsonType;
    public final int parserFeatures;
    public final FieldInfo[] sortedFields;

    public JavaBeanInfo(Class<?> cls, Class<?> cls2, Constructor<?> constructor, Constructor<?> constructor2, Method method, Method method2, JSONType jSONType, List<FieldInfo> list) {
        int length;
        this.clazz = cls;
        this.builderClass = cls2;
        this.defaultConstructor = constructor;
        this.creatorConstructor = constructor2;
        this.factoryMethod = method;
        this.parserFeatures = TypeUtils.getParserFeatures(cls);
        this.buildMethod = method2;
        this.jsonType = jSONType;
        this.fields = new FieldInfo[list.size()];
        list.toArray(this.fields);
        FieldInfo[] fieldInfoArr = new FieldInfo[this.fields.length];
        System.arraycopy(this.fields, 0, fieldInfoArr, 0, this.fields.length);
        Arrays.sort(fieldInfoArr);
        if (Arrays.equals(this.fields, fieldInfoArr)) {
            fieldInfoArr = this.fields;
        }
        this.sortedFields = fieldInfoArr;
        if (constructor != null) {
            length = constructor.getParameterTypes().length;
        } else {
            length = 0;
        }
        this.defaultConstructorParameterSize = length;
    }

    private static FieldInfo getField(List<FieldInfo> list, String str) {
        for (FieldInfo fieldInfo : list) {
            if (fieldInfo.name.equals(str)) {
                return fieldInfo;
            }
        }
        return null;
    }

    static boolean add(List<FieldInfo> list, FieldInfo fieldInfo) {
        int size = list.size() - 1;
        while (size >= 0) {
            FieldInfo fieldInfo2 = (FieldInfo) list.get(size);
            if (!fieldInfo2.name.equals(fieldInfo.name) || (fieldInfo2.getOnly && !fieldInfo.getOnly)) {
                size--;
            } else {
                if (fieldInfo2.fieldClass.isAssignableFrom(fieldInfo.fieldClass)) {
                    list.remove(size);
                } else if (fieldInfo2.compareTo(fieldInfo) >= 0) {
                    return false;
                } else {
                    list.remove(size);
                }
                list.add(fieldInfo);
                return true;
            }
        }
        list.add(fieldInfo);
        return true;
    }

    public static JavaBeanInfo build(Class<?> cls, Type type) {
        JSONType jSONType = (JSONType) cls.getAnnotation(JSONType.class);
        Class builderClass = getBuilderClass(jSONType);
        Field[] declaredFields = cls.getDeclaredFields();
        Method[] methods = cls.getMethods();
        AccessibleObject defaultConstructor = getDefaultConstructor(builderClass == null ? cls : builderClass);
        Method method = null;
        List<FieldInfo> arrayList = new ArrayList();
        if (defaultConstructor != null || cls.isInterface() || Modifier.isAbstract(cls.getModifiers())) {
            int i;
            int i2;
            JSONField jSONField;
            JSONField supperMethodAnnotation;
            String decapitalize;
            Field field;
            JSONField jSONField2;
            if (defaultConstructor != null) {
                TypeUtils.setAccessible(defaultConstructor);
            }
            if (builderClass != null) {
                String withPrefix;
                String str;
                JSONPOJOBuilder jSONPOJOBuilder = (JSONPOJOBuilder) builderClass.getAnnotation(JSONPOJOBuilder.class);
                if (jSONPOJOBuilder != null) {
                    withPrefix = jSONPOJOBuilder.withPrefix();
                } else {
                    withPrefix = null;
                }
                if (withPrefix == null || withPrefix.length() == 0) {
                    str = "with";
                } else {
                    str = withPrefix;
                }
                for (Method method2 : builderClass.getMethods()) {
                    if (!Modifier.isStatic(method2.getModifiers()) && method2.getReturnType().equals(builderClass)) {
                        i = 0;
                        i2 = 0;
                        jSONField = (JSONField) method2.getAnnotation(JSONField.class);
                        if (jSONField == null) {
                            supperMethodAnnotation = TypeUtils.getSupperMethodAnnotation(cls, method2);
                        } else {
                            supperMethodAnnotation = jSONField;
                        }
                        if (supperMethodAnnotation != null) {
                            if (supperMethodAnnotation.deserialize()) {
                                i = supperMethodAnnotation.ordinal();
                                i2 = SerializerFeature.of(supperMethodAnnotation.serialzeFeatures());
                                if (supperMethodAnnotation.name().length() != 0) {
                                    add(arrayList, new FieldInfo(supperMethodAnnotation.name(), method2, null, cls, type, i, i2, supperMethodAnnotation, null, null));
                                }
                            }
                        }
                        withPrefix = method2.getName();
                        if (withPrefix.startsWith(str) && withPrefix.length() > str.length()) {
                            char charAt = withPrefix.charAt(str.length());
                            if (Character.isUpperCase(charAt)) {
                                StringBuilder stringBuilder = new StringBuilder(withPrefix.substring(str.length()));
                                stringBuilder.setCharAt(0, Character.toLowerCase(charAt));
                                add(arrayList, new FieldInfo(stringBuilder.toString(), method2, null, cls, type, i, i2, supperMethodAnnotation, null, null));
                            }
                        }
                    }
                }
                if (builderClass != null) {
                    jSONPOJOBuilder = (JSONPOJOBuilder) builderClass.getAnnotation(JSONPOJOBuilder.class);
                    if (jSONPOJOBuilder != null) {
                        withPrefix = jSONPOJOBuilder.buildMethod();
                    } else {
                        withPrefix = null;
                    }
                    if (withPrefix == null || withPrefix.length() == 0) {
                        withPrefix = "build";
                    }
                    try {
                        method = builderClass.getMethod(withPrefix, new Class[0]);
                    } catch (NoSuchMethodException e) {
                    } catch (SecurityException e2) {
                    }
                    if (method == null) {
                        try {
                            method = builderClass.getMethod("create", new Class[0]);
                        } catch (NoSuchMethodException e3) {
                        } catch (SecurityException e4) {
                        }
                    }
                    if (method == null) {
                        throw new JSONException("buildMethod not found.");
                    }
                    TypeUtils.setAccessible(method);
                }
            }
            for (Method method22 : methods) {
                i = 0;
                i2 = 0;
                String name = method22.getName();
                if (name.length() >= 4 && !Modifier.isStatic(method22.getModifiers()) && (method22.getReturnType().equals(Void.TYPE) || method22.getReturnType().equals(cls))) {
                    Class[] parameterTypes = method22.getParameterTypes();
                    if (parameterTypes.length == 1) {
                        jSONField = (JSONField) method22.getAnnotation(JSONField.class);
                        if (jSONField == null) {
                            supperMethodAnnotation = TypeUtils.getSupperMethodAnnotation(cls, method22);
                        } else {
                            supperMethodAnnotation = jSONField;
                        }
                        if (supperMethodAnnotation != null) {
                            if (supperMethodAnnotation.deserialize()) {
                                i = supperMethodAnnotation.ordinal();
                                i2 = SerializerFeature.of(supperMethodAnnotation.serialzeFeatures());
                                if (supperMethodAnnotation.name().length() != 0) {
                                    add(arrayList, new FieldInfo(supperMethodAnnotation.name(), method22, null, cls, type, i, i2, supperMethodAnnotation, null, null));
                                }
                            }
                        }
                        if (name.startsWith("set")) {
                            char charAt2 = name.charAt(3);
                            if (Character.isUpperCase(charAt2) || charAt2 > 'Ȁ') {
                                if (TypeUtils.compatibleWithJavaBean) {
                                    decapitalize = TypeUtils.decapitalize(name.substring(3));
                                } else {
                                    decapitalize = Character.toLowerCase(name.charAt(3)) + name.substring(4);
                                }
                            } else if (charAt2 == '_') {
                                decapitalize = name.substring(4);
                            } else if (charAt2 == 'f') {
                                decapitalize = name.substring(3);
                            } else if (name.length() >= 5 && Character.isUpperCase(name.charAt(4))) {
                                decapitalize = TypeUtils.decapitalize(name.substring(3));
                            }
                            field = TypeUtils.getField(cls, decapitalize, declaredFields);
                            if (field == null && parameterTypes[0] == Boolean.TYPE) {
                                field = TypeUtils.getField(cls, "is" + Character.toUpperCase(decapitalize.charAt(0)) + decapitalize.substring(1), declaredFields);
                            }
                            jSONField2 = null;
                            if (field != null) {
                                jSONField2 = (JSONField) field.getAnnotation(JSONField.class);
                                if (jSONField2 != null) {
                                    i = jSONField2.ordinal();
                                    i2 = SerializerFeature.of(jSONField2.serialzeFeatures());
                                    if (jSONField2.name().length() != 0) {
                                        add(arrayList, new FieldInfo(jSONField2.name(), method22, field, cls, type, i, i2, supperMethodAnnotation, jSONField2, null));
                                    }
                                }
                            }
                            add(arrayList, new FieldInfo(decapitalize, method22, field, cls, type, i, i2, supperMethodAnnotation, jSONField2, null));
                        }
                    }
                }
            }
            for (Field field2 : cls.getFields()) {
                if (!Modifier.isStatic(field2.getModifiers())) {
                    Object obj;
                    for (FieldInfo fieldInfo : arrayList) {
                        if (fieldInfo.name.equals(field2.getName())) {
                            obj = 1;
                            break;
                        }
                    }
                    obj = null;
                    if (obj == null) {
                        i = 0;
                        i2 = 0;
                        decapitalize = field2.getName();
                        jSONField2 = (JSONField) field2.getAnnotation(JSONField.class);
                        if (jSONField2 != null) {
                            i = jSONField2.ordinal();
                            i2 = SerializerFeature.of(jSONField2.serialzeFeatures());
                            if (jSONField2.name().length() != 0) {
                                decapitalize = jSONField2.name();
                            }
                        }
                        add(arrayList, new FieldInfo(decapitalize, null, field2, cls, type, i, i2, null, jSONField2, null));
                    }
                }
            }
            for (Method method222 : cls.getMethods()) {
                String name2 = method222.getName();
                if (name2.length() >= 4 && !Modifier.isStatic(method222.getModifiers()) && name2.startsWith("get") && Character.isUpperCase(name2.charAt(3)) && method222.getParameterTypes().length == 0 && (Collection.class.isAssignableFrom(method222.getReturnType()) || Map.class.isAssignableFrom(method222.getReturnType()) || AtomicBoolean.class == method222.getReturnType() || AtomicInteger.class == method222.getReturnType() || AtomicLong.class == method222.getReturnType())) {
                    supperMethodAnnotation = (JSONField) method222.getAnnotation(JSONField.class);
                    if (supperMethodAnnotation == null || supperMethodAnnotation.name().length() <= 0) {
                        decapitalize = Character.toLowerCase(name2.charAt(3)) + name2.substring(4);
                    } else {
                        decapitalize = supperMethodAnnotation.name();
                    }
                    if (getField(arrayList, decapitalize) == null) {
                        add(arrayList, new FieldInfo(decapitalize, method222, null, cls, type, 0, 0, supperMethodAnnotation, null, null));
                    }
                }
            }
            return new JavaBeanInfo(cls, builderClass, defaultConstructor, null, null, method, jSONType, arrayList);
        }
        AccessibleObject creatorConstructor = getCreatorConstructor(cls);
        Class[] parameterTypes2;
        int i3;
        JSONField jSONField3;
        if (creatorConstructor != null) {
            TypeUtils.setAccessible(creatorConstructor);
            parameterTypes2 = creatorConstructor.getParameterTypes();
            if (parameterTypes2.length > 0) {
                Annotation[][] parameterAnnotations = creatorConstructor.getParameterAnnotations();
                for (i3 = 0; i3 < parameterTypes2.length; i3++) {
                    jSONField3 = null;
                    for (Annotation annotation : parameterAnnotations[i3]) {
                        if (annotation instanceof JSONField) {
                            jSONField3 = (JSONField) annotation;
                            break;
                        }
                    }
                    if (jSONField3 == null) {
                        throw new JSONException("illegal json creator");
                    }
                    add(arrayList, new FieldInfo(jSONField3.name(), cls, parameterTypes2[i3], creatorConstructor.getGenericParameterTypes()[i3], TypeUtils.getField(cls, jSONField3.name(), declaredFields), jSONField3.ordinal(), SerializerFeature.of(jSONField3.serialzeFeatures())));
                }
            }
            return new JavaBeanInfo(cls, builderClass, null, creatorConstructor, null, null, jSONType, arrayList);
        }
        creatorConstructor = getFactoryMethod(cls, methods);
        if (creatorConstructor != null) {
            TypeUtils.setAccessible(creatorConstructor);
            parameterTypes2 = creatorConstructor.getParameterTypes();
            if (parameterTypes2.length > 0) {
                parameterAnnotations = creatorConstructor.getParameterAnnotations();
                for (i3 = 0; i3 < parameterTypes2.length; i3++) {
                    jSONField3 = null;
                    for (Annotation annotation2 : parameterAnnotations[i3]) {
                        if (annotation2 instanceof JSONField) {
                            jSONField3 = (JSONField) annotation2;
                            break;
                        }
                    }
                    if (jSONField3 == null) {
                        throw new JSONException("illegal json creator");
                    }
                    add(arrayList, new FieldInfo(jSONField3.name(), cls, parameterTypes2[i3], creatorConstructor.getGenericParameterTypes()[i3], TypeUtils.getField(cls, jSONField3.name(), declaredFields), jSONField3.ordinal(), SerializerFeature.of(jSONField3.serialzeFeatures())));
                }
            }
            return new JavaBeanInfo(cls, builderClass, null, null, creatorConstructor, null, jSONType, arrayList);
        }
        throw new JSONException("default constructor not found. " + cls);
    }

    static Constructor<?> getDefaultConstructor(Class<?> cls) {
        if (Modifier.isAbstract(cls.getModifiers())) {
            return null;
        }
        Constructor[] declaredConstructors = cls.getDeclaredConstructors();
        for (Constructor<?> constructor : declaredConstructors) {
            if (constructor.getParameterTypes().length == 0) {
                break;
            }
        }
        Constructor<?> constructor2 = null;
        if (constructor2 == null && cls.isMemberClass() && !Modifier.isStatic(cls.getModifiers())) {
            for (Constructor<?> constructor3 : declaredConstructors) {
                Class[] parameterTypes = constructor3.getParameterTypes();
                if (parameterTypes.length == 1 && parameterTypes[0].equals(cls.getDeclaringClass())) {
                    return constructor3;
                }
            }
        }
        return constructor2;
    }

    public static Constructor<?> getCreatorConstructor(Class<?> cls) {
        Constructor<?> constructor = null;
        Constructor[] declaredConstructors = cls.getDeclaredConstructors();
        int length = declaredConstructors.length;
        int i = 0;
        while (i < length) {
            Constructor<?> constructor2;
            Constructor<?> constructor3 = declaredConstructors[i];
            if (((JSONCreator) constructor3.getAnnotation(JSONCreator.class)) == null) {
                constructor2 = constructor;
            } else if (constructor != null) {
                throw new JSONException("multi-JSONCreator");
            } else {
                constructor2 = constructor3;
            }
            i++;
            constructor = constructor2;
        }
        return constructor;
    }

    private static Method getFactoryMethod(Class<?> cls, Method[] methodArr) {
        Method method = null;
        int length = methodArr.length;
        int i = 0;
        while (i < length) {
            Method method2;
            Method method3 = methodArr[i];
            if (!Modifier.isStatic(method3.getModifiers())) {
                method2 = method;
            } else if (!cls.isAssignableFrom(method3.getReturnType())) {
                method2 = method;
            } else if (((JSONCreator) method3.getAnnotation(JSONCreator.class)) == null) {
                method2 = method;
            } else if (method != null) {
                throw new JSONException("multi-JSONCreator");
            } else {
                method2 = method3;
            }
            i++;
            method = method2;
        }
        return method;
    }

    public static Class<?> getBuilderClass(JSONType jSONType) {
        if (jSONType == null) {
            return null;
        }
        Class<?> builder = jSONType.builder();
        if (builder != Void.class) {
            return builder;
        }
        return null;
    }
}
