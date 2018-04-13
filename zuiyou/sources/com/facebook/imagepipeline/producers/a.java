package com.facebook.imagepipeline.producers;

import com.facebook.imagepipeline.g.e;

public class a implements ai<e> {
    private final ai<e> a;

    private static class a extends m<e, e> {
        private a(j<e> jVar) {
            super(jVar);
        }

        protected void a(e eVar, boolean z) {
            if (eVar == null) {
                d().b(null, z);
                return;
            }
            if (!e.c(eVar)) {
                eVar.l();
            }
            d().b(eVar, z);
        }
    }

    public a(ai<e> aiVar) {
        this.a = aiVar;
    }

    public void a(j<e> jVar, aj ajVar) {
        this.a.a(new a(jVar), ajVar);
    }
}
