package com.nineoldandroids.util;

public abstract class a<T> extends c<T, Float> {
    public abstract void a(T t, float f);

    public a(String str) {
        super(Float.class, str);
    }

    public final void a(T t, Float f) {
        a((Object) t, f.floatValue());
    }
}
