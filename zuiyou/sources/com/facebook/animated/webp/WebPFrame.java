package com.facebook.animated.webp;

import android.graphics.Bitmap;
import com.facebook.common.internal.d;
import com.facebook.imagepipeline.animated.base.j;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public class WebPFrame implements j {
    @d
    private long mNativeContext;

    private native void nativeDispose();

    private native void nativeFinalize();

    private native int nativeGetDurationMs();

    private native int nativeGetHeight();

    private native int nativeGetWidth();

    private native int nativeGetXOffset();

    private native int nativeGetYOffset();

    private native boolean nativeIsBlendWithPreviousFrame();

    private native void nativeRenderFrame(int i, int i2, Bitmap bitmap);

    private native boolean nativeShouldDisposeToBackgroundColor();

    @d
    WebPFrame(long j) {
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

    public boolean g() {
        return nativeShouldDisposeToBackgroundColor();
    }

    public boolean h() {
        return nativeIsBlendWithPreviousFrame();
    }
}
