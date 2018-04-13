package com.facebook.drawee.backends.pipeline;

import com.facebook.common.internal.ImmutableList;
import com.facebook.common.internal.Supplier;
import com.facebook.common.internal.Suppliers;
import com.facebook.imagepipeline.drawable.DrawableFactory;
import javax.annotation.Nullable;

public class DraweeConfig {
    @Nullable
    private final ImmutableList<DrawableFactory> mCustomDrawableFactories;
    private final Supplier<Boolean> mDebugOverlayEnabledSupplier;
    @Nullable
    private final PipelineDraweeControllerFactory mPipelineDraweeControllerFactory;

    private DraweeConfig(DraweeConfig$Builder draweeConfig$Builder) {
        Supplier access$100;
        this.mCustomDrawableFactories = DraweeConfig$Builder.access$000(draweeConfig$Builder) != null ? ImmutableList.copyOf(DraweeConfig$Builder.access$000(draweeConfig$Builder)) : null;
        if (DraweeConfig$Builder.access$100(draweeConfig$Builder) != null) {
            access$100 = DraweeConfig$Builder.access$100(draweeConfig$Builder);
        } else {
            access$100 = Suppliers.of(Boolean.valueOf(false));
        }
        this.mDebugOverlayEnabledSupplier = access$100;
        this.mPipelineDraweeControllerFactory = DraweeConfig$Builder.access$200(draweeConfig$Builder);
    }

    @Nullable
    public ImmutableList<DrawableFactory> getCustomDrawableFactories() {
        return this.mCustomDrawableFactories;
    }

    @Nullable
    public PipelineDraweeControllerFactory getPipelineDraweeControllerFactory() {
        return this.mPipelineDraweeControllerFactory;
    }

    public static DraweeConfig$Builder newBuilder() {
        return new DraweeConfig$Builder();
    }

    public Supplier<Boolean> getDebugOverlayEnabledSupplier() {
        return this.mDebugOverlayEnabledSupplier;
    }
}
