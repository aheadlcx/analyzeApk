package com.facebook.imagepipeline.c;

import com.facebook.imagepipeline.g.c;

public class b {
    public static t<com.facebook.cache.common.b, c> a(h<com.facebook.cache.common.b, c> hVar, final o oVar) {
        oVar.a((h) hVar);
        return new p(hVar, new v<com.facebook.cache.common.b>() {
            public void a(com.facebook.cache.common.b bVar) {
                oVar.a(bVar);
            }

            public void a() {
                oVar.b();
            }

            public void b() {
                oVar.a();
            }
        });
    }
}
