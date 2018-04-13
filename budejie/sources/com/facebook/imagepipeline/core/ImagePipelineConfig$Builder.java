package com.facebook.imagepipeline.core;

import android.content.Context;
import android.graphics.Bitmap.Config;
import com.facebook.cache.disk.DiskCacheConfig;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.internal.Supplier;
import com.facebook.common.memory.MemoryTrimmableRegistry;
import com.facebook.imagepipeline.bitmaps.PlatformBitmapFactory;
import com.facebook.imagepipeline.cache.CacheKeyFactory;
import com.facebook.imagepipeline.cache.CountingMemoryCache$CacheTrimStrategy;
import com.facebook.imagepipeline.cache.ImageCacheStatsTracker;
import com.facebook.imagepipeline.cache.MemoryCacheParams;
import com.facebook.imagepipeline.decoder.ImageDecoder;
import com.facebook.imagepipeline.decoder.ImageDecoderConfig;
import com.facebook.imagepipeline.decoder.ProgressiveJpegConfig;
import com.facebook.imagepipeline.listener.RequestListener;
import com.facebook.imagepipeline.memory.PoolFactory;
import com.facebook.imagepipeline.producers.NetworkFetcher;
import java.util.Set;

public class ImagePipelineConfig$Builder {
    private Config mBitmapConfig;
    private Supplier<MemoryCacheParams> mBitmapMemoryCacheParamsSupplier;
    private CountingMemoryCache$CacheTrimStrategy mBitmapMemoryCacheTrimStrategy;
    private CacheKeyFactory mCacheKeyFactory;
    private final Context mContext;
    private boolean mDownsampleEnabled;
    private Supplier<MemoryCacheParams> mEncodedMemoryCacheParamsSupplier;
    private ExecutorSupplier mExecutorSupplier;
    private final ImagePipelineExperiments$Builder mExperimentsBuilder;
    private FileCacheFactory mFileCacheFactory;
    private ImageCacheStatsTracker mImageCacheStatsTracker;
    private ImageDecoder mImageDecoder;
    private ImageDecoderConfig mImageDecoderConfig;
    private Supplier<Boolean> mIsPrefetchEnabledSupplier;
    private DiskCacheConfig mMainDiskCacheConfig;
    private MemoryTrimmableRegistry mMemoryTrimmableRegistry;
    private NetworkFetcher mNetworkFetcher;
    private PlatformBitmapFactory mPlatformBitmapFactory;
    private PoolFactory mPoolFactory;
    private ProgressiveJpegConfig mProgressiveJpegConfig;
    private Set<RequestListener> mRequestListeners;
    private boolean mResizeAndRotateEnabledForNetwork;
    private DiskCacheConfig mSmallImageDiskCacheConfig;

    private ImagePipelineConfig$Builder(Context context) {
        this.mDownsampleEnabled = false;
        this.mResizeAndRotateEnabledForNetwork = true;
        this.mExperimentsBuilder = new ImagePipelineExperiments$Builder(this);
        this.mContext = (Context) Preconditions.checkNotNull(context);
    }

    public ImagePipelineConfig$Builder setBitmapsConfig(Config config) {
        this.mBitmapConfig = config;
        return this;
    }

    public ImagePipelineConfig$Builder setBitmapMemoryCacheParamsSupplier(Supplier<MemoryCacheParams> supplier) {
        this.mBitmapMemoryCacheParamsSupplier = (Supplier) Preconditions.checkNotNull(supplier);
        return this;
    }

    public ImagePipelineConfig$Builder setBitmapMemoryCacheTrimStrategy(CountingMemoryCache$CacheTrimStrategy countingMemoryCache$CacheTrimStrategy) {
        this.mBitmapMemoryCacheTrimStrategy = countingMemoryCache$CacheTrimStrategy;
        return this;
    }

    public ImagePipelineConfig$Builder setCacheKeyFactory(CacheKeyFactory cacheKeyFactory) {
        this.mCacheKeyFactory = cacheKeyFactory;
        return this;
    }

    public ImagePipelineConfig$Builder setFileCacheFactory(FileCacheFactory fileCacheFactory) {
        this.mFileCacheFactory = fileCacheFactory;
        return this;
    }

    public boolean isDownsampleEnabled() {
        return this.mDownsampleEnabled;
    }

    public ImagePipelineConfig$Builder setDownsampleEnabled(boolean z) {
        this.mDownsampleEnabled = z;
        return this;
    }

    public ImagePipelineConfig$Builder setEncodedMemoryCacheParamsSupplier(Supplier<MemoryCacheParams> supplier) {
        this.mEncodedMemoryCacheParamsSupplier = (Supplier) Preconditions.checkNotNull(supplier);
        return this;
    }

    public ImagePipelineConfig$Builder setExecutorSupplier(ExecutorSupplier executorSupplier) {
        this.mExecutorSupplier = executorSupplier;
        return this;
    }

    public ImagePipelineConfig$Builder setImageCacheStatsTracker(ImageCacheStatsTracker imageCacheStatsTracker) {
        this.mImageCacheStatsTracker = imageCacheStatsTracker;
        return this;
    }

    public ImagePipelineConfig$Builder setImageDecoder(ImageDecoder imageDecoder) {
        this.mImageDecoder = imageDecoder;
        return this;
    }

    public ImagePipelineConfig$Builder setIsPrefetchEnabledSupplier(Supplier<Boolean> supplier) {
        this.mIsPrefetchEnabledSupplier = supplier;
        return this;
    }

    public ImagePipelineConfig$Builder setMainDiskCacheConfig(DiskCacheConfig diskCacheConfig) {
        this.mMainDiskCacheConfig = diskCacheConfig;
        return this;
    }

    public ImagePipelineConfig$Builder setMemoryTrimmableRegistry(MemoryTrimmableRegistry memoryTrimmableRegistry) {
        this.mMemoryTrimmableRegistry = memoryTrimmableRegistry;
        return this;
    }

    public ImagePipelineConfig$Builder setNetworkFetcher(NetworkFetcher networkFetcher) {
        this.mNetworkFetcher = networkFetcher;
        return this;
    }

    public ImagePipelineConfig$Builder setPlatformBitmapFactory(PlatformBitmapFactory platformBitmapFactory) {
        this.mPlatformBitmapFactory = platformBitmapFactory;
        return this;
    }

    public ImagePipelineConfig$Builder setPoolFactory(PoolFactory poolFactory) {
        this.mPoolFactory = poolFactory;
        return this;
    }

    public ImagePipelineConfig$Builder setProgressiveJpegConfig(ProgressiveJpegConfig progressiveJpegConfig) {
        this.mProgressiveJpegConfig = progressiveJpegConfig;
        return this;
    }

    public ImagePipelineConfig$Builder setRequestListeners(Set<RequestListener> set) {
        this.mRequestListeners = set;
        return this;
    }

    public ImagePipelineConfig$Builder setResizeAndRotateEnabledForNetwork(boolean z) {
        this.mResizeAndRotateEnabledForNetwork = z;
        return this;
    }

    public ImagePipelineConfig$Builder setSmallImageDiskCacheConfig(DiskCacheConfig diskCacheConfig) {
        this.mSmallImageDiskCacheConfig = diskCacheConfig;
        return this;
    }

    public ImagePipelineConfig$Builder setImageDecoderConfig(ImageDecoderConfig imageDecoderConfig) {
        this.mImageDecoderConfig = imageDecoderConfig;
        return this;
    }

    public ImagePipelineExperiments$Builder experiment() {
        return this.mExperimentsBuilder;
    }

    public ImagePipelineConfig build() {
        return new ImagePipelineConfig(this, null);
    }
}
