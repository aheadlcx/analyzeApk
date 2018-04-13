package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.io.NumberInput;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.KeyDeserializer;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;
import com.fasterxml.jackson.databind.util.ClassUtil;
import com.fasterxml.jackson.databind.util.EnumResolver;
import com.tencent.bugly.Bugly;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.net.URI;
import java.net.URL;
import java.util.Calendar;
import java.util.Currency;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

@JacksonStdImpl
public class StdKeyDeserializer extends KeyDeserializer implements Serializable {
    public static final int TYPE_BOOLEAN = 1;
    public static final int TYPE_BYTE = 2;
    public static final int TYPE_CALENDAR = 11;
    public static final int TYPE_CHAR = 4;
    public static final int TYPE_CLASS = 15;
    public static final int TYPE_CURRENCY = 16;
    public static final int TYPE_DATE = 10;
    public static final int TYPE_DOUBLE = 8;
    public static final int TYPE_FLOAT = 7;
    public static final int TYPE_INT = 5;
    public static final int TYPE_LOCALE = 9;
    public static final int TYPE_LONG = 6;
    public static final int TYPE_SHORT = 3;
    public static final int TYPE_URI = 13;
    public static final int TYPE_URL = 14;
    public static final int TYPE_UUID = 12;
    private static final long serialVersionUID = 1;
    protected final FromStringDeserializer<?> _deser;
    protected final Class<?> _keyClass;
    protected final int _kind;

    static final class DelegatingKD extends KeyDeserializer implements Serializable {
        private static final long serialVersionUID = 1;
        protected final JsonDeserializer<?> _delegate;
        protected final Class<?> _keyClass;

        protected DelegatingKD(Class<?> cls, JsonDeserializer<?> jsonDeserializer) {
            this._keyClass = cls;
            this._delegate = jsonDeserializer;
        }

        public final Object deserializeKey(String str, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
            if (str == null) {
                return null;
            }
            try {
                Object deserialize = this._delegate.deserialize(deserializationContext.getParser(), deserializationContext);
                if (deserialize != null) {
                    return deserialize;
                }
                throw deserializationContext.weirdKeyException(this._keyClass, str, "not a valid representation");
            } catch (Exception e) {
                throw deserializationContext.weirdKeyException(this._keyClass, str, "not a valid representation: " + e.getMessage());
            }
        }

        public Class<?> getKeyClass() {
            return this._keyClass;
        }
    }

    @JacksonStdImpl
    static final class EnumKD extends StdKeyDeserializer {
        private static final long serialVersionUID = 1;
        protected final AnnotatedMethod _factory;
        protected final EnumResolver _resolver;

        protected EnumKD(EnumResolver enumResolver, AnnotatedMethod annotatedMethod) {
            super(-1, enumResolver.getEnumClass());
            this._resolver = enumResolver;
            this._factory = annotatedMethod;
        }

        public Object _parse(String str, DeserializationContext deserializationContext) throws JsonMappingException {
            Object call1;
            if (this._factory != null) {
                try {
                    call1 = this._factory.call1(str);
                } catch (Throwable e) {
                    ClassUtil.unwrapAndThrowAsIAE(e);
                }
                return call1;
            }
            call1 = this._resolver.findEnum(str);
            if (call1 == null && !deserializationContext.getConfig().isEnabled(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL)) {
                throw deserializationContext.weirdKeyException(this._keyClass, str, "not one of values for Enum class");
            }
            return call1;
        }
    }

    static final class StringCtorKeyDeserializer extends StdKeyDeserializer {
        private static final long serialVersionUID = 1;
        protected final Constructor<?> _ctor;

        public StringCtorKeyDeserializer(Constructor<?> constructor) {
            super(-1, constructor.getDeclaringClass());
            this._ctor = constructor;
        }

        public Object _parse(String str, DeserializationContext deserializationContext) throws Exception {
            return this._ctor.newInstance(new Object[]{str});
        }
    }

    static final class StringFactoryKeyDeserializer extends StdKeyDeserializer {
        private static final long serialVersionUID = 1;
        final Method _factoryMethod;

        public StringFactoryKeyDeserializer(Method method) {
            super(-1, method.getDeclaringClass());
            this._factoryMethod = method;
        }

        public Object _parse(String str, DeserializationContext deserializationContext) throws Exception {
            return this._factoryMethod.invoke(null, new Object[]{str});
        }
    }

    @JacksonStdImpl
    static final class StringKD extends StdKeyDeserializer {
        private static final StringKD sObject = new StringKD(Object.class);
        private static final StringKD sString = new StringKD(String.class);
        private static final long serialVersionUID = 1;

        private StringKD(Class<?> cls) {
            super(-1, cls);
        }

        public static StringKD forType(Class<?> cls) {
            if (cls == String.class) {
                return sString;
            }
            if (cls == Object.class) {
                return sObject;
            }
            return new StringKD(cls);
        }

        public Object deserializeKey(String str, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
            return str;
        }
    }

    protected StdKeyDeserializer(int i, Class<?> cls) {
        this(i, cls, null);
    }

    protected StdKeyDeserializer(int i, Class<?> cls, FromStringDeserializer<?> fromStringDeserializer) {
        this._kind = i;
        this._keyClass = cls;
        this._deser = fromStringDeserializer;
    }

    public static StdKeyDeserializer forType(Class<?> cls) {
        if (cls == String.class || cls == Object.class) {
            return StringKD.forType(cls);
        }
        int i;
        if (cls == UUID.class) {
            i = 12;
        } else if (cls == Integer.class) {
            i = 5;
        } else if (cls == Long.class) {
            i = 6;
        } else if (cls == Date.class) {
            i = 10;
        } else if (cls == Calendar.class) {
            i = 11;
        } else if (cls == Boolean.class) {
            i = 1;
        } else if (cls == Byte.class) {
            i = 2;
        } else if (cls == Character.class) {
            i = 4;
        } else if (cls == Short.class) {
            i = 3;
        } else if (cls == Float.class) {
            i = 7;
        } else if (cls == Double.class) {
            i = 8;
        } else if (cls == URI.class) {
            i = 13;
        } else if (cls == URL.class) {
            i = 14;
        } else if (cls == Class.class) {
            i = 15;
        } else if (cls == Locale.class) {
            return new StdKeyDeserializer(9, cls, FromStringDeserializer.findDeserializer(Locale.class));
        } else {
            if (cls == Currency.class) {
                return new StdKeyDeserializer(16, cls, FromStringDeserializer.findDeserializer(Currency.class));
            }
            return null;
        }
        return new StdKeyDeserializer(i, cls);
    }

    public Object deserializeKey(String str, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        if (str == null) {
            return null;
        }
        try {
            Object _parse = _parse(str, deserializationContext);
            if (_parse != null) {
                return _parse;
            }
            if (this._keyClass.isEnum() && deserializationContext.getConfig().isEnabled(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL)) {
                return null;
            }
            throw deserializationContext.weirdKeyException(this._keyClass, str, "not a valid representation");
        } catch (Exception e) {
            throw deserializationContext.weirdKeyException(this._keyClass, str, "not a valid representation: " + e.getMessage());
        }
    }

    public Class<?> getKeyClass() {
        return this._keyClass;
    }

    protected Object _parse(String str, DeserializationContext deserializationContext) throws Exception {
        int _parseInt;
        switch (this._kind) {
            case 1:
                if ("true".equals(str)) {
                    return Boolean.TRUE;
                }
                if (Bugly.SDK_IS_DEV.equals(str)) {
                    return Boolean.FALSE;
                }
                throw deserializationContext.weirdKeyException(this._keyClass, str, "value not 'true' or 'false'");
            case 2:
                _parseInt = _parseInt(str);
                if (_parseInt >= -128 && _parseInt <= 255) {
                    return Byte.valueOf((byte) _parseInt);
                }
                throw deserializationContext.weirdKeyException(this._keyClass, str, "overflow, value can not be represented as 8-bit value");
            case 3:
                _parseInt = _parseInt(str);
                if (_parseInt >= -32768 && _parseInt <= 32767) {
                    return Short.valueOf((short) _parseInt);
                }
                throw deserializationContext.weirdKeyException(this._keyClass, str, "overflow, value can not be represented as 16-bit value");
            case 4:
                if (str.length() == 1) {
                    return Character.valueOf(str.charAt(0));
                }
                throw deserializationContext.weirdKeyException(this._keyClass, str, "can only convert 1-character Strings");
            case 5:
                return Integer.valueOf(_parseInt(str));
            case 6:
                return Long.valueOf(_parseLong(str));
            case 7:
                return Float.valueOf((float) _parseDouble(str));
            case 8:
                return Double.valueOf(_parseDouble(str));
            case 9:
                try {
                    return this._deser._deserialize(str, deserializationContext);
                } catch (IOException e) {
                    throw deserializationContext.weirdKeyException(this._keyClass, str, "unable to parse key as locale");
                }
            case 10:
                return deserializationContext.parseDate(str);
            case 11:
                Date parseDate = deserializationContext.parseDate(str);
                if (parseDate != null) {
                    return deserializationContext.constructCalendar(parseDate);
                }
                return null;
            case 12:
                return UUID.fromString(str);
            case 13:
                return URI.create(str);
            case 14:
                return new URL(str);
            case 15:
                try {
                    return deserializationContext.findClass(str);
                } catch (Exception e2) {
                    throw deserializationContext.weirdKeyException(this._keyClass, str, "unable to parse key as Class");
                }
            case 16:
                try {
                    return this._deser._deserialize(str, deserializationContext);
                } catch (IOException e3) {
                    throw deserializationContext.weirdKeyException(this._keyClass, str, "unable to parse key as currency");
                }
            default:
                return null;
        }
    }

    protected int _parseInt(String str) throws IllegalArgumentException {
        return Integer.parseInt(str);
    }

    protected long _parseLong(String str) throws IllegalArgumentException {
        return Long.parseLong(str);
    }

    protected double _parseDouble(String str) throws IllegalArgumentException {
        return NumberInput.parseDouble(str);
    }
}
