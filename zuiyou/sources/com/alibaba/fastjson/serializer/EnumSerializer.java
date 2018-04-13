package com.alibaba.fastjson.serializer;

import java.io.IOException;
import java.lang.reflect.Type;

class EnumSerializer implements ObjectSerializer {
    EnumSerializer() {
    }

    public void write(JSONSerializer jSONSerializer, Object obj, Object obj2, Type type) throws IOException {
        SerializeWriter serializeWriter = jSONSerializer.out;
        if ((serializeWriter.features & SerializerFeature.WriteEnumUsingToString.mask) != 0) {
            String str = ((Enum) obj).toString();
            if ((serializeWriter.features & SerializerFeature.UseSingleQuotes.mask) != 0) {
                serializeWriter.writeStringWithSingleQuote(str);
                return;
            } else {
                serializeWriter.writeStringWithDoubleQuote(str, '\u0000', false);
                return;
            }
        }
        serializeWriter.writeInt(((Enum) obj).ordinal());
    }
}
