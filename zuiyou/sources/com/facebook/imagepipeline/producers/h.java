package com.facebook.imagepipeline.producers;

import com.facebook.cache.common.b;
import com.facebook.common.internal.ImmutableMap;
import com.facebook.common.references.a;
import com.facebook.imagepipeline.c.f;
import com.facebook.imagepipeline.c.t;
import com.facebook.imagepipeline.g.c;
import com.facebook.imagepipeline.request.ImageRequest$RequestLevel;
import java.util.Map;

public class h implements ai<a<c>> {
    private final t<b, c> a;
    private final f b;
    private final ai<a<c>> c;

    public h(t<b, c> tVar, f fVar, ai<a<c>> aiVar) {
        this.a = tVar;
        this.b = fVar;
        this.c = aiVar;
    }

    public void a(j<a<c>> jVar, aj ajVar) {
        Map map = null;
        al c = ajVar.c();
        String b = ajVar.b();
        c.a(b, a());
        b a = this.b.a(ajVar.a(), ajVar.d());
        a a2 = this.a.a(a);
        if (a2 != null) {
            boolean c2 = ((c) a2.a()).g().c();
            if (c2) {
                c.a(b, a(), c.b(b) ? ImmutableMap.of("cached_value_found", "true") : null);
                c.a(b, a(), true);
                jVar.b(1.0f);
            }
            jVar.b(a2, c2);
            a2.close();
            if (c2) {
                return;
            }
        }
        if (ajVar.e().getValue() >= ImageRequest$RequestLevel.BITMAP_MEMORY_CACHE.getValue()) {
            Map of;
            String a3 = a();
            if (c.b(b)) {
                of = ImmutableMap.of("cached_value_found", "false");
            } else {
                of = null;
            }
            c.a(b, a3, of);
            c.a(b, a(), false);
            jVar.b(null, true);
            return;
        }
        j a4 = a((j) jVar, a);
        a3 = a();
        if (c.b(b)) {
            map = ImmutableMap.of("cached_value_found", "false");
        }
        c.a(b, a3, map);
        this.c.a(a4, ajVar);
    }

    protected j<a<c>> a(j<a<c>> jVar, final b bVar) {
        return new m<a<c>, a<c>>(this, jVar) {
            final /* synthetic */ h b;

            public void a(a<c> aVar, boolean z) {
                if (aVar == null) {
                    if (z) {
                        d().b(null, true);
                    }
                } else if (((c) aVar.a()).e()) {
                    d().b(aVar, z);
                } else {
                    if (!z) {
                        a a = this.b.a.a(bVar);
                        if (a != null) {
                            try {
                                com.facebook.imagepipeline.g.h g = ((c) aVar.a()).g();
                                com.facebook.imagepipeline.g.h g2 = ((c) a.a()).g();
                                if (g2.c() || g2.a() >= g.a()) {
                                    d().b(a, false);
                                    return;
                                }
                                a.c(a);
                            } finally {
                                a.c(a);
                            }
                        }
                    }
                    a<c> a2 = this.b.a.a(bVar, aVar);
                    if (z) {
                        try {
                            d().b(1.0f);
                        } catch (Throwable th) {
                            a.c(a2);
                        }
                    }
                    j d = d();
                    if (a2 != null) {
                        aVar = a2;
                    }
                    d.b(aVar, z);
                    a.c(a2);
                }
            }
        };
    }

    protected String a() {
        return "BitmapMemoryCacheProducer";
    }
}
