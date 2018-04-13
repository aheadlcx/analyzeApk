package com.facebook.imagepipeline.f;

import com.facebook.c.b;
import com.facebook.imagepipeline.common.a;
import com.facebook.imagepipeline.g.c;
import com.facebook.imagepipeline.g.e;
import com.facebook.imagepipeline.g.h;

class a$1 implements b {
    final /* synthetic */ a a;

    a$1(a aVar) {
        this.a = aVar;
    }

    public c a(e eVar, int i, h hVar, a aVar) {
        com.facebook.c.c e = eVar.e();
        if (e == b.a) {
            return this.a.b(eVar, i, hVar, aVar);
        }
        if (e == b.c) {
            return this.a.a(eVar, aVar);
        }
        if (e == b.i) {
            return this.a.c(eVar, aVar);
        }
        if (e != com.facebook.c.c.a) {
            return this.a.b(eVar, aVar);
        }
        throw new IllegalArgumentException("unknown image format");
    }
}
