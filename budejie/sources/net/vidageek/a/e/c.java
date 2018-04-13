package net.vidageek.a.e;

import net.vidageek.a.e.a.a;
import net.vidageek.a.e.a.b;
import net.vidageek.a.h.f;

public final class c<T> implements b<T> {
    private final Object a;
    private final Class<?> b;
    private final f c;

    public c(f fVar, Class<T> cls) {
        if (cls == null) {
            throw new IllegalArgumentException("target can't be null");
        }
        this.c = fVar;
        this.b = cls;
        this.a = null;
    }

    public c(f fVar, Object obj) {
        if (obj == null) {
            throw new IllegalArgumentException("target can't be null");
        }
        this.c = fVar;
        this.a = obj;
        this.b = obj.getClass();
    }

    public final a<T> a() {
        if (this.a == null) {
            return new a(this.c, this.b);
        }
        throw new IllegalStateException("must use constructor InvocationHandler(Class<T>) instead of InvocationHandler(Object).");
    }

    public final net.vidageek.a.e.a.c a(String str) {
        if (str != null) {
            return new e(this.c, this.a, this.b, str);
        }
        throw new IllegalArgumentException("methodName can't be null");
    }
}
