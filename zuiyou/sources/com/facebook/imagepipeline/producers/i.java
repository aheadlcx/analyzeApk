package com.facebook.imagepipeline.producers;

import com.facebook.imagepipeline.g.e;
import com.facebook.imagepipeline.request.ImageRequest;

public class i implements ai<e> {
    private final ai<e> a;
    private final ai<e> b;

    private class a extends m<e, e> {
        final /* synthetic */ i a;
        private aj b;

        private a(i iVar, j<e> jVar, aj ajVar) {
            this.a = iVar;
            super(jVar);
            this.b = ajVar;
        }

        protected void a(e eVar, boolean z) {
            ImageRequest a = this.b.a();
            boolean a2 = aw.a(eVar, a.g());
            if (eVar != null && (a2 || a.k())) {
                j d = d();
                boolean z2 = z && a2;
                d.b(eVar, z2);
            }
            if (z && !a2) {
                e.d(eVar);
                this.a.b.a(d(), this.b);
            }
        }

        protected void a(Throwable th) {
            this.a.b.a(d(), this.b);
        }
    }

    public i(ai<e> aiVar, ai<e> aiVar2) {
        this.a = aiVar;
        this.b = aiVar2;
    }

    public void a(j<e> jVar, aj ajVar) {
        this.a.a(new a(jVar, ajVar), ajVar);
    }
}
