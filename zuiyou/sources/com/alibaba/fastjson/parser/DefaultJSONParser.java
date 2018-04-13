package com.alibaba.fastjson.parser;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.deserializer.ExtraProcessor;
import com.alibaba.fastjson.parser.deserializer.ExtraTypeProvider;
import com.alibaba.fastjson.parser.deserializer.FieldDeserializer;
import com.alibaba.fastjson.parser.deserializer.FieldTypeResolver;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import com.alibaba.fastjson.serializer.IntegerCodec;
import com.alibaba.fastjson.serializer.StringCodec;
import com.alibaba.fastjson.util.TypeUtils;
import java.io.Closeable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

public class DefaultJSONParser implements Closeable {
    public static final int NONE = 0;
    public static final int NeedToResolve = 1;
    public static final int TypeNameRedirect = 2;
    public ParserConfig config;
    protected ParseContext contex;
    private ParseContext[] contextArray;
    private int contextArrayIndex;
    private DateFormat dateFormat;
    private String dateFormatPattern;
    protected List<ExtraProcessor> extraProcessors;
    protected List<ExtraTypeProvider> extraTypeProviders;
    public FieldTypeResolver fieldTypeResolver;
    public final JSONLexer lexer;
    public int resolveStatus;
    private List<ResolveTask> resolveTaskList;
    public final SymbolTable symbolTable;

    public static class ResolveTask {
        private final ParseContext context;
        public FieldDeserializer fieldDeserializer;
        public ParseContext ownerContext;
        private final String referenceValue;

        public ResolveTask(ParseContext parseContext, String str) {
            this.context = parseContext;
            this.referenceValue = str;
        }
    }

    public String getDateFomartPattern() {
        return this.dateFormatPattern;
    }

    public DateFormat getDateFormat() {
        if (this.dateFormat == null) {
            this.dateFormat = new SimpleDateFormat(this.dateFormatPattern, this.lexer.locale);
            this.dateFormat.setTimeZone(this.lexer.timeZone);
        }
        return this.dateFormat;
    }

    public void setDateFormat(String str) {
        this.dateFormatPattern = str;
        this.dateFormat = null;
    }

    public void setDateFomrat(DateFormat dateFormat) {
        this.dateFormat = dateFormat;
    }

    public DefaultJSONParser(String str) {
        this(str, ParserConfig.global, JSON.DEFAULT_PARSER_FEATURE);
    }

    public DefaultJSONParser(String str, ParserConfig parserConfig) {
        this(new JSONLexer(str, JSON.DEFAULT_PARSER_FEATURE), parserConfig);
    }

    public DefaultJSONParser(String str, ParserConfig parserConfig, int i) {
        this(new JSONLexer(str, i), parserConfig);
    }

    public DefaultJSONParser(char[] cArr, int i, ParserConfig parserConfig, int i2) {
        this(new JSONLexer(cArr, i, i2), parserConfig);
    }

    public DefaultJSONParser(JSONLexer jSONLexer) {
        this(jSONLexer, ParserConfig.global);
    }

    public DefaultJSONParser(JSONLexer jSONLexer, ParserConfig parserConfig) {
        char c = JSONLexer.EOI;
        this.dateFormatPattern = JSON.DEFFAULT_DATE_FORMAT;
        this.contextArrayIndex = 0;
        this.resolveStatus = 0;
        this.extraTypeProviders = null;
        this.extraProcessors = null;
        this.fieldTypeResolver = null;
        this.lexer = jSONLexer;
        this.config = parserConfig;
        this.symbolTable = parserConfig.symbolTable;
        int i;
        if (jSONLexer.ch == '{') {
            i = jSONLexer.bp + 1;
            jSONLexer.bp = i;
            if (i < jSONLexer.len) {
                c = jSONLexer.text.charAt(i);
            }
            jSONLexer.ch = c;
            jSONLexer.token = 12;
        } else if (jSONLexer.ch == '[') {
            i = jSONLexer.bp + 1;
            jSONLexer.bp = i;
            if (i < jSONLexer.len) {
                c = jSONLexer.text.charAt(i);
            }
            jSONLexer.ch = c;
            jSONLexer.token = 14;
        } else {
            jSONLexer.nextToken();
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object parseObject(java.util.Map r20, java.lang.Object r21) {
        /*
        r19 = this;
        r0 = r19;
        r14 = r0.lexer;
        r3 = r14.token;
        r4 = 8;
        if (r3 != r4) goto L_0x0010;
    L_0x000a:
        r14.nextToken();
        r20 = 0;
    L_0x000f:
        return r20;
    L_0x0010:
        r4 = 12;
        if (r3 == r4) goto L_0x0045;
    L_0x0014:
        r4 = 16;
        if (r3 == r4) goto L_0x0045;
    L_0x0018:
        r4 = new com.alibaba.fastjson.JSONException;
        r5 = new java.lang.StringBuilder;
        r5.<init>();
        r6 = "syntax error, expect {, actual ";
        r5 = r5.append(r6);
        r3 = com.alibaba.fastjson.parser.JSONToken.name(r3);
        r3 = r5.append(r3);
        r5 = ", ";
        r3 = r3.append(r5);
        r5 = r14.info();
        r3 = r3.append(r5);
        r3 = r3.toString();
        r4.<init>(r3);
        throw r4;
    L_0x0045:
        r0 = r20;
        r3 = r0 instanceof com.alibaba.fastjson.JSONObject;
        if (r3 == 0) goto L_0x0082;
    L_0x004b:
        r3 = r20;
        r3 = (com.alibaba.fastjson.JSONObject) r3;
        r4 = r3.getInnerMap();
        r3 = 1;
    L_0x0054:
        r5 = r14.features;
        r6 = com.alibaba.fastjson.parser.Feature.AllowISO8601DateFormat;
        r6 = r6.mask;
        r5 = r5 & r6;
        if (r5 == 0) goto L_0x0086;
    L_0x005d:
        r5 = 1;
    L_0x005e:
        r15 = r14.disableCircularReferenceDetect;
        r0 = r19;
        r7 = r0.contex;
        r13 = 0;
        r9 = r13;
    L_0x0066:
        r6 = r14.ch;	 Catch:{ all -> 0x00cd }
        r8 = 34;
        if (r6 == r8) goto L_0x0075;
    L_0x006c:
        r8 = 125; // 0x7d float:1.75E-43 double:6.2E-322;
        if (r6 == r8) goto L_0x0075;
    L_0x0070:
        r14.skipWhitespace();	 Catch:{ all -> 0x00cd }
        r6 = r14.ch;	 Catch:{ all -> 0x00cd }
    L_0x0075:
        r8 = 44;
        if (r6 != r8) goto L_0x0088;
    L_0x0079:
        r14.next();	 Catch:{ all -> 0x00cd }
        r14.skipWhitespace();	 Catch:{ all -> 0x00cd }
        r6 = r14.ch;	 Catch:{ all -> 0x00cd }
        goto L_0x0075;
    L_0x0082:
        r3 = 0;
        r4 = r20;
        goto L_0x0054;
    L_0x0086:
        r5 = 0;
        goto L_0x005e;
    L_0x0088:
        r10 = 0;
        r8 = 34;
        if (r6 != r8) goto L_0x00d5;
    L_0x008d:
        r0 = r19;
        r6 = r0.symbolTable;	 Catch:{ all -> 0x00cd }
        r8 = 34;
        r8 = r14.scanSymbol(r6, r8);	 Catch:{ all -> 0x00cd }
        r6 = r14.ch;	 Catch:{ all -> 0x00cd }
        r11 = 58;
        if (r6 == r11) goto L_0x07ba;
    L_0x009d:
        r14.skipWhitespace();	 Catch:{ all -> 0x00cd }
        r6 = r14.ch;	 Catch:{ all -> 0x00cd }
        r11 = 58;
        if (r6 == r11) goto L_0x07ba;
    L_0x00a6:
        r3 = new com.alibaba.fastjson.JSONException;	 Catch:{ all -> 0x00cd }
        r4 = new java.lang.StringBuilder;	 Catch:{ all -> 0x00cd }
        r4.<init>();	 Catch:{ all -> 0x00cd }
        r5 = "expect ':' at ";
        r4 = r4.append(r5);	 Catch:{ all -> 0x00cd }
        r5 = r14.pos;	 Catch:{ all -> 0x00cd }
        r4 = r4.append(r5);	 Catch:{ all -> 0x00cd }
        r5 = ", name ";
        r4 = r4.append(r5);	 Catch:{ all -> 0x00cd }
        r4 = r4.append(r8);	 Catch:{ all -> 0x00cd }
        r4 = r4.toString();	 Catch:{ all -> 0x00cd }
        r3.<init>(r4);	 Catch:{ all -> 0x00cd }
        throw r3;	 Catch:{ all -> 0x00cd }
    L_0x00cd:
        r3 = move-exception;
        if (r15 != 0) goto L_0x00d4;
    L_0x00d0:
        r0 = r19;
        r0.contex = r7;
    L_0x00d4:
        throw r3;
    L_0x00d5:
        r8 = 125; // 0x7d float:1.75E-43 double:6.2E-322;
        if (r6 != r8) goto L_0x00fe;
    L_0x00d9:
        r3 = r14.bp;	 Catch:{ all -> 0x00cd }
        r3 = r3 + 1;
        r14.bp = r3;	 Catch:{ all -> 0x00cd }
        r4 = r14.len;	 Catch:{ all -> 0x00cd }
        if (r3 < r4) goto L_0x00f7;
    L_0x00e3:
        r3 = 26;
    L_0x00e5:
        r14.ch = r3;	 Catch:{ all -> 0x00cd }
        r3 = 0;
        r14.sp = r3;	 Catch:{ all -> 0x00cd }
        r3 = 16;
        r14.nextToken(r3);	 Catch:{ all -> 0x00cd }
        if (r15 != 0) goto L_0x000f;
    L_0x00f1:
        r0 = r19;
        r0.contex = r7;
        goto L_0x000f;
    L_0x00f7:
        r4 = r14.text;	 Catch:{ all -> 0x00cd }
        r3 = r4.charAt(r3);	 Catch:{ all -> 0x00cd }
        goto L_0x00e5;
    L_0x00fe:
        r8 = 39;
        if (r6 != r8) goto L_0x0137;
    L_0x0102:
        r0 = r19;
        r6 = r0.symbolTable;	 Catch:{ all -> 0x00cd }
        r8 = 39;
        r8 = r14.scanSymbol(r6, r8);	 Catch:{ all -> 0x00cd }
        r6 = r14.ch;	 Catch:{ all -> 0x00cd }
        r11 = 58;
        if (r6 == r11) goto L_0x0115;
    L_0x0112:
        r14.skipWhitespace();	 Catch:{ all -> 0x00cd }
    L_0x0115:
        r6 = r14.ch;	 Catch:{ all -> 0x00cd }
        r11 = 58;
        if (r6 == r11) goto L_0x07ba;
    L_0x011b:
        r3 = new com.alibaba.fastjson.JSONException;	 Catch:{ all -> 0x00cd }
        r4 = new java.lang.StringBuilder;	 Catch:{ all -> 0x00cd }
        r4.<init>();	 Catch:{ all -> 0x00cd }
        r5 = "expect ':' at ";
        r4 = r4.append(r5);	 Catch:{ all -> 0x00cd }
        r5 = r14.pos;	 Catch:{ all -> 0x00cd }
        r4 = r4.append(r5);	 Catch:{ all -> 0x00cd }
        r4 = r4.toString();	 Catch:{ all -> 0x00cd }
        r3.<init>(r4);	 Catch:{ all -> 0x00cd }
        throw r3;	 Catch:{ all -> 0x00cd }
    L_0x0137:
        r8 = 26;
        if (r6 != r8) goto L_0x0159;
    L_0x013b:
        r3 = new com.alibaba.fastjson.JSONException;	 Catch:{ all -> 0x00cd }
        r4 = new java.lang.StringBuilder;	 Catch:{ all -> 0x00cd }
        r4.<init>();	 Catch:{ all -> 0x00cd }
        r5 = "syntax error, ";
        r4 = r4.append(r5);	 Catch:{ all -> 0x00cd }
        r5 = r14.info();	 Catch:{ all -> 0x00cd }
        r4 = r4.append(r5);	 Catch:{ all -> 0x00cd }
        r4 = r4.toString();	 Catch:{ all -> 0x00cd }
        r3.<init>(r4);	 Catch:{ all -> 0x00cd }
        throw r3;	 Catch:{ all -> 0x00cd }
    L_0x0159:
        r8 = 44;
        if (r6 != r8) goto L_0x017b;
    L_0x015d:
        r3 = new com.alibaba.fastjson.JSONException;	 Catch:{ all -> 0x00cd }
        r4 = new java.lang.StringBuilder;	 Catch:{ all -> 0x00cd }
        r4.<init>();	 Catch:{ all -> 0x00cd }
        r5 = "syntax error, ";
        r4 = r4.append(r5);	 Catch:{ all -> 0x00cd }
        r5 = r14.info();	 Catch:{ all -> 0x00cd }
        r4 = r4.append(r5);	 Catch:{ all -> 0x00cd }
        r4 = r4.toString();	 Catch:{ all -> 0x00cd }
        r3.<init>(r4);	 Catch:{ all -> 0x00cd }
        throw r3;	 Catch:{ all -> 0x00cd }
    L_0x017b:
        r8 = 48;
        if (r6 < r8) goto L_0x0183;
    L_0x017f:
        r8 = 57;
        if (r6 <= r8) goto L_0x0187;
    L_0x0183:
        r8 = 45;
        if (r6 != r8) goto L_0x01e5;
    L_0x0187:
        r6 = 0;
        r14.sp = r6;	 Catch:{ all -> 0x00cd }
        r14.scanNumber();	 Catch:{ all -> 0x00cd }
        r6 = r14.token;	 Catch:{ NumberFormatException -> 0x01c6 }
        r8 = 2;
        if (r6 != r8) goto L_0x01c0;
    L_0x0192:
        r6 = r14.integerValue();	 Catch:{ NumberFormatException -> 0x01c6 }
    L_0x0196:
        if (r3 == 0) goto L_0x019c;
    L_0x0198:
        r6 = r6.toString();	 Catch:{ NumberFormatException -> 0x01c6 }
    L_0x019c:
        r8 = r14.ch;	 Catch:{ all -> 0x00cd }
        r11 = 58;
        if (r8 == r11) goto L_0x07bd;
    L_0x01a2:
        r3 = new com.alibaba.fastjson.JSONException;	 Catch:{ all -> 0x00cd }
        r4 = new java.lang.StringBuilder;	 Catch:{ all -> 0x00cd }
        r4.<init>();	 Catch:{ all -> 0x00cd }
        r5 = "parse number key error, ";
        r4 = r4.append(r5);	 Catch:{ all -> 0x00cd }
        r5 = r14.info();	 Catch:{ all -> 0x00cd }
        r4 = r4.append(r5);	 Catch:{ all -> 0x00cd }
        r4 = r4.toString();	 Catch:{ all -> 0x00cd }
        r3.<init>(r4);	 Catch:{ all -> 0x00cd }
        throw r3;	 Catch:{ all -> 0x00cd }
    L_0x01c0:
        r6 = 1;
        r6 = r14.decimalValue(r6);	 Catch:{ NumberFormatException -> 0x01c6 }
        goto L_0x0196;
    L_0x01c6:
        r3 = move-exception;
        r3 = new com.alibaba.fastjson.JSONException;	 Catch:{ all -> 0x00cd }
        r4 = new java.lang.StringBuilder;	 Catch:{ all -> 0x00cd }
        r4.<init>();	 Catch:{ all -> 0x00cd }
        r5 = "parse number key error, ";
        r4 = r4.append(r5);	 Catch:{ all -> 0x00cd }
        r5 = r14.info();	 Catch:{ all -> 0x00cd }
        r4 = r4.append(r5);	 Catch:{ all -> 0x00cd }
        r4 = r4.toString();	 Catch:{ all -> 0x00cd }
        r3.<init>(r4);	 Catch:{ all -> 0x00cd }
        throw r3;	 Catch:{ all -> 0x00cd }
    L_0x01e5:
        r8 = 123; // 0x7b float:1.72E-43 double:6.1E-322;
        if (r6 == r8) goto L_0x01ed;
    L_0x01e9:
        r8 = 91;
        if (r6 != r8) goto L_0x0227;
    L_0x01ed:
        r14.nextToken();	 Catch:{ all -> 0x00cd }
        r8 = r19.parse();	 Catch:{ all -> 0x00cd }
        r6 = 1;
    L_0x01f5:
        if (r6 != 0) goto L_0x026e;
    L_0x01f7:
        r6 = r14.bp;	 Catch:{ all -> 0x00cd }
        r6 = r6 + 1;
        r14.bp = r6;	 Catch:{ all -> 0x00cd }
        r10 = r14.len;	 Catch:{ all -> 0x00cd }
        if (r6 < r10) goto L_0x0267;
    L_0x0201:
        r6 = 26;
    L_0x0203:
        r14.ch = r6;	 Catch:{ all -> 0x00cd }
    L_0x0205:
        r10 = 32;
        if (r6 > r10) goto L_0x07b7;
    L_0x0209:
        r10 = 32;
        if (r6 == r10) goto L_0x0221;
    L_0x020d:
        r10 = 10;
        if (r6 == r10) goto L_0x0221;
    L_0x0211:
        r10 = 13;
        if (r6 == r10) goto L_0x0221;
    L_0x0215:
        r10 = 9;
        if (r6 == r10) goto L_0x0221;
    L_0x0219:
        r10 = 12;
        if (r6 == r10) goto L_0x0221;
    L_0x021d:
        r10 = 8;
        if (r6 != r10) goto L_0x07b7;
    L_0x0221:
        r14.next();	 Catch:{ all -> 0x00cd }
        r6 = r14.ch;	 Catch:{ all -> 0x00cd }
        goto L_0x0205;
    L_0x0227:
        r0 = r19;
        r6 = r0.symbolTable;	 Catch:{ all -> 0x00cd }
        r8 = r14.scanSymbolUnQuoted(r6);	 Catch:{ all -> 0x00cd }
        r14.skipWhitespace();	 Catch:{ all -> 0x00cd }
        r6 = r14.ch;	 Catch:{ all -> 0x00cd }
        r11 = 58;
        if (r6 == r11) goto L_0x025f;
    L_0x0238:
        r3 = new com.alibaba.fastjson.JSONException;	 Catch:{ all -> 0x00cd }
        r4 = new java.lang.StringBuilder;	 Catch:{ all -> 0x00cd }
        r4.<init>();	 Catch:{ all -> 0x00cd }
        r5 = "expect ':' at ";
        r4 = r4.append(r5);	 Catch:{ all -> 0x00cd }
        r5 = r14.bp;	 Catch:{ all -> 0x00cd }
        r4 = r4.append(r5);	 Catch:{ all -> 0x00cd }
        r5 = ", actual ";
        r4 = r4.append(r5);	 Catch:{ all -> 0x00cd }
        r4 = r4.append(r6);	 Catch:{ all -> 0x00cd }
        r4 = r4.toString();	 Catch:{ all -> 0x00cd }
        r3.<init>(r4);	 Catch:{ all -> 0x00cd }
        throw r3;	 Catch:{ all -> 0x00cd }
    L_0x025f:
        if (r3 == 0) goto L_0x07ba;
    L_0x0261:
        r8 = r8.toString();	 Catch:{ all -> 0x00cd }
        r6 = r10;
        goto L_0x01f5;
    L_0x0267:
        r10 = r14.text;	 Catch:{ all -> 0x00cd }
        r6 = r10.charAt(r6);	 Catch:{ all -> 0x00cd }
        goto L_0x0203;
    L_0x026e:
        r6 = r14.ch;	 Catch:{ all -> 0x00cd }
        r10 = r6;
    L_0x0271:
        r6 = 0;
        r14.sp = r6;	 Catch:{ all -> 0x00cd }
        r6 = "@type";
        if (r8 != r6) goto L_0x036d;
    L_0x0279:
        r6 = com.alibaba.fastjson.parser.Feature.DisableSpecialKeyDetect;	 Catch:{ all -> 0x00cd }
        r6 = r14.isEnabled(r6);	 Catch:{ all -> 0x00cd }
        if (r6 != 0) goto L_0x036d;
    L_0x0281:
        r0 = r19;
        r6 = r0.symbolTable;	 Catch:{ all -> 0x00cd }
        r8 = 34;
        r8 = r14.scanSymbol(r6, r8);	 Catch:{ all -> 0x00cd }
        r0 = r19;
        r6 = r0.config;	 Catch:{ all -> 0x00cd }
        r10 = 0;
        r11 = r14.features;	 Catch:{ all -> 0x00cd }
        r10 = r6.checkAutoType(r8, r10, r11);	 Catch:{ all -> 0x00cd }
        if (r10 != 0) goto L_0x02a2;
    L_0x0298:
        r6 = "@type";
        r0 = r20;
        r0.put(r6, r8);	 Catch:{ all -> 0x00cd }
        goto L_0x0066;
    L_0x02a2:
        r3 = 16;
        r14.nextToken(r3);	 Catch:{ all -> 0x00cd }
        r3 = r14.token;	 Catch:{ all -> 0x00cd }
        r4 = 13;
        if (r3 != r4) goto L_0x0326;
    L_0x02ad:
        r3 = 16;
        r14.nextToken(r3);	 Catch:{ all -> 0x00cd }
        r4 = 0;
        r0 = r19;
        r3 = r0.config;	 Catch:{ Exception -> 0x02f3 }
        r3 = r3.getDeserializer(r10);	 Catch:{ Exception -> 0x02f3 }
        r5 = r3 instanceof com.alibaba.fastjson.parser.JavaBeanDeserializer;	 Catch:{ Exception -> 0x02f3 }
        if (r5 == 0) goto L_0x07b4;
    L_0x02bf:
        r3 = (com.alibaba.fastjson.parser.JavaBeanDeserializer) r3;	 Catch:{ Exception -> 0x02f3 }
        r0 = r19;
        r6 = r3.createInstance(r0, r10);	 Catch:{ Exception -> 0x02f3 }
        r4 = r20.entrySet();	 Catch:{ Exception -> 0x02f3 }
        r9 = r4.iterator();	 Catch:{ Exception -> 0x02f3 }
    L_0x02cf:
        r4 = r9.hasNext();	 Catch:{ Exception -> 0x02f3 }
        if (r4 == 0) goto L_0x02fd;
    L_0x02d5:
        r4 = r9.next();	 Catch:{ Exception -> 0x02f3 }
        r4 = (java.util.Map.Entry) r4;	 Catch:{ Exception -> 0x02f3 }
        r5 = r4.getKey();	 Catch:{ Exception -> 0x02f3 }
        r11 = r5 instanceof java.lang.String;	 Catch:{ Exception -> 0x02f3 }
        if (r11 == 0) goto L_0x02cf;
    L_0x02e3:
        r5 = (java.lang.String) r5;	 Catch:{ Exception -> 0x02f3 }
        r5 = r3.getFieldDeserializer(r5);	 Catch:{ Exception -> 0x02f3 }
        if (r5 == 0) goto L_0x02cf;
    L_0x02eb:
        r4 = r4.getValue();	 Catch:{ Exception -> 0x02f3 }
        r5.setValue(r6, r4);	 Catch:{ Exception -> 0x02f3 }
        goto L_0x02cf;
    L_0x02f3:
        r3 = move-exception;
        r4 = new com.alibaba.fastjson.JSONException;	 Catch:{ all -> 0x00cd }
        r5 = "create instance error";
        r4.<init>(r5, r3);	 Catch:{ all -> 0x00cd }
        throw r4;	 Catch:{ all -> 0x00cd }
    L_0x02fd:
        r3 = r6;
    L_0x02fe:
        if (r3 != 0) goto L_0x0309;
    L_0x0300:
        r3 = java.lang.Cloneable.class;
        if (r10 != r3) goto L_0x0313;
    L_0x0304:
        r3 = new java.util.HashMap;	 Catch:{ Exception -> 0x02f3 }
        r3.<init>();	 Catch:{ Exception -> 0x02f3 }
    L_0x0309:
        if (r15 != 0) goto L_0x030f;
    L_0x030b:
        r0 = r19;
        r0.contex = r7;
    L_0x030f:
        r20 = r3;
        goto L_0x000f;
    L_0x0313:
        r3 = "java.util.Collections$EmptyMap";
        r3 = r3.equals(r8);	 Catch:{ Exception -> 0x02f3 }
        if (r3 == 0) goto L_0x0321;
    L_0x031c:
        r3 = java.util.Collections.emptyMap();	 Catch:{ Exception -> 0x02f3 }
        goto L_0x0309;
    L_0x0321:
        r3 = r10.newInstance();	 Catch:{ Exception -> 0x02f3 }
        goto L_0x0309;
    L_0x0326:
        r3 = 2;
        r0 = r19;
        r0.resolveStatus = r3;	 Catch:{ all -> 0x00cd }
        r0 = r19;
        r3 = r0.contex;	 Catch:{ all -> 0x00cd }
        if (r3 == 0) goto L_0x033a;
    L_0x0331:
        r0 = r21;
        r3 = r0 instanceof java.lang.Integer;	 Catch:{ all -> 0x00cd }
        if (r3 != 0) goto L_0x033a;
    L_0x0337:
        r19.popContext();	 Catch:{ all -> 0x00cd }
    L_0x033a:
        r3 = r20.size();	 Catch:{ all -> 0x00cd }
        if (r3 <= 0) goto L_0x0355;
    L_0x0340:
        r0 = r19;
        r3 = r0.config;	 Catch:{ all -> 0x00cd }
        r0 = r20;
        r20 = com.alibaba.fastjson.util.TypeUtils.cast(r0, r10, r3);	 Catch:{ all -> 0x00cd }
        r19.parseObject(r20);	 Catch:{ all -> 0x00cd }
        if (r15 != 0) goto L_0x000f;
    L_0x034f:
        r0 = r19;
        r0.contex = r7;
        goto L_0x000f;
    L_0x0355:
        r0 = r19;
        r3 = r0.config;	 Catch:{ all -> 0x00cd }
        r3 = r3.getDeserializer(r10);	 Catch:{ all -> 0x00cd }
        r0 = r19;
        r1 = r21;
        r20 = r3.deserialze(r0, r10, r1);	 Catch:{ all -> 0x00cd }
        if (r15 != 0) goto L_0x000f;
    L_0x0367:
        r0 = r19;
        r0.contex = r7;
        goto L_0x000f;
    L_0x036d:
        r6 = "$ref";
        if (r8 != r6) goto L_0x0464;
    L_0x0372:
        if (r7 == 0) goto L_0x0464;
    L_0x0374:
        r6 = com.alibaba.fastjson.parser.Feature.DisableSpecialKeyDetect;	 Catch:{ all -> 0x00cd }
        r6 = r14.isEnabled(r6);	 Catch:{ all -> 0x00cd }
        if (r6 != 0) goto L_0x0464;
    L_0x037c:
        r3 = 4;
        r14.nextToken(r3);	 Catch:{ all -> 0x00cd }
        r3 = r14.token;	 Catch:{ all -> 0x00cd }
        r4 = 4;
        if (r3 != r4) goto L_0x0444;
    L_0x0385:
        r5 = r14.stringVal();	 Catch:{ all -> 0x00cd }
        r3 = 13;
        r14.nextToken(r3);	 Catch:{ all -> 0x00cd }
        r3 = 0;
        r4 = "@";
        r4 = r4.equals(r5);	 Catch:{ all -> 0x00cd }
        if (r4 == 0) goto L_0x03d6;
    L_0x0398:
        r0 = r19;
        r5 = r0.contex;	 Catch:{ all -> 0x00cd }
        r4 = r5.object;	 Catch:{ all -> 0x00cd }
        r6 = r4 instanceof java.lang.Object[];	 Catch:{ all -> 0x00cd }
        if (r6 != 0) goto L_0x03a6;
    L_0x03a2:
        r6 = r4 instanceof java.util.Collection;	 Catch:{ all -> 0x00cd }
        if (r6 == 0) goto L_0x03cd;
    L_0x03a6:
        r3 = r4;
    L_0x03a7:
        r20 = r3;
    L_0x03a9:
        r3 = r14.token;	 Catch:{ all -> 0x00cd }
        r4 = 13;
        if (r3 == r4) goto L_0x0437;
    L_0x03af:
        r3 = new com.alibaba.fastjson.JSONException;	 Catch:{ all -> 0x00cd }
        r4 = new java.lang.StringBuilder;	 Catch:{ all -> 0x00cd }
        r4.<init>();	 Catch:{ all -> 0x00cd }
        r5 = "syntax error, ";
        r4 = r4.append(r5);	 Catch:{ all -> 0x00cd }
        r5 = r14.info();	 Catch:{ all -> 0x00cd }
        r4 = r4.append(r5);	 Catch:{ all -> 0x00cd }
        r4 = r4.toString();	 Catch:{ all -> 0x00cd }
        r3.<init>(r4);	 Catch:{ all -> 0x00cd }
        throw r3;	 Catch:{ all -> 0x00cd }
    L_0x03cd:
        r4 = r5.parent;	 Catch:{ all -> 0x00cd }
        if (r4 == 0) goto L_0x03a7;
    L_0x03d1:
        r3 = r5.parent;	 Catch:{ all -> 0x00cd }
        r3 = r3.object;	 Catch:{ all -> 0x00cd }
        goto L_0x03a7;
    L_0x03d6:
        r4 = "..";
        r4 = r4.equals(r5);	 Catch:{ all -> 0x00cd }
        if (r4 == 0) goto L_0x03fa;
    L_0x03df:
        r4 = r7.object;	 Catch:{ all -> 0x00cd }
        if (r4 == 0) goto L_0x03e8;
    L_0x03e3:
        r3 = r7.object;	 Catch:{ all -> 0x00cd }
        r20 = r3;
        goto L_0x03a9;
    L_0x03e8:
        r4 = new com.alibaba.fastjson.parser.DefaultJSONParser$ResolveTask;	 Catch:{ all -> 0x00cd }
        r4.<init>(r7, r5);	 Catch:{ all -> 0x00cd }
        r0 = r19;
        r0.addResolveTask(r4);	 Catch:{ all -> 0x00cd }
        r4 = 1;
        r0 = r19;
        r0.resolveStatus = r4;	 Catch:{ all -> 0x00cd }
        r20 = r3;
        goto L_0x03a9;
    L_0x03fa:
        r4 = "$";
        r4 = r4.equals(r5);	 Catch:{ all -> 0x00cd }
        if (r4 == 0) goto L_0x0424;
    L_0x0403:
        r4 = r7;
    L_0x0404:
        r6 = r4.parent;	 Catch:{ all -> 0x00cd }
        if (r6 == 0) goto L_0x040b;
    L_0x0408:
        r4 = r4.parent;	 Catch:{ all -> 0x00cd }
        goto L_0x0404;
    L_0x040b:
        r6 = r4.object;	 Catch:{ all -> 0x00cd }
        if (r6 == 0) goto L_0x0414;
    L_0x040f:
        r3 = r4.object;	 Catch:{ all -> 0x00cd }
    L_0x0411:
        r20 = r3;
        goto L_0x03a9;
    L_0x0414:
        r6 = new com.alibaba.fastjson.parser.DefaultJSONParser$ResolveTask;	 Catch:{ all -> 0x00cd }
        r6.<init>(r4, r5);	 Catch:{ all -> 0x00cd }
        r0 = r19;
        r0.addResolveTask(r6);	 Catch:{ all -> 0x00cd }
        r4 = 1;
        r0 = r19;
        r0.resolveStatus = r4;	 Catch:{ all -> 0x00cd }
        goto L_0x0411;
    L_0x0424:
        r4 = new com.alibaba.fastjson.parser.DefaultJSONParser$ResolveTask;	 Catch:{ all -> 0x00cd }
        r4.<init>(r7, r5);	 Catch:{ all -> 0x00cd }
        r0 = r19;
        r0.addResolveTask(r4);	 Catch:{ all -> 0x00cd }
        r4 = 1;
        r0 = r19;
        r0.resolveStatus = r4;	 Catch:{ all -> 0x00cd }
        r20 = r3;
        goto L_0x03a9;
    L_0x0437:
        r3 = 16;
        r14.nextToken(r3);	 Catch:{ all -> 0x00cd }
        if (r15 != 0) goto L_0x000f;
    L_0x043e:
        r0 = r19;
        r0.contex = r7;
        goto L_0x000f;
    L_0x0444:
        r3 = new com.alibaba.fastjson.JSONException;	 Catch:{ all -> 0x00cd }
        r4 = new java.lang.StringBuilder;	 Catch:{ all -> 0x00cd }
        r4.<init>();	 Catch:{ all -> 0x00cd }
        r5 = "illegal ref, ";
        r4 = r4.append(r5);	 Catch:{ all -> 0x00cd }
        r5 = r14.token;	 Catch:{ all -> 0x00cd }
        r5 = com.alibaba.fastjson.parser.JSONToken.name(r5);	 Catch:{ all -> 0x00cd }
        r4 = r4.append(r5);	 Catch:{ all -> 0x00cd }
        r4 = r4.toString();	 Catch:{ all -> 0x00cd }
        r3.<init>(r4);	 Catch:{ all -> 0x00cd }
        throw r3;	 Catch:{ all -> 0x00cd }
    L_0x0464:
        if (r15 != 0) goto L_0x07b1;
    L_0x0466:
        if (r9 != 0) goto L_0x07b1;
    L_0x0468:
        r0 = r19;
        r6 = r0.contex;	 Catch:{ all -> 0x00cd }
        r0 = r19;
        r1 = r20;
        r2 = r21;
        r6 = r0.setContext(r6, r1, r2);	 Catch:{ all -> 0x00cd }
        if (r7 != 0) goto L_0x07ae;
    L_0x0478:
        r7 = 1;
        r13 = r7;
        r7 = r6;
    L_0x047b:
        r6 = 34;
        if (r10 != r6) goto L_0x04cb;
    L_0x047f:
        r6 = 34;
        r6 = r14.scanStringValue(r6);	 Catch:{ all -> 0x00cd }
        if (r5 == 0) goto L_0x049c;
    L_0x0487:
        r9 = new com.alibaba.fastjson.parser.JSONLexer;	 Catch:{ all -> 0x00cd }
        r9.<init>(r6);	 Catch:{ all -> 0x00cd }
        r10 = 1;
        r10 = r9.scanISO8601DateIfMatch(r10);	 Catch:{ all -> 0x00cd }
        if (r10 == 0) goto L_0x0499;
    L_0x0493:
        r6 = r9.calendar;	 Catch:{ all -> 0x00cd }
        r6 = r6.getTime();	 Catch:{ all -> 0x00cd }
    L_0x0499:
        r9.close();	 Catch:{ all -> 0x00cd }
    L_0x049c:
        if (r4 == 0) goto L_0x04c5;
    L_0x049e:
        r4.put(r8, r6);	 Catch:{ all -> 0x00cd }
    L_0x04a1:
        r6 = r14.ch;	 Catch:{ all -> 0x00cd }
        r8 = 44;
        if (r6 == r8) goto L_0x04b0;
    L_0x04a7:
        r8 = 125; // 0x7d float:1.75E-43 double:6.2E-322;
        if (r6 == r8) goto L_0x04b0;
    L_0x04ab:
        r14.skipWhitespace();	 Catch:{ all -> 0x00cd }
        r6 = r14.ch;	 Catch:{ all -> 0x00cd }
    L_0x04b0:
        r8 = 44;
        if (r6 != r8) goto L_0x06f6;
    L_0x04b4:
        r6 = r14.bp;	 Catch:{ all -> 0x00cd }
        r6 = r6 + 1;
        r14.bp = r6;	 Catch:{ all -> 0x00cd }
        r8 = r14.len;	 Catch:{ all -> 0x00cd }
        if (r6 < r8) goto L_0x06ee;
    L_0x04be:
        r6 = 26;
    L_0x04c0:
        r14.ch = r6;	 Catch:{ all -> 0x00cd }
        r9 = r13;
        goto L_0x0066;
    L_0x04c5:
        r0 = r20;
        r0.put(r8, r6);	 Catch:{ all -> 0x00cd }
        goto L_0x04a1;
    L_0x04cb:
        r6 = 48;
        if (r10 < r6) goto L_0x04d3;
    L_0x04cf:
        r6 = 57;
        if (r10 <= r6) goto L_0x04d7;
    L_0x04d3:
        r6 = 45;
        if (r10 != r6) goto L_0x04df;
    L_0x04d7:
        r6 = r14.scanNumberValue();	 Catch:{ all -> 0x00cd }
        r4.put(r8, r6);	 Catch:{ all -> 0x00cd }
        goto L_0x04a1;
    L_0x04df:
        r6 = 91;
        if (r10 != r6) goto L_0x0562;
    L_0x04e3:
        r6 = 14;
        r14.token = r6;	 Catch:{ all -> 0x00cd }
        r6 = r14.bp;	 Catch:{ all -> 0x00cd }
        r6 = r6 + 1;
        r14.bp = r6;	 Catch:{ all -> 0x00cd }
        r9 = r14.len;	 Catch:{ all -> 0x00cd }
        if (r6 < r9) goto L_0x052e;
    L_0x04f1:
        r6 = 26;
    L_0x04f3:
        r14.ch = r6;	 Catch:{ all -> 0x00cd }
        r9 = new java.util.ArrayList;	 Catch:{ all -> 0x00cd }
        r9.<init>();	 Catch:{ all -> 0x00cd }
        if (r21 == 0) goto L_0x0535;
    L_0x04fc:
        r6 = r21.getClass();	 Catch:{ all -> 0x00cd }
        r10 = java.lang.Integer.class;
        if (r6 != r10) goto L_0x0535;
    L_0x0504:
        r6 = 1;
    L_0x0505:
        if (r6 != 0) goto L_0x050c;
    L_0x0507:
        r0 = r19;
        r0.setContext(r7);	 Catch:{ all -> 0x00cd }
    L_0x050c:
        r0 = r19;
        r0.parseArray(r9, r8);	 Catch:{ all -> 0x00cd }
        r6 = new com.alibaba.fastjson.JSONArray;	 Catch:{ all -> 0x00cd }
        r6.<init>(r9);	 Catch:{ all -> 0x00cd }
        if (r4 == 0) goto L_0x0537;
    L_0x0518:
        r4.put(r8, r6);	 Catch:{ all -> 0x00cd }
    L_0x051b:
        r6 = r14.token;	 Catch:{ all -> 0x00cd }
        r8 = 13;
        if (r6 != r8) goto L_0x053d;
    L_0x0521:
        r3 = 16;
        r14.nextToken(r3);	 Catch:{ all -> 0x00cd }
        if (r15 != 0) goto L_0x000f;
    L_0x0528:
        r0 = r19;
        r0.contex = r7;
        goto L_0x000f;
    L_0x052e:
        r9 = r14.text;	 Catch:{ all -> 0x00cd }
        r6 = r9.charAt(r6);	 Catch:{ all -> 0x00cd }
        goto L_0x04f3;
    L_0x0535:
        r6 = 0;
        goto L_0x0505;
    L_0x0537:
        r0 = r20;
        r0.put(r8, r6);	 Catch:{ all -> 0x00cd }
        goto L_0x051b;
    L_0x053d:
        r8 = 16;
        if (r6 != r8) goto L_0x0544;
    L_0x0541:
        r9 = r13;
        goto L_0x0066;
    L_0x0544:
        r3 = new com.alibaba.fastjson.JSONException;	 Catch:{ all -> 0x00cd }
        r4 = new java.lang.StringBuilder;	 Catch:{ all -> 0x00cd }
        r4.<init>();	 Catch:{ all -> 0x00cd }
        r5 = "syntax error, ";
        r4 = r4.append(r5);	 Catch:{ all -> 0x00cd }
        r5 = r14.info();	 Catch:{ all -> 0x00cd }
        r4 = r4.append(r5);	 Catch:{ all -> 0x00cd }
        r4 = r4.toString();	 Catch:{ all -> 0x00cd }
        r3.<init>(r4);	 Catch:{ all -> 0x00cd }
        throw r3;	 Catch:{ all -> 0x00cd }
    L_0x0562:
        r6 = 123; // 0x7b float:1.72E-43 double:6.1E-322;
        if (r10 != r6) goto L_0x0654;
    L_0x0566:
        r6 = r14.bp;	 Catch:{ all -> 0x00cd }
        r6 = r6 + 1;
        r14.bp = r6;	 Catch:{ all -> 0x00cd }
        r9 = r14.len;	 Catch:{ all -> 0x00cd }
        if (r6 < r9) goto L_0x0617;
    L_0x0570:
        r6 = 26;
    L_0x0572:
        r14.ch = r6;	 Catch:{ all -> 0x00cd }
        r6 = 12;
        r14.token = r6;	 Catch:{ all -> 0x00cd }
        r0 = r21;
        r0 = r0 instanceof java.lang.Integer;	 Catch:{ all -> 0x00cd }
        r16 = r0;
        r6 = r14.features;	 Catch:{ all -> 0x00cd }
        r9 = com.alibaba.fastjson.parser.Feature.OrderedField;	 Catch:{ all -> 0x00cd }
        r9 = r9.mask;	 Catch:{ all -> 0x00cd }
        r6 = r6 & r9;
        if (r6 == 0) goto L_0x061f;
    L_0x0587:
        r6 = new com.alibaba.fastjson.JSONObject;	 Catch:{ all -> 0x00cd }
        r9 = new java.util.LinkedHashMap;	 Catch:{ all -> 0x00cd }
        r9.<init>();	 Catch:{ all -> 0x00cd }
        r6.<init>(r9);	 Catch:{ all -> 0x00cd }
        r12 = r6;
    L_0x0592:
        r6 = 0;
        if (r15 != 0) goto L_0x07ab;
    L_0x0595:
        if (r16 != 0) goto L_0x07ab;
    L_0x0597:
        r0 = r19;
        r6 = r0.setContext(r7, r12, r8);	 Catch:{ all -> 0x00cd }
        r11 = r6;
    L_0x059e:
        r9 = 0;
        r6 = 0;
        r0 = r19;
        r10 = r0.fieldTypeResolver;	 Catch:{ all -> 0x00cd }
        if (r10 == 0) goto L_0x07a4;
    L_0x05a6:
        if (r8 == 0) goto L_0x0627;
    L_0x05a8:
        r10 = r8.toString();	 Catch:{ all -> 0x00cd }
    L_0x05ac:
        r0 = r19;
        r0 = r0.fieldTypeResolver;	 Catch:{ all -> 0x00cd }
        r17 = r0;
        r0 = r17;
        r1 = r20;
        r10 = r0.resolve(r1, r10);	 Catch:{ all -> 0x00cd }
        if (r10 == 0) goto L_0x07a4;
    L_0x05bc:
        r0 = r19;
        r6 = r0.config;	 Catch:{ all -> 0x00cd }
        r6 = r6.getDeserializer(r10);	 Catch:{ all -> 0x00cd }
        r0 = r19;
        r9 = r6.deserialze(r0, r10, r8);	 Catch:{ all -> 0x00cd }
        r6 = 1;
        r18 = r6;
        r6 = r9;
        r9 = r18;
    L_0x05d0:
        if (r9 != 0) goto L_0x05d8;
    L_0x05d2:
        r0 = r19;
        r6 = r0.parseObject(r12, r8);	 Catch:{ all -> 0x00cd }
    L_0x05d8:
        if (r11 == 0) goto L_0x05e0;
    L_0x05da:
        if (r12 == r6) goto L_0x05e0;
    L_0x05dc:
        r0 = r20;
        r11.object = r0;	 Catch:{ all -> 0x00cd }
    L_0x05e0:
        r0 = r19;
        r9 = r0.resolveStatus;	 Catch:{ all -> 0x00cd }
        r10 = 1;
        if (r9 != r10) goto L_0x05f2;
    L_0x05e7:
        r9 = r8.toString();	 Catch:{ all -> 0x00cd }
        r0 = r19;
        r1 = r20;
        r0.checkMapResolve(r1, r9);	 Catch:{ all -> 0x00cd }
    L_0x05f2:
        if (r4 == 0) goto L_0x0629;
    L_0x05f4:
        r4.put(r8, r6);	 Catch:{ all -> 0x00cd }
    L_0x05f7:
        if (r16 == 0) goto L_0x05fe;
    L_0x05f9:
        r0 = r19;
        r0.setContext(r7, r6, r8);	 Catch:{ all -> 0x00cd }
    L_0x05fe:
        r6 = r14.token;	 Catch:{ all -> 0x00cd }
        r8 = 13;
        if (r6 != r8) goto L_0x062f;
    L_0x0604:
        r3 = 16;
        r14.nextToken(r3);	 Catch:{ all -> 0x00cd }
        if (r15 != 0) goto L_0x060f;
    L_0x060b:
        r0 = r19;
        r0.contex = r7;	 Catch:{ all -> 0x00cd }
    L_0x060f:
        if (r15 != 0) goto L_0x000f;
    L_0x0611:
        r0 = r19;
        r0.contex = r7;
        goto L_0x000f;
    L_0x0617:
        r9 = r14.text;	 Catch:{ all -> 0x00cd }
        r6 = r9.charAt(r6);	 Catch:{ all -> 0x00cd }
        goto L_0x0572;
    L_0x061f:
        r6 = new com.alibaba.fastjson.JSONObject;	 Catch:{ all -> 0x00cd }
        r6.<init>();	 Catch:{ all -> 0x00cd }
        r12 = r6;
        goto L_0x0592;
    L_0x0627:
        r10 = 0;
        goto L_0x05ac;
    L_0x0629:
        r0 = r20;
        r0.put(r8, r6);	 Catch:{ all -> 0x00cd }
        goto L_0x05f7;
    L_0x062f:
        r8 = 16;
        if (r6 != r8) goto L_0x0636;
    L_0x0633:
        r9 = r13;
        goto L_0x0066;
    L_0x0636:
        r3 = new com.alibaba.fastjson.JSONException;	 Catch:{ all -> 0x00cd }
        r4 = new java.lang.StringBuilder;	 Catch:{ all -> 0x00cd }
        r4.<init>();	 Catch:{ all -> 0x00cd }
        r5 = "syntax error, ";
        r4 = r4.append(r5);	 Catch:{ all -> 0x00cd }
        r5 = r14.info();	 Catch:{ all -> 0x00cd }
        r4 = r4.append(r5);	 Catch:{ all -> 0x00cd }
        r4 = r4.toString();	 Catch:{ all -> 0x00cd }
        r3.<init>(r4);	 Catch:{ all -> 0x00cd }
        throw r3;	 Catch:{ all -> 0x00cd }
    L_0x0654:
        r6 = 116; // 0x74 float:1.63E-43 double:5.73E-322;
        if (r10 != r6) goto L_0x0677;
    L_0x0658:
        r6 = r14.text;	 Catch:{ all -> 0x00cd }
        r9 = "true";
        r10 = r14.bp;	 Catch:{ all -> 0x00cd }
        r6 = r6.startsWith(r9, r10);	 Catch:{ all -> 0x00cd }
        if (r6 == 0) goto L_0x04a1;
    L_0x0665:
        r6 = r14.bp;	 Catch:{ all -> 0x00cd }
        r6 = r6 + 3;
        r14.bp = r6;	 Catch:{ all -> 0x00cd }
        r14.next();	 Catch:{ all -> 0x00cd }
        r6 = java.lang.Boolean.TRUE;	 Catch:{ all -> 0x00cd }
        r0 = r20;
        r0.put(r8, r6);	 Catch:{ all -> 0x00cd }
        goto L_0x04a1;
    L_0x0677:
        r6 = 102; // 0x66 float:1.43E-43 double:5.04E-322;
        if (r10 != r6) goto L_0x069a;
    L_0x067b:
        r6 = r14.text;	 Catch:{ all -> 0x00cd }
        r9 = "false";
        r10 = r14.bp;	 Catch:{ all -> 0x00cd }
        r6 = r6.startsWith(r9, r10);	 Catch:{ all -> 0x00cd }
        if (r6 == 0) goto L_0x04a1;
    L_0x0688:
        r6 = r14.bp;	 Catch:{ all -> 0x00cd }
        r6 = r6 + 4;
        r14.bp = r6;	 Catch:{ all -> 0x00cd }
        r14.next();	 Catch:{ all -> 0x00cd }
        r6 = java.lang.Boolean.FALSE;	 Catch:{ all -> 0x00cd }
        r0 = r20;
        r0.put(r8, r6);	 Catch:{ all -> 0x00cd }
        goto L_0x04a1;
    L_0x069a:
        r14.nextToken();	 Catch:{ all -> 0x00cd }
        r9 = r19.parse();	 Catch:{ all -> 0x00cd }
        r6 = r20.getClass();	 Catch:{ all -> 0x00cd }
        r10 = com.alibaba.fastjson.JSONObject.class;
        if (r6 != r10) goto L_0x06c5;
    L_0x06a9:
        r6 = r8.toString();	 Catch:{ all -> 0x00cd }
    L_0x06ad:
        r0 = r20;
        r0.put(r6, r9);	 Catch:{ all -> 0x00cd }
        r6 = r14.token;	 Catch:{ all -> 0x00cd }
        r8 = 13;
        if (r6 != r8) goto L_0x06c7;
    L_0x06b8:
        r3 = 16;
        r14.nextToken(r3);	 Catch:{ all -> 0x00cd }
        if (r15 != 0) goto L_0x000f;
    L_0x06bf:
        r0 = r19;
        r0.contex = r7;
        goto L_0x000f;
    L_0x06c5:
        r6 = r8;
        goto L_0x06ad;
    L_0x06c7:
        r6 = r14.token;	 Catch:{ all -> 0x00cd }
        r8 = 16;
        if (r6 != r8) goto L_0x06d0;
    L_0x06cd:
        r9 = r13;
        goto L_0x0066;
    L_0x06d0:
        r3 = new com.alibaba.fastjson.JSONException;	 Catch:{ all -> 0x00cd }
        r4 = new java.lang.StringBuilder;	 Catch:{ all -> 0x00cd }
        r4.<init>();	 Catch:{ all -> 0x00cd }
        r5 = "syntax error, ";
        r4 = r4.append(r5);	 Catch:{ all -> 0x00cd }
        r5 = r14.info();	 Catch:{ all -> 0x00cd }
        r4 = r4.append(r5);	 Catch:{ all -> 0x00cd }
        r4 = r4.toString();	 Catch:{ all -> 0x00cd }
        r3.<init>(r4);	 Catch:{ all -> 0x00cd }
        throw r3;	 Catch:{ all -> 0x00cd }
    L_0x06ee:
        r8 = r14.text;	 Catch:{ all -> 0x00cd }
        r6 = r8.charAt(r6);	 Catch:{ all -> 0x00cd }
        goto L_0x04c0;
    L_0x06f6:
        r3 = 125; // 0x7d float:1.75E-43 double:6.2E-322;
        if (r6 != r3) goto L_0x0786;
    L_0x06fa:
        r3 = r14.bp;	 Catch:{ all -> 0x00cd }
        r3 = r3 + 1;
        r14.bp = r3;	 Catch:{ all -> 0x00cd }
        r4 = r14.len;	 Catch:{ all -> 0x00cd }
        if (r3 < r4) goto L_0x0738;
    L_0x0704:
        r3 = 26;
    L_0x0706:
        r14.ch = r3;	 Catch:{ all -> 0x00cd }
        r4 = 0;
        r14.sp = r4;	 Catch:{ all -> 0x00cd }
        r4 = 44;
        if (r3 != r4) goto L_0x0746;
    L_0x070f:
        r3 = r14.bp;	 Catch:{ all -> 0x00cd }
        r3 = r3 + 1;
        r14.bp = r3;	 Catch:{ all -> 0x00cd }
        r4 = r14.len;	 Catch:{ all -> 0x00cd }
        if (r3 < r4) goto L_0x073f;
    L_0x0719:
        r3 = 26;
    L_0x071b:
        r14.ch = r3;	 Catch:{ all -> 0x00cd }
        r3 = 16;
        r14.token = r3;	 Catch:{ all -> 0x00cd }
    L_0x0721:
        if (r15 != 0) goto L_0x0730;
    L_0x0723:
        r0 = r19;
        r3 = r0.contex;	 Catch:{ all -> 0x00cd }
        r0 = r19;
        r1 = r20;
        r2 = r21;
        r0.setContext(r3, r1, r2);	 Catch:{ all -> 0x00cd }
    L_0x0730:
        if (r15 != 0) goto L_0x000f;
    L_0x0732:
        r0 = r19;
        r0.contex = r7;
        goto L_0x000f;
    L_0x0738:
        r4 = r14.text;	 Catch:{ all -> 0x00cd }
        r3 = r4.charAt(r3);	 Catch:{ all -> 0x00cd }
        goto L_0x0706;
    L_0x073f:
        r4 = r14.text;	 Catch:{ all -> 0x00cd }
        r3 = r4.charAt(r3);	 Catch:{ all -> 0x00cd }
        goto L_0x071b;
    L_0x0746:
        r4 = 125; // 0x7d float:1.75E-43 double:6.2E-322;
        if (r3 != r4) goto L_0x0764;
    L_0x074a:
        r3 = r14.bp;	 Catch:{ all -> 0x00cd }
        r3 = r3 + 1;
        r14.bp = r3;	 Catch:{ all -> 0x00cd }
        r4 = r14.len;	 Catch:{ all -> 0x00cd }
        if (r3 < r4) goto L_0x075d;
    L_0x0754:
        r3 = 26;
    L_0x0756:
        r14.ch = r3;	 Catch:{ all -> 0x00cd }
        r3 = 13;
        r14.token = r3;	 Catch:{ all -> 0x00cd }
        goto L_0x0721;
    L_0x075d:
        r4 = r14.text;	 Catch:{ all -> 0x00cd }
        r3 = r4.charAt(r3);	 Catch:{ all -> 0x00cd }
        goto L_0x0756;
    L_0x0764:
        r4 = 93;
        if (r3 != r4) goto L_0x0782;
    L_0x0768:
        r3 = r14.bp;	 Catch:{ all -> 0x00cd }
        r3 = r3 + 1;
        r14.bp = r3;	 Catch:{ all -> 0x00cd }
        r4 = r14.len;	 Catch:{ all -> 0x00cd }
        if (r3 < r4) goto L_0x077b;
    L_0x0772:
        r3 = 26;
    L_0x0774:
        r14.ch = r3;	 Catch:{ all -> 0x00cd }
        r3 = 15;
        r14.token = r3;	 Catch:{ all -> 0x00cd }
        goto L_0x0721;
    L_0x077b:
        r4 = r14.text;	 Catch:{ all -> 0x00cd }
        r3 = r4.charAt(r3);	 Catch:{ all -> 0x00cd }
        goto L_0x0774;
    L_0x0782:
        r14.nextToken();	 Catch:{ all -> 0x00cd }
        goto L_0x0721;
    L_0x0786:
        r3 = new com.alibaba.fastjson.JSONException;	 Catch:{ all -> 0x00cd }
        r4 = new java.lang.StringBuilder;	 Catch:{ all -> 0x00cd }
        r4.<init>();	 Catch:{ all -> 0x00cd }
        r5 = "syntax error, ";
        r4 = r4.append(r5);	 Catch:{ all -> 0x00cd }
        r5 = r14.info();	 Catch:{ all -> 0x00cd }
        r4 = r4.append(r5);	 Catch:{ all -> 0x00cd }
        r4 = r4.toString();	 Catch:{ all -> 0x00cd }
        r3.<init>(r4);	 Catch:{ all -> 0x00cd }
        throw r3;	 Catch:{ all -> 0x00cd }
    L_0x07a4:
        r18 = r6;
        r6 = r9;
        r9 = r18;
        goto L_0x05d0;
    L_0x07ab:
        r11 = r6;
        goto L_0x059e;
    L_0x07ae:
        r6 = r7;
        goto L_0x0478;
    L_0x07b1:
        r13 = r9;
        goto L_0x047b;
    L_0x07b4:
        r3 = r4;
        goto L_0x02fe;
    L_0x07b7:
        r10 = r6;
        goto L_0x0271;
    L_0x07ba:
        r6 = r10;
        goto L_0x01f5;
    L_0x07bd:
        r8 = r6;
        r6 = r10;
        goto L_0x01f5;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.parser.DefaultJSONParser.parseObject(java.util.Map, java.lang.Object):java.lang.Object");
    }

    public <T> T parseObject(Class<T> cls) {
        return parseObject((Type) cls, null);
    }

    public <T> T parseObject(Type type) {
        return parseObject(type, null);
    }

    public <T> T parseObject(Type type, Object obj) {
        if (this.lexer.token == 8) {
            this.lexer.nextToken();
            return null;
        }
        if (this.lexer.token == 4) {
            if (type == byte[].class) {
                T bytesValue = this.lexer.bytesValue();
                this.lexer.nextToken();
                return bytesValue;
            } else if (type == char[].class) {
                String stringVal = this.lexer.stringVal();
                this.lexer.nextToken();
                return stringVal.toCharArray();
            }
        }
        try {
            return this.config.getDeserializer(type).deserialze(this, type, obj);
        } catch (JSONException e) {
            throw e;
        } catch (Throwable e2) {
            throw new JSONException(e2.getMessage(), e2);
        }
    }

    public <T> List<T> parseArray(Class<T> cls) {
        Collection arrayList = new ArrayList();
        parseArray((Class) cls, arrayList);
        return arrayList;
    }

    public void parseArray(Class<?> cls, Collection collection) {
        parseArray((Type) cls, collection);
    }

    public void parseArray(Type type, Collection collection) {
        parseArray(type, collection, null);
    }

    public void parseArray(Type type, Collection collection, Object obj) {
        if (this.lexer.token == 21 || this.lexer.token == 22) {
            this.lexer.nextToken();
        }
        if (this.lexer.token != 14) {
            throw new JSONException("exepct '[', but " + JSONToken.name(this.lexer.token) + ", " + this.lexer.info());
        }
        ObjectDeserializer objectDeserializer;
        if (Integer.TYPE == type) {
            objectDeserializer = IntegerCodec.instance;
            this.lexer.nextToken(2);
        } else if (String.class == type) {
            objectDeserializer = StringCodec.instance;
            this.lexer.nextToken(4);
        } else {
            objectDeserializer = this.config.getDeserializer(type);
            this.lexer.nextToken(12);
        }
        ParseContext parseContext = this.contex;
        if (!this.lexer.disableCircularReferenceDetect) {
            setContext(this.contex, collection, obj);
        }
        int i = 0;
        while (true) {
            if (this.lexer.token == 16) {
                this.lexer.nextToken();
            } else {
                try {
                    if (this.lexer.token == 15) {
                        break;
                    }
                    if (Integer.TYPE == type) {
                        collection.add(IntegerCodec.instance.deserialze(this, null, null));
                    } else if (String.class == type) {
                        if (this.lexer.token == 4) {
                            r1 = this.lexer.stringVal();
                            this.lexer.nextToken(16);
                        } else {
                            r1 = parse();
                            if (r1 == null) {
                                r1 = null;
                            } else {
                                r1 = r1.toString();
                            }
                        }
                        collection.add(r1);
                    } else {
                        if (this.lexer.token == 8) {
                            this.lexer.nextToken();
                            r1 = null;
                        } else {
                            r1 = objectDeserializer.deserialze(this, type, Integer.valueOf(i));
                        }
                        collection.add(r1);
                        if (this.resolveStatus == 1) {
                            checkListResolve(collection);
                        }
                    }
                    if (this.lexer.token == 16) {
                        this.lexer.nextToken();
                    }
                    i++;
                } finally {
                    this.contex = parseContext;
                }
            }
        }
        this.lexer.nextToken(16);
    }

    public Object[] parseArray(Type[] typeArr) {
        if (this.lexer.token == 8) {
            this.lexer.nextToken(16);
            return null;
        } else if (this.lexer.token != 14) {
            throw new JSONException("syntax error, " + this.lexer.info());
        } else {
            Object[] objArr = new Object[typeArr.length];
            if (typeArr.length == 0) {
                this.lexer.nextToken(15);
                if (this.lexer.token != 15) {
                    throw new JSONException("syntax error, " + this.lexer.info());
                }
                this.lexer.nextToken(16);
                return new Object[0];
            }
            this.lexer.nextToken(2);
            int i = 0;
            while (i < typeArr.length) {
                String str;
                if (this.lexer.token == 8) {
                    this.lexer.nextToken(16);
                    str = null;
                } else {
                    Type type = typeArr[i];
                    if (type == Integer.TYPE || type == Integer.class) {
                        if (this.lexer.token == 2) {
                            str = Integer.valueOf(this.lexer.intValue());
                            this.lexer.nextToken(16);
                        } else {
                            str = TypeUtils.cast(parse(), type, this.config);
                        }
                    } else if (type != String.class) {
                        boolean isArray;
                        Type componentType;
                        if (i == typeArr.length - 1 && (type instanceof Class)) {
                            Class cls = (Class) type;
                            isArray = cls.isArray();
                            componentType = cls.getComponentType();
                        } else {
                            componentType = null;
                            isArray = false;
                        }
                        if (!isArray || this.lexer.token == 14) {
                            str = this.config.getDeserializer(type).deserialze(this, type, null);
                        } else {
                            Object arrayList = new ArrayList();
                            ObjectDeserializer deserializer = this.config.getDeserializer(componentType);
                            if (this.lexer.token != 15) {
                                while (true) {
                                    arrayList.add(deserializer.deserialze(this, type, null));
                                    if (this.lexer.token != 16) {
                                        break;
                                    }
                                    this.lexer.nextToken(12);
                                }
                                if (this.lexer.token != 15) {
                                    throw new JSONException("syntax error, " + this.lexer.info());
                                }
                            }
                            str = TypeUtils.cast(arrayList, type, this.config);
                        }
                    } else if (this.lexer.token == 4) {
                        str = this.lexer.stringVal();
                        this.lexer.nextToken(16);
                    } else {
                        str = TypeUtils.cast(parse(), type, this.config);
                    }
                }
                objArr[i] = str;
                if (this.lexer.token == 15) {
                    break;
                } else if (this.lexer.token != 16) {
                    throw new JSONException("syntax error, " + this.lexer.info());
                } else {
                    if (i == typeArr.length - 1) {
                        this.lexer.nextToken(15);
                    } else {
                        this.lexer.nextToken(2);
                    }
                    i++;
                }
            }
            if (this.lexer.token != 15) {
                throw new JSONException("syntax error, " + this.lexer.info());
            }
            this.lexer.nextToken(16);
            return objArr;
        }
    }

    public void parseObject(Object obj) {
        JavaBeanDeserializer javaBeanDeserializer;
        Object obj2 = obj.getClass();
        ObjectDeserializer deserializer = this.config.getDeserializer(obj2);
        if (deserializer instanceof JavaBeanDeserializer) {
            javaBeanDeserializer = (JavaBeanDeserializer) deserializer;
        } else {
            javaBeanDeserializer = null;
        }
        int i = this.lexer.token;
        if (i == 12 || i == 16) {
            while (true) {
                FieldDeserializer fieldDeserializer;
                String scanSymbol = this.lexer.scanSymbol(this.symbolTable);
                if (scanSymbol == null) {
                    if (this.lexer.token == 13) {
                        this.lexer.nextToken(16);
                        return;
                    } else if (this.lexer.token == 16) {
                        continue;
                    }
                }
                if (javaBeanDeserializer != null) {
                    fieldDeserializer = javaBeanDeserializer.getFieldDeserializer(scanSymbol);
                } else {
                    fieldDeserializer = null;
                }
                if (fieldDeserializer != null) {
                    Object deserialze;
                    Class cls = fieldDeserializer.fieldInfo.fieldClass;
                    Type type = fieldDeserializer.fieldInfo.fieldType;
                    if (cls == Integer.TYPE) {
                        this.lexer.nextTokenWithChar(':');
                        deserialze = IntegerCodec.instance.deserialze(this, type, null);
                    } else if (cls == String.class) {
                        this.lexer.nextTokenWithChar(':');
                        deserialze = parseString();
                    } else if (cls == Long.TYPE) {
                        this.lexer.nextTokenWithChar(':');
                        deserialze = IntegerCodec.instance.deserialze(this, type, null);
                    } else {
                        ObjectDeserializer deserializer2 = this.config.getDeserializer(cls, type);
                        this.lexer.nextTokenWithChar(':');
                        deserialze = deserializer2.deserialze(this, type, null);
                    }
                    fieldDeserializer.setValue(obj, deserialze);
                    if (this.lexer.token != 16 && this.lexer.token == 13) {
                        this.lexer.nextToken(16);
                        return;
                    }
                } else if ((this.lexer.features & Feature.IgnoreNotMatch.mask) == 0) {
                    throw new JSONException("setter not found, class " + obj2.getName() + ", property " + scanSymbol);
                } else {
                    this.lexer.nextTokenWithChar(':');
                    parse();
                    if (this.lexer.token == 13) {
                        this.lexer.nextToken();
                        return;
                    }
                }
            }
        } else {
            throw new JSONException("syntax error, expect {, actual " + JSONToken.name(i));
        }
    }

    public Object parseArrayWithType(Type type) {
        if (this.lexer.token == 8) {
            this.lexer.nextToken();
            return null;
        }
        Type[] actualTypeArguments = ((ParameterizedType) type).getActualTypeArguments();
        if (actualTypeArguments.length != 1) {
            throw new JSONException("not support type " + type);
        }
        Type type2 = actualTypeArguments[0];
        Collection arrayList;
        if (type2 instanceof Class) {
            arrayList = new ArrayList();
            parseArray((Class) type2, arrayList);
            return arrayList;
        } else if (type2 instanceof WildcardType) {
            WildcardType wildcardType = (WildcardType) type2;
            Object obj = wildcardType.getUpperBounds()[0];
            if (!Object.class.equals(obj)) {
                r2 = new ArrayList();
                parseArray((Class) obj, r2);
                return r2;
            } else if (wildcardType.getLowerBounds().length == 0) {
                return parse();
            } else {
                throw new JSONException("not support type : " + type);
            }
        } else {
            if (type2 instanceof TypeVariable) {
                TypeVariable typeVariable = (TypeVariable) type2;
                Type[] bounds = typeVariable.getBounds();
                if (bounds.length != 1) {
                    throw new JSONException("not support : " + typeVariable);
                }
                Type type3 = bounds[0];
                if (type3 instanceof Class) {
                    r2 = new ArrayList();
                    parseArray((Class) type3, r2);
                    return r2;
                }
            }
            if (type2 instanceof ParameterizedType) {
                type2 = (ParameterizedType) type2;
                arrayList = new ArrayList();
                parseArray(type2, arrayList);
                return arrayList;
            }
            throw new JSONException("TODO : " + type);
        }
    }

    protected void checkListResolve(Collection collection) {
        if (collection instanceof List) {
            ResolveTask lastResolveTask = getLastResolveTask();
            lastResolveTask.fieldDeserializer = new ResolveFieldDeserializer(this, (List) collection, collection.size() - 1);
            lastResolveTask.ownerContext = this.contex;
            this.resolveStatus = 0;
            return;
        }
        ResolveTask lastResolveTask2 = getLastResolveTask();
        lastResolveTask2.fieldDeserializer = new ResolveFieldDeserializer(collection);
        lastResolveTask2.ownerContext = this.contex;
        this.resolveStatus = 0;
    }

    protected void checkMapResolve(Map map, Object obj) {
        FieldDeserializer resolveFieldDeserializer = new ResolveFieldDeserializer(map, obj);
        ResolveTask lastResolveTask = getLastResolveTask();
        lastResolveTask.fieldDeserializer = resolveFieldDeserializer;
        lastResolveTask.ownerContext = this.contex;
        this.resolveStatus = 0;
    }

    public Object parseObject(Map map) {
        return parseObject(map, null);
    }

    public JSONObject parseObject() {
        return (JSONObject) parseObject((this.lexer.features & Feature.OrderedField.mask) != 0 ? new JSONObject(new LinkedHashMap()) : new JSONObject(), null);
    }

    public final void parseArray(Collection collection) {
        parseArray(collection, null);
    }

    public final void parseArray(Collection collection, Object obj) {
        char c = JSONLexer.EOI;
        int i = this.lexer.token;
        if (i == 21 || i == 22) {
            this.lexer.nextToken();
            i = this.lexer.token;
        }
        if (i != 14) {
            throw new JSONException("syntax error, expect [, actual " + JSONToken.name(i) + ", pos " + this.lexer.pos);
        }
        boolean z = this.lexer.disableCircularReferenceDetect;
        ParseContext parseContext = this.contex;
        if (!z) {
            setContext(this.contex, collection, obj);
        }
        try {
            JSONLexer jSONLexer;
            int i2;
            Object obj2;
            char c2 = this.lexer.ch;
            if (c2 != '\"') {
                if (c2 == ']') {
                    this.lexer.next();
                    this.lexer.nextToken(16);
                    return;
                }
                if (c2 == '{') {
                    jSONLexer = this.lexer;
                    i2 = jSONLexer.bp + 1;
                    jSONLexer.bp = i2;
                    this.lexer.ch = i2 >= this.lexer.len ? JSONLexer.EOI : this.lexer.text.charAt(i2);
                    this.lexer.token = 12;
                } else {
                    this.lexer.nextToken(12);
                }
                obj2 = null;
            } else if ((this.lexer.features & Feature.AllowISO8601DateFormat.mask) == 0) {
                i = 1;
            } else {
                this.lexer.nextToken(4);
                obj2 = null;
            }
            int i3 = 0;
            while (true) {
                char c3;
                JSONLexer jSONLexer2;
                Object integerValue;
                if (obj2 != null && this.lexer.ch == '\"') {
                    String scanStringValue = this.lexer.scanStringValue('\"');
                    c3 = this.lexer.ch;
                    if (c3 == ',') {
                        jSONLexer2 = this.lexer;
                        int i4 = jSONLexer2.bp + 1;
                        jSONLexer2.bp = i4;
                        JSONLexer jSONLexer3 = this.lexer;
                        c3 = i4 >= this.lexer.len ? JSONLexer.EOI : this.lexer.text.charAt(i4);
                        jSONLexer3.ch = c3;
                        collection.add(scanStringValue);
                        if (this.resolveStatus == 1) {
                            checkListResolve(collection);
                        }
                        if (c3 == '\"') {
                            continue;
                            i3++;
                        } else {
                            this.lexer.nextToken();
                            obj2 = null;
                        }
                    } else if (c3 == ']') {
                        jSONLexer = this.lexer;
                        int i5 = jSONLexer.bp + 1;
                        jSONLexer.bp = i5;
                        jSONLexer = this.lexer;
                        if (i5 < this.lexer.len) {
                            c = this.lexer.text.charAt(i5);
                        }
                        jSONLexer.ch = c;
                        collection.add(scanStringValue);
                        if (this.resolveStatus == 1) {
                            checkListResolve(collection);
                        }
                        this.lexer.nextToken(16);
                        if (!z) {
                            this.contex = parseContext;
                            return;
                        }
                        return;
                    } else {
                        this.lexer.nextToken();
                    }
                }
                i2 = this.lexer.token;
                while (i2 == 16) {
                    this.lexer.nextToken();
                    i2 = this.lexer.token;
                }
                switch (i2) {
                    case 2:
                        integerValue = this.lexer.integerValue();
                        this.lexer.nextToken(16);
                        break;
                    case 3:
                        if ((this.lexer.features & Feature.UseBigDecimal.mask) != 0) {
                            integerValue = this.lexer.decimalValue(true);
                        } else {
                            integerValue = this.lexer.decimalValue(false);
                        }
                        this.lexer.nextToken(16);
                        break;
                    case 4:
                        integerValue = this.lexer.stringVal();
                        this.lexer.nextToken(16);
                        if ((this.lexer.features & Feature.AllowISO8601DateFormat.mask) != 0) {
                            JSONLexer jSONLexer4 = new JSONLexer(integerValue);
                            if (jSONLexer4.scanISO8601DateIfMatch(true)) {
                                integerValue = jSONLexer4.calendar.getTime();
                            }
                            jSONLexer4.close();
                            break;
                        }
                        break;
                    case 6:
                        integerValue = Boolean.TRUE;
                        this.lexer.nextToken(16);
                        break;
                    case 7:
                        integerValue = Boolean.FALSE;
                        this.lexer.nextToken(16);
                        break;
                    case 8:
                        integerValue = null;
                        this.lexer.nextToken(4);
                        break;
                    case 12:
                        Map jSONObject;
                        if ((this.lexer.features & Feature.OrderedField.mask) != 0) {
                            jSONObject = new JSONObject(new LinkedHashMap());
                        } else {
                            jSONObject = new JSONObject();
                        }
                        integerValue = parseObject(jSONObject, Integer.valueOf(i3));
                        break;
                    case 14:
                        Collection jSONArray = new JSONArray();
                        parseArray(jSONArray, Integer.valueOf(i3));
                        break;
                    case 15:
                        this.lexer.nextToken(16);
                        if (!z) {
                            this.contex = parseContext;
                            return;
                        }
                        return;
                    case 20:
                        throw new JSONException("unclosed jsonArray");
                    case 23:
                        integerValue = null;
                        this.lexer.nextToken(4);
                        break;
                    default:
                        integerValue = parse();
                        break;
                }
                collection.add(integerValue);
                if (this.resolveStatus == 1) {
                    checkListResolve(collection);
                }
                if (this.lexer.token == 16) {
                    c3 = this.lexer.ch;
                    if (c3 == '\"') {
                        this.lexer.pos = this.lexer.bp;
                        this.lexer.scanString();
                    } else if (c3 >= '0' && c3 <= '9') {
                        this.lexer.pos = this.lexer.bp;
                        this.lexer.scanNumber();
                    } else if (c3 == '{') {
                        this.lexer.token = 12;
                        jSONLexer2 = this.lexer;
                        int i6 = jSONLexer2.bp + 1;
                        jSONLexer2.bp = i6;
                        this.lexer.ch = i6 >= this.lexer.len ? JSONLexer.EOI : this.lexer.text.charAt(i6);
                    } else {
                        this.lexer.nextToken();
                    }
                }
                i3++;
            }
        } finally {
            if (!z) {
                this.contex = parseContext;
            }
        }
    }

    protected void addResolveTask(ResolveTask resolveTask) {
        if (this.resolveTaskList == null) {
            this.resolveTaskList = new ArrayList(2);
        }
        this.resolveTaskList.add(resolveTask);
    }

    protected ResolveTask getLastResolveTask() {
        return (ResolveTask) this.resolveTaskList.get(this.resolveTaskList.size() - 1);
    }

    public List<ExtraProcessor> getExtraProcessors() {
        if (this.extraProcessors == null) {
            this.extraProcessors = new ArrayList(2);
        }
        return this.extraProcessors;
    }

    public List<ExtraTypeProvider> getExtraTypeProviders() {
        if (this.extraTypeProviders == null) {
            this.extraTypeProviders = new ArrayList(2);
        }
        return this.extraTypeProviders;
    }

    public void setContext(ParseContext parseContext) {
        if (!this.lexer.disableCircularReferenceDetect) {
            this.contex = parseContext;
        }
    }

    protected void popContext() {
        this.contex = this.contex.parent;
        this.contextArray[this.contextArrayIndex - 1] = null;
        this.contextArrayIndex--;
    }

    protected ParseContext setContext(ParseContext parseContext, Object obj, Object obj2) {
        if (this.lexer.disableCircularReferenceDetect) {
            return null;
        }
        this.contex = new ParseContext(parseContext, obj, obj2);
        int i = this.contextArrayIndex;
        this.contextArrayIndex = i + 1;
        if (this.contextArray == null) {
            this.contextArray = new ParseContext[8];
        } else if (i >= this.contextArray.length) {
            Object obj3 = new ParseContext[((this.contextArray.length * 3) / 2)];
            System.arraycopy(this.contextArray, 0, obj3, 0, this.contextArray.length);
            this.contextArray = obj3;
        }
        this.contextArray[i] = this.contex;
        return this.contex;
    }

    public Object parse() {
        return parse(null);
    }

    public Object parse(Object obj) {
        boolean z = true;
        Object integerValue;
        Collection jSONArray;
        switch (this.lexer.token) {
            case 2:
                integerValue = this.lexer.integerValue();
                this.lexer.nextToken();
                return integerValue;
            case 3:
                if ((this.lexer.features & Feature.UseBigDecimal.mask) == 0) {
                    z = false;
                }
                integerValue = this.lexer.decimalValue(z);
                this.lexer.nextToken();
                return integerValue;
            case 4:
                integerValue = this.lexer.stringVal();
                this.lexer.nextToken(16);
                if ((this.lexer.features & Feature.AllowISO8601DateFormat.mask) == 0) {
                    return integerValue;
                }
                JSONLexer jSONLexer = new JSONLexer(integerValue);
                try {
                    if (jSONLexer.scanISO8601DateIfMatch(true)) {
                        integerValue = jSONLexer.calendar.getTime();
                        return integerValue;
                    }
                    jSONLexer.close();
                    return integerValue;
                } finally {
                    jSONLexer.close();
                }
            case 6:
                this.lexer.nextToken(16);
                return Boolean.TRUE;
            case 7:
                this.lexer.nextToken(16);
                return Boolean.FALSE;
            case 8:
            case 23:
                this.lexer.nextToken();
                return null;
            case 9:
                this.lexer.nextToken(18);
                if (this.lexer.token != 18) {
                    throw new JSONException("syntax error, " + this.lexer.info());
                }
                this.lexer.nextToken(10);
                accept(10);
                long longValue = this.lexer.integerValue().longValue();
                accept(2);
                accept(11);
                return new Date(longValue);
            case 12:
                return parseObject((this.lexer.features & Feature.OrderedField.mask) != 0 ? new JSONObject(new LinkedHashMap()) : new JSONObject(), obj);
            case 14:
                jSONArray = new JSONArray();
                parseArray(jSONArray, obj);
                return jSONArray;
            case 20:
                if (this.lexer.isBlankInput()) {
                    return null;
                }
                throw new JSONException("syntax error, " + this.lexer.info());
            case 21:
                this.lexer.nextToken();
                jSONArray = new HashSet();
                parseArray(jSONArray, obj);
                return jSONArray;
            case 22:
                this.lexer.nextToken();
                jSONArray = new TreeSet();
                parseArray(jSONArray, obj);
                return jSONArray;
            default:
                throw new JSONException("syntax error, " + this.lexer.info());
        }
    }

    public void config(Feature feature, boolean z) {
        this.lexer.config(feature, z);
    }

    public final void accept(int i) {
        if (this.lexer.token == i) {
            this.lexer.nextToken();
            return;
        }
        throw new JSONException("syntax error, expect " + JSONToken.name(i) + ", actual " + JSONToken.name(this.lexer.token));
    }

    public void close() {
        try {
            if (this.lexer.token != 20) {
                throw new JSONException("not close json text, token : " + JSONToken.name(this.lexer.token));
            }
        } finally {
            this.lexer.close();
        }
    }

    public void handleResovleTask(Object obj) {
        if (this.resolveTaskList != null) {
            int size = this.resolveTaskList.size();
            for (int i = 0; i < size; i++) {
                ResolveTask resolveTask = (ResolveTask) this.resolveTaskList.get(i);
                FieldDeserializer fieldDeserializer = resolveTask.fieldDeserializer;
                if (fieldDeserializer != null) {
                    Object obj2;
                    Object obj3;
                    if (resolveTask.ownerContext != null) {
                        obj2 = resolveTask.ownerContext.object;
                    } else {
                        obj2 = null;
                    }
                    String access$000 = resolveTask.referenceValue;
                    if (access$000.startsWith("$")) {
                        obj3 = null;
                        for (int i2 = 0; i2 < this.contextArrayIndex; i2++) {
                            if (access$000.equals(this.contextArray[i2].toString())) {
                                obj3 = this.contextArray[i2].object;
                            }
                        }
                    } else {
                        obj3 = resolveTask.context.object;
                    }
                    fieldDeserializer.setValue(obj2, obj3);
                }
            }
        }
    }

    public String parseString() {
        char c = JSONLexer.EOI;
        int i = this.lexer.token;
        if (i == 4) {
            String stringVal = this.lexer.stringVal();
            JSONLexer jSONLexer;
            int i2;
            if (this.lexer.ch == ',') {
                jSONLexer = this.lexer;
                i2 = jSONLexer.bp + 1;
                jSONLexer.bp = i2;
                jSONLexer = this.lexer;
                if (i2 < this.lexer.len) {
                    c = this.lexer.text.charAt(i2);
                }
                jSONLexer.ch = c;
                this.lexer.token = 16;
            } else if (this.lexer.ch == ']') {
                jSONLexer = this.lexer;
                i2 = jSONLexer.bp + 1;
                jSONLexer.bp = i2;
                jSONLexer = this.lexer;
                if (i2 < this.lexer.len) {
                    c = this.lexer.text.charAt(i2);
                }
                jSONLexer.ch = c;
                this.lexer.token = 15;
            } else if (this.lexer.ch == '}') {
                jSONLexer = this.lexer;
                i2 = jSONLexer.bp + 1;
                jSONLexer.bp = i2;
                jSONLexer = this.lexer;
                if (i2 < this.lexer.len) {
                    c = this.lexer.text.charAt(i2);
                }
                jSONLexer.ch = c;
                this.lexer.token = 13;
            } else {
                this.lexer.nextToken();
            }
            return stringVal;
        } else if (i == 2) {
            String numberString = this.lexer.numberString();
            this.lexer.nextToken(16);
            return numberString;
        } else {
            Object parse = parse();
            if (parse == null) {
                return null;
            }
            return parse.toString();
        }
    }
}
