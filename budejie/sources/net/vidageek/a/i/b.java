package net.vidageek.a.i;

import java.lang.reflect.Constructor;
import net.vidageek.a.h.f;

public final class b<T> implements net.vidageek.a.i.a.b<T> {
    private final Class<T> a;
    private final f b;

    public b(f fVar, Class<T> cls) {
        if (cls == null) {
            throw new IllegalArgumentException("argument class cannot be null.");
        }
        this.b = fVar;
        this.a = cls;
    }

    public final Constructor<T> a(Class<?>... clsArr) {
        if (clsArr != null) {
            return this.b.a(this.a).a((Class[]) clsArr);
        }
        throw new IllegalArgumentException("classes cannot be null");
    }
}
