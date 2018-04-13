package com.alibaba.fastjson;

import com.alibaba.fastjson.util.ParameterizedTypeImpl;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.List;

public class TypeReference<T> {
    public static final Type LIST_STRING = new TypeReference<List<String>>() {
    }.getType();
    protected final Type type;

    protected TypeReference() {
        this.type = ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    protected TypeReference(Type... typeArr) {
        int i = 0;
        ParameterizedType parameterizedType = (ParameterizedType) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
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
        this.type = new ParameterizedTypeImpl(actualTypeArguments, getClass(), rawType);
    }

    public Type getType() {
        return this.type;
    }
}
