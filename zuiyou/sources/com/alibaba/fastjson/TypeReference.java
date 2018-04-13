package com.alibaba.fastjson;

import com.alibaba.fastjson.util.ParameterizedTypeImpl;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class TypeReference<T> {
    static ConcurrentMap<Type, Type> classTypeCache = new ConcurrentHashMap(16, 0.75f, 1);
    protected final Type type;

    protected TypeReference() {
        Type type = ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        if (type instanceof Class) {
            this.type = type;
            return;
        }
        Type type2 = (Type) classTypeCache.get(type);
        if (type2 == null) {
            classTypeCache.putIfAbsent(type, type);
            type2 = (Type) classTypeCache.get(type);
        }
        this.type = type2;
    }

    protected TypeReference(Type... typeArr) {
        int i = 0;
        Type type = getClass();
        ParameterizedType parameterizedType = (ParameterizedType) ((ParameterizedType) type.getGenericSuperclass()).getActualTypeArguments()[0];
        Type rawType = parameterizedType.getRawType();
        Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
        for (int i2 = 0; i2 < actualTypeArguments.length; i2++) {
            if (actualTypeArguments[i2] instanceof TypeVariable) {
                int i3 = i + 1;
                actualTypeArguments[i2] = typeArr[i];
                if (i3 >= typeArr.length) {
                    break;
                }
                i = i3;
            }
        }
        ParameterizedTypeImpl parameterizedTypeImpl = new ParameterizedTypeImpl(actualTypeArguments, type, rawType);
        Type type2 = (Type) classTypeCache.get(parameterizedTypeImpl);
        if (type2 == null) {
            classTypeCache.putIfAbsent(parameterizedTypeImpl, parameterizedTypeImpl);
            type2 = (Type) classTypeCache.get(parameterizedTypeImpl);
        }
        this.type = type2;
    }

    public Type getType() {
        return this.type;
    }
}
