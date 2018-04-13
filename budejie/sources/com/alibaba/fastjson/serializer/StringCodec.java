package com.alibaba.fastjson.serializer;

import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.JSONLexer;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import java.io.IOException;
import java.lang.reflect.Type;

public class StringCodec implements ObjectDeserializer, ObjectSerializer {
    public static StringCodec instance = new StringCodec();

    public void write(JSONSerializer jSONSerializer, Object obj, Object obj2, Type type, int i) throws IOException {
        write(jSONSerializer, (String) obj);
    }

    public void write(JSONSerializer jSONSerializer, String str) {
        SerializeWriter serializeWriter = jSONSerializer.out;
        if (str != null) {
            serializeWriter.writeString(str);
        } else if (serializeWriter.isEnabled(SerializerFeature.WriteNullStringAsEmpty)) {
            serializeWriter.writeString("");
        } else {
            serializeWriter.writeNull();
        }
    }

    public <T> T deserialze(DefaultJSONParser defaultJSONParser, Type type, Object obj) {
        JSONLexer jSONLexer;
        String stringVal;
        Object parse;
        if (type == StringBuffer.class) {
            jSONLexer = defaultJSONParser.lexer;
            if (jSONLexer.token() == 4) {
                stringVal = jSONLexer.stringVal();
                jSONLexer.nextToken(16);
                return new StringBuffer(stringVal);
            }
            parse = defaultJSONParser.parse();
            if (parse != null) {
                return new StringBuffer(parse.toString());
            }
            return null;
        } else if (type != StringBuilder.class) {
            return deserialze(defaultJSONParser);
        } else {
            jSONLexer = defaultJSONParser.lexer;
            if (jSONLexer.token() == 4) {
                stringVal = jSONLexer.stringVal();
                jSONLexer.nextToken(16);
                return new StringBuilder(stringVal);
            }
            parse = defaultJSONParser.parse();
            if (parse != null) {
                return new StringBuilder(parse.toString());
            }
            return null;
        }
    }

    public static <T> T deserialze(DefaultJSONParser defaultJSONParser) {
        JSONLexer lexer = defaultJSONParser.getLexer();
        T stringVal;
        if (lexer.token() == 4) {
            stringVal = lexer.stringVal();
            lexer.nextToken(16);
            return stringVal;
        } else if (lexer.token() == 2) {
            stringVal = lexer.numberString();
            lexer.nextToken(16);
            return stringVal;
        } else {
            Object parse = defaultJSONParser.parse();
            if (parse == null) {
                return null;
            }
            return parse.toString();
        }
    }

    public int getFastMatchToken() {
        return 4;
    }
}
