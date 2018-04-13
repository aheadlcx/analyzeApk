package com.facebook.imagepipeline.producers;

import com.facebook.common.memory.PooledByteBuffer;
import com.facebook.imagepipeline.g.e;

public class an implements ai<com.facebook.common.references.a<PooledByteBuffer>> {
    private final ai<e> a;

    private class a extends m<e, com.facebook.common.references.a<PooledByteBuffer>> {
        final /* synthetic */ an a;

        private a(an anVar, j<com.facebook.common.references.a<PooledByteBuffer>> jVar) {
            this.a = anVar;
            super(jVar);
        }

        protected void a(e eVar, boolean z) {
            com.facebook.common.references.a aVar = null;
            try {
                if (e.e(eVar)) {
                    aVar = eVar.c();
                }
                d().b(aVar, z);
            } finally {
                com.facebook.common.references.a.c(aVar);
            }
        }
    }

    public an(ai<e> aiVar) {
        this.a = aiVar;
    }

    public void a(j<com.facebook.common.references.a<PooledByteBuffer>> jVar, aj ajVar) {
        this.a.a(new a(jVar), ajVar);
    }
}
