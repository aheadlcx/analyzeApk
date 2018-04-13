package com.facebook.imagepipeline.producers;

import com.facebook.cache.common.b;
import com.facebook.common.internal.ImmutableMap;
import com.facebook.imagepipeline.c.f;
import com.facebook.imagepipeline.c.t;
import com.facebook.imagepipeline.g.c;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.d;
import java.util.Map;

public class ag implements ai<com.facebook.common.references.a<c>> {
    private final t<b, c> a;
    private final f b;
    private final ai<com.facebook.common.references.a<c>> c;

    public static class a extends m<com.facebook.common.references.a<c>, com.facebook.common.references.a<c>> {
        private final b a;
        private final boolean b;
        private final t<b, c> c;

        public a(j<com.facebook.common.references.a<c>> jVar, b bVar, boolean z, t<b, c> tVar) {
            super(jVar);
            this.a = bVar;
            this.b = z;
            this.c = tVar;
        }

        protected void a(com.facebook.common.references.a<c> aVar, boolean z) {
            if (aVar == null) {
                if (z) {
                    d().b(null, true);
                }
            } else if (z || this.b) {
                com.facebook.common.references.a<c> a = this.c.a(this.a, aVar);
                try {
                    d().b(1.0f);
                    j d = d();
                    if (a != null) {
                        aVar = a;
                    }
                    d.b(aVar, z);
                } finally {
                    com.facebook.common.references.a.c(a);
                }
            }
        }
    }

    public ag(t<b, c> tVar, f fVar, ai<com.facebook.common.references.a<c>> aiVar) {
        this.a = tVar;
        this.b = fVar;
        this.c = aiVar;
    }

    public void a(j<com.facebook.common.references.a<c>> jVar, aj ajVar) {
        Map map = null;
        al c = ajVar.c();
        String b = ajVar.b();
        ImageRequest a = ajVar.a();
        Object d = ajVar.d();
        com.facebook.imagepipeline.request.c p = a.p();
        if (p == null || p.a() == null) {
            this.c.a(jVar, ajVar);
            return;
        }
        c.a(b, a());
        b b2 = this.b.b(a, d);
        com.facebook.common.references.a a2 = this.a.a(b2);
        if (a2 != null) {
            String a3 = a();
            if (c.b(b)) {
                map = ImmutableMap.of("cached_value_found", "true");
            }
            c.a(b, a3, map);
            c.a(b, "PostprocessedBitmapMemoryCacheProducer", true);
            jVar.b(1.0f);
            jVar.b(a2, true);
            a2.close();
            return;
        }
        j aVar = new a(jVar, b2, p instanceof d, this.a);
        a3 = a();
        if (c.b(b)) {
            map = ImmutableMap.of("cached_value_found", "false");
        }
        c.a(b, a3, map);
        this.c.a(aVar, ajVar);
    }

    protected String a() {
        return "PostprocessedBitmapMemoryCacheProducer";
    }
}
