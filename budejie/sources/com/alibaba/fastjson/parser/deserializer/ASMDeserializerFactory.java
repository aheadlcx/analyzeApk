package com.alibaba.fastjson.parser.deserializer;

import com.alibaba.fastjson.asm.ClassWriter;
import com.alibaba.fastjson.asm.FieldWriter;
import com.alibaba.fastjson.asm.Label;
import com.alibaba.fastjson.asm.MethodVisitor;
import com.alibaba.fastjson.asm.MethodWriter;
import com.alibaba.fastjson.asm.Opcodes;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.DefaultJSONParser.ResolveTask;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.parser.JSONLexer;
import com.alibaba.fastjson.parser.JSONLexerBase;
import com.alibaba.fastjson.parser.JSONToken;
import com.alibaba.fastjson.parser.ParseContext;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.parser.SymbolTable;
import com.alibaba.fastjson.util.ASMClassLoader;
import com.alibaba.fastjson.util.ASMUtils;
import com.alibaba.fastjson.util.FieldInfo;
import com.alibaba.fastjson.util.JavaBeanInfo;
import com.alibaba.fastjson.util.TypeUtils;
import com.alibaba.wireless.security.open.nocaptcha.INoCaptchaComponent;
import com.alipay.sdk.util.h;
import com.umeng.analytics.pro.x;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.TreeSet;
import java.util.concurrent.atomic.AtomicLong;

public class ASMDeserializerFactory implements Opcodes {
    static final String DefaultJSONParser = ASMUtils.type(DefaultJSONParser.class);
    static final String JSONLexerBase = ASMUtils.type(JSONLexerBase.class);
    static final String JSONToken = ASMUtils.type(JSONToken.class);
    public final ASMClassLoader classLoader;
    protected final AtomicLong seed = new AtomicLong();

    static class Context {
        private final JavaBeanInfo beanInfo;
        private final String className;
        private final Class<?> clazz;
        private FieldInfo[] fieldInfoList;
        private int variantIndex = 5;
        private final Map<String, Integer> variants = new HashMap();

        public Context(String str, ParserConfig parserConfig, JavaBeanInfo javaBeanInfo, int i) {
            this.className = str;
            this.clazz = javaBeanInfo.clazz;
            this.variantIndex = i;
            this.beanInfo = javaBeanInfo;
            this.fieldInfoList = javaBeanInfo.fields;
        }

        public Class<?> getInstClass() {
            Class<?> cls = this.beanInfo.builderClass;
            if (cls == null) {
                return this.clazz;
            }
            return cls;
        }

        public int var(String str, int i) {
            if (((Integer) this.variants.get(str)) == null) {
                this.variants.put(str, Integer.valueOf(this.variantIndex));
                this.variantIndex += i;
            }
            return ((Integer) this.variants.get(str)).intValue();
        }

        public int var(String str) {
            if (((Integer) this.variants.get(str)) == null) {
                Map map = this.variants;
                int i = this.variantIndex;
                this.variantIndex = i + 1;
                map.put(str, Integer.valueOf(i));
            }
            return ((Integer) this.variants.get(str)).intValue();
        }
    }

    public ASMDeserializerFactory(ClassLoader classLoader) {
        this.classLoader = classLoader instanceof ASMClassLoader ? (ASMClassLoader) classLoader : new ASMClassLoader(classLoader);
    }

    public ObjectDeserializer createJavaBeanDeserializer(ParserConfig parserConfig, Class<?> cls, Type type) throws Exception {
        if (cls.isPrimitive()) {
            throw new IllegalArgumentException("not support type :" + cls.getName());
        }
        String str = "FastjsonASMDeserializer_" + this.seed.incrementAndGet() + "_" + cls.getSimpleName();
        String name = ASMDeserializerFactory.class.getPackage().getName();
        String str2 = name.replace('.', '/') + "/" + str;
        String str3 = name + "." + str;
        ClassWriter classWriter = new ClassWriter();
        classWriter.visit(49, 33, str2, ASMUtils.type(ASMJavaBeanDeserializer.class), null);
        JavaBeanInfo build = JavaBeanInfo.build(cls, type);
        _init(classWriter, new Context(str2, parserConfig, build, 3));
        _createInstance(classWriter, new Context(str2, parserConfig, build, 3));
        _deserialze(classWriter, new Context(str2, parserConfig, build, 4));
        _deserialzeArrayMapping(classWriter, new Context(str2, parserConfig, build, 4));
        byte[] toByteArray = classWriter.toByteArray();
        return (ObjectDeserializer) defineClassPublic(str3, toByteArray, 0, toByteArray.length).getConstructor(new Class[]{ParserConfig.class, Class.class}).newInstance(new Object[]{parserConfig, cls});
    }

    private Class<?> defineClassPublic(String str, byte[] bArr, int i, int i2) {
        return this.classLoader.defineClassPublic(str, bArr, i, i2);
    }

    void _setFlag(MethodVisitor methodVisitor, Context context, int i) {
        String str = "_asm_flag_" + (i / 32);
        methodVisitor.visitVarInsn(21, context.var(str));
        methodVisitor.visitLdcInsn(Integer.valueOf(1 << i));
        methodVisitor.visitInsn(128);
        methodVisitor.visitVarInsn(54, context.var(str));
    }

    void _isFlag(MethodVisitor methodVisitor, Context context, int i, Label label) {
        methodVisitor.visitVarInsn(21, context.var("_asm_flag_" + (i / 32)));
        methodVisitor.visitLdcInsn(Integer.valueOf(1 << i));
        methodVisitor.visitInsn(126);
        methodVisitor.visitJumpInsn(153, label);
    }

    void _deserialzeArrayMapping(ClassWriter classWriter, Context context) {
        MethodVisitor methodWriter = new MethodWriter(classWriter, 1, "deserialzeArrayMapping", "(L" + DefaultJSONParser + ";Ljava/lang/reflect/Type;Ljava/lang/Object;)Ljava/lang/Object;", null, null);
        defineVarLexer(context, methodWriter);
        _createInstance(context, methodWriter);
        FieldInfo[] fieldInfoArr = context.beanInfo.sortedFields;
        int length = fieldInfoArr.length;
        int i = 0;
        while (i < length) {
            Object obj = i == length + -1 ? 1 : null;
            int i2 = obj != null ? 93 : 44;
            FieldInfo fieldInfo = fieldInfoArr[i];
            Class cls = fieldInfo.fieldClass;
            Type type = fieldInfo.fieldType;
            if (cls == Byte.TYPE || cls == Short.TYPE || cls == Integer.TYPE) {
                methodWriter.visitVarInsn(25, context.var("lexer"));
                methodWriter.visitVarInsn(16, i2);
                methodWriter.visitMethodInsn(182, JSONLexerBase, "scanInt", "(C)I");
                methodWriter.visitVarInsn(54, context.var(fieldInfo.name + "_asm"));
            } else if (cls == Long.TYPE) {
                methodWriter.visitVarInsn(25, context.var("lexer"));
                methodWriter.visitVarInsn(16, i2);
                methodWriter.visitMethodInsn(182, JSONLexerBase, "scanLong", "(C)J");
                methodWriter.visitVarInsn(55, context.var(fieldInfo.name + "_asm", 2));
            } else if (cls == Boolean.TYPE) {
                methodWriter.visitVarInsn(25, context.var("lexer"));
                methodWriter.visitVarInsn(16, i2);
                methodWriter.visitMethodInsn(182, JSONLexerBase, "scanBoolean", "(C)Z");
                methodWriter.visitVarInsn(54, context.var(fieldInfo.name + "_asm"));
            } else if (cls == Float.TYPE) {
                methodWriter.visitVarInsn(25, context.var("lexer"));
                methodWriter.visitVarInsn(16, i2);
                methodWriter.visitMethodInsn(182, JSONLexerBase, "scanFloat", "(C)F");
                methodWriter.visitVarInsn(56, context.var(fieldInfo.name + "_asm"));
            } else if (cls == Double.TYPE) {
                methodWriter.visitVarInsn(25, context.var("lexer"));
                methodWriter.visitVarInsn(16, i2);
                methodWriter.visitMethodInsn(182, JSONLexerBase, "scanDouble", "(C)D");
                methodWriter.visitVarInsn(57, context.var(fieldInfo.name + "_asm", 2));
            } else if (cls == Character.TYPE) {
                methodWriter.visitVarInsn(25, context.var("lexer"));
                methodWriter.visitVarInsn(16, i2);
                methodWriter.visitMethodInsn(182, JSONLexerBase, "scanString", "(C)Ljava/lang/String;");
                methodWriter.visitInsn(3);
                methodWriter.visitMethodInsn(182, "java/lang/String", "charAt", "(I)C");
                methodWriter.visitVarInsn(54, context.var(fieldInfo.name + "_asm"));
            } else if (cls == String.class) {
                methodWriter.visitVarInsn(25, context.var("lexer"));
                methodWriter.visitVarInsn(16, i2);
                methodWriter.visitMethodInsn(182, JSONLexerBase, "scanString", "(C)Ljava/lang/String;");
                methodWriter.visitVarInsn(58, context.var(fieldInfo.name + "_asm"));
            } else if (cls.isEnum()) {
                methodWriter.visitVarInsn(25, context.var("lexer"));
                methodWriter.visitLdcInsn(com.alibaba.fastjson.asm.Type.getType(ASMUtils.desc(cls)));
                methodWriter.visitVarInsn(25, 1);
                methodWriter.visitMethodInsn(182, DefaultJSONParser, "getSymbolTable", "()" + ASMUtils.desc(SymbolTable.class));
                methodWriter.visitVarInsn(16, i2);
                methodWriter.visitMethodInsn(182, JSONLexerBase, "scanEnum", "(Ljava/lang/Class;" + ASMUtils.desc(SymbolTable.class) + "C)Ljava/lang/Enum;");
                methodWriter.visitTypeInsn(192, ASMUtils.type(cls));
                methodWriter.visitVarInsn(58, context.var(fieldInfo.name + "_asm"));
            } else if (Collection.class.isAssignableFrom(cls)) {
                Class collectionItemClass = TypeUtils.getCollectionItemClass(type);
                if (collectionItemClass == String.class) {
                    methodWriter.visitVarInsn(25, context.var("lexer"));
                    methodWriter.visitLdcInsn(com.alibaba.fastjson.asm.Type.getType(ASMUtils.desc(cls)));
                    methodWriter.visitVarInsn(16, i2);
                    methodWriter.visitMethodInsn(182, JSONLexerBase, "scanStringArray", "(Ljava/lang/Class;C)Ljava/util/Collection;");
                    methodWriter.visitVarInsn(58, context.var(fieldInfo.name + "_asm"));
                } else {
                    methodWriter.visitVarInsn(25, 1);
                    if (i == 0) {
                        methodWriter.visitFieldInsn(178, JSONToken, "LBRACKET", "I");
                    } else {
                        methodWriter.visitFieldInsn(178, JSONToken, "COMMA", "I");
                    }
                    methodWriter.visitFieldInsn(178, JSONToken, "LBRACKET", "I");
                    methodWriter.visitMethodInsn(182, DefaultJSONParser, "accept", "(II)V");
                    _newCollection(methodWriter, cls, i, false);
                    methodWriter.visitInsn(89);
                    methodWriter.visitVarInsn(58, context.var(fieldInfo.name + "_asm"));
                    _getCollectionFieldItemDeser(context, methodWriter, fieldInfo, collectionItemClass);
                    methodWriter.visitVarInsn(25, 1);
                    methodWriter.visitLdcInsn(com.alibaba.fastjson.asm.Type.getType(ASMUtils.desc(collectionItemClass)));
                    methodWriter.visitVarInsn(25, 3);
                    methodWriter.visitMethodInsn(184, ASMUtils.type(ASMUtils.class), "parseArray", "(Ljava/util/Collection;" + ASMUtils.desc(ObjectDeserializer.class) + "L" + DefaultJSONParser + h.b + "Ljava/lang/reflect/Type;Ljava/lang/Object;)V");
                }
            } else if (cls.isArray()) {
                methodWriter.visitVarInsn(25, context.var("lexer"));
                methodWriter.visitFieldInsn(178, JSONToken, "LBRACKET", "I");
                methodWriter.visitMethodInsn(182, JSONLexerBase, "nextToken", "(I)V");
                methodWriter.visitVarInsn(25, 1);
                methodWriter.visitVarInsn(25, 0);
                methodWriter.visitLdcInsn(Integer.valueOf(i));
                methodWriter.visitMethodInsn(182, ASMUtils.type(ASMJavaBeanDeserializer.class), "getFieldType", "(I)Ljava/lang/reflect/Type;");
                methodWriter.visitMethodInsn(182, DefaultJSONParser, "parseObject", "(Ljava/lang/reflect/Type;)Ljava/lang/Object;");
                methodWriter.visitTypeInsn(192, ASMUtils.type(cls));
                methodWriter.visitVarInsn(58, context.var(fieldInfo.name + "_asm"));
            } else {
                methodWriter.visitVarInsn(25, 1);
                if (i == 0) {
                    methodWriter.visitFieldInsn(178, JSONToken, "LBRACKET", "I");
                } else {
                    methodWriter.visitFieldInsn(178, JSONToken, "COMMA", "I");
                }
                methodWriter.visitFieldInsn(178, JSONToken, "LBRACKET", "I");
                methodWriter.visitMethodInsn(182, DefaultJSONParser, "accept", "(II)V");
                _deserObject(context, methodWriter, fieldInfo, cls, i);
                methodWriter.visitVarInsn(25, 1);
                if (obj == null) {
                    methodWriter.visitFieldInsn(178, JSONToken, "COMMA", "I");
                    methodWriter.visitFieldInsn(178, JSONToken, "LBRACKET", "I");
                } else {
                    methodWriter.visitFieldInsn(178, JSONToken, "RBRACKET", "I");
                    methodWriter.visitFieldInsn(178, JSONToken, "EOF", "I");
                }
                methodWriter.visitMethodInsn(182, DefaultJSONParser, "accept", "(II)V");
            }
            i++;
        }
        _batchSet(context, methodWriter, false);
        methodWriter.visitVarInsn(25, context.var("lexer"));
        methodWriter.visitFieldInsn(178, JSONToken, "COMMA", "I");
        methodWriter.visitMethodInsn(182, JSONLexerBase, "nextToken", "(I)V");
        methodWriter.visitVarInsn(25, context.var("instance"));
        methodWriter.visitInsn(176);
        methodWriter.visitMaxs(5, context.variantIndex);
        methodWriter.visitEnd();
    }

    void _deserialze(ClassWriter classWriter, Context context) {
        if (context.fieldInfoList.length != 0) {
            FieldInfo[] access$200 = context.fieldInfoList;
            int length = access$200.length;
            int i = 0;
            while (i < length) {
                FieldInfo fieldInfo = access$200[i];
                Class cls = fieldInfo.fieldClass;
                Type type = fieldInfo.fieldType;
                if (cls == Character.TYPE) {
                    return;
                }
                if (!Collection.class.isAssignableFrom(cls) || ((type instanceof ParameterizedType) && (((ParameterizedType) type).getActualTypeArguments()[0] instanceof Class))) {
                    i++;
                } else {
                    return;
                }
            }
            context.fieldInfoList = context.beanInfo.sortedFields;
            String str = "(L" + DefaultJSONParser + ";Ljava/lang/reflect/Type;Ljava/lang/Object;)Ljava/lang/Object;";
            MethodVisitor methodWriter = new MethodWriter(classWriter, 1, "deserialze", str, null, null);
            Label label = new Label();
            Label label2 = new Label();
            Label label3 = new Label();
            Label label4 = new Label();
            defineVarLexer(context, methodWriter);
            _isEnable(context, methodWriter, Feature.SortFeidFastMatch);
            methodWriter.visitJumpInsn(153, label2);
            Label label5 = new Label();
            methodWriter.visitVarInsn(25, 0);
            methodWriter.visitVarInsn(25, context.var("lexer"));
            methodWriter.visitMethodInsn(183, ASMUtils.type(ASMJavaBeanDeserializer.class), "isSupportArrayToBean", "(" + ASMUtils.desc(JSONLexer.class) + ")Z");
            methodWriter.visitJumpInsn(153, label5);
            methodWriter.visitVarInsn(25, context.var("lexer"));
            methodWriter.visitMethodInsn(182, JSONLexerBase, INoCaptchaComponent.token, "()I");
            methodWriter.visitFieldInsn(178, JSONToken, "LBRACKET", "I");
            methodWriter.visitJumpInsn(160, label5);
            methodWriter.visitVarInsn(25, 0);
            methodWriter.visitVarInsn(25, 1);
            methodWriter.visitVarInsn(25, 2);
            methodWriter.visitVarInsn(25, 3);
            methodWriter.visitMethodInsn(183, context.className, "deserialzeArrayMapping", "(L" + DefaultJSONParser + ";Ljava/lang/reflect/Type;Ljava/lang/Object;)Ljava/lang/Object;");
            methodWriter.visitInsn(176);
            methodWriter.visitLabel(label5);
            methodWriter.visitVarInsn(25, context.var("lexer"));
            methodWriter.visitLdcInsn(context.clazz.getName());
            methodWriter.visitMethodInsn(182, JSONLexerBase, "scanType", "(Ljava/lang/String;)I");
            methodWriter.visitFieldInsn(178, JSONLexerBase, "NOT_MATCH", "I");
            methodWriter.visitJumpInsn(159, label2);
            methodWriter.visitVarInsn(25, 1);
            methodWriter.visitMethodInsn(182, DefaultJSONParser, "getContext", "()" + ASMUtils.desc(ParseContext.class));
            methodWriter.visitVarInsn(58, context.var("mark_context"));
            methodWriter.visitInsn(3);
            methodWriter.visitVarInsn(54, context.var("matchedCount"));
            _createInstance(context, methodWriter);
            methodWriter.visitVarInsn(25, 1);
            methodWriter.visitMethodInsn(182, DefaultJSONParser, "getContext", "()" + ASMUtils.desc(ParseContext.class));
            methodWriter.visitVarInsn(58, context.var(x.aI));
            methodWriter.visitVarInsn(25, 1);
            methodWriter.visitVarInsn(25, context.var(x.aI));
            methodWriter.visitVarInsn(25, context.var("instance"));
            methodWriter.visitVarInsn(25, 3);
            methodWriter.visitMethodInsn(182, DefaultJSONParser, "setContext", "(" + ASMUtils.desc(ParseContext.class) + "Ljava/lang/Object;Ljava/lang/Object;)" + ASMUtils.desc(ParseContext.class));
            methodWriter.visitVarInsn(58, context.var("childContext"));
            methodWriter.visitVarInsn(25, context.var("lexer"));
            methodWriter.visitFieldInsn(180, JSONLexerBase, "matchStat", "I");
            methodWriter.visitFieldInsn(178, JSONLexerBase, "END", "I");
            methodWriter.visitJumpInsn(159, label3);
            methodWriter.visitInsn(3);
            methodWriter.visitIntInsn(54, context.var("matchStat"));
            int length2 = context.fieldInfoList.length;
            for (i = 0; i < length2; i += 32) {
                methodWriter.visitInsn(3);
                methodWriter.visitVarInsn(54, context.var("_asm_flag_" + (i / 32)));
            }
            _isEnable(context, methodWriter, Feature.InitStringFieldAsEmpty);
            methodWriter.visitIntInsn(54, context.var("initStringFieldAsEmpty"));
            for (i = 0; i < length2; i++) {
                FieldInfo fieldInfo2 = context.fieldInfoList[i];
                Class cls2 = fieldInfo2.fieldClass;
                if (cls2 == Boolean.TYPE || cls2 == Byte.TYPE || cls2 == Short.TYPE || cls2 == Integer.TYPE) {
                    methodWriter.visitInsn(3);
                    methodWriter.visitVarInsn(54, context.var(fieldInfo2.name + "_asm"));
                } else if (cls2 == Long.TYPE) {
                    methodWriter.visitInsn(9);
                    methodWriter.visitVarInsn(55, context.var(fieldInfo2.name + "_asm", 2));
                } else if (cls2 == Float.TYPE) {
                    methodWriter.visitInsn(11);
                    methodWriter.visitVarInsn(56, context.var(fieldInfo2.name + "_asm"));
                } else if (cls2 == Double.TYPE) {
                    methodWriter.visitInsn(14);
                    methodWriter.visitVarInsn(57, context.var(fieldInfo2.name + "_asm", 2));
                } else {
                    if (cls2 == String.class) {
                        Label label6 = new Label();
                        Label label7 = new Label();
                        methodWriter.visitVarInsn(21, context.var("initStringFieldAsEmpty"));
                        methodWriter.visitJumpInsn(153, label7);
                        _setFlag(methodWriter, context, i);
                        methodWriter.visitVarInsn(25, context.var("lexer"));
                        methodWriter.visitMethodInsn(182, JSONLexerBase, "stringDefaultValue", "()Ljava/lang/String;");
                        methodWriter.visitJumpInsn(167, label6);
                        methodWriter.visitLabel(label7);
                        methodWriter.visitInsn(1);
                        methodWriter.visitLabel(label6);
                    } else {
                        methodWriter.visitInsn(1);
                    }
                    methodWriter.visitTypeInsn(192, ASMUtils.type(cls2));
                    methodWriter.visitVarInsn(58, context.var(fieldInfo2.name + "_asm"));
                }
            }
            for (int i2 = 0; i2 < length2; i2++) {
                FieldInfo fieldInfo3 = context.fieldInfoList[i2];
                Class cls3 = fieldInfo3.fieldClass;
                Type type2 = fieldInfo3.fieldType;
                Label label8 = new Label();
                if (cls3 == Boolean.TYPE) {
                    methodWriter.visitVarInsn(25, context.var("lexer"));
                    methodWriter.visitVarInsn(25, 0);
                    methodWriter.visitFieldInsn(180, context.className, fieldInfo3.name + "_asm_prefix__", "[C");
                    methodWriter.visitMethodInsn(182, JSONLexerBase, "scanFieldBoolean", "([C)Z");
                    methodWriter.visitVarInsn(54, context.var(fieldInfo3.name + "_asm"));
                } else if (cls3 == Byte.TYPE) {
                    methodWriter.visitVarInsn(25, context.var("lexer"));
                    methodWriter.visitVarInsn(25, 0);
                    methodWriter.visitFieldInsn(180, context.className, fieldInfo3.name + "_asm_prefix__", "[C");
                    methodWriter.visitMethodInsn(182, JSONLexerBase, "scanFieldInt", "([C)I");
                    methodWriter.visitVarInsn(54, context.var(fieldInfo3.name + "_asm"));
                } else if (cls3 == Short.TYPE) {
                    methodWriter.visitVarInsn(25, context.var("lexer"));
                    methodWriter.visitVarInsn(25, 0);
                    methodWriter.visitFieldInsn(180, context.className, fieldInfo3.name + "_asm_prefix__", "[C");
                    methodWriter.visitMethodInsn(182, JSONLexerBase, "scanFieldInt", "([C)I");
                    methodWriter.visitVarInsn(54, context.var(fieldInfo3.name + "_asm"));
                } else if (cls3 == Integer.TYPE) {
                    methodWriter.visitVarInsn(25, context.var("lexer"));
                    methodWriter.visitVarInsn(25, 0);
                    methodWriter.visitFieldInsn(180, context.className, fieldInfo3.name + "_asm_prefix__", "[C");
                    methodWriter.visitMethodInsn(182, JSONLexerBase, "scanFieldInt", "([C)I");
                    methodWriter.visitVarInsn(54, context.var(fieldInfo3.name + "_asm"));
                } else if (cls3 == Long.TYPE) {
                    methodWriter.visitVarInsn(25, context.var("lexer"));
                    methodWriter.visitVarInsn(25, 0);
                    methodWriter.visitFieldInsn(180, context.className, fieldInfo3.name + "_asm_prefix__", "[C");
                    methodWriter.visitMethodInsn(182, JSONLexerBase, "scanFieldLong", "([C)J");
                    methodWriter.visitVarInsn(55, context.var(fieldInfo3.name + "_asm", 2));
                } else if (cls3 == Float.TYPE) {
                    methodWriter.visitVarInsn(25, context.var("lexer"));
                    methodWriter.visitVarInsn(25, 0);
                    methodWriter.visitFieldInsn(180, context.className, fieldInfo3.name + "_asm_prefix__", "[C");
                    methodWriter.visitMethodInsn(182, JSONLexerBase, "scanFieldFloat", "([C)F");
                    methodWriter.visitVarInsn(56, context.var(fieldInfo3.name + "_asm"));
                } else if (cls3 == Double.TYPE) {
                    methodWriter.visitVarInsn(25, context.var("lexer"));
                    methodWriter.visitVarInsn(25, 0);
                    methodWriter.visitFieldInsn(180, context.className, fieldInfo3.name + "_asm_prefix__", "[C");
                    methodWriter.visitMethodInsn(182, JSONLexerBase, "scanFieldDouble", "([C)D");
                    methodWriter.visitVarInsn(57, context.var(fieldInfo3.name + "_asm", 2));
                } else if (cls3 == String.class) {
                    methodWriter.visitVarInsn(25, context.var("lexer"));
                    methodWriter.visitVarInsn(25, 0);
                    methodWriter.visitFieldInsn(180, context.className, fieldInfo3.name + "_asm_prefix__", "[C");
                    methodWriter.visitMethodInsn(182, JSONLexerBase, "scanFieldString", "([C)Ljava/lang/String;");
                    methodWriter.visitVarInsn(58, context.var(fieldInfo3.name + "_asm"));
                } else if (cls3.isEnum()) {
                    methodWriter.visitVarInsn(25, context.var("lexer"));
                    methodWriter.visitVarInsn(25, 0);
                    methodWriter.visitFieldInsn(180, context.className, fieldInfo3.name + "_asm_prefix__", "[C");
                    label5 = new Label();
                    methodWriter.visitInsn(1);
                    methodWriter.visitTypeInsn(192, ASMUtils.type(cls3));
                    methodWriter.visitVarInsn(58, context.var(fieldInfo3.name + "_asm"));
                    methodWriter.visitVarInsn(25, 1);
                    methodWriter.visitMethodInsn(182, DefaultJSONParser, "getSymbolTable", "()" + ASMUtils.desc(SymbolTable.class));
                    methodWriter.visitMethodInsn(182, JSONLexerBase, "scanFieldSymbol", "([C" + ASMUtils.desc(SymbolTable.class) + ")Ljava/lang/String;");
                    methodWriter.visitInsn(89);
                    methodWriter.visitVarInsn(58, context.var(fieldInfo3.name + "_asm_enumName"));
                    methodWriter.visitJumpInsn(198, label5);
                    methodWriter.visitVarInsn(25, context.var(fieldInfo3.name + "_asm_enumName"));
                    methodWriter.visitMethodInsn(184, ASMUtils.type(cls3), "valueOf", "(Ljava/lang/String;)" + ASMUtils.desc(cls3));
                    methodWriter.visitVarInsn(58, context.var(fieldInfo3.name + "_asm"));
                    methodWriter.visitLabel(label5);
                } else {
                    if (Collection.class.isAssignableFrom(cls3)) {
                        methodWriter.visitVarInsn(25, context.var("lexer"));
                        methodWriter.visitVarInsn(25, 0);
                        methodWriter.visitFieldInsn(180, context.className, fieldInfo3.name + "_asm_prefix__", "[C");
                        Class collectionItemClass = TypeUtils.getCollectionItemClass(type2);
                        if (collectionItemClass == String.class) {
                            methodWriter.visitLdcInsn(com.alibaba.fastjson.asm.Type.getType(ASMUtils.desc(cls3)));
                            methodWriter.visitMethodInsn(182, JSONLexerBase, "scanFieldStringArray", "([CLjava/lang/Class;)" + ASMUtils.desc(Collection.class));
                            methodWriter.visitVarInsn(58, context.var(fieldInfo3.name + "_asm"));
                        } else {
                            _deserialze_list_obj(context, methodWriter, label, fieldInfo3, cls3, collectionItemClass, i2);
                            if (i2 == length2 - 1) {
                                _deserialize_endCheck(context, methodWriter, label);
                            }
                        }
                    } else {
                        _deserialze_obj(context, methodWriter, label, fieldInfo3, cls3, i2);
                        if (i2 == length2 - 1) {
                            _deserialize_endCheck(context, methodWriter, label);
                        }
                    }
                }
                methodWriter.visitVarInsn(25, context.var("lexer"));
                methodWriter.visitFieldInsn(180, JSONLexerBase, "matchStat", "I");
                label5 = new Label();
                methodWriter.visitJumpInsn(158, label5);
                _setFlag(methodWriter, context, i2);
                methodWriter.visitLabel(label5);
                methodWriter.visitVarInsn(25, context.var("lexer"));
                methodWriter.visitFieldInsn(180, JSONLexerBase, "matchStat", "I");
                methodWriter.visitInsn(89);
                methodWriter.visitVarInsn(54, context.var("matchStat"));
                methodWriter.visitFieldInsn(178, JSONLexerBase, "NOT_MATCH", "I");
                methodWriter.visitJumpInsn(159, label);
                methodWriter.visitVarInsn(25, context.var("lexer"));
                methodWriter.visitFieldInsn(180, JSONLexerBase, "matchStat", "I");
                methodWriter.visitJumpInsn(158, label8);
                methodWriter.visitVarInsn(21, context.var("matchedCount"));
                methodWriter.visitInsn(4);
                methodWriter.visitInsn(96);
                methodWriter.visitVarInsn(54, context.var("matchedCount"));
                methodWriter.visitVarInsn(25, context.var("lexer"));
                methodWriter.visitFieldInsn(180, JSONLexerBase, "matchStat", "I");
                methodWriter.visitFieldInsn(178, JSONLexerBase, "END", "I");
                methodWriter.visitJumpInsn(159, label4);
                methodWriter.visitLabel(label8);
                if (i2 == length2 - 1) {
                    methodWriter.visitVarInsn(25, context.var("lexer"));
                    methodWriter.visitFieldInsn(180, JSONLexerBase, "matchStat", "I");
                    methodWriter.visitFieldInsn(178, JSONLexerBase, "END", "I");
                    methodWriter.visitJumpInsn(160, label);
                }
            }
            methodWriter.visitLabel(label4);
            if (!(context.clazz.isInterface() || Modifier.isAbstract(context.clazz.getModifiers()))) {
                _batchSet(context, methodWriter);
            }
            methodWriter.visitLabel(label3);
            _setContext(context, methodWriter);
            methodWriter.visitVarInsn(25, context.var("instance"));
            Method method = context.beanInfo.buildMethod;
            if (method != null) {
                methodWriter.visitMethodInsn(182, ASMUtils.type(context.getInstClass()), method.getName(), "()" + ASMUtils.desc(method.getReturnType()));
            }
            methodWriter.visitInsn(176);
            methodWriter.visitLabel(label);
            _batchSet(context, methodWriter);
            methodWriter.visitVarInsn(25, 0);
            methodWriter.visitVarInsn(25, 1);
            methodWriter.visitVarInsn(25, 2);
            methodWriter.visitVarInsn(25, 3);
            methodWriter.visitVarInsn(25, context.var("instance"));
            methodWriter.visitMethodInsn(182, ASMUtils.type(ASMJavaBeanDeserializer.class), "parseRest", "(L" + DefaultJSONParser + ";Ljava/lang/reflect/Type;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;");
            methodWriter.visitTypeInsn(192, ASMUtils.type(context.clazz));
            methodWriter.visitInsn(176);
            methodWriter.visitLabel(label2);
            methodWriter.visitVarInsn(25, 0);
            methodWriter.visitVarInsn(25, 1);
            methodWriter.visitVarInsn(25, 2);
            methodWriter.visitVarInsn(25, 3);
            methodWriter.visitMethodInsn(183, ASMUtils.type(ASMJavaBeanDeserializer.class), "deserialze", "(L" + DefaultJSONParser + ";Ljava/lang/reflect/Type;Ljava/lang/Object;)Ljava/lang/Object;");
            methodWriter.visitInsn(176);
            methodWriter.visitMaxs(5, context.variantIndex);
            methodWriter.visitEnd();
        }
    }

    private void _isEnable(Context context, MethodVisitor methodVisitor, Feature feature) {
        methodVisitor.visitVarInsn(25, context.var("lexer"));
        methodVisitor.visitFieldInsn(178, ASMUtils.type(Feature.class), feature.name(), ASMUtils.desc(Feature.class));
        methodVisitor.visitMethodInsn(182, JSONLexerBase, "isEnabled", "(" + ASMUtils.desc(Feature.class) + ")Z");
    }

    private void defineVarLexer(Context context, MethodVisitor methodVisitor) {
        methodVisitor.visitVarInsn(25, 1);
        methodVisitor.visitFieldInsn(180, DefaultJSONParser, "lexer", ASMUtils.desc(JSONLexer.class));
        methodVisitor.visitTypeInsn(192, JSONLexerBase);
        methodVisitor.visitVarInsn(58, context.var("lexer"));
    }

    private void _createInstance(Context context, MethodVisitor methodVisitor) {
        Constructor constructor = context.beanInfo.defaultConstructor;
        if (Modifier.isPublic(constructor.getModifiers())) {
            methodVisitor.visitTypeInsn(187, ASMUtils.type(context.getInstClass()));
            methodVisitor.visitInsn(89);
            methodVisitor.visitMethodInsn(183, ASMUtils.type(constructor.getDeclaringClass()), "<init>", "()V");
            methodVisitor.visitVarInsn(58, context.var("instance"));
            return;
        }
        methodVisitor.visitVarInsn(25, 0);
        methodVisitor.visitVarInsn(25, 1);
        methodVisitor.visitMethodInsn(183, ASMUtils.type(ASMJavaBeanDeserializer.class), "createInstance", "(L" + DefaultJSONParser + ";)Ljava/lang/Object;");
        methodVisitor.visitTypeInsn(192, ASMUtils.type(context.getInstClass()));
        methodVisitor.visitVarInsn(58, context.var("instance"));
    }

    private void _batchSet(Context context, MethodVisitor methodVisitor) {
        _batchSet(context, methodVisitor, true);
    }

    private void _batchSet(Context context, MethodVisitor methodVisitor, boolean z) {
        int length = context.fieldInfoList.length;
        for (int i = 0; i < length; i++) {
            Label label = new Label();
            if (z) {
                _isFlag(methodVisitor, context, i, label);
            }
            _loadAndSet(context, methodVisitor, context.fieldInfoList[i]);
            if (z) {
                methodVisitor.visitLabel(label);
            }
        }
    }

    private void _loadAndSet(Context context, MethodVisitor methodVisitor, FieldInfo fieldInfo) {
        Class cls = fieldInfo.fieldClass;
        Type type = fieldInfo.fieldType;
        if (cls == Boolean.TYPE) {
            methodVisitor.visitVarInsn(25, context.var("instance"));
            methodVisitor.visitVarInsn(21, context.var(fieldInfo.name + "_asm"));
            _set(context, methodVisitor, fieldInfo);
        } else if (cls == Byte.TYPE || cls == Short.TYPE || cls == Integer.TYPE || cls == Character.TYPE) {
            methodVisitor.visitVarInsn(25, context.var("instance"));
            methodVisitor.visitVarInsn(21, context.var(fieldInfo.name + "_asm"));
            _set(context, methodVisitor, fieldInfo);
        } else if (cls == Long.TYPE) {
            methodVisitor.visitVarInsn(25, context.var("instance"));
            methodVisitor.visitVarInsn(22, context.var(fieldInfo.name + "_asm", 2));
            if (fieldInfo.method != null) {
                methodVisitor.visitMethodInsn(182, ASMUtils.type(context.getInstClass()), fieldInfo.method.getName(), ASMUtils.desc(fieldInfo.method));
                if (!fieldInfo.method.getReturnType().equals(Void.TYPE)) {
                    methodVisitor.visitInsn(87);
                    return;
                }
                return;
            }
            methodVisitor.visitFieldInsn(181, ASMUtils.type(fieldInfo.declaringClass), fieldInfo.field.getName(), ASMUtils.desc(fieldInfo.fieldClass));
        } else if (cls == Float.TYPE) {
            methodVisitor.visitVarInsn(25, context.var("instance"));
            methodVisitor.visitVarInsn(23, context.var(fieldInfo.name + "_asm"));
            _set(context, methodVisitor, fieldInfo);
        } else if (cls == Double.TYPE) {
            methodVisitor.visitVarInsn(25, context.var("instance"));
            methodVisitor.visitVarInsn(24, context.var(fieldInfo.name + "_asm", 2));
            _set(context, methodVisitor, fieldInfo);
        } else if (cls == String.class) {
            methodVisitor.visitVarInsn(25, context.var("instance"));
            methodVisitor.visitVarInsn(25, context.var(fieldInfo.name + "_asm"));
            _set(context, methodVisitor, fieldInfo);
        } else if (cls.isEnum()) {
            methodVisitor.visitVarInsn(25, context.var("instance"));
            methodVisitor.visitVarInsn(25, context.var(fieldInfo.name + "_asm"));
            _set(context, methodVisitor, fieldInfo);
        } else if (Collection.class.isAssignableFrom(cls)) {
            methodVisitor.visitVarInsn(25, context.var("instance"));
            if (TypeUtils.getCollectionItemClass(type) == String.class) {
                methodVisitor.visitVarInsn(25, context.var(fieldInfo.name + "_asm"));
                methodVisitor.visitTypeInsn(192, ASMUtils.type(cls));
            } else {
                methodVisitor.visitVarInsn(25, context.var(fieldInfo.name + "_asm"));
            }
            _set(context, methodVisitor, fieldInfo);
        } else {
            methodVisitor.visitVarInsn(25, context.var("instance"));
            methodVisitor.visitVarInsn(25, context.var(fieldInfo.name + "_asm"));
            _set(context, methodVisitor, fieldInfo);
        }
    }

    private void _set(Context context, MethodVisitor methodVisitor, FieldInfo fieldInfo) {
        if (fieldInfo.method != null) {
            methodVisitor.visitMethodInsn(182, ASMUtils.type(fieldInfo.declaringClass), fieldInfo.method.getName(), ASMUtils.desc(fieldInfo.method));
            if (!fieldInfo.method.getReturnType().equals(Void.TYPE)) {
                methodVisitor.visitInsn(87);
                return;
            }
            return;
        }
        methodVisitor.visitFieldInsn(181, ASMUtils.type(fieldInfo.declaringClass), fieldInfo.field.getName(), ASMUtils.desc(fieldInfo.fieldClass));
    }

    private void _setContext(Context context, MethodVisitor methodVisitor) {
        methodVisitor.visitVarInsn(25, 1);
        methodVisitor.visitVarInsn(25, context.var(x.aI));
        methodVisitor.visitMethodInsn(182, DefaultJSONParser, "setContext", "(" + ASMUtils.desc(ParseContext.class) + ")V");
        Label label = new Label();
        methodVisitor.visitVarInsn(25, context.var("childContext"));
        methodVisitor.visitJumpInsn(198, label);
        methodVisitor.visitVarInsn(25, context.var("childContext"));
        methodVisitor.visitVarInsn(25, context.var("instance"));
        methodVisitor.visitFieldInsn(181, ASMUtils.type(ParseContext.class), "object", "Ljava/lang/Object;");
        methodVisitor.visitLabel(label);
    }

    private void _deserialize_endCheck(Context context, MethodVisitor methodVisitor, Label label) {
        methodVisitor.visitIntInsn(21, context.var("matchedCount"));
        methodVisitor.visitJumpInsn(158, label);
        methodVisitor.visitVarInsn(25, context.var("lexer"));
        methodVisitor.visitMethodInsn(182, JSONLexerBase, INoCaptchaComponent.token, "()I");
        methodVisitor.visitFieldInsn(178, JSONToken, "RBRACE", "I");
        methodVisitor.visitJumpInsn(160, label);
        methodVisitor.visitVarInsn(25, context.var("lexer"));
        methodVisitor.visitFieldInsn(178, JSONToken, "COMMA", "I");
        methodVisitor.visitMethodInsn(182, JSONLexerBase, "nextToken", "(I)V");
    }

    private void _deserialze_list_obj(Context context, MethodVisitor methodVisitor, Label label, FieldInfo fieldInfo, Class<?> cls, Class<?> cls2, int i) {
        Label label2 = new Label();
        methodVisitor.visitMethodInsn(182, JSONLexerBase, "matchField", "([C)Z");
        methodVisitor.visitJumpInsn(153, label2);
        _setFlag(methodVisitor, context, i);
        Label label3 = new Label();
        methodVisitor.visitVarInsn(25, context.var("lexer"));
        methodVisitor.visitMethodInsn(182, JSONLexerBase, INoCaptchaComponent.token, "()I");
        methodVisitor.visitFieldInsn(178, JSONToken, "NULL", "I");
        methodVisitor.visitJumpInsn(160, label3);
        methodVisitor.visitVarInsn(25, context.var("lexer"));
        methodVisitor.visitFieldInsn(178, JSONToken, "COMMA", "I");
        methodVisitor.visitMethodInsn(182, JSONLexerBase, "nextToken", "(I)V");
        methodVisitor.visitJumpInsn(167, label2);
        methodVisitor.visitLabel(label3);
        label3 = new Label();
        Label label4 = new Label();
        methodVisitor.visitVarInsn(25, context.var("lexer"));
        methodVisitor.visitMethodInsn(182, JSONLexerBase, INoCaptchaComponent.token, "()I");
        methodVisitor.visitFieldInsn(178, JSONToken, "SET", "I");
        methodVisitor.visitJumpInsn(160, label4);
        methodVisitor.visitVarInsn(25, context.var("lexer"));
        methodVisitor.visitFieldInsn(178, JSONToken, "LBRACKET", "I");
        methodVisitor.visitMethodInsn(182, JSONLexerBase, "nextToken", "(I)V");
        methodVisitor.visitVarInsn(25, context.var("lexer"));
        methodVisitor.visitMethodInsn(182, JSONLexerBase, INoCaptchaComponent.token, "()I");
        methodVisitor.visitFieldInsn(178, JSONToken, "LBRACKET", "I");
        methodVisitor.visitJumpInsn(160, label);
        _newCollection(methodVisitor, cls, i, true);
        methodVisitor.visitJumpInsn(167, label3);
        methodVisitor.visitLabel(label4);
        methodVisitor.visitVarInsn(25, context.var("lexer"));
        methodVisitor.visitMethodInsn(182, JSONLexerBase, INoCaptchaComponent.token, "()I");
        methodVisitor.visitFieldInsn(178, JSONToken, "LBRACKET", "I");
        methodVisitor.visitJumpInsn(160, label);
        _newCollection(methodVisitor, cls, i, false);
        methodVisitor.visitLabel(label3);
        methodVisitor.visitVarInsn(58, context.var(fieldInfo.name + "_asm"));
        _getCollectionFieldItemDeser(context, methodVisitor, fieldInfo, cls2);
        methodVisitor.visitMethodInsn(185, ASMUtils.type(ObjectDeserializer.class), "getFastMatchToken", "()I");
        methodVisitor.visitVarInsn(54, context.var("fastMatchToken"));
        methodVisitor.visitVarInsn(25, context.var("lexer"));
        methodVisitor.visitVarInsn(21, context.var("fastMatchToken"));
        methodVisitor.visitMethodInsn(182, JSONLexerBase, "nextToken", "(I)V");
        methodVisitor.visitVarInsn(25, 1);
        methodVisitor.visitMethodInsn(182, DefaultJSONParser, "getContext", "()" + ASMUtils.desc(ParseContext.class));
        methodVisitor.visitVarInsn(58, context.var("listContext"));
        methodVisitor.visitVarInsn(25, 1);
        methodVisitor.visitVarInsn(25, context.var(fieldInfo.name + "_asm"));
        methodVisitor.visitLdcInsn(fieldInfo.name);
        methodVisitor.visitMethodInsn(182, DefaultJSONParser, "setContext", "(Ljava/lang/Object;Ljava/lang/Object;)" + ASMUtils.desc(ParseContext.class));
        methodVisitor.visitInsn(87);
        label3 = new Label();
        label4 = new Label();
        methodVisitor.visitInsn(3);
        methodVisitor.visitVarInsn(54, context.var("i"));
        methodVisitor.visitLabel(label3);
        methodVisitor.visitVarInsn(25, context.var("lexer"));
        methodVisitor.visitMethodInsn(182, JSONLexerBase, INoCaptchaComponent.token, "()I");
        methodVisitor.visitFieldInsn(178, JSONToken, "RBRACKET", "I");
        methodVisitor.visitJumpInsn(159, label4);
        methodVisitor.visitVarInsn(25, 0);
        methodVisitor.visitFieldInsn(180, context.className, fieldInfo.name + "_asm_list_item_deser__", ASMUtils.desc(ObjectDeserializer.class));
        methodVisitor.visitVarInsn(25, 1);
        methodVisitor.visitLdcInsn(com.alibaba.fastjson.asm.Type.getType(ASMUtils.desc((Class) cls2)));
        methodVisitor.visitVarInsn(21, context.var("i"));
        methodVisitor.visitMethodInsn(184, "java/lang/Integer", "valueOf", "(I)Ljava/lang/Integer;");
        methodVisitor.visitMethodInsn(185, ASMUtils.type(ObjectDeserializer.class), "deserialze", "(L" + DefaultJSONParser + ";Ljava/lang/reflect/Type;Ljava/lang/Object;)Ljava/lang/Object;");
        methodVisitor.visitVarInsn(58, context.var("list_item_value"));
        methodVisitor.visitIincInsn(context.var("i"), 1);
        methodVisitor.visitVarInsn(25, context.var(fieldInfo.name + "_asm"));
        methodVisitor.visitVarInsn(25, context.var("list_item_value"));
        if (cls.isInterface()) {
            methodVisitor.visitMethodInsn(185, ASMUtils.type(cls), "add", "(Ljava/lang/Object;)Z");
        } else {
            methodVisitor.visitMethodInsn(182, ASMUtils.type(cls), "add", "(Ljava/lang/Object;)Z");
        }
        methodVisitor.visitInsn(87);
        methodVisitor.visitVarInsn(25, 1);
        methodVisitor.visitVarInsn(25, context.var(fieldInfo.name + "_asm"));
        methodVisitor.visitMethodInsn(182, DefaultJSONParser, "checkListResolve", "(Ljava/util/Collection;)V");
        methodVisitor.visitVarInsn(25, context.var("lexer"));
        methodVisitor.visitMethodInsn(182, JSONLexerBase, INoCaptchaComponent.token, "()I");
        methodVisitor.visitFieldInsn(178, JSONToken, "COMMA", "I");
        methodVisitor.visitJumpInsn(160, label3);
        methodVisitor.visitVarInsn(25, context.var("lexer"));
        methodVisitor.visitVarInsn(21, context.var("fastMatchToken"));
        methodVisitor.visitMethodInsn(182, JSONLexerBase, "nextToken", "(I)V");
        methodVisitor.visitJumpInsn(167, label3);
        methodVisitor.visitLabel(label4);
        methodVisitor.visitVarInsn(25, 1);
        methodVisitor.visitVarInsn(25, context.var("listContext"));
        methodVisitor.visitMethodInsn(182, DefaultJSONParser, "setContext", "(" + ASMUtils.desc(ParseContext.class) + ")V");
        methodVisitor.visitVarInsn(25, context.var("lexer"));
        methodVisitor.visitMethodInsn(182, JSONLexerBase, INoCaptchaComponent.token, "()I");
        methodVisitor.visitFieldInsn(178, JSONToken, "RBRACKET", "I");
        methodVisitor.visitJumpInsn(160, label);
        methodVisitor.visitVarInsn(25, context.var("lexer"));
        methodVisitor.visitFieldInsn(178, JSONToken, "COMMA", "I");
        methodVisitor.visitMethodInsn(182, JSONLexerBase, "nextToken", "(I)V");
        methodVisitor.visitLabel(label2);
    }

    private void _getCollectionFieldItemDeser(Context context, MethodVisitor methodVisitor, FieldInfo fieldInfo, Class<?> cls) {
        Label label = new Label();
        methodVisitor.visitVarInsn(25, 0);
        methodVisitor.visitFieldInsn(180, context.className, fieldInfo.name + "_asm_list_item_deser__", ASMUtils.desc(ObjectDeserializer.class));
        methodVisitor.visitJumpInsn(199, label);
        methodVisitor.visitVarInsn(25, 0);
        methodVisitor.visitVarInsn(25, 1);
        methodVisitor.visitMethodInsn(182, DefaultJSONParser, "getConfig", "()" + ASMUtils.desc(ParserConfig.class));
        methodVisitor.visitLdcInsn(com.alibaba.fastjson.asm.Type.getType(ASMUtils.desc((Class) cls)));
        methodVisitor.visitMethodInsn(182, ASMUtils.type(ParserConfig.class), "getDeserializer", "(Ljava/lang/reflect/Type;)" + ASMUtils.desc(ObjectDeserializer.class));
        methodVisitor.visitFieldInsn(181, context.className, fieldInfo.name + "_asm_list_item_deser__", ASMUtils.desc(ObjectDeserializer.class));
        methodVisitor.visitLabel(label);
        methodVisitor.visitVarInsn(25, 0);
        methodVisitor.visitFieldInsn(180, context.className, fieldInfo.name + "_asm_list_item_deser__", ASMUtils.desc(ObjectDeserializer.class));
    }

    private void _newCollection(MethodVisitor methodVisitor, Class<?> cls, int i, boolean z) {
        if (cls.isAssignableFrom(ArrayList.class) && !z) {
            methodVisitor.visitTypeInsn(187, "java/util/ArrayList");
            methodVisitor.visitInsn(89);
            methodVisitor.visitMethodInsn(183, "java/util/ArrayList", "<init>", "()V");
        } else if (cls.isAssignableFrom(LinkedList.class) && !z) {
            methodVisitor.visitTypeInsn(187, ASMUtils.type(LinkedList.class));
            methodVisitor.visitInsn(89);
            methodVisitor.visitMethodInsn(183, ASMUtils.type(LinkedList.class), "<init>", "()V");
        } else if (cls.isAssignableFrom(HashSet.class)) {
            methodVisitor.visitTypeInsn(187, ASMUtils.type(HashSet.class));
            methodVisitor.visitInsn(89);
            methodVisitor.visitMethodInsn(183, ASMUtils.type(HashSet.class), "<init>", "()V");
        } else if (cls.isAssignableFrom(TreeSet.class)) {
            methodVisitor.visitTypeInsn(187, ASMUtils.type(TreeSet.class));
            methodVisitor.visitInsn(89);
            methodVisitor.visitMethodInsn(183, ASMUtils.type(TreeSet.class), "<init>", "()V");
        } else if (cls.isAssignableFrom(LinkedHashSet.class)) {
            methodVisitor.visitTypeInsn(187, ASMUtils.type(LinkedHashSet.class));
            methodVisitor.visitInsn(89);
            methodVisitor.visitMethodInsn(183, ASMUtils.type(LinkedHashSet.class), "<init>", "()V");
        } else if (z) {
            methodVisitor.visitTypeInsn(187, ASMUtils.type(HashSet.class));
            methodVisitor.visitInsn(89);
            methodVisitor.visitMethodInsn(183, ASMUtils.type(HashSet.class), "<init>", "()V");
        } else {
            methodVisitor.visitVarInsn(25, 0);
            methodVisitor.visitLdcInsn(Integer.valueOf(i));
            methodVisitor.visitMethodInsn(182, ASMUtils.type(ASMJavaBeanDeserializer.class), "getFieldType", "(I)Ljava/lang/reflect/Type;");
            methodVisitor.visitMethodInsn(184, ASMUtils.type(TypeUtils.class), "createCollection", "(Ljava/lang/reflect/Type;)Ljava/util/Collection;");
        }
        methodVisitor.visitTypeInsn(192, ASMUtils.type(cls));
    }

    private void _deserialze_obj(Context context, MethodVisitor methodVisitor, Label label, FieldInfo fieldInfo, Class<?> cls, int i) {
        Label label2 = new Label();
        Label label3 = new Label();
        methodVisitor.visitVarInsn(25, context.var("lexer"));
        methodVisitor.visitVarInsn(25, 0);
        methodVisitor.visitFieldInsn(180, context.className, fieldInfo.name + "_asm_prefix__", "[C");
        methodVisitor.visitMethodInsn(182, JSONLexerBase, "matchField", "([C)Z");
        methodVisitor.visitJumpInsn(154, label2);
        methodVisitor.visitInsn(1);
        methodVisitor.visitVarInsn(58, context.var(fieldInfo.name + "_asm"));
        methodVisitor.visitJumpInsn(167, label3);
        methodVisitor.visitLabel(label2);
        _setFlag(methodVisitor, context, i);
        methodVisitor.visitVarInsn(21, context.var("matchedCount"));
        methodVisitor.visitInsn(4);
        methodVisitor.visitInsn(96);
        methodVisitor.visitVarInsn(54, context.var("matchedCount"));
        _deserObject(context, methodVisitor, fieldInfo, cls, i);
        methodVisitor.visitVarInsn(25, 1);
        methodVisitor.visitMethodInsn(182, DefaultJSONParser, "getResolveStatus", "()I");
        methodVisitor.visitFieldInsn(178, DefaultJSONParser, "NeedToResolve", "I");
        methodVisitor.visitJumpInsn(160, label3);
        methodVisitor.visitVarInsn(25, 1);
        methodVisitor.visitMethodInsn(182, DefaultJSONParser, "getLastResolveTask", "()" + ASMUtils.desc(ResolveTask.class));
        methodVisitor.visitVarInsn(58, context.var("resolveTask"));
        methodVisitor.visitVarInsn(25, context.var("resolveTask"));
        methodVisitor.visitVarInsn(25, 1);
        methodVisitor.visitMethodInsn(182, DefaultJSONParser, "getContext", "()" + ASMUtils.desc(ParseContext.class));
        methodVisitor.visitFieldInsn(181, ASMUtils.type(ResolveTask.class), "ownerContext", ASMUtils.desc(ParseContext.class));
        methodVisitor.visitVarInsn(25, context.var("resolveTask"));
        methodVisitor.visitVarInsn(25, 0);
        methodVisitor.visitLdcInsn(fieldInfo.name);
        methodVisitor.visitMethodInsn(182, ASMUtils.type(ASMJavaBeanDeserializer.class), "getFieldDeserializer", "(Ljava/lang/String;)" + ASMUtils.desc(FieldDeserializer.class));
        methodVisitor.visitFieldInsn(181, ASMUtils.type(ResolveTask.class), "fieldDeserializer", ASMUtils.desc(FieldDeserializer.class));
        methodVisitor.visitVarInsn(25, 1);
        methodVisitor.visitFieldInsn(178, DefaultJSONParser, "NONE", "I");
        methodVisitor.visitMethodInsn(182, DefaultJSONParser, "setResolveStatus", "(I)V");
        methodVisitor.visitLabel(label3);
    }

    private void _deserObject(Context context, MethodVisitor methodVisitor, FieldInfo fieldInfo, Class<?> cls, int i) {
        _getFieldDeser(context, methodVisitor, fieldInfo);
        methodVisitor.visitVarInsn(25, 1);
        if (fieldInfo.fieldType instanceof Class) {
            methodVisitor.visitLdcInsn(com.alibaba.fastjson.asm.Type.getType(ASMUtils.desc(fieldInfo.fieldClass)));
        } else {
            methodVisitor.visitVarInsn(25, 0);
            methodVisitor.visitLdcInsn(Integer.valueOf(i));
            methodVisitor.visitMethodInsn(182, ASMUtils.type(ASMJavaBeanDeserializer.class), "getFieldType", "(I)Ljava/lang/reflect/Type;");
        }
        methodVisitor.visitLdcInsn(fieldInfo.name);
        methodVisitor.visitMethodInsn(185, ASMUtils.type(ObjectDeserializer.class), "deserialze", "(L" + DefaultJSONParser + ";Ljava/lang/reflect/Type;Ljava/lang/Object;)Ljava/lang/Object;");
        methodVisitor.visitTypeInsn(192, ASMUtils.type(cls));
        methodVisitor.visitVarInsn(58, context.var(fieldInfo.name + "_asm"));
    }

    private void _getFieldDeser(Context context, MethodVisitor methodVisitor, FieldInfo fieldInfo) {
        Label label = new Label();
        methodVisitor.visitVarInsn(25, 0);
        methodVisitor.visitFieldInsn(180, context.className, fieldInfo.name + "_asm_deser__", ASMUtils.desc(ObjectDeserializer.class));
        methodVisitor.visitJumpInsn(199, label);
        methodVisitor.visitVarInsn(25, 0);
        methodVisitor.visitVarInsn(25, 1);
        methodVisitor.visitMethodInsn(182, DefaultJSONParser, "getConfig", "()" + ASMUtils.desc(ParserConfig.class));
        methodVisitor.visitLdcInsn(com.alibaba.fastjson.asm.Type.getType(ASMUtils.desc(fieldInfo.fieldClass)));
        methodVisitor.visitMethodInsn(182, ASMUtils.type(ParserConfig.class), "getDeserializer", "(Ljava/lang/reflect/Type;)" + ASMUtils.desc(ObjectDeserializer.class));
        methodVisitor.visitFieldInsn(181, context.className, fieldInfo.name + "_asm_deser__", ASMUtils.desc(ObjectDeserializer.class));
        methodVisitor.visitLabel(label);
        methodVisitor.visitVarInsn(25, 0);
        methodVisitor.visitFieldInsn(180, context.className, fieldInfo.name + "_asm_deser__", ASMUtils.desc(ObjectDeserializer.class));
    }

    private void _init(ClassWriter classWriter, Context context) {
        for (FieldInfo fieldInfo : context.fieldInfoList) {
            new FieldWriter(classWriter, 1, fieldInfo.name + "_asm_prefix__", "[C").visitEnd();
        }
        for (FieldInfo fieldInfo2 : context.fieldInfoList) {
            Class cls = fieldInfo2.fieldClass;
            if (!(cls.isPrimitive() || cls.isEnum())) {
                if (Collection.class.isAssignableFrom(cls)) {
                    new FieldWriter(classWriter, 1, fieldInfo2.name + "_asm_list_item_deser__", ASMUtils.desc(ObjectDeserializer.class)).visitEnd();
                } else {
                    new FieldWriter(classWriter, 1, fieldInfo2.name + "_asm_deser__", ASMUtils.desc(ObjectDeserializer.class)).visitEnd();
                }
            }
        }
        MethodVisitor methodWriter = new MethodWriter(classWriter, 1, "<init>", "(" + ASMUtils.desc(ParserConfig.class) + "Ljava/lang/Class;)V", null, null);
        methodWriter.visitVarInsn(25, 0);
        methodWriter.visitVarInsn(25, 1);
        methodWriter.visitVarInsn(25, 2);
        methodWriter.visitMethodInsn(183, ASMUtils.type(ASMJavaBeanDeserializer.class), "<init>", "(" + ASMUtils.desc(ParserConfig.class) + "Ljava/lang/Class;)V");
        for (FieldInfo fieldInfo22 : context.fieldInfoList) {
            methodWriter.visitVarInsn(25, 0);
            methodWriter.visitLdcInsn("\"" + fieldInfo22.name + "\":");
            methodWriter.visitMethodInsn(182, "java/lang/String", "toCharArray", "()[C");
            methodWriter.visitFieldInsn(181, context.className, fieldInfo22.name + "_asm_prefix__", "[C");
        }
        methodWriter.visitInsn(177);
        methodWriter.visitMaxs(4, 4);
        methodWriter.visitEnd();
    }

    private void _createInstance(ClassWriter classWriter, Context context) {
        String str = "(L" + DefaultJSONParser + ";Ljava/lang/reflect/Type;)Ljava/lang/Object;";
        MethodVisitor methodWriter = new MethodWriter(classWriter, 1, "createInstance", str, null, null);
        methodWriter.visitTypeInsn(187, ASMUtils.type(context.getInstClass()));
        methodWriter.visitInsn(89);
        methodWriter.visitMethodInsn(183, ASMUtils.type(context.getInstClass()), "<init>", "()V");
        methodWriter.visitInsn(176);
        methodWriter.visitMaxs(3, 3);
        methodWriter.visitEnd();
    }
}
