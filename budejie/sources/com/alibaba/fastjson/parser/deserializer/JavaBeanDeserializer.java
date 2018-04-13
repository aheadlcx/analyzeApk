package com.alibaba.fastjson.parser.deserializer;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.parser.JSONLexer;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.util.FieldInfo;
import com.alibaba.fastjson.util.JavaBeanInfo;
import com.alibaba.fastjson.util.TypeUtils;
import com.umeng.analytics.pro.x;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.Map.Entry;

public class JavaBeanDeserializer implements ObjectDeserializer {
    public final JavaBeanInfo beanInfo;
    protected final Class<?> clazz;
    private final FieldDeserializer[] fieldDeserializers;
    protected final FieldDeserializer[] sortedFieldDeserializers = new FieldDeserializer[this.beanInfo.sortedFields.length];

    public JavaBeanDeserializer(ParserConfig parserConfig, Class<?> cls, Type type) {
        int i;
        int i2 = 0;
        this.clazz = cls;
        this.beanInfo = JavaBeanInfo.build(cls, type);
        int length = this.beanInfo.sortedFields.length;
        for (i = 0; i < length; i++) {
            this.sortedFieldDeserializers[i] = parserConfig.createFieldDeserializer(parserConfig, this.beanInfo, this.beanInfo.sortedFields[i]);
        }
        this.fieldDeserializers = new FieldDeserializer[this.beanInfo.fields.length];
        i = this.beanInfo.fields.length;
        while (i2 < i) {
            this.fieldDeserializers[i2] = getFieldDeserializer(this.beanInfo.fields[i2].name);
            i2++;
        }
    }

    public FieldDeserializer getFieldDeserializer(String str) {
        if (str == null) {
            return null;
        }
        int i = 0;
        int length = this.sortedFieldDeserializers.length - 1;
        while (i <= length) {
            int i2 = (i + length) >>> 1;
            int compareTo = this.sortedFieldDeserializers[i2].fieldInfo.name.compareTo(str);
            if (compareTo < 0) {
                i = i2 + 1;
            } else if (compareTo <= 0) {
                return this.sortedFieldDeserializers[i2];
            } else {
                length = i2 - 1;
            }
        }
        return null;
    }

    public Object createInstance(DefaultJSONParser defaultJSONParser, Type type) {
        int i = 0;
        if ((type instanceof Class) && this.clazz.isInterface()) {
            Class cls = (Class) type;
            return Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class[]{cls}, new JSONObject());
        } else if (this.beanInfo.defaultConstructor == null) {
            return null;
        } else {
            try {
                Object newInstance;
                Constructor constructor = this.beanInfo.defaultConstructor;
                if (this.beanInfo.defaultConstructorParameterSize == 0) {
                    newInstance = constructor.newInstance(new Object[0]);
                } else {
                    newInstance = constructor.newInstance(new Object[]{defaultJSONParser.getContext().object});
                }
                if (defaultJSONParser == null || !defaultJSONParser.lexer.isEnabled(Feature.InitStringFieldAsEmpty)) {
                    return newInstance;
                }
                FieldInfo[] fieldInfoArr = this.beanInfo.fields;
                int length = fieldInfoArr.length;
                while (i < length) {
                    FieldInfo fieldInfo = fieldInfoArr[i];
                    if (fieldInfo.fieldClass == String.class) {
                        try {
                            fieldInfo.set(newInstance, "");
                        } catch (Throwable e) {
                            throw new JSONException("create instance error, class " + this.clazz.getName(), e);
                        }
                    }
                    i++;
                }
                return newInstance;
            } catch (Throwable e2) {
                throw new JSONException("create instance error, class " + this.clazz.getName(), e2);
            }
        }
    }

    public <T> T deserialze(DefaultJSONParser defaultJSONParser, Type type, Object obj) {
        return deserialze(defaultJSONParser, type, obj, null);
    }

    public <T> T deserialzeArrayMapping(DefaultJSONParser defaultJSONParser, Type type, Object obj, Object obj2) {
        JSONLexer jSONLexer = defaultJSONParser.lexer;
        if (jSONLexer.token() != 14) {
            throw new JSONException(x.aF);
        }
        Object createInstance = createInstance(defaultJSONParser, type);
        int length = this.sortedFieldDeserializers.length;
        int i = 0;
        while (i < length) {
            char c = i == length + -1 ? ']' : ',';
            FieldDeserializer fieldDeserializer = this.sortedFieldDeserializers[i];
            Class cls = fieldDeserializer.fieldInfo.fieldClass;
            if (cls == Integer.TYPE) {
                fieldDeserializer.setValue(createInstance, jSONLexer.scanInt(c));
            } else if (cls == String.class) {
                fieldDeserializer.setValue(createInstance, jSONLexer.scanString(c));
            } else if (cls == Long.TYPE) {
                fieldDeserializer.setValue(createInstance, jSONLexer.scanLong(c));
            } else if (cls.isEnum()) {
                fieldDeserializer.setValue(createInstance, jSONLexer.scanEnum(cls, defaultJSONParser.getSymbolTable(), c));
            } else {
                jSONLexer.nextToken(14);
                fieldDeserializer.setValue(createInstance, defaultJSONParser.parseObject(fieldDeserializer.fieldInfo.fieldType));
                if (c == ']') {
                    if (jSONLexer.token() != 15) {
                        throw new JSONException("syntax error");
                    }
                    jSONLexer.nextToken(16);
                } else if (c == ',' && jSONLexer.token() != 16) {
                    throw new JSONException("syntax error");
                }
            }
            i++;
        }
        jSONLexer.nextToken(16);
        return createInstance;
    }

    protected <T> T deserialze(com.alibaba.fastjson.parser.DefaultJSONParser r22, java.lang.reflect.Type r23, java.lang.Object r24, java.lang.Object r25) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:com.alibaba.fastjson.parser.deserializer.JavaBeanDeserializer.deserialze(com.alibaba.fastjson.parser.DefaultJSONParser, java.lang.reflect.Type, java.lang.Object, java.lang.Object):T. bs: [B:104:0x017c, B:318:0x0558]
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.searchTryCatchDominators(ProcessTryCatchRegions.java:86)
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:63)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:58)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:56)
	at jadx.core.ProcessClass.process(ProcessClass.java:39)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
*/
        /*
        r21 = this;
        r4 = com.alibaba.fastjson.JSON.class;
        r0 = r23;
        if (r0 == r4) goto L_0x000c;
    L_0x0006:
        r4 = com.alibaba.fastjson.JSONObject.class;
        r0 = r23;
        if (r0 != r4) goto L_0x0011;
    L_0x000c:
        r25 = r22.parse();
    L_0x0010:
        return r25;
    L_0x0011:
        r0 = r22;
        r4 = r0.lexer;
        r10 = r4;
        r10 = (com.alibaba.fastjson.parser.JSONLexerBase) r10;
        r6 = r10.token();
        r4 = 8;
        if (r6 != r4) goto L_0x0028;
    L_0x0020:
        r4 = 16;
        r10.nextToken(r4);
        r25 = 0;
        goto L_0x0010;
    L_0x0028:
        r4 = r22.getContext();
        if (r25 == 0) goto L_0x0599;
    L_0x002e:
        if (r4 == 0) goto L_0x0599;
    L_0x0030:
        r4 = r4.parent;
        r11 = r4;
    L_0x0033:
        r5 = 0;
        r9 = 0;
        r4 = 13;
        if (r6 != r4) goto L_0x0050;
    L_0x0039:
        r4 = 16;
        r10.nextToken(r4);	 Catch:{ all -> 0x00fa }
        if (r25 != 0) goto L_0x0044;	 Catch:{ all -> 0x00fa }
    L_0x0040:
        r25 = r21.createInstance(r22, r23);	 Catch:{ all -> 0x00fa }
    L_0x0044:
        if (r5 == 0) goto L_0x004a;
    L_0x0046:
        r0 = r25;
        r5.object = r0;
    L_0x004a:
        r0 = r22;
        r0.setContext(r11);
        goto L_0x0010;
    L_0x0050:
        r4 = 14;
        if (r6 != r4) goto L_0x0080;
    L_0x0054:
        r0 = r21;	 Catch:{ all -> 0x00fa }
        r4 = r0.beanInfo;	 Catch:{ all -> 0x00fa }
        r4 = r4.parserFeatures;	 Catch:{ all -> 0x00fa }
        r7 = com.alibaba.fastjson.parser.Feature.SupportArrayToBean;	 Catch:{ all -> 0x00fa }
        r7 = r7.mask;	 Catch:{ all -> 0x00fa }
        r4 = r4 & r7;	 Catch:{ all -> 0x00fa }
        if (r4 != 0) goto L_0x0069;	 Catch:{ all -> 0x00fa }
    L_0x0061:
        r4 = com.alibaba.fastjson.parser.Feature.SupportArrayToBean;	 Catch:{ all -> 0x00fa }
        r4 = r10.isEnabled(r4);	 Catch:{ all -> 0x00fa }
        if (r4 == 0) goto L_0x007e;	 Catch:{ all -> 0x00fa }
    L_0x0069:
        r4 = 1;	 Catch:{ all -> 0x00fa }
    L_0x006a:
        if (r4 == 0) goto L_0x0080;	 Catch:{ all -> 0x00fa }
    L_0x006c:
        r7 = r21.deserialzeArrayMapping(r22, r23, r24, r25);	 Catch:{ all -> 0x00fa }
        if (r5 == 0) goto L_0x0076;
    L_0x0072:
        r0 = r25;
        r5.object = r0;
    L_0x0076:
        r0 = r22;
        r0.setContext(r11);
        r25 = r7;
        goto L_0x0010;
    L_0x007e:
        r4 = 0;
        goto L_0x006a;
    L_0x0080:
        r4 = 12;
        if (r6 == r4) goto L_0x0107;
    L_0x0084:
        r4 = 16;
        if (r6 == r4) goto L_0x0107;
    L_0x0088:
        r4 = r10.isBlankInput();	 Catch:{ all -> 0x00fa }
        if (r4 == 0) goto L_0x009e;
    L_0x008e:
        r7 = 0;
        if (r5 == 0) goto L_0x0095;
    L_0x0091:
        r0 = r25;
        r5.object = r0;
    L_0x0095:
        r0 = r22;
        r0.setContext(r11);
        r25 = r7;
        goto L_0x0010;
    L_0x009e:
        r4 = 4;
        if (r6 != r4) goto L_0x00be;
    L_0x00a1:
        r4 = r10.stringVal();	 Catch:{ all -> 0x00fa }
        r4 = r4.length();	 Catch:{ all -> 0x00fa }
        if (r4 != 0) goto L_0x00be;	 Catch:{ all -> 0x00fa }
    L_0x00ab:
        r10.nextToken();	 Catch:{ all -> 0x00fa }
        r7 = 0;
        if (r5 == 0) goto L_0x00b5;
    L_0x00b1:
        r0 = r25;
        r5.object = r0;
    L_0x00b5:
        r0 = r22;
        r0.setContext(r11);
        r25 = r7;
        goto L_0x0010;
    L_0x00be:
        r4 = new java.lang.StringBuffer;	 Catch:{ all -> 0x00fa }
        r4.<init>();	 Catch:{ all -> 0x00fa }
        r6 = "syntax error, expect {, actual ";	 Catch:{ all -> 0x00fa }
        r4 = r4.append(r6);	 Catch:{ all -> 0x00fa }
        r6 = r10.tokenName();	 Catch:{ all -> 0x00fa }
        r4 = r4.append(r6);	 Catch:{ all -> 0x00fa }
        r6 = ", pos ";	 Catch:{ all -> 0x00fa }
        r4 = r4.append(r6);	 Catch:{ all -> 0x00fa }
        r6 = r10.pos();	 Catch:{ all -> 0x00fa }
        r4 = r4.append(r6);	 Catch:{ all -> 0x00fa }
        r0 = r24;	 Catch:{ all -> 0x00fa }
        r6 = r0 instanceof java.lang.String;	 Catch:{ all -> 0x00fa }
        if (r6 == 0) goto L_0x00f0;	 Catch:{ all -> 0x00fa }
    L_0x00e5:
        r6 = ", fieldName ";	 Catch:{ all -> 0x00fa }
        r6 = r4.append(r6);	 Catch:{ all -> 0x00fa }
        r0 = r24;	 Catch:{ all -> 0x00fa }
        r6.append(r0);	 Catch:{ all -> 0x00fa }
    L_0x00f0:
        r6 = new com.alibaba.fastjson.JSONException;	 Catch:{ all -> 0x00fa }
        r4 = r4.toString();	 Catch:{ all -> 0x00fa }
        r6.<init>(r4);	 Catch:{ all -> 0x00fa }
        throw r6;	 Catch:{ all -> 0x00fa }
    L_0x00fa:
        r4 = move-exception;
        r7 = r25;
    L_0x00fd:
        if (r5 == 0) goto L_0x0101;
    L_0x00ff:
        r5.object = r7;
    L_0x0101:
        r0 = r22;
        r0.setContext(r11);
        throw r4;
    L_0x0107:
        r0 = r22;	 Catch:{ all -> 0x00fa }
        r4 = r0.resolveStatus;	 Catch:{ all -> 0x00fa }
        r6 = 2;	 Catch:{ all -> 0x00fa }
        if (r4 != r6) goto L_0x0113;	 Catch:{ all -> 0x00fa }
    L_0x010e:
        r4 = 0;	 Catch:{ all -> 0x00fa }
        r0 = r22;	 Catch:{ all -> 0x00fa }
        r0.resolveStatus = r4;	 Catch:{ all -> 0x00fa }
    L_0x0113:
        r4 = 0;
        r17 = r4;
        r7 = r25;
    L_0x0118:
        r12 = 0;
        r8 = 0;
        r6 = 0;
        r4 = 0;
        r0 = r21;	 Catch:{ all -> 0x02ef }
        r13 = r0.sortedFieldDeserializers;	 Catch:{ all -> 0x02ef }
        r13 = r13.length;	 Catch:{ all -> 0x02ef }
        r0 = r17;	 Catch:{ all -> 0x02ef }
        if (r0 >= r13) goto L_0x0590;	 Catch:{ all -> 0x02ef }
    L_0x0125:
        r0 = r21;	 Catch:{ all -> 0x02ef }
        r4 = r0.sortedFieldDeserializers;	 Catch:{ all -> 0x02ef }
        r8 = r4[r17];	 Catch:{ all -> 0x02ef }
        r6 = r8.fieldInfo;	 Catch:{ all -> 0x02ef }
        r4 = r6.fieldClass;	 Catch:{ all -> 0x02ef }
        r14 = r4;	 Catch:{ all -> 0x02ef }
        r15 = r6;	 Catch:{ all -> 0x02ef }
        r16 = r8;	 Catch:{ all -> 0x02ef }
    L_0x0133:
        r8 = 0;	 Catch:{ all -> 0x02ef }
        r6 = 0;	 Catch:{ all -> 0x02ef }
        r4 = 0;	 Catch:{ all -> 0x02ef }
        if (r16 == 0) goto L_0x058c;	 Catch:{ all -> 0x02ef }
    L_0x0138:
        r13 = r15.name_chars;	 Catch:{ all -> 0x02ef }
        r18 = java.lang.Integer.TYPE;	 Catch:{ all -> 0x02ef }
        r0 = r18;	 Catch:{ all -> 0x02ef }
        if (r14 == r0) goto L_0x0146;	 Catch:{ all -> 0x02ef }
    L_0x0140:
        r18 = java.lang.Integer.class;	 Catch:{ all -> 0x02ef }
        r0 = r18;	 Catch:{ all -> 0x02ef }
        if (r14 != r0) goto L_0x01a3;	 Catch:{ all -> 0x02ef }
    L_0x0146:
        r4 = r10.scanFieldInt(r13);	 Catch:{ all -> 0x02ef }
        r4 = java.lang.Integer.valueOf(r4);	 Catch:{ all -> 0x02ef }
        r13 = r10.matchStat;	 Catch:{ all -> 0x02ef }
        if (r13 <= 0) goto L_0x0193;	 Catch:{ all -> 0x02ef }
    L_0x0152:
        r8 = 1;	 Catch:{ all -> 0x02ef }
        r6 = 1;	 Catch:{ all -> 0x02ef }
        r13 = r8;	 Catch:{ all -> 0x02ef }
        r8 = r6;	 Catch:{ all -> 0x02ef }
    L_0x0156:
        if (r13 != 0) goto L_0x03ea;	 Catch:{ all -> 0x02ef }
    L_0x0158:
        r0 = r22;	 Catch:{ all -> 0x02ef }
        r6 = r0.symbolTable;	 Catch:{ all -> 0x02ef }
        r6 = r10.scanSymbol(r6);	 Catch:{ all -> 0x02ef }
        if (r6 != 0) goto L_0x02bd;	 Catch:{ all -> 0x02ef }
    L_0x0162:
        r12 = r10.token();	 Catch:{ all -> 0x02ef }
        r18 = 13;	 Catch:{ all -> 0x02ef }
        r0 = r18;	 Catch:{ all -> 0x02ef }
        if (r12 != r0) goto L_0x02ac;	 Catch:{ all -> 0x02ef }
    L_0x016c:
        r4 = 16;	 Catch:{ all -> 0x02ef }
        r10.nextToken(r4);	 Catch:{ all -> 0x02ef }
        r4 = r5;
    L_0x0172:
        if (r7 != 0) goto L_0x0582;
    L_0x0174:
        if (r9 != 0) goto L_0x04af;
    L_0x0176:
        r25 = r21.createInstance(r22, r23);	 Catch:{ all -> 0x0515 }
        if (r4 != 0) goto L_0x0186;
    L_0x017c:
        r0 = r22;	 Catch:{ all -> 0x0578 }
        r1 = r25;	 Catch:{ all -> 0x0578 }
        r2 = r24;	 Catch:{ all -> 0x0578 }
        r4 = r0.setContext(r11, r1, r2);	 Catch:{ all -> 0x0578 }
    L_0x0186:
        if (r4 == 0) goto L_0x018c;
    L_0x0188:
        r0 = r25;
        r4.object = r0;
    L_0x018c:
        r0 = r22;
        r0.setContext(r11);
        goto L_0x0010;
    L_0x0193:
        r13 = r10.matchStat;	 Catch:{ all -> 0x02ef }
        r18 = -2;	 Catch:{ all -> 0x02ef }
        r0 = r18;	 Catch:{ all -> 0x02ef }
        if (r13 != r0) goto L_0x058c;	 Catch:{ all -> 0x02ef }
    L_0x019b:
        r4 = r5;	 Catch:{ all -> 0x02ef }
    L_0x019c:
        r5 = r17 + 1;	 Catch:{ all -> 0x02ef }
        r17 = r5;	 Catch:{ all -> 0x02ef }
        r5 = r4;	 Catch:{ all -> 0x02ef }
        goto L_0x0118;	 Catch:{ all -> 0x02ef }
    L_0x01a3:
        r18 = java.lang.Long.TYPE;	 Catch:{ all -> 0x02ef }
        r0 = r18;	 Catch:{ all -> 0x02ef }
        if (r14 == r0) goto L_0x01af;	 Catch:{ all -> 0x02ef }
    L_0x01a9:
        r18 = java.lang.Long.class;	 Catch:{ all -> 0x02ef }
        r0 = r18;	 Catch:{ all -> 0x02ef }
        if (r14 != r0) goto L_0x01ca;	 Catch:{ all -> 0x02ef }
    L_0x01af:
        r18 = r10.scanFieldLong(r13);	 Catch:{ all -> 0x02ef }
        r4 = java.lang.Long.valueOf(r18);	 Catch:{ all -> 0x02ef }
        r13 = r10.matchStat;	 Catch:{ all -> 0x02ef }
        if (r13 <= 0) goto L_0x01c0;	 Catch:{ all -> 0x02ef }
    L_0x01bb:
        r8 = 1;	 Catch:{ all -> 0x02ef }
        r6 = 1;	 Catch:{ all -> 0x02ef }
        r13 = r8;	 Catch:{ all -> 0x02ef }
        r8 = r6;	 Catch:{ all -> 0x02ef }
        goto L_0x0156;	 Catch:{ all -> 0x02ef }
    L_0x01c0:
        r13 = r10.matchStat;	 Catch:{ all -> 0x02ef }
        r18 = -2;	 Catch:{ all -> 0x02ef }
        r0 = r18;	 Catch:{ all -> 0x02ef }
        if (r13 != r0) goto L_0x058c;	 Catch:{ all -> 0x02ef }
    L_0x01c8:
        r4 = r5;	 Catch:{ all -> 0x02ef }
        goto L_0x019c;	 Catch:{ all -> 0x02ef }
    L_0x01ca:
        r18 = java.lang.String.class;	 Catch:{ all -> 0x02ef }
        r0 = r18;	 Catch:{ all -> 0x02ef }
        if (r14 != r0) goto L_0x01e8;	 Catch:{ all -> 0x02ef }
    L_0x01d0:
        r4 = r10.scanFieldString(r13);	 Catch:{ all -> 0x02ef }
        r13 = r10.matchStat;	 Catch:{ all -> 0x02ef }
        if (r13 <= 0) goto L_0x01de;	 Catch:{ all -> 0x02ef }
    L_0x01d8:
        r8 = 1;	 Catch:{ all -> 0x02ef }
        r6 = 1;	 Catch:{ all -> 0x02ef }
        r13 = r8;	 Catch:{ all -> 0x02ef }
        r8 = r6;	 Catch:{ all -> 0x02ef }
        goto L_0x0156;	 Catch:{ all -> 0x02ef }
    L_0x01de:
        r13 = r10.matchStat;	 Catch:{ all -> 0x02ef }
        r18 = -2;	 Catch:{ all -> 0x02ef }
        r0 = r18;	 Catch:{ all -> 0x02ef }
        if (r13 != r0) goto L_0x058c;	 Catch:{ all -> 0x02ef }
    L_0x01e6:
        r4 = r5;	 Catch:{ all -> 0x02ef }
        goto L_0x019c;	 Catch:{ all -> 0x02ef }
    L_0x01e8:
        r18 = java.lang.Boolean.TYPE;	 Catch:{ all -> 0x02ef }
        r0 = r18;	 Catch:{ all -> 0x02ef }
        if (r14 == r0) goto L_0x01f4;	 Catch:{ all -> 0x02ef }
    L_0x01ee:
        r18 = java.lang.Boolean.class;	 Catch:{ all -> 0x02ef }
        r0 = r18;	 Catch:{ all -> 0x02ef }
        if (r14 != r0) goto L_0x0210;	 Catch:{ all -> 0x02ef }
    L_0x01f4:
        r4 = r10.scanFieldBoolean(r13);	 Catch:{ all -> 0x02ef }
        r4 = java.lang.Boolean.valueOf(r4);	 Catch:{ all -> 0x02ef }
        r13 = r10.matchStat;	 Catch:{ all -> 0x02ef }
        if (r13 <= 0) goto L_0x0206;	 Catch:{ all -> 0x02ef }
    L_0x0200:
        r8 = 1;	 Catch:{ all -> 0x02ef }
        r6 = 1;	 Catch:{ all -> 0x02ef }
        r13 = r8;	 Catch:{ all -> 0x02ef }
        r8 = r6;	 Catch:{ all -> 0x02ef }
        goto L_0x0156;	 Catch:{ all -> 0x02ef }
    L_0x0206:
        r13 = r10.matchStat;	 Catch:{ all -> 0x02ef }
        r18 = -2;	 Catch:{ all -> 0x02ef }
        r0 = r18;	 Catch:{ all -> 0x02ef }
        if (r13 != r0) goto L_0x058c;	 Catch:{ all -> 0x02ef }
    L_0x020e:
        r4 = r5;	 Catch:{ all -> 0x02ef }
        goto L_0x019c;	 Catch:{ all -> 0x02ef }
    L_0x0210:
        r18 = java.lang.Float.TYPE;	 Catch:{ all -> 0x02ef }
        r0 = r18;	 Catch:{ all -> 0x02ef }
        if (r14 == r0) goto L_0x021c;	 Catch:{ all -> 0x02ef }
    L_0x0216:
        r18 = java.lang.Float.class;	 Catch:{ all -> 0x02ef }
        r0 = r18;	 Catch:{ all -> 0x02ef }
        if (r14 != r0) goto L_0x0239;	 Catch:{ all -> 0x02ef }
    L_0x021c:
        r4 = r10.scanFieldFloat(r13);	 Catch:{ all -> 0x02ef }
        r4 = java.lang.Float.valueOf(r4);	 Catch:{ all -> 0x02ef }
        r13 = r10.matchStat;	 Catch:{ all -> 0x02ef }
        if (r13 <= 0) goto L_0x022e;	 Catch:{ all -> 0x02ef }
    L_0x0228:
        r8 = 1;	 Catch:{ all -> 0x02ef }
        r6 = 1;	 Catch:{ all -> 0x02ef }
        r13 = r8;	 Catch:{ all -> 0x02ef }
        r8 = r6;	 Catch:{ all -> 0x02ef }
        goto L_0x0156;	 Catch:{ all -> 0x02ef }
    L_0x022e:
        r13 = r10.matchStat;	 Catch:{ all -> 0x02ef }
        r18 = -2;	 Catch:{ all -> 0x02ef }
        r0 = r18;	 Catch:{ all -> 0x02ef }
        if (r13 != r0) goto L_0x058c;	 Catch:{ all -> 0x02ef }
    L_0x0236:
        r4 = r5;	 Catch:{ all -> 0x02ef }
        goto L_0x019c;	 Catch:{ all -> 0x02ef }
    L_0x0239:
        r18 = java.lang.Double.TYPE;	 Catch:{ all -> 0x02ef }
        r0 = r18;	 Catch:{ all -> 0x02ef }
        if (r14 == r0) goto L_0x0245;	 Catch:{ all -> 0x02ef }
    L_0x023f:
        r18 = java.lang.Double.class;	 Catch:{ all -> 0x02ef }
        r0 = r18;	 Catch:{ all -> 0x02ef }
        if (r14 != r0) goto L_0x0262;	 Catch:{ all -> 0x02ef }
    L_0x0245:
        r18 = r10.scanFieldDouble(r13);	 Catch:{ all -> 0x02ef }
        r4 = java.lang.Double.valueOf(r18);	 Catch:{ all -> 0x02ef }
        r13 = r10.matchStat;	 Catch:{ all -> 0x02ef }
        if (r13 <= 0) goto L_0x0257;	 Catch:{ all -> 0x02ef }
    L_0x0251:
        r8 = 1;	 Catch:{ all -> 0x02ef }
        r6 = 1;	 Catch:{ all -> 0x02ef }
        r13 = r8;	 Catch:{ all -> 0x02ef }
        r8 = r6;	 Catch:{ all -> 0x02ef }
        goto L_0x0156;	 Catch:{ all -> 0x02ef }
    L_0x0257:
        r13 = r10.matchStat;	 Catch:{ all -> 0x02ef }
        r18 = -2;	 Catch:{ all -> 0x02ef }
        r0 = r18;	 Catch:{ all -> 0x02ef }
        if (r13 != r0) goto L_0x058c;	 Catch:{ all -> 0x02ef }
    L_0x025f:
        r4 = r5;	 Catch:{ all -> 0x02ef }
        goto L_0x019c;	 Catch:{ all -> 0x02ef }
    L_0x0262:
        r18 = r14.isEnum();	 Catch:{ all -> 0x02ef }
        if (r18 == 0) goto L_0x02a1;	 Catch:{ all -> 0x02ef }
    L_0x0268:
        r18 = r22.getConfig();	 Catch:{ all -> 0x02ef }
        r0 = r18;	 Catch:{ all -> 0x02ef }
        r18 = r0.getDeserializer(r14);	 Catch:{ all -> 0x02ef }
        r0 = r18;	 Catch:{ all -> 0x02ef }
        r0 = r0 instanceof com.alibaba.fastjson.parser.deserializer.EnumDeserializer;	 Catch:{ all -> 0x02ef }
        r18 = r0;	 Catch:{ all -> 0x02ef }
        if (r18 == 0) goto L_0x02a1;	 Catch:{ all -> 0x02ef }
    L_0x027a:
        r0 = r22;	 Catch:{ all -> 0x02ef }
        r0 = r0.symbolTable;	 Catch:{ all -> 0x02ef }
        r18 = r0;	 Catch:{ all -> 0x02ef }
        r0 = r18;	 Catch:{ all -> 0x02ef }
        r13 = r10.scanFieldSymbol(r13, r0);	 Catch:{ all -> 0x02ef }
        r0 = r10.matchStat;	 Catch:{ all -> 0x02ef }
        r18 = r0;	 Catch:{ all -> 0x02ef }
        if (r18 <= 0) goto L_0x0296;	 Catch:{ all -> 0x02ef }
    L_0x028c:
        r8 = 1;	 Catch:{ all -> 0x02ef }
        r6 = 1;	 Catch:{ all -> 0x02ef }
        r4 = java.lang.Enum.valueOf(r14, r13);	 Catch:{ all -> 0x02ef }
    L_0x0292:
        r13 = r8;	 Catch:{ all -> 0x02ef }
        r8 = r6;	 Catch:{ all -> 0x02ef }
        goto L_0x0156;	 Catch:{ all -> 0x02ef }
    L_0x0296:
        r13 = r10.matchStat;	 Catch:{ all -> 0x02ef }
        r18 = -2;	 Catch:{ all -> 0x02ef }
        r0 = r18;	 Catch:{ all -> 0x02ef }
        if (r13 != r0) goto L_0x0292;	 Catch:{ all -> 0x02ef }
    L_0x029e:
        r4 = r5;	 Catch:{ all -> 0x02ef }
        goto L_0x019c;	 Catch:{ all -> 0x02ef }
    L_0x02a1:
        r8 = r10.matchField(r13);	 Catch:{ all -> 0x02ef }
        if (r8 == 0) goto L_0x0589;	 Catch:{ all -> 0x02ef }
    L_0x02a7:
        r8 = 1;	 Catch:{ all -> 0x02ef }
        r13 = r8;	 Catch:{ all -> 0x02ef }
        r8 = r6;	 Catch:{ all -> 0x02ef }
        goto L_0x0156;	 Catch:{ all -> 0x02ef }
    L_0x02ac:
        r18 = 16;	 Catch:{ all -> 0x02ef }
        r0 = r18;	 Catch:{ all -> 0x02ef }
        if (r12 != r0) goto L_0x02bd;	 Catch:{ all -> 0x02ef }
    L_0x02b2:
        r12 = com.alibaba.fastjson.parser.Feature.AllowArbitraryCommas;	 Catch:{ all -> 0x02ef }
        r12 = r10.isEnabled(r12);	 Catch:{ all -> 0x02ef }
        if (r12 == 0) goto L_0x02bd;	 Catch:{ all -> 0x02ef }
    L_0x02ba:
        r4 = r5;	 Catch:{ all -> 0x02ef }
        goto L_0x019c;	 Catch:{ all -> 0x02ef }
    L_0x02bd:
        r12 = "$ref";	 Catch:{ all -> 0x02ef }
        if (r12 != r6) goto L_0x0380;	 Catch:{ all -> 0x02ef }
    L_0x02c1:
        r4 = 4;	 Catch:{ all -> 0x02ef }
        r10.nextTokenWithColon(r4);	 Catch:{ all -> 0x02ef }
        r4 = r10.token();	 Catch:{ all -> 0x02ef }
        r6 = 4;	 Catch:{ all -> 0x02ef }
        if (r4 != r6) goto L_0x034a;	 Catch:{ all -> 0x02ef }
    L_0x02cc:
        r6 = r10.stringVal();	 Catch:{ all -> 0x02ef }
        r4 = "@";	 Catch:{ all -> 0x02ef }
        r4 = r4.equals(r6);	 Catch:{ all -> 0x02ef }
        if (r4 == 0) goto L_0x02f2;	 Catch:{ all -> 0x02ef }
    L_0x02d8:
        r7 = r11.object;	 Catch:{ all -> 0x02ef }
    L_0x02da:
        r4 = 13;	 Catch:{ all -> 0x02ef }
        r10.nextToken(r4);	 Catch:{ all -> 0x02ef }
        r4 = r10.token();	 Catch:{ all -> 0x02ef }
        r6 = 13;	 Catch:{ all -> 0x02ef }
        if (r4 == r6) goto L_0x0367;	 Catch:{ all -> 0x02ef }
    L_0x02e7:
        r4 = new com.alibaba.fastjson.JSONException;	 Catch:{ all -> 0x02ef }
        r6 = "illegal ref";	 Catch:{ all -> 0x02ef }
        r4.<init>(r6);	 Catch:{ all -> 0x02ef }
        throw r4;	 Catch:{ all -> 0x02ef }
    L_0x02ef:
        r4 = move-exception;	 Catch:{ all -> 0x02ef }
        goto L_0x00fd;	 Catch:{ all -> 0x02ef }
    L_0x02f2:
        r4 = "..";	 Catch:{ all -> 0x02ef }
        r4 = r4.equals(r6);	 Catch:{ all -> 0x02ef }
        if (r4 == 0) goto L_0x0313;	 Catch:{ all -> 0x02ef }
    L_0x02fa:
        r4 = r11.parent;	 Catch:{ all -> 0x02ef }
        r8 = r4.object;	 Catch:{ all -> 0x02ef }
        if (r8 == 0) goto L_0x0303;	 Catch:{ all -> 0x02ef }
    L_0x0300:
        r7 = r4.object;	 Catch:{ all -> 0x02ef }
        goto L_0x02da;	 Catch:{ all -> 0x02ef }
    L_0x0303:
        r8 = new com.alibaba.fastjson.parser.DefaultJSONParser$ResolveTask;	 Catch:{ all -> 0x02ef }
        r8.<init>(r4, r6);	 Catch:{ all -> 0x02ef }
        r0 = r22;	 Catch:{ all -> 0x02ef }
        r0.addResolveTask(r8);	 Catch:{ all -> 0x02ef }
        r4 = 1;	 Catch:{ all -> 0x02ef }
        r0 = r22;	 Catch:{ all -> 0x02ef }
        r0.resolveStatus = r4;	 Catch:{ all -> 0x02ef }
        goto L_0x02da;	 Catch:{ all -> 0x02ef }
    L_0x0313:
        r4 = "$";	 Catch:{ all -> 0x02ef }
        r4 = r4.equals(r6);	 Catch:{ all -> 0x02ef }
        if (r4 == 0) goto L_0x033a;	 Catch:{ all -> 0x02ef }
    L_0x031b:
        r4 = r11;	 Catch:{ all -> 0x02ef }
    L_0x031c:
        r8 = r4.parent;	 Catch:{ all -> 0x02ef }
        if (r8 == 0) goto L_0x0323;	 Catch:{ all -> 0x02ef }
    L_0x0320:
        r4 = r4.parent;	 Catch:{ all -> 0x02ef }
        goto L_0x031c;	 Catch:{ all -> 0x02ef }
    L_0x0323:
        r8 = r4.object;	 Catch:{ all -> 0x02ef }
        if (r8 == 0) goto L_0x032a;	 Catch:{ all -> 0x02ef }
    L_0x0327:
        r7 = r4.object;	 Catch:{ all -> 0x02ef }
        goto L_0x02da;	 Catch:{ all -> 0x02ef }
    L_0x032a:
        r8 = new com.alibaba.fastjson.parser.DefaultJSONParser$ResolveTask;	 Catch:{ all -> 0x02ef }
        r8.<init>(r4, r6);	 Catch:{ all -> 0x02ef }
        r0 = r22;	 Catch:{ all -> 0x02ef }
        r0.addResolveTask(r8);	 Catch:{ all -> 0x02ef }
        r4 = 1;	 Catch:{ all -> 0x02ef }
        r0 = r22;	 Catch:{ all -> 0x02ef }
        r0.resolveStatus = r4;	 Catch:{ all -> 0x02ef }
        goto L_0x02da;	 Catch:{ all -> 0x02ef }
    L_0x033a:
        r4 = new com.alibaba.fastjson.parser.DefaultJSONParser$ResolveTask;	 Catch:{ all -> 0x02ef }
        r4.<init>(r11, r6);	 Catch:{ all -> 0x02ef }
        r0 = r22;	 Catch:{ all -> 0x02ef }
        r0.addResolveTask(r4);	 Catch:{ all -> 0x02ef }
        r4 = 1;	 Catch:{ all -> 0x02ef }
        r0 = r22;	 Catch:{ all -> 0x02ef }
        r0.resolveStatus = r4;	 Catch:{ all -> 0x02ef }
        goto L_0x02da;	 Catch:{ all -> 0x02ef }
    L_0x034a:
        r6 = new com.alibaba.fastjson.JSONException;	 Catch:{ all -> 0x02ef }
        r8 = new java.lang.StringBuilder;	 Catch:{ all -> 0x02ef }
        r8.<init>();	 Catch:{ all -> 0x02ef }
        r9 = "illegal ref, ";	 Catch:{ all -> 0x02ef }
        r8 = r8.append(r9);	 Catch:{ all -> 0x02ef }
        r4 = com.alibaba.fastjson.parser.JSONToken.name(r4);	 Catch:{ all -> 0x02ef }
        r4 = r8.append(r4);	 Catch:{ all -> 0x02ef }
        r4 = r4.toString();	 Catch:{ all -> 0x02ef }
        r6.<init>(r4);	 Catch:{ all -> 0x02ef }
        throw r6;	 Catch:{ all -> 0x02ef }
    L_0x0367:
        r4 = 16;	 Catch:{ all -> 0x02ef }
        r10.nextToken(r4);	 Catch:{ all -> 0x02ef }
        r0 = r22;	 Catch:{ all -> 0x02ef }
        r1 = r24;	 Catch:{ all -> 0x02ef }
        r0.setContext(r11, r7, r1);	 Catch:{ all -> 0x02ef }
        if (r5 == 0) goto L_0x0377;
    L_0x0375:
        r5.object = r7;
    L_0x0377:
        r0 = r22;
        r0.setContext(r11);
        r25 = r7;
        goto L_0x0010;
    L_0x0380:
        r12 = com.alibaba.fastjson.JSON.DEFAULT_TYPE_KEY;	 Catch:{ all -> 0x02ef }
        if (r12 != r6) goto L_0x03eb;	 Catch:{ all -> 0x02ef }
    L_0x0384:
        r4 = 4;	 Catch:{ all -> 0x02ef }
        r10.nextTokenWithColon(r4);	 Catch:{ all -> 0x02ef }
        r4 = r10.token();	 Catch:{ all -> 0x02ef }
        r6 = 4;	 Catch:{ all -> 0x02ef }
        if (r4 != r6) goto L_0x03e2;	 Catch:{ all -> 0x02ef }
    L_0x038f:
        r6 = r10.stringVal();	 Catch:{ all -> 0x02ef }
        r4 = 16;	 Catch:{ all -> 0x02ef }
        r10.nextToken(r4);	 Catch:{ all -> 0x02ef }
        r0 = r23;	 Catch:{ all -> 0x02ef }
        r4 = r0 instanceof java.lang.Class;	 Catch:{ all -> 0x02ef }
        if (r4 == 0) goto L_0x03bb;	 Catch:{ all -> 0x02ef }
    L_0x039e:
        r0 = r23;	 Catch:{ all -> 0x02ef }
        r0 = (java.lang.Class) r0;	 Catch:{ all -> 0x02ef }
        r4 = r0;	 Catch:{ all -> 0x02ef }
        r4 = r4.getName();	 Catch:{ all -> 0x02ef }
        r4 = r6.equals(r4);	 Catch:{ all -> 0x02ef }
        if (r4 == 0) goto L_0x03bb;	 Catch:{ all -> 0x02ef }
    L_0x03ad:
        r4 = r10.token();	 Catch:{ all -> 0x02ef }
        r6 = 13;	 Catch:{ all -> 0x02ef }
        if (r4 != r6) goto L_0x0589;	 Catch:{ all -> 0x02ef }
    L_0x03b5:
        r10.nextToken();	 Catch:{ all -> 0x02ef }
        r4 = r5;	 Catch:{ all -> 0x02ef }
        goto L_0x0172;	 Catch:{ all -> 0x02ef }
    L_0x03bb:
        r4 = r22.getConfig();	 Catch:{ all -> 0x02ef }
        r4 = r4.getDefaultClassLoader();	 Catch:{ all -> 0x02ef }
        r4 = com.alibaba.fastjson.util.TypeUtils.loadClass(r6, r4);	 Catch:{ all -> 0x02ef }
        r6 = r22.getConfig();	 Catch:{ all -> 0x02ef }
        r6 = r6.getDeserializer(r4);	 Catch:{ all -> 0x02ef }
        r0 = r22;	 Catch:{ all -> 0x02ef }
        r1 = r24;	 Catch:{ all -> 0x02ef }
        r25 = r6.deserialze(r0, r4, r1);	 Catch:{ all -> 0x02ef }
        if (r5 == 0) goto L_0x03db;
    L_0x03d9:
        r5.object = r7;
    L_0x03db:
        r0 = r22;
        r0.setContext(r11);
        goto L_0x0010;
    L_0x03e2:
        r4 = new com.alibaba.fastjson.JSONException;	 Catch:{ all -> 0x02ef }
        r6 = "syntax error";	 Catch:{ all -> 0x02ef }
        r4.<init>(r6);	 Catch:{ all -> 0x02ef }
        throw r4;	 Catch:{ all -> 0x02ef }
    L_0x03ea:
        r6 = r12;	 Catch:{ all -> 0x02ef }
    L_0x03eb:
        if (r7 != 0) goto L_0x0586;	 Catch:{ all -> 0x02ef }
    L_0x03ed:
        if (r9 != 0) goto L_0x0586;	 Catch:{ all -> 0x02ef }
    L_0x03ef:
        r7 = r21.createInstance(r22, r23);	 Catch:{ all -> 0x02ef }
        if (r7 != 0) goto L_0x03ff;	 Catch:{ all -> 0x02ef }
    L_0x03f5:
        r9 = new java.util.HashMap;	 Catch:{ all -> 0x02ef }
        r0 = r21;	 Catch:{ all -> 0x02ef }
        r12 = r0.fieldDeserializers;	 Catch:{ all -> 0x02ef }
        r12 = r12.length;	 Catch:{ all -> 0x02ef }
        r9.<init>(r12);	 Catch:{ all -> 0x02ef }
    L_0x03ff:
        r0 = r22;	 Catch:{ all -> 0x02ef }
        r1 = r24;	 Catch:{ all -> 0x02ef }
        r5 = r0.setContext(r11, r7, r1);	 Catch:{ all -> 0x02ef }
        r12 = r5;
    L_0x0408:
        if (r13 == 0) goto L_0x0455;
    L_0x040a:
        if (r8 != 0) goto L_0x0420;
    L_0x040c:
        r0 = r16;	 Catch:{ all -> 0x044b }
        r1 = r22;	 Catch:{ all -> 0x044b }
        r2 = r23;	 Catch:{ all -> 0x044b }
        r0.parseField(r1, r7, r2, r9);	 Catch:{ all -> 0x044b }
    L_0x0415:
        r4 = r10.token();	 Catch:{ all -> 0x044b }
        r5 = 16;	 Catch:{ all -> 0x044b }
        if (r4 != r5) goto L_0x046f;	 Catch:{ all -> 0x044b }
    L_0x041d:
        r4 = r12;	 Catch:{ all -> 0x044b }
        goto L_0x019c;	 Catch:{ all -> 0x044b }
    L_0x0420:
        if (r7 != 0) goto L_0x042f;	 Catch:{ all -> 0x044b }
    L_0x0422:
        r5 = r15.name;	 Catch:{ all -> 0x044b }
        r9.put(r5, r4);	 Catch:{ all -> 0x044b }
    L_0x0427:
        r4 = r10.matchStat;	 Catch:{ all -> 0x044b }
        r5 = 4;	 Catch:{ all -> 0x044b }
        if (r4 != r5) goto L_0x0415;	 Catch:{ all -> 0x044b }
    L_0x042c:
        r4 = r12;	 Catch:{ all -> 0x044b }
        goto L_0x0172;	 Catch:{ all -> 0x044b }
    L_0x042f:
        if (r4 != 0) goto L_0x044f;	 Catch:{ all -> 0x044b }
    L_0x0431:
        r5 = java.lang.Integer.TYPE;	 Catch:{ all -> 0x044b }
        if (r14 == r5) goto L_0x0427;	 Catch:{ all -> 0x044b }
    L_0x0435:
        r5 = java.lang.Long.TYPE;	 Catch:{ all -> 0x044b }
        if (r14 == r5) goto L_0x0427;	 Catch:{ all -> 0x044b }
    L_0x0439:
        r5 = java.lang.Float.TYPE;	 Catch:{ all -> 0x044b }
        if (r14 == r5) goto L_0x0427;	 Catch:{ all -> 0x044b }
    L_0x043d:
        r5 = java.lang.Double.TYPE;	 Catch:{ all -> 0x044b }
        if (r14 == r5) goto L_0x0427;	 Catch:{ all -> 0x044b }
    L_0x0441:
        r5 = java.lang.Boolean.TYPE;	 Catch:{ all -> 0x044b }
        if (r14 == r5) goto L_0x0427;	 Catch:{ all -> 0x044b }
    L_0x0445:
        r0 = r16;	 Catch:{ all -> 0x044b }
        r0.setValue(r7, r4);	 Catch:{ all -> 0x044b }
        goto L_0x0427;	 Catch:{ all -> 0x044b }
    L_0x044b:
        r4 = move-exception;	 Catch:{ all -> 0x044b }
        r5 = r12;	 Catch:{ all -> 0x044b }
        goto L_0x00fd;	 Catch:{ all -> 0x044b }
    L_0x044f:
        r0 = r16;	 Catch:{ all -> 0x044b }
        r0.setValue(r7, r4);	 Catch:{ all -> 0x044b }
        goto L_0x0427;	 Catch:{ all -> 0x044b }
    L_0x0455:
        r4 = r21;	 Catch:{ all -> 0x044b }
        r5 = r22;	 Catch:{ all -> 0x044b }
        r8 = r23;	 Catch:{ all -> 0x044b }
        r4 = r4.parseField(r5, r6, r7, r8, r9);	 Catch:{ all -> 0x044b }
        if (r4 != 0) goto L_0x0415;	 Catch:{ all -> 0x044b }
    L_0x0461:
        r4 = r10.token();	 Catch:{ all -> 0x044b }
        r5 = 13;	 Catch:{ all -> 0x044b }
        if (r4 != r5) goto L_0x0596;	 Catch:{ all -> 0x044b }
    L_0x0469:
        r10.nextToken();	 Catch:{ all -> 0x044b }
        r4 = r12;	 Catch:{ all -> 0x044b }
        goto L_0x0172;	 Catch:{ all -> 0x044b }
    L_0x046f:
        r4 = r10.token();	 Catch:{ all -> 0x044b }
        r5 = 13;	 Catch:{ all -> 0x044b }
        if (r4 != r5) goto L_0x047f;	 Catch:{ all -> 0x044b }
    L_0x0477:
        r4 = 16;	 Catch:{ all -> 0x044b }
        r10.nextToken(r4);	 Catch:{ all -> 0x044b }
        r4 = r12;	 Catch:{ all -> 0x044b }
        goto L_0x0172;	 Catch:{ all -> 0x044b }
    L_0x047f:
        r4 = r10.token();	 Catch:{ all -> 0x044b }
        r5 = 18;	 Catch:{ all -> 0x044b }
        if (r4 == r5) goto L_0x048e;	 Catch:{ all -> 0x044b }
    L_0x0487:
        r4 = r10.token();	 Catch:{ all -> 0x044b }
        r5 = 1;	 Catch:{ all -> 0x044b }
        if (r4 != r5) goto L_0x0596;	 Catch:{ all -> 0x044b }
    L_0x048e:
        r4 = new com.alibaba.fastjson.JSONException;	 Catch:{ all -> 0x044b }
        r5 = new java.lang.StringBuilder;	 Catch:{ all -> 0x044b }
        r5.<init>();	 Catch:{ all -> 0x044b }
        r6 = "syntax error, unexpect token ";	 Catch:{ all -> 0x044b }
        r5 = r5.append(r6);	 Catch:{ all -> 0x044b }
        r6 = r10.token();	 Catch:{ all -> 0x044b }
        r6 = com.alibaba.fastjson.parser.JSONToken.name(r6);	 Catch:{ all -> 0x044b }
        r5 = r5.append(r6);	 Catch:{ all -> 0x044b }
        r5 = r5.toString();	 Catch:{ all -> 0x044b }
        r4.<init>(r5);	 Catch:{ all -> 0x044b }
        throw r4;	 Catch:{ all -> 0x044b }
    L_0x04af:
        r0 = r21;	 Catch:{ all -> 0x0515 }
        r5 = r0.beanInfo;	 Catch:{ all -> 0x0515 }
        r6 = r5.fields;	 Catch:{ all -> 0x0515 }
        r8 = r6.length;	 Catch:{ all -> 0x0515 }
        r10 = new java.lang.Object[r8];	 Catch:{ all -> 0x0515 }
        r5 = 0;	 Catch:{ all -> 0x0515 }
    L_0x04b9:
        if (r5 >= r8) goto L_0x04c8;	 Catch:{ all -> 0x0515 }
    L_0x04bb:
        r12 = r6[r5];	 Catch:{ all -> 0x0515 }
        r12 = r12.name;	 Catch:{ all -> 0x0515 }
        r12 = r9.get(r12);	 Catch:{ all -> 0x0515 }
        r10[r5] = r12;	 Catch:{ all -> 0x0515 }
        r5 = r5 + 1;	 Catch:{ all -> 0x0515 }
        goto L_0x04b9;	 Catch:{ all -> 0x0515 }
    L_0x04c8:
        r0 = r21;	 Catch:{ all -> 0x0515 }
        r5 = r0.beanInfo;	 Catch:{ all -> 0x0515 }
        r5 = r5.creatorConstructor;	 Catch:{ all -> 0x0515 }
        if (r5 == 0) goto L_0x051d;
    L_0x04d0:
        r0 = r21;	 Catch:{ Exception -> 0x04f1 }
        r5 = r0.beanInfo;	 Catch:{ Exception -> 0x04f1 }
        r5 = r5.creatorConstructor;	 Catch:{ Exception -> 0x04f1 }
        r7 = r5.newInstance(r10);	 Catch:{ Exception -> 0x04f1 }
        r25 = r7;
    L_0x04dc:
        r0 = r21;	 Catch:{ all -> 0x0578 }
        r5 = r0.beanInfo;	 Catch:{ all -> 0x0578 }
        r5 = r5.buildMethod;	 Catch:{ all -> 0x0578 }
        if (r5 != 0) goto L_0x0557;
    L_0x04e4:
        if (r4 == 0) goto L_0x04ea;
    L_0x04e6:
        r0 = r25;
        r4.object = r0;
    L_0x04ea:
        r0 = r22;
        r0.setContext(r11);
        goto L_0x0010;
    L_0x04f1:
        r5 = move-exception;
        r6 = new com.alibaba.fastjson.JSONException;	 Catch:{ all -> 0x0515 }
        r8 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0515 }
        r8.<init>();	 Catch:{ all -> 0x0515 }
        r9 = "create instance error, ";	 Catch:{ all -> 0x0515 }
        r8 = r8.append(r9);	 Catch:{ all -> 0x0515 }
        r0 = r21;	 Catch:{ all -> 0x0515 }
        r9 = r0.beanInfo;	 Catch:{ all -> 0x0515 }
        r9 = r9.creatorConstructor;	 Catch:{ all -> 0x0515 }
        r9 = r9.toGenericString();	 Catch:{ all -> 0x0515 }
        r8 = r8.append(r9);	 Catch:{ all -> 0x0515 }
        r8 = r8.toString();	 Catch:{ all -> 0x0515 }
        r6.<init>(r8, r5);	 Catch:{ all -> 0x0515 }
        throw r6;	 Catch:{ all -> 0x0515 }
    L_0x0515:
        r5 = move-exception;	 Catch:{ all -> 0x0515 }
        r20 = r5;	 Catch:{ all -> 0x0515 }
        r5 = r4;	 Catch:{ all -> 0x0515 }
        r4 = r20;	 Catch:{ all -> 0x0515 }
        goto L_0x00fd;	 Catch:{ all -> 0x0515 }
    L_0x051d:
        r0 = r21;	 Catch:{ all -> 0x0515 }
        r5 = r0.beanInfo;	 Catch:{ all -> 0x0515 }
        r5 = r5.factoryMethod;	 Catch:{ all -> 0x0515 }
        if (r5 == 0) goto L_0x0582;
    L_0x0525:
        r0 = r21;	 Catch:{ Exception -> 0x0533 }
        r5 = r0.beanInfo;	 Catch:{ Exception -> 0x0533 }
        r5 = r5.factoryMethod;	 Catch:{ Exception -> 0x0533 }
        r6 = 0;	 Catch:{ Exception -> 0x0533 }
        r7 = r5.invoke(r6, r10);	 Catch:{ Exception -> 0x0533 }
        r25 = r7;
        goto L_0x04dc;
    L_0x0533:
        r5 = move-exception;
        r6 = new com.alibaba.fastjson.JSONException;	 Catch:{ all -> 0x0515 }
        r8 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0515 }
        r8.<init>();	 Catch:{ all -> 0x0515 }
        r9 = "create factory method error, ";	 Catch:{ all -> 0x0515 }
        r8 = r8.append(r9);	 Catch:{ all -> 0x0515 }
        r0 = r21;	 Catch:{ all -> 0x0515 }
        r9 = r0.beanInfo;	 Catch:{ all -> 0x0515 }
        r9 = r9.factoryMethod;	 Catch:{ all -> 0x0515 }
        r9 = r9.toString();	 Catch:{ all -> 0x0515 }
        r8 = r8.append(r9);	 Catch:{ all -> 0x0515 }
        r8 = r8.toString();	 Catch:{ all -> 0x0515 }
        r6.<init>(r8, r5);	 Catch:{ all -> 0x0515 }
        throw r6;	 Catch:{ all -> 0x0515 }
    L_0x0557:
        r6 = 0;
        r6 = new java.lang.Object[r6];	 Catch:{ Exception -> 0x056f }
        r0 = r25;	 Catch:{ Exception -> 0x056f }
        r7 = r5.invoke(r0, r6);	 Catch:{ Exception -> 0x056f }
        if (r4 == 0) goto L_0x0566;
    L_0x0562:
        r0 = r25;
        r4.object = r0;
    L_0x0566:
        r0 = r22;
        r0.setContext(r11);
        r25 = r7;
        goto L_0x0010;
    L_0x056f:
        r5 = move-exception;
        r6 = new com.alibaba.fastjson.JSONException;	 Catch:{ all -> 0x0578 }
        r7 = "build object error";	 Catch:{ all -> 0x0578 }
        r6.<init>(r7, r5);	 Catch:{ all -> 0x0578 }
        throw r6;	 Catch:{ all -> 0x0578 }
    L_0x0578:
        r5 = move-exception;
        r7 = r25;
        r20 = r4;
        r4 = r5;
        r5 = r20;
        goto L_0x00fd;
    L_0x0582:
        r25 = r7;
        goto L_0x04dc;
    L_0x0586:
        r12 = r5;
        goto L_0x0408;
    L_0x0589:
        r4 = r5;
        goto L_0x019c;
    L_0x058c:
        r13 = r8;
        r8 = r6;
        goto L_0x0156;
    L_0x0590:
        r14 = r4;
        r15 = r6;
        r16 = r8;
        goto L_0x0133;
    L_0x0596:
        r4 = r12;
        goto L_0x019c;
    L_0x0599:
        r11 = r4;
        goto L_0x0033;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.parser.deserializer.JavaBeanDeserializer.deserialze(com.alibaba.fastjson.parser.DefaultJSONParser, java.lang.reflect.Type, java.lang.Object, java.lang.Object):T");
    }

    public boolean parseField(DefaultJSONParser defaultJSONParser, String str, Object obj, Type type, Map<String, Object> map) {
        JSONLexer jSONLexer = defaultJSONParser.lexer;
        FieldDeserializer smartMatch = smartMatch(str);
        if (smartMatch != null) {
            jSONLexer.nextTokenWithColon(smartMatch.getFastMatchToken());
            smartMatch.parseField(defaultJSONParser, obj, type, map);
            return true;
        } else if (jSONLexer.isEnabled(Feature.IgnoreNotMatch)) {
            defaultJSONParser.parseExtra(obj, str);
            return false;
        } else {
            throw new JSONException("setter not found, class " + this.clazz.getName() + ", property " + str);
        }
    }

    public FieldDeserializer smartMatch(String str) {
        int i = 0;
        if (str == null) {
            return null;
        }
        FieldDeserializer fieldDeserializer = getFieldDeserializer(str);
        if (fieldDeserializer == null) {
            boolean startsWith = str.startsWith("is");
            for (FieldDeserializer fieldDeserializer2 : this.sortedFieldDeserializers) {
                FieldInfo fieldInfo = fieldDeserializer2.fieldInfo;
                Class cls = fieldInfo.fieldClass;
                String str2 = fieldInfo.name;
                if (str2.equalsIgnoreCase(str) || (startsWith && ((cls == Boolean.TYPE || cls == Boolean.class) && str2.equalsIgnoreCase(str.substring(2))))) {
                    break;
                }
            }
        }
        FieldDeserializer fieldDeserializer22 = fieldDeserializer;
        if (fieldDeserializer22 != null || str.indexOf(95) == -1) {
            return fieldDeserializer22;
        }
        String replaceAll = str.replaceAll("_", "");
        fieldDeserializer = getFieldDeserializer(replaceAll);
        if (fieldDeserializer == null) {
            FieldDeserializer[] fieldDeserializerArr = this.sortedFieldDeserializers;
            int length = fieldDeserializerArr.length;
            while (i < length) {
                fieldDeserializer22 = fieldDeserializerArr[i];
                if (fieldDeserializer22.fieldInfo.name.equalsIgnoreCase(replaceAll)) {
                    return fieldDeserializer22;
                }
                i++;
            }
        }
        return fieldDeserializer;
    }

    public int getFastMatchToken() {
        return 12;
    }

    public final boolean isSupportArrayToBean(JSONLexer jSONLexer) {
        return Feature.isEnabled(this.beanInfo.parserFeatures, Feature.SupportArrayToBean) || jSONLexer.isEnabled(Feature.SupportArrayToBean);
    }

    public Object createInstance(Map<String, Object> map, ParserConfig parserConfig) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {
        if (this.beanInfo.creatorConstructor == null && this.beanInfo.buildMethod == null) {
            Object createInstance = createInstance(null, this.clazz);
            for (Entry entry : map.entrySet()) {
                String str = (String) entry.getKey();
                Object value = entry.getValue();
                FieldDeserializer fieldDeserializer = getFieldDeserializer(str);
                if (fieldDeserializer != null) {
                    Method method = fieldDeserializer.fieldInfo.method;
                    if (method != null) {
                        value = TypeUtils.cast(value, method.getGenericParameterTypes()[0], parserConfig);
                        method.invoke(createInstance, new Object[]{value});
                    } else {
                        fieldDeserializer.fieldInfo.field.set(createInstance, TypeUtils.cast(value, fieldDeserializer.fieldInfo.fieldType, parserConfig));
                    }
                }
            }
            return createInstance;
        }
        FieldInfo[] fieldInfoArr = this.beanInfo.fields;
        int length = fieldInfoArr.length;
        Object[] objArr = new Object[length];
        for (int i = 0; i < length; i++) {
            objArr[i] = map.get(fieldInfoArr[i].name);
        }
        if (this.beanInfo.creatorConstructor != null) {
            try {
                return this.beanInfo.creatorConstructor.newInstance(objArr);
            } catch (Throwable e) {
                throw new JSONException("create instance error, " + this.beanInfo.creatorConstructor.toGenericString(), e);
            }
        } else if (this.beanInfo.factoryMethod == null) {
            return null;
        } else {
            try {
                return this.beanInfo.factoryMethod.invoke(null, objArr);
            } catch (Throwable e2) {
                throw new JSONException("create factory method error, " + this.beanInfo.factoryMethod.toString(), e2);
            }
        }
    }
}
