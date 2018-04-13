package com.fasterxml.jackson.databind.ser.std;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.RuntimeJsonMappingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonSerialize.Typing;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWrapper;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import com.fasterxml.jackson.databind.ser.impl.PropertySerializerMap;
import com.fasterxml.jackson.databind.type.ReferenceType;
import com.fasterxml.jackson.databind.util.NameTransformer;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;

public class AtomicReferenceSerializer extends StdSerializer<AtomicReference<?>> implements ContextualSerializer {
    private static final long serialVersionUID = 1;
    protected final Include _contentInclusion;
    protected transient PropertySerializerMap _dynamicSerializers;
    protected final BeanProperty _property;
    protected final JavaType _referredType;
    protected final NameTransformer _unwrapper;
    protected final JsonSerializer<Object> _valueSerializer;
    protected final TypeSerializer _valueTypeSerializer;

    public AtomicReferenceSerializer(ReferenceType referenceType, boolean z, TypeSerializer typeSerializer, JsonSerializer<Object> jsonSerializer) {
        super((JavaType) referenceType);
        this._referredType = referenceType.getReferencedType();
        this._property = null;
        this._valueTypeSerializer = typeSerializer;
        this._valueSerializer = jsonSerializer;
        this._unwrapper = null;
        this._contentInclusion = null;
        this._dynamicSerializers = PropertySerializerMap.emptyForProperties();
    }

    protected AtomicReferenceSerializer(AtomicReferenceSerializer atomicReferenceSerializer, BeanProperty beanProperty, TypeSerializer typeSerializer, JsonSerializer<?> jsonSerializer, NameTransformer nameTransformer, Include include) {
        super((StdSerializer) atomicReferenceSerializer);
        this._referredType = atomicReferenceSerializer._referredType;
        this._dynamicSerializers = atomicReferenceSerializer._dynamicSerializers;
        this._property = beanProperty;
        this._valueTypeSerializer = typeSerializer;
        this._valueSerializer = jsonSerializer;
        this._unwrapper = nameTransformer;
        if (include == Include.USE_DEFAULTS || include == Include.ALWAYS) {
            this._contentInclusion = null;
        } else {
            this._contentInclusion = include;
        }
    }

    public JsonSerializer<AtomicReference<?>> unwrappingSerializer(NameTransformer nameTransformer) {
        JsonSerializer jsonSerializer = this._valueSerializer;
        if (jsonSerializer != null) {
            jsonSerializer = jsonSerializer.unwrappingSerializer(nameTransformer);
        }
        return withResolved(this._property, this._valueTypeSerializer, jsonSerializer, this._unwrapper == null ? nameTransformer : NameTransformer.chainedTransformer(nameTransformer, this._unwrapper), this._contentInclusion);
    }

    protected AtomicReferenceSerializer withResolved(BeanProperty beanProperty, TypeSerializer typeSerializer, JsonSerializer<?> jsonSerializer, NameTransformer nameTransformer, Include include) {
        return (this._property == beanProperty && include == this._contentInclusion && this._valueTypeSerializer == typeSerializer && this._valueSerializer == jsonSerializer && this._unwrapper == nameTransformer) ? this : new AtomicReferenceSerializer(this, beanProperty, typeSerializer, jsonSerializer, nameTransformer, include);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.fasterxml.jackson.databind.JsonSerializer<?> createContextual(com.fasterxml.jackson.databind.SerializerProvider r7, com.fasterxml.jackson.databind.BeanProperty r8) throws com.fasterxml.jackson.databind.JsonMappingException {
        /*
        r6 = this;
        r2 = r6._valueTypeSerializer;
        if (r2 == 0) goto L_0x0008;
    L_0x0004:
        r2 = r2.forProperty(r8);
    L_0x0008:
        r3 = r6._valueSerializer;
        if (r3 != 0) goto L_0x003b;
    L_0x000c:
        r0 = r6._referredType;
        r0 = r6._useStatic(r7, r8, r0);
        if (r0 == 0) goto L_0x001a;
    L_0x0014:
        r0 = r6._referredType;
        r3 = r6._findSerializer(r7, r0, r8);
    L_0x001a:
        r0 = r6._contentInclusion;
        if (r8 == 0) goto L_0x0040;
    L_0x001e:
        r1 = r7.getConfig();
        r4 = java.util.concurrent.atomic.AtomicReference.class;
        r1 = r8.findPropertyInclusion(r1, r4);
        r5 = r1.getContentInclusion();
        if (r5 == r0) goto L_0x0040;
    L_0x002e:
        r1 = com.fasterxml.jackson.annotation.JsonInclude.Include.USE_DEFAULTS;
        if (r5 == r1) goto L_0x0040;
    L_0x0032:
        r4 = r6._unwrapper;
        r0 = r6;
        r1 = r8;
        r0 = r0.withResolved(r1, r2, r3, r4, r5);
        return r0;
    L_0x003b:
        r3 = r7.handlePrimaryContextualization(r3, r8);
        goto L_0x001a;
    L_0x0040:
        r5 = r0;
        goto L_0x0032;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.ser.std.AtomicReferenceSerializer.createContextual(com.fasterxml.jackson.databind.SerializerProvider, com.fasterxml.jackson.databind.BeanProperty):com.fasterxml.jackson.databind.JsonSerializer<?>");
    }

    protected boolean _useStatic(SerializerProvider serializerProvider, BeanProperty beanProperty, JavaType javaType) {
        if (javaType.isJavaLangObject()) {
            return false;
        }
        if (javaType.isFinal()) {
            return true;
        }
        if (javaType.useStaticType()) {
            return true;
        }
        AnnotationIntrospector annotationIntrospector = serializerProvider.getAnnotationIntrospector();
        if (!(annotationIntrospector == null || beanProperty == null || beanProperty.getMember() == null)) {
            Typing findSerializationTyping = annotationIntrospector.findSerializationTyping(beanProperty.getMember());
            if (findSerializationTyping == Typing.STATIC) {
                return true;
            }
            if (findSerializationTyping == Typing.DYNAMIC) {
                return false;
            }
        }
        return serializerProvider.isEnabled(MapperFeature.USE_STATIC_TYPING);
    }

    public boolean isEmpty(SerializerProvider serializerProvider, AtomicReference<?> atomicReference) {
        if (atomicReference == null) {
            return true;
        }
        Object obj = atomicReference.get();
        if (obj == null) {
            return true;
        }
        if (this._contentInclusion == null) {
            return false;
        }
        JsonSerializer jsonSerializer = this._valueSerializer;
        if (jsonSerializer == null) {
            try {
                jsonSerializer = _findCachedSerializer(serializerProvider, atomicReference.getClass());
            } catch (JsonMappingException e) {
                throw new RuntimeJsonMappingException(e);
            }
        }
        return jsonSerializer.isEmpty(serializerProvider, obj);
    }

    public boolean isUnwrappingSerializer() {
        return this._unwrapper != null;
    }

    public void serialize(AtomicReference<?> atomicReference, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        Object obj = atomicReference.get();
        if (obj != null) {
            JsonSerializer jsonSerializer = this._valueSerializer;
            if (jsonSerializer == null) {
                jsonSerializer = _findCachedSerializer(serializerProvider, obj.getClass());
            }
            if (this._valueTypeSerializer != null) {
                jsonSerializer.serializeWithType(obj, jsonGenerator, serializerProvider, this._valueTypeSerializer);
            } else {
                jsonSerializer.serialize(obj, jsonGenerator, serializerProvider);
            }
        } else if (this._unwrapper == null) {
            serializerProvider.defaultSerializeNull(jsonGenerator);
        }
    }

    public void serializeWithType(AtomicReference<?> atomicReference, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, TypeSerializer typeSerializer) throws IOException {
        if (atomicReference.get() != null) {
            typeSerializer.writeTypePrefixForScalar(atomicReference, jsonGenerator);
            serialize((AtomicReference) atomicReference, jsonGenerator, serializerProvider);
            typeSerializer.writeTypeSuffixForScalar(atomicReference, jsonGenerator);
        } else if (this._unwrapper == null) {
            serializerProvider.defaultSerializeNull(jsonGenerator);
        }
    }

    public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper jsonFormatVisitorWrapper, JavaType javaType) throws JsonMappingException {
        JsonSerializer jsonSerializer = this._valueSerializer;
        if (jsonSerializer == null) {
            jsonSerializer = _findSerializer(jsonFormatVisitorWrapper.getProvider(), this._referredType, this._property);
            if (this._unwrapper != null) {
                jsonSerializer = jsonSerializer.unwrappingSerializer(this._unwrapper);
            }
        }
        jsonSerializer.acceptJsonFormatVisitor(jsonFormatVisitorWrapper, this._referredType);
    }

    private final JsonSerializer<Object> _findCachedSerializer(SerializerProvider serializerProvider, Class<?> cls) throws JsonMappingException {
        JsonSerializer<Object> serializerFor = this._dynamicSerializers.serializerFor(cls);
        if (serializerFor == null) {
            serializerFor = _findSerializer(serializerProvider, (Class) cls, this._property);
            if (this._unwrapper != null) {
                serializerFor = serializerFor.unwrappingSerializer(this._unwrapper);
            }
            this._dynamicSerializers = this._dynamicSerializers.newWith(cls, serializerFor);
        }
        return serializerFor;
    }

    private final JsonSerializer<Object> _findSerializer(SerializerProvider serializerProvider, Class<?> cls, BeanProperty beanProperty) throws JsonMappingException {
        return serializerProvider.findTypedValueSerializer((Class) cls, true, beanProperty);
    }

    private final JsonSerializer<Object> _findSerializer(SerializerProvider serializerProvider, JavaType javaType, BeanProperty beanProperty) throws JsonMappingException {
        return serializerProvider.findTypedValueSerializer(javaType, true, beanProperty);
    }
}
