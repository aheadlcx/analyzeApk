package com.fasterxml.jackson.databind;

import com.fasterxml.jackson.annotation.JsonFormat.Value;
import com.fasterxml.jackson.annotation.ObjectIdGenerator;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.cfg.ContextAttributes;
import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.ResolvableSerializer;
import com.fasterxml.jackson.databind.ser.SerializerCache;
import com.fasterxml.jackson.databind.ser.SerializerFactory;
import com.fasterxml.jackson.databind.ser.impl.FailingSerializer;
import com.fasterxml.jackson.databind.ser.impl.ReadOnlyClassToSerializerMap;
import com.fasterxml.jackson.databind.ser.impl.TypeWrappedSerializer;
import com.fasterxml.jackson.databind.ser.impl.UnknownSerializer;
import com.fasterxml.jackson.databind.ser.impl.WritableObjectId;
import com.fasterxml.jackson.databind.ser.std.NullSerializer;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.ClassUtil;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public abstract class SerializerProvider extends DatabindContext {
    protected static final boolean CACHE_UNKNOWN_MAPPINGS = false;
    public static final JsonSerializer<Object> DEFAULT_NULL_KEY_SERIALIZER = new FailingSerializer("Null key for a Map not allowed in JSON (use a converting NullKeySerializer?)");
    protected static final JsonSerializer<Object> DEFAULT_UNKNOWN_SERIALIZER = new UnknownSerializer();
    protected transient ContextAttributes _attributes;
    protected final SerializationConfig _config;
    protected DateFormat _dateFormat;
    protected JsonSerializer<Object> _keySerializer;
    protected final ReadOnlyClassToSerializerMap _knownSerializers;
    protected JsonSerializer<Object> _nullKeySerializer;
    protected JsonSerializer<Object> _nullValueSerializer;
    protected final Class<?> _serializationView;
    protected final SerializerCache _serializerCache;
    protected final SerializerFactory _serializerFactory;
    protected final boolean _stdNullValueSerializer;
    protected JsonSerializer<Object> _unknownTypeSerializer;

    public abstract WritableObjectId findObjectId(Object obj, ObjectIdGenerator<?> objectIdGenerator);

    public abstract JsonSerializer<Object> serializerInstance(Annotated annotated, Object obj) throws JsonMappingException;

    public SerializerProvider() {
        this._unknownTypeSerializer = DEFAULT_UNKNOWN_SERIALIZER;
        this._nullValueSerializer = NullSerializer.instance;
        this._nullKeySerializer = DEFAULT_NULL_KEY_SERIALIZER;
        this._config = null;
        this._serializerFactory = null;
        this._serializerCache = new SerializerCache();
        this._knownSerializers = null;
        this._serializationView = null;
        this._attributes = null;
        this._stdNullValueSerializer = true;
    }

    protected SerializerProvider(SerializerProvider serializerProvider, SerializationConfig serializationConfig, SerializerFactory serializerFactory) {
        this._unknownTypeSerializer = DEFAULT_UNKNOWN_SERIALIZER;
        this._nullValueSerializer = NullSerializer.instance;
        this._nullKeySerializer = DEFAULT_NULL_KEY_SERIALIZER;
        if (serializationConfig == null) {
            throw new NullPointerException();
        }
        this._serializerFactory = serializerFactory;
        this._config = serializationConfig;
        this._serializerCache = serializerProvider._serializerCache;
        this._unknownTypeSerializer = serializerProvider._unknownTypeSerializer;
        this._keySerializer = serializerProvider._keySerializer;
        this._nullValueSerializer = serializerProvider._nullValueSerializer;
        this._nullKeySerializer = serializerProvider._nullKeySerializer;
        this._stdNullValueSerializer = this._nullValueSerializer == DEFAULT_NULL_KEY_SERIALIZER;
        this._serializationView = serializationConfig.getActiveView();
        this._attributes = serializationConfig.getAttributes();
        this._knownSerializers = this._serializerCache.getReadOnlyLookupMap();
    }

    protected SerializerProvider(SerializerProvider serializerProvider) {
        this._unknownTypeSerializer = DEFAULT_UNKNOWN_SERIALIZER;
        this._nullValueSerializer = NullSerializer.instance;
        this._nullKeySerializer = DEFAULT_NULL_KEY_SERIALIZER;
        this._config = null;
        this._serializationView = null;
        this._serializerFactory = null;
        this._knownSerializers = null;
        this._serializerCache = new SerializerCache();
        this._unknownTypeSerializer = serializerProvider._unknownTypeSerializer;
        this._keySerializer = serializerProvider._keySerializer;
        this._nullValueSerializer = serializerProvider._nullValueSerializer;
        this._nullKeySerializer = serializerProvider._nullKeySerializer;
        this._stdNullValueSerializer = serializerProvider._stdNullValueSerializer;
    }

    public void setDefaultKeySerializer(JsonSerializer<Object> jsonSerializer) {
        if (jsonSerializer == null) {
            throw new IllegalArgumentException("Can not pass null JsonSerializer");
        }
        this._keySerializer = jsonSerializer;
    }

    public void setNullValueSerializer(JsonSerializer<Object> jsonSerializer) {
        if (jsonSerializer == null) {
            throw new IllegalArgumentException("Can not pass null JsonSerializer");
        }
        this._nullValueSerializer = jsonSerializer;
    }

    public void setNullKeySerializer(JsonSerializer<Object> jsonSerializer) {
        if (jsonSerializer == null) {
            throw new IllegalArgumentException("Can not pass null JsonSerializer");
        }
        this._nullKeySerializer = jsonSerializer;
    }

    public final SerializationConfig getConfig() {
        return this._config;
    }

    public final AnnotationIntrospector getAnnotationIntrospector() {
        return this._config.getAnnotationIntrospector();
    }

    public final TypeFactory getTypeFactory() {
        return this._config.getTypeFactory();
    }

    public final Class<?> getActiveView() {
        return this._serializationView;
    }

    @Deprecated
    public final Class<?> getSerializationView() {
        return this._serializationView;
    }

    public final boolean canOverrideAccessModifiers() {
        return this._config.canOverrideAccessModifiers();
    }

    public final boolean isEnabled(MapperFeature mapperFeature) {
        return this._config.isEnabled(mapperFeature);
    }

    public final Value getDefaultPropertyFormat(Class<?> cls) {
        return this._config.getDefaultPropertyFormat(cls);
    }

    public Locale getLocale() {
        return this._config.getLocale();
    }

    public TimeZone getTimeZone() {
        return this._config.getTimeZone();
    }

    public Object getAttribute(Object obj) {
        return this._attributes.getAttribute(obj);
    }

    public SerializerProvider setAttribute(Object obj, Object obj2) {
        this._attributes = this._attributes.withPerCallAttribute(obj, obj2);
        return this;
    }

    public final boolean isEnabled(SerializationFeature serializationFeature) {
        return this._config.isEnabled(serializationFeature);
    }

    public final boolean hasSerializationFeatures(int i) {
        return this._config.hasSerializationFeatures(i);
    }

    public final FilterProvider getFilterProvider() {
        return this._config.getFilterProvider();
    }

    public JsonSerializer<Object> findValueSerializer(Class<?> cls, BeanProperty beanProperty) throws JsonMappingException {
        JsonSerializer untypedValueSerializer = this._knownSerializers.untypedValueSerializer((Class) cls);
        if (untypedValueSerializer == null) {
            untypedValueSerializer = this._serializerCache.untypedValueSerializer((Class) cls);
            if (untypedValueSerializer == null) {
                untypedValueSerializer = this._serializerCache.untypedValueSerializer(this._config.constructType((Class) cls));
                if (untypedValueSerializer == null) {
                    untypedValueSerializer = _createAndCacheUntypedSerializer((Class) cls);
                    if (untypedValueSerializer == null) {
                        return getUnknownTypeSerializer(cls);
                    }
                }
            }
        }
        return handleSecondaryContextualization(untypedValueSerializer, beanProperty);
    }

    public JsonSerializer<Object> findValueSerializer(JavaType javaType, BeanProperty beanProperty) throws JsonMappingException {
        JsonSerializer untypedValueSerializer = this._knownSerializers.untypedValueSerializer(javaType);
        if (untypedValueSerializer == null) {
            untypedValueSerializer = this._serializerCache.untypedValueSerializer(javaType);
            if (untypedValueSerializer == null) {
                untypedValueSerializer = _createAndCacheUntypedSerializer(javaType);
                if (untypedValueSerializer == null) {
                    return getUnknownTypeSerializer(javaType.getRawClass());
                }
            }
        }
        return handleSecondaryContextualization(untypedValueSerializer, beanProperty);
    }

    public JsonSerializer<Object> findValueSerializer(Class<?> cls) throws JsonMappingException {
        JsonSerializer<Object> untypedValueSerializer = this._knownSerializers.untypedValueSerializer((Class) cls);
        if (untypedValueSerializer != null) {
            return untypedValueSerializer;
        }
        untypedValueSerializer = this._serializerCache.untypedValueSerializer((Class) cls);
        if (untypedValueSerializer != null) {
            return untypedValueSerializer;
        }
        untypedValueSerializer = this._serializerCache.untypedValueSerializer(this._config.constructType((Class) cls));
        if (untypedValueSerializer != null) {
            return untypedValueSerializer;
        }
        untypedValueSerializer = _createAndCacheUntypedSerializer((Class) cls);
        if (untypedValueSerializer == null) {
            return getUnknownTypeSerializer(cls);
        }
        return untypedValueSerializer;
    }

    public JsonSerializer<Object> findValueSerializer(JavaType javaType) throws JsonMappingException {
        JsonSerializer<Object> untypedValueSerializer = this._knownSerializers.untypedValueSerializer(javaType);
        if (untypedValueSerializer != null) {
            return untypedValueSerializer;
        }
        untypedValueSerializer = this._serializerCache.untypedValueSerializer(javaType);
        if (untypedValueSerializer != null) {
            return untypedValueSerializer;
        }
        untypedValueSerializer = _createAndCacheUntypedSerializer(javaType);
        if (untypedValueSerializer == null) {
            return getUnknownTypeSerializer(javaType.getRawClass());
        }
        return untypedValueSerializer;
    }

    public JsonSerializer<Object> findPrimaryPropertySerializer(JavaType javaType, BeanProperty beanProperty) throws JsonMappingException {
        JsonSerializer untypedValueSerializer = this._knownSerializers.untypedValueSerializer(javaType);
        if (untypedValueSerializer == null) {
            untypedValueSerializer = this._serializerCache.untypedValueSerializer(javaType);
            if (untypedValueSerializer == null) {
                untypedValueSerializer = _createAndCacheUntypedSerializer(javaType);
                if (untypedValueSerializer == null) {
                    return getUnknownTypeSerializer(javaType.getRawClass());
                }
            }
        }
        return handlePrimaryContextualization(untypedValueSerializer, beanProperty);
    }

    public JsonSerializer<Object> findPrimaryPropertySerializer(Class<?> cls, BeanProperty beanProperty) throws JsonMappingException {
        JsonSerializer untypedValueSerializer = this._knownSerializers.untypedValueSerializer((Class) cls);
        if (untypedValueSerializer == null) {
            untypedValueSerializer = this._serializerCache.untypedValueSerializer((Class) cls);
            if (untypedValueSerializer == null) {
                untypedValueSerializer = this._serializerCache.untypedValueSerializer(this._config.constructType((Class) cls));
                if (untypedValueSerializer == null) {
                    untypedValueSerializer = _createAndCacheUntypedSerializer((Class) cls);
                    if (untypedValueSerializer == null) {
                        return getUnknownTypeSerializer(cls);
                    }
                }
            }
        }
        return handlePrimaryContextualization(untypedValueSerializer, beanProperty);
    }

    public JsonSerializer<Object> findTypedValueSerializer(Class<?> cls, boolean z, BeanProperty beanProperty) throws JsonMappingException {
        JsonSerializer<Object> typedValueSerializer = this._knownSerializers.typedValueSerializer((Class) cls);
        if (typedValueSerializer == null) {
            typedValueSerializer = this._serializerCache.typedValueSerializer((Class) cls);
            if (typedValueSerializer == null) {
                JsonSerializer<Object> findValueSerializer = findValueSerializer((Class) cls, beanProperty);
                TypeSerializer createTypeSerializer = this._serializerFactory.createTypeSerializer(this._config, this._config.constructType((Class) cls));
                if (createTypeSerializer != null) {
                    typedValueSerializer = new TypeWrappedSerializer(createTypeSerializer.forProperty(beanProperty), findValueSerializer);
                } else {
                    typedValueSerializer = findValueSerializer;
                }
                if (z) {
                    this._serializerCache.addTypedSerializer((Class) cls, r0);
                }
            }
        }
        return typedValueSerializer;
    }

    public JsonSerializer<Object> findTypedValueSerializer(JavaType javaType, boolean z, BeanProperty beanProperty) throws JsonMappingException {
        JsonSerializer<Object> typedValueSerializer = this._knownSerializers.typedValueSerializer(javaType);
        if (typedValueSerializer == null) {
            typedValueSerializer = this._serializerCache.typedValueSerializer(javaType);
            if (typedValueSerializer == null) {
                JsonSerializer<Object> findValueSerializer = findValueSerializer(javaType, beanProperty);
                TypeSerializer createTypeSerializer = this._serializerFactory.createTypeSerializer(this._config, javaType);
                if (createTypeSerializer != null) {
                    typedValueSerializer = new TypeWrappedSerializer(createTypeSerializer.forProperty(beanProperty), findValueSerializer);
                } else {
                    typedValueSerializer = findValueSerializer;
                }
                if (z) {
                    this._serializerCache.addTypedSerializer(javaType, r0);
                }
            }
        }
        return typedValueSerializer;
    }

    public TypeSerializer findTypeSerializer(JavaType javaType) throws JsonMappingException {
        return this._serializerFactory.createTypeSerializer(this._config, javaType);
    }

    public JsonSerializer<Object> findKeySerializer(JavaType javaType, BeanProperty beanProperty) throws JsonMappingException {
        return _handleContextualResolvable(this._serializerFactory.createKeySerializer(this._config, javaType, this._keySerializer), beanProperty);
    }

    public JsonSerializer<Object> findKeySerializer(Class<?> cls, BeanProperty beanProperty) throws JsonMappingException {
        return findKeySerializer(this._config.constructType((Class) cls), beanProperty);
    }

    public JsonSerializer<Object> getDefaultNullKeySerializer() {
        return this._nullKeySerializer;
    }

    public JsonSerializer<Object> getDefaultNullValueSerializer() {
        return this._nullValueSerializer;
    }

    public JsonSerializer<Object> findNullKeySerializer(JavaType javaType, BeanProperty beanProperty) throws JsonMappingException {
        return this._nullKeySerializer;
    }

    public JsonSerializer<Object> findNullValueSerializer(BeanProperty beanProperty) throws JsonMappingException {
        return this._nullValueSerializer;
    }

    public JsonSerializer<Object> getUnknownTypeSerializer(Class<?> cls) {
        if (cls == Object.class) {
            return this._unknownTypeSerializer;
        }
        return new UnknownSerializer(cls);
    }

    public boolean isUnknownTypeSerializer(JsonSerializer<?> jsonSerializer) {
        if (jsonSerializer == this._unknownTypeSerializer || jsonSerializer == null) {
            return true;
        }
        if (isEnabled(SerializationFeature.FAIL_ON_EMPTY_BEANS) && jsonSerializer.getClass() == UnknownSerializer.class) {
            return true;
        }
        return false;
    }

    public JsonSerializer<?> handlePrimaryContextualization(JsonSerializer<?> jsonSerializer, BeanProperty beanProperty) throws JsonMappingException {
        if (jsonSerializer == null || !(jsonSerializer instanceof ContextualSerializer)) {
            return jsonSerializer;
        }
        return ((ContextualSerializer) jsonSerializer).createContextual(this, beanProperty);
    }

    public JsonSerializer<?> handleSecondaryContextualization(JsonSerializer<?> jsonSerializer, BeanProperty beanProperty) throws JsonMappingException {
        if (jsonSerializer == null || !(jsonSerializer instanceof ContextualSerializer)) {
            return jsonSerializer;
        }
        return ((ContextualSerializer) jsonSerializer).createContextual(this, beanProperty);
    }

    public final void defaultSerializeValue(Object obj, JsonGenerator jsonGenerator) throws IOException {
        if (obj != null) {
            findTypedValueSerializer(obj.getClass(), true, null).serialize(obj, jsonGenerator, this);
        } else if (this._stdNullValueSerializer) {
            jsonGenerator.writeNull();
        } else {
            this._nullValueSerializer.serialize(null, jsonGenerator, this);
        }
    }

    public final void defaultSerializeField(String str, Object obj, JsonGenerator jsonGenerator) throws IOException {
        jsonGenerator.writeFieldName(str);
        if (obj != null) {
            findTypedValueSerializer(obj.getClass(), true, null).serialize(obj, jsonGenerator, this);
        } else if (this._stdNullValueSerializer) {
            jsonGenerator.writeNull();
        } else {
            this._nullValueSerializer.serialize(null, jsonGenerator, this);
        }
    }

    public final void defaultSerializeDateValue(long j, JsonGenerator jsonGenerator) throws IOException {
        if (isEnabled(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)) {
            jsonGenerator.writeNumber(j);
        } else {
            jsonGenerator.writeString(_dateFormat().format(new Date(j)));
        }
    }

    public final void defaultSerializeDateValue(Date date, JsonGenerator jsonGenerator) throws IOException {
        if (isEnabled(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)) {
            jsonGenerator.writeNumber(date.getTime());
        } else {
            jsonGenerator.writeString(_dateFormat().format(date));
        }
    }

    public void defaultSerializeDateKey(long j, JsonGenerator jsonGenerator) throws IOException {
        if (isEnabled(SerializationFeature.WRITE_DATE_KEYS_AS_TIMESTAMPS)) {
            jsonGenerator.writeFieldName(String.valueOf(j));
        } else {
            jsonGenerator.writeFieldName(_dateFormat().format(new Date(j)));
        }
    }

    public void defaultSerializeDateKey(Date date, JsonGenerator jsonGenerator) throws IOException {
        if (isEnabled(SerializationFeature.WRITE_DATE_KEYS_AS_TIMESTAMPS)) {
            jsonGenerator.writeFieldName(String.valueOf(date.getTime()));
        } else {
            jsonGenerator.writeFieldName(_dateFormat().format(date));
        }
    }

    public final void defaultSerializeNull(JsonGenerator jsonGenerator) throws IOException {
        if (this._stdNullValueSerializer) {
            jsonGenerator.writeNull();
        } else {
            this._nullValueSerializer.serialize(null, jsonGenerator, this);
        }
    }

    public JsonMappingException mappingException(String str, Object... objArr) {
        if (objArr != null && objArr.length > 0) {
            str = String.format(str, objArr);
        }
        return JsonMappingException.from(this, str);
    }

    protected void _reportIncompatibleRootType(Object obj, JavaType javaType) throws IOException {
        if (!javaType.isPrimitive() || !ClassUtil.wrapperType(javaType.getRawClass()).isAssignableFrom(obj.getClass())) {
            throw JsonMappingException.from(this, "Incompatible types: declared root type (" + javaType + ") vs " + obj.getClass().getName());
        }
    }

    protected JsonSerializer<Object> _findExplicitUntypedSerializer(Class<?> cls) throws JsonMappingException {
        JsonSerializer<Object> untypedValueSerializer = this._knownSerializers.untypedValueSerializer((Class) cls);
        if (untypedValueSerializer == null) {
            untypedValueSerializer = this._serializerCache.untypedValueSerializer((Class) cls);
            if (untypedValueSerializer == null) {
                untypedValueSerializer = _createAndCacheUntypedSerializer((Class) cls);
            }
        }
        if (isUnknownTypeSerializer(untypedValueSerializer)) {
            return null;
        }
        return untypedValueSerializer;
    }

    protected JsonSerializer<Object> _createAndCacheUntypedSerializer(Class<?> cls) throws JsonMappingException {
        JavaType constructType = this._config.constructType((Class) cls);
        try {
            JsonSerializer<Object> _createUntypedSerializer = _createUntypedSerializer(constructType);
            if (_createUntypedSerializer != null) {
                this._serializerCache.addAndResolveNonTypedSerializer(cls, constructType, _createUntypedSerializer, this);
            }
            return _createUntypedSerializer;
        } catch (Throwable e) {
            throw JsonMappingException.from(this, e.getMessage(), e);
        }
    }

    protected JsonSerializer<Object> _createAndCacheUntypedSerializer(JavaType javaType) throws JsonMappingException {
        try {
            JsonSerializer _createUntypedSerializer = _createUntypedSerializer(javaType);
            if (_createUntypedSerializer != null) {
                this._serializerCache.addAndResolveNonTypedSerializer(javaType, _createUntypedSerializer, this);
            }
            return _createUntypedSerializer;
        } catch (Throwable e) {
            throw JsonMappingException.from(this, e.getMessage(), e);
        }
    }

    protected JsonSerializer<Object> _createUntypedSerializer(JavaType javaType) throws JsonMappingException {
        JsonSerializer<Object> createSerializer;
        synchronized (this._serializerCache) {
            createSerializer = this._serializerFactory.createSerializer(this, javaType);
        }
        return createSerializer;
    }

    protected JsonSerializer<Object> _handleContextualResolvable(JsonSerializer<?> jsonSerializer, BeanProperty beanProperty) throws JsonMappingException {
        if (jsonSerializer instanceof ResolvableSerializer) {
            ((ResolvableSerializer) jsonSerializer).resolve(this);
        }
        return handleSecondaryContextualization(jsonSerializer, beanProperty);
    }

    protected JsonSerializer<Object> _handleResolvable(JsonSerializer<?> jsonSerializer) throws JsonMappingException {
        if (jsonSerializer instanceof ResolvableSerializer) {
            ((ResolvableSerializer) jsonSerializer).resolve(this);
        }
        return jsonSerializer;
    }

    protected final DateFormat _dateFormat() {
        if (this._dateFormat != null) {
            return this._dateFormat;
        }
        DateFormat dateFormat = (DateFormat) this._config.getDateFormat().clone();
        this._dateFormat = dateFormat;
        return dateFormat;
    }
}
