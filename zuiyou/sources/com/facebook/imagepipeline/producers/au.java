package com.facebook.imagepipeline.producers;

import com.facebook.common.internal.g;
import com.facebook.imagepipeline.common.c;
import com.facebook.imagepipeline.g.e;

public class au implements ai<e> {
    private final av<e>[] a;

    private class a extends m<e, e> {
        final /* synthetic */ au a;
        private final aj b;
        private final int c;
        private final c d = this.b.a().g();

        public a(au auVar, j<e> jVar, aj ajVar, int i) {
            this.a = auVar;
            super(jVar);
            this.b = ajVar;
            this.c = i;
        }

        protected void a(e eVar, boolean z) {
            if (eVar != null && (!z || aw.a(eVar, this.d))) {
                d().b(eVar, z);
            } else if (z) {
                e.d(eVar);
                if (!this.a.a(this.c + 1, d(), this.b)) {
                    d().b(null, true);
                }
            }
        }

        protected void a(Throwable th) {
            if (!this.a.a(this.c + 1, d(), this.b)) {
                d().b(th);
            }
        }
    }

    public au(av<e>... avVarArr) {
        this.a = (av[]) g.a((Object) avVarArr);
        g.a(0, this.a.length);
    }

    public void a(j<e> jVar, aj ajVar) {
        if (ajVar.a().g() == null) {
            jVar.b(null, true);
        } else if (!a(0, jVar, ajVar)) {
            jVar.b(null, true);
        }
    }

    private boolean a(int i, j<e> jVar, aj ajVar) {
        int a = a(i, ajVar.a().g());
        if (a == -1) {
            return false;
        }
        this.a[a].a(new a(this, jVar, ajVar, a), ajVar);
        return true;
    }

    private int a(int i, c cVar) {
        while (i < this.a.length) {
            if (this.a[i].a(cVar)) {
                return i;
            }
            i++;
        }
        return -1;
    }
}
