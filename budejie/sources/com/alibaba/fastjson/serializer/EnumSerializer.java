package com.alibaba.fastjson.serializer;

import java.io.IOException;
import java.lang.reflect.Type;

public class EnumSerializer implements ObjectSerializer {
    public static final EnumSerializer instance = new EnumSerializer();

    public void write(JSONSerializer jSONSerializer, Object obj, Object obj2, Type type, int i) throws IOException {
        SerializeWriter serializeWriter = jSONSerializer.out;
        if (obj == null) {
            serializeWriter.writeNull();
            return;
        }
        Enum enumR = (Enum) obj;
        if (serializeWriter.writeEnumUsingName && !serializeWriter.writeEnumUsingToString) {
            jSONSerializer.write(enumR.name());
        } else if (serializeWriter.writeEnumUsingToString) {
            jSONSerializer.write(enumR.toString());
        } else {
            serializeWriter.writeInt(enumR.ordinal());
        }
    }
}
