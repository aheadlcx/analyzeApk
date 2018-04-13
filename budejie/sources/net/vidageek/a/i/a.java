package net.vidageek.a.i;

import java.lang.reflect.Field;
import net.vidageek.a.h.f;

public final class a<T> implements net.vidageek.a.i.a.a<T> {
    private final Class<T> a;
    private final f b;

    public a(f fVar, Class<T> cls) {
        if (cls == null) {
            throw new IllegalArgumentException("clazz cannot be null");
        }
        this.b = fVar;
        this.a = cls;
    }

    public final net.vidageek.a.f.a.a<Field> a() {
        return new net.vidageek.a.f.a(this.b.a(this.a).b());
    }
}
