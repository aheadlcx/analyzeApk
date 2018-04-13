package com.facebook.imagepipeline.producers;

import android.util.Pair;
import com.facebook.cache.common.b;
import com.facebook.common.references.a;
import com.facebook.imagepipeline.c.f;
import com.facebook.imagepipeline.g.c;
import com.facebook.imagepipeline.request.ImageRequest$RequestLevel;

public class g extends ad<Pair<b, ImageRequest$RequestLevel>, a<c>> {
    private final f b;

    protected /* synthetic */ Object b(aj ajVar) {
        return a(ajVar);
    }

    public g(f fVar, ai aiVar) {
        super(aiVar);
        this.b = fVar;
    }

    protected Pair<b, ImageRequest$RequestLevel> a(aj ajVar) {
        return Pair.create(this.b.a(ajVar.a(), ajVar.d()), ajVar.e());
    }

    public a<c> a(a<c> aVar) {
        return a.b(aVar);
    }
}
