package com.alibaba.fastjson.serializer;

import com.ali.auth.third.core.model.Constants;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicLongArray;
import org.apache.commons.httpclient.HttpState;

public class AtomicCodec implements ObjectDeserializer, ObjectSerializer {
    public static final AtomicCodec instance = new AtomicCodec();

    public void write(JSONSerializer jSONSerializer, Object obj, Object obj2, Type type, int i) throws IOException {
        int i2 = 0;
        SerializeWriter serializeWriter = jSONSerializer.out;
        if (obj instanceof AtomicInteger) {
            serializeWriter.writeInt(((AtomicInteger) obj).get());
        } else if (obj instanceof AtomicLong) {
            serializeWriter.writeLong(((AtomicLong) obj).get());
        } else if (obj instanceof AtomicBoolean) {
            serializeWriter.append(((AtomicBoolean) obj).get() ? Constants.SERVICE_SCOPE_FLAG_VALUE : HttpState.PREEMPTIVE_DEFAULT);
        } else if (obj == null) {
            if (serializeWriter.isEnabled(SerializerFeature.WriteNullListAsEmpty)) {
                serializeWriter.write("[]");
            } else {
                serializeWriter.writeNull();
            }
        } else if (obj instanceof AtomicIntegerArray) {
            AtomicIntegerArray atomicIntegerArray = (AtomicIntegerArray) obj;
            r2 = atomicIntegerArray.length();
            serializeWriter.write(91);
            while (i2 < r2) {
                int i3 = atomicIntegerArray.get(i2);
                if (i2 != 0) {
                    serializeWriter.write(44);
                }
                serializeWriter.writeInt(i3);
                i2++;
            }
            serializeWriter.write(93);
        } else {
            AtomicLongArray atomicLongArray = (AtomicLongArray) obj;
            r2 = atomicLongArray.length();
            serializeWriter.write(91);
            while (i2 < r2) {
                long j = atomicLongArray.get(i2);
                if (i2 != 0) {
                    serializeWriter.write(44);
                }
                serializeWriter.writeLong(j);
                i2++;
            }
            serializeWriter.write(93);
        }
    }

    public <T> T deserialze(DefaultJSONParser defaultJSONParser, Type type, Object obj) {
        int i = 0;
        if (defaultJSONParser.lexer.token() == 8) {
            defaultJSONParser.lexer.nextToken(16);
            return null;
        }
        Collection jSONArray = new JSONArray();
        defaultJSONParser.parseArray(jSONArray);
        T atomicIntegerArray;
        if (type == AtomicIntegerArray.class) {
            atomicIntegerArray = new AtomicIntegerArray(jSONArray.size());
            while (i < jSONArray.size()) {
                atomicIntegerArray.set(i, jSONArray.getInteger(i).intValue());
                i++;
            }
            return atomicIntegerArray;
        }
        atomicIntegerArray = new AtomicLongArray(jSONArray.size());
        while (i < jSONArray.size()) {
            atomicIntegerArray.set(i, jSONArray.getLong(i).longValue());
            i++;
        }
        return atomicIntegerArray;
    }

    public int getFastMatchToken() {
        return 14;
    }
}
