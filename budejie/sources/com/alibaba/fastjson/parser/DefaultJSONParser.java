package com.alibaba.fastjson.parser;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.deserializer.ASMJavaBeanDeserializer;
import com.alibaba.fastjson.parser.deserializer.ExtraProcessable;
import com.alibaba.fastjson.parser.deserializer.ExtraProcessor;
import com.alibaba.fastjson.parser.deserializer.ExtraTypeProvider;
import com.alibaba.fastjson.parser.deserializer.FieldDeserializer;
import com.alibaba.fastjson.parser.deserializer.FieldTypeResolver;
import com.alibaba.fastjson.parser.deserializer.JavaBeanDeserializer;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import com.alibaba.fastjson.parser.deserializer.ResolveFieldDeserializer;
import com.alibaba.fastjson.serializer.IntegerCodec;
import com.alibaba.fastjson.serializer.LongCodec;
import com.alibaba.fastjson.serializer.StringCodec;
import com.alibaba.fastjson.util.TypeUtils;
import java.io.Closeable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class DefaultJSONParser implements Closeable {
    public static final int NONE = 0;
    public static final int NeedToResolve = 1;
    public static final int TypeNameRedirect = 2;
    private static final Set<Class<?>> primitiveClasses = new HashSet();
    protected ParserConfig config;
    protected ParseContext context;
    private ParseContext[] contextArray;
    private int contextArrayIndex;
    private DateFormat dateFormat;
    private String dateFormatPattern;
    private List<ExtraProcessor> extraProcessors;
    private List<ExtraTypeProvider> extraTypeProviders;
    protected FieldTypeResolver fieldTypeResolver;
    public final Object input;
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

        public ParseContext getContext() {
            return this.context;
        }

        public String getReferenceValue() {
            return this.referenceValue;
        }

        public FieldDeserializer getFieldDeserializer() {
            return this.fieldDeserializer;
        }

        public void setFieldDeserializer(FieldDeserializer fieldDeserializer) {
            this.fieldDeserializer = fieldDeserializer;
        }

        public ParseContext getOwnerContext() {
            return this.ownerContext;
        }

        public void setOwnerContext(ParseContext parseContext) {
            this.ownerContext = parseContext;
        }
    }

    static {
        primitiveClasses.add(Boolean.TYPE);
        primitiveClasses.add(Byte.TYPE);
        primitiveClasses.add(Short.TYPE);
        primitiveClasses.add(Integer.TYPE);
        primitiveClasses.add(Long.TYPE);
        primitiveClasses.add(Float.TYPE);
        primitiveClasses.add(Double.TYPE);
        primitiveClasses.add(Boolean.class);
        primitiveClasses.add(Byte.class);
        primitiveClasses.add(Short.class);
        primitiveClasses.add(Integer.class);
        primitiveClasses.add(Long.class);
        primitiveClasses.add(Float.class);
        primitiveClasses.add(Double.class);
        primitiveClasses.add(BigInteger.class);
        primitiveClasses.add(BigDecimal.class);
        primitiveClasses.add(String.class);
    }

    public String getDateFomartPattern() {
        return this.dateFormatPattern;
    }

    public DateFormat getDateFormat() {
        if (this.dateFormat == null) {
            this.dateFormat = new SimpleDateFormat(this.dateFormatPattern, this.lexer.getLocale());
            this.dateFormat.setTimeZone(this.lexer.getTimeZone());
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
        this(str, ParserConfig.getGlobalInstance(), JSON.DEFAULT_PARSER_FEATURE);
    }

    public DefaultJSONParser(String str, ParserConfig parserConfig) {
        this((Object) str, new JSONScanner(str, JSON.DEFAULT_PARSER_FEATURE), parserConfig);
    }

    public DefaultJSONParser(String str, ParserConfig parserConfig, int i) {
        this((Object) str, new JSONScanner(str, i), parserConfig);
    }

    public DefaultJSONParser(char[] cArr, int i, ParserConfig parserConfig, int i2) {
        this((Object) cArr, new JSONScanner(cArr, i, i2), parserConfig);
    }

    public DefaultJSONParser(JSONLexer jSONLexer) {
        this(jSONLexer, ParserConfig.getGlobalInstance());
    }

    public DefaultJSONParser(JSONLexer jSONLexer, ParserConfig parserConfig) {
        this(null, jSONLexer, parserConfig);
    }

    public DefaultJSONParser(Object obj, JSONLexer jSONLexer, ParserConfig parserConfig) {
        this.dateFormatPattern = JSON.DEFFAULT_DATE_FORMAT;
        this.contextArrayIndex = 0;
        this.resolveStatus = 0;
        this.extraTypeProviders = null;
        this.extraProcessors = null;
        this.fieldTypeResolver = null;
        this.lexer = jSONLexer;
        this.input = obj;
        this.config = parserConfig;
        this.symbolTable = parserConfig.symbolTable;
        jSONLexer.nextToken(12);
    }

    public SymbolTable getSymbolTable() {
        return this.symbolTable;
    }

    public String getInput() {
        if (this.input instanceof char[]) {
            return new String((char[]) this.input);
        }
        return this.input.toString();
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object parseObject(java.util.Map r13, java.lang.Object r14) {
        /*
        r12 = this;
        r8 = r12.lexer;
        r0 = r8.token();
        r1 = 8;
        if (r0 != r1) goto L_0x000f;
    L_0x000a:
        r8.nextToken();
        r13 = 0;
    L_0x000e:
        return r13;
    L_0x000f:
        r0 = r8.token();
        r1 = 13;
        if (r0 != r1) goto L_0x001b;
    L_0x0017:
        r8.nextToken();
        goto L_0x000e;
    L_0x001b:
        r0 = r8.token();
        r1 = 12;
        if (r0 == r1) goto L_0x0056;
    L_0x0023:
        r0 = r8.token();
        r1 = 16;
        if (r0 == r1) goto L_0x0056;
    L_0x002b:
        r0 = new com.alibaba.fastjson.JSONException;
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "syntax error, expect {, actual ";
        r1 = r1.append(r2);
        r2 = r8.tokenName();
        r1 = r1.append(r2);
        r2 = ", ";
        r1 = r1.append(r2);
        r2 = r8.info();
        r1 = r1.append(r2);
        r1 = r1.toString();
        r0.<init>(r1);
        throw r0;
    L_0x0056:
        r2 = r12.context;
        r7 = 0;
        r0 = r7;
    L_0x005a:
        r8.skipWhitespace();	 Catch:{ all -> 0x00b7 }
        r1 = r8.getCurrent();	 Catch:{ all -> 0x00b7 }
        r3 = com.alibaba.fastjson.parser.Feature.AllowArbitraryCommas;	 Catch:{ all -> 0x00b7 }
        r3 = r8.isEnabled(r3);	 Catch:{ all -> 0x00b7 }
        if (r3 == 0) goto L_0x0078;
    L_0x0069:
        r3 = 44;
        if (r1 != r3) goto L_0x0078;
    L_0x006d:
        r8.next();	 Catch:{ all -> 0x00b7 }
        r8.skipWhitespace();	 Catch:{ all -> 0x00b7 }
        r1 = r8.getCurrent();	 Catch:{ all -> 0x00b7 }
        goto L_0x0069;
    L_0x0078:
        r3 = 0;
        r4 = 34;
        if (r1 != r4) goto L_0x00bc;
    L_0x007d:
        r1 = r12.symbolTable;	 Catch:{ all -> 0x00b7 }
        r4 = 34;
        r1 = r8.scanSymbol(r1, r4);	 Catch:{ all -> 0x00b7 }
        r8.skipWhitespace();	 Catch:{ all -> 0x00b7 }
        r4 = r8.getCurrent();	 Catch:{ all -> 0x00b7 }
        r5 = 58;
        if (r4 == r5) goto L_0x018c;
    L_0x0090:
        r0 = new com.alibaba.fastjson.JSONException;	 Catch:{ all -> 0x00b7 }
        r3 = new java.lang.StringBuilder;	 Catch:{ all -> 0x00b7 }
        r3.<init>();	 Catch:{ all -> 0x00b7 }
        r4 = "expect ':' at ";
        r3 = r3.append(r4);	 Catch:{ all -> 0x00b7 }
        r4 = r8.pos();	 Catch:{ all -> 0x00b7 }
        r3 = r3.append(r4);	 Catch:{ all -> 0x00b7 }
        r4 = ", name ";
        r3 = r3.append(r4);	 Catch:{ all -> 0x00b7 }
        r1 = r3.append(r1);	 Catch:{ all -> 0x00b7 }
        r1 = r1.toString();	 Catch:{ all -> 0x00b7 }
        r0.<init>(r1);	 Catch:{ all -> 0x00b7 }
        throw r0;	 Catch:{ all -> 0x00b7 }
    L_0x00b7:
        r0 = move-exception;
        r12.setContext(r2);
        throw r0;
    L_0x00bc:
        r4 = 125; // 0x7d float:1.75E-43 double:6.2E-322;
        if (r1 != r4) goto L_0x00ce;
    L_0x00c0:
        r8.next();	 Catch:{ all -> 0x00b7 }
        r8.resetStringPosition();	 Catch:{ all -> 0x00b7 }
        r8.nextToken();	 Catch:{ all -> 0x00b7 }
        r12.setContext(r2);
        goto L_0x000e;
    L_0x00ce:
        r4 = 39;
        if (r1 != r4) goto L_0x0112;
    L_0x00d2:
        r1 = com.alibaba.fastjson.parser.Feature.AllowSingleQuotes;	 Catch:{ all -> 0x00b7 }
        r1 = r8.isEnabled(r1);	 Catch:{ all -> 0x00b7 }
        if (r1 != 0) goto L_0x00e2;
    L_0x00da:
        r0 = new com.alibaba.fastjson.JSONException;	 Catch:{ all -> 0x00b7 }
        r1 = "syntax error";
        r0.<init>(r1);	 Catch:{ all -> 0x00b7 }
        throw r0;	 Catch:{ all -> 0x00b7 }
    L_0x00e2:
        r1 = r12.symbolTable;	 Catch:{ all -> 0x00b7 }
        r4 = 39;
        r1 = r8.scanSymbol(r1, r4);	 Catch:{ all -> 0x00b7 }
        r8.skipWhitespace();	 Catch:{ all -> 0x00b7 }
        r4 = r8.getCurrent();	 Catch:{ all -> 0x00b7 }
        r5 = 58;
        if (r4 == r5) goto L_0x018c;
    L_0x00f5:
        r0 = new com.alibaba.fastjson.JSONException;	 Catch:{ all -> 0x00b7 }
        r1 = new java.lang.StringBuilder;	 Catch:{ all -> 0x00b7 }
        r1.<init>();	 Catch:{ all -> 0x00b7 }
        r3 = "expect ':' at ";
        r1 = r1.append(r3);	 Catch:{ all -> 0x00b7 }
        r3 = r8.pos();	 Catch:{ all -> 0x00b7 }
        r1 = r1.append(r3);	 Catch:{ all -> 0x00b7 }
        r1 = r1.toString();	 Catch:{ all -> 0x00b7 }
        r0.<init>(r1);	 Catch:{ all -> 0x00b7 }
        throw r0;	 Catch:{ all -> 0x00b7 }
    L_0x0112:
        r4 = 26;
        if (r1 != r4) goto L_0x011e;
    L_0x0116:
        r0 = new com.alibaba.fastjson.JSONException;	 Catch:{ all -> 0x00b7 }
        r1 = "syntax error";
        r0.<init>(r1);	 Catch:{ all -> 0x00b7 }
        throw r0;	 Catch:{ all -> 0x00b7 }
    L_0x011e:
        r4 = 44;
        if (r1 != r4) goto L_0x012a;
    L_0x0122:
        r0 = new com.alibaba.fastjson.JSONException;	 Catch:{ all -> 0x00b7 }
        r1 = "syntax error";
        r0.<init>(r1);	 Catch:{ all -> 0x00b7 }
        throw r0;	 Catch:{ all -> 0x00b7 }
    L_0x012a:
        r4 = 48;
        if (r1 < r4) goto L_0x0132;
    L_0x012e:
        r4 = 57;
        if (r1 <= r4) goto L_0x0136;
    L_0x0132:
        r4 = 45;
        if (r1 != r4) goto L_0x017c;
    L_0x0136:
        r8.resetStringPosition();	 Catch:{ all -> 0x00b7 }
        r8.scanNumber();	 Catch:{ all -> 0x00b7 }
        r1 = r8.token();	 Catch:{ all -> 0x00b7 }
        r4 = 2;
        if (r1 != r4) goto L_0x0176;
    L_0x0143:
        r1 = r8.integerValue();	 Catch:{ all -> 0x00b7 }
    L_0x0147:
        r4 = r8.getCurrent();	 Catch:{ all -> 0x00b7 }
        r5 = 58;
        if (r4 == r5) goto L_0x018c;
    L_0x014f:
        r0 = new com.alibaba.fastjson.JSONException;	 Catch:{ all -> 0x00b7 }
        r3 = new java.lang.StringBuilder;	 Catch:{ all -> 0x00b7 }
        r3.<init>();	 Catch:{ all -> 0x00b7 }
        r4 = "expect ':' at ";
        r3 = r3.append(r4);	 Catch:{ all -> 0x00b7 }
        r4 = r8.pos();	 Catch:{ all -> 0x00b7 }
        r3 = r3.append(r4);	 Catch:{ all -> 0x00b7 }
        r4 = ", name ";
        r3 = r3.append(r4);	 Catch:{ all -> 0x00b7 }
        r1 = r3.append(r1);	 Catch:{ all -> 0x00b7 }
        r1 = r1.toString();	 Catch:{ all -> 0x00b7 }
        r0.<init>(r1);	 Catch:{ all -> 0x00b7 }
        throw r0;	 Catch:{ all -> 0x00b7 }
    L_0x0176:
        r1 = 1;
        r1 = r8.decimalValue(r1);	 Catch:{ all -> 0x00b7 }
        goto L_0x0147;
    L_0x017c:
        r4 = 123; // 0x7b float:1.72E-43 double:6.1E-322;
        if (r1 == r4) goto L_0x0184;
    L_0x0180:
        r4 = 91;
        if (r1 != r4) goto L_0x01c2;
    L_0x0184:
        r8.nextToken();	 Catch:{ all -> 0x00b7 }
        r1 = r12.parse();	 Catch:{ all -> 0x00b7 }
        r3 = 1;
    L_0x018c:
        if (r3 != 0) goto L_0x0194;
    L_0x018e:
        r8.next();	 Catch:{ all -> 0x00b7 }
        r8.skipWhitespace();	 Catch:{ all -> 0x00b7 }
    L_0x0194:
        r3 = r8.getCurrent();	 Catch:{ all -> 0x00b7 }
        r8.resetStringPosition();	 Catch:{ all -> 0x00b7 }
        r4 = com.alibaba.fastjson.JSON.DEFAULT_TYPE_KEY;	 Catch:{ all -> 0x00b7 }
        if (r1 != r4) goto L_0x0296;
    L_0x019f:
        r4 = com.alibaba.fastjson.parser.Feature.DisableSpecialKeyDetect;	 Catch:{ all -> 0x00b7 }
        r4 = r8.isEnabled(r4);	 Catch:{ all -> 0x00b7 }
        if (r4 != 0) goto L_0x0296;
    L_0x01a7:
        r1 = r12.symbolTable;	 Catch:{ all -> 0x00b7 }
        r3 = 34;
        r3 = r8.scanSymbol(r1, r3);	 Catch:{ all -> 0x00b7 }
        r1 = r12.config;	 Catch:{ all -> 0x00b7 }
        r1 = r1.getDefaultClassLoader();	 Catch:{ all -> 0x00b7 }
        r4 = com.alibaba.fastjson.util.TypeUtils.loadClass(r3, r1);	 Catch:{ all -> 0x00b7 }
        if (r4 != 0) goto L_0x020a;
    L_0x01bb:
        r1 = com.alibaba.fastjson.JSON.DEFAULT_TYPE_KEY;	 Catch:{ all -> 0x00b7 }
        r13.put(r1, r3);	 Catch:{ all -> 0x00b7 }
        goto L_0x005a;
    L_0x01c2:
        r1 = com.alibaba.fastjson.parser.Feature.AllowUnQuotedFieldNames;	 Catch:{ all -> 0x00b7 }
        r1 = r8.isEnabled(r1);	 Catch:{ all -> 0x00b7 }
        if (r1 != 0) goto L_0x01d2;
    L_0x01ca:
        r0 = new com.alibaba.fastjson.JSONException;	 Catch:{ all -> 0x00b7 }
        r1 = "syntax error";
        r0.<init>(r1);	 Catch:{ all -> 0x00b7 }
        throw r0;	 Catch:{ all -> 0x00b7 }
    L_0x01d2:
        r1 = r12.symbolTable;	 Catch:{ all -> 0x00b7 }
        r1 = r8.scanSymbolUnQuoted(r1);	 Catch:{ all -> 0x00b7 }
        r8.skipWhitespace();	 Catch:{ all -> 0x00b7 }
        r4 = r8.getCurrent();	 Catch:{ all -> 0x00b7 }
        r5 = 58;
        if (r4 == r5) goto L_0x018c;
    L_0x01e3:
        r0 = new com.alibaba.fastjson.JSONException;	 Catch:{ all -> 0x00b7 }
        r1 = new java.lang.StringBuilder;	 Catch:{ all -> 0x00b7 }
        r1.<init>();	 Catch:{ all -> 0x00b7 }
        r3 = "expect ':' at ";
        r1 = r1.append(r3);	 Catch:{ all -> 0x00b7 }
        r3 = r8.pos();	 Catch:{ all -> 0x00b7 }
        r1 = r1.append(r3);	 Catch:{ all -> 0x00b7 }
        r3 = ", actual ";
        r1 = r1.append(r3);	 Catch:{ all -> 0x00b7 }
        r1 = r1.append(r4);	 Catch:{ all -> 0x00b7 }
        r1 = r1.toString();	 Catch:{ all -> 0x00b7 }
        r0.<init>(r1);	 Catch:{ all -> 0x00b7 }
        throw r0;	 Catch:{ all -> 0x00b7 }
    L_0x020a:
        r0 = 16;
        r8.nextToken(r0);	 Catch:{ all -> 0x00b7 }
        r0 = r8.token();	 Catch:{ all -> 0x00b7 }
        r1 = 13;
        if (r0 != r1) goto L_0x0264;
    L_0x0217:
        r0 = 16;
        r8.nextToken(r0);	 Catch:{ all -> 0x00b7 }
        r1 = 0;
        r0 = r12.config;	 Catch:{ Exception -> 0x025b }
        r0 = r0.getDeserializer(r4);	 Catch:{ Exception -> 0x025b }
        r5 = r0 instanceof com.alibaba.fastjson.parser.deserializer.ASMJavaBeanDeserializer;	 Catch:{ Exception -> 0x025b }
        if (r5 == 0) goto L_0x023e;
    L_0x0227:
        r0 = (com.alibaba.fastjson.parser.deserializer.ASMJavaBeanDeserializer) r0;	 Catch:{ Exception -> 0x025b }
        r0 = r0.createInstance(r12, r4);	 Catch:{ Exception -> 0x025b }
    L_0x022d:
        if (r0 != 0) goto L_0x0238;
    L_0x022f:
        r0 = java.lang.Cloneable.class;
        if (r4 != r0) goto L_0x0249;
    L_0x0233:
        r0 = new java.util.HashMap;	 Catch:{ Exception -> 0x025b }
        r0.<init>();	 Catch:{ Exception -> 0x025b }
    L_0x0238:
        r12.setContext(r2);
        r13 = r0;
        goto L_0x000e;
    L_0x023e:
        r5 = r0 instanceof com.alibaba.fastjson.parser.deserializer.JavaBeanDeserializer;	 Catch:{ Exception -> 0x025b }
        if (r5 == 0) goto L_0x058a;
    L_0x0242:
        r0 = (com.alibaba.fastjson.parser.deserializer.JavaBeanDeserializer) r0;	 Catch:{ Exception -> 0x025b }
        r0 = r0.createInstance(r12, r4);	 Catch:{ Exception -> 0x025b }
        goto L_0x022d;
    L_0x0249:
        r0 = "java.util.Collections$EmptyMap";
        r0 = r0.equals(r3);	 Catch:{ Exception -> 0x025b }
        if (r0 == 0) goto L_0x0256;
    L_0x0251:
        r0 = java.util.Collections.emptyMap();	 Catch:{ Exception -> 0x025b }
        goto L_0x0238;
    L_0x0256:
        r0 = r4.newInstance();	 Catch:{ Exception -> 0x025b }
        goto L_0x0238;
    L_0x025b:
        r0 = move-exception;
        r1 = new com.alibaba.fastjson.JSONException;	 Catch:{ all -> 0x00b7 }
        r3 = "create instance error";
        r1.<init>(r3, r0);	 Catch:{ all -> 0x00b7 }
        throw r1;	 Catch:{ all -> 0x00b7 }
    L_0x0264:
        r0 = 2;
        r12.setResolveStatus(r0);	 Catch:{ all -> 0x00b7 }
        r0 = r12.context;	 Catch:{ all -> 0x00b7 }
        if (r0 == 0) goto L_0x0273;
    L_0x026c:
        r0 = r14 instanceof java.lang.Integer;	 Catch:{ all -> 0x00b7 }
        if (r0 != 0) goto L_0x0273;
    L_0x0270:
        r12.popContext();	 Catch:{ all -> 0x00b7 }
    L_0x0273:
        r0 = r13.size();	 Catch:{ all -> 0x00b7 }
        if (r0 <= 0) goto L_0x0287;
    L_0x0279:
        r0 = r12.config;	 Catch:{ all -> 0x00b7 }
        r13 = com.alibaba.fastjson.util.TypeUtils.cast(r13, r4, r0);	 Catch:{ all -> 0x00b7 }
        r12.parseObject(r13);	 Catch:{ all -> 0x00b7 }
        r12.setContext(r2);
        goto L_0x000e;
    L_0x0287:
        r0 = r12.config;	 Catch:{ all -> 0x00b7 }
        r0 = r0.getDeserializer(r4);	 Catch:{ all -> 0x00b7 }
        r13 = r0.deserialze(r12, r4, r14);	 Catch:{ all -> 0x00b7 }
        r12.setContext(r2);
        goto L_0x000e;
    L_0x0296:
        r4 = "$ref";
        if (r1 != r4) goto L_0x0366;
    L_0x029a:
        r4 = com.alibaba.fastjson.parser.Feature.DisableSpecialKeyDetect;	 Catch:{ all -> 0x00b7 }
        r4 = r8.isEnabled(r4);	 Catch:{ all -> 0x00b7 }
        if (r4 != 0) goto L_0x0366;
    L_0x02a2:
        r0 = 4;
        r8.nextToken(r0);	 Catch:{ all -> 0x00b7 }
        r0 = r8.token();	 Catch:{ all -> 0x00b7 }
        r1 = 4;
        if (r0 != r1) goto L_0x0345;
    L_0x02ad:
        r3 = r8.stringVal();	 Catch:{ all -> 0x00b7 }
        r0 = 13;
        r8.nextToken(r0);	 Catch:{ all -> 0x00b7 }
        r0 = 0;
        r1 = "@";
        r1 = r1.equals(r3);	 Catch:{ all -> 0x00b7 }
        if (r1 == 0) goto L_0x02ea;
    L_0x02bf:
        r1 = r12.context;	 Catch:{ all -> 0x00b7 }
        if (r1 == 0) goto L_0x0339;
    L_0x02c3:
        r3 = r12.context;	 Catch:{ all -> 0x00b7 }
        r1 = r3.object;	 Catch:{ all -> 0x00b7 }
        r4 = r1 instanceof java.lang.Object[];	 Catch:{ all -> 0x00b7 }
        if (r4 != 0) goto L_0x02cf;
    L_0x02cb:
        r4 = r1 instanceof java.util.Collection;	 Catch:{ all -> 0x00b7 }
        if (r4 == 0) goto L_0x02e1;
    L_0x02cf:
        r0 = r1;
    L_0x02d0:
        r13 = r0;
    L_0x02d1:
        r0 = r8.token();	 Catch:{ all -> 0x00b7 }
        r1 = 13;
        if (r0 == r1) goto L_0x033b;
    L_0x02d9:
        r0 = new com.alibaba.fastjson.JSONException;	 Catch:{ all -> 0x00b7 }
        r1 = "syntax error";
        r0.<init>(r1);	 Catch:{ all -> 0x00b7 }
        throw r0;	 Catch:{ all -> 0x00b7 }
    L_0x02e1:
        r1 = r3.parent;	 Catch:{ all -> 0x00b7 }
        if (r1 == 0) goto L_0x02d0;
    L_0x02e5:
        r0 = r3.parent;	 Catch:{ all -> 0x00b7 }
        r0 = r0.object;	 Catch:{ all -> 0x00b7 }
        goto L_0x02d0;
    L_0x02ea:
        r1 = "..";
        r1 = r1.equals(r3);	 Catch:{ all -> 0x00b7 }
        if (r1 == 0) goto L_0x0308;
    L_0x02f2:
        r1 = r2.object;	 Catch:{ all -> 0x00b7 }
        if (r1 == 0) goto L_0x02fa;
    L_0x02f6:
        r0 = r2.object;	 Catch:{ all -> 0x00b7 }
        r13 = r0;
        goto L_0x02d1;
    L_0x02fa:
        r1 = new com.alibaba.fastjson.parser.DefaultJSONParser$ResolveTask;	 Catch:{ all -> 0x00b7 }
        r1.<init>(r2, r3);	 Catch:{ all -> 0x00b7 }
        r12.addResolveTask(r1);	 Catch:{ all -> 0x00b7 }
        r1 = 1;
        r12.setResolveStatus(r1);	 Catch:{ all -> 0x00b7 }
        r13 = r0;
        goto L_0x02d1;
    L_0x0308:
        r1 = "$";
        r1 = r1.equals(r3);	 Catch:{ all -> 0x00b7 }
        if (r1 == 0) goto L_0x032d;
    L_0x0310:
        r1 = r2;
    L_0x0311:
        r4 = r1.parent;	 Catch:{ all -> 0x00b7 }
        if (r4 == 0) goto L_0x0318;
    L_0x0315:
        r1 = r1.parent;	 Catch:{ all -> 0x00b7 }
        goto L_0x0311;
    L_0x0318:
        r4 = r1.object;	 Catch:{ all -> 0x00b7 }
        if (r4 == 0) goto L_0x0320;
    L_0x031c:
        r0 = r1.object;	 Catch:{ all -> 0x00b7 }
    L_0x031e:
        r13 = r0;
        goto L_0x02d1;
    L_0x0320:
        r4 = new com.alibaba.fastjson.parser.DefaultJSONParser$ResolveTask;	 Catch:{ all -> 0x00b7 }
        r4.<init>(r1, r3);	 Catch:{ all -> 0x00b7 }
        r12.addResolveTask(r4);	 Catch:{ all -> 0x00b7 }
        r1 = 1;
        r12.setResolveStatus(r1);	 Catch:{ all -> 0x00b7 }
        goto L_0x031e;
    L_0x032d:
        r1 = new com.alibaba.fastjson.parser.DefaultJSONParser$ResolveTask;	 Catch:{ all -> 0x00b7 }
        r1.<init>(r2, r3);	 Catch:{ all -> 0x00b7 }
        r12.addResolveTask(r1);	 Catch:{ all -> 0x00b7 }
        r1 = 1;
        r12.setResolveStatus(r1);	 Catch:{ all -> 0x00b7 }
    L_0x0339:
        r13 = r0;
        goto L_0x02d1;
    L_0x033b:
        r0 = 16;
        r8.nextToken(r0);	 Catch:{ all -> 0x00b7 }
        r12.setContext(r2);
        goto L_0x000e;
    L_0x0345:
        r0 = new com.alibaba.fastjson.JSONException;	 Catch:{ all -> 0x00b7 }
        r1 = new java.lang.StringBuilder;	 Catch:{ all -> 0x00b7 }
        r1.<init>();	 Catch:{ all -> 0x00b7 }
        r3 = "illegal ref, ";
        r1 = r1.append(r3);	 Catch:{ all -> 0x00b7 }
        r3 = r8.token();	 Catch:{ all -> 0x00b7 }
        r3 = com.alibaba.fastjson.parser.JSONToken.name(r3);	 Catch:{ all -> 0x00b7 }
        r1 = r1.append(r3);	 Catch:{ all -> 0x00b7 }
        r1 = r1.toString();	 Catch:{ all -> 0x00b7 }
        r0.<init>(r1);	 Catch:{ all -> 0x00b7 }
        throw r0;	 Catch:{ all -> 0x00b7 }
    L_0x0366:
        if (r0 != 0) goto L_0x0587;
    L_0x0368:
        r0 = r12.setContext(r13, r14);	 Catch:{ all -> 0x00b7 }
        if (r2 != 0) goto L_0x0584;
    L_0x036e:
        r2 = 1;
        r7 = r2;
        r2 = r0;
    L_0x0371:
        r0 = r13.getClass();	 Catch:{ all -> 0x00b7 }
        r4 = com.alibaba.fastjson.JSONObject.class;
        if (r0 != r4) goto L_0x037e;
    L_0x0379:
        if (r1 != 0) goto L_0x03bb;
    L_0x037b:
        r0 = "null";
    L_0x037d:
        r1 = r0;
    L_0x037e:
        r0 = 34;
        if (r3 != r0) goto L_0x03c0;
    L_0x0382:
        r8.scanString();	 Catch:{ all -> 0x00b7 }
        r0 = r8.stringVal();	 Catch:{ all -> 0x00b7 }
        r3 = com.alibaba.fastjson.parser.Feature.AllowISO8601DateFormat;	 Catch:{ all -> 0x00b7 }
        r3 = r8.isEnabled(r3);	 Catch:{ all -> 0x00b7 }
        if (r3 == 0) goto L_0x03a7;
    L_0x0391:
        r3 = new com.alibaba.fastjson.parser.JSONScanner;	 Catch:{ all -> 0x00b7 }
        r3.<init>(r0);	 Catch:{ all -> 0x00b7 }
        r4 = r3.scanISO8601DateIfMatch();	 Catch:{ all -> 0x00b7 }
        if (r4 == 0) goto L_0x03a4;
    L_0x039c:
        r0 = r3.getCalendar();	 Catch:{ all -> 0x00b7 }
        r0 = r0.getTime();	 Catch:{ all -> 0x00b7 }
    L_0x03a4:
        r3.close();	 Catch:{ all -> 0x00b7 }
    L_0x03a7:
        r13.put(r1, r0);	 Catch:{ all -> 0x00b7 }
    L_0x03aa:
        r8.skipWhitespace();	 Catch:{ all -> 0x00b7 }
        r3 = r8.getCurrent();	 Catch:{ all -> 0x00b7 }
        r4 = 44;
        if (r3 != r4) goto L_0x053a;
    L_0x03b5:
        r8.next();	 Catch:{ all -> 0x00b7 }
        r0 = r7;
        goto L_0x005a;
    L_0x03bb:
        r0 = r1.toString();	 Catch:{ all -> 0x00b7 }
        goto L_0x037d;
    L_0x03c0:
        r0 = 48;
        if (r3 < r0) goto L_0x03c8;
    L_0x03c4:
        r0 = 57;
        if (r3 <= r0) goto L_0x03cc;
    L_0x03c8:
        r0 = 45;
        if (r3 != r0) goto L_0x03e9;
    L_0x03cc:
        r8.scanNumber();	 Catch:{ all -> 0x00b7 }
        r0 = r8.token();	 Catch:{ all -> 0x00b7 }
        r3 = 2;
        if (r0 != r3) goto L_0x03de;
    L_0x03d6:
        r0 = r8.integerValue();	 Catch:{ all -> 0x00b7 }
    L_0x03da:
        r13.put(r1, r0);	 Catch:{ all -> 0x00b7 }
        goto L_0x03aa;
    L_0x03de:
        r0 = com.alibaba.fastjson.parser.Feature.UseBigDecimal;	 Catch:{ all -> 0x00b7 }
        r0 = r8.isEnabled(r0);	 Catch:{ all -> 0x00b7 }
        r0 = r8.decimalValue(r0);	 Catch:{ all -> 0x00b7 }
        goto L_0x03da;
    L_0x03e9:
        r0 = 91;
        if (r3 != r0) goto L_0x042a;
    L_0x03ed:
        r8.nextToken();	 Catch:{ all -> 0x00b7 }
        r0 = new com.alibaba.fastjson.JSONArray;	 Catch:{ all -> 0x00b7 }
        r0.<init>();	 Catch:{ all -> 0x00b7 }
        r12.parseArray(r0, r1);	 Catch:{ all -> 0x00b7 }
        r3 = com.alibaba.fastjson.parser.Feature.UseObjectArray;	 Catch:{ all -> 0x00b7 }
        r3 = r8.isEnabled(r3);	 Catch:{ all -> 0x00b7 }
        if (r3 == 0) goto L_0x0404;
    L_0x0400:
        r0 = r0.toArray();	 Catch:{ all -> 0x00b7 }
    L_0x0404:
        r13.put(r1, r0);	 Catch:{ all -> 0x00b7 }
        r0 = r8.token();	 Catch:{ all -> 0x00b7 }
        r1 = 13;
        if (r0 != r1) goto L_0x0417;
    L_0x040f:
        r8.nextToken();	 Catch:{ all -> 0x00b7 }
        r12.setContext(r2);
        goto L_0x000e;
    L_0x0417:
        r0 = r8.token();	 Catch:{ all -> 0x00b7 }
        r1 = 16;
        if (r0 != r1) goto L_0x0422;
    L_0x041f:
        r0 = r7;
        goto L_0x005a;
    L_0x0422:
        r0 = new com.alibaba.fastjson.JSONException;	 Catch:{ all -> 0x00b7 }
        r1 = "syntax error";
        r0.<init>(r1);	 Catch:{ all -> 0x00b7 }
        throw r0;	 Catch:{ all -> 0x00b7 }
    L_0x042a:
        r0 = 123; // 0x7b float:1.72E-43 double:6.1E-322;
        if (r3 != r0) goto L_0x04e2;
    L_0x042e:
        r8.nextToken();	 Catch:{ all -> 0x00b7 }
        if (r14 == 0) goto L_0x04ac;
    L_0x0433:
        r0 = r14.getClass();	 Catch:{ all -> 0x00b7 }
        r3 = java.lang.Integer.class;
        if (r0 != r3) goto L_0x04ac;
    L_0x043b:
        r0 = 1;
        r6 = r0;
    L_0x043d:
        r9 = new com.alibaba.fastjson.JSONObject;	 Catch:{ all -> 0x00b7 }
        r0 = com.alibaba.fastjson.parser.Feature.OrderedField;	 Catch:{ all -> 0x00b7 }
        r0 = r8.isEnabled(r0);	 Catch:{ all -> 0x00b7 }
        r9.<init>(r0);	 Catch:{ all -> 0x00b7 }
        r0 = 0;
        if (r6 != 0) goto L_0x0581;
    L_0x044b:
        r0 = r12.setContext(r2, r9, r1);	 Catch:{ all -> 0x00b7 }
        r5 = r0;
    L_0x0450:
        r3 = 0;
        r0 = 0;
        r4 = r12.fieldTypeResolver;	 Catch:{ all -> 0x00b7 }
        if (r4 == 0) goto L_0x057c;
    L_0x0456:
        if (r1 == 0) goto L_0x04af;
    L_0x0458:
        r4 = r1.toString();	 Catch:{ all -> 0x00b7 }
    L_0x045c:
        r10 = r12.fieldTypeResolver;	 Catch:{ all -> 0x00b7 }
        r4 = r10.resolve(r13, r4);	 Catch:{ all -> 0x00b7 }
        if (r4 == 0) goto L_0x057c;
    L_0x0464:
        r0 = r12.config;	 Catch:{ all -> 0x00b7 }
        r0 = r0.getDeserializer(r4);	 Catch:{ all -> 0x00b7 }
        r3 = r0.deserialze(r12, r4, r1);	 Catch:{ all -> 0x00b7 }
        r0 = 1;
        r11 = r0;
        r0 = r3;
        r3 = r11;
    L_0x0472:
        if (r3 != 0) goto L_0x0478;
    L_0x0474:
        r0 = r12.parseObject(r9, r1);	 Catch:{ all -> 0x00b7 }
    L_0x0478:
        if (r5 == 0) goto L_0x047e;
    L_0x047a:
        if (r9 == r0) goto L_0x047e;
    L_0x047c:
        r5.object = r13;	 Catch:{ all -> 0x00b7 }
    L_0x047e:
        r3 = r1.toString();	 Catch:{ all -> 0x00b7 }
        r12.checkMapResolve(r13, r3);	 Catch:{ all -> 0x00b7 }
        r3 = r13.getClass();	 Catch:{ all -> 0x00b7 }
        r4 = com.alibaba.fastjson.JSONObject.class;
        if (r3 != r4) goto L_0x04b1;
    L_0x048d:
        r3 = r1.toString();	 Catch:{ all -> 0x00b7 }
        r13.put(r3, r0);	 Catch:{ all -> 0x00b7 }
    L_0x0494:
        if (r6 == 0) goto L_0x0499;
    L_0x0496:
        r12.setContext(r0, r1);	 Catch:{ all -> 0x00b7 }
    L_0x0499:
        r0 = r8.token();	 Catch:{ all -> 0x00b7 }
        r1 = 13;
        if (r0 != r1) goto L_0x04b5;
    L_0x04a1:
        r8.nextToken();	 Catch:{ all -> 0x00b7 }
        r12.setContext(r2);	 Catch:{ all -> 0x00b7 }
        r12.setContext(r2);
        goto L_0x000e;
    L_0x04ac:
        r0 = 0;
        r6 = r0;
        goto L_0x043d;
    L_0x04af:
        r4 = 0;
        goto L_0x045c;
    L_0x04b1:
        r13.put(r1, r0);	 Catch:{ all -> 0x00b7 }
        goto L_0x0494;
    L_0x04b5:
        r0 = r8.token();	 Catch:{ all -> 0x00b7 }
        r1 = 16;
        if (r0 != r1) goto L_0x04c5;
    L_0x04bd:
        if (r6 == 0) goto L_0x0579;
    L_0x04bf:
        r12.popContext();	 Catch:{ all -> 0x00b7 }
        r0 = r7;
        goto L_0x005a;
    L_0x04c5:
        r0 = new com.alibaba.fastjson.JSONException;	 Catch:{ all -> 0x00b7 }
        r1 = new java.lang.StringBuilder;	 Catch:{ all -> 0x00b7 }
        r1.<init>();	 Catch:{ all -> 0x00b7 }
        r3 = "syntax error, ";
        r1 = r1.append(r3);	 Catch:{ all -> 0x00b7 }
        r3 = r8.tokenName();	 Catch:{ all -> 0x00b7 }
        r1 = r1.append(r3);	 Catch:{ all -> 0x00b7 }
        r1 = r1.toString();	 Catch:{ all -> 0x00b7 }
        r0.<init>(r1);	 Catch:{ all -> 0x00b7 }
        throw r0;	 Catch:{ all -> 0x00b7 }
    L_0x04e2:
        r8.nextToken();	 Catch:{ all -> 0x00b7 }
        r3 = r12.parse();	 Catch:{ all -> 0x00b7 }
        r0 = r13.getClass();	 Catch:{ all -> 0x00b7 }
        r4 = com.alibaba.fastjson.JSONObject.class;
        if (r0 != r4) goto L_0x0576;
    L_0x04f1:
        r0 = r1.toString();	 Catch:{ all -> 0x00b7 }
    L_0x04f5:
        r13.put(r0, r3);	 Catch:{ all -> 0x00b7 }
        r1 = r8.token();	 Catch:{ all -> 0x00b7 }
        r3 = 13;
        if (r1 != r3) goto L_0x0508;
    L_0x0500:
        r8.nextToken();	 Catch:{ all -> 0x00b7 }
        r12.setContext(r2);
        goto L_0x000e;
    L_0x0508:
        r1 = r8.token();	 Catch:{ all -> 0x00b7 }
        r3 = 16;
        if (r1 != r3) goto L_0x0513;
    L_0x0510:
        r0 = r7;
        goto L_0x005a;
    L_0x0513:
        r1 = new com.alibaba.fastjson.JSONException;	 Catch:{ all -> 0x00b7 }
        r3 = new java.lang.StringBuilder;	 Catch:{ all -> 0x00b7 }
        r3.<init>();	 Catch:{ all -> 0x00b7 }
        r4 = "syntax error, position at ";
        r3 = r3.append(r4);	 Catch:{ all -> 0x00b7 }
        r4 = r8.pos();	 Catch:{ all -> 0x00b7 }
        r3 = r3.append(r4);	 Catch:{ all -> 0x00b7 }
        r4 = ", name ";
        r3 = r3.append(r4);	 Catch:{ all -> 0x00b7 }
        r0 = r3.append(r0);	 Catch:{ all -> 0x00b7 }
        r0 = r0.toString();	 Catch:{ all -> 0x00b7 }
        r1.<init>(r0);	 Catch:{ all -> 0x00b7 }
        throw r1;	 Catch:{ all -> 0x00b7 }
    L_0x053a:
        r4 = 125; // 0x7d float:1.75E-43 double:6.2E-322;
        if (r3 != r4) goto L_0x054f;
    L_0x053e:
        r8.next();	 Catch:{ all -> 0x00b7 }
        r8.resetStringPosition();	 Catch:{ all -> 0x00b7 }
        r8.nextToken();	 Catch:{ all -> 0x00b7 }
        r12.setContext(r0, r1);	 Catch:{ all -> 0x00b7 }
        r12.setContext(r2);
        goto L_0x000e;
    L_0x054f:
        r0 = new com.alibaba.fastjson.JSONException;	 Catch:{ all -> 0x00b7 }
        r3 = new java.lang.StringBuilder;	 Catch:{ all -> 0x00b7 }
        r3.<init>();	 Catch:{ all -> 0x00b7 }
        r4 = "syntax error, position at ";
        r3 = r3.append(r4);	 Catch:{ all -> 0x00b7 }
        r4 = r8.pos();	 Catch:{ all -> 0x00b7 }
        r3 = r3.append(r4);	 Catch:{ all -> 0x00b7 }
        r4 = ", name ";
        r3 = r3.append(r4);	 Catch:{ all -> 0x00b7 }
        r1 = r3.append(r1);	 Catch:{ all -> 0x00b7 }
        r1 = r1.toString();	 Catch:{ all -> 0x00b7 }
        r0.<init>(r1);	 Catch:{ all -> 0x00b7 }
        throw r0;	 Catch:{ all -> 0x00b7 }
    L_0x0576:
        r0 = r1;
        goto L_0x04f5;
    L_0x0579:
        r0 = r7;
        goto L_0x005a;
    L_0x057c:
        r11 = r0;
        r0 = r3;
        r3 = r11;
        goto L_0x0472;
    L_0x0581:
        r5 = r0;
        goto L_0x0450;
    L_0x0584:
        r0 = r2;
        goto L_0x036e;
    L_0x0587:
        r7 = r0;
        goto L_0x0371;
    L_0x058a:
        r0 = r1;
        goto L_0x022d;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.parser.DefaultJSONParser.parseObject(java.util.Map, java.lang.Object):java.lang.Object");
    }

    public ParserConfig getConfig() {
        return this.config;
    }

    public void setConfig(ParserConfig parserConfig) {
        this.config = parserConfig;
    }

    public <T> T parseObject(Class<T> cls) {
        return parseObject((Type) cls, null);
    }

    public <T> T parseObject(Type type) {
        return parseObject(type, null);
    }

    public <T> T parseObject(Type type, Object obj) {
        if (this.lexer.token() == 8) {
            this.lexer.nextToken();
            return null;
        }
        if (this.lexer.token() == 4) {
            type = TypeUtils.unwrap(type);
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
        } catch (Throwable th) {
            JSONException jSONException = new JSONException(th.getMessage(), th);
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
        if (this.lexer.token() == 21 || this.lexer.token() == 22) {
            this.lexer.nextToken();
        }
        if (this.lexer.token() != 14) {
            throw new JSONException("exepct '[', but " + JSONToken.name(this.lexer.token()) + ", " + this.lexer.info());
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
            this.lexer.nextToken(objectDeserializer.getFastMatchToken());
        }
        ParseContext parseContext = this.context;
        setContext(collection, obj);
        int i = 0;
        while (true) {
            if (this.lexer.isEnabled(Feature.AllowArbitraryCommas)) {
                while (this.lexer.token() == 16) {
                    this.lexer.nextToken();
                }
            }
            try {
                if (this.lexer.token() == 15) {
                    break;
                }
                if (Integer.TYPE == type) {
                    collection.add(IntegerCodec.instance.deserialze(this, null, null));
                } else if (String.class == type) {
                    if (this.lexer.token() == 4) {
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
                    if (this.lexer.token() == 8) {
                        this.lexer.nextToken();
                        r1 = null;
                    } else {
                        r1 = objectDeserializer.deserialze(this, type, Integer.valueOf(i));
                    }
                    collection.add(r1);
                    checkListResolve(collection);
                }
                if (this.lexer.token() == 16) {
                    this.lexer.nextToken(objectDeserializer.getFastMatchToken());
                }
                i++;
            } finally {
                setContext(parseContext);
            }
        }
        this.lexer.nextToken(16);
    }

    public Object[] parseArray(Type[] typeArr) {
        if (this.lexer.token() == 8) {
            this.lexer.nextToken(16);
            return null;
        } else if (this.lexer.token() != 14) {
            throw new JSONException("syntax error : " + this.lexer.tokenName());
        } else {
            Object[] objArr = new Object[typeArr.length];
            if (typeArr.length == 0) {
                this.lexer.nextToken(15);
                if (this.lexer.token() != 15) {
                    throw new JSONException("syntax error");
                }
                this.lexer.nextToken(16);
                return new Object[0];
            }
            this.lexer.nextToken(2);
            int i = 0;
            while (i < typeArr.length) {
                String str;
                if (this.lexer.token() == 8) {
                    this.lexer.nextToken(16);
                    str = null;
                } else {
                    Type type = typeArr[i];
                    if (type == Integer.TYPE || type == Integer.class) {
                        if (this.lexer.token() == 2) {
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
                        if (!isArray || this.lexer.token() == 14) {
                            str = this.config.getDeserializer(type).deserialze(this, type, null);
                        } else {
                            Object arrayList = new ArrayList();
                            ObjectDeserializer deserializer = this.config.getDeserializer(componentType);
                            int fastMatchToken = deserializer.getFastMatchToken();
                            if (this.lexer.token() != 15) {
                                while (true) {
                                    arrayList.add(deserializer.deserialze(this, type, null));
                                    if (this.lexer.token() != 16) {
                                        break;
                                    }
                                    this.lexer.nextToken(fastMatchToken);
                                }
                                if (this.lexer.token() != 15) {
                                    throw new JSONException("syntax error :" + JSONToken.name(this.lexer.token()));
                                }
                            }
                            str = TypeUtils.cast(arrayList, type, this.config);
                        }
                    } else if (this.lexer.token() == 4) {
                        str = this.lexer.stringVal();
                        this.lexer.nextToken(16);
                    } else {
                        str = TypeUtils.cast(parse(), type, this.config);
                    }
                }
                objArr[i] = str;
                if (this.lexer.token() == 15) {
                    break;
                } else if (this.lexer.token() != 16) {
                    throw new JSONException("syntax error :" + JSONToken.name(this.lexer.token()));
                } else {
                    if (i == typeArr.length - 1) {
                        this.lexer.nextToken(15);
                    } else {
                        this.lexer.nextToken(2);
                    }
                    i++;
                }
            }
            if (this.lexer.token() != 15) {
                throw new JSONException("syntax error");
            }
            this.lexer.nextToken(16);
            return objArr;
        }
    }

    public void parseObject(Object obj) {
        JavaBeanDeserializer javaBeanDeserializer;
        Type type = obj.getClass();
        ObjectDeserializer deserializer = this.config.getDeserializer(type);
        if (deserializer instanceof JavaBeanDeserializer) {
            javaBeanDeserializer = (JavaBeanDeserializer) deserializer;
        } else if (deserializer instanceof ASMJavaBeanDeserializer) {
            javaBeanDeserializer = ((ASMJavaBeanDeserializer) deserializer).getInnterSerializer();
        } else {
            javaBeanDeserializer = null;
        }
        if (this.lexer.token() == 12 || this.lexer.token() == 16) {
            while (true) {
                FieldDeserializer fieldDeserializer;
                String scanSymbol = this.lexer.scanSymbol(this.symbolTable);
                if (scanSymbol == null) {
                    if (this.lexer.token() == 13) {
                        this.lexer.nextToken(16);
                        return;
                    } else if (this.lexer.token() == 16 && this.lexer.isEnabled(Feature.AllowArbitraryCommas)) {
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
                    Type type2 = fieldDeserializer.fieldInfo.fieldType;
                    if (cls == Integer.TYPE) {
                        this.lexer.nextTokenWithColon(2);
                        deserialze = IntegerCodec.instance.deserialze(this, type2, null);
                    } else if (cls == String.class) {
                        this.lexer.nextTokenWithColon(4);
                        deserialze = StringCodec.deserialze(this);
                    } else if (cls == Long.TYPE) {
                        this.lexer.nextTokenWithColon(2);
                        deserialze = LongCodec.instance.deserialze(this, type2, null);
                    } else {
                        ObjectDeserializer deserializer2 = this.config.getDeserializer(cls, type2);
                        this.lexer.nextTokenWithColon(deserializer2.getFastMatchToken());
                        deserialze = deserializer2.deserialze(this, type2, null);
                    }
                    fieldDeserializer.setValue(obj, deserialze);
                    if (this.lexer.token() != 16 && this.lexer.token() == 13) {
                        this.lexer.nextToken(16);
                        return;
                    }
                } else if (this.lexer.isEnabled(Feature.IgnoreNotMatch)) {
                    this.lexer.nextTokenWithColon();
                    parse();
                    if (this.lexer.token() == 13) {
                        this.lexer.nextToken();
                        return;
                    }
                } else {
                    throw new JSONException("setter not found, class " + type.getName() + ", property " + scanSymbol);
                }
            }
        }
        throw new JSONException("syntax error, expect {, actual " + this.lexer.tokenName());
    }

    public Object parseArrayWithType(Type type) {
        if (this.lexer.token() == 8) {
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

    public void acceptType(String str) {
        JSONLexer jSONLexer = this.lexer;
        jSONLexer.nextTokenWithColon();
        if (jSONLexer.token() != 4) {
            throw new JSONException("type not match error");
        } else if (str.equals(jSONLexer.stringVal())) {
            jSONLexer.nextToken();
            if (jSONLexer.token() == 16) {
                jSONLexer.nextToken();
            }
        } else {
            throw new JSONException("type not match error");
        }
    }

    public int getResolveStatus() {
        return this.resolveStatus;
    }

    public void setResolveStatus(int i) {
        this.resolveStatus = i;
    }

    public Object getObject(String str) {
        for (int i = 0; i < this.contextArrayIndex; i++) {
            if (str.equals(this.contextArray[i].toString())) {
                return this.contextArray[i].object;
            }
        }
        return null;
    }

    public void checkListResolve(Collection collection) {
        if (this.resolveStatus != 1) {
            return;
        }
        if (collection instanceof List) {
            int size = collection.size() - 1;
            List list = (List) collection;
            ResolveTask lastResolveTask = getLastResolveTask();
            lastResolveTask.fieldDeserializer = new ResolveFieldDeserializer(this, list, size);
            lastResolveTask.ownerContext = this.context;
            setResolveStatus(0);
            return;
        }
        ResolveTask lastResolveTask2 = getLastResolveTask();
        lastResolveTask2.fieldDeserializer = new ResolveFieldDeserializer(collection);
        lastResolveTask2.ownerContext = this.context;
        setResolveStatus(0);
    }

    public void checkMapResolve(Map map, Object obj) {
        if (this.resolveStatus == 1) {
            FieldDeserializer resolveFieldDeserializer = new ResolveFieldDeserializer(map, obj);
            ResolveTask lastResolveTask = getLastResolveTask();
            lastResolveTask.fieldDeserializer = resolveFieldDeserializer;
            lastResolveTask.ownerContext = this.context;
            setResolveStatus(0);
        }
    }

    public Object parseObject(Map map) {
        return parseObject(map, null);
    }

    public JSONObject parseObject() {
        return (JSONObject) parseObject(new JSONObject(this.lexer.isEnabled(Feature.OrderedField)));
    }

    public final void parseArray(Collection collection) {
        parseArray(collection, null);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void parseArray(java.util.Collection r9, java.lang.Object r10) {
        /*
        r8 = this;
        r1 = 0;
        r0 = 0;
        r5 = 4;
        r7 = 16;
        r3 = r8.lexer;
        r2 = r3.token();
        r4 = 21;
        if (r2 == r4) goto L_0x0017;
    L_0x000f:
        r2 = r3.token();
        r4 = 22;
        if (r2 != r4) goto L_0x001a;
    L_0x0017:
        r3.nextToken();
    L_0x001a:
        r2 = r3.token();
        r4 = 14;
        if (r2 == r4) goto L_0x0051;
    L_0x0022:
        r0 = new com.alibaba.fastjson.JSONException;
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "syntax error, expect [, actual ";
        r1 = r1.append(r2);
        r2 = r3.token();
        r2 = com.alibaba.fastjson.parser.JSONToken.name(r2);
        r1 = r1.append(r2);
        r2 = ", pos ";
        r1 = r1.append(r2);
        r2 = r3.pos();
        r1 = r1.append(r2);
        r1 = r1.toString();
        r0.<init>(r1);
        throw r0;
    L_0x0051:
        r3.nextToken(r5);
        r4 = r8.context;
        r8.setContext(r9, r10);
        r2 = r0;
    L_0x005a:
        r0 = com.alibaba.fastjson.parser.Feature.AllowArbitraryCommas;	 Catch:{ all -> 0x006c }
        r0 = r3.isEnabled(r0);	 Catch:{ all -> 0x006c }
        if (r0 == 0) goto L_0x0071;
    L_0x0062:
        r0 = r3.token();	 Catch:{ all -> 0x006c }
        if (r0 != r7) goto L_0x0071;
    L_0x0068:
        r3.nextToken();	 Catch:{ all -> 0x006c }
        goto L_0x0062;
    L_0x006c:
        r0 = move-exception;
        r8.setContext(r4);
        throw r0;
    L_0x0071:
        r0 = r3.token();	 Catch:{ all -> 0x006c }
        switch(r0) {
            case 2: goto L_0x0090;
            case 3: goto L_0x009a;
            case 4: goto L_0x00b3;
            case 5: goto L_0x0078;
            case 6: goto L_0x00db;
            case 7: goto L_0x00e3;
            case 8: goto L_0x011a;
            case 9: goto L_0x0078;
            case 10: goto L_0x0078;
            case 11: goto L_0x0078;
            case 12: goto L_0x00eb;
            case 13: goto L_0x0078;
            case 14: goto L_0x0100;
            case 15: goto L_0x0128;
            case 16: goto L_0x0078;
            case 17: goto L_0x0078;
            case 18: goto L_0x0078;
            case 19: goto L_0x0078;
            case 20: goto L_0x0131;
            case 21: goto L_0x0078;
            case 22: goto L_0x0078;
            case 23: goto L_0x0121;
            default: goto L_0x0078;
        };	 Catch:{ all -> 0x006c }
    L_0x0078:
        r0 = r8.parse();	 Catch:{ all -> 0x006c }
    L_0x007c:
        r9.add(r0);	 Catch:{ all -> 0x006c }
        r8.checkListResolve(r9);	 Catch:{ all -> 0x006c }
        r0 = r3.token();	 Catch:{ all -> 0x006c }
        if (r0 != r7) goto L_0x008c;
    L_0x0088:
        r0 = 4;
        r3.nextToken(r0);	 Catch:{ all -> 0x006c }
    L_0x008c:
        r0 = r2 + 1;
        r2 = r0;
        goto L_0x005a;
    L_0x0090:
        r0 = r3.integerValue();	 Catch:{ all -> 0x006c }
        r5 = 16;
        r3.nextToken(r5);	 Catch:{ all -> 0x006c }
        goto L_0x007c;
    L_0x009a:
        r0 = com.alibaba.fastjson.parser.Feature.UseBigDecimal;	 Catch:{ all -> 0x006c }
        r0 = r3.isEnabled(r0);	 Catch:{ all -> 0x006c }
        if (r0 == 0) goto L_0x00ad;
    L_0x00a2:
        r0 = 1;
        r0 = r3.decimalValue(r0);	 Catch:{ all -> 0x006c }
    L_0x00a7:
        r5 = 16;
        r3.nextToken(r5);	 Catch:{ all -> 0x006c }
        goto L_0x007c;
    L_0x00ad:
        r0 = 0;
        r0 = r3.decimalValue(r0);	 Catch:{ all -> 0x006c }
        goto L_0x00a7;
    L_0x00b3:
        r0 = r3.stringVal();	 Catch:{ all -> 0x006c }
        r5 = 16;
        r3.nextToken(r5);	 Catch:{ all -> 0x006c }
        r5 = com.alibaba.fastjson.parser.Feature.AllowISO8601DateFormat;	 Catch:{ all -> 0x006c }
        r5 = r3.isEnabled(r5);	 Catch:{ all -> 0x006c }
        if (r5 == 0) goto L_0x007c;
    L_0x00c4:
        r5 = new com.alibaba.fastjson.parser.JSONScanner;	 Catch:{ all -> 0x006c }
        r5.<init>(r0);	 Catch:{ all -> 0x006c }
        r6 = r5.scanISO8601DateIfMatch();	 Catch:{ all -> 0x006c }
        if (r6 == 0) goto L_0x00d7;
    L_0x00cf:
        r0 = r5.getCalendar();	 Catch:{ all -> 0x006c }
        r0 = r0.getTime();	 Catch:{ all -> 0x006c }
    L_0x00d7:
        r5.close();	 Catch:{ all -> 0x006c }
        goto L_0x007c;
    L_0x00db:
        r0 = java.lang.Boolean.TRUE;	 Catch:{ all -> 0x006c }
        r5 = 16;
        r3.nextToken(r5);	 Catch:{ all -> 0x006c }
        goto L_0x007c;
    L_0x00e3:
        r0 = java.lang.Boolean.FALSE;	 Catch:{ all -> 0x006c }
        r5 = 16;
        r3.nextToken(r5);	 Catch:{ all -> 0x006c }
        goto L_0x007c;
    L_0x00eb:
        r0 = new com.alibaba.fastjson.JSONObject;	 Catch:{ all -> 0x006c }
        r5 = com.alibaba.fastjson.parser.Feature.OrderedField;	 Catch:{ all -> 0x006c }
        r5 = r3.isEnabled(r5);	 Catch:{ all -> 0x006c }
        r0.<init>(r5);	 Catch:{ all -> 0x006c }
        r5 = java.lang.Integer.valueOf(r2);	 Catch:{ all -> 0x006c }
        r0 = r8.parseObject(r0, r5);	 Catch:{ all -> 0x006c }
        goto L_0x007c;
    L_0x0100:
        r0 = new com.alibaba.fastjson.JSONArray;	 Catch:{ all -> 0x006c }
        r0.<init>();	 Catch:{ all -> 0x006c }
        r5 = java.lang.Integer.valueOf(r2);	 Catch:{ all -> 0x006c }
        r8.parseArray(r0, r5);	 Catch:{ all -> 0x006c }
        r5 = com.alibaba.fastjson.parser.Feature.UseObjectArray;	 Catch:{ all -> 0x006c }
        r5 = r3.isEnabled(r5);	 Catch:{ all -> 0x006c }
        if (r5 == 0) goto L_0x007c;
    L_0x0114:
        r0 = r0.toArray();	 Catch:{ all -> 0x006c }
        goto L_0x007c;
    L_0x011a:
        r0 = 4;
        r3.nextToken(r0);	 Catch:{ all -> 0x006c }
        r0 = r1;
        goto L_0x007c;
    L_0x0121:
        r0 = 4;
        r3.nextToken(r0);	 Catch:{ all -> 0x006c }
        r0 = r1;
        goto L_0x007c;
    L_0x0128:
        r0 = 16;
        r3.nextToken(r0);	 Catch:{ all -> 0x006c }
        r8.setContext(r4);
        return;
    L_0x0131:
        r0 = new com.alibaba.fastjson.JSONException;	 Catch:{ all -> 0x006c }
        r1 = "unclosed jsonArray";
        r0.<init>(r1);	 Catch:{ all -> 0x006c }
        throw r0;	 Catch:{ all -> 0x006c }
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.parser.DefaultJSONParser.parseArray(java.util.Collection, java.lang.Object):void");
    }

    public ParseContext getContext() {
        return this.context;
    }

    public List<ResolveTask> getResolveTaskList() {
        if (this.resolveTaskList == null) {
            this.resolveTaskList = new ArrayList(2);
        }
        return this.resolveTaskList;
    }

    public void addResolveTask(ResolveTask resolveTask) {
        if (this.resolveTaskList == null) {
            this.resolveTaskList = new ArrayList(2);
        }
        this.resolveTaskList.add(resolveTask);
    }

    public ResolveTask getLastResolveTask() {
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

    public FieldTypeResolver getFieldTypeResolver() {
        return this.fieldTypeResolver;
    }

    public void setFieldTypeResolver(FieldTypeResolver fieldTypeResolver) {
        this.fieldTypeResolver = fieldTypeResolver;
    }

    public void setContext(ParseContext parseContext) {
        if (!this.lexer.isEnabled(Feature.DisableCircularReferenceDetect)) {
            this.context = parseContext;
        }
    }

    public void popContext() {
        if (!this.lexer.isEnabled(Feature.DisableCircularReferenceDetect)) {
            this.context = this.context.parent;
            this.contextArray[this.contextArrayIndex - 1] = null;
            this.contextArrayIndex--;
        }
    }

    public ParseContext setContext(Object obj, Object obj2) {
        if (this.lexer.isEnabled(Feature.DisableCircularReferenceDetect)) {
            return null;
        }
        return setContext(this.context, obj, obj2);
    }

    public ParseContext setContext(ParseContext parseContext, Object obj, Object obj2) {
        if (this.lexer.isEnabled(Feature.DisableCircularReferenceDetect)) {
            return null;
        }
        this.context = new ParseContext(parseContext, obj, obj2);
        addContext(this.context);
        return this.context;
    }

    private void addContext(ParseContext parseContext) {
        int i = this.contextArrayIndex;
        this.contextArrayIndex = i + 1;
        if (this.contextArray == null) {
            this.contextArray = new ParseContext[8];
        } else if (i >= this.contextArray.length) {
            Object obj = new ParseContext[((this.contextArray.length * 3) / 2)];
            System.arraycopy(this.contextArray, 0, obj, 0, this.contextArray.length);
            this.contextArray = obj;
        }
        this.contextArray[i] = parseContext;
    }

    public Object parse() {
        return parse(null);
    }

    public Object parseKey() {
        if (this.lexer.token() != 18) {
            return parse(null);
        }
        String stringVal = this.lexer.stringVal();
        this.lexer.nextToken(16);
        return stringVal;
    }

    public Object parse(Object obj) {
        JSONLexer jSONLexer = this.lexer;
        Object integerValue;
        Collection jSONArray;
        switch (jSONLexer.token()) {
            case 2:
                integerValue = jSONLexer.integerValue();
                jSONLexer.nextToken();
                return integerValue;
            case 3:
                integerValue = jSONLexer.decimalValue(jSONLexer.isEnabled(Feature.UseBigDecimal));
                jSONLexer.nextToken();
                return integerValue;
            case 4:
                integerValue = jSONLexer.stringVal();
                jSONLexer.nextToken(16);
                if (!jSONLexer.isEnabled(Feature.AllowISO8601DateFormat)) {
                    return integerValue;
                }
                JSONScanner jSONScanner = new JSONScanner(integerValue);
                try {
                    if (jSONScanner.scanISO8601DateIfMatch()) {
                        integerValue = jSONScanner.getCalendar().getTime();
                        return integerValue;
                    }
                    jSONScanner.close();
                    return integerValue;
                } finally {
                    jSONScanner.close();
                }
            case 6:
                jSONLexer.nextToken();
                return Boolean.TRUE;
            case 7:
                jSONLexer.nextToken();
                return Boolean.FALSE;
            case 8:
                jSONLexer.nextToken();
                return null;
            case 9:
                jSONLexer.nextToken(18);
                if (jSONLexer.token() != 18) {
                    throw new JSONException("syntax error");
                }
                jSONLexer.nextToken(10);
                accept(10);
                long longValue = jSONLexer.integerValue().longValue();
                accept(2);
                accept(11);
                return new Date(longValue);
            case 12:
                return parseObject(new JSONObject(jSONLexer.isEnabled(Feature.OrderedField)), obj);
            case 14:
                jSONArray = new JSONArray();
                parseArray(jSONArray, obj);
                if (jSONLexer.isEnabled(Feature.UseObjectArray)) {
                    return jSONArray.toArray();
                }
                return jSONArray;
            case 20:
                if (jSONLexer.isBlankInput()) {
                    return null;
                }
                throw new JSONException("unterminated json string, pos " + jSONLexer.getBufferPosition());
            case 21:
                jSONLexer.nextToken();
                jSONArray = new HashSet();
                parseArray(jSONArray, obj);
                return jSONArray;
            case 22:
                jSONLexer.nextToken();
                jSONArray = new TreeSet();
                parseArray(jSONArray, obj);
                return jSONArray;
            case 23:
                jSONLexer.nextToken();
                return null;
            default:
                throw new JSONException("syntax error, pos " + jSONLexer.getBufferPosition());
        }
    }

    public void config(Feature feature, boolean z) {
        this.lexer.config(feature, z);
    }

    public boolean isEnabled(Feature feature) {
        return this.lexer.isEnabled(feature);
    }

    public JSONLexer getLexer() {
        return this.lexer;
    }

    public final void accept(int i) {
        JSONLexer jSONLexer = this.lexer;
        if (jSONLexer.token() == i) {
            jSONLexer.nextToken();
            return;
        }
        throw new JSONException("syntax error, expect " + JSONToken.name(i) + ", actual " + JSONToken.name(jSONLexer.token()));
    }

    public final void accept(int i, int i2) {
        JSONLexer jSONLexer = this.lexer;
        if (jSONLexer.token() == i) {
            jSONLexer.nextToken(i2);
            return;
        }
        throw new JSONException("syntax error, expect " + JSONToken.name(i) + ", actual " + JSONToken.name(jSONLexer.token()));
    }

    public void close() {
        JSONLexer jSONLexer = this.lexer;
        try {
            if (!jSONLexer.isEnabled(Feature.AutoCloseSource) || jSONLexer.token() == 20) {
                jSONLexer.close();
                return;
            }
            throw new JSONException("not close json text, token : " + JSONToken.name(jSONLexer.token()));
        } catch (Throwable th) {
            jSONLexer.close();
        }
    }

    public void handleResovleTask(Object obj) {
        if (this.resolveTaskList != null) {
            int size = this.resolveTaskList.size();
            for (int i = 0; i < size; i++) {
                Object object;
                ResolveTask resolveTask = (ResolveTask) this.resolveTaskList.get(i);
                String access$000 = resolveTask.referenceValue;
                Object obj2 = null;
                if (resolveTask.ownerContext != null) {
                    obj2 = resolveTask.ownerContext.object;
                }
                if (access$000.startsWith("$")) {
                    object = getObject(access$000);
                } else {
                    object = resolveTask.context.object;
                }
                FieldDeserializer fieldDeserializer = resolveTask.fieldDeserializer;
                if (fieldDeserializer != null) {
                    fieldDeserializer.setValue(obj2, object);
                }
            }
        }
    }

    public void parseExtra(Object obj, String str) {
        Object parse;
        this.lexer.nextTokenWithColon();
        Type type = null;
        if (this.extraTypeProviders != null) {
            for (ExtraTypeProvider extraType : this.extraTypeProviders) {
                type = extraType.getExtraType(obj, str);
            }
        }
        if (type == null) {
            parse = parse();
        } else {
            parse = parseObject(type);
        }
        if (obj instanceof ExtraProcessable) {
            ((ExtraProcessable) obj).processExtra(str, parse);
        } else if (this.extraProcessors != null) {
            for (ExtraProcessor processExtra : this.extraProcessors) {
                processExtra.processExtra(obj, str, parse);
            }
        }
    }
}
