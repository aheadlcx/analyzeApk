package com.alibaba.fastjson.parser.deserializer;

import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.util.TypeUtils;
import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.Collection;

public class JavaObjectDeserializer implements ObjectDeserializer {
    public static final JavaObjectDeserializer instance = new JavaObjectDeserializer();

    public <T> T deserialze(DefaultJSONParser defaultJSONParser, Type type, Object obj) {
        if (!(type instanceof GenericArrayType)) {
            return defaultJSONParser.parse(obj);
        }
        Type genericComponentType = ((GenericArrayType) type).getGenericComponentType();
        if (genericComponentType instanceof TypeVariable) {
            genericComponentType = ((TypeVariable) genericComponentType).getBounds()[0];
        }
        Object arrayList = new ArrayList();
        defaultJSONParser.parseArray(genericComponentType, (Collection) arrayList);
        if (!(genericComponentType instanceof Class)) {
            return arrayList.toArray();
        }
        Class cls = (Class) genericComponentType;
        if (cls == Boolean.TYPE) {
            return TypeUtils.cast(arrayList, boolean[].class, defaultJSONParser.getConfig());
        }
        if (cls == Short.TYPE) {
            return TypeUtils.cast(arrayList, short[].class, defaultJSONParser.getConfig());
        }
        if (cls == Integer.TYPE) {
            return TypeUtils.cast(arrayList, int[].class, defaultJSONParser.getConfig());
        }
        if (cls == Long.TYPE) {
            return TypeUtils.cast(arrayList, long[].class, defaultJSONParser.getConfig());
        }
        if (cls == Float.TYPE) {
            return TypeUtils.cast(arrayList, float[].class, defaultJSONParser.getConfig());
        }
        if (cls == Double.TYPE) {
            return TypeUtils.cast(arrayList, double[].class, defaultJSONParser.getConfig());
        }
        Object[] objArr = (Object[]) Array.newInstance(cls, arrayList.size());
        arrayList.toArray(objArr);
        return objArr;
    }

    public int getFastMatchToken() {
        return 12;
    }
}
