package com.facebook.imagepipeline.c;

import com.facebook.common.references.c;
import com.facebook.imagepipeline.c.h.b;

class h$3 implements c<V> {
    final /* synthetic */ b a;
    final /* synthetic */ h b;

    h$3(h hVar, b bVar) {
        this.b = hVar;
        this.a = bVar;
    }

    public void release(V v) {
        h.a(this.b, this.a);
    }
}
