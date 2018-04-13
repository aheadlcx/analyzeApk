package com.facebook.imagepipeline.producers;

import com.facebook.imagepipeline.c.l;
import com.facebook.imagepipeline.g.e;
import com.facebook.imagepipeline.request.ImageRequest$RequestLevel;

public class o implements ai<e> {
    private final ai<e> a;
    private final l b;

    private static class a extends m<e, e> {
        private final aj a;
        private final l b;

        private a(j<e> jVar, aj ajVar, l lVar) {
            super(jVar);
            this.a = ajVar;
            this.b = lVar;
        }

        public void a(e eVar, boolean z) {
            if (eVar != null && z) {
                this.b.a(eVar, this.a.a(), this.a.d());
            }
            d().b(eVar, z);
        }
    }

    public o(ai<e> aiVar, l lVar) {
        this.a = aiVar;
        this.b = lVar;
    }

    public void a(j<e> jVar, aj ajVar) {
        b(jVar, ajVar);
    }

    private void b(j<e> jVar, aj ajVar) {
        if (ajVar.e().getValue() >= ImageRequest$RequestLevel.DISK_CACHE.getValue()) {
            jVar.b(null, true);
            return;
        }
        if (ajVar.a().n()) {
            jVar = new a(jVar, ajVar, this.b);
        }
        this.a.a(jVar, ajVar);
    }
}
