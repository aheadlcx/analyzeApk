package com.facebook.imagepipeline.animated.base;

public class AnimatedDrawableFrameInfo {
    public final int a;
    public final int b;
    public final int c;
    public final int d;
    public final int e;
    public final BlendOperation f;
    public final DisposalMethod g;

    public enum BlendOperation {
        BLEND_WITH_PREVIOUS,
        NO_BLEND
    }

    public enum DisposalMethod {
        DISPOSE_DO_NOT,
        DISPOSE_TO_BACKGROUND,
        DISPOSE_TO_PREVIOUS
    }

    public AnimatedDrawableFrameInfo(int i, int i2, int i3, int i4, int i5, BlendOperation blendOperation, DisposalMethod disposalMethod) {
        this.a = i;
        this.b = i2;
        this.c = i3;
        this.d = i4;
        this.e = i5;
        this.f = blendOperation;
        this.g = disposalMethod;
    }
}
