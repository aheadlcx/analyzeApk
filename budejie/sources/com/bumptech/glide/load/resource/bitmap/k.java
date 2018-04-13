package com.bumptech.glide.load.resource.bitmap;

import com.bumptech.glide.i.h;
import com.bumptech.glide.load.engine.a.c;
import com.bumptech.glide.load.resource.a.a;

public class k extends a<j> {
    private final c b;

    public k(j jVar, c cVar) {
        super(jVar);
        this.b = cVar;
    }

    public int c() {
        return h.a(((j) this.a).b());
    }

    public void d() {
        this.b.a(((j) this.a).b());
    }
}
