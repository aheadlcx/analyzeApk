package com.facebook.imagepipeline.producers;

import bolts.f;
import bolts.g;
import com.facebook.common.internal.ImmutableMap;
import com.facebook.imagepipeline.c.l;
import com.facebook.imagepipeline.g.e;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequest$RequestLevel;
import java.util.Map;
import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicBoolean;

public class n implements ai<e> {
    private final ai<e> a;
    private final l b;

    public n(ai<e> aiVar, l lVar) {
        this.a = aiVar;
        this.b = lVar;
    }

    public void a(j<e> jVar, aj ajVar) {
        ImageRequest a = ajVar.a();
        if (a.n()) {
            ajVar.c().a(ajVar.b(), "DiskCacheProducer");
            AtomicBoolean atomicBoolean = new AtomicBoolean(false);
            this.b.a(a, ajVar.d(), atomicBoolean).a(b(jVar, ajVar));
            a(atomicBoolean, ajVar);
            return;
        }
        c(jVar, ajVar);
    }

    private f<e, Void> b(j<e> jVar, aj ajVar) {
        final String b = ajVar.b();
        final al c = ajVar.c();
        final j<e> jVar2 = jVar;
        final aj ajVar2 = ajVar;
        return new f<e, Void>(this) {
            final /* synthetic */ n e;

            public /* synthetic */ Object a(g gVar) throws Exception {
                return b(gVar);
            }

            public Void b(g<e> gVar) throws Exception {
                if (n.b(gVar)) {
                    c.b(b, "DiskCacheProducer", null);
                    jVar2.b();
                } else if (gVar.d()) {
                    c.a(b, "DiskCacheProducer", gVar.f(), null);
                    this.e.a.a(jVar2, ajVar2);
                } else {
                    e eVar = (e) gVar.e();
                    if (eVar != null) {
                        c.a(b, "DiskCacheProducer", n.a(c, b, true, eVar.k()));
                        c.a(b, "DiskCacheProducer", true);
                        jVar2.b(1.0f);
                        jVar2.b(eVar, true);
                        eVar.close();
                    } else {
                        c.a(b, "DiskCacheProducer", n.a(c, b, false, 0));
                        this.e.a.a(jVar2, ajVar2);
                    }
                }
                return null;
            }
        };
    }

    private static boolean b(g<?> gVar) {
        return gVar.c() || (gVar.d() && (gVar.f() instanceof CancellationException));
    }

    private void c(j<e> jVar, aj ajVar) {
        if (ajVar.e().getValue() >= ImageRequest$RequestLevel.DISK_CACHE.getValue()) {
            jVar.b(null, true);
        } else {
            this.a.a(jVar, ajVar);
        }
    }

    static Map<String, String> a(al alVar, String str, boolean z, int i) {
        if (!alVar.b(str)) {
            return null;
        }
        if (z) {
            return ImmutableMap.of("cached_value_found", String.valueOf(z), "encodedImageSize", String.valueOf(i));
        }
        return ImmutableMap.of("cached_value_found", String.valueOf(z));
    }

    private void a(final AtomicBoolean atomicBoolean, aj ajVar) {
        ajVar.a(new e(this) {
            final /* synthetic */ n b;

            public void a() {
                atomicBoolean.set(true);
            }
        });
    }
}
