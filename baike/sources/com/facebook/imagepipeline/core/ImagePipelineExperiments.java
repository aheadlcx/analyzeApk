package com.facebook.imagepipeline.core;

import android.content.Context;
import com.facebook.cache.common.CacheKey;
import com.facebook.common.internal.Supplier;
import com.facebook.common.internal.Suppliers;
import com.facebook.common.memory.ByteArrayPool;
import com.facebook.common.memory.PooledByteBuffer;
import com.facebook.common.memory.PooledByteBufferFactory;
import com.facebook.common.webp.WebpBitmapFactory;
import com.facebook.common.webp.WebpBitmapFactory.WebpErrorLogger;
import com.facebook.imagepipeline.bitmaps.PlatformBitmapFactory;
import com.facebook.imagepipeline.cache.BufferedDiskCache;
import com.facebook.imagepipeline.cache.CacheKeyFactory;
import com.facebook.imagepipeline.cache.MediaVariationsIndex;
import com.facebook.imagepipeline.cache.MemoryCache;
import com.facebook.imagepipeline.decoder.ImageDecoder;
import com.facebook.imagepipeline.decoder.ProgressiveJpegConfig;
import com.facebook.imagepipeline.image.CloseableImage;

public class ImagePipelineExperiments {
    private boolean mBitmapPrepareToDrawForPrefetch;
    private final int mBitmapPrepareToDrawMaxSizeBytes;
    private final int mBitmapPrepareToDrawMinSizeBytes;
    private final boolean mDecodeCancellationEnabled;
    private final boolean mExternalCreatedBitmapLogEnabled;
    private final Supplier<Boolean> mMediaVariationsIndexEnabled;
    private final boolean mPartialImageCachingEnabled;
    private final ProducerFactoryMethod mProducerFactoryMethod;
    private final Supplier<Boolean> mSmartResizingEnabled;
    private final boolean mSuppressBitmapPrefetching;
    private final boolean mUseBitmapPrepareToDraw;
    private final boolean mUseDownsamplingRatioForResizing;
    private final WebpBitmapFactory mWebpBitmapFactory;
    private final WebpErrorLogger mWebpErrorLogger;
    private final boolean mWebpSupportEnabled;

    public static class Builder {
        public boolean mBitmapPrepareToDrawForPrefetch = false;
        private int mBitmapPrepareToDrawMaxSizeBytes = 0;
        private int mBitmapPrepareToDrawMinSizeBytes = 0;
        private final com.facebook.imagepipeline.core.ImagePipelineConfig.Builder mConfigBuilder;
        private boolean mDecodeCancellationEnabled = false;
        private boolean mExternalCreatedBitmapLogEnabled = false;
        private Supplier<Boolean> mMediaVariationsIndexEnabled = null;
        private boolean mPartialImageCachingEnabled = false;
        private ProducerFactoryMethod mProducerFactoryMethod;
        private Supplier<Boolean> mSmartResizingEnabled = Suppliers.BOOLEAN_FALSE;
        private boolean mSuppressBitmapPrefetching = false;
        private boolean mUseBitmapPrepareToDraw = false;
        private boolean mUseDownsamplingRatioForResizing = false;
        private WebpBitmapFactory mWebpBitmapFactory;
        private WebpErrorLogger mWebpErrorLogger;
        private boolean mWebpSupportEnabled = false;

        public Builder(com.facebook.imagepipeline.core.ImagePipelineConfig.Builder builder) {
            this.mConfigBuilder = builder;
        }

        public com.facebook.imagepipeline.core.ImagePipelineConfig.Builder setExternalCreatedBitmapLogEnabled(boolean z) {
            this.mExternalCreatedBitmapLogEnabled = z;
            return this.mConfigBuilder;
        }

        public com.facebook.imagepipeline.core.ImagePipelineConfig.Builder setMediaVariationsIndexEnabled(Supplier<Boolean> supplier) {
            this.mMediaVariationsIndexEnabled = supplier;
            return this.mConfigBuilder;
        }

        public com.facebook.imagepipeline.core.ImagePipelineConfig.Builder setWebpSupportEnabled(boolean z) {
            this.mWebpSupportEnabled = z;
            return this.mConfigBuilder;
        }

        public com.facebook.imagepipeline.core.ImagePipelineConfig.Builder setUseDownsampligRatioForResizing(boolean z) {
            this.mUseDownsamplingRatioForResizing = z;
            return this.mConfigBuilder;
        }

        public com.facebook.imagepipeline.core.ImagePipelineConfig.Builder setPartialImageCachingEnabled(boolean z) {
            this.mPartialImageCachingEnabled = z;
            return this.mConfigBuilder;
        }

        public boolean isPartialImageCachingEnabled() {
            return this.mPartialImageCachingEnabled;
        }

        public com.facebook.imagepipeline.core.ImagePipelineConfig.Builder setDecodeCancellationEnabled(boolean z) {
            this.mDecodeCancellationEnabled = z;
            return this.mConfigBuilder;
        }

        public com.facebook.imagepipeline.core.ImagePipelineConfig.Builder setWebpErrorLogger(WebpErrorLogger webpErrorLogger) {
            this.mWebpErrorLogger = webpErrorLogger;
            return this.mConfigBuilder;
        }

        public com.facebook.imagepipeline.core.ImagePipelineConfig.Builder setWebpBitmapFactory(WebpBitmapFactory webpBitmapFactory) {
            this.mWebpBitmapFactory = webpBitmapFactory;
            return this.mConfigBuilder;
        }

        public com.facebook.imagepipeline.core.ImagePipelineConfig.Builder setSuppressBitmapPrefetching(boolean z) {
            this.mSuppressBitmapPrefetching = z;
            return this.mConfigBuilder;
        }

        public com.facebook.imagepipeline.core.ImagePipelineConfig.Builder setBitmapPrepareToDraw(boolean z, int i, int i2, boolean z2) {
            this.mUseBitmapPrepareToDraw = z;
            this.mBitmapPrepareToDrawMinSizeBytes = i;
            this.mBitmapPrepareToDrawMaxSizeBytes = i2;
            this.mBitmapPrepareToDrawForPrefetch = z2;
            return this.mConfigBuilder;
        }

        public com.facebook.imagepipeline.core.ImagePipelineConfig.Builder setSmartResizingEnabled(Supplier<Boolean> supplier) {
            this.mSmartResizingEnabled = supplier;
            return this.mConfigBuilder;
        }

        public com.facebook.imagepipeline.core.ImagePipelineConfig.Builder setProducerFactoryMethod(ProducerFactoryMethod producerFactoryMethod) {
            this.mProducerFactoryMethod = producerFactoryMethod;
            return this.mConfigBuilder;
        }

        public ImagePipelineExperiments build() {
            return new ImagePipelineExperiments();
        }
    }

    public interface ProducerFactoryMethod {
        ProducerFactory createProducerFactory(Context context, ByteArrayPool byteArrayPool, ImageDecoder imageDecoder, ProgressiveJpegConfig progressiveJpegConfig, boolean z, boolean z2, boolean z3, Supplier<Boolean> supplier, ExecutorSupplier executorSupplier, PooledByteBufferFactory pooledByteBufferFactory, MemoryCache<CacheKey, CloseableImage> memoryCache, MemoryCache<CacheKey, PooledByteBuffer> memoryCache2, BufferedDiskCache bufferedDiskCache, BufferedDiskCache bufferedDiskCache2, MediaVariationsIndex mediaVariationsIndex, CacheKeyFactory cacheKeyFactory, PlatformBitmapFactory platformBitmapFactory, int i, int i2, boolean z4);
    }

    public static class DefaultProducerFactoryMethod implements ProducerFactoryMethod {
        public ProducerFactory createProducerFactory(Context context, ByteArrayPool byteArrayPool, ImageDecoder imageDecoder, ProgressiveJpegConfig progressiveJpegConfig, boolean z, boolean z2, boolean z3, Supplier<Boolean> supplier, ExecutorSupplier executorSupplier, PooledByteBufferFactory pooledByteBufferFactory, MemoryCache<CacheKey, CloseableImage> memoryCache, MemoryCache<CacheKey, PooledByteBuffer> memoryCache2, BufferedDiskCache bufferedDiskCache, BufferedDiskCache bufferedDiskCache2, MediaVariationsIndex mediaVariationsIndex, CacheKeyFactory cacheKeyFactory, PlatformBitmapFactory platformBitmapFactory, int i, int i2, boolean z4) {
            return new ProducerFactory(context, byteArrayPool, imageDecoder, progressiveJpegConfig, z, z2, z3, supplier, executorSupplier, pooledByteBufferFactory, memoryCache, memoryCache2, bufferedDiskCache, bufferedDiskCache2, mediaVariationsIndex, cacheKeyFactory, platformBitmapFactory, i, i2, z4);
        }
    }

    private ImagePipelineExperiments(Builder builder) {
        this.mWebpSupportEnabled = builder.mWebpSupportEnabled;
        this.mExternalCreatedBitmapLogEnabled = builder.mExternalCreatedBitmapLogEnabled;
        if (builder.mMediaVariationsIndexEnabled != null) {
            this.mMediaVariationsIndexEnabled = builder.mMediaVariationsIndexEnabled;
        } else {
            this.mMediaVariationsIndexEnabled = new Supplier<Boolean>() {
                public Boolean get() {
                    return Boolean.FALSE;
                }
            };
        }
        this.mWebpErrorLogger = builder.mWebpErrorLogger;
        this.mDecodeCancellationEnabled = builder.mDecodeCancellationEnabled;
        this.mWebpBitmapFactory = builder.mWebpBitmapFactory;
        this.mSuppressBitmapPrefetching = builder.mSuppressBitmapPrefetching;
        this.mUseDownsamplingRatioForResizing = builder.mUseDownsamplingRatioForResizing;
        this.mUseBitmapPrepareToDraw = builder.mUseBitmapPrepareToDraw;
        this.mBitmapPrepareToDrawMinSizeBytes = builder.mBitmapPrepareToDrawMinSizeBytes;
        this.mBitmapPrepareToDrawMaxSizeBytes = builder.mBitmapPrepareToDrawMaxSizeBytes;
        this.mBitmapPrepareToDrawForPrefetch = builder.mBitmapPrepareToDrawForPrefetch;
        this.mPartialImageCachingEnabled = builder.mPartialImageCachingEnabled;
        this.mSmartResizingEnabled = builder.mSmartResizingEnabled;
        if (builder.mProducerFactoryMethod == null) {
            this.mProducerFactoryMethod = new DefaultProducerFactoryMethod();
        } else {
            this.mProducerFactoryMethod = builder.mProducerFactoryMethod;
        }
    }

    public boolean isExternalCreatedBitmapLogEnabled() {
        return this.mExternalCreatedBitmapLogEnabled;
    }

    public boolean getMediaVariationsIndexEnabled() {
        return ((Boolean) this.mMediaVariationsIndexEnabled.get()).booleanValue();
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

    public int getBitmapPrepareToDrawMinSizeBytes() {
        return this.mBitmapPrepareToDrawMinSizeBytes;
    }

    public int getBitmapPrepareToDrawMaxSizeBytes() {
        return this.mBitmapPrepareToDrawMaxSizeBytes;
    }

    public boolean isPartialImageCachingEnabled() {
        return this.mPartialImageCachingEnabled;
    }

    public Supplier<Boolean> isSmartResizingEnabled() {
        return this.mSmartResizingEnabled;
    }

    public ProducerFactoryMethod getProducerFactoryMethod() {
        return this.mProducerFactoryMethod;
    }

    public static Builder newBuilder(com.facebook.imagepipeline.core.ImagePipelineConfig.Builder builder) {
        return new Builder(builder);
    }

    public boolean getBitmapPrepareToDrawForPrefetch() {
        return this.mBitmapPrepareToDrawForPrefetch;
    }
}
