package com.alibaba.fastjson.serializer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONAware;
import com.alibaba.fastjson.JSONStreamAware;
import com.alibaba.fastjson.PropertyNamingStrategy;
import com.alibaba.fastjson.util.IdentityHashMap;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URI;
import java.net.URL;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.AbstractSequentialList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Currency;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.UUID;
import java.util.regex.Pattern;

public class SerializeConfig {
    public static final SerializeConfig globalInstance = new SerializeConfig();
    public PropertyNamingStrategy propertyNamingStrategy;
    private final IdentityHashMap<ObjectSerializer> serializers = new IdentityHashMap(1024);
    protected String typeKey = JSON.DEFAULT_TYPE_KEY;

    public static final SerializeConfig getGlobalInstance() {
        return globalInstance;
    }

    public ObjectSerializer registerIfNotExists(Class<?> cls) {
        return registerIfNotExists(cls, cls.getModifiers(), false, true, true, true);
    }

    public ObjectSerializer registerIfNotExists(Class<?> cls, int i, boolean z, boolean z2, boolean z3, boolean z4) {
        ObjectSerializer objectSerializer = (ObjectSerializer) this.serializers.get(cls);
        if (objectSerializer != null) {
            return objectSerializer;
        }
        objectSerializer = new JavaBeanSerializer(cls, i, null, z, z2, z3, z4, this.propertyNamingStrategy);
        this.serializers.put(cls, objectSerializer);
        return objectSerializer;
    }

    public SerializeConfig() {
        this.serializers.put(Boolean.class, BooleanCodec.instance);
        this.serializers.put(Character.class, MiscCodec.instance);
        this.serializers.put(Byte.class, IntegerCodec.instance);
        this.serializers.put(Short.class, IntegerCodec.instance);
        this.serializers.put(Integer.class, IntegerCodec.instance);
        this.serializers.put(Long.class, IntegerCodec.instance);
        this.serializers.put(Float.class, NumberCodec.instance);
        this.serializers.put(Double.class, NumberCodec.instance);
        this.serializers.put(Number.class, NumberCodec.instance);
        this.serializers.put(BigDecimal.class, BigDecimalCodec.instance);
        this.serializers.put(BigInteger.class, BigDecimalCodec.instance);
        this.serializers.put(String.class, StringCodec.instance);
        this.serializers.put(Object[].class, ArrayCodec.instance);
        this.serializers.put(Class.class, MiscCodec.instance);
        this.serializers.put(SimpleDateFormat.class, MiscCodec.instance);
        this.serializers.put(Locale.class, MiscCodec.instance);
        this.serializers.put(Currency.class, MiscCodec.instance);
        this.serializers.put(TimeZone.class, MiscCodec.instance);
        this.serializers.put(UUID.class, MiscCodec.instance);
        this.serializers.put(URI.class, MiscCodec.instance);
        this.serializers.put(URL.class, MiscCodec.instance);
        this.serializers.put(Pattern.class, MiscCodec.instance);
        this.serializers.put(Charset.class, MiscCodec.instance);
    }

    public ObjectSerializer get(Class<?> cls) {
        Object obj = null;
        ObjectSerializer objectSerializer = (ObjectSerializer) this.serializers.get(cls);
        if (objectSerializer != null) {
            return objectSerializer;
        }
        IdentityHashMap identityHashMap;
        if (Map.class.isAssignableFrom(cls)) {
            identityHashMap = this.serializers;
            objectSerializer = new MapSerializer();
            identityHashMap.put(cls, objectSerializer);
        } else if (AbstractSequentialList.class.isAssignableFrom(cls)) {
            identityHashMap = this.serializers;
            objectSerializer = CollectionCodec.instance;
            identityHashMap.put(cls, objectSerializer);
        } else if (List.class.isAssignableFrom(cls)) {
            identityHashMap = this.serializers;
            objectSerializer = new ListSerializer();
            identityHashMap.put(cls, objectSerializer);
        } else if (Collection.class.isAssignableFrom(cls)) {
            identityHashMap = this.serializers;
            objectSerializer = CollectionCodec.instance;
            identityHashMap.put(cls, objectSerializer);
        } else if (Date.class.isAssignableFrom(cls)) {
            identityHashMap = this.serializers;
            objectSerializer = DateCodec.instance;
            identityHashMap.put(cls, objectSerializer);
        } else if (JSONAware.class.isAssignableFrom(cls)) {
            identityHashMap = this.serializers;
            objectSerializer = MiscCodec.instance;
            identityHashMap.put(cls, objectSerializer);
        } else if (JSONSerializable.class.isAssignableFrom(cls)) {
            identityHashMap = this.serializers;
            objectSerializer = MiscCodec.instance;
            identityHashMap.put(cls, objectSerializer);
        } else if (JSONStreamAware.class.isAssignableFrom(cls)) {
            identityHashMap = this.serializers;
            objectSerializer = MiscCodec.instance;
            identityHashMap.put(cls, objectSerializer);
        } else {
            if (!cls.isEnum()) {
                Class superclass = cls.getSuperclass();
                if (superclass == null || superclass == Object.class || !superclass.isEnum()) {
                    if (cls.isArray()) {
                        Class componentType = cls.getComponentType();
                        ObjectSerializer objectSerializer2 = get(componentType);
                        IdentityHashMap identityHashMap2 = this.serializers;
                        objectSerializer = new ArraySerializer(componentType, objectSerializer2);
                        identityHashMap2.put(cls, objectSerializer);
                    } else if (Throwable.class.isAssignableFrom(cls)) {
                        objectSerializer = new JavaBeanSerializer((Class) cls, this.propertyNamingStrategy);
                        objectSerializer.features |= SerializerFeature.WriteClassName.mask;
                        this.serializers.put(cls, objectSerializer);
                    } else if (TimeZone.class.isAssignableFrom(cls)) {
                        identityHashMap = this.serializers;
                        objectSerializer = MiscCodec.instance;
                        identityHashMap.put(cls, objectSerializer);
                    } else if (Charset.class.isAssignableFrom(cls)) {
                        identityHashMap = this.serializers;
                        objectSerializer = MiscCodec.instance;
                        identityHashMap.put(cls, objectSerializer);
                    } else if (Enumeration.class.isAssignableFrom(cls)) {
                        identityHashMap = this.serializers;
                        objectSerializer = MiscCodec.instance;
                        identityHashMap.put(cls, objectSerializer);
                    } else if (Calendar.class.isAssignableFrom(cls)) {
                        identityHashMap = this.serializers;
                        objectSerializer = DateCodec.instance;
                        identityHashMap.put(cls, objectSerializer);
                    } else {
                        Object obj2;
                        Class[] interfaces = cls.getInterfaces();
                        int length = interfaces.length;
                        int i = 0;
                        while (i < length) {
                            Class cls2 = interfaces[i];
                            if (cls2.getName().equals("net.sf.cglib.proxy.Factory") || cls2.getName().equals("org.springframework.cglib.proxy.Factory")) {
                                obj2 = null;
                                obj = 1;
                                break;
                            } else if (cls2.getName().equals("javassist.util.proxy.ProxyObject")) {
                                i = 1;
                                break;
                            } else {
                                i++;
                            }
                        }
                        obj2 = null;
                        if (obj == null && r0 == null) {
                            if (cls.getName().startsWith("android.net.Uri$")) {
                                objectSerializer = MiscCodec.instance;
                            } else {
                                objectSerializer = new JavaBeanSerializer((Class) cls, this.propertyNamingStrategy);
                            }
                            this.serializers.put(cls, objectSerializer);
                        } else {
                            objectSerializer = get(cls.getSuperclass());
                            this.serializers.put(cls, objectSerializer);
                            return objectSerializer;
                        }
                    }
                }
            }
            identityHashMap = this.serializers;
            objectSerializer = new EnumSerializer();
            identityHashMap.put(cls, objectSerializer);
        }
        if (objectSerializer == null) {
            return (ObjectSerializer) this.serializers.get(cls);
        }
        return objectSerializer;
    }

    public boolean put(Type type, ObjectSerializer objectSerializer) {
        return this.serializers.put(type, objectSerializer);
    }

    public String getTypeKey() {
        return this.typeKey;
    }

    public void setTypeKey(String str) {
        this.typeKey = str;
    }
}
