package com.alibaba.fastjson.util;

import com.alibaba.fastjson.annotation.JSONField;
import java.lang.annotation.Annotation;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;

public class FieldInfo implements Comparable<FieldInfo> {
    public final Class<?> declaringClass;
    public final Field field;
    public final boolean fieldAccess;
    private final JSONField fieldAnnotation;
    public final Class<?> fieldClass;
    public final boolean fieldTransient;
    public final Type fieldType;
    public final boolean getOnly;
    public final boolean isEnum;
    public final String label;
    public final Method method;
    private final JSONField methodAnnotation;
    public final String name;
    public final char[] name_chars;
    private int ordinal = 0;
    public final int serialzeFeatures;

    public FieldInfo(String str, Class<?> cls, Class<?> cls2, Type type, Field field, int i, int i2) {
        this.name = str;
        this.declaringClass = cls;
        this.fieldClass = cls2;
        this.fieldType = type;
        this.method = null;
        this.field = field;
        this.ordinal = i;
        this.serialzeFeatures = i2;
        this.isEnum = cls2.isEnum();
        if (field != null) {
            int modifiers = field.getModifiers();
            boolean z = (modifiers & 1) != 0 || this.method == null;
            this.fieldAccess = z;
            this.fieldTransient = Modifier.isTransient(modifiers);
        } else {
            this.fieldTransient = false;
            this.fieldAccess = false;
        }
        this.name_chars = genFieldNameChars();
        if (field != null) {
            TypeUtils.setAccessible(field);
        }
        this.label = "";
        this.fieldAnnotation = null;
        this.methodAnnotation = null;
        this.getOnly = false;
    }

    public FieldInfo(String str, Method method, Field field, Class<?> cls, Type type, int i, int i2, JSONField jSONField, JSONField jSONField2, String str2) {
        boolean z;
        Type type2;
        Class cls2;
        Type inheritGenericType;
        boolean z2 = true;
        if (field != null) {
            String name = field.getName();
            if (name.equals(str)) {
                str = name;
            }
        }
        this.name = str;
        this.method = method;
        this.field = field;
        this.ordinal = i;
        this.serialzeFeatures = i2;
        this.fieldAnnotation = jSONField;
        this.methodAnnotation = jSONField2;
        if (field != null) {
            int modifiers = field.getModifiers();
            z = (modifiers & 1) != 0 || method == null;
            this.fieldAccess = z;
            this.fieldTransient = Modifier.isTransient(modifiers);
        } else {
            this.fieldAccess = false;
            this.fieldTransient = false;
        }
        if (str2 == null || str2.length() <= 0) {
            this.label = "";
        } else {
            this.label = str2;
        }
        this.name_chars = genFieldNameChars();
        if (method != null) {
            TypeUtils.setAccessible(method);
        }
        if (field != null) {
            TypeUtils.setAccessible(field);
        }
        Class cls3;
        Class cls4;
        if (method != null) {
            Class[] parameterTypes = method.getParameterTypes();
            if (parameterTypes.length == 1) {
                cls3 = parameterTypes[0];
                z2 = false;
                type2 = method.getGenericParameterTypes()[0];
            } else {
                cls3 = method.getReturnType();
                type2 = method.getGenericReturnType();
            }
            this.declaringClass = method.getDeclaringClass();
            cls4 = cls3;
            z = z2;
            cls2 = cls4;
        } else {
            cls3 = field.getType();
            Type genericType = field.getGenericType();
            this.declaringClass = field.getDeclaringClass();
            cls4 = cls3;
            z = false;
            type2 = genericType;
            cls2 = cls4;
        }
        this.getOnly = z;
        if (cls != null && cls2 == Object.class && (type2 instanceof TypeVariable)) {
            inheritGenericType = getInheritGenericType(cls, (TypeVariable) type2);
            if (inheritGenericType != null) {
                this.fieldClass = TypeUtils.getClass(inheritGenericType);
                this.fieldType = inheritGenericType;
                this.isEnum = cls2.isEnum();
                return;
            }
        }
        if (!(type2 instanceof Class)) {
            inheritGenericType = getFieldType(cls, type, type2);
            if (inheritGenericType != type2) {
                if (inheritGenericType instanceof ParameterizedType) {
                    cls2 = TypeUtils.getClass(inheritGenericType);
                    type2 = inheritGenericType;
                } else if (inheritGenericType instanceof Class) {
                    cls2 = TypeUtils.getClass(inheritGenericType);
                    type2 = inheritGenericType;
                }
            }
            type2 = inheritGenericType;
        }
        this.fieldType = type2;
        this.fieldClass = cls2;
        this.isEnum = cls2.isEnum();
    }

    protected char[] genFieldNameChars() {
        int length = this.name.length();
        char[] cArr = new char[(length + 3)];
        this.name.getChars(0, this.name.length(), cArr, 1);
        cArr[0] = '\"';
        cArr[length + 1] = '\"';
        cArr[length + 2] = ':';
        return cArr;
    }

    public <T extends Annotation> T getAnnation(Class<T> cls) {
        if (cls == JSONField.class) {
            return getAnnotation();
        }
        T t = null;
        if (this.method != null) {
            t = this.method.getAnnotation(cls);
        }
        if (t == null && this.field == null) {
            return this.field.getAnnotation(cls);
        }
        return t;
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
            if (type2 instanceof TypeVariable) {
                parameterizedType = (ParameterizedType) TypeUtils.getGenericParamType(type);
                TypeVariable typeVariable = (TypeVariable) type2;
                TypeVariable[] typeParameters = TypeUtils.getClass(parameterizedType).getTypeParameters();
                for (int i = 0; i < typeParameters.length; i++) {
                    if (typeParameters[i].getName().equals(typeVariable.getName())) {
                        return parameterizedType.getActualTypeArguments()[i];
                    }
                }
            }
            if (!(type2 instanceof ParameterizedType)) {
                return type2;
            }
            parameterizedType = (ParameterizedType) type2;
            Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
            int i2 = 0;
            TypeVariable[] typeVariableArr = null;
            int i3 = 0;
            while (i2 < actualTypeArguments.length) {
                TypeVariable[] typeVariableArr2;
                fieldType = actualTypeArguments[i2];
                if (fieldType instanceof TypeVariable) {
                    TypeVariable typeVariable2 = (TypeVariable) fieldType;
                    if (type instanceof ParameterizedType) {
                        if (typeVariableArr == null) {
                            typeVariableArr = cls.getTypeParameters();
                        }
                        Type[] typeArr2 = typeArr;
                        int i4 = i3;
                        for (i3 = 0; i3 < typeVariableArr.length; i3++) {
                            if (typeVariableArr[i3].getName().equals(typeVariable2.getName())) {
                                if (typeArr2 == null) {
                                    typeArr2 = ((ParameterizedType) type).getActualTypeArguments();
                                }
                                actualTypeArguments[i2] = typeArr2[i3];
                                i4 = 1;
                            }
                        }
                        i3 = i4;
                        typeArr = typeArr2;
                        typeVariableArr2 = typeVariableArr;
                        i2++;
                        typeVariableArr = typeVariableArr2;
                    }
                }
                typeVariableArr2 = typeVariableArr;
                i2++;
                typeVariableArr = typeVariableArr2;
            }
            if (i3 != 0) {
                return new ParameterizedTypeImpl(actualTypeArguments, parameterizedType.getOwnerType(), parameterizedType.getRawType());
            }
            return type2;
        }
    }

    public static Type getInheritGenericType(Class<?> cls, TypeVariable<?> typeVariable) {
        Type genericDeclaration = typeVariable.getGenericDeclaration();
        Type genericSuperclass;
        do {
            genericSuperclass = cls.getGenericSuperclass();
            if (genericSuperclass == null) {
                return null;
            }
            if (genericSuperclass instanceof ParameterizedType) {
                ParameterizedType parameterizedType = (ParameterizedType) genericSuperclass;
                if (parameterizedType.getRawType() == genericDeclaration) {
                    TypeVariable[] typeParameters = genericDeclaration.getTypeParameters();
                    Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
                    for (int i = 0; i < typeParameters.length; i++) {
                        if (typeParameters[i] == typeVariable) {
                            return actualTypeArguments[i];
                        }
                    }
                    return null;
                }
            }
            cls = TypeUtils.getClass(genericSuperclass);
        } while (genericSuperclass != null);
        return null;
    }

    public String toString() {
        return this.name;
    }

    public Member getMember() {
        if (this.method != null) {
            return this.method;
        }
        return this.field;
    }

    protected Class<?> getDeclaredClass() {
        if (this.method != null) {
            return this.method.getDeclaringClass();
        }
        if (this.field != null) {
            return this.field.getDeclaringClass();
        }
        return null;
    }

    public int compareTo(FieldInfo fieldInfo) {
        int i = 0;
        if (this.ordinal < fieldInfo.ordinal) {
            return -1;
        }
        if (this.ordinal > fieldInfo.ordinal) {
            return 1;
        }
        int compareTo = this.name.compareTo(fieldInfo.name);
        if (compareTo != 0) {
            return compareTo;
        }
        Class declaredClass = getDeclaredClass();
        Class declaredClass2 = fieldInfo.getDeclaredClass();
        if (!(declaredClass == null || declaredClass2 == null || declaredClass == declaredClass2)) {
            if (declaredClass.isAssignableFrom(declaredClass2)) {
                return -1;
            }
            if (declaredClass2.isAssignableFrom(declaredClass)) {
                return 1;
            }
        }
        compareTo = (this.field == null || this.field.getType() != this.fieldClass) ? 0 : 1;
        if (fieldInfo.field != null && fieldInfo.field.getType() == fieldInfo.fieldClass) {
            i = 1;
        }
        if (compareTo != 0 && i == 0) {
            return 1;
        }
        if (i != 0 && compareTo == 0) {
            return -1;
        }
        if (fieldInfo.fieldClass.isPrimitive() && !this.fieldClass.isPrimitive()) {
            return 1;
        }
        if (this.fieldClass.isPrimitive() && !fieldInfo.fieldClass.isPrimitive()) {
            return -1;
        }
        if (fieldInfo.fieldClass.getName().startsWith("java.") && !this.fieldClass.getName().startsWith("java.")) {
            return 1;
        }
        if (!this.fieldClass.getName().startsWith("java.") || fieldInfo.fieldClass.getName().startsWith("java.")) {
            return this.fieldClass.getName().compareTo(fieldInfo.fieldClass.getName());
        }
        return -1;
    }

    public JSONField getAnnotation() {
        if (this.fieldAnnotation != null) {
            return this.fieldAnnotation;
        }
        return this.methodAnnotation;
    }

    public String getFormat() {
        JSONField annotation = getAnnotation();
        if (annotation == null) {
            return null;
        }
        String format = annotation.format();
        if (format.trim().length() == 0) {
            return null;
        }
        return format;
    }

    public Object get(Object obj) throws IllegalAccessException, InvocationTargetException {
        if (this.method != null) {
            return this.method.invoke(obj, new Object[0]);
        }
        return this.field.get(obj);
    }

    public void set(Object obj, Object obj2) throws IllegalAccessException, InvocationTargetException {
        if (this.method != null) {
            this.method.invoke(obj, new Object[]{obj2});
            return;
        }
        this.field.set(obj, obj2);
    }

    public void setAccessible() throws SecurityException {
        if (this.method != null) {
            TypeUtils.setAccessible(this.method);
        } else {
            TypeUtils.setAccessible(this.field);
        }
    }
}
