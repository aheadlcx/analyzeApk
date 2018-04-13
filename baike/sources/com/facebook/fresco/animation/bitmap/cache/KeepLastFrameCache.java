package com.facebook.fresco.animation.bitmap.cache;

import android.graphics.Bitmap;
import com.facebook.common.references.CloseableReference;
import com.facebook.fresco.animation.bitmap.BitmapFrameCache;
import com.facebook.fresco.animation.bitmap.BitmapFrameCache.FrameCacheListener;
import com.facebook.imageutils.BitmapUtil;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;

public class KeepLastFrameCache implements BitmapFrameCache {
    private static final int FRAME_NUMBER_UNSET = -1;
    @Nullable
    private FrameCacheListener mFrameCacheListener;
    @GuardedBy("this")
    @Nullable
    private CloseableReference<Bitmap> mLastBitmapReference;
    private int mLastFrameNumber = -1;

    @Nullable
    public synchronized CloseableReference<Bitmap> getCachedFrame(int i) {
        CloseableReference<Bitmap> cloneOrNull;
        if (this.mLastFrameNumber == i) {
            cloneOrNull = CloseableReference.cloneOrNull(this.mLastBitmapReference);
        } else {
            cloneOrNull = null;
        }
        return cloneOrNull;
    }

    @Nullable
    public synchronized CloseableReference<Bitmap> getFallbackFrame(int i) {
        return CloseableReference.cloneOrNull(this.mLastBitmapReference);
    }

    public synchronized CloseableReference<Bitmap> getBitmapToReuseForFrame(int i, int i2, int i3) {
        CloseableReference<Bitmap> cloneOrNull;
        try {
            cloneOrNull = CloseableReference.cloneOrNull(this.mLastBitmapReference);
            closeAndResetLastBitmapReference();
        } catch (Throwable th) {
            closeAndResetLastBitmapReference();
        }
        return cloneOrNull;
    }

    public synchronized boolean contains(int i) {
        boolean z;
        z = i == this.mLastFrameNumber && CloseableReference.isValid(this.mLastBitmapReference);
        return z;
    }

    public synchronized int getSizeInBytes() {
        int i;
        if (this.mLastBitmapReference == null) {
            i = 0;
        } else {
            i = BitmapUtil.getSizeInBytes((Bitmap) this.mLastBitmapReference.get());
        }
        return i;
    }

    public synchronized void clear() {
        closeAndResetLastBitmapReference();
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void onFrameRendered(int r3, com.facebook.common.references.CloseableReference<android.graphics.Bitmap> r4, int r5) {
        /*
        r2 = this;
        monitor-enter(r2);
        if (r4 == 0) goto L_0x001b;
    L_0x0003:
        r0 = r2.mLastBitmapReference;	 Catch:{ all -> 0x0042 }
        if (r0 == 0) goto L_0x001b;
    L_0x0007:
        r0 = r4.get();	 Catch:{ all -> 0x0042 }
        r0 = (android.graphics.Bitmap) r0;	 Catch:{ all -> 0x0042 }
        r1 = r2.mLastBitmapReference;	 Catch:{ all -> 0x0042 }
        r1 = r1.get();	 Catch:{ all -> 0x0042 }
        r0 = r0.equals(r1);	 Catch:{ all -> 0x0042 }
        if (r0 == 0) goto L_0x001b;
    L_0x0019:
        monitor-exit(r2);
        return;
    L_0x001b:
        r0 = r2.mLastBitmapReference;	 Catch:{ all -> 0x0042 }
        com.facebook.common.references.CloseableReference.closeSafely(r0);	 Catch:{ all -> 0x0042 }
        r0 = r2.mFrameCacheListener;	 Catch:{ all -> 0x0042 }
        if (r0 == 0) goto L_0x0030;
    L_0x0024:
        r0 = r2.mLastFrameNumber;	 Catch:{ all -> 0x0042 }
        r1 = -1;
        if (r0 == r1) goto L_0x0030;
    L_0x0029:
        r0 = r2.mFrameCacheListener;	 Catch:{ all -> 0x0042 }
        r1 = r2.mLastFrameNumber;	 Catch:{ all -> 0x0042 }
        r0.onFrameEvicted(r2, r1);	 Catch:{ all -> 0x0042 }
    L_0x0030:
        r0 = com.facebook.common.references.CloseableReference.cloneOrNull(r4);	 Catch:{ all -> 0x0042 }
        r2.mLastBitmapReference = r0;	 Catch:{ all -> 0x0042 }
        r0 = r2.mFrameCacheListener;	 Catch:{ all -> 0x0042 }
        if (r0 == 0) goto L_0x003f;
    L_0x003a:
        r0 = r2.mFrameCacheListener;	 Catch:{ all -> 0x0042 }
        r0.onFrameCached(r2, r3);	 Catch:{ all -> 0x0042 }
    L_0x003f:
        r2.mLastFrameNumber = r3;	 Catch:{ all -> 0x0042 }
        goto L_0x0019;
    L_0x0042:
        r0 = move-exception;
        monitor-exit(r2);
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.fresco.animation.bitmap.cache.KeepLastFrameCache.onFrameRendered(int, com.facebook.common.references.CloseableReference, int):void");
    }

    public void onFramePrepared(int i, CloseableReference<Bitmap> closeableReference, int i2) {
    }

    public void setFrameCacheListener(FrameCacheListener frameCacheListener) {
        this.mFrameCacheListener = frameCacheListener;
    }

    private synchronized void closeAndResetLastBitmapReference() {
        if (!(this.mFrameCacheListener == null || this.mLastFrameNumber == -1)) {
            this.mFrameCacheListener.onFrameEvicted(this, this.mLastFrameNumber);
        }
        CloseableReference.closeSafely(this.mLastBitmapReference);
        this.mLastBitmapReference = null;
        this.mLastFrameNumber = -1;
    }
}
