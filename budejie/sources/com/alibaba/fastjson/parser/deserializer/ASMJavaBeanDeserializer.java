package com.alibaba.fastjson.parser.deserializer;

import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.JSONLexer;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.util.FieldInfo;
import com.alibaba.fastjson.util.JavaBeanInfo;
import java.lang.reflect.Type;

public abstract class ASMJavaBeanDeserializer implements ObjectDeserializer {
    private JavaBeanDeserializer serializer;

    public abstract Object createInstance(DefaultJSONParser defaultJSONParser, Type type);

    public ASMJavaBeanDeserializer(ParserConfig parserConfig, Class<?> cls) {
        this.serializer = new JavaBeanDeserializer(parserConfig, cls, cls);
    }

    public JavaBeanDeserializer getInnterSerializer() {
        return this.serializer;
    }

    public <T> T deserialze(DefaultJSONParser defaultJSONParser, Type type, Object obj) {
        return this.serializer.deserialze(defaultJSONParser, type, obj);
    }

    public int getFastMatchToken() {
        return this.serializer.getFastMatchToken();
    }

    public Object createInstance(DefaultJSONParser defaultJSONParser) {
        return this.serializer.createInstance(defaultJSONParser, this.serializer.clazz);
    }

    public FieldDeserializer createFieldDeserializer(ParserConfig parserConfig, JavaBeanInfo javaBeanInfo, FieldInfo fieldInfo) {
        return parserConfig.createFieldDeserializer(parserConfig, javaBeanInfo, fieldInfo);
    }

    public FieldDeserializer getFieldDeserializer(String str) {
        return this.serializer.getFieldDeserializer(str);
    }

    public Type getFieldType(int i) {
        return this.serializer.sortedFieldDeserializers[i].fieldInfo.fieldType;
    }

    public boolean isSupportArrayToBean(JSONLexer jSONLexer) {
        return this.serializer.isSupportArrayToBean(jSONLexer);
    }

    public Object parseRest(DefaultJSONParser defaultJSONParser, Type type, Object obj, Object obj2) {
        return this.serializer.deserialze(defaultJSONParser, type, obj, obj2);
    }

    public <T> T deserialzeArrayMapping(DefaultJSONParser defaultJSONParser, Type type, Object obj, Object obj2) {
        return this.serializer.deserialzeArrayMapping(defaultJSONParser, type, obj, obj2);
    }
}
