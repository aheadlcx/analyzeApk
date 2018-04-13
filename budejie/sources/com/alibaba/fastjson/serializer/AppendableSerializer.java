package com.alibaba.fastjson.serializer;

import java.io.IOException;
import java.lang.reflect.Type;

public class AppendableSerializer implements ObjectSerializer {
    public static final AppendableSerializer instance = new AppendableSerializer();

    public void write(JSONSerializer jSONSerializer, Object obj, Object obj2, Type type, int i) throws IOException {
        if (obj == null) {
            SerializeWriter serializeWriter = jSONSerializer.out;
            if (serializeWriter.isEnabled(SerializerFeature.WriteNullStringAsEmpty)) {
                serializeWriter.writeString("");
                return;
            } else {
                serializeWriter.writeNull();
                return;
            }
        }
        jSONSerializer.write(obj.toString());
    }
}
