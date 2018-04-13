package com.fasterxml.jackson.databind.ser;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonInclude.Value;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.PropertyName;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.introspect.AnnotatedClass;
import com.fasterxml.jackson.databind.introspect.BeanPropertyDefinition;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.ser.impl.PropertySerializerMap;
import com.fasterxml.jackson.databind.util.Annotations;
import java.io.Serializable;

public abstract class VirtualBeanPropertyWriter extends BeanPropertyWriter implements Serializable {
    private static final long serialVersionUID = 1;

    protected abstract Object value(Object obj, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws Exception;

    public abstract VirtualBeanPropertyWriter withConfig(MapperConfig<?> mapperConfig, AnnotatedClass annotatedClass, BeanPropertyDefinition beanPropertyDefinition, JavaType javaType);

    protected VirtualBeanPropertyWriter(BeanPropertyDefinition beanPropertyDefinition, Annotations annotations, JavaType javaType) {
        this(beanPropertyDefinition, annotations, javaType, null, null, null, beanPropertyDefinition.findInclusion());
    }

    protected VirtualBeanPropertyWriter() {
    }

    protected VirtualBeanPropertyWriter(BeanPropertyDefinition beanPropertyDefinition, Annotations annotations, JavaType javaType, JsonSerializer<?> jsonSerializer, TypeSerializer typeSerializer, JavaType javaType2, Value value) {
        super(beanPropertyDefinition, beanPropertyDefinition.getPrimaryMember(), annotations, javaType, jsonSerializer, typeSerializer, javaType2, _suppressNulls(value), _suppressableValue(value));
    }

    protected VirtualBeanPropertyWriter(VirtualBeanPropertyWriter virtualBeanPropertyWriter) {
        super(virtualBeanPropertyWriter);
    }

    protected VirtualBeanPropertyWriter(VirtualBeanPropertyWriter virtualBeanPropertyWriter, PropertyName propertyName) {
        super((BeanPropertyWriter) virtualBeanPropertyWriter, propertyName);
    }

    protected static boolean _suppressNulls(Value value) {
        if (value == null) {
            return false;
        }
        Include valueInclusion = value.getValueInclusion();
        if (valueInclusion == Include.ALWAYS || valueInclusion == Include.USE_DEFAULTS) {
            return false;
        }
        return true;
    }

    protected static Object _suppressableValue(Value value) {
        if (value == null) {
            return Boolean.valueOf(false);
        }
        Include valueInclusion = value.getValueInclusion();
        if (valueInclusion == Include.ALWAYS || valueInclusion == Include.NON_NULL || valueInclusion == Include.USE_DEFAULTS) {
            return null;
        }
        return MARKER_FOR_EMPTY;
    }

    public boolean isVirtual() {
        return true;
    }

    public void serializeAsField(Object obj, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws Exception {
        Object value = value(obj, jsonGenerator, serializerProvider);
        if (value != null) {
            JsonSerializer jsonSerializer = this._serializer;
            if (jsonSerializer == null) {
                Class cls = value.getClass();
                PropertySerializerMap propertySerializerMap = this._dynamicSerializers;
                jsonSerializer = propertySerializerMap.serializerFor(cls);
                if (jsonSerializer == null) {
                    jsonSerializer = _findAndAddDynamic(propertySerializerMap, cls, serializerProvider);
                }
            }
            if (this._suppressableValue != null) {
                if (MARKER_FOR_EMPTY == this._suppressableValue) {
                    if (jsonSerializer.isEmpty(serializerProvider, value)) {
                        return;
                    }
                } else if (this._suppressableValue.equals(value)) {
                    return;
                }
            }
            if (value != obj || !_handleSelfReference(obj, jsonGenerator, serializerProvider, jsonSerializer)) {
                jsonGenerator.writeFieldName(this._name);
                if (this._typeSerializer == null) {
                    jsonSerializer.serialize(value, jsonGenerator, serializerProvider);
                } else {
                    jsonSerializer.serializeWithType(value, jsonGenerator, serializerProvider, this._typeSerializer);
                }
            }
        } else if (this._nullSerializer != null) {
            jsonGenerator.writeFieldName(this._name);
            this._nullSerializer.serialize(null, jsonGenerator, serializerProvider);
        }
    }

    public void serializeAsElement(Object obj, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws Exception {
        Object value = value(obj, jsonGenerator, serializerProvider);
        if (value != null) {
            JsonSerializer jsonSerializer = this._serializer;
            if (jsonSerializer == null) {
                Class cls = value.getClass();
                PropertySerializerMap propertySerializerMap = this._dynamicSerializers;
                jsonSerializer = propertySerializerMap.serializerFor(cls);
                if (jsonSerializer == null) {
                    jsonSerializer = _findAndAddDynamic(propertySerializerMap, cls, serializerProvider);
                }
            }
            if (this._suppressableValue != null) {
                if (MARKER_FOR_EMPTY == this._suppressableValue) {
                    if (jsonSerializer.isEmpty(serializerProvider, value)) {
                        serializeAsPlaceholder(obj, jsonGenerator, serializerProvider);
                        return;
                    }
                } else if (this._suppressableValue.equals(value)) {
                    serializeAsPlaceholder(obj, jsonGenerator, serializerProvider);
                    return;
                }
            }
            if (value != obj || !_handleSelfReference(obj, jsonGenerator, serializerProvider, jsonSerializer)) {
                if (this._typeSerializer == null) {
                    jsonSerializer.serialize(value, jsonGenerator, serializerProvider);
                } else {
                    jsonSerializer.serializeWithType(value, jsonGenerator, serializerProvider, this._typeSerializer);
                }
            }
        } else if (this._nullSerializer != null) {
            this._nullSerializer.serialize(null, jsonGenerator, serializerProvider);
        } else {
            jsonGenerator.writeNull();
        }
    }
}
