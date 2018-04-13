package com.facebook.imagepipeline.producers;

import com.facebook.common.internal.g;
import com.facebook.common.references.a;
import com.facebook.imagepipeline.b.f;
import com.facebook.imagepipeline.request.d;
import java.util.concurrent.Executor;

public class ah implements ai<a<com.facebook.imagepipeline.g.c>> {
    private final ai<a<com.facebook.imagepipeline.g.c>> a;
    private final f b;
    private final Executor c;

    class c extends m<a<com.facebook.imagepipeline.g.c>, a<com.facebook.imagepipeline.g.c>> {
        final /* synthetic */ ah a;

        private c(ah ahVar, ah$a ah_a) {
            this.a = ahVar;
            super(ah_a);
        }

        protected void a(a<com.facebook.imagepipeline.g.c> aVar, boolean z) {
            if (z) {
                d().b(aVar, z);
            }
        }
    }

    public ah(ai<a<com.facebook.imagepipeline.g.c>> aiVar, f fVar, Executor executor) {
        this.a = (ai) g.a((Object) aiVar);
        this.b = fVar;
        this.c = (Executor) g.a((Object) executor);
    }

    public void a(j<a<com.facebook.imagepipeline.g.c>> jVar, aj ajVar) {
        j ah_b;
        al c = ajVar.c();
        com.facebook.imagepipeline.request.c p = ajVar.a().p();
        ah$a ah_a = new ah$a(this, jVar, c, ajVar.b(), p, ajVar);
        if (p instanceof d) {
            ah_b = new ah$b(this, ah_a, (d) p, ajVar, null);
        } else {
            ah_b = new c(ah_a);
        }
        this.a.a(ah_b, ajVar);
    }
}
