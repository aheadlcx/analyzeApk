package com.bumptech.glide.f;

import com.bumptech.glide.load.b.l;
import com.bumptech.glide.load.d;
import com.bumptech.glide.load.e;
import com.bumptech.glide.load.resource.e.c;
import java.io.File;

public class a<A, T, Z, R> implements f<A, T, Z, R>, Cloneable {
    private final f<A, T, Z, R> a;
    private d<File, Z> b;
    private d<T, Z> c;
    private e<Z> d;
    private c<Z, R> e;
    private com.bumptech.glide.load.a<T> f;

    public /* synthetic */ Object clone() throws CloneNotSupportedException {
        return g();
    }

    public a(f<A, T, Z, R> fVar) {
        this.a = fVar;
    }

    public l<A, T> e() {
        return this.a.e();
    }

    public void a(d<T, Z> dVar) {
        this.c = dVar;
    }

    public void a(com.bumptech.glide.load.a<T> aVar) {
        this.f = aVar;
    }

    public d<File, Z> a() {
        if (this.b != null) {
            return this.b;
        }
        return this.a.a();
    }

    public d<T, Z> b() {
        if (this.c != null) {
            return this.c;
        }
        return this.a.b();
    }

    public com.bumptech.glide.load.a<T> c() {
        if (this.f != null) {
            return this.f;
        }
        return this.a.c();
    }

    public e<Z> d() {
        if (this.d != null) {
            return this.d;
        }
        return this.a.d();
    }

    public c<Z, R> f() {
        if (this.e != null) {
            return this.e;
        }
        return this.a.f();
    }

    public a<A, T, Z, R> g() {
        try {
            return (a) super.clone();
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }
}
