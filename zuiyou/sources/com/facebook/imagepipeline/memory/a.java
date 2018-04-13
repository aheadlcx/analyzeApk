package com.facebook.imagepipeline.memory;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import com.facebook.common.internal.g;
import com.facebook.common.memory.c;
import javax.annotation.concurrent.ThreadSafe;

@TargetApi(21)
@ThreadSafe
public class a extends BasePool<Bitmap> {
    protected /* synthetic */ Object alloc(int i) {
        return a(i);
    }

    protected /* synthetic */ void free(Object obj) {
        a((Bitmap) obj);
    }

    protected /* synthetic */ int getBucketedSizeForValue(Object obj) {
        return b((Bitmap) obj);
    }

    protected /* synthetic */ boolean isReusable(Object obj) {
        return c((Bitmap) obj);
    }

    public a(c cVar, PoolParams poolParams, PoolStatsTracker poolStatsTracker) {
        super(cVar, poolParams, poolStatsTracker);
        initialize();
    }

    protected Bitmap a(int i) {
        return Bitmap.createBitmap(1, (int) Math.ceil(((double) i) / 2.0d), Config.RGB_565);
    }

    protected void a(Bitmap bitmap) {
        g.a((Object) bitmap);
        bitmap.recycle();
    }

    protected int getBucketedSize(int i) {
        return i;
    }

    protected int b(Bitmap bitmap) {
        g.a((Object) bitmap);
        return bitmap.getAllocationByteCount();
    }

    protected int getSizeInBytes(int i) {
        return i;
    }

    protected boolean c(Bitmap bitmap) {
        g.a((Object) bitmap);
        return !bitmap.isRecycled() && bitmap.isMutable();
    }
}
