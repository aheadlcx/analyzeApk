package com.alibaba.fastjson.serializer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONAware;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONStreamAware;
import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.annotation.JSONType;
import com.alibaba.fastjson.parser.deserializer.Jdk8DateCodec;
import com.alibaba.fastjson.parser.deserializer.OptionalCodec;
import com.alibaba.fastjson.util.ASMUtils;
import com.alibaba.fastjson.util.IdentityHashMap;
import com.alibaba.fastjson.util.ServiceLoader;
import com.alibaba.fastjson.util.TypeUtils;
import java.io.File;
import java.io.Serializable;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.URL;
import java.nio.charset.Charset;
import java.sql.Clob;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Currency;
import java.util.Date;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicLongArray;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Pattern;

public class SerializeConfig extends IdentityHashMap<Type, ObjectSerializer> {
    private static boolean awtError = false;
    public static final SerializeConfig globalInstance = new SerializeConfig();
    private static boolean jdk8Error = false;
    private static boolean oracleJdbcError = false;
    private boolean asm;
    private ASMSerializerFactory asmFactory;
    private String typeKey;

    public String getTypeKey() {
        return this.typeKey;
    }

    public void setTypeKey(String str) {
        this.typeKey = str;
    }

    public final ObjectSerializer createASMSerializer(Class<?> cls) throws Exception {
        return this.asmFactory.createJavaBeanSerializer(cls, null);
    }

    public ObjectSerializer createJavaBeanSerializer(Class<?> cls) {
        boolean z = false;
        if (!Modifier.isPublic(cls.getModifiers())) {
            return new JavaBeanSerializer(cls);
        }
        boolean z2;
        boolean z3 = this.asm;
        if ((z3 && this.asmFactory.classLoader.isExternalClass(cls)) || cls == Serializable.class || cls == Object.class) {
            z2 = false;
        } else {
            z2 = z3;
        }
        JSONType jSONType = (JSONType) cls.getAnnotation(JSONType.class);
        if (!(jSONType == null || jSONType.asm())) {
            z2 = false;
        }
        if (z2 && !ASMUtils.checkName(cls.getName())) {
            z2 = false;
        }
        if (z2) {
            for (Field annotation : cls.getDeclaredFields()) {
                JSONField jSONField = (JSONField) annotation.getAnnotation(JSONField.class);
                if (jSONField != null && !ASMUtils.checkName(jSONField.name())) {
                    break;
                }
            }
        }
        z = z2;
        if (z) {
            try {
                ObjectSerializer createASMSerializer = createASMSerializer(cls);
                if (createASMSerializer != null) {
                    return createASMSerializer;
                }
            } catch (ClassCastException e) {
            } catch (Throwable th) {
                JSONException jSONException = new JSONException("create asm serializer error, class " + cls, th);
            }
        }
        return new JavaBeanSerializer(cls);
    }

    public boolean isAsmEnable() {
        return this.asm;
    }

    public void setAsmEnable(boolean z) {
        if (!ASMUtils.IS_ANDROID) {
            this.asm = z;
        }
    }

    public static SerializeConfig getGlobalInstance() {
        return globalInstance;
    }

    public SerializeConfig() {
        this(1024);
    }

    public SerializeConfig(int i) {
        super(i);
        this.asm = !ASMUtils.IS_ANDROID;
        this.typeKey = JSON.DEFAULT_TYPE_KEY;
        try {
            if (this.asm) {
                this.asmFactory = new ASMSerializerFactory();
            }
        } catch (NoClassDefFoundError e) {
            this.asm = false;
        } catch (ExceptionInInitializerError e2) {
            this.asm = false;
        }
        put(Boolean.class, BooleanCodec.instance);
        put(Character.class, CharacterCodec.instance);
        put(Byte.class, IntegerCodec.instance);
        put(Short.class, IntegerCodec.instance);
        put(Integer.class, IntegerCodec.instance);
        put(Long.class, LongCodec.instance);
        put(Float.class, FloatCodec.instance);
        put(Double.class, DoubleSerializer.instance);
        put(BigDecimal.class, BigDecimalCodec.instance);
        put(BigInteger.class, BigIntegerCodec.instance);
        put(String.class, StringCodec.instance);
        put(byte[].class, PrimitiveArraySerializer.instance);
        put(short[].class, PrimitiveArraySerializer.instance);
        put(int[].class, PrimitiveArraySerializer.instance);
        put(long[].class, PrimitiveArraySerializer.instance);
        put(float[].class, PrimitiveArraySerializer.instance);
        put(double[].class, PrimitiveArraySerializer.instance);
        put(boolean[].class, PrimitiveArraySerializer.instance);
        put(char[].class, PrimitiveArraySerializer.instance);
        put(Object[].class, ObjectArrayCodec.instance);
        put(Class.class, MiscCodec.instance);
        put(SimpleDateFormat.class, MiscCodec.instance);
        put(Locale.class, MiscCodec.instance);
        put(Currency.class, CurrencyCodec.instance);
        put(TimeZone.class, MiscCodec.instance);
        put(UUID.class, MiscCodec.instance);
        put(InetAddress.class, MiscCodec.instance);
        put(Inet4Address.class, MiscCodec.instance);
        put(Inet6Address.class, MiscCodec.instance);
        put(InetSocketAddress.class, MiscCodec.instance);
        put(File.class, MiscCodec.instance);
        put(URI.class, MiscCodec.instance);
        put(URL.class, MiscCodec.instance);
        put(Appendable.class, AppendableSerializer.instance);
        put(StringBuffer.class, AppendableSerializer.instance);
        put(StringBuilder.class, AppendableSerializer.instance);
        put(Pattern.class, MiscCodec.instance);
        put(Charset.class, CharsetCodec.instance);
        put(AtomicBoolean.class, AtomicCodec.instance);
        put(AtomicInteger.class, AtomicCodec.instance);
        put(AtomicLong.class, AtomicCodec.instance);
        put(AtomicReference.class, ReferenceCodec.instance);
        put(AtomicIntegerArray.class, AtomicCodec.instance);
        put(AtomicLongArray.class, AtomicCodec.instance);
        put(WeakReference.class, ReferenceCodec.instance);
        put(SoftReference.class, ReferenceCodec.instance);
        if (!awtError) {
            try {
                put(Class.forName("java.awt.Color"), AwtCodec.instance);
                put(Class.forName("java.awt.Font"), AwtCodec.instance);
                put(Class.forName("java.awt.Point"), AwtCodec.instance);
                put(Class.forName("java.awt.Rectangle"), AwtCodec.instance);
            } catch (Throwable th) {
                awtError = true;
            }
        }
        if (!jdk8Error) {
            try {
                put(Class.forName("java.time.LocalDateTime"), Jdk8DateCodec.instance);
                put(Class.forName("java.time.LocalDate"), Jdk8DateCodec.instance);
                put(Class.forName("java.time.LocalTime"), Jdk8DateCodec.instance);
                put(Class.forName("java.time.ZonedDateTime"), Jdk8DateCodec.instance);
                put(Class.forName("java.time.OffsetDateTime"), Jdk8DateCodec.instance);
                put(Class.forName("java.time.OffsetTime"), Jdk8DateCodec.instance);
                put(Class.forName("java.time.ZoneOffset"), Jdk8DateCodec.instance);
                put(Class.forName("java.time.ZoneRegion"), Jdk8DateCodec.instance);
                put(Class.forName("java.time.Period"), Jdk8DateCodec.instance);
                put(Class.forName("java.time.Duration"), Jdk8DateCodec.instance);
                put(Class.forName("java.time.Instant"), Jdk8DateCodec.instance);
                put(Class.forName("java.util.Optional"), OptionalCodec.instance);
                put(Class.forName("java.util.OptionalDouble"), OptionalCodec.instance);
                put(Class.forName("java.util.OptionalInt"), OptionalCodec.instance);
                put(Class.forName("java.util.OptionalLong"), OptionalCodec.instance);
            } catch (Throwable th2) {
                jdk8Error = true;
            }
        }
        if (!oracleJdbcError) {
            try {
                put(Class.forName("oracle.sql.DATE"), DateCodec.instance);
                put(Class.forName("oracle.sql.TIMESTAMP"), DateCodec.instance);
            } catch (Throwable th3) {
                oracleJdbcError = true;
            }
        }
    }

    public ObjectSerializer getObjectWriter(Class<?> cls) {
        Object obj;
        AutowiredObjectSerializer autowiredObjectSerializer;
        Object obj2 = null;
        ObjectSerializer objectSerializer = (ObjectSerializer) get(cls);
        if (objectSerializer == null) {
            try {
                for (Object obj3 : ServiceLoader.load(AutowiredObjectSerializer.class, Thread.currentThread().getContextClassLoader())) {
                    if (obj3 instanceof AutowiredObjectSerializer) {
                        autowiredObjectSerializer = (AutowiredObjectSerializer) obj3;
                        for (Type put : autowiredObjectSerializer.getAutowiredFor()) {
                            put(put, autowiredObjectSerializer);
                        }
                    }
                }
            } catch (ClassCastException e) {
            }
            objectSerializer = (ObjectSerializer) get(cls);
        }
        if (objectSerializer == null) {
            ClassLoader classLoader = JSON.class.getClassLoader();
            if (classLoader != Thread.currentThread().getContextClassLoader()) {
                try {
                    for (Object obj32 : ServiceLoader.load(AutowiredObjectSerializer.class, classLoader)) {
                        if (obj32 instanceof AutowiredObjectSerializer) {
                            autowiredObjectSerializer = (AutowiredObjectSerializer) obj32;
                            for (Type put2 : autowiredObjectSerializer.getAutowiredFor()) {
                                put(put2, autowiredObjectSerializer);
                            }
                        }
                    }
                } catch (ClassCastException e2) {
                }
                objectSerializer = (ObjectSerializer) get(cls);
            }
        }
        if (objectSerializer != null) {
            return objectSerializer;
        }
        if (Map.class.isAssignableFrom(cls)) {
            put(cls, MapSerializer.instance);
        } else if (List.class.isAssignableFrom(cls)) {
            put(cls, ListSerializer.instance);
        } else if (Collection.class.isAssignableFrom(cls)) {
            put(cls, CollectionCodec.instance);
        } else if (Date.class.isAssignableFrom(cls)) {
            put(cls, DateCodec.instance);
        } else if (JSONAware.class.isAssignableFrom(cls)) {
            put(cls, JSONAwareSerializer.instance);
        } else if (JSONSerializable.class.isAssignableFrom(cls)) {
            put(cls, JSONSerializableSerializer.instance);
        } else if (JSONStreamAware.class.isAssignableFrom(cls)) {
            put(cls, MiscCodec.instance);
        } else if (cls.isEnum() || (cls.getSuperclass() != null && cls.getSuperclass().isEnum())) {
            put(cls, EnumSerializer.instance);
        } else if (cls.isArray()) {
            Class componentType = cls.getComponentType();
            put(cls, new ArraySerializer(componentType, getObjectWriter(componentType)));
        } else if (Throwable.class.isAssignableFrom(cls)) {
            put(cls, new JavaBeanSerializer(cls, null, TypeUtils.getSerializeFeatures(cls) | SerializerFeature.WriteClassName.mask));
        } else if (TimeZone.class.isAssignableFrom(cls)) {
            put(cls, MiscCodec.instance);
        } else if (Appendable.class.isAssignableFrom(cls)) {
            put(cls, AppendableSerializer.instance);
        } else if (Charset.class.isAssignableFrom(cls)) {
            put(cls, CharsetCodec.instance);
        } else if (Enumeration.class.isAssignableFrom(cls)) {
            put(cls, EnumerationSerializer.instance);
        } else if (Calendar.class.isAssignableFrom(cls)) {
            put(cls, CalendarCodec.instance);
        } else if (Clob.class.isAssignableFrom(cls)) {
            put(cls, ClobSeriliazer.instance);
        } else if (Iterable.class.isAssignableFrom(cls) || Iterator.class.isAssignableFrom(cls)) {
            put(cls, MiscCodec.instance);
        } else {
            Class[] interfaces = cls.getInterfaces();
            int length = interfaces.length;
            int i = 0;
            while (i < length) {
                String name = interfaces[i].getName();
                if (name.equals("net.sf.cglib.proxy.Factory") || name.equals("org.springframework.cglib.proxy.Factory")) {
                    obj32 = null;
                    obj2 = 1;
                    break;
                } else if (name.equals("javassist.util.proxy.ProxyObject") || name.equals("org.apache.ibatis.javassist.util.proxy.ProxyObject")) {
                    i = 1;
                    break;
                } else {
                    i++;
                }
            }
            obj32 = null;
            if (obj2 == null && r0 == null) {
                put(cls, createJavaBeanSerializer(cls));
            } else {
                objectSerializer = getObjectWriter(cls.getSuperclass());
                put(cls, objectSerializer);
                return objectSerializer;
            }
        }
        return (ObjectSerializer) get(cls);
    }
}
