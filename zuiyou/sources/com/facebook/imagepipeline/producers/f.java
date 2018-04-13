package com.facebook.imagepipeline.producers;

import com.facebook.cache.common.b;
import com.facebook.common.references.a;
import com.facebook.imagepipeline.c.t;
import com.facebook.imagepipeline.g.c;

public class f extends h {
    public f(t<b, c> tVar, com.facebook.imagepipeline.c.f fVar, ai<a<c>> aiVar) {
        super(tVar, fVar, aiVar);
    }

    protected j<a<c>> a(j<a<c>> jVar, b bVar) {
        return jVar;
    }

    protected String a() {
        return "BitmapMemoryCacheGetProducer";
    }
}
