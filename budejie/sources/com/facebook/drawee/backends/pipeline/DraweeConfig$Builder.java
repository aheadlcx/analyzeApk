package com.facebook.drawee.backends.pipeline;

import com.facebook.common.internal.Preconditions;
import com.facebook.common.internal.Supplier;
import com.facebook.common.internal.Suppliers;
import com.facebook.imagepipeline.drawable.DrawableFactory;
import java.util.ArrayList;
import java.util.List;

public class DraweeConfig$Builder {
    private List<DrawableFactory> mCustomDrawableFactories;
    private Supplier<Boolean> mDebugOverlayEnabledSupplier;
    private PipelineDraweeControllerFactory mPipelineDraweeControllerFactory;

    public DraweeConfig$Builder addCustomDrawableFactory(DrawableFactory drawableFactory) {
        if (this.mCustomDrawableFactories == null) {
            this.mCustomDrawableFactories = new ArrayList();
        }
        this.mCustomDrawableFactories.add(drawableFactory);
        return this;
    }

    public DraweeConfig$Builder setDrawDebugOverlay(boolean z) {
        return setDebugOverlayEnabledSupplier(Suppliers.of(Boolean.valueOf(z)));
    }

    public DraweeConfig$Builder setDebugOverlayEnabledSupplier(Supplier<Boolean> supplier) {
        Preconditions.checkNotNull(supplier);
        this.mDebugOverlayEnabledSupplier = supplier;
        return this;
    }

    public DraweeConfig$Builder setPipelineDraweeControllerFactory(PipelineDraweeControllerFactory pipelineDraweeControllerFactory) {
        this.mPipelineDraweeControllerFactory = pipelineDraweeControllerFactory;
        return this;
    }

    public DraweeConfig build() {
        return new DraweeConfig(this, null);
    }
}
