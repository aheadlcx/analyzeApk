package com.nineoldandroids.util;

public abstract class c<T, V> {
    private final String a;
    private final Class<V> b;

    public abstract V a(T t);

    public c(Class<V> cls, String str) {
        this.a = str;
        this.b = cls;
    }

    public void a(T t, V v) {
        throw new UnsupportedOperationException("Property " + a() + " is read-only");
    }

    public String a() {
        return this.a;
    }
}
