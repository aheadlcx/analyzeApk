package com.facebook.fresco.animation.factory;

import android.graphics.Rect;
import com.facebook.imagepipeline.animated.base.AnimatedDrawableBackend;
import com.facebook.imagepipeline.animated.base.AnimatedImageResult;
import com.facebook.imagepipeline.animated.impl.AnimatedDrawableBackendImpl;
import com.facebook.imagepipeline.animated.impl.AnimatedDrawableBackendProvider;

class AnimatedFactoryV2Impl$5 implements AnimatedDrawableBackendProvider {
    final /* synthetic */ AnimatedFactoryV2Impl this$0;

    AnimatedFactoryV2Impl$5(AnimatedFactoryV2Impl animatedFactoryV2Impl) {
        this.this$0 = animatedFactoryV2Impl;
    }

    public AnimatedDrawableBackend get(AnimatedImageResult animatedImageResult, Rect rect) {
        return new AnimatedDrawableBackendImpl(AnimatedFactoryV2Impl.access$100(this.this$0), animatedImageResult, rect);
    }
}
