package com.facebook.fresco.animation.bitmap.wrapper;

import android.graphics.Bitmap;
import com.facebook.common.references.CloseableReference;
import com.facebook.imagepipeline.animated.impl.AnimatedImageCompositor.Callback;
import javax.annotation.Nullable;

class AnimatedDrawableBackendFrameRenderer$1 implements Callback {
    final /* synthetic */ AnimatedDrawableBackendFrameRenderer this$0;

    AnimatedDrawableBackendFrameRenderer$1(AnimatedDrawableBackendFrameRenderer animatedDrawableBackendFrameRenderer) {
        this.this$0 = animatedDrawableBackendFrameRenderer;
    }

    public void onIntermediateResult(int i, Bitmap bitmap) {
    }

    @Nullable
    public CloseableReference<Bitmap> getCachedBitmap(int i) {
        return AnimatedDrawableBackendFrameRenderer.access$000(this.this$0).getCachedFrame(i);
    }
}
