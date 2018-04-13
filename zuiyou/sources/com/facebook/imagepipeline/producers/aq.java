package com.facebook.imagepipeline.producers;

import com.facebook.common.b.h;
import java.util.Map;

public abstract class aq<T> extends h<T> {
    private final j<T> b;
    private final al c;
    private final String d;
    private final String e;

    protected abstract void b(T t);

    public aq(j<T> jVar, al alVar, String str, String str2) {
        this.b = jVar;
        this.c = alVar;
        this.d = str;
        this.e = str2;
        this.c.a(this.e, this.d);
    }

    protected void a(T t) {
        this.c.a(this.e, this.d, this.c.b(this.e) ? c(t) : null);
        this.b.b(t, true);
    }

    protected void a(Exception exception) {
        this.c.a(this.e, this.d, exception, this.c.b(this.e) ? b(exception) : null);
        this.b.b((Throwable) exception);
    }

    protected void b() {
        this.c.b(this.e, this.d, this.c.b(this.e) ? e() : null);
        this.b.b();
    }

    protected Map<String, String> c(T t) {
        return null;
    }

    protected Map<String, String> b(Exception exception) {
        return null;
    }

    protected Map<String, String> e() {
        return null;
    }
}
