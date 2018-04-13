package com.alibaba.fastjson.parser;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.deserializer.ExtraProcessable;
import com.alibaba.fastjson.parser.deserializer.ExtraProcessor;
import com.alibaba.fastjson.parser.deserializer.ExtraTypeProvider;
import com.alibaba.fastjson.parser.deserializer.FieldDeserializer;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import com.alibaba.fastjson.util.FieldInfo;
import com.alibaba.fastjson.util.TypeUtils;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class JavaBeanDeserializer implements ObjectDeserializer {
    private final Map<String, FieldDeserializer> alterNameFieldDeserializers;
    public final JavaBeanInfo beanInfo;
    protected final Class<?> clazz;
    private ConcurrentMap<String, Object> extraFieldDeserializers;
    private final FieldDeserializer[] fieldDeserializers;
    private transient long[] smartMatchHashArray;
    private transient int[] smartMatchHashArrayMapping;
    private final FieldDeserializer[] sortedFieldDeserializers;

    public JavaBeanDeserializer(ParserConfig parserConfig, Class<?> cls, Type type) {
        this(parserConfig, cls, type, JavaBeanInfo.build(cls, cls.getModifiers(), type, false, true, true, true, parserConfig.propertyNamingStrategy));
    }

    public JavaBeanDeserializer(ParserConfig parserConfig, Class<?> cls, Type type, JavaBeanInfo javaBeanInfo) {
        int i;
        this.clazz = cls;
        this.beanInfo = javaBeanInfo;
        Map map = null;
        this.sortedFieldDeserializers = new FieldDeserializer[javaBeanInfo.sortedFields.length];
        int length = javaBeanInfo.sortedFields.length;
        for (i = 0; i < length; i++) {
            FieldInfo fieldInfo = javaBeanInfo.sortedFields[i];
            FieldDeserializer createFieldDeserializer = parserConfig.createFieldDeserializer(parserConfig, cls, fieldInfo);
            this.sortedFieldDeserializers[i] = createFieldDeserializer;
            for (Object obj : fieldInfo.alternateNames) {
                if (map == null) {
                    map = new HashMap();
                }
                map.put(obj, createFieldDeserializer);
            }
        }
        this.alterNameFieldDeserializers = map;
        this.fieldDeserializers = new FieldDeserializer[javaBeanInfo.fields.length];
        i = javaBeanInfo.fields.length;
        for (int i2 = 0; i2 < i; i2++) {
            this.fieldDeserializers[i2] = getFieldDeserializer(javaBeanInfo.fields[i2].name);
        }
    }

    protected Object createInstance(DefaultJSONParser defaultJSONParser, Type type) {
        int i = 0;
        if ((type instanceof Class) && this.clazz.isInterface()) {
            Class cls = (Class) type;
            return Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class[]{cls}, new JSONObject((defaultJSONParser.lexer.features & Feature.OrderedField.mask) != 0));
        } else if (this.beanInfo.defaultConstructor == null && this.beanInfo.factoryMethod == null) {
            return null;
        } else {
            if (this.beanInfo.factoryMethod != null && this.beanInfo.defaultConstructorParameterSize > 0) {
                return null;
            }
            try {
                Object newInstance;
                Constructor constructor = this.beanInfo.defaultConstructor;
                if (this.beanInfo.defaultConstructorParameterSize != 0) {
                    newInstance = constructor.newInstance(new Object[]{defaultJSONParser.contex.object});
                } else if (constructor != null) {
                    newInstance = constructor.newInstance(new Object[0]);
                } else {
                    newInstance = this.beanInfo.factoryMethod.invoke(null, new Object[0]);
                }
                if (defaultJSONParser == null || (defaultJSONParser.lexer.features & Feature.InitStringFieldAsEmpty.mask) == 0) {
                    return newInstance;
                }
                FieldInfo[] fieldInfoArr = this.beanInfo.fields;
                int length = fieldInfoArr.length;
                while (i < length) {
                    FieldInfo fieldInfo = fieldInfoArr[i];
                    if (fieldInfo.fieldClass == String.class) {
                        fieldInfo.set(newInstance, "");
                    }
                    i++;
                }
                return newInstance;
            } catch (Throwable e) {
                throw new JSONException("create instance error, class " + this.clazz.getName(), e);
            }
        }
    }

    public <T> T deserialze(DefaultJSONParser defaultJSONParser, Type type, Object obj) {
        return deserialze(defaultJSONParser, type, obj, null);
    }

    private <T> T deserialzeArrayMapping(DefaultJSONParser defaultJSONParser, Type type, Object obj, Object obj2) {
        JSONLexer jSONLexer = defaultJSONParser.lexer;
        Object createInstance = createInstance(defaultJSONParser, type);
        int length = this.sortedFieldDeserializers.length;
        int i = 0;
        while (i < length) {
            char c;
            int i2 = i == length + -1 ? 93 : 44;
            FieldDeserializer fieldDeserializer = this.sortedFieldDeserializers[i];
            FieldInfo fieldInfo = fieldDeserializer.fieldInfo;
            Class cls = fieldInfo.fieldClass;
            if (cls == Integer.TYPE) {
                i2 = (int) jSONLexer.scanLongValue();
                if (fieldInfo.fieldAccess) {
                    fieldInfo.field.setInt(createInstance, i2);
                } else {
                    fieldDeserializer.setValue(createInstance, new Integer(i2));
                }
                if (jSONLexer.ch == ',') {
                    i2 = jSONLexer.bp + 1;
                    jSONLexer.bp = i2;
                    if (i2 >= jSONLexer.len) {
                        c = JSONLexer.EOI;
                    } else {
                        try {
                            c = jSONLexer.text.charAt(i2);
                        } catch (Throwable e) {
                            throw new JSONException("set " + fieldInfo.name + "error", e);
                        }
                    }
                    jSONLexer.ch = c;
                    jSONLexer.token = 16;
                } else if (jSONLexer.ch == ']') {
                    i2 = jSONLexer.bp + 1;
                    jSONLexer.bp = i2;
                    if (i2 >= jSONLexer.len) {
                        c = JSONLexer.EOI;
                    } else {
                        c = jSONLexer.text.charAt(i2);
                    }
                    jSONLexer.ch = c;
                    jSONLexer.token = 15;
                } else {
                    jSONLexer.nextToken();
                }
            } else if (cls == String.class) {
                if (jSONLexer.ch == '\"') {
                    r2 = jSONLexer.scanStringValue('\"');
                } else if (jSONLexer.ch == 'n' && jSONLexer.text.startsWith("null", jSONLexer.bp)) {
                    jSONLexer.bp += 4;
                    i2 = jSONLexer.bp;
                    if (jSONLexer.bp >= jSONLexer.len) {
                        c = JSONLexer.EOI;
                    } else {
                        c = jSONLexer.text.charAt(i2);
                    }
                    jSONLexer.ch = c;
                    r2 = null;
                } else {
                    throw new JSONException("not match string. feild : " + obj);
                }
                if (fieldInfo.fieldAccess) {
                    fieldInfo.field.set(createInstance, r2);
                } else {
                    fieldDeserializer.setValue(createInstance, r2);
                }
                if (jSONLexer.ch == ',') {
                    i2 = jSONLexer.bp + 1;
                    jSONLexer.bp = i2;
                    if (i2 >= jSONLexer.len) {
                        c = JSONLexer.EOI;
                    } else {
                        c = jSONLexer.text.charAt(i2);
                    }
                    jSONLexer.ch = c;
                    jSONLexer.token = 16;
                } else if (jSONLexer.ch == ']') {
                    i2 = jSONLexer.bp + 1;
                    jSONLexer.bp = i2;
                    if (i2 >= jSONLexer.len) {
                        c = JSONLexer.EOI;
                    } else {
                        c = jSONLexer.text.charAt(i2);
                    }
                    jSONLexer.ch = c;
                    jSONLexer.token = 15;
                } else {
                    jSONLexer.nextToken();
                }
            } else if (cls == Long.TYPE) {
                long scanLongValue = jSONLexer.scanLongValue();
                if (fieldInfo.fieldAccess) {
                    fieldInfo.field.setLong(createInstance, scanLongValue);
                } else {
                    fieldDeserializer.setValue(createInstance, new Long(scanLongValue));
                }
                if (jSONLexer.ch == ',') {
                    i2 = jSONLexer.bp + 1;
                    jSONLexer.bp = i2;
                    if (i2 >= jSONLexer.len) {
                        c = JSONLexer.EOI;
                    } else {
                        c = jSONLexer.text.charAt(i2);
                    }
                    jSONLexer.ch = c;
                    jSONLexer.token = 16;
                } else if (jSONLexer.ch == ']') {
                    i2 = jSONLexer.bp + 1;
                    jSONLexer.bp = i2;
                    if (i2 >= jSONLexer.len) {
                        c = JSONLexer.EOI;
                    } else {
                        c = jSONLexer.text.charAt(i2);
                    }
                    jSONLexer.ch = c;
                    jSONLexer.token = 15;
                } else {
                    jSONLexer.nextToken();
                }
            } else if (cls == Boolean.TYPE) {
                boolean scanBoolean = jSONLexer.scanBoolean();
                if (fieldInfo.fieldAccess) {
                    fieldInfo.field.setBoolean(createInstance, scanBoolean);
                } else {
                    fieldDeserializer.setValue(createInstance, Boolean.valueOf(scanBoolean));
                }
                if (jSONLexer.ch == ',') {
                    i2 = jSONLexer.bp + 1;
                    jSONLexer.bp = i2;
                    if (i2 >= jSONLexer.len) {
                        c = JSONLexer.EOI;
                    } else {
                        c = jSONLexer.text.charAt(i2);
                    }
                    jSONLexer.ch = c;
                    jSONLexer.token = 16;
                } else if (jSONLexer.ch == ']') {
                    i2 = jSONLexer.bp + 1;
                    jSONLexer.bp = i2;
                    if (i2 >= jSONLexer.len) {
                        c = JSONLexer.EOI;
                    } else {
                        c = jSONLexer.text.charAt(i2);
                    }
                    jSONLexer.ch = c;
                    jSONLexer.token = 15;
                } else {
                    jSONLexer.nextToken();
                }
            } else if (cls.isEnum()) {
                c = jSONLexer.ch;
                if (c == '\"') {
                    String scanSymbol = jSONLexer.scanSymbol(defaultJSONParser.symbolTable);
                    if (scanSymbol == null) {
                        r2 = null;
                    } else {
                        r2 = Enum.valueOf(cls, scanSymbol);
                    }
                } else if (c < '0' || c > '9') {
                    throw new JSONException("illegal enum." + jSONLexer.info());
                } else {
                    r2 = ((EnumDeserializer) ((DefaultFieldDeserializer) fieldDeserializer).getFieldValueDeserilizer(defaultJSONParser.config)).ordinalEnums[(int) jSONLexer.scanLongValue()];
                }
                fieldDeserializer.setValue(createInstance, r2);
                if (jSONLexer.ch == ',') {
                    i2 = jSONLexer.bp + 1;
                    jSONLexer.bp = i2;
                    if (i2 >= jSONLexer.len) {
                        c = JSONLexer.EOI;
                    } else {
                        c = jSONLexer.text.charAt(i2);
                    }
                    jSONLexer.ch = c;
                    jSONLexer.token = 16;
                } else if (jSONLexer.ch == ']') {
                    i2 = jSONLexer.bp + 1;
                    jSONLexer.bp = i2;
                    if (i2 >= jSONLexer.len) {
                        c = JSONLexer.EOI;
                    } else {
                        c = jSONLexer.text.charAt(i2);
                    }
                    jSONLexer.ch = c;
                    jSONLexer.token = 15;
                } else {
                    jSONLexer.nextToken();
                }
            } else if (cls == Date.class && jSONLexer.ch == '1') {
                fieldDeserializer.setValue(createInstance, new Date(jSONLexer.scanLongValue()));
                if (jSONLexer.ch == ',') {
                    i2 = jSONLexer.bp + 1;
                    jSONLexer.bp = i2;
                    if (i2 >= jSONLexer.len) {
                        c = JSONLexer.EOI;
                    } else {
                        c = jSONLexer.text.charAt(i2);
                    }
                    jSONLexer.ch = c;
                    jSONLexer.token = 16;
                } else if (jSONLexer.ch == ']') {
                    i2 = jSONLexer.bp + 1;
                    jSONLexer.bp = i2;
                    if (i2 >= jSONLexer.len) {
                        c = JSONLexer.EOI;
                    } else {
                        c = jSONLexer.text.charAt(i2);
                    }
                    jSONLexer.ch = c;
                    jSONLexer.token = 15;
                } else {
                    jSONLexer.nextToken();
                }
            } else {
                int i3;
                char c2;
                if (jSONLexer.ch == '[') {
                    i3 = jSONLexer.bp + 1;
                    jSONLexer.bp = i3;
                    if (i3 >= jSONLexer.len) {
                        c2 = JSONLexer.EOI;
                    } else {
                        c2 = jSONLexer.text.charAt(i3);
                    }
                    jSONLexer.ch = c2;
                    jSONLexer.token = 14;
                } else if (jSONLexer.ch == '{') {
                    i3 = jSONLexer.bp + 1;
                    jSONLexer.bp = i3;
                    if (i3 >= jSONLexer.len) {
                        c2 = JSONLexer.EOI;
                    } else {
                        c2 = jSONLexer.text.charAt(i3);
                    }
                    jSONLexer.ch = c2;
                    jSONLexer.token = 12;
                } else {
                    jSONLexer.nextToken();
                }
                fieldDeserializer.parseField(defaultJSONParser, createInstance, fieldInfo.fieldType, null);
                if (i2 == 93) {
                    if (jSONLexer.token != 15) {
                        throw new JSONException("syntax error");
                    }
                } else if (i2 == 44 && jSONLexer.token != 16) {
                    throw new JSONException("syntax error");
                }
            }
            i++;
        }
        if (jSONLexer.ch == ',') {
            i2 = jSONLexer.bp + 1;
            jSONLexer.bp = i2;
            if (i2 >= jSONLexer.len) {
                c = JSONLexer.EOI;
            } else {
                c = jSONLexer.text.charAt(i2);
            }
            jSONLexer.ch = c;
            jSONLexer.token = 16;
        } else {
            jSONLexer.nextToken();
        }
        return createInstance;
    }

    private <T> T deserialze(com.alibaba.fastjson.parser.DefaultJSONParser r36, java.lang.reflect.Type r37, java.lang.Object r38, java.lang.Object r39) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:com.alibaba.fastjson.parser.JavaBeanDeserializer.deserialze(com.alibaba.fastjson.parser.DefaultJSONParser, java.lang.reflect.Type, java.lang.Object, java.lang.Object):T. bs: [B:107:0x01bf, B:442:0x098a, B:469:0x0a01]
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
        r35 = this;
        r4 = com.alibaba.fastjson.JSON.class;
        r0 = r37;
        if (r0 == r4) goto L_0x000c;
    L_0x0006:
        r4 = com.alibaba.fastjson.JSONObject.class;
        r0 = r37;
        if (r0 != r4) goto L_0x0011;
    L_0x000c:
        r4 = r36.parse();
    L_0x0010:
        return r4;
    L_0x0011:
        r0 = r36;
        r0 = r0.lexer;
        r23 = r0;
        r0 = r23;
        r5 = r0.token;
        r4 = 8;
        if (r5 != r4) goto L_0x0028;
    L_0x001f:
        r4 = 16;
        r0 = r23;
        r0.nextToken(r4);
        r4 = 0;
        goto L_0x0010;
    L_0x0028:
        r0 = r23;
        r0 = r0.disableCircularReferenceDetect;
        r26 = r0;
        r0 = r36;
        r4 = r0.contex;
        if (r39 == 0) goto L_0x0afe;
    L_0x0034:
        if (r4 == 0) goto L_0x0afe;
    L_0x0036:
        r4 = r4.parent;
        r10 = r4;
    L_0x0039:
        r6 = 0;
        r9 = 0;
        r4 = 13;
        if (r5 != r4) goto L_0x005a;
    L_0x003f:
        r4 = 16;
        r0 = r23;	 Catch:{ all -> 0x00f0 }
        r0.nextToken(r4);	 Catch:{ all -> 0x00f0 }
        if (r39 != 0) goto L_0x004c;	 Catch:{ all -> 0x00f0 }
    L_0x0048:
        r39 = r35.createInstance(r36, r37);	 Catch:{ all -> 0x00f0 }
    L_0x004c:
        if (r6 == 0) goto L_0x0052;
    L_0x004e:
        r0 = r39;
        r6.object = r0;
    L_0x0052:
        r0 = r36;
        r0.setContext(r10);
        r4 = r39;
        goto L_0x0010;
    L_0x005a:
        r4 = 14;
        if (r5 != r4) goto L_0x0086;
    L_0x005e:
        r0 = r35;	 Catch:{ all -> 0x00f0 }
        r4 = r0.beanInfo;	 Catch:{ all -> 0x00f0 }
        r4 = r4.supportBeanToArray;	 Catch:{ all -> 0x00f0 }
        if (r4 != 0) goto L_0x0071;	 Catch:{ all -> 0x00f0 }
    L_0x0066:
        r0 = r23;	 Catch:{ all -> 0x00f0 }
        r4 = r0.features;	 Catch:{ all -> 0x00f0 }
        r7 = com.alibaba.fastjson.parser.Feature.SupportArrayToBean;	 Catch:{ all -> 0x00f0 }
        r7 = r7.mask;	 Catch:{ all -> 0x00f0 }
        r4 = r4 & r7;	 Catch:{ all -> 0x00f0 }
        if (r4 == 0) goto L_0x0084;	 Catch:{ all -> 0x00f0 }
    L_0x0071:
        r4 = 1;	 Catch:{ all -> 0x00f0 }
    L_0x0072:
        if (r4 == 0) goto L_0x0086;	 Catch:{ all -> 0x00f0 }
    L_0x0074:
        r4 = r35.deserialzeArrayMapping(r36, r37, r38, r39);	 Catch:{ all -> 0x00f0 }
        if (r6 == 0) goto L_0x007e;
    L_0x007a:
        r0 = r39;
        r6.object = r0;
    L_0x007e:
        r0 = r36;
        r0.setContext(r10);
        goto L_0x0010;
    L_0x0084:
        r4 = 0;
        goto L_0x0072;
    L_0x0086:
        r4 = 12;
        if (r5 == r4) goto L_0x00fd;
    L_0x008a:
        r4 = 16;
        if (r5 == r4) goto L_0x00fd;
    L_0x008e:
        r4 = r23.isBlankInput();	 Catch:{ all -> 0x00f0 }
        if (r4 == 0) goto L_0x00a2;
    L_0x0094:
        r4 = 0;
        if (r6 == 0) goto L_0x009b;
    L_0x0097:
        r0 = r39;
        r6.object = r0;
    L_0x009b:
        r0 = r36;
        r0.setContext(r10);
        goto L_0x0010;
    L_0x00a2:
        r4 = 4;
        if (r5 != r4) goto L_0x00c0;
    L_0x00a5:
        r4 = r23.stringVal();	 Catch:{ all -> 0x00f0 }
        r4 = r4.length();	 Catch:{ all -> 0x00f0 }
        if (r4 != 0) goto L_0x00c0;	 Catch:{ all -> 0x00f0 }
    L_0x00af:
        r23.nextToken();	 Catch:{ all -> 0x00f0 }
        r4 = 0;
        if (r6 == 0) goto L_0x00b9;
    L_0x00b5:
        r0 = r39;
        r6.object = r0;
    L_0x00b9:
        r0 = r36;
        r0.setContext(r10);
        goto L_0x0010;
    L_0x00c0:
        r4 = new java.lang.StringBuffer;	 Catch:{ all -> 0x00f0 }
        r4.<init>();	 Catch:{ all -> 0x00f0 }
        r5 = "syntax error, expect {, actual ";	 Catch:{ all -> 0x00f0 }
        r4 = r4.append(r5);	 Catch:{ all -> 0x00f0 }
        r5 = r23.info();	 Catch:{ all -> 0x00f0 }
        r4 = r4.append(r5);	 Catch:{ all -> 0x00f0 }
        r0 = r38;	 Catch:{ all -> 0x00f0 }
        r5 = r0 instanceof java.lang.String;	 Catch:{ all -> 0x00f0 }
        if (r5 == 0) goto L_0x00e6;	 Catch:{ all -> 0x00f0 }
    L_0x00da:
        r5 = ", fieldName ";	 Catch:{ all -> 0x00f0 }
        r5 = r4.append(r5);	 Catch:{ all -> 0x00f0 }
        r0 = r38;	 Catch:{ all -> 0x00f0 }
        r5.append(r0);	 Catch:{ all -> 0x00f0 }
    L_0x00e6:
        r5 = new com.alibaba.fastjson.JSONException;	 Catch:{ all -> 0x00f0 }
        r4 = r4.toString();	 Catch:{ all -> 0x00f0 }
        r5.<init>(r4);	 Catch:{ all -> 0x00f0 }
        throw r5;	 Catch:{ all -> 0x00f0 }
    L_0x00f0:
        r4 = move-exception;
        r7 = r39;
    L_0x00f3:
        if (r6 == 0) goto L_0x00f7;
    L_0x00f5:
        r6.object = r7;
    L_0x00f7:
        r0 = r36;
        r0.setContext(r10);
        throw r4;
    L_0x00fd:
        r0 = r36;	 Catch:{ all -> 0x00f0 }
        r4 = r0.resolveStatus;	 Catch:{ all -> 0x00f0 }
        r5 = 2;	 Catch:{ all -> 0x00f0 }
        if (r4 != r5) goto L_0x0109;	 Catch:{ all -> 0x00f0 }
    L_0x0104:
        r4 = 0;	 Catch:{ all -> 0x00f0 }
        r0 = r36;	 Catch:{ all -> 0x00f0 }
        r0.resolveStatus = r4;	 Catch:{ all -> 0x00f0 }
    L_0x0109:
        r0 = r35;	 Catch:{ all -> 0x00f0 }
        r4 = r0.beanInfo;	 Catch:{ all -> 0x00f0 }
        r0 = r4.typeKey;	 Catch:{ all -> 0x00f0 }
        r27 = r0;	 Catch:{ all -> 0x00f0 }
        r24 = 0;	 Catch:{ all -> 0x00f0 }
        r21 = 0;	 Catch:{ all -> 0x00f0 }
        r0 = r35;	 Catch:{ all -> 0x00f0 }
        r4 = r0.sortedFieldDeserializers;	 Catch:{ all -> 0x00f0 }
        r0 = r4.length;	 Catch:{ all -> 0x00f0 }
        r28 = r0;	 Catch:{ all -> 0x00f0 }
        r14 = r24;
        r8 = r6;
        r7 = r39;
        r6 = r21;
    L_0x0123:
        r11 = 0;
        r12 = 0;
        r5 = 0;
        r4 = 0;
        r16 = 0;
        r13 = (r14 > r16 ? 1 : (r14 == r16 ? 0 : -1));
        if (r13 == 0) goto L_0x0af3;
    L_0x012d:
        r0 = r35;	 Catch:{ all -> 0x05f5 }
        r12 = r0.getFieldDeserializerByHash(r14);	 Catch:{ all -> 0x05f5 }
        if (r12 == 0) goto L_0x0139;	 Catch:{ all -> 0x05f5 }
    L_0x0135:
        r5 = r12.fieldInfo;	 Catch:{ all -> 0x05f5 }
        r4 = r5.fieldClass;	 Catch:{ all -> 0x05f5 }
    L_0x0139:
        r14 = 0;	 Catch:{ all -> 0x05f5 }
        r24 = r14;	 Catch:{ all -> 0x05f5 }
    L_0x013d:
        if (r12 != 0) goto L_0x0ae9;	 Catch:{ all -> 0x05f5 }
    L_0x013f:
        r0 = r28;	 Catch:{ all -> 0x05f5 }
        if (r6 >= r0) goto L_0x01de;	 Catch:{ all -> 0x05f5 }
    L_0x0143:
        r0 = r35;	 Catch:{ all -> 0x05f5 }
        r4 = r0.sortedFieldDeserializers;	 Catch:{ all -> 0x05f5 }
        r12 = r4[r6];	 Catch:{ all -> 0x05f5 }
        r5 = r12.fieldInfo;	 Catch:{ all -> 0x05f5 }
        r4 = r5.fieldClass;	 Catch:{ all -> 0x05f5 }
        r6 = r6 + 1;	 Catch:{ all -> 0x05f5 }
        r19 = r4;	 Catch:{ all -> 0x05f5 }
        r20 = r5;	 Catch:{ all -> 0x05f5 }
        r21 = r6;	 Catch:{ all -> 0x05f5 }
        r22 = r12;	 Catch:{ all -> 0x05f5 }
    L_0x0157:
        r6 = 0;	 Catch:{ all -> 0x05f5 }
        r5 = 0;	 Catch:{ all -> 0x05f5 }
        r4 = 0;	 Catch:{ all -> 0x05f5 }
        r15 = 0;	 Catch:{ all -> 0x05f5 }
        r16 = 0;	 Catch:{ all -> 0x05f5 }
        r14 = 0;	 Catch:{ all -> 0x05f5 }
        r12 = 0;	 Catch:{ all -> 0x05f5 }
        if (r22 == 0) goto L_0x0aa1;	 Catch:{ all -> 0x05f5 }
    L_0x0162:
        r0 = r20;	 Catch:{ all -> 0x05f5 }
        r0 = r0.nameHashCode;	 Catch:{ all -> 0x05f5 }
        r30 = r0;	 Catch:{ all -> 0x05f5 }
        r18 = java.lang.Integer.TYPE;	 Catch:{ all -> 0x05f5 }
        r0 = r19;	 Catch:{ all -> 0x05f5 }
        r1 = r18;	 Catch:{ all -> 0x05f5 }
        if (r0 == r1) goto L_0x0178;	 Catch:{ all -> 0x05f5 }
    L_0x0170:
        r18 = java.lang.Integer.class;	 Catch:{ all -> 0x05f5 }
        r0 = r19;	 Catch:{ all -> 0x05f5 }
        r1 = r18;	 Catch:{ all -> 0x05f5 }
        if (r0 != r1) goto L_0x0204;	 Catch:{ all -> 0x05f5 }
    L_0x0178:
        r0 = r23;	 Catch:{ all -> 0x05f5 }
        r1 = r30;	 Catch:{ all -> 0x05f5 }
        r15 = r0.scanFieldInt(r1);	 Catch:{ all -> 0x05f5 }
        r0 = r23;	 Catch:{ all -> 0x05f5 }
        r0 = r0.matchStat;	 Catch:{ all -> 0x05f5 }
        r18 = r0;	 Catch:{ all -> 0x05f5 }
        if (r18 <= 0) goto L_0x01ea;	 Catch:{ all -> 0x05f5 }
    L_0x0188:
        r6 = 1;	 Catch:{ all -> 0x05f5 }
        r5 = 1;	 Catch:{ all -> 0x05f5 }
        r18 = r6;	 Catch:{ all -> 0x05f5 }
        r32 = r14;	 Catch:{ all -> 0x05f5 }
        r33 = r16;	 Catch:{ all -> 0x05f5 }
        r16 = r4;	 Catch:{ all -> 0x05f5 }
        r17 = r5;	 Catch:{ all -> 0x05f5 }
        r4 = r12;	 Catch:{ all -> 0x05f5 }
        r12 = r32;	 Catch:{ all -> 0x05f5 }
        r13 = r15;	 Catch:{ all -> 0x05f5 }
        r14 = r33;	 Catch:{ all -> 0x05f5 }
    L_0x019a:
        if (r18 != 0) goto L_0x0756;	 Catch:{ all -> 0x05f5 }
    L_0x019c:
        r0 = r36;	 Catch:{ all -> 0x05f5 }
        r6 = r0.symbolTable;	 Catch:{ all -> 0x05f5 }
        r0 = r23;	 Catch:{ all -> 0x05f5 }
        r6 = r0.scanSymbol(r6);	 Catch:{ all -> 0x05f5 }
        if (r6 != 0) goto L_0x05b9;	 Catch:{ all -> 0x05f5 }
    L_0x01a8:
        r0 = r23;	 Catch:{ all -> 0x05f5 }
        r11 = r0.token;	 Catch:{ all -> 0x05f5 }
        r29 = 13;	 Catch:{ all -> 0x05f5 }
        r0 = r29;	 Catch:{ all -> 0x05f5 }
        if (r11 != r0) goto L_0x05ad;	 Catch:{ all -> 0x05f5 }
    L_0x01b2:
        r4 = 16;	 Catch:{ all -> 0x05f5 }
        r0 = r23;	 Catch:{ all -> 0x05f5 }
        r0.nextToken(r4);	 Catch:{ all -> 0x05f5 }
        r6 = r8;
        r4 = r7;
    L_0x01bb:
        if (r4 != 0) goto L_0x09ee;
    L_0x01bd:
        if (r9 != 0) goto L_0x0948;
    L_0x01bf:
        r39 = r35.createInstance(r36, r37);	 Catch:{ all -> 0x09e8 }
        if (r6 != 0) goto L_0x0a34;
    L_0x01c5:
        r0 = r36;	 Catch:{ all -> 0x00f0 }
        r1 = r39;	 Catch:{ all -> 0x00f0 }
        r2 = r38;	 Catch:{ all -> 0x00f0 }
        r4 = r0.setContext(r10, r1, r2);	 Catch:{ all -> 0x00f0 }
    L_0x01cf:
        if (r4 == 0) goto L_0x01d5;
    L_0x01d1:
        r0 = r39;
        r4.object = r0;
    L_0x01d5:
        r0 = r36;
        r0.setContext(r10);
        r4 = r39;
        goto L_0x0010;
    L_0x01de:
        r6 = r6 + 1;
        r19 = r4;
        r20 = r5;
        r21 = r6;
        r22 = r12;
        goto L_0x0157;
    L_0x01ea:
        r0 = r23;	 Catch:{ all -> 0x05f5 }
        r0 = r0.matchStat;	 Catch:{ all -> 0x05f5 }
        r18 = r0;	 Catch:{ all -> 0x05f5 }
        r29 = -2;	 Catch:{ all -> 0x05f5 }
        r0 = r18;	 Catch:{ all -> 0x05f5 }
        r1 = r29;	 Catch:{ all -> 0x05f5 }
        if (r0 != r1) goto L_0x0aa1;	 Catch:{ all -> 0x05f5 }
    L_0x01f8:
        r0 = r23;	 Catch:{ all -> 0x05f5 }
        r0 = r0.fieldHash;	 Catch:{ all -> 0x05f5 }
        r24 = r0;	 Catch:{ all -> 0x05f5 }
        r6 = r21;	 Catch:{ all -> 0x05f5 }
        r14 = r24;	 Catch:{ all -> 0x05f5 }
        goto L_0x0123;	 Catch:{ all -> 0x05f5 }
    L_0x0204:
        r18 = java.lang.Long.TYPE;	 Catch:{ all -> 0x05f5 }
        r0 = r19;	 Catch:{ all -> 0x05f5 }
        r1 = r18;	 Catch:{ all -> 0x05f5 }
        if (r0 == r1) goto L_0x0214;	 Catch:{ all -> 0x05f5 }
    L_0x020c:
        r18 = java.lang.Long.class;	 Catch:{ all -> 0x05f5 }
        r0 = r19;	 Catch:{ all -> 0x05f5 }
        r1 = r18;	 Catch:{ all -> 0x05f5 }
        if (r0 != r1) goto L_0x0252;	 Catch:{ all -> 0x05f5 }
    L_0x0214:
        r0 = r23;	 Catch:{ all -> 0x05f5 }
        r1 = r30;	 Catch:{ all -> 0x05f5 }
        r16 = r0.scanFieldLong(r1);	 Catch:{ all -> 0x05f5 }
        r0 = r23;	 Catch:{ all -> 0x05f5 }
        r0 = r0.matchStat;	 Catch:{ all -> 0x05f5 }
        r18 = r0;	 Catch:{ all -> 0x05f5 }
        if (r18 <= 0) goto L_0x0238;	 Catch:{ all -> 0x05f5 }
    L_0x0224:
        r6 = 1;	 Catch:{ all -> 0x05f5 }
        r5 = 1;	 Catch:{ all -> 0x05f5 }
        r18 = r6;	 Catch:{ all -> 0x05f5 }
        r32 = r14;	 Catch:{ all -> 0x05f5 }
        r33 = r16;	 Catch:{ all -> 0x05f5 }
        r16 = r4;	 Catch:{ all -> 0x05f5 }
        r17 = r5;	 Catch:{ all -> 0x05f5 }
        r4 = r12;	 Catch:{ all -> 0x05f5 }
        r12 = r32;	 Catch:{ all -> 0x05f5 }
        r13 = r15;	 Catch:{ all -> 0x05f5 }
        r14 = r33;	 Catch:{ all -> 0x05f5 }
        goto L_0x019a;	 Catch:{ all -> 0x05f5 }
    L_0x0238:
        r0 = r23;	 Catch:{ all -> 0x05f5 }
        r0 = r0.matchStat;	 Catch:{ all -> 0x05f5 }
        r18 = r0;	 Catch:{ all -> 0x05f5 }
        r29 = -2;	 Catch:{ all -> 0x05f5 }
        r0 = r18;	 Catch:{ all -> 0x05f5 }
        r1 = r29;	 Catch:{ all -> 0x05f5 }
        if (r0 != r1) goto L_0x0aa1;	 Catch:{ all -> 0x05f5 }
    L_0x0246:
        r0 = r23;	 Catch:{ all -> 0x05f5 }
        r0 = r0.fieldHash;	 Catch:{ all -> 0x05f5 }
        r24 = r0;	 Catch:{ all -> 0x05f5 }
        r6 = r21;	 Catch:{ all -> 0x05f5 }
        r14 = r24;	 Catch:{ all -> 0x05f5 }
        goto L_0x0123;	 Catch:{ all -> 0x05f5 }
    L_0x0252:
        r18 = java.lang.String.class;	 Catch:{ all -> 0x05f5 }
        r0 = r19;	 Catch:{ all -> 0x05f5 }
        r1 = r18;	 Catch:{ all -> 0x05f5 }
        if (r0 != r1) goto L_0x0298;	 Catch:{ all -> 0x05f5 }
    L_0x025a:
        r0 = r23;	 Catch:{ all -> 0x05f5 }
        r1 = r30;	 Catch:{ all -> 0x05f5 }
        r4 = r0.scanFieldString(r1);	 Catch:{ all -> 0x05f5 }
        r0 = r23;	 Catch:{ all -> 0x05f5 }
        r0 = r0.matchStat;	 Catch:{ all -> 0x05f5 }
        r18 = r0;	 Catch:{ all -> 0x05f5 }
        if (r18 <= 0) goto L_0x027e;	 Catch:{ all -> 0x05f5 }
    L_0x026a:
        r6 = 1;	 Catch:{ all -> 0x05f5 }
        r5 = 1;	 Catch:{ all -> 0x05f5 }
        r18 = r6;	 Catch:{ all -> 0x05f5 }
        r32 = r14;	 Catch:{ all -> 0x05f5 }
        r33 = r16;	 Catch:{ all -> 0x05f5 }
        r16 = r4;	 Catch:{ all -> 0x05f5 }
        r17 = r5;	 Catch:{ all -> 0x05f5 }
        r4 = r12;	 Catch:{ all -> 0x05f5 }
        r12 = r32;	 Catch:{ all -> 0x05f5 }
        r13 = r15;	 Catch:{ all -> 0x05f5 }
        r14 = r33;	 Catch:{ all -> 0x05f5 }
        goto L_0x019a;	 Catch:{ all -> 0x05f5 }
    L_0x027e:
        r0 = r23;	 Catch:{ all -> 0x05f5 }
        r0 = r0.matchStat;	 Catch:{ all -> 0x05f5 }
        r18 = r0;	 Catch:{ all -> 0x05f5 }
        r29 = -2;	 Catch:{ all -> 0x05f5 }
        r0 = r18;	 Catch:{ all -> 0x05f5 }
        r1 = r29;	 Catch:{ all -> 0x05f5 }
        if (r0 != r1) goto L_0x0ad7;	 Catch:{ all -> 0x05f5 }
    L_0x028c:
        r0 = r23;	 Catch:{ all -> 0x05f5 }
        r0 = r0.fieldHash;	 Catch:{ all -> 0x05f5 }
        r24 = r0;	 Catch:{ all -> 0x05f5 }
        r6 = r21;	 Catch:{ all -> 0x05f5 }
        r14 = r24;	 Catch:{ all -> 0x05f5 }
        goto L_0x0123;	 Catch:{ all -> 0x05f5 }
    L_0x0298:
        r18 = java.util.Date.class;	 Catch:{ all -> 0x05f5 }
        r0 = r19;	 Catch:{ all -> 0x05f5 }
        r1 = r18;	 Catch:{ all -> 0x05f5 }
        if (r0 != r1) goto L_0x02de;	 Catch:{ all -> 0x05f5 }
    L_0x02a0:
        r0 = r23;	 Catch:{ all -> 0x05f5 }
        r1 = r30;	 Catch:{ all -> 0x05f5 }
        r4 = r0.scanFieldDate(r1);	 Catch:{ all -> 0x05f5 }
        r0 = r23;	 Catch:{ all -> 0x05f5 }
        r0 = r0.matchStat;	 Catch:{ all -> 0x05f5 }
        r18 = r0;	 Catch:{ all -> 0x05f5 }
        if (r18 <= 0) goto L_0x02c4;	 Catch:{ all -> 0x05f5 }
    L_0x02b0:
        r6 = 1;	 Catch:{ all -> 0x05f5 }
        r5 = 1;	 Catch:{ all -> 0x05f5 }
        r18 = r6;	 Catch:{ all -> 0x05f5 }
        r32 = r14;	 Catch:{ all -> 0x05f5 }
        r33 = r16;	 Catch:{ all -> 0x05f5 }
        r16 = r4;	 Catch:{ all -> 0x05f5 }
        r17 = r5;	 Catch:{ all -> 0x05f5 }
        r4 = r12;	 Catch:{ all -> 0x05f5 }
        r12 = r32;	 Catch:{ all -> 0x05f5 }
        r13 = r15;	 Catch:{ all -> 0x05f5 }
        r14 = r33;	 Catch:{ all -> 0x05f5 }
        goto L_0x019a;	 Catch:{ all -> 0x05f5 }
    L_0x02c4:
        r0 = r23;	 Catch:{ all -> 0x05f5 }
        r0 = r0.matchStat;	 Catch:{ all -> 0x05f5 }
        r18 = r0;	 Catch:{ all -> 0x05f5 }
        r29 = -2;	 Catch:{ all -> 0x05f5 }
        r0 = r18;	 Catch:{ all -> 0x05f5 }
        r1 = r29;	 Catch:{ all -> 0x05f5 }
        if (r0 != r1) goto L_0x0ac5;	 Catch:{ all -> 0x05f5 }
    L_0x02d2:
        r0 = r23;	 Catch:{ all -> 0x05f5 }
        r0 = r0.fieldHash;	 Catch:{ all -> 0x05f5 }
        r24 = r0;	 Catch:{ all -> 0x05f5 }
        r6 = r21;	 Catch:{ all -> 0x05f5 }
        r14 = r24;	 Catch:{ all -> 0x05f5 }
        goto L_0x0123;	 Catch:{ all -> 0x05f5 }
    L_0x02de:
        r18 = java.lang.Boolean.TYPE;	 Catch:{ all -> 0x05f5 }
        r0 = r19;	 Catch:{ all -> 0x05f5 }
        r1 = r18;	 Catch:{ all -> 0x05f5 }
        if (r0 == r1) goto L_0x02ee;	 Catch:{ all -> 0x05f5 }
    L_0x02e6:
        r18 = java.lang.Boolean.class;	 Catch:{ all -> 0x05f5 }
        r0 = r19;	 Catch:{ all -> 0x05f5 }
        r1 = r18;	 Catch:{ all -> 0x05f5 }
        if (r0 != r1) goto L_0x0330;	 Catch:{ all -> 0x05f5 }
    L_0x02ee:
        r0 = r23;	 Catch:{ all -> 0x05f5 }
        r1 = r30;	 Catch:{ all -> 0x05f5 }
        r4 = r0.scanFieldBoolean(r1);	 Catch:{ all -> 0x05f5 }
        r4 = java.lang.Boolean.valueOf(r4);	 Catch:{ all -> 0x05f5 }
        r0 = r23;	 Catch:{ all -> 0x05f5 }
        r0 = r0.matchStat;	 Catch:{ all -> 0x05f5 }
        r18 = r0;	 Catch:{ all -> 0x05f5 }
        if (r18 <= 0) goto L_0x0316;	 Catch:{ all -> 0x05f5 }
    L_0x0302:
        r6 = 1;	 Catch:{ all -> 0x05f5 }
        r5 = 1;	 Catch:{ all -> 0x05f5 }
        r18 = r6;	 Catch:{ all -> 0x05f5 }
        r32 = r14;	 Catch:{ all -> 0x05f5 }
        r33 = r16;	 Catch:{ all -> 0x05f5 }
        r16 = r4;	 Catch:{ all -> 0x05f5 }
        r17 = r5;	 Catch:{ all -> 0x05f5 }
        r4 = r12;	 Catch:{ all -> 0x05f5 }
        r12 = r32;	 Catch:{ all -> 0x05f5 }
        r13 = r15;	 Catch:{ all -> 0x05f5 }
        r14 = r33;	 Catch:{ all -> 0x05f5 }
        goto L_0x019a;	 Catch:{ all -> 0x05f5 }
    L_0x0316:
        r0 = r23;	 Catch:{ all -> 0x05f5 }
        r0 = r0.matchStat;	 Catch:{ all -> 0x05f5 }
        r18 = r0;	 Catch:{ all -> 0x05f5 }
        r29 = -2;	 Catch:{ all -> 0x05f5 }
        r0 = r18;	 Catch:{ all -> 0x05f5 }
        r1 = r29;	 Catch:{ all -> 0x05f5 }
        if (r0 != r1) goto L_0x0ab3;	 Catch:{ all -> 0x05f5 }
    L_0x0324:
        r0 = r23;	 Catch:{ all -> 0x05f5 }
        r0 = r0.fieldHash;	 Catch:{ all -> 0x05f5 }
        r24 = r0;	 Catch:{ all -> 0x05f5 }
        r6 = r21;	 Catch:{ all -> 0x05f5 }
        r14 = r24;	 Catch:{ all -> 0x05f5 }
        goto L_0x0123;	 Catch:{ all -> 0x05f5 }
    L_0x0330:
        r18 = java.lang.Float.TYPE;	 Catch:{ all -> 0x05f5 }
        r0 = r19;	 Catch:{ all -> 0x05f5 }
        r1 = r18;	 Catch:{ all -> 0x05f5 }
        if (r0 == r1) goto L_0x0340;	 Catch:{ all -> 0x05f5 }
    L_0x0338:
        r18 = java.lang.Float.class;	 Catch:{ all -> 0x05f5 }
        r0 = r19;	 Catch:{ all -> 0x05f5 }
        r1 = r18;	 Catch:{ all -> 0x05f5 }
        if (r0 != r1) goto L_0x037e;	 Catch:{ all -> 0x05f5 }
    L_0x0340:
        r0 = r23;	 Catch:{ all -> 0x05f5 }
        r1 = r30;	 Catch:{ all -> 0x05f5 }
        r14 = r0.scanFieldFloat(r1);	 Catch:{ all -> 0x05f5 }
        r0 = r23;	 Catch:{ all -> 0x05f5 }
        r0 = r0.matchStat;	 Catch:{ all -> 0x05f5 }
        r18 = r0;	 Catch:{ all -> 0x05f5 }
        if (r18 <= 0) goto L_0x0364;	 Catch:{ all -> 0x05f5 }
    L_0x0350:
        r6 = 1;	 Catch:{ all -> 0x05f5 }
        r5 = 1;	 Catch:{ all -> 0x05f5 }
        r18 = r6;	 Catch:{ all -> 0x05f5 }
        r32 = r14;	 Catch:{ all -> 0x05f5 }
        r33 = r16;	 Catch:{ all -> 0x05f5 }
        r16 = r4;	 Catch:{ all -> 0x05f5 }
        r17 = r5;	 Catch:{ all -> 0x05f5 }
        r4 = r12;	 Catch:{ all -> 0x05f5 }
        r12 = r32;	 Catch:{ all -> 0x05f5 }
        r13 = r15;	 Catch:{ all -> 0x05f5 }
        r14 = r33;	 Catch:{ all -> 0x05f5 }
        goto L_0x019a;	 Catch:{ all -> 0x05f5 }
    L_0x0364:
        r0 = r23;	 Catch:{ all -> 0x05f5 }
        r0 = r0.matchStat;	 Catch:{ all -> 0x05f5 }
        r18 = r0;	 Catch:{ all -> 0x05f5 }
        r29 = -2;	 Catch:{ all -> 0x05f5 }
        r0 = r18;	 Catch:{ all -> 0x05f5 }
        r1 = r29;	 Catch:{ all -> 0x05f5 }
        if (r0 != r1) goto L_0x0aa1;	 Catch:{ all -> 0x05f5 }
    L_0x0372:
        r0 = r23;	 Catch:{ all -> 0x05f5 }
        r0 = r0.fieldHash;	 Catch:{ all -> 0x05f5 }
        r24 = r0;	 Catch:{ all -> 0x05f5 }
        r6 = r21;	 Catch:{ all -> 0x05f5 }
        r14 = r24;	 Catch:{ all -> 0x05f5 }
        goto L_0x0123;	 Catch:{ all -> 0x05f5 }
    L_0x037e:
        r18 = java.lang.Double.TYPE;	 Catch:{ all -> 0x05f5 }
        r0 = r19;	 Catch:{ all -> 0x05f5 }
        r1 = r18;	 Catch:{ all -> 0x05f5 }
        if (r0 == r1) goto L_0x038e;	 Catch:{ all -> 0x05f5 }
    L_0x0386:
        r18 = java.lang.Double.class;	 Catch:{ all -> 0x05f5 }
        r0 = r19;	 Catch:{ all -> 0x05f5 }
        r1 = r18;	 Catch:{ all -> 0x05f5 }
        if (r0 != r1) goto L_0x03cc;	 Catch:{ all -> 0x05f5 }
    L_0x038e:
        r0 = r23;	 Catch:{ all -> 0x05f5 }
        r1 = r30;	 Catch:{ all -> 0x05f5 }
        r12 = r0.scanFieldDouble(r1);	 Catch:{ all -> 0x05f5 }
        r0 = r23;	 Catch:{ all -> 0x05f5 }
        r0 = r0.matchStat;	 Catch:{ all -> 0x05f5 }
        r18 = r0;	 Catch:{ all -> 0x05f5 }
        if (r18 <= 0) goto L_0x03b2;	 Catch:{ all -> 0x05f5 }
    L_0x039e:
        r6 = 1;	 Catch:{ all -> 0x05f5 }
        r5 = 1;	 Catch:{ all -> 0x05f5 }
        r18 = r6;	 Catch:{ all -> 0x05f5 }
        r32 = r14;	 Catch:{ all -> 0x05f5 }
        r33 = r16;	 Catch:{ all -> 0x05f5 }
        r16 = r4;	 Catch:{ all -> 0x05f5 }
        r17 = r5;	 Catch:{ all -> 0x05f5 }
        r4 = r12;	 Catch:{ all -> 0x05f5 }
        r12 = r32;	 Catch:{ all -> 0x05f5 }
        r13 = r15;	 Catch:{ all -> 0x05f5 }
        r14 = r33;	 Catch:{ all -> 0x05f5 }
        goto L_0x019a;	 Catch:{ all -> 0x05f5 }
    L_0x03b2:
        r0 = r23;	 Catch:{ all -> 0x05f5 }
        r0 = r0.matchStat;	 Catch:{ all -> 0x05f5 }
        r18 = r0;	 Catch:{ all -> 0x05f5 }
        r29 = -2;	 Catch:{ all -> 0x05f5 }
        r0 = r18;	 Catch:{ all -> 0x05f5 }
        r1 = r29;	 Catch:{ all -> 0x05f5 }
        if (r0 != r1) goto L_0x0aa1;	 Catch:{ all -> 0x05f5 }
    L_0x03c0:
        r0 = r23;	 Catch:{ all -> 0x05f5 }
        r0 = r0.fieldHash;	 Catch:{ all -> 0x05f5 }
        r24 = r0;	 Catch:{ all -> 0x05f5 }
        r6 = r21;	 Catch:{ all -> 0x05f5 }
        r14 = r24;	 Catch:{ all -> 0x05f5 }
        goto L_0x0123;	 Catch:{ all -> 0x05f5 }
    L_0x03cc:
        r0 = r20;	 Catch:{ all -> 0x05f5 }
        r0 = r0.isEnum;	 Catch:{ all -> 0x05f5 }
        r18 = r0;	 Catch:{ all -> 0x05f5 }
        if (r18 == 0) goto L_0x042c;	 Catch:{ all -> 0x05f5 }
    L_0x03d4:
        r0 = r36;	 Catch:{ all -> 0x05f5 }
        r0 = r0.config;	 Catch:{ all -> 0x05f5 }
        r18 = r0;	 Catch:{ all -> 0x05f5 }
        r18 = r18.getDeserializer(r19);	 Catch:{ all -> 0x05f5 }
        r0 = r18;	 Catch:{ all -> 0x05f5 }
        r0 = r0 instanceof com.alibaba.fastjson.parser.EnumDeserializer;	 Catch:{ all -> 0x05f5 }
        r18 = r0;	 Catch:{ all -> 0x05f5 }
        if (r18 == 0) goto L_0x042c;	 Catch:{ all -> 0x05f5 }
    L_0x03e6:
        r0 = r23;	 Catch:{ all -> 0x05f5 }
        r1 = r30;	 Catch:{ all -> 0x05f5 }
        r30 = r0.scanFieldSymbol(r1);	 Catch:{ all -> 0x05f5 }
        r0 = r23;	 Catch:{ all -> 0x05f5 }
        r0 = r0.matchStat;	 Catch:{ all -> 0x05f5 }
        r18 = r0;	 Catch:{ all -> 0x05f5 }
        if (r18 <= 0) goto L_0x0412;	 Catch:{ all -> 0x05f5 }
    L_0x03f6:
        r6 = 1;	 Catch:{ all -> 0x05f5 }
        r5 = 1;	 Catch:{ all -> 0x05f5 }
        r0 = r22;	 Catch:{ all -> 0x05f5 }
        r1 = r30;	 Catch:{ all -> 0x05f5 }
        r4 = r0.getEnumByHashCode(r1);	 Catch:{ all -> 0x05f5 }
    L_0x0400:
        r18 = r6;	 Catch:{ all -> 0x05f5 }
        r32 = r14;	 Catch:{ all -> 0x05f5 }
        r33 = r16;	 Catch:{ all -> 0x05f5 }
        r16 = r4;	 Catch:{ all -> 0x05f5 }
        r17 = r5;	 Catch:{ all -> 0x05f5 }
        r4 = r12;	 Catch:{ all -> 0x05f5 }
        r12 = r32;	 Catch:{ all -> 0x05f5 }
        r13 = r15;	 Catch:{ all -> 0x05f5 }
        r14 = r33;	 Catch:{ all -> 0x05f5 }
        goto L_0x019a;	 Catch:{ all -> 0x05f5 }
    L_0x0412:
        r0 = r23;	 Catch:{ all -> 0x05f5 }
        r0 = r0.matchStat;	 Catch:{ all -> 0x05f5 }
        r18 = r0;	 Catch:{ all -> 0x05f5 }
        r29 = -2;	 Catch:{ all -> 0x05f5 }
        r0 = r18;	 Catch:{ all -> 0x05f5 }
        r1 = r29;	 Catch:{ all -> 0x05f5 }
        if (r0 != r1) goto L_0x0400;	 Catch:{ all -> 0x05f5 }
    L_0x0420:
        r0 = r23;	 Catch:{ all -> 0x05f5 }
        r0 = r0.fieldHash;	 Catch:{ all -> 0x05f5 }
        r24 = r0;	 Catch:{ all -> 0x05f5 }
        r6 = r21;	 Catch:{ all -> 0x05f5 }
        r14 = r24;	 Catch:{ all -> 0x05f5 }
        goto L_0x0123;	 Catch:{ all -> 0x05f5 }
    L_0x042c:
        r18 = int[].class;	 Catch:{ all -> 0x05f5 }
        r0 = r19;	 Catch:{ all -> 0x05f5 }
        r1 = r18;	 Catch:{ all -> 0x05f5 }
        if (r0 != r1) goto L_0x0472;	 Catch:{ all -> 0x05f5 }
    L_0x0434:
        r0 = r23;	 Catch:{ all -> 0x05f5 }
        r1 = r30;	 Catch:{ all -> 0x05f5 }
        r4 = r0.scanFieldIntArray(r1);	 Catch:{ all -> 0x05f5 }
        r0 = r23;	 Catch:{ all -> 0x05f5 }
        r0 = r0.matchStat;	 Catch:{ all -> 0x05f5 }
        r18 = r0;	 Catch:{ all -> 0x05f5 }
        if (r18 <= 0) goto L_0x0458;	 Catch:{ all -> 0x05f5 }
    L_0x0444:
        r6 = 1;	 Catch:{ all -> 0x05f5 }
        r5 = 1;	 Catch:{ all -> 0x05f5 }
        r18 = r6;	 Catch:{ all -> 0x05f5 }
        r32 = r14;	 Catch:{ all -> 0x05f5 }
        r33 = r16;	 Catch:{ all -> 0x05f5 }
        r16 = r4;	 Catch:{ all -> 0x05f5 }
        r17 = r5;	 Catch:{ all -> 0x05f5 }
        r4 = r12;	 Catch:{ all -> 0x05f5 }
        r12 = r32;	 Catch:{ all -> 0x05f5 }
        r13 = r15;	 Catch:{ all -> 0x05f5 }
        r14 = r33;	 Catch:{ all -> 0x05f5 }
        goto L_0x019a;	 Catch:{ all -> 0x05f5 }
    L_0x0458:
        r0 = r23;	 Catch:{ all -> 0x05f5 }
        r0 = r0.matchStat;	 Catch:{ all -> 0x05f5 }
        r18 = r0;	 Catch:{ all -> 0x05f5 }
        r29 = -2;	 Catch:{ all -> 0x05f5 }
        r0 = r18;	 Catch:{ all -> 0x05f5 }
        r1 = r29;	 Catch:{ all -> 0x05f5 }
        if (r0 != r1) goto L_0x0a8f;	 Catch:{ all -> 0x05f5 }
    L_0x0466:
        r0 = r23;	 Catch:{ all -> 0x05f5 }
        r0 = r0.fieldHash;	 Catch:{ all -> 0x05f5 }
        r24 = r0;	 Catch:{ all -> 0x05f5 }
        r6 = r21;	 Catch:{ all -> 0x05f5 }
        r14 = r24;	 Catch:{ all -> 0x05f5 }
        goto L_0x0123;	 Catch:{ all -> 0x05f5 }
    L_0x0472:
        r18 = float[].class;	 Catch:{ all -> 0x05f5 }
        r0 = r19;	 Catch:{ all -> 0x05f5 }
        r1 = r18;	 Catch:{ all -> 0x05f5 }
        if (r0 != r1) goto L_0x04b8;	 Catch:{ all -> 0x05f5 }
    L_0x047a:
        r0 = r23;	 Catch:{ all -> 0x05f5 }
        r1 = r30;	 Catch:{ all -> 0x05f5 }
        r4 = r0.scanFieldFloatArray(r1);	 Catch:{ all -> 0x05f5 }
        r0 = r23;	 Catch:{ all -> 0x05f5 }
        r0 = r0.matchStat;	 Catch:{ all -> 0x05f5 }
        r18 = r0;	 Catch:{ all -> 0x05f5 }
        if (r18 <= 0) goto L_0x049e;	 Catch:{ all -> 0x05f5 }
    L_0x048a:
        r6 = 1;	 Catch:{ all -> 0x05f5 }
        r5 = 1;	 Catch:{ all -> 0x05f5 }
        r18 = r6;	 Catch:{ all -> 0x05f5 }
        r32 = r14;	 Catch:{ all -> 0x05f5 }
        r33 = r16;	 Catch:{ all -> 0x05f5 }
        r16 = r4;	 Catch:{ all -> 0x05f5 }
        r17 = r5;	 Catch:{ all -> 0x05f5 }
        r4 = r12;	 Catch:{ all -> 0x05f5 }
        r12 = r32;	 Catch:{ all -> 0x05f5 }
        r13 = r15;	 Catch:{ all -> 0x05f5 }
        r14 = r33;	 Catch:{ all -> 0x05f5 }
        goto L_0x019a;	 Catch:{ all -> 0x05f5 }
    L_0x049e:
        r0 = r23;	 Catch:{ all -> 0x05f5 }
        r0 = r0.matchStat;	 Catch:{ all -> 0x05f5 }
        r18 = r0;	 Catch:{ all -> 0x05f5 }
        r29 = -2;	 Catch:{ all -> 0x05f5 }
        r0 = r18;	 Catch:{ all -> 0x05f5 }
        r1 = r29;	 Catch:{ all -> 0x05f5 }
        if (r0 != r1) goto L_0x0a7d;	 Catch:{ all -> 0x05f5 }
    L_0x04ac:
        r0 = r23;	 Catch:{ all -> 0x05f5 }
        r0 = r0.fieldHash;	 Catch:{ all -> 0x05f5 }
        r24 = r0;	 Catch:{ all -> 0x05f5 }
        r6 = r21;	 Catch:{ all -> 0x05f5 }
        r14 = r24;	 Catch:{ all -> 0x05f5 }
        goto L_0x0123;	 Catch:{ all -> 0x05f5 }
    L_0x04b8:
        r18 = double[].class;	 Catch:{ all -> 0x05f5 }
        r0 = r19;	 Catch:{ all -> 0x05f5 }
        r1 = r18;	 Catch:{ all -> 0x05f5 }
        if (r0 != r1) goto L_0x04fe;	 Catch:{ all -> 0x05f5 }
    L_0x04c0:
        r0 = r23;	 Catch:{ all -> 0x05f5 }
        r1 = r30;	 Catch:{ all -> 0x05f5 }
        r4 = r0.scanFieldDoubleArray(r1);	 Catch:{ all -> 0x05f5 }
        r0 = r23;	 Catch:{ all -> 0x05f5 }
        r0 = r0.matchStat;	 Catch:{ all -> 0x05f5 }
        r18 = r0;	 Catch:{ all -> 0x05f5 }
        if (r18 <= 0) goto L_0x04e4;	 Catch:{ all -> 0x05f5 }
    L_0x04d0:
        r6 = 1;	 Catch:{ all -> 0x05f5 }
        r5 = 1;	 Catch:{ all -> 0x05f5 }
        r18 = r6;	 Catch:{ all -> 0x05f5 }
        r32 = r14;	 Catch:{ all -> 0x05f5 }
        r33 = r16;	 Catch:{ all -> 0x05f5 }
        r16 = r4;	 Catch:{ all -> 0x05f5 }
        r17 = r5;	 Catch:{ all -> 0x05f5 }
        r4 = r12;	 Catch:{ all -> 0x05f5 }
        r12 = r32;	 Catch:{ all -> 0x05f5 }
        r13 = r15;	 Catch:{ all -> 0x05f5 }
        r14 = r33;	 Catch:{ all -> 0x05f5 }
        goto L_0x019a;	 Catch:{ all -> 0x05f5 }
    L_0x04e4:
        r0 = r23;	 Catch:{ all -> 0x05f5 }
        r0 = r0.matchStat;	 Catch:{ all -> 0x05f5 }
        r18 = r0;	 Catch:{ all -> 0x05f5 }
        r29 = -2;	 Catch:{ all -> 0x05f5 }
        r0 = r18;	 Catch:{ all -> 0x05f5 }
        r1 = r29;	 Catch:{ all -> 0x05f5 }
        if (r0 != r1) goto L_0x0a6b;	 Catch:{ all -> 0x05f5 }
    L_0x04f2:
        r0 = r23;	 Catch:{ all -> 0x05f5 }
        r0 = r0.fieldHash;	 Catch:{ all -> 0x05f5 }
        r24 = r0;	 Catch:{ all -> 0x05f5 }
        r6 = r21;	 Catch:{ all -> 0x05f5 }
        r14 = r24;	 Catch:{ all -> 0x05f5 }
        goto L_0x0123;	 Catch:{ all -> 0x05f5 }
    L_0x04fe:
        r18 = float[][].class;	 Catch:{ all -> 0x05f5 }
        r0 = r19;	 Catch:{ all -> 0x05f5 }
        r1 = r18;	 Catch:{ all -> 0x05f5 }
        if (r0 != r1) goto L_0x0544;	 Catch:{ all -> 0x05f5 }
    L_0x0506:
        r0 = r23;	 Catch:{ all -> 0x05f5 }
        r1 = r30;	 Catch:{ all -> 0x05f5 }
        r4 = r0.scanFieldFloatArray2(r1);	 Catch:{ all -> 0x05f5 }
        r0 = r23;	 Catch:{ all -> 0x05f5 }
        r0 = r0.matchStat;	 Catch:{ all -> 0x05f5 }
        r18 = r0;	 Catch:{ all -> 0x05f5 }
        if (r18 <= 0) goto L_0x052a;	 Catch:{ all -> 0x05f5 }
    L_0x0516:
        r6 = 1;	 Catch:{ all -> 0x05f5 }
        r5 = 1;	 Catch:{ all -> 0x05f5 }
        r18 = r6;	 Catch:{ all -> 0x05f5 }
        r32 = r14;	 Catch:{ all -> 0x05f5 }
        r33 = r16;	 Catch:{ all -> 0x05f5 }
        r16 = r4;	 Catch:{ all -> 0x05f5 }
        r17 = r5;	 Catch:{ all -> 0x05f5 }
        r4 = r12;	 Catch:{ all -> 0x05f5 }
        r12 = r32;	 Catch:{ all -> 0x05f5 }
        r13 = r15;	 Catch:{ all -> 0x05f5 }
        r14 = r33;	 Catch:{ all -> 0x05f5 }
        goto L_0x019a;	 Catch:{ all -> 0x05f5 }
    L_0x052a:
        r0 = r23;	 Catch:{ all -> 0x05f5 }
        r0 = r0.matchStat;	 Catch:{ all -> 0x05f5 }
        r18 = r0;	 Catch:{ all -> 0x05f5 }
        r29 = -2;	 Catch:{ all -> 0x05f5 }
        r0 = r18;	 Catch:{ all -> 0x05f5 }
        r1 = r29;	 Catch:{ all -> 0x05f5 }
        if (r0 != r1) goto L_0x0a59;	 Catch:{ all -> 0x05f5 }
    L_0x0538:
        r0 = r23;	 Catch:{ all -> 0x05f5 }
        r0 = r0.fieldHash;	 Catch:{ all -> 0x05f5 }
        r24 = r0;	 Catch:{ all -> 0x05f5 }
        r6 = r21;	 Catch:{ all -> 0x05f5 }
        r14 = r24;	 Catch:{ all -> 0x05f5 }
        goto L_0x0123;	 Catch:{ all -> 0x05f5 }
    L_0x0544:
        r18 = double[][].class;	 Catch:{ all -> 0x05f5 }
        r0 = r19;	 Catch:{ all -> 0x05f5 }
        r1 = r18;	 Catch:{ all -> 0x05f5 }
        if (r0 != r1) goto L_0x058a;	 Catch:{ all -> 0x05f5 }
    L_0x054c:
        r0 = r23;	 Catch:{ all -> 0x05f5 }
        r1 = r30;	 Catch:{ all -> 0x05f5 }
        r4 = r0.scanFieldDoubleArray2(r1);	 Catch:{ all -> 0x05f5 }
        r0 = r23;	 Catch:{ all -> 0x05f5 }
        r0 = r0.matchStat;	 Catch:{ all -> 0x05f5 }
        r18 = r0;	 Catch:{ all -> 0x05f5 }
        if (r18 <= 0) goto L_0x0570;	 Catch:{ all -> 0x05f5 }
    L_0x055c:
        r6 = 1;	 Catch:{ all -> 0x05f5 }
        r5 = 1;	 Catch:{ all -> 0x05f5 }
        r18 = r6;	 Catch:{ all -> 0x05f5 }
        r32 = r14;	 Catch:{ all -> 0x05f5 }
        r33 = r16;	 Catch:{ all -> 0x05f5 }
        r16 = r4;	 Catch:{ all -> 0x05f5 }
        r17 = r5;	 Catch:{ all -> 0x05f5 }
        r4 = r12;	 Catch:{ all -> 0x05f5 }
        r12 = r32;	 Catch:{ all -> 0x05f5 }
        r13 = r15;	 Catch:{ all -> 0x05f5 }
        r14 = r33;	 Catch:{ all -> 0x05f5 }
        goto L_0x019a;	 Catch:{ all -> 0x05f5 }
    L_0x0570:
        r0 = r23;	 Catch:{ all -> 0x05f5 }
        r0 = r0.matchStat;	 Catch:{ all -> 0x05f5 }
        r18 = r0;	 Catch:{ all -> 0x05f5 }
        r29 = -2;	 Catch:{ all -> 0x05f5 }
        r0 = r18;	 Catch:{ all -> 0x05f5 }
        r1 = r29;	 Catch:{ all -> 0x05f5 }
        if (r0 != r1) goto L_0x0a47;	 Catch:{ all -> 0x05f5 }
    L_0x057e:
        r0 = r23;	 Catch:{ all -> 0x05f5 }
        r0 = r0.fieldHash;	 Catch:{ all -> 0x05f5 }
        r24 = r0;	 Catch:{ all -> 0x05f5 }
        r6 = r21;	 Catch:{ all -> 0x05f5 }
        r14 = r24;	 Catch:{ all -> 0x05f5 }
        goto L_0x0123;	 Catch:{ all -> 0x05f5 }
    L_0x058a:
        r0 = r20;	 Catch:{ all -> 0x05f5 }
        r0 = r0.nameHashCode;	 Catch:{ all -> 0x05f5 }
        r30 = r0;	 Catch:{ all -> 0x05f5 }
        r0 = r23;	 Catch:{ all -> 0x05f5 }
        r1 = r30;	 Catch:{ all -> 0x05f5 }
        r6 = r0.matchField(r1);	 Catch:{ all -> 0x05f5 }
        if (r6 == 0) goto L_0x0a41;	 Catch:{ all -> 0x05f5 }
    L_0x059a:
        r6 = 1;	 Catch:{ all -> 0x05f5 }
        r18 = r6;	 Catch:{ all -> 0x05f5 }
        r32 = r14;	 Catch:{ all -> 0x05f5 }
        r33 = r16;	 Catch:{ all -> 0x05f5 }
        r16 = r4;	 Catch:{ all -> 0x05f5 }
        r17 = r5;	 Catch:{ all -> 0x05f5 }
        r4 = r12;	 Catch:{ all -> 0x05f5 }
        r12 = r32;	 Catch:{ all -> 0x05f5 }
        r13 = r15;	 Catch:{ all -> 0x05f5 }
        r14 = r33;	 Catch:{ all -> 0x05f5 }
        goto L_0x019a;	 Catch:{ all -> 0x05f5 }
    L_0x05ad:
        r29 = 16;	 Catch:{ all -> 0x05f5 }
        r0 = r29;	 Catch:{ all -> 0x05f5 }
        if (r11 != r0) goto L_0x05b9;	 Catch:{ all -> 0x05f5 }
    L_0x05b3:
        r6 = r21;	 Catch:{ all -> 0x05f5 }
        r14 = r24;	 Catch:{ all -> 0x05f5 }
        goto L_0x0123;	 Catch:{ all -> 0x05f5 }
    L_0x05b9:
        r11 = "$ref";	 Catch:{ all -> 0x05f5 }
        if (r11 != r6) goto L_0x068d;	 Catch:{ all -> 0x05f5 }
    L_0x05be:
        if (r10 == 0) goto L_0x068d;	 Catch:{ all -> 0x05f5 }
    L_0x05c0:
        r4 = 58;	 Catch:{ all -> 0x05f5 }
        r0 = r23;	 Catch:{ all -> 0x05f5 }
        r0.nextTokenWithChar(r4);	 Catch:{ all -> 0x05f5 }
        r0 = r23;	 Catch:{ all -> 0x05f5 }
        r4 = r0.token;	 Catch:{ all -> 0x05f5 }
        r5 = 4;	 Catch:{ all -> 0x05f5 }
        if (r4 != r5) goto L_0x0655;	 Catch:{ all -> 0x05f5 }
    L_0x05ce:
        r5 = r23.stringVal();	 Catch:{ all -> 0x05f5 }
        r4 = "@";	 Catch:{ all -> 0x05f5 }
        r4 = r4.equals(r5);	 Catch:{ all -> 0x05f5 }
        if (r4 == 0) goto L_0x05f9;	 Catch:{ all -> 0x05f5 }
    L_0x05db:
        r7 = r10.object;	 Catch:{ all -> 0x05f5 }
    L_0x05dd:
        r4 = 13;	 Catch:{ all -> 0x05f5 }
        r0 = r23;	 Catch:{ all -> 0x05f5 }
        r0.nextToken(r4);	 Catch:{ all -> 0x05f5 }
        r0 = r23;	 Catch:{ all -> 0x05f5 }
        r4 = r0.token;	 Catch:{ all -> 0x05f5 }
        r5 = 13;	 Catch:{ all -> 0x05f5 }
        if (r4 == r5) goto L_0x0673;	 Catch:{ all -> 0x05f5 }
    L_0x05ec:
        r4 = new com.alibaba.fastjson.JSONException;	 Catch:{ all -> 0x05f5 }
        r5 = "illegal ref";	 Catch:{ all -> 0x05f5 }
        r4.<init>(r5);	 Catch:{ all -> 0x05f5 }
        throw r4;	 Catch:{ all -> 0x05f5 }
    L_0x05f5:
        r4 = move-exception;	 Catch:{ all -> 0x05f5 }
        r6 = r8;	 Catch:{ all -> 0x05f5 }
        goto L_0x00f3;	 Catch:{ all -> 0x05f5 }
    L_0x05f9:
        r4 = "..";	 Catch:{ all -> 0x05f5 }
        r4 = r4.equals(r5);	 Catch:{ all -> 0x05f5 }
        if (r4 == 0) goto L_0x061d;	 Catch:{ all -> 0x05f5 }
    L_0x0602:
        r4 = r10.parent;	 Catch:{ all -> 0x05f5 }
        r6 = r4.object;	 Catch:{ all -> 0x05f5 }
        if (r6 == 0) goto L_0x060c;	 Catch:{ all -> 0x05f5 }
    L_0x0608:
        r4 = r4.object;	 Catch:{ all -> 0x05f5 }
    L_0x060a:
        r7 = r4;	 Catch:{ all -> 0x05f5 }
        goto L_0x05dd;	 Catch:{ all -> 0x05f5 }
    L_0x060c:
        r6 = new com.alibaba.fastjson.parser.DefaultJSONParser$ResolveTask;	 Catch:{ all -> 0x05f5 }
        r6.<init>(r4, r5);	 Catch:{ all -> 0x05f5 }
        r0 = r36;	 Catch:{ all -> 0x05f5 }
        r0.addResolveTask(r6);	 Catch:{ all -> 0x05f5 }
        r4 = 1;	 Catch:{ all -> 0x05f5 }
        r0 = r36;	 Catch:{ all -> 0x05f5 }
        r0.resolveStatus = r4;	 Catch:{ all -> 0x05f5 }
        r4 = r7;	 Catch:{ all -> 0x05f5 }
        goto L_0x060a;	 Catch:{ all -> 0x05f5 }
    L_0x061d:
        r4 = "$";	 Catch:{ all -> 0x05f5 }
        r4 = r4.equals(r5);	 Catch:{ all -> 0x05f5 }
        if (r4 == 0) goto L_0x0645;	 Catch:{ all -> 0x05f5 }
    L_0x0626:
        r4 = r10;	 Catch:{ all -> 0x05f5 }
    L_0x0627:
        r6 = r4.parent;	 Catch:{ all -> 0x05f5 }
        if (r6 == 0) goto L_0x062e;	 Catch:{ all -> 0x05f5 }
    L_0x062b:
        r4 = r4.parent;	 Catch:{ all -> 0x05f5 }
        goto L_0x0627;	 Catch:{ all -> 0x05f5 }
    L_0x062e:
        r6 = r4.object;	 Catch:{ all -> 0x05f5 }
        if (r6 == 0) goto L_0x0635;	 Catch:{ all -> 0x05f5 }
    L_0x0632:
        r7 = r4.object;	 Catch:{ all -> 0x05f5 }
        goto L_0x05dd;	 Catch:{ all -> 0x05f5 }
    L_0x0635:
        r6 = new com.alibaba.fastjson.parser.DefaultJSONParser$ResolveTask;	 Catch:{ all -> 0x05f5 }
        r6.<init>(r4, r5);	 Catch:{ all -> 0x05f5 }
        r0 = r36;	 Catch:{ all -> 0x05f5 }
        r0.addResolveTask(r6);	 Catch:{ all -> 0x05f5 }
        r4 = 1;	 Catch:{ all -> 0x05f5 }
        r0 = r36;	 Catch:{ all -> 0x05f5 }
        r0.resolveStatus = r4;	 Catch:{ all -> 0x05f5 }
        goto L_0x05dd;	 Catch:{ all -> 0x05f5 }
    L_0x0645:
        r4 = new com.alibaba.fastjson.parser.DefaultJSONParser$ResolveTask;	 Catch:{ all -> 0x05f5 }
        r4.<init>(r10, r5);	 Catch:{ all -> 0x05f5 }
        r0 = r36;	 Catch:{ all -> 0x05f5 }
        r0.addResolveTask(r4);	 Catch:{ all -> 0x05f5 }
        r4 = 1;	 Catch:{ all -> 0x05f5 }
        r0 = r36;	 Catch:{ all -> 0x05f5 }
        r0.resolveStatus = r4;	 Catch:{ all -> 0x05f5 }
        goto L_0x05dd;	 Catch:{ all -> 0x05f5 }
    L_0x0655:
        r5 = new com.alibaba.fastjson.JSONException;	 Catch:{ all -> 0x05f5 }
        r6 = new java.lang.StringBuilder;	 Catch:{ all -> 0x05f5 }
        r6.<init>();	 Catch:{ all -> 0x05f5 }
        r9 = "illegal ref, ";	 Catch:{ all -> 0x05f5 }
        r6 = r6.append(r9);	 Catch:{ all -> 0x05f5 }
        r4 = com.alibaba.fastjson.parser.JSONToken.name(r4);	 Catch:{ all -> 0x05f5 }
        r4 = r6.append(r4);	 Catch:{ all -> 0x05f5 }
        r4 = r4.toString();	 Catch:{ all -> 0x05f5 }
        r5.<init>(r4);	 Catch:{ all -> 0x05f5 }
        throw r5;	 Catch:{ all -> 0x05f5 }
    L_0x0673:
        r4 = 16;	 Catch:{ all -> 0x05f5 }
        r0 = r23;	 Catch:{ all -> 0x05f5 }
        r0.nextToken(r4);	 Catch:{ all -> 0x05f5 }
        r0 = r36;	 Catch:{ all -> 0x05f5 }
        r1 = r38;	 Catch:{ all -> 0x05f5 }
        r0.setContext(r10, r7, r1);	 Catch:{ all -> 0x05f5 }
        if (r8 == 0) goto L_0x0685;
    L_0x0683:
        r8.object = r7;
    L_0x0685:
        r0 = r36;
        r0.setContext(r10);
        r4 = r7;
        goto L_0x0010;
    L_0x068d:
        if (r27 == 0) goto L_0x0697;
    L_0x068f:
        r0 = r27;	 Catch:{ all -> 0x05f5 }
        r11 = r0.equals(r6);	 Catch:{ all -> 0x05f5 }
        if (r11 != 0) goto L_0x069c;	 Catch:{ all -> 0x05f5 }
    L_0x0697:
        r11 = "@type";	 Catch:{ all -> 0x05f5 }
        if (r11 != r6) goto L_0x0757;	 Catch:{ all -> 0x05f5 }
    L_0x069c:
        r4 = 58;	 Catch:{ all -> 0x05f5 }
        r0 = r23;	 Catch:{ all -> 0x05f5 }
        r0.nextTokenWithChar(r4);	 Catch:{ all -> 0x05f5 }
        r0 = r23;	 Catch:{ all -> 0x05f5 }
        r4 = r0.token;	 Catch:{ all -> 0x05f5 }
        r5 = 4;	 Catch:{ all -> 0x05f5 }
        if (r4 != r5) goto L_0x074d;	 Catch:{ all -> 0x05f5 }
    L_0x06aa:
        r6 = r23.stringVal();	 Catch:{ all -> 0x05f5 }
        r4 = 16;	 Catch:{ all -> 0x05f5 }
        r0 = r23;	 Catch:{ all -> 0x05f5 }
        r0.nextToken(r4);	 Catch:{ all -> 0x05f5 }
        r0 = r37;	 Catch:{ all -> 0x05f5 }
        r4 = r0 instanceof java.lang.Class;	 Catch:{ all -> 0x05f5 }
        if (r4 == 0) goto L_0x06d9;	 Catch:{ all -> 0x05f5 }
    L_0x06bb:
        r0 = r37;	 Catch:{ all -> 0x05f5 }
        r0 = (java.lang.Class) r0;	 Catch:{ all -> 0x05f5 }
        r4 = r0;	 Catch:{ all -> 0x05f5 }
        r4 = r4.getName();	 Catch:{ all -> 0x05f5 }
        r4 = r6.equals(r4);	 Catch:{ all -> 0x05f5 }
        if (r4 == 0) goto L_0x06d9;	 Catch:{ all -> 0x05f5 }
    L_0x06ca:
        r0 = r23;	 Catch:{ all -> 0x05f5 }
        r4 = r0.token;	 Catch:{ all -> 0x05f5 }
        r5 = 13;	 Catch:{ all -> 0x05f5 }
        if (r4 != r5) goto L_0x0a41;	 Catch:{ all -> 0x05f5 }
    L_0x06d2:
        r23.nextToken();	 Catch:{ all -> 0x05f5 }
        r6 = r8;	 Catch:{ all -> 0x05f5 }
        r4 = r7;	 Catch:{ all -> 0x05f5 }
        goto L_0x01bb;	 Catch:{ all -> 0x05f5 }
    L_0x06d9:
        r0 = r36;	 Catch:{ all -> 0x05f5 }
        r4 = r0.config;	 Catch:{ all -> 0x05f5 }
        r0 = r35;	 Catch:{ all -> 0x05f5 }
        r5 = r0.beanInfo;	 Catch:{ all -> 0x05f5 }
        r0 = r35;	 Catch:{ all -> 0x05f5 }
        r5 = r0.getSeeAlso(r4, r5, r6);	 Catch:{ all -> 0x05f5 }
        r4 = 0;	 Catch:{ all -> 0x05f5 }
        if (r5 != 0) goto L_0x0a3a;	 Catch:{ all -> 0x05f5 }
    L_0x06ea:
        r0 = r36;	 Catch:{ all -> 0x05f5 }
        r4 = r0.config;	 Catch:{ all -> 0x05f5 }
        r0 = r35;	 Catch:{ all -> 0x05f5 }
        r5 = r0.clazz;	 Catch:{ all -> 0x05f5 }
        r0 = r23;	 Catch:{ all -> 0x05f5 }
        r9 = r0.features;	 Catch:{ all -> 0x05f5 }
        r4 = r4.checkAutoType(r6, r5, r9);	 Catch:{ all -> 0x05f5 }
        r5 = com.alibaba.fastjson.util.TypeUtils.getClass(r37);	 Catch:{ all -> 0x05f5 }
        if (r5 == 0) goto L_0x0708;	 Catch:{ all -> 0x05f5 }
    L_0x0700:
        if (r4 == 0) goto L_0x073b;	 Catch:{ all -> 0x05f5 }
    L_0x0702:
        r5 = r5.isAssignableFrom(r4);	 Catch:{ all -> 0x05f5 }
        if (r5 == 0) goto L_0x073b;	 Catch:{ all -> 0x05f5 }
    L_0x0708:
        r0 = r36;	 Catch:{ all -> 0x05f5 }
        r5 = r0.config;	 Catch:{ all -> 0x05f5 }
        r5 = r5.getDeserializer(r4);	 Catch:{ all -> 0x05f5 }
        r32 = r4;	 Catch:{ all -> 0x05f5 }
        r4 = r5;	 Catch:{ all -> 0x05f5 }
        r5 = r32;	 Catch:{ all -> 0x05f5 }
    L_0x0715:
        r9 = r4 instanceof com.alibaba.fastjson.parser.JavaBeanDeserializer;	 Catch:{ all -> 0x05f5 }
        if (r9 == 0) goto L_0x0744;	 Catch:{ all -> 0x05f5 }
    L_0x0719:
        r4 = (com.alibaba.fastjson.parser.JavaBeanDeserializer) r4;	 Catch:{ all -> 0x05f5 }
        r9 = 0;	 Catch:{ all -> 0x05f5 }
        r0 = r36;	 Catch:{ all -> 0x05f5 }
        r1 = r38;	 Catch:{ all -> 0x05f5 }
        r5 = r4.deserialze(r0, r5, r1, r9);	 Catch:{ all -> 0x05f5 }
        if (r27 == 0) goto L_0x072f;	 Catch:{ all -> 0x05f5 }
    L_0x0726:
        r0 = r27;	 Catch:{ all -> 0x05f5 }
        r4 = r4.getFieldDeserializer(r0);	 Catch:{ all -> 0x05f5 }
        r4.setValue(r5, r6);	 Catch:{ all -> 0x05f5 }
    L_0x072f:
        r4 = r5;
    L_0x0730:
        if (r8 == 0) goto L_0x0734;
    L_0x0732:
        r8.object = r7;
    L_0x0734:
        r0 = r36;
        r0.setContext(r10);
        goto L_0x0010;
    L_0x073b:
        r4 = new com.alibaba.fastjson.JSONException;	 Catch:{ all -> 0x05f5 }
        r5 = "type not match";	 Catch:{ all -> 0x05f5 }
        r4.<init>(r5);	 Catch:{ all -> 0x05f5 }
        throw r4;	 Catch:{ all -> 0x05f5 }
    L_0x0744:
        r0 = r36;	 Catch:{ all -> 0x05f5 }
        r1 = r38;	 Catch:{ all -> 0x05f5 }
        r4 = r4.deserialze(r0, r5, r1);	 Catch:{ all -> 0x05f5 }
        goto L_0x0730;	 Catch:{ all -> 0x05f5 }
    L_0x074d:
        r4 = new com.alibaba.fastjson.JSONException;	 Catch:{ all -> 0x05f5 }
        r5 = "syntax error";	 Catch:{ all -> 0x05f5 }
        r4.<init>(r5);	 Catch:{ all -> 0x05f5 }
        throw r4;	 Catch:{ all -> 0x05f5 }
    L_0x0756:
        r6 = r11;	 Catch:{ all -> 0x05f5 }
    L_0x0757:
        if (r7 != 0) goto L_0x0a37;	 Catch:{ all -> 0x05f5 }
    L_0x0759:
        if (r9 != 0) goto L_0x0a37;	 Catch:{ all -> 0x05f5 }
    L_0x075b:
        r7 = r35.createInstance(r36, r37);	 Catch:{ all -> 0x05f5 }
        if (r7 != 0) goto L_0x076b;	 Catch:{ all -> 0x05f5 }
    L_0x0761:
        r9 = new java.util.HashMap;	 Catch:{ all -> 0x05f5 }
        r0 = r35;	 Catch:{ all -> 0x05f5 }
        r11 = r0.fieldDeserializers;	 Catch:{ all -> 0x05f5 }
        r11 = r11.length;	 Catch:{ all -> 0x05f5 }
        r9.<init>(r11);	 Catch:{ all -> 0x05f5 }
    L_0x076b:
        if (r26 != 0) goto L_0x0a37;	 Catch:{ all -> 0x05f5 }
    L_0x076d:
        r0 = r36;	 Catch:{ all -> 0x05f5 }
        r1 = r38;	 Catch:{ all -> 0x05f5 }
        r8 = r0.setContext(r10, r7, r1);	 Catch:{ all -> 0x05f5 }
        r11 = r8;
    L_0x0776:
        if (r18 == 0) goto L_0x08d1;
    L_0x0778:
        if (r17 != 0) goto L_0x0792;
    L_0x077a:
        r0 = r22;	 Catch:{ all -> 0x07dd }
        r1 = r36;	 Catch:{ all -> 0x07dd }
        r2 = r37;	 Catch:{ all -> 0x07dd }
        r0.parseField(r1, r7, r2, r9);	 Catch:{ all -> 0x07dd }
    L_0x0783:
        r0 = r23;	 Catch:{ all -> 0x07dd }
        r4 = r0.token;	 Catch:{ all -> 0x07dd }
        r5 = 16;	 Catch:{ all -> 0x07dd }
        if (r4 != r5) goto L_0x08fd;	 Catch:{ all -> 0x07dd }
    L_0x078b:
        r6 = r21;	 Catch:{ all -> 0x07dd }
        r14 = r24;	 Catch:{ all -> 0x07dd }
        r8 = r11;	 Catch:{ all -> 0x07dd }
        goto L_0x0123;	 Catch:{ all -> 0x07dd }
    L_0x0792:
        if (r7 != 0) goto L_0x07f5;	 Catch:{ all -> 0x07dd }
    L_0x0794:
        r6 = java.lang.Integer.TYPE;	 Catch:{ all -> 0x07dd }
        r0 = r19;	 Catch:{ all -> 0x07dd }
        if (r0 == r6) goto L_0x07a0;	 Catch:{ all -> 0x07dd }
    L_0x079a:
        r6 = java.lang.Integer.class;	 Catch:{ all -> 0x07dd }
        r0 = r19;	 Catch:{ all -> 0x07dd }
        if (r0 != r6) goto L_0x07b8;	 Catch:{ all -> 0x07dd }
    L_0x07a0:
        r16 = java.lang.Integer.valueOf(r13);	 Catch:{ all -> 0x07dd }
    L_0x07a4:
        r0 = r20;	 Catch:{ all -> 0x07dd }
        r4 = r0.name;	 Catch:{ all -> 0x07dd }
        r0 = r16;	 Catch:{ all -> 0x07dd }
        r9.put(r4, r0);	 Catch:{ all -> 0x07dd }
    L_0x07ad:
        r0 = r23;	 Catch:{ all -> 0x07dd }
        r4 = r0.matchStat;	 Catch:{ all -> 0x07dd }
        r5 = 4;	 Catch:{ all -> 0x07dd }
        if (r4 != r5) goto L_0x0783;	 Catch:{ all -> 0x07dd }
    L_0x07b4:
        r6 = r11;	 Catch:{ all -> 0x07dd }
        r4 = r7;	 Catch:{ all -> 0x07dd }
        goto L_0x01bb;	 Catch:{ all -> 0x07dd }
    L_0x07b8:
        r6 = java.lang.Long.TYPE;	 Catch:{ all -> 0x07dd }
        r0 = r19;	 Catch:{ all -> 0x07dd }
        if (r0 == r6) goto L_0x07c4;	 Catch:{ all -> 0x07dd }
    L_0x07be:
        r6 = java.lang.Long.class;	 Catch:{ all -> 0x07dd }
        r0 = r19;	 Catch:{ all -> 0x07dd }
        if (r0 != r6) goto L_0x07c9;	 Catch:{ all -> 0x07dd }
    L_0x07c4:
        r16 = java.lang.Long.valueOf(r14);	 Catch:{ all -> 0x07dd }
        goto L_0x07a4;	 Catch:{ all -> 0x07dd }
    L_0x07c9:
        r6 = java.lang.Float.TYPE;	 Catch:{ all -> 0x07dd }
        r0 = r19;	 Catch:{ all -> 0x07dd }
        if (r0 == r6) goto L_0x07d5;	 Catch:{ all -> 0x07dd }
    L_0x07cf:
        r6 = java.lang.Float.class;	 Catch:{ all -> 0x07dd }
        r0 = r19;	 Catch:{ all -> 0x07dd }
        if (r0 != r6) goto L_0x07e1;	 Catch:{ all -> 0x07dd }
    L_0x07d5:
        r16 = new java.lang.Float;	 Catch:{ all -> 0x07dd }
        r0 = r16;	 Catch:{ all -> 0x07dd }
        r0.<init>(r12);	 Catch:{ all -> 0x07dd }
        goto L_0x07a4;	 Catch:{ all -> 0x07dd }
    L_0x07dd:
        r4 = move-exception;	 Catch:{ all -> 0x07dd }
        r6 = r11;	 Catch:{ all -> 0x07dd }
        goto L_0x00f3;	 Catch:{ all -> 0x07dd }
    L_0x07e1:
        r6 = java.lang.Double.TYPE;	 Catch:{ all -> 0x07dd }
        r0 = r19;	 Catch:{ all -> 0x07dd }
        if (r0 == r6) goto L_0x07ed;	 Catch:{ all -> 0x07dd }
    L_0x07e7:
        r6 = java.lang.Double.class;	 Catch:{ all -> 0x07dd }
        r0 = r19;	 Catch:{ all -> 0x07dd }
        if (r0 != r6) goto L_0x07a4;	 Catch:{ all -> 0x07dd }
    L_0x07ed:
        r16 = new java.lang.Double;	 Catch:{ all -> 0x07dd }
        r0 = r16;	 Catch:{ all -> 0x07dd }
        r0.<init>(r4);	 Catch:{ all -> 0x07dd }
        goto L_0x07a4;
    L_0x07f5:
        if (r16 != 0) goto L_0x08c8;
    L_0x07f7:
        r6 = java.lang.Integer.TYPE;	 Catch:{ IllegalAccessException -> 0x0815 }
        r0 = r19;	 Catch:{ IllegalAccessException -> 0x0815 }
        if (r0 == r6) goto L_0x0803;	 Catch:{ IllegalAccessException -> 0x0815 }
    L_0x07fd:
        r6 = java.lang.Integer.class;	 Catch:{ IllegalAccessException -> 0x0815 }
        r0 = r19;	 Catch:{ IllegalAccessException -> 0x0815 }
        if (r0 != r6) goto L_0x083f;	 Catch:{ IllegalAccessException -> 0x0815 }
    L_0x0803:
        r0 = r20;	 Catch:{ IllegalAccessException -> 0x0815 }
        r4 = r0.fieldAccess;	 Catch:{ IllegalAccessException -> 0x0815 }
        if (r4 == 0) goto L_0x0834;	 Catch:{ IllegalAccessException -> 0x0815 }
    L_0x0809:
        r4 = java.lang.Integer.TYPE;	 Catch:{ IllegalAccessException -> 0x0815 }
        r0 = r19;	 Catch:{ IllegalAccessException -> 0x0815 }
        if (r0 != r4) goto L_0x0834;	 Catch:{ IllegalAccessException -> 0x0815 }
    L_0x080f:
        r0 = r22;	 Catch:{ IllegalAccessException -> 0x0815 }
        r0.setValue(r7, r13);	 Catch:{ IllegalAccessException -> 0x0815 }
        goto L_0x07ad;
    L_0x0815:
        r4 = move-exception;
        r5 = new com.alibaba.fastjson.JSONException;	 Catch:{ all -> 0x07dd }
        r6 = new java.lang.StringBuilder;	 Catch:{ all -> 0x07dd }
        r6.<init>();	 Catch:{ all -> 0x07dd }
        r8 = "set property error, ";	 Catch:{ all -> 0x07dd }
        r6 = r6.append(r8);	 Catch:{ all -> 0x07dd }
        r0 = r20;	 Catch:{ all -> 0x07dd }
        r8 = r0.name;	 Catch:{ all -> 0x07dd }
        r6 = r6.append(r8);	 Catch:{ all -> 0x07dd }
        r6 = r6.toString();	 Catch:{ all -> 0x07dd }
        r5.<init>(r6, r4);	 Catch:{ all -> 0x07dd }
        throw r5;	 Catch:{ all -> 0x07dd }
    L_0x0834:
        r4 = java.lang.Integer.valueOf(r13);	 Catch:{ IllegalAccessException -> 0x0815 }
        r0 = r22;	 Catch:{ IllegalAccessException -> 0x0815 }
        r0.setValue(r7, r4);	 Catch:{ IllegalAccessException -> 0x0815 }
        goto L_0x07ad;	 Catch:{ IllegalAccessException -> 0x0815 }
    L_0x083f:
        r6 = java.lang.Long.TYPE;	 Catch:{ IllegalAccessException -> 0x0815 }
        r0 = r19;	 Catch:{ IllegalAccessException -> 0x0815 }
        if (r0 == r6) goto L_0x084b;	 Catch:{ IllegalAccessException -> 0x0815 }
    L_0x0845:
        r6 = java.lang.Long.class;	 Catch:{ IllegalAccessException -> 0x0815 }
        r0 = r19;	 Catch:{ IllegalAccessException -> 0x0815 }
        if (r0 != r6) goto L_0x0869;	 Catch:{ IllegalAccessException -> 0x0815 }
    L_0x084b:
        r0 = r20;	 Catch:{ IllegalAccessException -> 0x0815 }
        r4 = r0.fieldAccess;	 Catch:{ IllegalAccessException -> 0x0815 }
        if (r4 == 0) goto L_0x085e;	 Catch:{ IllegalAccessException -> 0x0815 }
    L_0x0851:
        r4 = java.lang.Long.TYPE;	 Catch:{ IllegalAccessException -> 0x0815 }
        r0 = r19;	 Catch:{ IllegalAccessException -> 0x0815 }
        if (r0 != r4) goto L_0x085e;	 Catch:{ IllegalAccessException -> 0x0815 }
    L_0x0857:
        r0 = r22;	 Catch:{ IllegalAccessException -> 0x0815 }
        r0.setValue(r7, r14);	 Catch:{ IllegalAccessException -> 0x0815 }
        goto L_0x07ad;	 Catch:{ IllegalAccessException -> 0x0815 }
    L_0x085e:
        r4 = java.lang.Long.valueOf(r14);	 Catch:{ IllegalAccessException -> 0x0815 }
        r0 = r22;	 Catch:{ IllegalAccessException -> 0x0815 }
        r0.setValue(r7, r4);	 Catch:{ IllegalAccessException -> 0x0815 }
        goto L_0x07ad;	 Catch:{ IllegalAccessException -> 0x0815 }
    L_0x0869:
        r6 = java.lang.Float.TYPE;	 Catch:{ IllegalAccessException -> 0x0815 }
        r0 = r19;	 Catch:{ IllegalAccessException -> 0x0815 }
        if (r0 == r6) goto L_0x0875;	 Catch:{ IllegalAccessException -> 0x0815 }
    L_0x086f:
        r6 = java.lang.Float.class;	 Catch:{ IllegalAccessException -> 0x0815 }
        r0 = r19;	 Catch:{ IllegalAccessException -> 0x0815 }
        if (r0 != r6) goto L_0x0894;	 Catch:{ IllegalAccessException -> 0x0815 }
    L_0x0875:
        r0 = r20;	 Catch:{ IllegalAccessException -> 0x0815 }
        r4 = r0.fieldAccess;	 Catch:{ IllegalAccessException -> 0x0815 }
        if (r4 == 0) goto L_0x0888;	 Catch:{ IllegalAccessException -> 0x0815 }
    L_0x087b:
        r4 = java.lang.Float.TYPE;	 Catch:{ IllegalAccessException -> 0x0815 }
        r0 = r19;	 Catch:{ IllegalAccessException -> 0x0815 }
        if (r0 != r4) goto L_0x0888;	 Catch:{ IllegalAccessException -> 0x0815 }
    L_0x0881:
        r0 = r22;	 Catch:{ IllegalAccessException -> 0x0815 }
        r0.setValue(r7, r12);	 Catch:{ IllegalAccessException -> 0x0815 }
        goto L_0x07ad;	 Catch:{ IllegalAccessException -> 0x0815 }
    L_0x0888:
        r4 = new java.lang.Float;	 Catch:{ IllegalAccessException -> 0x0815 }
        r4.<init>(r12);	 Catch:{ IllegalAccessException -> 0x0815 }
        r0 = r22;	 Catch:{ IllegalAccessException -> 0x0815 }
        r0.setValue(r7, r4);	 Catch:{ IllegalAccessException -> 0x0815 }
        goto L_0x07ad;	 Catch:{ IllegalAccessException -> 0x0815 }
    L_0x0894:
        r6 = java.lang.Double.TYPE;	 Catch:{ IllegalAccessException -> 0x0815 }
        r0 = r19;	 Catch:{ IllegalAccessException -> 0x0815 }
        if (r0 == r6) goto L_0x08a0;	 Catch:{ IllegalAccessException -> 0x0815 }
    L_0x089a:
        r6 = java.lang.Double.class;	 Catch:{ IllegalAccessException -> 0x0815 }
        r0 = r19;	 Catch:{ IllegalAccessException -> 0x0815 }
        if (r0 != r6) goto L_0x08bf;	 Catch:{ IllegalAccessException -> 0x0815 }
    L_0x08a0:
        r0 = r20;	 Catch:{ IllegalAccessException -> 0x0815 }
        r6 = r0.fieldAccess;	 Catch:{ IllegalAccessException -> 0x0815 }
        if (r6 == 0) goto L_0x08b3;	 Catch:{ IllegalAccessException -> 0x0815 }
    L_0x08a6:
        r6 = java.lang.Double.TYPE;	 Catch:{ IllegalAccessException -> 0x0815 }
        r0 = r19;	 Catch:{ IllegalAccessException -> 0x0815 }
        if (r0 != r6) goto L_0x08b3;	 Catch:{ IllegalAccessException -> 0x0815 }
    L_0x08ac:
        r0 = r22;	 Catch:{ IllegalAccessException -> 0x0815 }
        r0.setValue(r7, r4);	 Catch:{ IllegalAccessException -> 0x0815 }
        goto L_0x07ad;	 Catch:{ IllegalAccessException -> 0x0815 }
    L_0x08b3:
        r6 = new java.lang.Double;	 Catch:{ IllegalAccessException -> 0x0815 }
        r6.<init>(r4);	 Catch:{ IllegalAccessException -> 0x0815 }
        r0 = r22;	 Catch:{ IllegalAccessException -> 0x0815 }
        r0.setValue(r7, r6);	 Catch:{ IllegalAccessException -> 0x0815 }
        goto L_0x07ad;	 Catch:{ IllegalAccessException -> 0x0815 }
    L_0x08bf:
        r0 = r22;	 Catch:{ IllegalAccessException -> 0x0815 }
        r1 = r16;	 Catch:{ IllegalAccessException -> 0x0815 }
        r0.setValue(r7, r1);	 Catch:{ IllegalAccessException -> 0x0815 }
        goto L_0x07ad;
    L_0x08c8:
        r0 = r22;	 Catch:{ all -> 0x07dd }
        r1 = r16;	 Catch:{ all -> 0x07dd }
        r0.setValue(r7, r1);	 Catch:{ all -> 0x07dd }
        goto L_0x07ad;	 Catch:{ all -> 0x07dd }
    L_0x08d1:
        r4 = r35;	 Catch:{ all -> 0x07dd }
        r5 = r36;	 Catch:{ all -> 0x07dd }
        r8 = r37;	 Catch:{ all -> 0x07dd }
        r4 = r4.parseField(r5, r6, r7, r8, r9);	 Catch:{ all -> 0x07dd }
        if (r4 != 0) goto L_0x08ec;	 Catch:{ all -> 0x07dd }
    L_0x08dd:
        r0 = r23;	 Catch:{ all -> 0x07dd }
        r4 = r0.token;	 Catch:{ all -> 0x07dd }
        r5 = 13;	 Catch:{ all -> 0x07dd }
        if (r4 != r5) goto L_0x0af7;	 Catch:{ all -> 0x07dd }
    L_0x08e5:
        r23.nextToken();	 Catch:{ all -> 0x07dd }
        r6 = r11;	 Catch:{ all -> 0x07dd }
        r4 = r7;	 Catch:{ all -> 0x07dd }
        goto L_0x01bb;	 Catch:{ all -> 0x07dd }
    L_0x08ec:
        r0 = r23;	 Catch:{ all -> 0x07dd }
        r4 = r0.token;	 Catch:{ all -> 0x07dd }
        r5 = 17;	 Catch:{ all -> 0x07dd }
        if (r4 != r5) goto L_0x0783;	 Catch:{ all -> 0x07dd }
    L_0x08f4:
        r4 = new com.alibaba.fastjson.JSONException;	 Catch:{ all -> 0x07dd }
        r5 = "syntax error, unexpect token ':'";	 Catch:{ all -> 0x07dd }
        r4.<init>(r5);	 Catch:{ all -> 0x07dd }
        throw r4;	 Catch:{ all -> 0x07dd }
    L_0x08fd:
        r0 = r23;	 Catch:{ all -> 0x07dd }
        r4 = r0.token;	 Catch:{ all -> 0x07dd }
        r5 = 13;	 Catch:{ all -> 0x07dd }
        if (r4 != r5) goto L_0x0910;	 Catch:{ all -> 0x07dd }
    L_0x0905:
        r4 = 16;	 Catch:{ all -> 0x07dd }
        r0 = r23;	 Catch:{ all -> 0x07dd }
        r0.nextToken(r4);	 Catch:{ all -> 0x07dd }
        r6 = r11;	 Catch:{ all -> 0x07dd }
        r4 = r7;	 Catch:{ all -> 0x07dd }
        goto L_0x01bb;	 Catch:{ all -> 0x07dd }
    L_0x0910:
        r0 = r23;	 Catch:{ all -> 0x07dd }
        r4 = r0.token;	 Catch:{ all -> 0x07dd }
        r5 = 18;	 Catch:{ all -> 0x07dd }
        if (r4 == r5) goto L_0x091f;	 Catch:{ all -> 0x07dd }
    L_0x0918:
        r0 = r23;	 Catch:{ all -> 0x07dd }
        r4 = r0.token;	 Catch:{ all -> 0x07dd }
        r5 = 1;	 Catch:{ all -> 0x07dd }
        if (r4 != r5) goto L_0x0941;	 Catch:{ all -> 0x07dd }
    L_0x091f:
        r4 = new com.alibaba.fastjson.JSONException;	 Catch:{ all -> 0x07dd }
        r5 = new java.lang.StringBuilder;	 Catch:{ all -> 0x07dd }
        r5.<init>();	 Catch:{ all -> 0x07dd }
        r6 = "syntax error, unexpect token ";	 Catch:{ all -> 0x07dd }
        r5 = r5.append(r6);	 Catch:{ all -> 0x07dd }
        r0 = r23;	 Catch:{ all -> 0x07dd }
        r6 = r0.token;	 Catch:{ all -> 0x07dd }
        r6 = com.alibaba.fastjson.parser.JSONToken.name(r6);	 Catch:{ all -> 0x07dd }
        r5 = r5.append(r6);	 Catch:{ all -> 0x07dd }
        r5 = r5.toString();	 Catch:{ all -> 0x07dd }
        r4.<init>(r5);	 Catch:{ all -> 0x07dd }
        throw r4;	 Catch:{ all -> 0x07dd }
    L_0x0941:
        r6 = r21;
        r14 = r24;
        r8 = r11;
        goto L_0x0123;
    L_0x0948:
        r0 = r35;	 Catch:{ all -> 0x09e8 }
        r5 = r0.beanInfo;	 Catch:{ all -> 0x09e8 }
        r11 = r5.creatorConstructorParameters;	 Catch:{ all -> 0x09e8 }
        if (r11 == 0) goto L_0x0974;	 Catch:{ all -> 0x09e8 }
    L_0x0950:
        r5 = r11.length;	 Catch:{ all -> 0x09e8 }
        r8 = r5;	 Catch:{ all -> 0x09e8 }
    L_0x0952:
        r12 = new java.lang.Object[r8];	 Catch:{ all -> 0x09e8 }
        r5 = 0;	 Catch:{ all -> 0x09e8 }
    L_0x0955:
        if (r5 >= r8) goto L_0x0982;	 Catch:{ all -> 0x09e8 }
    L_0x0957:
        r0 = r35;	 Catch:{ all -> 0x09e8 }
        r7 = r0.fieldDeserializers;	 Catch:{ all -> 0x09e8 }
        r7 = r7[r5];	 Catch:{ all -> 0x09e8 }
        r13 = r7.fieldInfo;	 Catch:{ all -> 0x09e8 }
        if (r11 == 0) goto L_0x097b;	 Catch:{ all -> 0x09e8 }
    L_0x0961:
        r7 = r13.name;	 Catch:{ all -> 0x09e8 }
        r7 = r9.remove(r7);	 Catch:{ all -> 0x09e8 }
    L_0x0967:
        if (r7 != 0) goto L_0x096f;	 Catch:{ all -> 0x09e8 }
    L_0x0969:
        r7 = r13.fieldClass;	 Catch:{ all -> 0x09e8 }
        r7 = com.alibaba.fastjson.util.TypeUtils.defaultValue(r7);	 Catch:{ all -> 0x09e8 }
    L_0x096f:
        r12[r5] = r7;	 Catch:{ all -> 0x09e8 }
        r5 = r5 + 1;	 Catch:{ all -> 0x09e8 }
        goto L_0x0955;	 Catch:{ all -> 0x09e8 }
    L_0x0974:
        r0 = r35;	 Catch:{ all -> 0x09e8 }
        r5 = r0.fieldDeserializers;	 Catch:{ all -> 0x09e8 }
        r5 = r5.length;	 Catch:{ all -> 0x09e8 }
        r8 = r5;	 Catch:{ all -> 0x09e8 }
        goto L_0x0952;	 Catch:{ all -> 0x09e8 }
    L_0x097b:
        r7 = r13.name;	 Catch:{ all -> 0x09e8 }
        r7 = r9.get(r7);	 Catch:{ all -> 0x09e8 }
        goto L_0x0967;	 Catch:{ all -> 0x09e8 }
    L_0x0982:
        r0 = r35;	 Catch:{ all -> 0x09e8 }
        r5 = r0.beanInfo;	 Catch:{ all -> 0x09e8 }
        r5 = r5.creatorConstructor;	 Catch:{ all -> 0x09e8 }
        if (r5 == 0) goto L_0x09f9;
    L_0x098a:
        r0 = r35;	 Catch:{ Exception -> 0x09c3 }
        r5 = r0.beanInfo;	 Catch:{ Exception -> 0x09c3 }
        r5 = r5.creatorConstructor;	 Catch:{ Exception -> 0x09c3 }
        r7 = r5.newInstance(r12);	 Catch:{ Exception -> 0x09c3 }
        if (r11 == 0) goto L_0x0a32;
    L_0x0996:
        r4 = r9.entrySet();	 Catch:{ all -> 0x09c0 }
        r8 = r4.iterator();	 Catch:{ all -> 0x09c0 }
    L_0x099e:
        r4 = r8.hasNext();	 Catch:{ all -> 0x09c0 }
        if (r4 == 0) goto L_0x09ed;	 Catch:{ all -> 0x09c0 }
    L_0x09a4:
        r4 = r8.next();	 Catch:{ all -> 0x09c0 }
        r4 = (java.util.Map.Entry) r4;	 Catch:{ all -> 0x09c0 }
        r5 = r4.getKey();	 Catch:{ all -> 0x09c0 }
        r5 = (java.lang.String) r5;	 Catch:{ all -> 0x09c0 }
        r0 = r35;	 Catch:{ all -> 0x09c0 }
        r5 = r0.getFieldDeserializer(r5);	 Catch:{ all -> 0x09c0 }
        if (r5 == 0) goto L_0x099e;	 Catch:{ all -> 0x09c0 }
    L_0x09b8:
        r4 = r4.getValue();	 Catch:{ all -> 0x09c0 }
        r5.setValue(r7, r4);	 Catch:{ all -> 0x09c0 }
        goto L_0x099e;
    L_0x09c0:
        r4 = move-exception;
        goto L_0x00f3;
    L_0x09c3:
        r5 = move-exception;
        r7 = new com.alibaba.fastjson.JSONException;	 Catch:{ all -> 0x09e8 }
        r8 = new java.lang.StringBuilder;	 Catch:{ all -> 0x09e8 }
        r8.<init>();	 Catch:{ all -> 0x09e8 }
        r9 = "create instance error, ";	 Catch:{ all -> 0x09e8 }
        r8 = r8.append(r9);	 Catch:{ all -> 0x09e8 }
        r0 = r35;	 Catch:{ all -> 0x09e8 }
        r9 = r0.beanInfo;	 Catch:{ all -> 0x09e8 }
        r9 = r9.creatorConstructor;	 Catch:{ all -> 0x09e8 }
        r9 = r9.toGenericString();	 Catch:{ all -> 0x09e8 }
        r8 = r8.append(r9);	 Catch:{ all -> 0x09e8 }
        r8 = r8.toString();	 Catch:{ all -> 0x09e8 }
        r7.<init>(r8, r5);	 Catch:{ all -> 0x09e8 }
        throw r7;	 Catch:{ all -> 0x09e8 }
    L_0x09e8:
        r5 = move-exception;
        r7 = r4;
        r4 = r5;
        goto L_0x00f3;
    L_0x09ed:
        r4 = r7;
    L_0x09ee:
        if (r6 == 0) goto L_0x09f2;
    L_0x09f0:
        r6.object = r4;
    L_0x09f2:
        r0 = r36;
        r0.setContext(r10);
        goto L_0x0010;
    L_0x09f9:
        r0 = r35;	 Catch:{ all -> 0x09e8 }
        r5 = r0.beanInfo;	 Catch:{ all -> 0x09e8 }
        r5 = r5.factoryMethod;	 Catch:{ all -> 0x09e8 }
        if (r5 == 0) goto L_0x09ee;
    L_0x0a01:
        r0 = r35;	 Catch:{ Exception -> 0x0a0d }
        r5 = r0.beanInfo;	 Catch:{ Exception -> 0x0a0d }
        r5 = r5.factoryMethod;	 Catch:{ Exception -> 0x0a0d }
        r7 = 0;	 Catch:{ Exception -> 0x0a0d }
        r4 = r5.invoke(r7, r12);	 Catch:{ Exception -> 0x0a0d }
        goto L_0x09ee;
    L_0x0a0d:
        r5 = move-exception;
        r7 = new com.alibaba.fastjson.JSONException;	 Catch:{ all -> 0x09e8 }
        r8 = new java.lang.StringBuilder;	 Catch:{ all -> 0x09e8 }
        r8.<init>();	 Catch:{ all -> 0x09e8 }
        r9 = "create factory method error, ";	 Catch:{ all -> 0x09e8 }
        r8 = r8.append(r9);	 Catch:{ all -> 0x09e8 }
        r0 = r35;	 Catch:{ all -> 0x09e8 }
        r9 = r0.beanInfo;	 Catch:{ all -> 0x09e8 }
        r9 = r9.factoryMethod;	 Catch:{ all -> 0x09e8 }
        r9 = r9.toString();	 Catch:{ all -> 0x09e8 }
        r8 = r8.append(r9);	 Catch:{ all -> 0x09e8 }
        r8 = r8.toString();	 Catch:{ all -> 0x09e8 }
        r7.<init>(r8, r5);	 Catch:{ all -> 0x09e8 }
        throw r7;	 Catch:{ all -> 0x09e8 }
    L_0x0a32:
        r4 = r7;
        goto L_0x09ee;
    L_0x0a34:
        r4 = r6;
        goto L_0x01cf;
    L_0x0a37:
        r11 = r8;
        goto L_0x0776;
    L_0x0a3a:
        r32 = r4;
        r4 = r5;
        r5 = r32;
        goto L_0x0715;
    L_0x0a41:
        r6 = r21;
        r14 = r24;
        goto L_0x0123;
    L_0x0a47:
        r18 = r6;
        r32 = r14;
        r33 = r16;
        r16 = r4;
        r17 = r5;
        r4 = r12;
        r12 = r32;
        r13 = r15;
        r14 = r33;
        goto L_0x019a;
    L_0x0a59:
        r18 = r6;
        r32 = r14;
        r33 = r16;
        r16 = r4;
        r17 = r5;
        r4 = r12;
        r12 = r32;
        r13 = r15;
        r14 = r33;
        goto L_0x019a;
    L_0x0a6b:
        r18 = r6;
        r32 = r14;
        r33 = r16;
        r16 = r4;
        r17 = r5;
        r4 = r12;
        r12 = r32;
        r13 = r15;
        r14 = r33;
        goto L_0x019a;
    L_0x0a7d:
        r18 = r6;
        r32 = r14;
        r33 = r16;
        r16 = r4;
        r17 = r5;
        r4 = r12;
        r12 = r32;
        r13 = r15;
        r14 = r33;
        goto L_0x019a;
    L_0x0a8f:
        r18 = r6;
        r32 = r14;
        r33 = r16;
        r16 = r4;
        r17 = r5;
        r4 = r12;
        r12 = r32;
        r13 = r15;
        r14 = r33;
        goto L_0x019a;
    L_0x0aa1:
        r18 = r6;
        r32 = r14;
        r33 = r16;
        r16 = r4;
        r17 = r5;
        r4 = r12;
        r12 = r32;
        r13 = r15;
        r14 = r33;
        goto L_0x019a;
    L_0x0ab3:
        r18 = r6;
        r32 = r14;
        r33 = r16;
        r16 = r4;
        r17 = r5;
        r4 = r12;
        r12 = r32;
        r13 = r15;
        r14 = r33;
        goto L_0x019a;
    L_0x0ac5:
        r18 = r6;
        r32 = r14;
        r33 = r16;
        r16 = r4;
        r17 = r5;
        r4 = r12;
        r12 = r32;
        r13 = r15;
        r14 = r33;
        goto L_0x019a;
    L_0x0ad7:
        r18 = r6;
        r32 = r14;
        r33 = r16;
        r16 = r4;
        r17 = r5;
        r4 = r12;
        r12 = r32;
        r13 = r15;
        r14 = r33;
        goto L_0x019a;
    L_0x0ae9:
        r19 = r4;
        r20 = r5;
        r21 = r6;
        r22 = r12;
        goto L_0x0157;
    L_0x0af3:
        r24 = r14;
        goto L_0x013d;
    L_0x0af7:
        r6 = r21;
        r14 = r24;
        r8 = r11;
        goto L_0x0123;
    L_0x0afe:
        r10 = r4;
        goto L_0x0039;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.parser.JavaBeanDeserializer.deserialze(com.alibaba.fastjson.parser.DefaultJSONParser, java.lang.reflect.Type, java.lang.Object, java.lang.Object):T");
    }

    protected FieldDeserializer getFieldDeserializerByHash(long j) {
        for (FieldDeserializer fieldDeserializer : this.sortedFieldDeserializers) {
            if (fieldDeserializer.fieldInfo.nameHashCode == j) {
                return fieldDeserializer;
            }
        }
        return null;
    }

    protected FieldDeserializer getFieldDeserializer(String str) {
        int i = 0;
        if (str == null) {
            return null;
        }
        if (this.beanInfo.ordered) {
            while (i < this.sortedFieldDeserializers.length) {
                FieldDeserializer fieldDeserializer = this.sortedFieldDeserializers[i];
                if (fieldDeserializer.fieldInfo.name.equalsIgnoreCase(str)) {
                    return fieldDeserializer;
                }
                i++;
            }
            return null;
        }
        int i2 = 0;
        i = this.sortedFieldDeserializers.length - 1;
        while (i2 <= i) {
            int i3 = (i2 + i) >>> 1;
            int compareTo = this.sortedFieldDeserializers[i3].fieldInfo.name.compareTo(str);
            if (compareTo < 0) {
                i2 = i3 + 1;
            } else if (compareTo <= 0) {
                return this.sortedFieldDeserializers[i3];
            } else {
                i = i3 - 1;
            }
        }
        return this.alterNameFieldDeserializers != null ? (FieldDeserializer) this.alterNameFieldDeserializers.get(str) : null;
    }

    private boolean parseField(DefaultJSONParser defaultJSONParser, String str, Object obj, Type type, Map<String, Object> map) {
        int i;
        int binarySearch;
        JSONLexer jSONLexer = defaultJSONParser.lexer;
        FieldDeserializer fieldDeserializer = getFieldDeserializer(str);
        if (fieldDeserializer == null) {
            int binarySearch2;
            boolean z;
            long fnv_64_lower = TypeUtils.fnv_64_lower(str);
            if (this.smartMatchHashArray == null) {
                long[] jArr = new long[this.sortedFieldDeserializers.length];
                for (i = 0; i < this.sortedFieldDeserializers.length; i++) {
                    jArr[i] = TypeUtils.fnv_64_lower(this.sortedFieldDeserializers[i].fieldInfo.name);
                }
                Arrays.sort(jArr);
                this.smartMatchHashArray = jArr;
            }
            int binarySearch3 = Arrays.binarySearch(this.smartMatchHashArray, fnv_64_lower);
            if (binarySearch3 < 0) {
                boolean startsWith = str.startsWith("is");
                if (startsWith) {
                    binarySearch2 = Arrays.binarySearch(this.smartMatchHashArray, TypeUtils.fnv_64_lower(str.substring(2)));
                    z = startsWith;
                } else {
                    binarySearch2 = binarySearch3;
                    z = startsWith;
                }
            } else {
                binarySearch2 = binarySearch3;
                z = false;
            }
            if (binarySearch2 >= 0) {
                if (this.smartMatchHashArrayMapping == null) {
                    int[] iArr = new int[this.smartMatchHashArray.length];
                    Arrays.fill(iArr, -1);
                    for (i = 0; i < this.sortedFieldDeserializers.length; i++) {
                        binarySearch = Arrays.binarySearch(this.smartMatchHashArray, TypeUtils.fnv_64_lower(this.sortedFieldDeserializers[i].fieldInfo.name));
                        if (binarySearch >= 0) {
                            iArr[binarySearch] = i;
                        }
                    }
                    this.smartMatchHashArrayMapping = iArr;
                }
                i = this.smartMatchHashArrayMapping[binarySearch2];
                if (i != -1) {
                    FieldDeserializer fieldDeserializer2 = this.sortedFieldDeserializers[i];
                    Class cls = fieldDeserializer2.fieldInfo.fieldClass;
                    fieldDeserializer = (!z || cls == Boolean.TYPE || cls == Boolean.class) ? fieldDeserializer2 : null;
                }
            }
        }
        i = Feature.SupportNonPublicField.mask;
        if (fieldDeserializer == null && !((defaultJSONParser.lexer.features & i) == 0 && (i & this.beanInfo.parserFeatures) == 0)) {
            if (this.extraFieldDeserializers == null) {
                ConcurrentMap concurrentHashMap = new ConcurrentHashMap(1, 0.75f, 1);
                Class cls2 = this.clazz;
                while (cls2 != null && cls2 != Object.class) {
                    for (Field field : cls2.getDeclaredFields()) {
                        String name = field.getName();
                        if (getFieldDeserializer(name) == null) {
                            int modifiers = field.getModifiers();
                            if ((modifiers & 16) == 0 && (modifiers & 8) == 0) {
                                concurrentHashMap.put(name, field);
                            }
                        }
                    }
                    cls2 = cls2.getSuperclass();
                }
                this.extraFieldDeserializers = concurrentHashMap;
            }
            Object obj2 = this.extraFieldDeserializers.get(str);
            if (obj2 != null) {
                if (obj2 instanceof FieldDeserializer) {
                    fieldDeserializer = (FieldDeserializer) obj2;
                } else {
                    Field field2 = (Field) obj2;
                    field2.setAccessible(true);
                    fieldDeserializer = new DefaultFieldDeserializer(defaultJSONParser.config, this.clazz, new FieldInfo(str, field2.getDeclaringClass(), field2.getType(), field2.getGenericType(), field2, 0, 0));
                    this.extraFieldDeserializers.put(str, fieldDeserializer);
                }
            }
        }
        if (fieldDeserializer == null) {
            parseExtra(defaultJSONParser, obj, str);
            return false;
        }
        jSONLexer.nextTokenWithChar(':');
        fieldDeserializer.parseField(defaultJSONParser, obj, type, map);
        return true;
    }

    void parseExtra(DefaultJSONParser defaultJSONParser, Object obj, String str) {
        JSONLexer jSONLexer = defaultJSONParser.lexer;
        if ((defaultJSONParser.lexer.features & Feature.IgnoreNotMatch.mask) == 0) {
            throw new JSONException("setter not found, class " + this.clazz.getName() + ", property " + str);
        }
        Object parse;
        jSONLexer.nextTokenWithChar(':');
        Type type = null;
        List<ExtraTypeProvider> list = defaultJSONParser.extraTypeProviders;
        if (list != null) {
            for (ExtraTypeProvider extraType : list) {
                type = extraType.getExtraType(obj, str);
            }
        }
        if (type == null) {
            parse = defaultJSONParser.parse();
        } else {
            parse = defaultJSONParser.parseObject(type);
        }
        if (obj instanceof ExtraProcessable) {
            ((ExtraProcessable) obj).processExtra(str, parse);
            return;
        }
        List<ExtraProcessor> list2 = defaultJSONParser.extraProcessors;
        if (list2 != null) {
            for (ExtraProcessor processExtra : list2) {
                processExtra.processExtra(obj, str, parse);
            }
        }
    }

    public Object createInstance(Map<String, Object> map, ParserConfig parserConfig) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Object value;
        if (this.beanInfo.creatorConstructor == null) {
            Object createInstance = createInstance(null, this.clazz);
            for (Entry entry : map.entrySet()) {
                FieldDeserializer fieldDeserializer = getFieldDeserializer((String) entry.getKey());
                if (fieldDeserializer != null) {
                    value = entry.getValue();
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
            FieldInfo fieldInfo = fieldInfoArr[i];
            value = map.get(fieldInfo.name);
            if (value == null) {
                value = TypeUtils.defaultValue(fieldInfo.fieldClass);
            }
            objArr[i] = value;
        }
        if (this.beanInfo.creatorConstructor == null) {
            return null;
        }
        try {
            return this.beanInfo.creatorConstructor.newInstance(objArr);
        } catch (Throwable e) {
            throw new JSONException("create instance error, " + this.beanInfo.creatorConstructor.toGenericString(), e);
        }
    }

    protected JavaBeanDeserializer getSeeAlso(ParserConfig parserConfig, JavaBeanInfo javaBeanInfo, String str) {
        if (javaBeanInfo.jsonType == null) {
            return null;
        }
        for (Type deserializer : javaBeanInfo.jsonType.seeAlso()) {
            ObjectDeserializer deserializer2 = parserConfig.getDeserializer(deserializer);
            if (deserializer2 instanceof JavaBeanDeserializer) {
                JavaBeanDeserializer javaBeanDeserializer = (JavaBeanDeserializer) deserializer2;
                JavaBeanInfo javaBeanInfo2 = javaBeanDeserializer.beanInfo;
                if (javaBeanInfo2.typeName.equals(str)) {
                    return javaBeanDeserializer;
                }
                javaBeanDeserializer = getSeeAlso(parserConfig, javaBeanInfo2, str);
                if (javaBeanDeserializer != null) {
                    return javaBeanDeserializer;
                }
            }
        }
        return null;
    }
}
