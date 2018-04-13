package com.facebook.imagepipeline.c;

import com.facebook.cache.common.b;
import com.facebook.common.internal.i;
import com.facebook.imagepipeline.b.f;
import com.facebook.imagepipeline.g.c;

public class a {
    public static h<b, c> a(i<u> iVar, com.facebook.common.memory.c cVar, f fVar, boolean z) {
        Object hVar = new h(new ac<c>() {
            public int a(c cVar) {
                return cVar.d();
            }
        }, new d(), iVar, fVar, z);
        cVar.a(hVar);
        return hVar;
    }
}
