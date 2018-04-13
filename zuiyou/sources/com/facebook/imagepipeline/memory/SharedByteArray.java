package com.facebook.imagepipeline.memory;

import com.facebook.common.internal.g;
import com.facebook.common.internal.k;
import com.facebook.common.memory.MemoryTrimType;
import com.facebook.common.memory.b;
import com.facebook.common.references.a;
import com.facebook.common.references.c;
import java.util.concurrent.Semaphore;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public class SharedByteArray implements b {
    final com.facebook.common.references.b<byte[]> mByteArraySoftRef;
    final int mMaxByteArraySize;
    final int mMinByteArraySize;
    private final c<byte[]> mResourceReleaser;
    final Semaphore mSemaphore;

    public SharedByteArray(com.facebook.common.memory.c cVar, PoolParams poolParams) {
        boolean z = false;
        g.a((Object) cVar);
        g.a(poolParams.minBucketSize > 0);
        if (poolParams.maxBucketSize >= poolParams.minBucketSize) {
            z = true;
        }
        g.a(z);
        this.mMaxByteArraySize = poolParams.maxBucketSize;
        this.mMinByteArraySize = poolParams.minBucketSize;
        this.mByteArraySoftRef = new com.facebook.common.references.b();
        this.mSemaphore = new Semaphore(1);
        this.mResourceReleaser = new c<byte[]>() {
            public void release(byte[] bArr) {
                SharedByteArray.this.mSemaphore.release();
            }
        };
        cVar.a(this);
    }

    public a<byte[]> get(int i) {
        boolean z = true;
        g.a(i > 0, (Object) "Size must be greater than zero");
        if (i > this.mMaxByteArraySize) {
            z = false;
        }
        g.a(z, (Object) "Requested size is too big");
        this.mSemaphore.acquireUninterruptibly();
        try {
            return a.a(getByteArray(i), this.mResourceReleaser);
        } catch (Throwable th) {
            this.mSemaphore.release();
            RuntimeException b = k.b(th);
        }
    }

    private byte[] getByteArray(int i) {
        int bucketedSize = getBucketedSize(i);
        byte[] bArr = (byte[]) this.mByteArraySoftRef.a();
        if (bArr == null || bArr.length < bucketedSize) {
            return allocateByteArray(bucketedSize);
        }
        return bArr;
    }

    public void trim(MemoryTrimType memoryTrimType) {
        if (this.mSemaphore.tryAcquire()) {
            try {
                this.mByteArraySoftRef.b();
            } finally {
                this.mSemaphore.release();
            }
        }
    }

    int getBucketedSize(int i) {
        return Integer.highestOneBit(Math.max(i, this.mMinByteArraySize) - 1) * 2;
    }

    private synchronized byte[] allocateByteArray(int i) {
        Object obj;
        this.mByteArraySoftRef.b();
        obj = new byte[i];
        this.mByteArraySoftRef.a(obj);
        return obj;
    }
}
