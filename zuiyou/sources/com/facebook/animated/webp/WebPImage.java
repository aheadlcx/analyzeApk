package com.facebook.animated.webp;

import com.facebook.common.internal.d;
import com.facebook.common.internal.g;
import com.facebook.imagepipeline.animated.base.AnimatedDrawableFrameInfo;
import com.facebook.imagepipeline.animated.base.AnimatedDrawableFrameInfo.BlendOperation;
import com.facebook.imagepipeline.animated.base.AnimatedDrawableFrameInfo.DisposalMethod;
import com.facebook.imagepipeline.animated.base.i;
import com.facebook.imagepipeline.animated.base.j;
import com.facebook.imagepipeline.animated.factory.e;
import com.facebook.imagepipeline.nativecode.b;
import java.nio.ByteBuffer;
import javax.annotation.concurrent.ThreadSafe;

@d
@ThreadSafe
public class WebPImage implements i, e {
    @d
    private long mNativeContext;

    private static native WebPImage nativeCreateFromDirectByteBuffer(ByteBuffer byteBuffer);

    private static native WebPImage nativeCreateFromNativeMemory(long j, int i);

    private native void nativeDispose();

    private native void nativeFinalize();

    private native int nativeGetDuration();

    private native WebPFrame nativeGetFrame(int i);

    private native int nativeGetFrameCount();

    private native int[] nativeGetFrameDurations();

    private native int nativeGetHeight();

    private native int nativeGetLoopCount();

    private native int nativeGetSizeInBytes();

    private native int nativeGetWidth();

    public /* synthetic */ j c(int i) {
        return a(i);
    }

    @d
    WebPImage(long j) {
        this.mNativeContext = j;
    }

    protected void finalize() {
        nativeFinalize();
    }

    public void h() {
        nativeDispose();
    }

    public static WebPImage a(long j, int i) {
        b.a();
        g.a(j != 0);
        return nativeCreateFromNativeMemory(j, i);
    }

    public i b(long j, int i) {
        return a(j, i);
    }

    public int a() {
        return nativeGetWidth();
    }

    public int b() {
        return nativeGetHeight();
    }

    public int c() {
        return nativeGetFrameCount();
    }

    public int i() {
        return nativeGetDuration();
    }

    public int[] d() {
        return nativeGetFrameDurations();
    }

    public int e() {
        return nativeGetLoopCount();
    }

    public WebPFrame a(int i) {
        return nativeGetFrame(i);
    }

    public int g() {
        return nativeGetSizeInBytes();
    }

    public boolean f() {
        return true;
    }

    public AnimatedDrawableFrameInfo b(int i) {
        WebPFrame a = a(i);
        try {
            AnimatedDrawableFrameInfo animatedDrawableFrameInfo = new AnimatedDrawableFrameInfo(i, a.e(), a.f(), a.c(), a.d(), a.h() ? BlendOperation.BLEND_WITH_PREVIOUS : BlendOperation.NO_BLEND, a.g() ? DisposalMethod.DISPOSE_TO_BACKGROUND : DisposalMethod.DISPOSE_DO_NOT);
            return animatedDrawableFrameInfo;
        } finally {
            a.a();
        }
    }
}
