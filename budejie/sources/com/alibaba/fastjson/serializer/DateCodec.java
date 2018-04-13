package com.alibaba.fastjson.serializer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.JSONScanner;
import com.alibaba.fastjson.parser.deserializer.AbstractDateDeserializer;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import com.alibaba.fastjson.util.IOUtils;
import com.alibaba.fastjson.util.TypeUtils;
import java.io.IOException;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateCodec extends AbstractDateDeserializer implements ObjectDeserializer, ObjectSerializer {
    public static final DateCodec instance = new DateCodec();

    public void write(JSONSerializer jSONSerializer, Object obj, Object obj2, Type type, int i) throws IOException {
        SerializeWriter serializeWriter = jSONSerializer.out;
        if (obj == null) {
            serializeWriter.writeNull();
            return;
        }
        Date date;
        if (obj instanceof Date) {
            date = (Date) obj;
        } else {
            date = TypeUtils.castToDate(obj);
        }
        if (serializeWriter.isEnabled(SerializerFeature.WriteDateUseDateFormat)) {
            DateFormat dateFormat = jSONSerializer.getDateFormat();
            if (dateFormat == null) {
                dateFormat = new SimpleDateFormat(JSON.DEFFAULT_DATE_FORMAT, jSONSerializer.locale);
                dateFormat.setTimeZone(jSONSerializer.timeZone);
            }
            serializeWriter.writeString(dateFormat.format(date));
        } else if (!serializeWriter.isEnabled(SerializerFeature.WriteClassName) || obj.getClass() == type) {
            long time = date.getTime();
            if (serializeWriter.isEnabled(SerializerFeature.UseISO8601DateFormat)) {
                char[] toCharArray;
                int i2 = serializeWriter.isEnabled(SerializerFeature.UseSingleQuotes) ? 39 : 34;
                serializeWriter.write(i2);
                Calendar instance = Calendar.getInstance(jSONSerializer.timeZone, jSONSerializer.locale);
                instance.setTimeInMillis(time);
                int i3 = instance.get(1);
                int i4 = instance.get(2) + 1;
                int i5 = instance.get(5);
                int i6 = instance.get(11);
                int i7 = instance.get(12);
                int i8 = instance.get(13);
                int i9 = instance.get(14);
                if (i9 != 0) {
                    toCharArray = "0000-00-00T00:00:00.000".toCharArray();
                    IOUtils.getChars(i9, 23, toCharArray);
                    IOUtils.getChars(i8, 19, toCharArray);
                    IOUtils.getChars(i7, 16, toCharArray);
                    IOUtils.getChars(i6, 13, toCharArray);
                    IOUtils.getChars(i5, 10, toCharArray);
                    IOUtils.getChars(i4, 7, toCharArray);
                    IOUtils.getChars(i3, 4, toCharArray);
                } else if (i8 == 0 && i7 == 0 && i6 == 0) {
                    toCharArray = "0000-00-00".toCharArray();
                    IOUtils.getChars(i5, 10, toCharArray);
                    IOUtils.getChars(i4, 7, toCharArray);
                    IOUtils.getChars(i3, 4, toCharArray);
                } else {
                    toCharArray = "0000-00-00T00:00:00".toCharArray();
                    IOUtils.getChars(i8, 19, toCharArray);
                    IOUtils.getChars(i7, 16, toCharArray);
                    IOUtils.getChars(i6, 13, toCharArray);
                    IOUtils.getChars(i5, 10, toCharArray);
                    IOUtils.getChars(i4, 7, toCharArray);
                    IOUtils.getChars(i3, 4, toCharArray);
                }
                serializeWriter.write(toCharArray);
                int rawOffset = instance.getTimeZone().getRawOffset() / 3600000;
                if (rawOffset == 0) {
                    serializeWriter.write(90);
                } else {
                    if (rawOffset > 0) {
                        serializeWriter.append('+').append(String.format("%02d", new Object[]{Integer.valueOf(rawOffset)}));
                    } else {
                        serializeWriter.append('-').append(String.format("%02d", new Object[]{Integer.valueOf(-rawOffset)}));
                    }
                    serializeWriter.append((CharSequence) ":00");
                }
                serializeWriter.write(i2);
                return;
            }
            serializeWriter.writeLong(time);
        } else if (obj.getClass() == Date.class) {
            serializeWriter.write("new Date(");
            serializeWriter.writeLongAndChar(((Date) obj).getTime(), ')');
        } else {
            serializeWriter.write(123);
            serializeWriter.writeFieldName(JSON.DEFAULT_TYPE_KEY);
            jSONSerializer.write(obj.getClass().getName());
            serializeWriter.writeFieldValue(',', "val", ((Date) obj).getTime());
            serializeWriter.write(125);
        }
    }

    protected <T> T cast(DefaultJSONParser defaultJSONParser, Type type, Object obj, Object obj2) {
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
            JSONScanner jSONScanner = new JSONScanner(obj2);
            try {
                if (jSONScanner.scanISO8601DateIfMatch(false)) {
                    obj2 = jSONScanner.getCalendar();
                    if (type == Calendar.class) {
                        return obj2;
                    }
                    obj2 = obj2.getTime();
                    jSONScanner.close();
                    return obj2;
                }
                jSONScanner.close();
                if (obj2.length() == defaultJSONParser.getDateFomartPattern().length()) {
                    try {
                        return defaultJSONParser.getDateFormat().parse(obj2);
                    } catch (ParseException e) {
                    }
                }
                return new Date(Long.parseLong(obj2));
            } finally {
                jSONScanner.close();
            }
        } else {
            throw new JSONException("parse error");
        }
    }

    public int getFastMatchToken() {
        return 2;
    }
}
