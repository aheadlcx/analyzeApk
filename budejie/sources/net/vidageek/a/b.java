package net.vidageek.a;

import net.vidageek.a.e.c;
import net.vidageek.a.h.f;
import net.vidageek.a.i.a.a;
import net.vidageek.a.i.a.d;
import net.vidageek.a.i.e;

public final class b<T> implements net.vidageek.a.b.b<T> {
    private final Class<T> a;
    private final f b;

    public b(f fVar, Class<T> cls) {
        this.b = fVar;
        if (cls == null) {
            throw new IllegalArgumentException("clazz cannot be null");
        }
        this.a = cls;
    }

    public final net.vidageek.a.e.a.b<T> a() {
        return new c(this.b, this.a);
    }

    public final d<T> b() {
        return new e(this.b, this.a);
    }

    public final a<T> c() {
        return new net.vidageek.a.i.a(this.b, this.a);
    }
}
