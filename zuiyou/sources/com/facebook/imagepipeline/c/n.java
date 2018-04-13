package com.facebook.imagepipeline.c;

import com.facebook.cache.common.b;
import com.facebook.common.memory.PooledByteBuffer;

public class n {
    public static t<b, PooledByteBuffer> a(h<b, PooledByteBuffer> hVar, final o oVar) {
        oVar.b((h) hVar);
        return new p(hVar, new v<b>() {
            public void a(b bVar) {
                oVar.b(bVar);
            }

            public void a() {
                oVar.d();
            }

            public void b() {
                oVar.c();
            }
        });
    }
}
