package com.alibaba.fastjson.parser.deserializer;

import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.JSONLexer;
import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;
import com.alibaba.fastjson.serializer.SerializeWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.OffsetTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class Jdk8DateCodec implements ObjectDeserializer, ObjectSerializer {
    public static final Jdk8DateCodec instance = new Jdk8DateCodec();

    public <T> T deserialze(DefaultJSONParser defaultJSONParser, Type type, Object obj) {
        JSONLexer jSONLexer = defaultJSONParser.lexer;
        if (jSONLexer.token() == 4) {
            CharSequence stringVal = jSONLexer.stringVal();
            jSONLexer.nextToken();
            if (type == LocalDateTime.class) {
                return LocalDateTime.parse(stringVal);
            }
            if (type == LocalDate.class) {
                return LocalDate.parse(stringVal);
            }
            if (type == LocalTime.class) {
                return LocalTime.parse(stringVal);
            }
            if (type == ZonedDateTime.class) {
                return ZonedDateTime.parse(stringVal);
            }
            if (type == OffsetDateTime.class) {
                return OffsetDateTime.parse(stringVal);
            }
            if (type == OffsetTime.class) {
                return OffsetTime.parse(stringVal);
            }
            if (type == ZoneId.class) {
                return ZoneId.of(stringVal);
            }
            if (type == Period.class) {
                return Period.parse(stringVal);
            }
            if (type == Duration.class) {
                return Duration.parse(stringVal);
            }
            if (type == Instant.class) {
                return Instant.parse(stringVal);
            }
            return null;
        }
        throw new UnsupportedOperationException();
    }

    public int getFastMatchToken() {
        return 4;
    }

    public void write(JSONSerializer jSONSerializer, Object obj, Object obj2, Type type, int i) throws IOException {
        SerializeWriter serializeWriter = jSONSerializer.out;
        if (obj == null) {
            serializeWriter.writeNull();
        } else {
            serializeWriter.writeString(obj.toString());
        }
    }
}
