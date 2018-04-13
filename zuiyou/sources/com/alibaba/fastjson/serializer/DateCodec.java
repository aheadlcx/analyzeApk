package com.alibaba.fastjson.serializer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.parser.JSONLexer;
import com.alibaba.fastjson.parser.JSONToken;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import java.io.IOException;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public final class DateCodec implements ObjectDeserializer, ObjectSerializer {
    public static final DateCodec instance = new DateCodec();

    private DateCodec() {
    }

    public void write(JSONSerializer jSONSerializer, Object obj, Object obj2, Type type) throws IOException {
        SerializeWriter serializeWriter = jSONSerializer.out;
        if (obj == null) {
            serializeWriter.writeNull();
        } else if ((serializeWriter.features & SerializerFeature.WriteClassName.mask) == 0 || obj.getClass() == type) {
            if (obj instanceof Calendar) {
                obj = ((Calendar) obj).getTime();
            } else {
                Date date = (Date) obj;
            }
            if ((serializeWriter.features & SerializerFeature.WriteDateUseDateFormat.mask) != 0) {
                DateFormat dateFormat = jSONSerializer.getDateFormat();
                if (dateFormat == null) {
                    dateFormat = new SimpleDateFormat(JSON.DEFFAULT_DATE_FORMAT, jSONSerializer.locale);
                    dateFormat.setTimeZone(jSONSerializer.timeZone);
                }
                serializeWriter.writeString(dateFormat.format(obj));
                return;
            }
            long time = obj.getTime();
            if ((serializeWriter.features & SerializerFeature.UseISO8601DateFormat.mask) != 0) {
                char[] toCharArray;
                if ((serializeWriter.features & SerializerFeature.UseSingleQuotes.mask) != 0) {
                    serializeWriter.write(39);
                } else {
                    serializeWriter.write(34);
                }
                Calendar instance = Calendar.getInstance(jSONSerializer.timeZone, jSONSerializer.locale);
                instance.setTimeInMillis(time);
                int i = instance.get(1);
                int i2 = instance.get(2) + 1;
                int i3 = instance.get(5);
                int i4 = instance.get(11);
                int i5 = instance.get(12);
                int i6 = instance.get(13);
                int i7 = instance.get(14);
                if (i7 != 0) {
                    toCharArray = "0000-00-00T00:00:00.000".toCharArray();
                    SerializeWriter.getChars((long) i7, 23, toCharArray);
                    SerializeWriter.getChars((long) i6, 19, toCharArray);
                    SerializeWriter.getChars((long) i5, 16, toCharArray);
                    SerializeWriter.getChars((long) i4, 13, toCharArray);
                    SerializeWriter.getChars((long) i3, 10, toCharArray);
                    SerializeWriter.getChars((long) i2, 7, toCharArray);
                    SerializeWriter.getChars((long) i, 4, toCharArray);
                } else if (i6 == 0 && i5 == 0 && i4 == 0) {
                    toCharArray = "0000-00-00".toCharArray();
                    SerializeWriter.getChars((long) i3, 10, toCharArray);
                    SerializeWriter.getChars((long) i2, 7, toCharArray);
                    SerializeWriter.getChars((long) i, 4, toCharArray);
                } else {
                    toCharArray = "0000-00-00T00:00:00".toCharArray();
                    SerializeWriter.getChars((long) i6, 19, toCharArray);
                    SerializeWriter.getChars((long) i5, 16, toCharArray);
                    SerializeWriter.getChars((long) i4, 13, toCharArray);
                    SerializeWriter.getChars((long) i3, 10, toCharArray);
                    SerializeWriter.getChars((long) i2, 7, toCharArray);
                    SerializeWriter.getChars((long) i, 4, toCharArray);
                }
                serializeWriter.write(toCharArray);
                if ((serializeWriter.features & SerializerFeature.UseSingleQuotes.mask) != 0) {
                    serializeWriter.write(39);
                    return;
                } else {
                    serializeWriter.write(34);
                    return;
                }
            }
            serializeWriter.writeLong(time);
        } else if (obj.getClass() == Date.class) {
            serializeWriter.write("new Date(");
            serializeWriter.writeLong(((Date) obj).getTime());
            serializeWriter.write(41);
        } else {
            serializeWriter.write(123);
            serializeWriter.writeFieldName(JSON.DEFAULT_TYPE_KEY, false);
            jSONSerializer.write(obj.getClass().getName());
            serializeWriter.write(44);
            serializeWriter.writeFieldName("val", false);
            serializeWriter.writeLong(((Date) obj).getTime());
            serializeWriter.write(125);
        }
    }

    public <T> T deserialze(DefaultJSONParser defaultJSONParser, Type type, Object obj) {
        return deserialze(defaultJSONParser, type, obj, null);
    }

    public <T> T deserialze(DefaultJSONParser defaultJSONParser, Type type, Object obj, String str) {
        Object valueOf;
        Type type2;
        T t;
        JSONLexer jSONLexer = defaultJSONParser.lexer;
        int token = jSONLexer.token();
        if (token == 2) {
            valueOf = Long.valueOf(jSONLexer.longValue());
            jSONLexer.nextToken(16);
            type2 = type;
        } else if (token == 4) {
            String stringVal = jSONLexer.stringVal();
            jSONLexer.nextToken(16);
            if ((jSONLexer.features & Feature.AllowISO8601DateFormat.mask) != 0) {
                JSONLexer jSONLexer2 = new JSONLexer(stringVal);
                if (jSONLexer2.scanISO8601DateIfMatch(true)) {
                    t = jSONLexer2.calendar;
                    if (type == Calendar.class) {
                        jSONLexer2.close();
                        return t;
                    }
                    stringVal = t.getTime();
                }
                jSONLexer2.close();
            }
            String str2 = stringVal;
            type2 = type;
        } else if (token == 8) {
            jSONLexer.nextToken();
            valueOf = null;
            type2 = type;
        } else if (token == 12) {
            jSONLexer.nextToken();
            if (jSONLexer.token() == 4) {
                if (JSON.DEFAULT_TYPE_KEY.equals(jSONLexer.stringVal())) {
                    jSONLexer.nextToken();
                    defaultJSONParser.accept(17);
                    Class checkAutoType = defaultJSONParser.config.checkAutoType(jSONLexer.stringVal(), null, jSONLexer.features);
                    if (checkAutoType != null) {
                        type = checkAutoType;
                    }
                    defaultJSONParser.accept(4);
                    defaultJSONParser.accept(16);
                }
                jSONLexer.nextTokenWithChar(':');
                token = jSONLexer.token();
                if (token == 2) {
                    long longValue = jSONLexer.longValue();
                    jSONLexer.nextToken();
                    valueOf = Long.valueOf(longValue);
                    defaultJSONParser.accept(13);
                    type2 = type;
                } else {
                    throw new JSONException("syntax error : " + JSONToken.name(token));
                }
            }
            throw new JSONException("syntax error");
        } else if (defaultJSONParser.resolveStatus == 2) {
            defaultJSONParser.resolveStatus = 0;
            defaultJSONParser.accept(16);
            if (jSONLexer.token() != 4) {
                throw new JSONException("syntax error");
            } else if ("val".equals(jSONLexer.stringVal())) {
                jSONLexer.nextToken();
                defaultJSONParser.accept(17);
                valueOf = defaultJSONParser.parse();
                defaultJSONParser.accept(13);
                type2 = type;
            } else {
                throw new JSONException("syntax error");
            }
        } else {
            valueOf = defaultJSONParser.parse();
            type2 = type;
        }
        t = cast(defaultJSONParser, type2, obj, valueOf, str);
        if (type2 != Calendar.class || (t instanceof Calendar)) {
            return t;
        }
        Date date = (Date) t;
        if (date == null) {
            return null;
        }
        T instance = Calendar.getInstance(jSONLexer.timeZone, jSONLexer.locale);
        instance.setTime(date);
        return instance;
    }

    protected <T> T cast(DefaultJSONParser defaultJSONParser, Type type, Object obj, Object obj2, String str) {
        if (obj2 == null) {
            return null;
        }
        if (obj2 instanceof Date) {
            return obj2;
        }
        if (obj2 instanceof Number) {
            return new Date(((Number) obj2).longValue());
        }
        if (obj2 instanceof String) {
            obj2 = (String) obj2;
            if (obj2.length() == 0) {
                return null;
            }
            JSONLexer jSONLexer = new JSONLexer(obj2);
            try {
                if (jSONLexer.scanISO8601DateIfMatch(false)) {
                    obj2 = jSONLexer.calendar;
                    if (type == Calendar.class) {
                        return obj2;
                    }
                    obj2 = obj2.getTime();
                    jSONLexer.close();
                    return obj2;
                }
                jSONLexer.close();
                if ("0000-00-00".equals(obj2) || "0000-00-00T00:00:00".equalsIgnoreCase(obj2) || "0001-01-01T00:00:00+08:00".equalsIgnoreCase(obj2)) {
                    return null;
                }
                DateFormat simpleDateFormat;
                if (str != null) {
                    simpleDateFormat = new SimpleDateFormat(str);
                } else {
                    simpleDateFormat = defaultJSONParser.getDateFormat();
                }
                try {
                    return simpleDateFormat.parse(obj2);
                } catch (ParseException e) {
                    return new Date(Long.parseLong(obj2));
                }
            } finally {
                jSONLexer.close();
            }
        } else {
            throw new JSONException("parse error");
        }
    }
}
