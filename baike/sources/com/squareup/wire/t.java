package com.squareup.wire;

import com.squareup.wire.Message.Builder;
import com.squareup.wire.ProtoAdapter.EnumConstantNotFoundException;
import com.squareup.wire.WireField.Label;
import com.squareup.wire.internal.Internal;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

final class t<M extends Message<M, B>, B extends Builder<M, B>> extends ProtoAdapter<M> {
    private final Class<M> d;
    private final Class<B> e;
    private final Map<Integer, a<M, B>> f;

    static <M extends Message<M, B>, B extends Builder<M, B>> t<M, B> a(Class<M> cls) {
        Class b = b(cls);
        Map linkedHashMap = new LinkedHashMap();
        for (Field field : cls.getDeclaredFields()) {
            WireField wireField = (WireField) field.getAnnotation(WireField.class);
            if (wireField != null) {
                linkedHashMap.put(Integer.valueOf(wireField.tag()), new a(wireField, field, b));
            }
        }
        return new t(cls, b, Collections.unmodifiableMap(linkedHashMap));
    }

    t(Class<M> cls, Class<B> cls2, Map<Integer, a<M, B>> map) {
        super(FieldEncoding.LENGTH_DELIMITED, cls);
        this.d = cls;
        this.e = cls2;
        this.f = map;
    }

    B a() {
        Object e;
        try {
            return (Builder) this.e.newInstance();
        } catch (IllegalAccessException e2) {
            e = e2;
            throw new AssertionError(e);
        } catch (InstantiationException e3) {
            e = e3;
            throw new AssertionError(e);
        }
    }

    private static <M extends Message<M, B>, B extends Builder<M, B>> Class<B> b(Class<M> cls) {
        try {
            return Class.forName(cls.getName() + "$Builder");
        } catch (ClassNotFoundException e) {
            throw new IllegalArgumentException("No builder class found for message type " + cls.getName());
        }
    }

    public int encodedSize(M m) {
        int i = m.e;
        if (i != 0) {
            return i;
        }
        int i2 = 0;
        for (a aVar : this.f.values()) {
            Object a = aVar.a((Message) m);
            if (a != null) {
                i2 = aVar.d().encodedSizeWithTag(aVar.tag, a) + i2;
            }
        }
        i = m.unknownFields().size() + i2;
        m.e = i;
        return i;
    }

    public void encode(ProtoWriter protoWriter, M m) throws IOException {
        for (a aVar : this.f.values()) {
            Object a = aVar.a((Message) m);
            if (a != null) {
                aVar.d().encodeWithTag(protoWriter, aVar.tag, a);
            }
        }
        protoWriter.writeBytes(m.unknownFields());
    }

    public M redact(M m) {
        Builder newBuilder = m.newBuilder();
        for (a aVar : this.f.values()) {
            if (aVar.redacted && aVar.label == Label.REQUIRED) {
                throw new UnsupportedOperationException(String.format("Field '%s' in %s is required and cannot be redacted.", new Object[]{aVar.name, this.a.getName()}));
            }
            boolean isAssignableFrom = Message.class.isAssignableFrom(aVar.b().a);
            if (aVar.redacted || (isAssignableFrom && !aVar.label.a())) {
                Object a = aVar.a(newBuilder);
                if (a != null) {
                    aVar.b(newBuilder, aVar.d().redact(a));
                }
            } else if (isAssignableFrom && aVar.label.a()) {
                Internal.redactElements((List) aVar.a(newBuilder), aVar.b());
            }
        }
        newBuilder.clearUnknownFields();
        return newBuilder.build();
    }

    public boolean equals(Object obj) {
        return (obj instanceof t) && ((t) obj).d == this.d;
    }

    public int hashCode() {
        return this.d.hashCode();
    }

    public String toString(M m) {
        StringBuilder stringBuilder = new StringBuilder();
        for (a aVar : this.f.values()) {
            Object a = aVar.a((Message) m);
            if (a != null) {
                stringBuilder.append(", ").append(aVar.name).append('=').append(aVar.redacted ? "██" : a);
            }
        }
        stringBuilder.replace(0, 2, this.d.getSimpleName() + '{');
        return stringBuilder.append('}').toString();
    }

    public M decode(ProtoReader protoReader) throws IOException {
        Builder a = a();
        long beginMessage = protoReader.beginMessage();
        while (true) {
            int nextTag = protoReader.nextTag();
            if (nextTag != -1) {
                a aVar = (a) this.f.get(Integer.valueOf(nextTag));
                if (aVar != null) {
                    try {
                        ProtoAdapter d;
                        if (aVar.a()) {
                            d = aVar.d();
                        } else {
                            d = aVar.b();
                        }
                        aVar.a(a, d.decode(protoReader));
                    } catch (EnumConstantNotFoundException e) {
                        a.addUnknownField(nextTag, FieldEncoding.VARINT, Long.valueOf((long) e.value));
                    }
                } else {
                    FieldEncoding peekFieldEncoding = protoReader.peekFieldEncoding();
                    a.addUnknownField(nextTag, peekFieldEncoding, peekFieldEncoding.rawProtoAdapter().decode(protoReader));
                }
            } else {
                protoReader.endMessage(beginMessage);
                return a.build();
            }
        }
    }
}
