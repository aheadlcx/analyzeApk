package com.alibaba.fastjson.serializer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONAware;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONStreamAware;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.JSONLexer;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import com.alibaba.fastjson.util.TypeUtils;
import com.tencent.tinker.android.dex.DexFormat;
import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.URL;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Currency;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Map.Entry;
import java.util.TimeZone;
import java.util.UUID;
import java.util.regex.Pattern;

public final class MiscCodec implements ObjectDeserializer, ObjectSerializer {
    public static final MiscCodec instance = new MiscCodec();

    private MiscCodec() {
    }

    public void write(JSONSerializer jSONSerializer, Object obj, Object obj2, Type type) throws IOException {
        int i = 0;
        SerializeWriter serializeWriter = jSONSerializer.out;
        if (obj == null) {
            if (type == Character.TYPE || type == Character.class) {
                jSONSerializer.write("");
                return;
            }
            if ((serializeWriter.features & SerializerFeature.WriteNullListAsEmpty.mask) != 0) {
                if (Enumeration.class.isAssignableFrom(TypeUtils.getClass(type))) {
                    serializeWriter.write("[]");
                    return;
                }
            }
            serializeWriter.writeNull();
        } else if (obj instanceof Pattern) {
            jSONSerializer.write(((Pattern) obj).pattern());
        } else if (obj instanceof TimeZone) {
            jSONSerializer.write(((TimeZone) obj).getID());
        } else if (obj instanceof Currency) {
            jSONSerializer.write(((Currency) obj).getCurrencyCode());
        } else if (obj instanceof Class) {
            jSONSerializer.write(((Class) obj).getName());
        } else if (obj instanceof Character) {
            Character ch = (Character) obj;
            if (ch.charValue() == '\u0000') {
                jSONSerializer.write(DexFormat.MAGIC_SUFFIX);
            } else {
                jSONSerializer.write(ch.toString());
            }
        } else if (obj instanceof SimpleDateFormat) {
            String toPattern = ((SimpleDateFormat) obj).toPattern();
            if ((serializeWriter.features & SerializerFeature.WriteClassName.mask) == 0 || obj.getClass() == type) {
                serializeWriter.writeString(toPattern);
                return;
            }
            serializeWriter.write(123);
            serializeWriter.writeFieldName(JSON.DEFAULT_TYPE_KEY, false);
            jSONSerializer.write(obj.getClass().getName());
            serializeWriter.write(44);
            serializeWriter.writeFieldName("val", false);
            serializeWriter.writeString(toPattern);
            serializeWriter.write(125);
        } else if (obj instanceof JSONStreamAware) {
            ((JSONStreamAware) obj).writeJSONString(jSONSerializer.out);
        } else if (obj instanceof JSONAware) {
            serializeWriter.write(((JSONAware) obj).toJSONString());
        } else if (obj instanceof JSONSerializable) {
            ((JSONSerializable) obj).write(jSONSerializer, obj2, type);
        } else if (obj instanceof Enumeration) {
            Type type2;
            if ((serializeWriter.features & SerializerFeature.WriteClassName.mask) == 0 || !(type instanceof ParameterizedType)) {
                type2 = null;
            } else {
                type2 = ((ParameterizedType) type).getActualTypeArguments()[0];
            }
            Enumeration enumeration = (Enumeration) obj;
            SerialContext serialContext = jSONSerializer.context;
            jSONSerializer.setContext(serialContext, obj, obj2, 0);
            try {
                serializeWriter.write(91);
                while (enumeration.hasMoreElements()) {
                    Object nextElement = enumeration.nextElement();
                    int i2 = i + 1;
                    if (i != 0) {
                        serializeWriter.write(44);
                    }
                    if (nextElement == null) {
                        serializeWriter.writeNull();
                        i = i2;
                    } else {
                        jSONSerializer.config.get(nextElement.getClass()).write(jSONSerializer, nextElement, Integer.valueOf(i2 - 1), type2);
                        i = i2;
                    }
                }
                serializeWriter.write(93);
            } finally {
                jSONSerializer.context = serialContext;
            }
        } else {
            jSONSerializer.write(obj.toString());
        }
    }

    public <T> T deserialze(DefaultJSONParser defaultJSONParser, Type type, Object obj) {
        if (type == StackTraceElement.class) {
            return parseStackTraceElement(defaultJSONParser);
        }
        JSONLexer jSONLexer = defaultJSONParser.lexer;
        if (defaultJSONParser.resolveStatus == 2) {
            defaultJSONParser.resolveStatus = 0;
            defaultJSONParser.accept(16);
            if (jSONLexer.token() != 4) {
                throw new JSONException("syntax error");
            } else if ("val".equals(jSONLexer.stringVal())) {
                jSONLexer.nextToken();
                defaultJSONParser.accept(17);
                Object parse = defaultJSONParser.parse();
                defaultJSONParser.accept(13);
            } else {
                throw new JSONException("syntax error");
            }
        }
        parse = defaultJSONParser.parse();
        if (parse == null) {
            return null;
        }
        if (parse instanceof String) {
            String str = (String) parse;
            if (str.length() == 0) {
                return null;
            }
            if (type == UUID.class) {
                return UUID.fromString(str);
            }
            if (type == Class.class) {
                return TypeUtils.loadClass(str, defaultJSONParser.config.defaultClassLoader);
            }
            if (type == Locale.class) {
                String[] split = str.split("_");
                if (split.length == 1) {
                    return new Locale(split[0]);
                }
                if (split.length == 2) {
                    return new Locale(split[0], split[1]);
                }
                return new Locale(split[0], split[1], split[2]);
            } else if (type == URI.class) {
                return URI.create(str);
            } else {
                if (type == URL.class) {
                    try {
                        return new URL(str);
                    } catch (Throwable e) {
                        throw new JSONException("create url error", e);
                    }
                } else if (type == Pattern.class) {
                    return Pattern.compile(str);
                } else {
                    if (type == Charset.class) {
                        return Charset.forName(str);
                    }
                    if (type == Currency.class) {
                        return Currency.getInstance(str);
                    }
                    if (type == SimpleDateFormat.class) {
                        T simpleDateFormat = new SimpleDateFormat(str, defaultJSONParser.lexer.locale);
                        simpleDateFormat.setTimeZone(defaultJSONParser.lexer.timeZone);
                        return simpleDateFormat;
                    } else if (type == Character.TYPE || type == Character.class) {
                        return TypeUtils.castToChar(str);
                    } else {
                        if (type instanceof Class) {
                            if ("android.net.Uri".equals(((Class) type).getName())) {
                                try {
                                    return Class.forName("android.net.Uri").getMethod("parse", new Class[]{String.class}).invoke(null, new Object[]{str});
                                } catch (Throwable e2) {
                                    throw new JSONException("parse android.net.Uri error.", e2);
                                }
                            }
                        }
                        return TimeZone.getTimeZone(str);
                    }
                }
            }
        }
        if (parse instanceof JSONObject) {
            JSONObject jSONObject = (JSONObject) parse;
            if (type == Currency.class) {
                String string = jSONObject.getString("currency");
                if (string != null) {
                    return Currency.getInstance(string);
                }
                string = jSONObject.getString("currencyCode");
                if (string != null) {
                    return Currency.getInstance(string);
                }
            }
            if (type == Entry.class) {
                return jSONObject.entrySet().iterator().next();
            }
        }
        throw new JSONException("except string value");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected <T> T parseStackTraceElement(com.alibaba.fastjson.parser.DefaultJSONParser r14) {
        /*
        r13 = this;
        r1 = 0;
        r12 = 4;
        r3 = 0;
        r11 = 16;
        r10 = 8;
        r6 = r14.lexer;
        r0 = r6.token();
        if (r0 != r10) goto L_0x0013;
    L_0x000f:
        r6.nextToken();
    L_0x0012:
        return r3;
    L_0x0013:
        r0 = r6.token();
        r2 = 12;
        if (r0 == r2) goto L_0x0043;
    L_0x001b:
        r0 = r6.token();
        if (r0 == r11) goto L_0x0043;
    L_0x0021:
        r0 = new com.alibaba.fastjson.JSONException;
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "syntax error: ";
        r1 = r1.append(r2);
        r2 = r6.token();
        r2 = com.alibaba.fastjson.parser.JSONToken.name(r2);
        r1 = r1.append(r2);
        r1 = r1.toString();
        r0.<init>(r1);
        throw r0;
    L_0x0043:
        r0 = r1;
        r2 = r3;
        r4 = r3;
        r5 = r3;
    L_0x0047:
        r7 = r14.symbolTable;
        r7 = r6.scanSymbol(r7);
        if (r7 != 0) goto L_0x0066;
    L_0x004f:
        r8 = r6.token();
        r9 = 13;
        if (r8 != r9) goto L_0x0060;
    L_0x0057:
        r6.nextToken(r11);
    L_0x005a:
        r3 = new java.lang.StackTraceElement;
        r3.<init>(r5, r4, r2, r0);
        goto L_0x0012;
    L_0x0060:
        r8 = r6.token();
        if (r8 == r11) goto L_0x0047;
    L_0x0066:
        r8 = 58;
        r6.nextTokenWithChar(r8);
        r8 = "className";
        r8 = r8.equals(r7);
        if (r8 == 0) goto L_0x009b;
    L_0x0074:
        r5 = r6.token();
        if (r5 != r10) goto L_0x0087;
    L_0x007a:
        r5 = r3;
    L_0x007b:
        r7 = r6.token();
        r8 = 13;
        if (r7 != r8) goto L_0x0047;
    L_0x0083:
        r6.nextToken(r11);
        goto L_0x005a;
    L_0x0087:
        r5 = r6.token();
        if (r5 != r12) goto L_0x0092;
    L_0x008d:
        r5 = r6.stringVal();
        goto L_0x007b;
    L_0x0092:
        r0 = new com.alibaba.fastjson.JSONException;
        r1 = "syntax error";
        r0.<init>(r1);
        throw r0;
    L_0x009b:
        r8 = "methodName";
        r8 = r8.equals(r7);
        if (r8 == 0) goto L_0x00c0;
    L_0x00a4:
        r4 = r6.token();
        if (r4 != r10) goto L_0x00ac;
    L_0x00aa:
        r4 = r3;
        goto L_0x007b;
    L_0x00ac:
        r4 = r6.token();
        if (r4 != r12) goto L_0x00b7;
    L_0x00b2:
        r4 = r6.stringVal();
        goto L_0x007b;
    L_0x00b7:
        r0 = new com.alibaba.fastjson.JSONException;
        r1 = "syntax error";
        r0.<init>(r1);
        throw r0;
    L_0x00c0:
        r8 = "fileName";
        r8 = r8.equals(r7);
        if (r8 == 0) goto L_0x00e5;
    L_0x00c9:
        r2 = r6.token();
        if (r2 != r10) goto L_0x00d1;
    L_0x00cf:
        r2 = r3;
        goto L_0x007b;
    L_0x00d1:
        r2 = r6.token();
        if (r2 != r12) goto L_0x00dc;
    L_0x00d7:
        r2 = r6.stringVal();
        goto L_0x007b;
    L_0x00dc:
        r0 = new com.alibaba.fastjson.JSONException;
        r1 = "syntax error";
        r0.<init>(r1);
        throw r0;
    L_0x00e5:
        r8 = "lineNumber";
        r8 = r8.equals(r7);
        if (r8 == 0) goto L_0x010c;
    L_0x00ee:
        r0 = r6.token();
        if (r0 != r10) goto L_0x00f6;
    L_0x00f4:
        r0 = r1;
        goto L_0x007b;
    L_0x00f6:
        r0 = r6.token();
        r7 = 2;
        if (r0 != r7) goto L_0x0103;
    L_0x00fd:
        r0 = r6.intValue();
        goto L_0x007b;
    L_0x0103:
        r0 = new com.alibaba.fastjson.JSONException;
        r1 = "syntax error";
        r0.<init>(r1);
        throw r0;
    L_0x010c:
        r8 = "nativeMethod";
        r8 = r8.equals(r7);
        if (r8 == 0) goto L_0x0141;
    L_0x0115:
        r7 = r6.token();
        if (r7 != r10) goto L_0x0120;
    L_0x011b:
        r6.nextToken(r11);
        goto L_0x007b;
    L_0x0120:
        r7 = r6.token();
        r8 = 6;
        if (r7 != r8) goto L_0x012c;
    L_0x0127:
        r6.nextToken(r11);
        goto L_0x007b;
    L_0x012c:
        r7 = r6.token();
        r8 = 7;
        if (r7 != r8) goto L_0x0138;
    L_0x0133:
        r6.nextToken(r11);
        goto L_0x007b;
    L_0x0138:
        r0 = new com.alibaba.fastjson.JSONException;
        r1 = "syntax error";
        r0.<init>(r1);
        throw r0;
    L_0x0141:
        r8 = "@type";
        if (r7 != r8) goto L_0x0182;
    L_0x0146:
        r7 = r6.token();
        if (r7 != r12) goto L_0x0173;
    L_0x014c:
        r7 = r6.stringVal();
        r8 = "java.lang.StackTraceElement";
        r8 = r7.equals(r8);
        if (r8 != 0) goto L_0x007b;
    L_0x0159:
        r0 = new com.alibaba.fastjson.JSONException;
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "syntax error : ";
        r1 = r1.append(r2);
        r1 = r1.append(r7);
        r1 = r1.toString();
        r0.<init>(r1);
        throw r0;
    L_0x0173:
        r7 = r6.token();
        if (r7 == r10) goto L_0x007b;
    L_0x0179:
        r0 = new com.alibaba.fastjson.JSONException;
        r1 = "syntax error";
        r0.<init>(r1);
        throw r0;
    L_0x0182:
        r0 = new com.alibaba.fastjson.JSONException;
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "syntax error : ";
        r1 = r1.append(r2);
        r1 = r1.append(r7);
        r1 = r1.toString();
        r0.<init>(r1);
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.serializer.MiscCodec.parseStackTraceElement(com.alibaba.fastjson.parser.DefaultJSONParser):T");
    }
}
