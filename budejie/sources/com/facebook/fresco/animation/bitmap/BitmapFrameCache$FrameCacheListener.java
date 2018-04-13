package com.facebook.fresco.animation.bitmap;

public interface BitmapFrameCache$FrameCacheListener {
    void onFrameCached(BitmapFrameCache bitmapFrameCache, int i);

    void onFrameEvicted(BitmapFrameCache bitmapFrameCache, int i);
}
