package com.alibaba.fastjson.serializer;

import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.JSONLexer;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import com.alibaba.fastjson.util.TypeUtils;
import java.io.IOException;
import java.lang.reflect.Type;
import java.text.DecimalFormat;

public class NumberCodec implements ObjectDeserializer, ObjectSerializer {
    public static final NumberCodec instance = new NumberCodec();
    private DecimalFormat decimalFormat;

    private NumberCodec() {
        this.decimalFormat = null;
    }

    public NumberCodec(DecimalFormat decimalFormat) {
        this.decimalFormat = null;
        this.decimalFormat = decimalFormat;
    }

    public NumberCodec(String str) {
        this(new DecimalFormat(str));
    }

    public void write(JSONSerializer jSONSerializer, Object obj, Object obj2, Type type) throws IOException {
        SerializeWriter serializeWriter = jSONSerializer.out;
        if (obj == null) {
            if ((serializeWriter.features & SerializerFeature.WriteNullNumberAsZero.mask) != 0) {
                serializeWriter.write(48);
            } else {
                serializeWriter.writeNull();
            }
        } else if (obj instanceof Float) {
            float floatValue = ((Float) obj).floatValue();
            if (Float.isNaN(floatValue)) {
                serializeWriter.writeNull();
            } else if (Float.isInfinite(floatValue)) {
                serializeWriter.writeNull();
            } else {
                String f = Float.toString(floatValue);
                if (f.endsWith(".0")) {
                    f = f.substring(0, f.length() - 2);
                }
                serializeWriter.write(f);
                if ((serializeWriter.features & SerializerFeature.WriteClassName.mask) != 0) {
                    serializeWriter.write(70);
                }
            }
        } else {
            double doubleValue = ((Double) obj).doubleValue();
            if (Double.isNaN(doubleValue)) {
                serializeWriter.writeNull();
            } else if (Double.isInfinite(doubleValue)) {
                serializeWriter.writeNull();
            } else {
                CharSequence d;
                if (this.decimalFormat == null) {
                    d = Double.toString(doubleValue);
                    if (d.endsWith(".0")) {
                        d = d.substring(0, d.length() - 2);
                    }
                } else {
                    d = this.decimalFormat.format(doubleValue);
                }
                serializeWriter.append(d);
                if ((serializeWriter.features & SerializerFeature.WriteClassName.mask) != 0) {
                    serializeWriter.write(68);
                }
            }
        }
    }

    public <T> T deserialze(DefaultJSONParser defaultJSONParser, Type type, Object obj) {
        JSONLexer jSONLexer = defaultJSONParser.lexer;
        int token = jSONLexer.token();
        String numberString;
        if (token == 2) {
            if (type == Double.TYPE || type == Double.class) {
                numberString = jSONLexer.numberString();
                jSONLexer.nextToken(16);
                return Double.valueOf(Double.parseDouble(numberString));
            } else if (type == Float.TYPE || type == Float.class) {
                numberString = jSONLexer.numberString();
                jSONLexer.nextToken(16);
                return Float.valueOf(Float.parseFloat(numberString));
            } else {
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
            }
        } else if (token != 3) {
            Object parse = defaultJSONParser.parse();
            if (parse == null) {
                return null;
            }
            if (type == Double.TYPE || type == Double.class) {
                return TypeUtils.castToDouble(parse);
            }
            if (type == Float.TYPE || type == Float.class) {
                return TypeUtils.castToFloat(parse);
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
        } else if (type == Float.TYPE || type == Float.class) {
            numberString = jSONLexer.numberString();
            jSONLexer.nextToken(16);
            return Float.valueOf(Float.parseFloat(numberString));
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
}
