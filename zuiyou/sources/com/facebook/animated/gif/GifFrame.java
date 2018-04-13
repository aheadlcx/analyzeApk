package com.facebook.animated.gif;

import android.graphics.Bitmap;
import com.facebook.common.internal.d;
import com.facebook.imagepipeline.animated.base.j;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public class GifFrame implements j {
    @d
    private long mNativeContext;

    private native void nativeDispose();

    private native void nativeFinalize();

    private native int nativeGetDisposalMode();

    private native int nativeGetDurationMs();

    private native int nativeGetHeight();

    private native int nativeGetTransparentPixelColor();

    private native int nativeGetWidth();

    private native int nativeGetXOffset();

    private native int nativeGetYOffset();

    private native boolean nativeHasTransparency();

    private native void nativeRenderFrame(int i, int i2, Bitmap bitmap);

    @d
    GifFrame(long j) {
        this.mNativeContext = j;
    }

    protected void finalize() {
        nativeFinalize();
    }

    public void a() {
        nativeDispose();
    }

    public void a(int i, int i2, Bitmap bitmap) {
        nativeRenderFrame(i, i2, bitmap);
    }

    public int b() {
        return nativeGetDurationMs();
    }

    public int c() {
        return nativeGetWidth();
    }

    public int d() {
        return nativeGetHeight();
    }

    public int e() {
        return nativeGetXOffset();
    }

    public int f() {
        return nativeGetYOffset();
    }

    public int g() {
        return nativeGetDisposalMode();
    }
}
