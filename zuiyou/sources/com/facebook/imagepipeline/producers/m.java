package com.facebook.imagepipeline.producers;

public abstract class m<I, O> extends b<I> {
    private final j<O> a;

    public m(j<O> jVar) {
        this.a = jVar;
    }

    public j<O> d() {
        return this.a;
    }

    protected void a(Throwable th) {
        this.a.b(th);
    }

    protected void a() {
        this.a.b();
    }

    protected void a(float f) {
        this.a.b(f);
    }
}
