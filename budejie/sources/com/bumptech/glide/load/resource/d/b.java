package com.bumptech.glide.load.resource.d;

import com.bumptech.glide.load.engine.j;

public class b implements j<a> {
    private final a a;

    public /* synthetic */ Object b() {
        return a();
    }

    public b(a aVar) {
        if (aVar == null) {
            throw new NullPointerException("Data must not be null");
        }
        this.a = aVar;
    }

    public a a() {
        return this.a;
    }

    public int c() {
        return this.a.a();
    }

    public void d() {
        j b = this.a.b();
        if (b != null) {
            b.d();
        }
        b = this.a.c();
        if (b != null) {
            b.d();
        }
    }
}
