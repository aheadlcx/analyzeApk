package com.alibaba.fastjson.serializer;

import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import com.alibaba.fastjson.util.TypeUtils;
import java.io.IOException;
import java.lang.reflect.Type;

public class CharacterCodec implements ObjectDeserializer, ObjectSerializer {
    public static final CharacterCodec instance = new CharacterCodec();

    public void write(JSONSerializer jSONSerializer, Object obj, Object obj2, Type type, int i) throws IOException {
        SerializeWriter serializeWriter = jSONSerializer.out;
        Character ch = (Character) obj;
        if (ch == null) {
            serializeWriter.writeString("");
        } else if (ch.charValue() == '\u0000') {
            serializeWriter.writeString("\u0000");
        } else {
            serializeWriter.writeString(ch.toString());
        }
    }

    public <T> T deserialze(DefaultJSONParser defaultJSONParser, Type type, Object obj) {
        Object parse = defaultJSONParser.parse();
        if (parse == null) {
            return null;
        }
        return TypeUtils.castToChar(parse);
    }

    public int getFastMatchToken() {
        return 4;
    }
}
