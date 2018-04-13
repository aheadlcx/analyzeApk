package com.facebook.animated.gif;

import com.facebook.common.e.a;
import com.facebook.common.internal.d;
import com.facebook.common.internal.g;
import com.facebook.imagepipeline.animated.base.AnimatedDrawableFrameInfo;
import com.facebook.imagepipeline.animated.base.AnimatedDrawableFrameInfo.BlendOperation;
import com.facebook.imagepipeline.animated.base.AnimatedDrawableFrameInfo.DisposalMethod;
import com.facebook.imagepipeline.animated.base.i;
import com.facebook.imagepipeline.animated.base.j;
import com.facebook.imagepipeline.animated.factory.e;
import java.nio.ByteBuffer;
import javax.annotation.concurrent.ThreadSafe;

@d
@ThreadSafe
public class GifImage implements i, e {
    private static volatile boolean a;
    @d
    private long mNativeContext;

    private static native GifImage nativeCreateFromDirectByteBuffer(ByteBuffer byteBuffer);

    private static native GifImage nativeCreateFromNativeMemory(long j, int i);

    private native void nativeDispose();

    private native void nativeFinalize();

    private native int nativeGetDuration();

    private native GifFrame nativeGetFrame(int i);

    private native int nativeGetFrameCount();

    private native int[] nativeGetFrameDurations();

    private native int nativeGetHeight();

    private native int nativeGetLoopCount();

    private native int nativeGetSizeInBytes();

    private native int nativeGetWidth();

    public /* synthetic */ j c(int i) {
        return a(i);
    }

    private static synchronized void h() {
        synchronized (GifImage.class) {
            if (!a) {
                a = true;
                a.a("gifimage");
            }
        }
    }

    public static GifImage a(long j, int i) {
        h();
        g.a(j != 0);
        return nativeCreateFromNativeMemory(j, i);
    }

    public i b(long j, int i) {
        return a(j, i);
    }

    @d
    GifImage(long j) {
        this.mNativeContext = j;
    }

    protected void finalize() {
        nativeFinalize();
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

    public int[] d() {
        return nativeGetFrameDurations();
    }

    public int e() {
        int nativeGetLoopCount = nativeGetLoopCount();
        switch (nativeGetLoopCount) {
            case -1:
                return 1;
            case 0:
                return 0;
            default:
                return nativeGetLoopCount + 1;
        }
    }

    public GifFrame a(int i) {
        return nativeGetFrame(i);
    }

    public boolean f() {
        return false;
    }

    public int g() {
        return nativeGetSizeInBytes();
    }

    public AnimatedDrawableFrameInfo b(int i) {
        GifFrame a = a(i);
        try {
            AnimatedDrawableFrameInfo animatedDrawableFrameInfo = new AnimatedDrawableFrameInfo(i, a.e(), a.f(), a.c(), a.d(), BlendOperation.BLEND_WITH_PREVIOUS, d(a.g()));
            return animatedDrawableFrameInfo;
        } finally {
            a.a();
        }
    }

    private static DisposalMethod d(int i) {
        if (i == 0) {
            return DisposalMethod.DISPOSE_DO_NOT;
        }
        if (i == 1) {
            return DisposalMethod.DISPOSE_DO_NOT;
        }
        if (i == 2) {
            return DisposalMethod.DISPOSE_TO_BACKGROUND;
        }
        if (i == 3) {
            return DisposalMethod.DISPOSE_TO_PREVIOUS;
        }
        return DisposalMethod.DISPOSE_DO_NOT;
    }
}
