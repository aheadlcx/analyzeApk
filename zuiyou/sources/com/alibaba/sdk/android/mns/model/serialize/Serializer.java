package com.alibaba.sdk.android.mns.model.serialize;

public interface Serializer<T> {
    String serialize(T t, String str) throws Exception;
}
