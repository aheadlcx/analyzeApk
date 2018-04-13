package com.alibaba.fastjson.util;

import com.alibaba.fastjson.JSONAware;
import com.alibaba.fastjson.annotation.JSONField;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;

public class FieldInfo implements Comparable<FieldInfo> {
    public final String[] alternateNames;
    public final Class<?> declaringClass;
    public final Field field;
    public final boolean fieldAccess;
    private final JSONField fieldAnnotation;
    public final Class<?> fieldClass;
    public final boolean fieldTransient;
    public final Type fieldType;
    public final String format;
    public final boolean getOnly;
    public final boolean isEnum;
    public final Method method;
    private final JSONField methodAnnotation;
    public final String name;
    public final long nameHashCode;
    private int ordinal = 0;

    public FieldInfo(String str, Class<?> cls, Class<?> cls2, Type type, Field field, int i, int i2) {
        this.name = str;
        this.declaringClass = cls;
        this.fieldClass = cls2;
        this.fieldType = type;
        this.method = null;
        this.field = field;
        this.ordinal = i;
        boolean z = cls2.isEnum() && !JSONAware.class.isAssignableFrom(cls2);
        this.isEnum = z;
        this.fieldAnnotation = null;
        this.methodAnnotation = null;
        if (field != null) {
            int modifiers = field.getModifiers();
            z = (modifiers & 1) != 0 || this.method == null;
            this.fieldAccess = z;
            this.fieldTransient = Modifier.isTransient(modifiers);
        } else {
            this.fieldAccess = false;
            this.fieldTransient = false;
        }
        this.getOnly = false;
        long j = -3750763034362895579L;
        for (int i3 = 0; i3 < str.length(); i3++) {
            j = (j ^ ((long) str.charAt(i3))) * 1099511628211L;
        }
        this.nameHashCode = j;
        this.format = null;
        this.alternateNames = new String[0];
    }

    public FieldInfo(String str, Method method, Field field, Class<?> cls, Type type, int i, int i2, JSONField jSONField, JSONField jSONField2, boolean z) {
        boolean z2;
        Type type2;
        Class cls2;
        Type type3;
        Class cls3;
        this.name = str;
        this.method = method;
        this.field = field;
        this.ordinal = i;
        this.methodAnnotation = jSONField;
        this.fieldAnnotation = jSONField2;
        JSONField annotation = getAnnotation();
        String str2 = null;
        if (annotation != null) {
            str2 = annotation.format();
            if (str2.trim().length() == 0) {
                str2 = null;
            }
            this.alternateNames = annotation.alternateNames();
        } else {
            this.alternateNames = new String[0];
        }
        this.format = str2;
        if (field != null) {
            int modifiers = field.getModifiers();
            z2 = method == null || ((modifiers & 1) != 0 && method.getReturnType() == field.getType());
            this.fieldAccess = z2;
            this.fieldTransient = (modifiers & 128) != 0;
        } else {
            this.fieldAccess = false;
            this.fieldTransient = false;
        }
        long j = -3750763034362895579L;
        for (int i3 = 0; i3 < str.length(); i3++) {
            j = (j ^ ((long) str.charAt(i3))) * 1099511628211L;
        }
        this.nameHashCode = j;
        Class cls4;
        Object obj;
        if (method != null) {
            Class[] parameterTypes = method.getParameterTypes();
            if (parameterTypes.length == 1) {
                cls4 = parameterTypes[0];
                if (cls4 == Class.class || cls4 == String.class || cls4.isPrimitive()) {
                    type2 = cls4;
                } else if (z) {
                    type2 = method.getGenericParameterTypes()[0];
                } else {
                    obj = cls4;
                }
                this.getOnly = false;
            } else {
                cls4 = method.getReturnType();
                if (cls4 == Class.class) {
                    type2 = cls4;
                } else if (z) {
                    type2 = method.getGenericReturnType();
                } else {
                    obj = cls4;
                }
                this.getOnly = true;
            }
            this.declaringClass = method.getDeclaringClass();
            cls2 = cls4;
            type3 = type2;
        } else {
            cls4 = field.getType();
            if (cls4.isPrimitive() || cls4 == String.class || cls4.isEnum()) {
                type2 = cls4;
            } else if (z) {
                type2 = field.getGenericType();
            } else {
                obj = cls4;
            }
            this.declaringClass = field.getDeclaringClass();
            this.getOnly = Modifier.isFinal(field.getModifiers());
            cls2 = cls4;
            type3 = type2;
        }
        if (cls != null && cls2 == Object.class && (type3 instanceof TypeVariable)) {
            TypeVariable typeVariable = (TypeVariable) type3;
            Type[] typeArr = null;
            if (type instanceof ParameterizedType) {
                typeArr = ((ParameterizedType) type).getActualTypeArguments();
            }
            Class cls5 = cls;
            Type[] typeArr2 = typeArr;
            while (cls5 != null && cls5 != Object.class && cls5 != this.declaringClass) {
                Type genericSuperclass = cls5.getGenericSuperclass();
                if (genericSuperclass instanceof ParameterizedType) {
                    typeArr = ((ParameterizedType) genericSuperclass).getActualTypeArguments();
                    TypeUtils.getArgument(typeArr, cls5.getTypeParameters(), typeArr2);
                } else {
                    typeArr = typeArr2;
                }
                cls5 = cls5.getSuperclass();
                typeArr2 = typeArr;
            }
            if (typeArr2 != null) {
                TypeVariable[] typeParameters = this.declaringClass.getTypeParameters();
                for (int i4 = 0; i4 < typeParameters.length; i4++) {
                    if (typeVariable.equals(typeParameters[i4])) {
                        type2 = typeArr2[i4];
                        break;
                    }
                }
            }
            type2 = null;
            if (type2 != null) {
                this.fieldClass = TypeUtils.getClass(type2);
                this.fieldType = type2;
                if (!cls2.isEnum() || JSONAware.class.isAssignableFrom(cls2)) {
                    z2 = false;
                } else {
                    z2 = true;
                }
                this.isEnum = z2;
                return;
            }
        }
        if (type3 instanceof Class) {
            cls3 = cls2;
        } else {
            if (type == null) {
                Object obj2 = cls;
            }
            type2 = getFieldType(cls, type, type3);
            if (type2 != type3) {
                Type type4;
                if (type2 instanceof ParameterizedType) {
                    type4 = type2;
                    cls3 = TypeUtils.getClass(type2);
                    type3 = type4;
                } else if (type2 instanceof Class) {
                    type4 = type2;
                    cls3 = TypeUtils.getClass(type2);
                    type3 = type4;
                }
            }
            type3 = type2;
            cls3 = cls2;
        }
        this.fieldType = type3;
        this.fieldClass = cls3;
        if (cls3.isArray() || !cls3.isEnum() || JSONAware.class.isAssignableFrom(cls3)) {
            z2 = false;
        } else {
            z2 = true;
        }
        this.isEnum = z2;
    }

    public static Type getFieldType(Class<?> cls, Type type, Type type2) {
        Type[] typeArr = null;
        if (cls == null || type == null) {
            return type2;
        }
        Type fieldType;
        if (type2 instanceof GenericArrayType) {
            Type genericComponentType = ((GenericArrayType) type2).getGenericComponentType();
            fieldType = getFieldType(cls, type, genericComponentType);
            if (genericComponentType != fieldType) {
                return Array.newInstance(TypeUtils.getClass(fieldType), 0).getClass();
            }
            return type2;
        } else if (!TypeUtils.isGenericParamType(type)) {
            return type2;
        } else {
            ParameterizedType parameterizedType;
            TypeVariable typeVariable;
            if (type2 instanceof TypeVariable) {
                parameterizedType = (ParameterizedType) TypeUtils.getGenericParamType(type);
                Class cls2 = TypeUtils.getClass(parameterizedType);
                typeVariable = (TypeVariable) type2;
                for (int i = 0; i < cls2.getTypeParameters().length; i++) {
                    if (cls2.getTypeParameters()[i].getName().equals(typeVariable.getName())) {
                        return parameterizedType.getActualTypeArguments()[i];
                    }
                }
            }
            if (!(type2 instanceof ParameterizedType)) {
                return type2;
            }
            TypeVariable[] typeParameters;
            parameterizedType = (ParameterizedType) type2;
            Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
            if (type instanceof ParameterizedType) {
                type = (ParameterizedType) type;
                typeParameters = cls.getTypeParameters();
            } else if (cls.getGenericSuperclass() instanceof ParameterizedType) {
                ParameterizedType parameterizedType2 = (ParameterizedType) cls.getGenericSuperclass();
                typeParameters = cls.getSuperclass().getTypeParameters();
                Object obj = parameterizedType2;
            } else {
                type = null;
                typeParameters = null;
            }
            int i2 = 0;
            for (int i3 = 0; i3 < actualTypeArguments.length && type != null; i3++) {
                fieldType = actualTypeArguments[i3];
                if (fieldType instanceof TypeVariable) {
                    typeVariable = (TypeVariable) fieldType;
                    for (int i4 = 0; i4 < typeParameters.length; i4++) {
                        if (typeParameters[i4].getName().equals(typeVariable.getName())) {
                            if (typeArr == null) {
                                typeArr = type.getActualTypeArguments();
                            }
                            actualTypeArguments[i3] = typeArr[i4];
                            i2 = 1;
                        }
                    }
                }
            }
            if (i2 != 0) {
                return new ParameterizedTypeImpl(actualTypeArguments, parameterizedType.getOwnerType(), parameterizedType.getRawType());
            }
            return type2;
        }
    }

    public String toString() {
        return this.name;
    }

    public int compareTo(FieldInfo fieldInfo) {
        if (this.ordinal < fieldInfo.ordinal) {
            return -1;
        }
        if (this.ordinal > fieldInfo.ordinal) {
            return 1;
        }
        return this.name.compareTo(fieldInfo.name);
    }

    public boolean equals(FieldInfo fieldInfo) {
        if (fieldInfo == this || compareTo(fieldInfo) == 0) {
            return true;
        }
        return false;
    }

    public JSONField getAnnotation() {
        if (this.fieldAnnotation != null) {
            return this.fieldAnnotation;
        }
        return this.methodAnnotation;
    }

    public Object get(Object obj) throws IllegalAccessException, InvocationTargetException {
        if (this.fieldAccess) {
            return this.field.get(obj);
        }
        return this.method.invoke(obj, new Object[0]);
    }

    public void set(Object obj, Object obj2) throws IllegalAccessException, InvocationTargetException {
        if (this.method != null) {
            this.method.invoke(obj, new Object[]{obj2});
            return;
        }
        this.field.set(obj, obj2);
    }
}
