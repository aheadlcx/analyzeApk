package com.alibaba.fastjson.parser.deserializer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.DefaultJSONParser.ResolveTask;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.parser.JSONLexer;
import com.alibaba.fastjson.parser.JSONToken;
import com.alibaba.fastjson.parser.ParseContext;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
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

public class MapDeserializer implements ObjectDeserializer {
    public static MapDeserializer instance = new MapDeserializer();

    public <T> T deserialze(DefaultJSONParser defaultJSONParser, Type type, Object obj) {
        if (type == JSONObject.class && defaultJSONParser.getFieldTypeResolver() == null) {
            return defaultJSONParser.parseObject();
        }
        JSONLexer jSONLexer = defaultJSONParser.lexer;
        if (jSONLexer.token() == 8) {
            jSONLexer.nextToken(16);
            return null;
        }
        Map createMap = createMap(type);
        ParseContext context = defaultJSONParser.getContext();
        try {
            defaultJSONParser.setContext(context, createMap, obj);
            T deserialze = deserialze(defaultJSONParser, type, obj, createMap);
            return deserialze;
        } finally {
            defaultJSONParser.setContext(context);
        }
    }

    protected Object deserialze(DefaultJSONParser defaultJSONParser, Type type, Object obj, Map map) {
        if (!(type instanceof ParameterizedType)) {
            return defaultJSONParser.parseObject(map, obj);
        }
        ParameterizedType parameterizedType = (ParameterizedType) type;
        Class cls = parameterizedType.getActualTypeArguments()[0];
        Type type2 = parameterizedType.getActualTypeArguments()[1];
        if (String.class == cls) {
            return parseMap(defaultJSONParser, map, type2, obj);
        }
        return parseMap(defaultJSONParser, map, cls, type2, obj);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.util.Map parseMap(com.alibaba.fastjson.parser.DefaultJSONParser r10, java.util.Map<java.lang.String, java.lang.Object> r11, java.lang.reflect.Type r12, java.lang.Object r13) {
        /*
        r9 = 39;
        r8 = 13;
        r7 = 58;
        r6 = 34;
        r3 = r10.lexer;
        r0 = r3.token();
        r1 = 12;
        if (r0 == r1) goto L_0x002f;
    L_0x0012:
        r0 = new com.alibaba.fastjson.JSONException;
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "syntax error, expect {, actual ";
        r1 = r1.append(r2);
        r2 = r3.token();
        r1 = r1.append(r2);
        r1 = r1.toString();
        r0.<init>(r1);
        throw r0;
    L_0x002f:
        r4 = r10.getContext();
        r0 = 0;
        r2 = r0;
    L_0x0035:
        r3.skipWhitespace();	 Catch:{ all -> 0x0085 }
        r0 = r3.getCurrent();	 Catch:{ all -> 0x0085 }
        r1 = com.alibaba.fastjson.parser.Feature.AllowArbitraryCommas;	 Catch:{ all -> 0x0085 }
        r1 = r3.isEnabled(r1);	 Catch:{ all -> 0x0085 }
        if (r1 == 0) goto L_0x0053;
    L_0x0044:
        r1 = 44;
        if (r0 != r1) goto L_0x0053;
    L_0x0048:
        r3.next();	 Catch:{ all -> 0x0085 }
        r3.skipWhitespace();	 Catch:{ all -> 0x0085 }
        r0 = r3.getCurrent();	 Catch:{ all -> 0x0085 }
        goto L_0x0044;
    L_0x0053:
        if (r0 != r6) goto L_0x008a;
    L_0x0055:
        r0 = r10.getSymbolTable();	 Catch:{ all -> 0x0085 }
        r1 = 34;
        r0 = r3.scanSymbol(r0, r1);	 Catch:{ all -> 0x0085 }
        r3.skipWhitespace();	 Catch:{ all -> 0x0085 }
        r1 = r3.getCurrent();	 Catch:{ all -> 0x0085 }
        if (r1 == r7) goto L_0x0127;
    L_0x0068:
        r0 = new com.alibaba.fastjson.JSONException;	 Catch:{ all -> 0x0085 }
        r1 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0085 }
        r1.<init>();	 Catch:{ all -> 0x0085 }
        r2 = "expect ':' at ";
        r1 = r1.append(r2);	 Catch:{ all -> 0x0085 }
        r2 = r3.pos();	 Catch:{ all -> 0x0085 }
        r1 = r1.append(r2);	 Catch:{ all -> 0x0085 }
        r1 = r1.toString();	 Catch:{ all -> 0x0085 }
        r0.<init>(r1);	 Catch:{ all -> 0x0085 }
        throw r0;	 Catch:{ all -> 0x0085 }
    L_0x0085:
        r0 = move-exception;
        r10.setContext(r4);
        throw r0;
    L_0x008a:
        r1 = 125; // 0x7d float:1.75E-43 double:6.2E-322;
        if (r0 != r1) goto L_0x009d;
    L_0x008e:
        r3.next();	 Catch:{ all -> 0x0085 }
        r3.resetStringPosition();	 Catch:{ all -> 0x0085 }
        r0 = 16;
        r3.nextToken(r0);	 Catch:{ all -> 0x0085 }
        r10.setContext(r4);
    L_0x009c:
        return r11;
    L_0x009d:
        if (r0 != r9) goto L_0x00df;
    L_0x009f:
        r0 = com.alibaba.fastjson.parser.Feature.AllowSingleQuotes;	 Catch:{ all -> 0x0085 }
        r0 = r3.isEnabled(r0);	 Catch:{ all -> 0x0085 }
        if (r0 != 0) goto L_0x00af;
    L_0x00a7:
        r0 = new com.alibaba.fastjson.JSONException;	 Catch:{ all -> 0x0085 }
        r1 = "syntax error";
        r0.<init>(r1);	 Catch:{ all -> 0x0085 }
        throw r0;	 Catch:{ all -> 0x0085 }
    L_0x00af:
        r0 = r10.getSymbolTable();	 Catch:{ all -> 0x0085 }
        r1 = 39;
        r0 = r3.scanSymbol(r0, r1);	 Catch:{ all -> 0x0085 }
        r3.skipWhitespace();	 Catch:{ all -> 0x0085 }
        r1 = r3.getCurrent();	 Catch:{ all -> 0x0085 }
        if (r1 == r7) goto L_0x0127;
    L_0x00c2:
        r0 = new com.alibaba.fastjson.JSONException;	 Catch:{ all -> 0x0085 }
        r1 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0085 }
        r1.<init>();	 Catch:{ all -> 0x0085 }
        r2 = "expect ':' at ";
        r1 = r1.append(r2);	 Catch:{ all -> 0x0085 }
        r2 = r3.pos();	 Catch:{ all -> 0x0085 }
        r1 = r1.append(r2);	 Catch:{ all -> 0x0085 }
        r1 = r1.toString();	 Catch:{ all -> 0x0085 }
        r0.<init>(r1);	 Catch:{ all -> 0x0085 }
        throw r0;	 Catch:{ all -> 0x0085 }
    L_0x00df:
        r0 = com.alibaba.fastjson.parser.Feature.AllowUnQuotedFieldNames;	 Catch:{ all -> 0x0085 }
        r0 = r3.isEnabled(r0);	 Catch:{ all -> 0x0085 }
        if (r0 != 0) goto L_0x00ef;
    L_0x00e7:
        r0 = new com.alibaba.fastjson.JSONException;	 Catch:{ all -> 0x0085 }
        r1 = "syntax error";
        r0.<init>(r1);	 Catch:{ all -> 0x0085 }
        throw r0;	 Catch:{ all -> 0x0085 }
    L_0x00ef:
        r0 = r10.getSymbolTable();	 Catch:{ all -> 0x0085 }
        r0 = r3.scanSymbolUnQuoted(r0);	 Catch:{ all -> 0x0085 }
        r3.skipWhitespace();	 Catch:{ all -> 0x0085 }
        r1 = r3.getCurrent();	 Catch:{ all -> 0x0085 }
        if (r1 == r7) goto L_0x0127;
    L_0x0100:
        r0 = new com.alibaba.fastjson.JSONException;	 Catch:{ all -> 0x0085 }
        r2 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0085 }
        r2.<init>();	 Catch:{ all -> 0x0085 }
        r5 = "expect ':' at ";
        r2 = r2.append(r5);	 Catch:{ all -> 0x0085 }
        r3 = r3.pos();	 Catch:{ all -> 0x0085 }
        r2 = r2.append(r3);	 Catch:{ all -> 0x0085 }
        r3 = ", actual ";
        r2 = r2.append(r3);	 Catch:{ all -> 0x0085 }
        r1 = r2.append(r1);	 Catch:{ all -> 0x0085 }
        r1 = r1.toString();	 Catch:{ all -> 0x0085 }
        r0.<init>(r1);	 Catch:{ all -> 0x0085 }
        throw r0;	 Catch:{ all -> 0x0085 }
    L_0x0127:
        r1 = r0;
        r3.next();	 Catch:{ all -> 0x0085 }
        r3.skipWhitespace();	 Catch:{ all -> 0x0085 }
        r3.getCurrent();	 Catch:{ all -> 0x0085 }
        r3.resetStringPosition();	 Catch:{ all -> 0x0085 }
        r0 = com.alibaba.fastjson.JSON.DEFAULT_TYPE_KEY;	 Catch:{ all -> 0x0085 }
        if (r1 != r0) goto L_0x0199;
    L_0x0138:
        r0 = com.alibaba.fastjson.parser.Feature.DisableSpecialKeyDetect;	 Catch:{ all -> 0x0085 }
        r0 = r3.isEnabled(r0);	 Catch:{ all -> 0x0085 }
        if (r0 != 0) goto L_0x0199;
    L_0x0140:
        r0 = r10.getSymbolTable();	 Catch:{ all -> 0x0085 }
        r1 = 34;
        r0 = r3.scanSymbol(r0, r1);	 Catch:{ all -> 0x0085 }
        r1 = r10.getConfig();	 Catch:{ all -> 0x0085 }
        r1 = r1.getDefaultClassLoader();	 Catch:{ all -> 0x0085 }
        r0 = com.alibaba.fastjson.util.TypeUtils.loadClass(r0, r1);	 Catch:{ all -> 0x0085 }
        r1 = java.util.Map.class;
        r1 = r1.isAssignableFrom(r0);	 Catch:{ all -> 0x0085 }
        if (r1 == 0) goto L_0x0173;
    L_0x015e:
        r0 = 16;
        r3.nextToken(r0);	 Catch:{ all -> 0x0085 }
        r0 = r3.token();	 Catch:{ all -> 0x0085 }
        if (r0 != r8) goto L_0x01d9;
    L_0x0169:
        r0 = 16;
        r3.nextToken(r0);	 Catch:{ all -> 0x0085 }
        r10.setContext(r4);
        goto L_0x009c;
    L_0x0173:
        r1 = r10.getConfig();	 Catch:{ all -> 0x0085 }
        r1 = r1.getDeserializer(r0);	 Catch:{ all -> 0x0085 }
        r2 = 16;
        r3.nextToken(r2);	 Catch:{ all -> 0x0085 }
        r2 = 2;
        r10.setResolveStatus(r2);	 Catch:{ all -> 0x0085 }
        if (r4 == 0) goto L_0x018d;
    L_0x0186:
        r2 = r13 instanceof java.lang.Integer;	 Catch:{ all -> 0x0085 }
        if (r2 != 0) goto L_0x018d;
    L_0x018a:
        r10.popContext();	 Catch:{ all -> 0x0085 }
    L_0x018d:
        r0 = r1.deserialze(r10, r0, r13);	 Catch:{ all -> 0x0085 }
        r0 = (java.util.Map) r0;	 Catch:{ all -> 0x0085 }
        r10.setContext(r4);
        r11 = r0;
        goto L_0x009c;
    L_0x0199:
        r3.nextToken();	 Catch:{ all -> 0x0085 }
        if (r2 == 0) goto L_0x01a1;
    L_0x019e:
        r10.setContext(r4);	 Catch:{ all -> 0x0085 }
    L_0x01a1:
        r0 = r3.token();	 Catch:{ all -> 0x0085 }
        r5 = 8;
        if (r0 != r5) goto L_0x01ca;
    L_0x01a9:
        r0 = 0;
        r3.nextToken();	 Catch:{ all -> 0x0085 }
    L_0x01ad:
        r11.put(r1, r0);	 Catch:{ all -> 0x0085 }
        r10.checkMapResolve(r11, r1);	 Catch:{ all -> 0x0085 }
        r10.setContext(r4, r0, r1);	 Catch:{ all -> 0x0085 }
        r10.setContext(r4);	 Catch:{ all -> 0x0085 }
        r0 = r3.token();	 Catch:{ all -> 0x0085 }
        r1 = 20;
        if (r0 == r1) goto L_0x01c5;
    L_0x01c1:
        r1 = 15;
        if (r0 != r1) goto L_0x01cf;
    L_0x01c5:
        r10.setContext(r4);
        goto L_0x009c;
    L_0x01ca:
        r0 = r10.parseObject(r12, r1);	 Catch:{ all -> 0x0085 }
        goto L_0x01ad;
    L_0x01cf:
        if (r0 != r8) goto L_0x01d9;
    L_0x01d1:
        r3.nextToken();	 Catch:{ all -> 0x0085 }
        r10.setContext(r4);
        goto L_0x009c;
    L_0x01d9:
        r0 = r2 + 1;
        r2 = r0;
        goto L_0x0035;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.parser.deserializer.MapDeserializer.parseMap(com.alibaba.fastjson.parser.DefaultJSONParser, java.util.Map, java.lang.reflect.Type, java.lang.Object):java.util.Map");
    }

    public static Object parseMap(DefaultJSONParser defaultJSONParser, Map<Object, Object> map, Type type, Type type2, Object obj) {
        Map<Object, Object> map2 = null;
        JSONLexer jSONLexer = defaultJSONParser.lexer;
        if (jSONLexer.token() == 12 || jSONLexer.token() == 16) {
            ObjectDeserializer deserializer = defaultJSONParser.getConfig().getDeserializer(type);
            ObjectDeserializer deserializer2 = defaultJSONParser.getConfig().getDeserializer(type2);
            jSONLexer.nextToken(deserializer.getFastMatchToken());
            ParseContext context = defaultJSONParser.getContext();
            while (jSONLexer.token() != 13) {
                if (jSONLexer.token() == 4 && jSONLexer.isRef() && !jSONLexer.isEnabled(Feature.DisableSpecialKeyDetect)) {
                    jSONLexer.nextTokenWithColon(4);
                    if (jSONLexer.token() == 4) {
                        String stringVal = jSONLexer.stringVal();
                        if ("..".equals(stringVal)) {
                            map2 = context.parent.object;
                        } else if ("$".equals(stringVal)) {
                            ParseContext parseContext = context;
                            while (parseContext.parent != null) {
                                parseContext = parseContext.parent;
                            }
                            map2 = parseContext.object;
                        } else {
                            defaultJSONParser.addResolveTask(new ResolveTask(context, stringVal));
                            defaultJSONParser.setResolveStatus(1);
                        }
                        jSONLexer.nextToken(13);
                        if (jSONLexer.token() != 13) {
                            throw new JSONException("illegal ref");
                        }
                        jSONLexer.nextToken(16);
                        defaultJSONParser.setContext(context);
                        return map2;
                    }
                    throw new JSONException("illegal ref, " + JSONToken.name(jSONLexer.token()));
                }
                if (map.size() == 0 && jSONLexer.token() == 4 && JSON.DEFAULT_TYPE_KEY.equals(jSONLexer.stringVal()) && !jSONLexer.isEnabled(Feature.DisableSpecialKeyDetect)) {
                    jSONLexer.nextTokenWithColon(4);
                    jSONLexer.nextToken(16);
                    if (jSONLexer.token() == 13) {
                        jSONLexer.nextToken();
                        return map;
                    }
                    try {
                        jSONLexer.nextToken(deserializer.getFastMatchToken());
                    } finally {
                        defaultJSONParser.setContext(context);
                    }
                }
                Object deserialze = deserializer.deserialze(defaultJSONParser, type, null);
                if (jSONLexer.token() != 17) {
                    throw new JSONException("syntax error, expect :, actual " + jSONLexer.token());
                }
                jSONLexer.nextToken(deserializer2.getFastMatchToken());
                Object deserialze2 = deserializer2.deserialze(defaultJSONParser, type2, deserialze);
                defaultJSONParser.checkMapResolve(map, deserialze);
                map.put(deserialze, deserialze2);
                if (jSONLexer.token() == 16) {
                    jSONLexer.nextToken(deserializer.getFastMatchToken());
                }
            }
            jSONLexer.nextToken(16);
            defaultJSONParser.setContext(context);
            return map;
        }
        throw new JSONException("syntax error, expect {, actual " + jSONLexer.tokenName());
    }

    protected Map<Object, Object> createMap(Type type) {
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
        if (type instanceof ParameterizedType) {
            return createMap(((ParameterizedType) type).getRawType());
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

    public int getFastMatchToken() {
        return 12;
    }
}
