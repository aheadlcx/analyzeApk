package com.alibaba.fastjson.parser;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.IdentityHashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

class MapDeserializer implements ObjectDeserializer {
    public static MapDeserializer instance = new MapDeserializer();

    MapDeserializer() {
    }

    public <T> T deserialze(DefaultJSONParser defaultJSONParser, Type type, Object obj) {
        if (type == JSONObject.class && defaultJSONParser.fieldTypeResolver == null) {
            return defaultJSONParser.parseObject();
        }
        JSONLexer jSONLexer = defaultJSONParser.lexer;
        if (jSONLexer.token == 8) {
            jSONLexer.nextToken(16);
            return null;
        }
        Map createMap = createMap(type);
        ParseContext parseContext = defaultJSONParser.contex;
        try {
            defaultJSONParser.setContext(parseContext, createMap, obj);
            T parseMap;
            if (type instanceof ParameterizedType) {
                ParameterizedType parameterizedType = (ParameterizedType) type;
                Class cls = parameterizedType.getActualTypeArguments()[0];
                Type type2 = parameterizedType.getActualTypeArguments()[1];
                if (String.class == cls) {
                    parseMap = parseMap(defaultJSONParser, createMap, type2, obj);
                    return parseMap;
                }
                parseMap = parseMap(defaultJSONParser, createMap, cls, type2, obj);
                defaultJSONParser.setContext(parseContext);
                return parseMap;
            }
            parseMap = defaultJSONParser.parseObject(createMap, obj);
            defaultJSONParser.setContext(parseContext);
            return parseMap;
        } finally {
            defaultJSONParser.setContext(parseContext);
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.util.Map parseMap(com.alibaba.fastjson.parser.DefaultJSONParser r9, java.util.Map<java.lang.String, java.lang.Object> r10, java.lang.reflect.Type r11, java.lang.Object r12) {
        /*
        r8 = 13;
        r7 = 58;
        r6 = 34;
        r2 = r9.lexer;
        r0 = r2.token;
        r1 = 12;
        if (r0 == r1) goto L_0x002a;
    L_0x000e:
        r0 = new com.alibaba.fastjson.JSONException;
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r3 = "syntax error, expect {, actual ";
        r1 = r1.append(r3);
        r2 = r2.token;
        r1 = r1.append(r2);
        r1 = r1.toString();
        r0.<init>(r1);
        throw r0;
    L_0x002a:
        r3 = r9.contex;
    L_0x002c:
        r2.skipWhitespace();	 Catch:{ all -> 0x006d }
        r0 = r2.ch;	 Catch:{ all -> 0x006d }
    L_0x0031:
        r1 = 44;
        if (r0 != r1) goto L_0x003e;
    L_0x0035:
        r2.next();	 Catch:{ all -> 0x006d }
        r2.skipWhitespace();	 Catch:{ all -> 0x006d }
        r0 = r2.ch;	 Catch:{ all -> 0x006d }
        goto L_0x0031;
    L_0x003e:
        if (r0 != r6) goto L_0x0072;
    L_0x0040:
        r0 = r9.symbolTable;	 Catch:{ all -> 0x006d }
        r1 = 34;
        r0 = r2.scanSymbol(r0, r1);	 Catch:{ all -> 0x006d }
        r2.skipWhitespace();	 Catch:{ all -> 0x006d }
        r1 = r2.ch;	 Catch:{ all -> 0x006d }
        if (r1 == r7) goto L_0x00ea;
    L_0x004f:
        r0 = new com.alibaba.fastjson.JSONException;	 Catch:{ all -> 0x006d }
        r1 = new java.lang.StringBuilder;	 Catch:{ all -> 0x006d }
        r1.<init>();	 Catch:{ all -> 0x006d }
        r4 = "syntax error, ";
        r1 = r1.append(r4);	 Catch:{ all -> 0x006d }
        r2 = r2.info();	 Catch:{ all -> 0x006d }
        r1 = r1.append(r2);	 Catch:{ all -> 0x006d }
        r1 = r1.toString();	 Catch:{ all -> 0x006d }
        r0.<init>(r1);	 Catch:{ all -> 0x006d }
        throw r0;	 Catch:{ all -> 0x006d }
    L_0x006d:
        r0 = move-exception;
        r9.setContext(r3);
        throw r0;
    L_0x0072:
        r1 = 125; // 0x7d float:1.75E-43 double:6.2E-322;
        if (r0 != r1) goto L_0x0085;
    L_0x0076:
        r2.next();	 Catch:{ all -> 0x006d }
        r0 = 0;
        r2.sp = r0;	 Catch:{ all -> 0x006d }
        r0 = 16;
        r2.nextToken(r0);	 Catch:{ all -> 0x006d }
        r9.setContext(r3);
    L_0x0084:
        return r10;
    L_0x0085:
        r1 = 39;
        if (r0 != r1) goto L_0x00b6;
    L_0x0089:
        r0 = r9.symbolTable;	 Catch:{ all -> 0x006d }
        r1 = 39;
        r0 = r2.scanSymbol(r0, r1);	 Catch:{ all -> 0x006d }
        r2.skipWhitespace();	 Catch:{ all -> 0x006d }
        r1 = r2.ch;	 Catch:{ all -> 0x006d }
        if (r1 == r7) goto L_0x00ea;
    L_0x0098:
        r0 = new com.alibaba.fastjson.JSONException;	 Catch:{ all -> 0x006d }
        r1 = new java.lang.StringBuilder;	 Catch:{ all -> 0x006d }
        r1.<init>();	 Catch:{ all -> 0x006d }
        r4 = "syntax error, ";
        r1 = r1.append(r4);	 Catch:{ all -> 0x006d }
        r2 = r2.info();	 Catch:{ all -> 0x006d }
        r1 = r1.append(r2);	 Catch:{ all -> 0x006d }
        r1 = r1.toString();	 Catch:{ all -> 0x006d }
        r0.<init>(r1);	 Catch:{ all -> 0x006d }
        throw r0;	 Catch:{ all -> 0x006d }
    L_0x00b6:
        r0 = r9.symbolTable;	 Catch:{ all -> 0x006d }
        r0 = r2.scanSymbolUnQuoted(r0);	 Catch:{ all -> 0x006d }
        r2.skipWhitespace();	 Catch:{ all -> 0x006d }
        r1 = r2.ch;	 Catch:{ all -> 0x006d }
        if (r1 == r7) goto L_0x00ea;
    L_0x00c3:
        r0 = new com.alibaba.fastjson.JSONException;	 Catch:{ all -> 0x006d }
        r4 = new java.lang.StringBuilder;	 Catch:{ all -> 0x006d }
        r4.<init>();	 Catch:{ all -> 0x006d }
        r5 = "expect ':' at ";
        r4 = r4.append(r5);	 Catch:{ all -> 0x006d }
        r2 = r2.pos;	 Catch:{ all -> 0x006d }
        r2 = r4.append(r2);	 Catch:{ all -> 0x006d }
        r4 = ", actual ";
        r2 = r2.append(r4);	 Catch:{ all -> 0x006d }
        r1 = r2.append(r1);	 Catch:{ all -> 0x006d }
        r1 = r1.toString();	 Catch:{ all -> 0x006d }
        r0.<init>(r1);	 Catch:{ all -> 0x006d }
        throw r0;	 Catch:{ all -> 0x006d }
    L_0x00ea:
        r1 = r0;
        r2.next();	 Catch:{ all -> 0x006d }
        r2.skipWhitespace();	 Catch:{ all -> 0x006d }
        r0 = r2.ch;	 Catch:{ all -> 0x006d }
        r0 = 0;
        r2.sp = r0;	 Catch:{ all -> 0x006d }
        r0 = "@type";
        if (r1 != r0) goto L_0x0150;
    L_0x00fb:
        r0 = com.alibaba.fastjson.parser.Feature.DisableSpecialKeyDetect;	 Catch:{ all -> 0x006d }
        r0 = r2.isEnabled(r0);	 Catch:{ all -> 0x006d }
        if (r0 != 0) goto L_0x0150;
    L_0x0103:
        r0 = r9.symbolTable;	 Catch:{ all -> 0x006d }
        r1 = 34;
        r0 = r2.scanSymbol(r0, r1);	 Catch:{ all -> 0x006d }
        r1 = r9.config;	 Catch:{ all -> 0x006d }
        r4 = 0;
        r5 = r2.features;	 Catch:{ all -> 0x006d }
        r0 = r1.checkAutoType(r0, r4, r5);	 Catch:{ all -> 0x006d }
        r1 = r10.getClass();	 Catch:{ all -> 0x006d }
        if (r0 != r1) goto L_0x012d;
    L_0x011a:
        r0 = 16;
        r2.nextToken(r0);	 Catch:{ all -> 0x006d }
        r0 = r2.token;	 Catch:{ all -> 0x006d }
        if (r0 != r8) goto L_0x002c;
    L_0x0123:
        r0 = 16;
        r2.nextToken(r0);	 Catch:{ all -> 0x006d }
        r9.setContext(r3);
        goto L_0x0084;
    L_0x012d:
        r1 = r9.config;	 Catch:{ all -> 0x006d }
        r1 = r1.getDeserializer(r0);	 Catch:{ all -> 0x006d }
        r4 = 16;
        r2.nextToken(r4);	 Catch:{ all -> 0x006d }
        r2 = 2;
        r9.resolveStatus = r2;	 Catch:{ all -> 0x006d }
        if (r3 == 0) goto L_0x0144;
    L_0x013d:
        r2 = r12 instanceof java.lang.Integer;	 Catch:{ all -> 0x006d }
        if (r2 != 0) goto L_0x0144;
    L_0x0141:
        r9.popContext();	 Catch:{ all -> 0x006d }
    L_0x0144:
        r0 = r1.deserialze(r9, r0, r12);	 Catch:{ all -> 0x006d }
        r0 = (java.util.Map) r0;	 Catch:{ all -> 0x006d }
        r9.setContext(r3);
        r10 = r0;
        goto L_0x0084;
    L_0x0150:
        r2.nextToken();	 Catch:{ all -> 0x006d }
        r9.setContext(r3);	 Catch:{ all -> 0x006d }
        r0 = r2.token;	 Catch:{ all -> 0x006d }
        r4 = 8;
        if (r0 != r4) goto L_0x017d;
    L_0x015c:
        r0 = 0;
        r2.nextToken();	 Catch:{ all -> 0x006d }
    L_0x0160:
        r10.put(r1, r0);	 Catch:{ all -> 0x006d }
        r4 = r9.resolveStatus;	 Catch:{ all -> 0x006d }
        r5 = 1;
        if (r4 != r5) goto L_0x016b;
    L_0x0168:
        r9.checkMapResolve(r10, r1);	 Catch:{ all -> 0x006d }
    L_0x016b:
        r9.setContext(r3, r0, r1);	 Catch:{ all -> 0x006d }
        r0 = r2.token;	 Catch:{ all -> 0x006d }
        r1 = 20;
        if (r0 == r1) goto L_0x0178;
    L_0x0174:
        r1 = 15;
        if (r0 != r1) goto L_0x0182;
    L_0x0178:
        r9.setContext(r3);
        goto L_0x0084;
    L_0x017d:
        r0 = r9.parseObject(r11, r1);	 Catch:{ all -> 0x006d }
        goto L_0x0160;
    L_0x0182:
        if (r0 != r8) goto L_0x002c;
    L_0x0184:
        r2.nextToken();	 Catch:{ all -> 0x006d }
        r9.setContext(r3);
        goto L_0x0084;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.parser.MapDeserializer.parseMap(com.alibaba.fastjson.parser.DefaultJSONParser, java.util.Map, java.lang.reflect.Type, java.lang.Object):java.util.Map");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.Object parseMap(com.alibaba.fastjson.parser.DefaultJSONParser r8, java.util.Map<java.lang.Object, java.lang.Object> r9, java.lang.reflect.Type r10, java.lang.reflect.Type r11, java.lang.Object r12) {
        /*
        r2 = r8.lexer;
        r0 = r2.token;
        r1 = 12;
        if (r0 == r1) goto L_0x002a;
    L_0x0008:
        r1 = 16;
        if (r0 == r1) goto L_0x002a;
    L_0x000c:
        r1 = new com.alibaba.fastjson.JSONException;
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r3 = "syntax error, expect {, actual ";
        r2 = r2.append(r3);
        r0 = com.alibaba.fastjson.parser.JSONToken.name(r0);
        r0 = r2.append(r0);
        r0 = r0.toString();
        r1.<init>(r0);
        throw r1;
    L_0x002a:
        r0 = r8.config;
        r0 = r0.getDeserializer(r10);
        r1 = r8.config;
        r3 = r1.getDeserializer(r11);
        r2.nextToken();
        r1 = r8.contex;
    L_0x003b:
        r4 = r2.token;	 Catch:{ all -> 0x0099 }
        r5 = 13;
        if (r4 != r5) goto L_0x004a;
    L_0x0041:
        r0 = 16;
        r2.nextToken(r0);	 Catch:{ all -> 0x0099 }
        r8.setContext(r1);
    L_0x0049:
        return r9;
    L_0x004a:
        r5 = 4;
        if (r4 != r5) goto L_0x00e7;
    L_0x004d:
        r5 = r2.sp;	 Catch:{ all -> 0x0099 }
        r6 = 4;
        if (r5 != r6) goto L_0x00e7;
    L_0x0052:
        r5 = r2.text;	 Catch:{ all -> 0x0099 }
        r6 = "$ref";
        r7 = r2.np;	 Catch:{ all -> 0x0099 }
        r7 = r7 + 1;
        r5 = r5.startsWith(r6, r7);	 Catch:{ all -> 0x0099 }
        if (r5 == 0) goto L_0x00e7;
    L_0x0061:
        r5 = com.alibaba.fastjson.parser.Feature.DisableSpecialKeyDetect;	 Catch:{ all -> 0x0099 }
        r5 = r2.isEnabled(r5);	 Catch:{ all -> 0x0099 }
        if (r5 != 0) goto L_0x00e7;
    L_0x0069:
        r0 = 0;
        r3 = 58;
        r2.nextTokenWithChar(r3);	 Catch:{ all -> 0x0099 }
        r3 = r2.token;	 Catch:{ all -> 0x0099 }
        r5 = 4;
        if (r3 != r5) goto L_0x00be;
    L_0x0074:
        r3 = r2.stringVal();	 Catch:{ all -> 0x0099 }
        r4 = "..";
        r4 = r4.equals(r3);	 Catch:{ all -> 0x0099 }
        if (r4 == 0) goto L_0x009e;
    L_0x0081:
        r0 = r1.parent;	 Catch:{ all -> 0x0099 }
        r0 = r0.object;	 Catch:{ all -> 0x0099 }
    L_0x0085:
        r3 = 13;
        r2.nextToken(r3);	 Catch:{ all -> 0x0099 }
        r3 = r2.token;	 Catch:{ all -> 0x0099 }
        r4 = 13;
        if (r3 == r4) goto L_0x00dc;
    L_0x0090:
        r0 = new com.alibaba.fastjson.JSONException;	 Catch:{ all -> 0x0099 }
        r2 = "illegal ref";
        r0.<init>(r2);	 Catch:{ all -> 0x0099 }
        throw r0;	 Catch:{ all -> 0x0099 }
    L_0x0099:
        r0 = move-exception;
        r8.setContext(r1);
        throw r0;
    L_0x009e:
        r4 = "$";
        r4 = r4.equals(r3);	 Catch:{ all -> 0x0099 }
        if (r4 == 0) goto L_0x00b2;
    L_0x00a7:
        r0 = r1;
    L_0x00a8:
        r3 = r0.parent;	 Catch:{ all -> 0x0099 }
        if (r3 == 0) goto L_0x00af;
    L_0x00ac:
        r0 = r0.parent;	 Catch:{ all -> 0x0099 }
        goto L_0x00a8;
    L_0x00af:
        r0 = r0.object;	 Catch:{ all -> 0x0099 }
        goto L_0x0085;
    L_0x00b2:
        r4 = new com.alibaba.fastjson.parser.DefaultJSONParser$ResolveTask;	 Catch:{ all -> 0x0099 }
        r4.<init>(r1, r3);	 Catch:{ all -> 0x0099 }
        r8.addResolveTask(r4);	 Catch:{ all -> 0x0099 }
        r3 = 1;
        r8.resolveStatus = r3;	 Catch:{ all -> 0x0099 }
        goto L_0x0085;
    L_0x00be:
        r0 = new com.alibaba.fastjson.JSONException;	 Catch:{ all -> 0x0099 }
        r2 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0099 }
        r2.<init>();	 Catch:{ all -> 0x0099 }
        r3 = "illegal ref, ";
        r2 = r2.append(r3);	 Catch:{ all -> 0x0099 }
        r3 = com.alibaba.fastjson.parser.JSONToken.name(r4);	 Catch:{ all -> 0x0099 }
        r2 = r2.append(r3);	 Catch:{ all -> 0x0099 }
        r2 = r2.toString();	 Catch:{ all -> 0x0099 }
        r0.<init>(r2);	 Catch:{ all -> 0x0099 }
        throw r0;	 Catch:{ all -> 0x0099 }
    L_0x00dc:
        r3 = 16;
        r2.nextToken(r3);	 Catch:{ all -> 0x0099 }
        r8.setContext(r1);
        r9 = r0;
        goto L_0x0049;
    L_0x00e7:
        r5 = r9.size();	 Catch:{ all -> 0x0099 }
        if (r5 != 0) goto L_0x0120;
    L_0x00ed:
        r5 = 4;
        if (r4 != r5) goto L_0x0120;
    L_0x00f0:
        r4 = "@type";
        r5 = r2.stringVal();	 Catch:{ all -> 0x0099 }
        r4 = r4.equals(r5);	 Catch:{ all -> 0x0099 }
        if (r4 == 0) goto L_0x0120;
    L_0x00fd:
        r4 = com.alibaba.fastjson.parser.Feature.DisableSpecialKeyDetect;	 Catch:{ all -> 0x0099 }
        r4 = r2.isEnabled(r4);	 Catch:{ all -> 0x0099 }
        if (r4 != 0) goto L_0x0120;
    L_0x0105:
        r4 = 58;
        r2.nextTokenWithChar(r4);	 Catch:{ all -> 0x0099 }
        r4 = 16;
        r2.nextToken(r4);	 Catch:{ all -> 0x0099 }
        r4 = r2.token;	 Catch:{ all -> 0x0099 }
        r5 = 13;
        if (r4 != r5) goto L_0x011d;
    L_0x0115:
        r2.nextToken();	 Catch:{ all -> 0x0099 }
        r8.setContext(r1);
        goto L_0x0049;
    L_0x011d:
        r2.nextToken();	 Catch:{ all -> 0x0099 }
    L_0x0120:
        r4 = 0;
        r4 = r0.deserialze(r8, r10, r4);	 Catch:{ all -> 0x0099 }
        r5 = r2.token;	 Catch:{ all -> 0x0099 }
        r6 = 17;
        if (r5 == r6) goto L_0x0147;
    L_0x012b:
        r0 = new com.alibaba.fastjson.JSONException;	 Catch:{ all -> 0x0099 }
        r3 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0099 }
        r3.<init>();	 Catch:{ all -> 0x0099 }
        r4 = "syntax error, expect :, actual ";
        r3 = r3.append(r4);	 Catch:{ all -> 0x0099 }
        r2 = r2.token;	 Catch:{ all -> 0x0099 }
        r2 = r3.append(r2);	 Catch:{ all -> 0x0099 }
        r2 = r2.toString();	 Catch:{ all -> 0x0099 }
        r0.<init>(r2);	 Catch:{ all -> 0x0099 }
        throw r0;	 Catch:{ all -> 0x0099 }
    L_0x0147:
        r2.nextToken();	 Catch:{ all -> 0x0099 }
        r5 = r3.deserialze(r8, r11, r4);	 Catch:{ all -> 0x0099 }
        r6 = r8.resolveStatus;	 Catch:{ all -> 0x0099 }
        r7 = 1;
        if (r6 != r7) goto L_0x0156;
    L_0x0153:
        r8.checkMapResolve(r9, r4);	 Catch:{ all -> 0x0099 }
    L_0x0156:
        r9.put(r4, r5);	 Catch:{ all -> 0x0099 }
        r4 = r2.token;	 Catch:{ all -> 0x0099 }
        r5 = 16;
        if (r4 != r5) goto L_0x003b;
    L_0x015f:
        r2.nextToken();	 Catch:{ all -> 0x0099 }
        goto L_0x003b;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.parser.MapDeserializer.parseMap(com.alibaba.fastjson.parser.DefaultJSONParser, java.util.Map, java.lang.reflect.Type, java.lang.reflect.Type, java.lang.Object):java.lang.Object");
    }

    protected Map<?, ?> createMap(Type type) {
        if (type == Properties.class) {
            return new Properties();
        }
        if (type == Hashtable.class) {
            return new Hashtable();
        }
        if (type == IdentityHashMap.class) {
            return new IdentityHashMap();
        }
        if (type == SortedMap.class || type == TreeMap.class) {
            return new TreeMap();
        }
        if (type == ConcurrentMap.class || type == ConcurrentHashMap.class) {
            return new ConcurrentHashMap();
        }
        if (type == Map.class || type == HashMap.class) {
            return new HashMap();
        }
        if (type == LinkedHashMap.class) {
            return new LinkedHashMap();
        }
        if (type == JSONObject.class) {
            return new JSONObject();
        }
        if (type instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) type;
            Type rawType = parameterizedType.getRawType();
            if (EnumMap.class.equals(rawType)) {
                return new EnumMap((Class) parameterizedType.getActualTypeArguments()[0]);
            }
            return createMap(rawType);
        }
        Class cls = (Class) type;
        if (cls.isInterface()) {
            throw new JSONException("unsupport type " + type);
        }
        try {
            return (Map) cls.newInstance();
        } catch (Throwable e) {
            throw new JSONException("unsupport type " + type, e);
        }
    }
}
