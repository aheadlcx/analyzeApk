package com.alibaba.fastjson.serializer;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.util.FieldInfo;
import java.io.IOException;
import java.lang.reflect.Member;
import java.util.Collection;
import org.apache.commons.httpclient.HttpState;

public class FieldSerializer implements Comparable<FieldSerializer> {
    private final String double_quoted_fieldPrefix;
    protected int features;
    protected BeanContext fieldContext;
    public final FieldInfo fieldInfo;
    private String format;
    private RuntimeSerializerInfo runtimeInfo;
    private String single_quoted_fieldPrefix;
    private String un_quoted_fieldPrefix;
    protected boolean writeEnumUsingName = false;
    protected boolean writeEnumUsingToString = false;
    protected final boolean writeNull;
    protected boolean writeNullBooleanAsFalse = false;
    protected boolean writeNullListAsEmpty = false;
    protected boolean writeNullStringAsEmpty = false;
    protected boolean writeNumberAsZero = false;

    static class RuntimeSerializerInfo {
        ObjectSerializer fieldSerializer;
        Class<?> runtimeFieldClass;

        public RuntimeSerializerInfo(ObjectSerializer objectSerializer, Class<?> cls) {
            this.fieldSerializer = objectSerializer;
            this.runtimeFieldClass = cls;
        }
    }

    public FieldSerializer(Class<?> cls, FieldInfo fieldInfo) {
        boolean z;
        int i = 0;
        this.fieldInfo = fieldInfo;
        this.fieldContext = new BeanContext(cls, fieldInfo);
        fieldInfo.setAccessible();
        this.double_quoted_fieldPrefix = '\"' + fieldInfo.name + "\":";
        JSONField annotation = fieldInfo.getAnnotation();
        if (annotation != null) {
            SerializerFeature serializerFeature;
            for (SerializerFeature serializerFeature2 : annotation.serialzeFeatures()) {
                if (serializerFeature2 == SerializerFeature.WriteMapNullValue) {
                    z = true;
                    break;
                }
            }
            z = false;
            this.format = annotation.format();
            if (this.format.trim().length() == 0) {
                this.format = null;
            }
            SerializerFeature[] serialzeFeatures = annotation.serialzeFeatures();
            int length = serialzeFeatures.length;
            while (i < length) {
                serializerFeature2 = serialzeFeatures[i];
                if (serializerFeature2 == SerializerFeature.WriteNullNumberAsZero) {
                    this.writeNumberAsZero = true;
                } else if (serializerFeature2 == SerializerFeature.WriteNullStringAsEmpty) {
                    this.writeNullStringAsEmpty = true;
                } else if (serializerFeature2 == SerializerFeature.WriteNullBooleanAsFalse) {
                    this.writeNullBooleanAsFalse = true;
                } else if (serializerFeature2 == SerializerFeature.WriteNullListAsEmpty) {
                    this.writeNullListAsEmpty = true;
                } else if (serializerFeature2 == SerializerFeature.WriteEnumUsingToString) {
                    this.writeEnumUsingToString = true;
                } else if (serializerFeature2 == SerializerFeature.WriteEnumUsingName) {
                    this.writeEnumUsingName = true;
                }
                i++;
            }
            this.features = SerializerFeature.of(annotation.serialzeFeatures());
        } else {
            z = false;
        }
        this.writeNull = z;
    }

    public void writePrefix(JSONSerializer jSONSerializer) throws IOException {
        SerializeWriter serializeWriter = jSONSerializer.out;
        if (!serializeWriter.quoteFieldNames) {
            if (this.un_quoted_fieldPrefix == null) {
                this.un_quoted_fieldPrefix = this.fieldInfo.name + ":";
            }
            serializeWriter.write(this.un_quoted_fieldPrefix);
        } else if (serializeWriter.useSingleQuotes) {
            if (this.single_quoted_fieldPrefix == null) {
                this.single_quoted_fieldPrefix = '\'' + this.fieldInfo.name + "':";
            }
            serializeWriter.write(this.single_quoted_fieldPrefix);
        } else {
            serializeWriter.write(this.double_quoted_fieldPrefix);
        }
    }

    public Object getPropertyValue(Object obj) throws Exception {
        try {
            return this.fieldInfo.get(obj);
        } catch (Throwable e) {
            Member member = this.fieldInfo.getMember();
            throw new JSONException("get property error。 " + (member.getDeclaringClass().getName() + "." + member.getName()), e);
        }
    }

    public int compareTo(FieldSerializer fieldSerializer) {
        return this.fieldInfo.compareTo(fieldSerializer.fieldInfo);
    }

    public void writeValue(JSONSerializer jSONSerializer, Object obj) throws Exception {
        if (this.format != null) {
            jSONSerializer.writeWithFormat(obj, this.format);
            return;
        }
        Class cls;
        if (this.runtimeInfo == null) {
            if (obj == null) {
                cls = this.fieldInfo.fieldClass;
            } else {
                cls = obj.getClass();
            }
            this.runtimeInfo = new RuntimeSerializerInfo(jSONSerializer.getObjectWriter(cls), cls);
        }
        RuntimeSerializerInfo runtimeSerializerInfo = this.runtimeInfo;
        int i = this.fieldInfo.serialzeFeatures;
        if (obj == null) {
            Class cls2 = runtimeSerializerInfo.runtimeFieldClass;
            SerializeWriter serializeWriter = jSONSerializer.out;
            Object obj2 = (this.writeNumberAsZero || (serializeWriter.features & SerializerFeature.WriteNullNumberAsZero.mask) != 0) ? 1 : null;
            if (obj2 != null && Number.class.isAssignableFrom(cls2)) {
                serializeWriter.write(48);
                return;
            } else if (this.writeNullStringAsEmpty && String.class == cls2) {
                serializeWriter.write("\"\"");
                return;
            } else if (this.writeNullBooleanAsFalse && Boolean.class == cls2) {
                serializeWriter.write(HttpState.PREEMPTIVE_DEFAULT);
                return;
            } else if (this.writeNullListAsEmpty && Collection.class.isAssignableFrom(cls2)) {
                serializeWriter.write("[]");
                return;
            } else {
                ObjectSerializer objectSerializer = runtimeSerializerInfo.fieldSerializer;
                if (serializeWriter.writeMapNullValue && (objectSerializer instanceof ASMJavaBeanSerializer)) {
                    serializeWriter.writeNull();
                    return;
                } else {
                    objectSerializer.write(jSONSerializer, null, this.fieldInfo.name, this.fieldInfo.fieldType, i);
                    return;
                }
            }
        }
        if (this.fieldInfo.isEnum) {
            if (this.writeEnumUsingName) {
                jSONSerializer.out.writeString(((Enum) obj).name());
                return;
            } else if (this.writeEnumUsingToString) {
                jSONSerializer.out.writeString(((Enum) obj).toString());
                return;
            }
        }
        cls = obj.getClass();
        if (cls == runtimeSerializerInfo.runtimeFieldClass) {
            runtimeSerializerInfo.fieldSerializer.write(jSONSerializer, obj, this.fieldInfo.name, this.fieldInfo.fieldType, i);
        } else {
            jSONSerializer.getObjectWriter(cls).write(jSONSerializer, obj, this.fieldInfo.name, this.fieldInfo.fieldType, i);
        }
    }
}
