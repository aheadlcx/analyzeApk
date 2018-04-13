package com.facebook.imagepipeline.producers;

import com.facebook.imagepipeline.g.e;
import com.facebook.imagepipeline.request.ImageRequest;

class ac$a extends m<e, e> {
    final /* synthetic */ ac a;
    private final aj b;
    private final String c;

    public ac$a(ac acVar, j<e> jVar, aj ajVar, String str) {
        this.a = acVar;
        super(jVar);
        this.b = ajVar;
        this.c = str;
    }

    protected void a(e eVar, boolean z) {
        if (z && eVar != null) {
            a(eVar);
        }
        d().b(eVar, z);
    }

    private void a(e eVar) {
        ImageRequest a = this.b.a();
        if (a.n() && this.c != null) {
            ac.c(this.a).a(this.c, ac.a(this.a).a(a, eVar), ac.b(this.a).c(a, this.b.d()), eVar);
        }
    }
}
