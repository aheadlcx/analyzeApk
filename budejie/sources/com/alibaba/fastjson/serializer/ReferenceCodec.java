package com.alibaba.fastjson.serializer;

import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import java.io.IOException;
import java.lang.ref.Reference;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.concurrent.atomic.AtomicReference;

public class ReferenceCodec implements ObjectDeserializer, ObjectSerializer {
    public static final ReferenceCodec instance = new ReferenceCodec();

    public void write(JSONSerializer jSONSerializer, Object obj, Object obj2, Type type, int i) throws IOException {
        Object obj3;
        if (obj instanceof AtomicReference) {
            obj3 = ((AtomicReference) obj).get();
        } else {
            obj3 = ((Reference) obj).get();
        }
        jSONSerializer.write(obj3);
    }

    public <T> T deserialze(DefaultJSONParser defaultJSONParser, Type type, Object obj) {
        ParameterizedType parameterizedType = (ParameterizedType) type;
        Object parseObject = defaultJSONParser.parseObject(parameterizedType.getActualTypeArguments()[0]);
        Type rawType = parameterizedType.getRawType();
        if (rawType == AtomicReference.class) {
            return new AtomicReference(parseObject);
        }
        if (rawType == WeakReference.class) {
            return new WeakReference(parseObject);
        }
        if (rawType == SoftReference.class) {
            return new SoftReference(parseObject);
        }
        throw new UnsupportedOperationException(rawType.toString());
    }

    public int getFastMatchToken() {
        return 12;
    }
}
