package com.alibaba.fastjson.parser.deserializer;

import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.JSONLexer;
import com.alibaba.fastjson.util.TypeUtils;
import java.lang.reflect.Type;

public class NumberDeserializer implements ObjectDeserializer {
    public static final NumberDeserializer instance = new NumberDeserializer();

    public <T> T deserialze(DefaultJSONParser defaultJSONParser, Type type, Object obj) {
        JSONLexer jSONLexer = defaultJSONParser.lexer;
        String numberString;
        if (jSONLexer.token() == 2) {
            if (type == Double.TYPE || type == Double.class) {
                numberString = jSONLexer.numberString();
                jSONLexer.nextToken(16);
                return Double.valueOf(Double.parseDouble(numberString));
            }
            long longValue = jSONLexer.longValue();
            jSONLexer.nextToken(16);
            if (type == Short.TYPE || type == Short.class) {
                return Short.valueOf((short) ((int) longValue));
            }
            if (type == Byte.TYPE || type == Byte.class) {
                return Byte.valueOf((byte) ((int) longValue));
            }
            if (longValue < -2147483648L || longValue > 2147483647L) {
                return Long.valueOf(longValue);
            }
            return Integer.valueOf((int) longValue);
        } else if (jSONLexer.token() != 3) {
            Object parse = defaultJSONParser.parse();
            if (parse == null) {
                return null;
            }
            if (type == Double.TYPE || type == Double.class) {
                return TypeUtils.castToDouble(parse);
            }
            if (type == Short.TYPE || type == Short.class) {
                return TypeUtils.castToShort(parse);
            }
            if (type == Byte.TYPE || type == Byte.class) {
                return TypeUtils.castToByte(parse);
            }
            return TypeUtils.castToBigDecimal(parse);
        } else if (type == Double.TYPE || type == Double.class) {
            numberString = jSONLexer.numberString();
            jSONLexer.nextToken(16);
            return Double.valueOf(Double.parseDouble(numberString));
        } else {
            T decimalValue = jSONLexer.decimalValue();
            jSONLexer.nextToken(16);
            if (type == Short.TYPE || type == Short.class) {
                return Short.valueOf(decimalValue.shortValue());
            }
            if (type == Byte.TYPE || type == Byte.class) {
                return Byte.valueOf(decimalValue.byteValue());
            }
            return decimalValue;
        }
    }

    public int getFastMatchToken() {
        return 2;
    }
}
