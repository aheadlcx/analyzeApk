package com.facebook.imagepipeline.c;

import com.facebook.cache.common.b;
import com.facebook.common.internal.i;
import com.facebook.common.memory.PooledByteBuffer;
import com.facebook.common.memory.c;
import com.facebook.imagepipeline.b.f;

public class m {
    public static h<b, PooledByteBuffer> a(i<u> iVar, c cVar, f fVar) {
        Object hVar = new h(new ac<PooledByteBuffer>() {
            public int a(PooledByteBuffer pooledByteBuffer) {
                return pooledByteBuffer.size();
            }
        }, new w(), iVar, fVar, false);
        cVar.a(hVar);
        return hVar;
    }
}
