package com.facebook.imagepipeline.producers;

import com.facebook.cache.common.b;
import com.facebook.common.internal.ImmutableMap;
import com.facebook.common.memory.PooledByteBuffer;
import com.facebook.imagepipeline.c.f;
import com.facebook.imagepipeline.c.t;
import com.facebook.imagepipeline.g.e;
import com.facebook.imagepipeline.request.ImageRequest$RequestLevel;
import java.util.Map;

public class r implements ai<e> {
    private final t<b, PooledByteBuffer> a;
    private final f b;
    private final ai<e> c;

    private static class a extends m<e, e> {
        private final t<b, PooledByteBuffer> a;
        private final b b;

        public a(j<e> jVar, t<b, PooledByteBuffer> tVar, b bVar) {
            super(jVar);
            this.a = tVar;
            this.b = bVar;
        }

        public void a(e eVar, boolean z) {
            if (!z || eVar == null) {
                d().b(eVar, z);
                return;
            }
            e c = eVar.c();
            if (c != null) {
                try {
                    com.facebook.common.references.a a = this.a.a(eVar.j() != null ? eVar.j() : this.b, c);
                    if (a != null) {
                        try {
                            c = new e(a);
                            c.b(eVar);
                            try {
                                d().b(1.0f);
                                d().b(c, true);
                                return;
                            } finally {
                                e.d(c);
                            }
                        } finally {
                            com.facebook.common.references.a.c(a);
                        }
                    }
                } finally {
                    com.facebook.common.references.a.c(c);
                }
            }
            d().b(eVar, true);
        }
    }

    public r(t<b, PooledByteBuffer> tVar, f fVar, ai<e> aiVar) {
        this.a = tVar;
        this.b = fVar;
        this.c = aiVar;
    }

    public void a(j<e> jVar, aj ajVar) {
        Map map = null;
        String b = ajVar.b();
        al c = ajVar.c();
        c.a(b, "EncodedMemoryCacheProducer");
        b c2 = this.b.c(ajVar.a(), ajVar.d());
        com.facebook.common.references.a a = this.a.a(c2);
        String str;
        if (a != null) {
            e eVar;
            try {
                eVar = new e(a);
                eVar.a(c2);
                str = "EncodedMemoryCacheProducer";
                if (c.b(b)) {
                    map = ImmutableMap.of("cached_value_found", "true");
                }
                c.a(b, str, map);
                c.a(b, "EncodedMemoryCacheProducer", true);
                jVar.b(1.0f);
                jVar.b(eVar, true);
                e.d(eVar);
                com.facebook.common.references.a.c(a);
            } catch (Throwable th) {
                com.facebook.common.references.a.c(a);
            }
        } else if (ajVar.e().getValue() >= ImageRequest$RequestLevel.ENCODED_MEMORY_CACHE.getValue()) {
            str = "EncodedMemoryCacheProducer";
            if (c.b(b)) {
                map = ImmutableMap.of("cached_value_found", "false");
            }
            c.a(b, str, map);
            c.a(b, "EncodedMemoryCacheProducer", false);
            jVar.b(null, true);
            com.facebook.common.references.a.c(a);
        } else {
            j aVar = new a(jVar, this.a, c2);
            str = "EncodedMemoryCacheProducer";
            if (c.b(b)) {
                map = ImmutableMap.of("cached_value_found", "false");
            }
            c.a(b, str, map);
            this.c.a(aVar, ajVar);
            com.facebook.common.references.a.c(a);
        }
    }
}
