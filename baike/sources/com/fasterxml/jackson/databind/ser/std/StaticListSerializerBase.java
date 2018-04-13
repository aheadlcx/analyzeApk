package com.fasterxml.jackson.databind.ser.std;

import com.fasterxml.jackson.annotation.JsonFormat.Feature;
import com.fasterxml.jackson.annotation.JsonFormat.Value;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonArrayFormatVisitor;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWrapper;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import java.lang.reflect.Type;
import java.util.Collection;

public abstract class StaticListSerializerBase<T extends Collection<?>> extends StdSerializer<T> implements ContextualSerializer {
    protected final JsonSerializer<String> _serializer;
    protected final Boolean _unwrapSingle;

    public abstract JsonSerializer<?> _withResolved(BeanProperty beanProperty, JsonSerializer<?> jsonSerializer, Boolean bool);

    protected abstract void acceptContentVisitor(JsonArrayFormatVisitor jsonArrayFormatVisitor) throws JsonMappingException;

    protected abstract JsonNode contentSchema();

    protected StaticListSerializerBase(Class<?> cls) {
        super(cls, false);
        this._serializer = null;
        this._unwrapSingle = null;
    }

    protected StaticListSerializerBase(StaticListSerializerBase<?> staticListSerializerBase, JsonSerializer<?> jsonSerializer, Boolean bool) {
        super((StdSerializer) staticListSerializerBase);
        this._serializer = jsonSerializer;
        this._unwrapSingle = bool;
    }

    public JsonSerializer<?> createContextual(SerializerProvider serializerProvider, BeanProperty beanProperty) throws JsonMappingException {
        JsonSerializer serializerInstance;
        Boolean feature;
        JsonSerializer jsonSerializer = null;
        if (beanProperty != null) {
            Value findPropertyFormat;
            AnnotationIntrospector annotationIntrospector = serializerProvider.getAnnotationIntrospector();
            Annotated member = beanProperty.getMember();
            if (member != null) {
                Object findContentSerializer = annotationIntrospector.findContentSerializer(member);
                if (findContentSerializer != null) {
                    serializerInstance = serializerProvider.serializerInstance(member, findContentSerializer);
                    findPropertyFormat = beanProperty.findPropertyFormat(serializerProvider.getConfig(), this._handledType);
                    feature = findPropertyFormat == null ? findPropertyFormat.getFeature(Feature.WRITE_SINGLE_ELEM_ARRAYS_UNWRAPPED) : null;
                }
            }
            serializerInstance = null;
            findPropertyFormat = beanProperty.findPropertyFormat(serializerProvider.getConfig(), this._handledType);
            if (findPropertyFormat == null) {
            }
        } else {
            feature = null;
            serializerInstance = null;
        }
        if (serializerInstance == null) {
            serializerInstance = this._serializer;
        }
        serializerInstance = findConvertingContentSerializer(serializerProvider, beanProperty, serializerInstance);
        if (serializerInstance == null) {
            serializerInstance = serializerProvider.findValueSerializer(String.class, beanProperty);
        } else {
            serializerInstance = serializerProvider.handleSecondaryContextualization(serializerInstance, beanProperty);
        }
        if (!isDefaultSerializer(serializerInstance)) {
            jsonSerializer = serializerInstance;
        }
        return (jsonSerializer == this._serializer && feature == this._unwrapSingle) ? this : _withResolved(beanProperty, jsonSerializer, feature);
    }

    @Deprecated
    public boolean isEmpty(T t) {
        return isEmpty(null, (Collection) t);
    }

    public boolean isEmpty(SerializerProvider serializerProvider, T t) {
        return t == null || t.size() == 0;
    }

    public JsonNode getSchema(SerializerProvider serializerProvider, Type type) {
        return createSchemaNode("array", true).set("items", contentSchema());
    }

    public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper jsonFormatVisitorWrapper, JavaType javaType) throws JsonMappingException {
        acceptContentVisitor(jsonFormatVisitorWrapper.expectArrayFormat(javaType));
    }
}
