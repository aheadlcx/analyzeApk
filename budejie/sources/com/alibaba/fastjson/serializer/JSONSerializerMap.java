package com.alibaba.fastjson.serializer;

@Deprecated
public class JSONSerializerMap extends SerializeConfig {
    public final boolean put(Class<?> cls, ObjectSerializer objectSerializer) {
        return super.put(cls, objectSerializer);
    }
}
