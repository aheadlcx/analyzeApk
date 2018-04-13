package com.facebook.imagepipeline.producers;

import bolts.f;
import bolts.g;
import com.facebook.imagepipeline.g.e;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.b;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

class ac$2 implements f<e, Void> {
    final /* synthetic */ al a;
    final /* synthetic */ String b;
    final /* synthetic */ j c;
    final /* synthetic */ aj d;
    final /* synthetic */ b e;
    final /* synthetic */ List f;
    final /* synthetic */ int g;
    final /* synthetic */ ImageRequest h;
    final /* synthetic */ AtomicBoolean i;
    final /* synthetic */ ac j;

    ac$2(ac acVar, al alVar, String str, j jVar, aj ajVar, b bVar, List list, int i, ImageRequest imageRequest, AtomicBoolean atomicBoolean) {
        this.j = acVar;
        this.a = alVar;
        this.b = str;
        this.c = jVar;
        this.d = ajVar;
        this.e = bVar;
        this.f = list;
        this.g = i;
        this.h = imageRequest;
        this.i = atomicBoolean;
    }

    public /* synthetic */ Object a(g gVar) throws Exception {
        return b(gVar);
    }

    public Void b(g<e> gVar) throws Exception {
        boolean z = true;
        if (ac.a(gVar)) {
            this.a.b(this.b, "MediaVariationsFallbackProducer", null);
            this.c.b();
            z = false;
        } else if (gVar.d()) {
            this.a.a(this.b, "MediaVariationsFallbackProducer", gVar.f(), null);
            ac.a(this.j, this.c, this.d, this.e.a());
        } else {
            e eVar = (e) gVar.e();
            if (eVar != null) {
                boolean z2 = !this.e.c() && ac.a((b.b) this.f.get(this.g), this.h.g());
                this.a.a(this.b, "MediaVariationsFallbackProducer", ac.a(this.a, this.b, true, this.f.size(), this.e.d(), z2));
                if (z2) {
                    this.a.a(this.b, "MediaVariationsFallbackProducer", true);
                    this.c.b(1.0f);
                }
                this.c.b(eVar, z2);
                eVar.close();
                if (z2) {
                    z = false;
                }
            } else if (this.g < this.f.size() - 1) {
                ac.a(this.j, this.c, this.d, this.h, this.e, this.f, this.g + 1, this.i);
                z = false;
            } else {
                this.a.a(this.b, "MediaVariationsFallbackProducer", ac.a(this.a, this.b, false, this.f.size(), this.e.d(), false));
            }
        }
        if (z) {
            ac.a(this.j, this.c, this.d, this.e.a());
        }
        return null;
    }
}
