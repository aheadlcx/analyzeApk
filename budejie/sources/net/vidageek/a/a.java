package net.vidageek.a;

import net.vidageek.a.e.a.b;
import net.vidageek.a.e.c;
import net.vidageek.a.h.f;

public final class a implements net.vidageek.a.b.a {
    private final Object a;
    private final f b;

    public a(f fVar, Object obj) {
        if (obj == null) {
            throw new IllegalArgumentException("target cannot be null");
        }
        this.b = fVar;
        this.a = obj;
    }

    public final b<Object> a() {
        return new c(this.b, this.a);
    }

    public final net.vidageek.a.d.a.a b() {
        return new net.vidageek.a.d.a(this.b, this.a);
    }
}
