package com.alibaba.fastjson.util;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;

public class ParameterizedTypeImpl implements ParameterizedType {
    private final Type[] actualTypeArguments;
    private final Type ownerType;
    private final Type rawType;

    public ParameterizedTypeImpl(Type[] typeArr, Type type, Type type2) {
        this.actualTypeArguments = typeArr;
        this.ownerType = type;
        this.rawType = type2;
    }

    public Type[] getActualTypeArguments() {
        return this.actualTypeArguments;
    }

    public Type getOwnerType() {
        return this.ownerType;
    }

    public Type getRawType() {
        return this.rawType;
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ParameterizedTypeImpl parameterizedTypeImpl = (ParameterizedTypeImpl) obj;
        if (!Arrays.equals(this.actualTypeArguments, parameterizedTypeImpl.actualTypeArguments)) {
            return false;
        }
        if (this.ownerType != null) {
            if (!this.ownerType.equals(parameterizedTypeImpl.ownerType)) {
                return false;
            }
        } else if (parameterizedTypeImpl.ownerType != null) {
            return false;
        }
        if (this.rawType != null) {
            z = this.rawType.equals(parameterizedTypeImpl.rawType);
        } else if (parameterizedTypeImpl.rawType != null) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        int hashCode;
        int i = 0;
        if (this.actualTypeArguments != null) {
            hashCode = Arrays.hashCode(this.actualTypeArguments);
        } else {
            hashCode = 0;
        }
        int i2 = hashCode * 31;
        if (this.ownerType != null) {
            hashCode = this.ownerType.hashCode();
        } else {
            hashCode = 0;
        }
        hashCode = (hashCode + i2) * 31;
        if (this.rawType != null) {
            i = this.rawType.hashCode();
        }
        return hashCode + i;
    }
}
