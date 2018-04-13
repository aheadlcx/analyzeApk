package com.facebook.imagepipeline.core;

import com.facebook.common.internal.Supplier;
import com.facebook.common.webp.WebpBitmapFactory;
import com.facebook.common.webp.WebpBitmapFactory.WebpErrorLogger;
import com.facebook.imagepipeline.cache.MediaIdExtractor;
import javax.annotation.Nullable;

public class ImagePipelineExperiments {
    private final boolean mDecodeCancellationEnabled;
    private final boolean mExternalCreatedBitmapLogEnabled;
    private final MediaIdExtractor mMediaIdExtractor;
    private final Supplier<Boolean> mMediaVariationsIndexEnabled;
    private final boolean mPartialImageCachingEnabled;
    private final boolean mSuppressBitmapPrefetching;
    private final boolean mUseBitmapPrepareToDraw;
    private final boolean mUseDownsamplingRatioForResizing;
    private final WebpBitmapFactory mWebpBitmapFactory;
    private final WebpErrorLogger mWebpErrorLogger;
    private final boolean mWebpSupportEnabled;

    private ImagePipelineExperiments(ImagePipelineExperiments$Builder imagePipelineExperiments$Builder, ImagePipelineConfig$Builder imagePipelineConfig$Builder) {
        this.mWebpSupportEnabled = ImagePipelineExperiments$Builder.access$000(imagePipelineExperiments$Builder);
        this.mExternalCreatedBitmapLogEnabled = ImagePipelineExperiments$Builder.access$100(imagePipelineExperiments$Builder);
        if (ImagePipelineExperiments$Builder.access$200(imagePipelineExperiments$Builder) != null) {
            this.mMediaVariationsIndexEnabled = ImagePipelineExperiments$Builder.access$200(imagePipelineExperiments$Builder);
        } else {
            this.mMediaVariationsIndexEnabled = new ImagePipelineExperiments$1(this);
        }
        this.mMediaIdExtractor = ImagePipelineExperiments$Builder.access$300(imagePipelineExperiments$Builder);
        this.mWebpErrorLogger = ImagePipelineExperiments$Builder.access$400(imagePipelineExperiments$Builder);
        this.mDecodeCancellationEnabled = ImagePipelineExperiments$Builder.access$500(imagePipelineExperiments$Builder);
        this.mWebpBitmapFactory = ImagePipelineExperiments$Builder.access$600(imagePipelineExperiments$Builder);
        this.mSuppressBitmapPrefetching = ImagePipelineExperiments$Builder.access$700(imagePipelineExperiments$Builder);
        this.mUseDownsamplingRatioForResizing = ImagePipelineExperiments$Builder.access$800(imagePipelineExperiments$Builder);
        this.mUseBitmapPrepareToDraw = ImagePipelineExperiments$Builder.access$900(imagePipelineExperiments$Builder);
        this.mPartialImageCachingEnabled = ImagePipelineExperiments$Builder.access$1000(imagePipelineExperiments$Builder);
    }

    public boolean isExternalCreatedBitmapLogEnabled() {
        return this.mExternalCreatedBitmapLogEnabled;
    }

    public boolean getMediaVariationsIndexEnabled() {
        return ((Boolean) this.mMediaVariationsIndexEnabled.get()).booleanValue();
    }

    @Nullable
    public MediaIdExtractor getMediaIdExtractor() {
        return this.mMediaIdExtractor;
    }

    public boolean getUseDownsamplingRatioForResizing() {
        return this.mUseDownsamplingRatioForResizing;
    }

    public boolean isWebpSupportEnabled() {
        return this.mWebpSupportEnabled;
    }

    public boolean isDecodeCancellationEnabled() {
        return this.mDecodeCancellationEnabled;
    }

    public WebpErrorLogger getWebpErrorLogger() {
        return this.mWebpErrorLogger;
    }

    public WebpBitmapFactory getWebpBitmapFactory() {
        return this.mWebpBitmapFactory;
    }

    public boolean getUseBitmapPrepareToDraw() {
        return this.mUseBitmapPrepareToDraw;
    }

    public boolean isPartialImageCachingEnabled() {
        return this.mPartialImageCachingEnabled;
    }

    public static ImagePipelineExperiments$Builder newBuilder(ImagePipelineConfig$Builder imagePipelineConfig$Builder) {
        return new ImagePipelineExperiments$Builder(imagePipelineConfig$Builder);
    }
}
