package com.facebook.imagepipeline.producers;

import android.util.Pair;
import com.facebook.cache.common.b;
import com.facebook.imagepipeline.c.f;
import com.facebook.imagepipeline.g.e;
import com.facebook.imagepipeline.request.ImageRequest$RequestLevel;

public class q extends ad<Pair<b, ImageRequest$RequestLevel>, e> {
    private final f b;

    protected /* synthetic */ Object b(aj ajVar) {
        return a(ajVar);
    }

    public q(f fVar, ai aiVar) {
        super(aiVar);
        this.b = fVar;
    }

    protected Pair<b, ImageRequest$RequestLevel> a(aj ajVar) {
        return Pair.create(this.b.c(ajVar.a(), ajVar.d()), ajVar.e());
    }

    public e a(e eVar) {
        return e.a(eVar);
    }
}
