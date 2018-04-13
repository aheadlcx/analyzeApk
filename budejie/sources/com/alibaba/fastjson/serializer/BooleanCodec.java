package com.alibaba.fastjson.serializer;

import com.ali.auth.third.core.model.Constants;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.JSONLexer;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import com.alibaba.fastjson.util.TypeUtils;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.concurrent.atomic.AtomicBoolean;
import org.apache.commons.httpclient.HttpState;

public class BooleanCodec implements ObjectDeserializer, ObjectSerializer {
    public static final BooleanCodec instance = new BooleanCodec();

    public void write(JSONSerializer jSONSerializer, Object obj, Object obj2, Type type, int i) throws IOException {
        SerializeWriter serializeWriter = jSONSerializer.out;
        Boolean bool = (Boolean) obj;
        if (bool == null) {
            if (serializeWriter.isEnabled(SerializerFeature.WriteNullBooleanAsFalse)) {
                serializeWriter.write(HttpState.PREEMPTIVE_DEFAULT);
            } else {
                serializeWriter.writeNull();
            }
        } else if (bool.booleanValue()) {
            serializeWriter.write(Constants.SERVICE_SCOPE_FLAG_VALUE);
        } else {
            serializeWriter.write(HttpState.PREEMPTIVE_DEFAULT);
        }
    }

    public <T> T deserialze(DefaultJSONParser defaultJSONParser, Type type, Object obj) {
        T t;
        JSONLexer jSONLexer = defaultJSONParser.lexer;
        if (jSONLexer.token() == 6) {
            jSONLexer.nextToken(16);
            t = Boolean.TRUE;
        } else if (jSONLexer.token() == 7) {
            jSONLexer.nextToken(16);
            t = Boolean.FALSE;
        } else if (jSONLexer.token() == 2) {
            int intValue = jSONLexer.intValue();
            jSONLexer.nextToken(16);
            if (intValue == 1) {
                t = Boolean.TRUE;
            } else {
                t = Boolean.FALSE;
            }
        } else {
            Object parse = defaultJSONParser.parse();
            if (parse == null) {
                return null;
            }
            t = TypeUtils.castToBoolean(parse);
        }
        if (type == AtomicBoolean.class) {
            return new AtomicBoolean(t.booleanValue());
        }
        return t;
    }

    public int getFastMatchToken() {
        return 6;
    }
}
