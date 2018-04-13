package com.fasterxml.jackson.databind.util;

import com.fasterxml.jackson.databind.AnnotationIntrospector;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class EnumResolver implements Serializable {
    private static final long serialVersionUID = 1;
    protected final Class<Enum<?>> _enumClass;
    protected final Enum<?>[] _enums;
    protected final HashMap<String, Enum<?>> _enumsById;

    protected EnumResolver(Class<Enum<?>> cls, Enum<?>[] enumArr, HashMap<String, Enum<?>> hashMap) {
        this._enumClass = cls;
        this._enums = enumArr;
        this._enumsById = hashMap;
    }

    public static EnumResolver constructFor(Class<Enum<?>> cls, AnnotationIntrospector annotationIntrospector) {
        Enum[] enumArr = (Enum[]) cls.getEnumConstants();
        if (enumArr == null) {
            throw new IllegalArgumentException("No enum constants for class " + cls.getName());
        }
        String[] findEnumValues = annotationIntrospector.findEnumValues(cls, enumArr, new String[enumArr.length]);
        HashMap hashMap = new HashMap();
        int length = enumArr.length;
        for (int i = 0; i < length; i++) {
            Object obj = findEnumValues[i];
            if (obj == null) {
                obj = enumArr[i].name();
            }
            hashMap.put(obj, enumArr[i]);
        }
        return new EnumResolver(cls, enumArr, hashMap);
    }

    public static EnumResolver constructUsingToString(Class<Enum<?>> cls) {
        Enum[] enumArr = (Enum[]) cls.getEnumConstants();
        HashMap hashMap = new HashMap();
        int length = enumArr.length;
        while (true) {
            length--;
            if (length < 0) {
                return new EnumResolver(cls, enumArr, hashMap);
            }
            Enum enumR = enumArr[length];
            hashMap.put(enumR.toString(), enumR);
        }
    }

    public static EnumResolver constructUsingMethod(Class<Enum<?>> cls, Method method) {
        Enum[] enumArr = (Enum[]) cls.getEnumConstants();
        HashMap hashMap = new HashMap();
        int length = enumArr.length;
        while (true) {
            length--;
            if (length < 0) {
                return new EnumResolver(cls, enumArr, hashMap);
            }
            Object obj = enumArr[length];
            try {
                Object invoke = method.invoke(obj, new Object[0]);
                if (invoke != null) {
                    hashMap.put(invoke.toString(), obj);
                }
            } catch (Exception e) {
                throw new IllegalArgumentException("Failed to access @JsonValue of Enum value " + obj + ": " + e.getMessage());
            }
        }
    }

    public static EnumResolver constructUnsafe(Class<?> cls, AnnotationIntrospector annotationIntrospector) {
        return constructFor(cls, annotationIntrospector);
    }

    public static EnumResolver constructUnsafeUsingToString(Class<?> cls) {
        return constructUsingToString(cls);
    }

    public static EnumResolver constructUnsafeUsingMethod(Class<?> cls, Method method) {
        return constructUsingMethod(cls, method);
    }

    public CompactStringObjectMap constructLookup() {
        return CompactStringObjectMap.construct(this._enumsById);
    }

    public Enum<?> findEnum(String str) {
        return (Enum) this._enumsById.get(str);
    }

    public Enum<?> getEnum(int i) {
        if (i < 0 || i >= this._enums.length) {
            return null;
        }
        return this._enums[i];
    }

    public Enum<?>[] getRawEnums() {
        return this._enums;
    }

    public List<Enum<?>> getEnums() {
        List arrayList = new ArrayList(this._enums.length);
        for (Object add : this._enums) {
            arrayList.add(add);
        }
        return arrayList;
    }

    public Class<Enum<?>> getEnumClass() {
        return this._enumClass;
    }

    public int lastValidIndex() {
        return this._enums.length - 1;
    }
}
