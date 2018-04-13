package com.facebook.imagepipeline.memory;

import com.facebook.common.internal.g;
import com.facebook.common.references.c;
import java.util.Map;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public class FlexByteArrayPool {
    final a mDelegatePool;
    private final c<byte[]> mResourceReleaser;

    static class a extends GenericByteArrayPool {
        public a(com.facebook.common.memory.c cVar, PoolParams poolParams, PoolStatsTracker poolStatsTracker) {
            super(cVar, poolParams, poolStatsTracker);
        }

        Bucket<byte[]> newBucket(int i) {
            return new OOMSoftReferenceBucket(getSizeInBytes(i), this.mPoolParams.maxNumThreads, 0);
        }
    }

    public FlexByteArrayPool(com.facebook.common.memory.c cVar, PoolParams poolParams) {
        g.a(poolParams.maxNumThreads > 0);
        this.mDelegatePool = new a(cVar, poolParams, NoOpPoolStatsTracker.getInstance());
        this.mResourceReleaser = new c<byte[]>() {
            public void release(byte[] bArr) {
                FlexByteArrayPool.this.release(bArr);
            }
        };
    }

    public com.facebook.common.references.a<byte[]> get(int i) {
        return com.facebook.common.references.a.a(this.mDelegatePool.get(i), this.mResourceReleaser);
    }

    public void release(byte[] bArr) {
        this.mDelegatePool.release(bArr);
    }

    public Map<String, Integer> getStats() {
        return this.mDelegatePool.getStats();
    }

    public int getMinBufferSize() {
        return this.mDelegatePool.getMinBufferSize();
    }
}
