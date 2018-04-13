package com.facebook.imagepipeline.memory;

import android.util.SparseIntArray;
import com.facebook.common.internal.g;
import com.facebook.common.memory.a;
import com.facebook.common.memory.c;
import com.facebook.imagepipeline.memory.BasePool.InvalidSizeException;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public class GenericByteArrayPool extends BasePool<byte[]> implements a {
    private final int[] mBucketSizes;

    public GenericByteArrayPool(c cVar, PoolParams poolParams, PoolStatsTracker poolStatsTracker) {
        super(cVar, poolParams, poolStatsTracker);
        SparseIntArray sparseIntArray = poolParams.bucketSizes;
        this.mBucketSizes = new int[sparseIntArray.size()];
        for (int i = 0; i < sparseIntArray.size(); i++) {
            this.mBucketSizes[i] = sparseIntArray.keyAt(i);
        }
        initialize();
    }

    public int getMinBufferSize() {
        return this.mBucketSizes[0];
    }

    protected byte[] alloc(int i) {
        return new byte[i];
    }

    protected void free(byte[] bArr) {
        g.a((Object) bArr);
    }

    protected int getSizeInBytes(int i) {
        return i;
    }

    protected int getBucketedSize(int i) {
        if (i <= 0) {
            throw new InvalidSizeException(Integer.valueOf(i));
        }
        for (int i2 : this.mBucketSizes) {
            if (i2 >= i) {
                return i2;
            }
        }
        return i;
    }

    protected int getBucketedSizeForValue(byte[] bArr) {
        g.a((Object) bArr);
        return bArr.length;
    }
}
