package com.facebook.imagepipeline.core;

import com.facebook.common.internal.Supplier;
import com.facebook.common.webp.WebpBitmapFactory;
import com.facebook.common.webp.WebpBitmapFactory.WebpErrorLogger;
import com.facebook.imagepipeline.cache.MediaIdExtractor;

public class ImagePipelineExperiments$Builder {
    private final ImagePipelineConfig$Builder mConfigBuilder;
    private boolean mDecodeCancellationEnabled = false;
    private boolean mExternalCreatedBitmapLogEnabled = false;
    private MediaIdExtractor mMediaIdExtractor;
    private Supplier<Boolean> mMediaVariationsIndexEnabled = null;
    private boolean mPartialImageCachingEnabled = false;
    private boolean mSuppressBitmapPrefetching = false;
    private boolean mUseBitmapPrepareToDraw = false;
    private boolean mUseDownsamplingRatioForResizing = false;
    private WebpBitmapFactory mWebpBitmapFactory;
    private WebpErrorLogger mWebpErrorLogger;
    private boolean mWebpSupportEnabled = false;

    public ImagePipelineExperiments$Builder(ImagePipelineConfig$Builder imagePipelineConfig$Builder) {
        this.mConfigBuilder = imagePipelineConfig$Builder;
    }

    public ImagePipelineConfig$Builder setExternalCreatedBitmapLogEnabled(boolean z) {
        this.mExternalCreatedBitmapLogEnabled = z;
        return this.mConfigBuilder;
    }

    public ImagePipelineConfig$Builder setMediaVariationsIndexEnabled(Supplier<Boolean> supplier) {
        this.mMediaVariationsIndexEnabled = supplier;
        return this.mConfigBuilder;
    }

    public ImagePipelineConfig$Builder setMediaIdExtractor(MediaIdExtractor mediaIdExtractor) {
        this.mMediaIdExtractor = mediaIdExtractor;
        return this.mConfigBuilder;
    }

    public ImagePipelineConfig$Builder setWebpSupportEnabled(boolean z) {
        this.mWebpSupportEnabled = z;
        return this.mConfigBuilder;
    }

    public ImagePipelineConfig$Builder setUseDownsampligRatioForResizing(boolean z) {
        this.mUseDownsamplingRatioForResizing = z;
        return this.mConfigBuilder;
    }

    public ImagePipelineConfig$Builder setPartialImageCachingEnabled(boolean z) {
        this.mPartialImageCachingEnabled = z;
        return this.mConfigBuilder;
    }

    public boolean isPartialImageCachingEnabled() {
        return this.mPartialImageCachingEnabled;
    }

    public ImagePipelineConfig$Builder setDecodeCancellationEnabled(boolean z) {
        this.mDecodeCancellationEnabled = z;
        return this.mConfigBuilder;
    }

    public ImagePipelineConfig$Builder setWebpErrorLogger(WebpErrorLogger webpErrorLogger) {
        this.mWebpErrorLogger = webpErrorLogger;
        return this.mConfigBuilder;
    }

    public ImagePipelineConfig$Builder setWebpBitmapFactory(WebpBitmapFactory webpBitmapFactory) {
        this.mWebpBitmapFactory = webpBitmapFactory;
        return this.mConfigBuilder;
    }

    public ImagePipelineConfig$Builder setSuppressBitmapPrefetching(boolean z) {
        this.mSuppressBitmapPrefetching = z;
        return this.mConfigBuilder;
    }

    public ImagePipelineConfig$Builder setBitmapPrepareToDraw(boolean z) {
        this.mUseBitmapPrepareToDraw = z;
        return this.mConfigBuilder;
    }

    public ImagePipelineExperiments build() {
        return new ImagePipelineExperiments(this, this.mConfigBuilder, null);
    }
}
