package com.facebook.imagepipeline.memory;

import android.support.v4.view.ViewCompat;
import android.util.SparseIntArray;

public class DefaultBitmapPoolParams {
    private static final SparseIntArray DEFAULT_BUCKETS = new SparseIntArray(0);
    private static final int MAX_SIZE_SOFT_CAP = 0;

    private DefaultBitmapPoolParams() {
    }

    private static int getMaxSizeHardCap() {
        int min = (int) Math.min(Runtime.getRuntime().maxMemory(), 2147483647L);
        if (min > ViewCompat.MEASURED_STATE_TOO_SMALL) {
            return (min / 4) * 3;
        }
        return min / 2;
    }

    public static PoolParams get() {
        return new PoolParams(0, getMaxSizeHardCap(), DEFAULT_BUCKETS);
    }
}
