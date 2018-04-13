package com.facebook.imagepipeline.memory;

import android.support.v4.view.ViewCompat;
import android.util.SparseIntArray;

public class DefaultNativeMemoryChunkPoolParams {
    private static final int LARGE_BUCKET_LENGTH = 2;
    private static final int SMALL_BUCKET_LENGTH = 5;

    public static PoolParams get() {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sparseIntArray.put(1024, 5);
        sparseIntArray.put(2048, 5);
        sparseIntArray.put(4096, 5);
        sparseIntArray.put(8192, 5);
        sparseIntArray.put(16384, 5);
        sparseIntArray.put(32768, 5);
        sparseIntArray.put(65536, 5);
        sparseIntArray.put(131072, 5);
        sparseIntArray.put(262144, 2);
        sparseIntArray.put(524288, 2);
        sparseIntArray.put(1048576, 2);
        return new PoolParams(getMaxSizeSoftCap(), getMaxSizeHardCap(), sparseIntArray);
    }

    private static int getMaxSizeSoftCap() {
        int min = (int) Math.min(Runtime.getRuntime().maxMemory(), 2147483647L);
        if (min < ViewCompat.MEASURED_STATE_TOO_SMALL) {
            return 3145728;
        }
        if (min < 33554432) {
            return 6291456;
        }
        return 12582912;
    }

    private static int getMaxSizeHardCap() {
        int min = (int) Math.min(Runtime.getRuntime().maxMemory(), 2147483647L);
        if (min < ViewCompat.MEASURED_STATE_TOO_SMALL) {
            return min / 2;
        }
        return (min / 4) * 3;
    }
}