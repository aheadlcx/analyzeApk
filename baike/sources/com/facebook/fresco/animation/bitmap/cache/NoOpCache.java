package com.facebook.fresco.animation.bitmap.cache;

import android.graphics.Bitmap;
import com.facebook.common.references.CloseableReference;
import com.facebook.fresco.animation.bitmap.BitmapFrameCache;
import com.facebook.fresco.animation.bitmap.BitmapFrameCache.FrameCacheListener;
import javax.annotation.Nullable;

public class NoOpCache implements BitmapFrameCache {
    @Nullable
    public CloseableReference<Bitmap> getCachedFrame(int i) {
        return null;
    }

    @Nullable
    public CloseableReference<Bitmap> getFallbackFrame(int i) {
        return null;
    }

    @Nullable
    public CloseableReference<Bitmap> getBitmapToReuseForFrame(int i, int i2, int i3) {
        return null;
    }

    public boolean contains(int i) {
        return false;
    }

    public int getSizeInBytes() {
        return 0;
    }

    public void clear() {
    }

    public void onFrameRendered(int i, CloseableReference<Bitmap> closeableReference, int i2) {
    }

    public void onFramePrepared(int i, CloseableReference<Bitmap> closeableReference, int i2) {
    }

    public void setFrameCacheListener(FrameCacheListener frameCacheListener) {
    }
}
