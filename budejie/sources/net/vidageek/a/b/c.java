package net.vidageek.a.b;

import net.vidageek.a.a;
import net.vidageek.a.a.b;
import net.vidageek.a.h.f;

public final class c {
    private static final f a = new b(c.class.getResourceAsStream("/mirror.properties")).a();
    private final f b;

    public c() {
        this(a);
    }

    public c(f fVar) {
        this.b = fVar;
    }

    public final Class<?> a(String str) {
        if (str != null && str.trim().length() != 0) {
            return this.b.a(str).a();
        }
        throw new IllegalArgumentException("className cannot be null or empty");
    }

    public final a a(Object obj) {
        return new a(this.b, obj);
    }

    public final <T> b<T> a(Class<T> cls) {
        return new net.vidageek.a.b(this.b, cls);
    }

    public final b<?> b(String str) {
        return a(a(str));
    }
}
