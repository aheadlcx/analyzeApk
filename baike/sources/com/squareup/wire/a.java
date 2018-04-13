package com.squareup.wire;

import com.squareup.wire.Message.Builder;
import com.squareup.wire.WireField.Label;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

final class a<M extends Message<M, B>, B extends Builder<M, B>> {
    private final String a;
    private final String b;
    private final Field c;
    private final Field d;
    private final Method e;
    private ProtoAdapter<?> f;
    private ProtoAdapter<?> g;
    private ProtoAdapter<Object> h;
    public final Label label;
    public final String name;
    public final boolean redacted;
    public final int tag;

    private static Field a(Class<?> cls, String str) {
        try {
            return cls.getField(str);
        } catch (NoSuchFieldException e) {
            throw new AssertionError("No builder field " + cls.getName() + "." + str);
        }
    }

    private static Method a(Class<?> cls, String str, Class<?> cls2) {
        try {
            return cls.getMethod(str, new Class[]{cls2});
        } catch (NoSuchMethodException e) {
            throw new AssertionError("No builder method " + cls.getName() + "." + str + "(" + cls2.getName() + ")");
        }
    }

    a(WireField wireField, Field field, Class<B> cls) {
        this.label = wireField.label();
        this.name = field.getName();
        this.tag = wireField.tag();
        this.a = wireField.keyAdapter();
        this.b = wireField.adapter();
        this.redacted = wireField.redacted();
        this.c = field;
        this.d = a((Class) cls, this.name);
        this.e = a(cls, this.name, field.getType());
    }

    boolean a() {
        return !this.a.isEmpty();
    }

    ProtoAdapter<?> b() {
        ProtoAdapter<?> protoAdapter = this.f;
        if (protoAdapter != null) {
            return protoAdapter;
        }
        protoAdapter = ProtoAdapter.get(this.b);
        this.f = protoAdapter;
        return protoAdapter;
    }

    ProtoAdapter<?> c() {
        ProtoAdapter<?> protoAdapter = this.g;
        if (protoAdapter != null) {
            return protoAdapter;
        }
        protoAdapter = ProtoAdapter.get(this.a);
        this.g = protoAdapter;
        return protoAdapter;
    }

    ProtoAdapter<Object> d() {
        ProtoAdapter<Object> protoAdapter = this.h;
        if (protoAdapter != null) {
            return protoAdapter;
        }
        if (a()) {
            protoAdapter = ProtoAdapter.newMapAdapter(c(), b());
            this.h = protoAdapter;
            return protoAdapter;
        }
        protoAdapter = b().a(this.label);
        this.h = protoAdapter;
        return protoAdapter;
    }

    void a(B b, Object obj) {
        if (this.label.a()) {
            ((List) a((Builder) b)).add(obj);
        } else if (this.a.isEmpty()) {
            b(b, obj);
        } else {
            ((Map) a((Builder) b)).putAll((Map) obj);
        }
    }

    void b(B b, Object obj) {
        Object e;
        try {
            if (this.label.c()) {
                this.e.invoke(b, new Object[]{obj});
                return;
            }
            this.d.set(b, obj);
        } catch (IllegalAccessException e2) {
            e = e2;
            throw new AssertionError(e);
        } catch (InvocationTargetException e3) {
            e = e3;
            throw new AssertionError(e);
        }
    }

    Object a(M m) {
        try {
            return this.c.get(m);
        } catch (IllegalAccessException e) {
            throw new AssertionError(e);
        }
    }

    Object a(B b) {
        try {
            return this.d.get(b);
        } catch (IllegalAccessException e) {
            throw new AssertionError(e);
        }
    }
}
