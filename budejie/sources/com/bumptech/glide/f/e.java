package com.bumptech.glide.f;

import com.bumptech.glide.load.a;
import com.bumptech.glide.load.b.l;
import com.bumptech.glide.load.d;
import com.bumptech.glide.load.resource.e.c;
import java.io.File;

public class e<A, T, Z, R> implements f<A, T, Z, R> {
    private final l<A, T> a;
    private final c<Z, R> b;
    private final b<T, Z> c;

    public e(l<A, T> lVar, c<Z, R> cVar, b<T, Z> bVar) {
        if (lVar == null) {
            throw new NullPointerException("ModelLoader must not be null");
        }
        this.a = lVar;
        if (cVar == null) {
            throw new NullPointerException("Transcoder must not be null");
        }
        this.b = cVar;
        if (bVar == null) {
            throw new NullPointerException("DataLoadProvider must not be null");
        }
        this.c = bVar;
    }

    public l<A, T> e() {
        return this.a;
    }

    public c<Z, R> f() {
        return this.b;
    }

    public d<File, Z> a() {
        return this.c.a();
    }

    public d<T, Z> b() {
        return this.c.b();
    }

    public a<T> c() {
        return this.c.c();
    }

    public com.bumptech.glide.load.e<Z> d() {
        return this.c.d();
    }
}
