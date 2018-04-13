package com.facebook.imagepipeline.producers;

import bolts.g;
import com.facebook.common.internal.ImmutableMap;
import com.facebook.imagepipeline.c.f;
import com.facebook.imagepipeline.c.l;
import com.facebook.imagepipeline.c.q;
import com.facebook.imagepipeline.c.r;
import com.facebook.imagepipeline.common.c;
import com.facebook.imagepipeline.g.e;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequest$CacheChoice;
import com.facebook.imagepipeline.request.b;
import com.facebook.imagepipeline.request.b$a;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.annotation.Nullable;

public class ac implements ai<e> {
    private final com.facebook.imagepipeline.c.e a;
    private final com.facebook.imagepipeline.c.e b;
    private final f c;
    private final r d;
    @Nullable
    private q e;
    private final l f;
    private final ai<e> g;

    public ac(com.facebook.imagepipeline.c.e eVar, com.facebook.imagepipeline.c.e eVar2, f fVar, r rVar, @Nullable q qVar, l lVar, ai<e> aiVar) {
        this.a = eVar;
        this.b = eVar2;
        this.c = fVar;
        this.d = rVar;
        this.e = qVar;
        this.f = lVar;
        this.g = aiVar;
    }

    public void a(j<e> jVar, aj ajVar) {
        ImageRequest a = ajVar.a();
        c g = a.g();
        b d = a.d();
        if (!a.n() || g == null || g.b <= 0 || g.a <= 0) {
            b((j) jVar, ajVar);
            return;
        }
        String a2;
        String str;
        if (d != null) {
            a2 = d.a();
            str = "index_db";
        } else if (this.e == null) {
            a2 = null;
            str = null;
        } else {
            a2 = this.e.a(a.b());
            str = "id_extractor";
        }
        if (d == null && a2 == null) {
            b((j) jVar, ajVar);
            return;
        }
        ajVar.c().a(ajVar.b(), "MediaVariationsFallbackProducer");
        AtomicBoolean atomicBoolean = new AtomicBoolean(false);
        if (d == null || d.b() <= 0) {
            b$a a3 = b.a(a2);
            boolean z = d != null && d.c();
            this.d.a(a2, a3.a(z).a(str)).a(new ac$1(this, jVar, ajVar, a2, a, g, atomicBoolean));
        } else {
            a((j) jVar, ajVar, a, d, g, atomicBoolean);
        }
        a(atomicBoolean, ajVar);
    }

    private g a(j<e> jVar, aj ajVar, ImageRequest imageRequest, b bVar, c cVar, AtomicBoolean atomicBoolean) {
        if (bVar.b() == 0) {
            return g.a((e) null).a(b(jVar, ajVar, imageRequest, bVar, Collections.emptyList(), 0, atomicBoolean));
        }
        return a((j) jVar, ajVar, imageRequest, bVar, bVar.a(new ac$b(cVar)), 0, atomicBoolean);
    }

    private g a(j<e> jVar, aj ajVar, ImageRequest imageRequest, b bVar, List<b.b> list, int i, AtomicBoolean atomicBoolean) {
        ImageRequest$CacheChoice a;
        b.b bVar2 = (b.b) list.get(i);
        com.facebook.cache.common.b a2 = this.c.a(imageRequest, bVar2.a(), ajVar.d());
        if (bVar2.d() == null) {
            a = imageRequest.a();
        } else {
            a = bVar2.d();
        }
        return (a == ImageRequest$CacheChoice.SMALL ? this.b : this.a).a(a2, atomicBoolean).a(b(jVar, ajVar, imageRequest, bVar, list, i, atomicBoolean));
    }

    private static boolean b(b.b bVar, c cVar) {
        return bVar.b() >= cVar.a && bVar.c() >= cVar.b;
    }

    private bolts.f<e, Void> b(j<e> jVar, aj ajVar, ImageRequest imageRequest, b bVar, List<b.b> list, int i, AtomicBoolean atomicBoolean) {
        return new ac$2(this, ajVar.c(), ajVar.b(), jVar, ajVar, bVar, list, i, imageRequest, atomicBoolean);
    }

    private void b(j<e> jVar, aj ajVar) {
        this.g.a(jVar, ajVar);
    }

    private void a(j<e> jVar, aj ajVar, String str) {
        this.g.a(new ac$a(this, jVar, ajVar, str), ajVar);
    }

    private static boolean b(g<?> gVar) {
        return gVar.c() || (gVar.d() && (gVar.f() instanceof CancellationException));
    }

    static Map<String, String> a(al alVar, String str, boolean z, int i, String str2, boolean z2) {
        if (!alVar.b(str)) {
            return null;
        }
        if (z) {
            return ImmutableMap.of("cached_value_found", String.valueOf(true), "cached_value_used_as_last", String.valueOf(z2), "variants_count", String.valueOf(i), "variants_source", str2);
        }
        return ImmutableMap.of("cached_value_found", String.valueOf(false), "variants_count", String.valueOf(i), "variants_source", str2);
    }

    private void a(AtomicBoolean atomicBoolean, aj ajVar) {
        ajVar.a(new ac$3(this, atomicBoolean));
    }
}
