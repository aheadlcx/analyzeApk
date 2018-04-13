package com.facebook.fresco.animation.bitmap;

import android.graphics.Bitmap;
import com.facebook.common.references.CloseableReference;
import javax.annotation.Nullable;

public interface BitmapFrameCache {

    public interface FrameCacheListener {
        void onFrameCached(BitmapFrameCache bitmapFrameCache, int i);

        void onFrameEvicted(BitmapFrameCache bitmapFrameCache, int i);
    }

    void clear();

    boolean contains(int i);

    @Nullable
    CloseableReference<Bitmap> getBitmapToReuseForFrame(int i, int i2, int i3);

    @Nullable
    CloseableReference<Bitmap> getCachedFrame(int i);

    @Nullable
    CloseableReference<Bitmap> getFallbackFrame(int i);

    int getSizeInBytes();

    void onFramePrepared(int i, CloseableReference<Bitmap> closeableReference, int i2);

    void onFrameRendered(int i, CloseableReference<Bitmap> closeableReference, int i2);

    void setFrameCacheListener(FrameCacheListener frameCacheListener);
}
