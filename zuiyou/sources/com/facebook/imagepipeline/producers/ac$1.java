package com.facebook.imagepipeline.producers;

import bolts.f;
import bolts.g;
import com.facebook.imagepipeline.common.c;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.b;
import java.util.concurrent.atomic.AtomicBoolean;

class ac$1 implements f<b, Object> {
    final /* synthetic */ j a;
    final /* synthetic */ aj b;
    final /* synthetic */ String c;
    final /* synthetic */ ImageRequest d;
    final /* synthetic */ c e;
    final /* synthetic */ AtomicBoolean f;
    final /* synthetic */ ac g;

    ac$1(ac acVar, j jVar, aj ajVar, String str, ImageRequest imageRequest, c cVar, AtomicBoolean atomicBoolean) {
        this.g = acVar;
        this.a = jVar;
        this.b = ajVar;
        this.c = str;
        this.d = imageRequest;
        this.e = cVar;
        this.f = atomicBoolean;
    }

    public Object a(g<b> gVar) throws Exception {
        if (gVar.c() || gVar.d()) {
            return gVar;
        }
        try {
            if (gVar.e() != null) {
                return ac.a(this.g, this.a, this.b, this.d, (b) gVar.e(), this.e, this.f);
            }
            ac.a(this.g, this.a, this.b, this.c);
            return null;
        } catch (Exception e) {
            return null;
        }
    }
}
