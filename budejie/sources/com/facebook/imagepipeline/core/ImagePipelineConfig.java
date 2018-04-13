package com.facebook.imagepipeline.core;

import android.app.ActivityManager;
import android.content.Context;
import android.graphics.Bitmap.Config;
import com.facebook.cache.disk.DiskCacheConfig;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.internal.Supplier;
import com.facebook.common.internal.VisibleForTesting;
import com.facebook.common.memory.MemoryTrimmableRegistry;
import com.facebook.common.memory.NoOpMemoryTrimmableRegistry;
import com.facebook.common.webp.BitmapCreator;
import com.facebook.common.webp.WebpBitmapFactory;
import com.facebook.common.webp.WebpBitmapFactory.WebpErrorLogger;
import com.facebook.common.webp.WebpSupportStatus;
import com.facebook.imagepipeline.bitmaps.HoneycombBitmapCreator;
import com.facebook.imagepipeline.bitmaps.PlatformBitmapFactory;
import com.facebook.imagepipeline.cache.BitmapMemoryCacheTrimStrategy;
import com.facebook.imagepipeline.cache.CacheKeyFactory;
import com.facebook.imagepipeline.cache.CountingMemoryCache$CacheTrimStrategy;
import com.facebook.imagepipeline.cache.DefaultBitmapMemoryCacheParamsSupplier;
import com.facebook.imagepipeline.cache.DefaultCacheKeyFactory;
import com.facebook.imagepipeline.cache.DefaultEncodedMemoryCacheParamsSupplier;
import com.facebook.imagepipeline.cache.ImageCacheStatsTracker;
import com.facebook.imagepipeline.cache.MemoryCacheParams;
import com.facebook.imagepipeline.cache.NoOpImageCacheStatsTracker;
import com.facebook.imagepipeline.decoder.ImageDecoder;
import com.facebook.imagepipeline.decoder.ImageDecoderConfig;
import com.facebook.imagepipeline.decoder.ProgressiveJpegConfig;
import com.facebook.imagepipeline.decoder.SimpleProgressiveJpegConfig;
import com.facebook.imagepipeline.listener.RequestListener;
import com.facebook.imagepipeline.memory.PoolConfig;
import com.facebook.imagepipeline.memory.PoolFactory;
import com.facebook.imagepipeline.producers.HttpUrlConnectionNetworkFetcher;
import com.facebook.imagepipeline.producers.NetworkFetcher;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import javax.annotation.Nullable;

public class ImagePipelineConfig {
    private static ImagePipelineConfig$DefaultImageRequestConfig sDefaultImageRequestConfig = new ImagePipelineConfig$DefaultImageRequestConfig(null);
    private final Config mBitmapConfig;
    private final Supplier<MemoryCacheParams> mBitmapMemoryCacheParamsSupplier;
    private final CountingMemoryCache$CacheTrimStrategy mBitmapMemoryCacheTrimStrategy;
    private final CacheKeyFactory mCacheKeyFactory;
    private final Context mContext;
    private final boolean mDownsampleEnabled;
    private final Supplier<MemoryCacheParams> mEncodedMemoryCacheParamsSupplier;
    private final ExecutorSupplier mExecutorSupplier;
    private final FileCacheFactory mFileCacheFactory;
    private final ImageCacheStatsTracker mImageCacheStatsTracker;
    @Nullable
    private final ImageDecoder mImageDecoder;
    @Nullable
    private final ImageDecoderConfig mImageDecoderConfig;
    private final ImagePipelineExperiments mImagePipelineExperiments;
    private final Supplier<Boolean> mIsPrefetchEnabledSupplier;
    private final DiskCacheConfig mMainDiskCacheConfig;
    private final MemoryTrimmableRegistry mMemoryTrimmableRegistry;
    private final NetworkFetcher mNetworkFetcher;
    @Nullable
    private final PlatformBitmapFactory mPlatformBitmapFactory;
    private final PoolFactory mPoolFactory;
    private final ProgressiveJpegConfig mProgressiveJpegConfig;
    private final Set<RequestListener> mRequestListeners;
    private final boolean mResizeAndRotateEnabledForNetwork;
    private final DiskCacheConfig mSmallImageDiskCacheConfig;

    private ImagePipelineConfig(ImagePipelineConfig$Builder imagePipelineConfig$Builder) {
        Supplier defaultBitmapMemoryCacheParamsSupplier;
        CountingMemoryCache$CacheTrimStrategy bitmapMemoryCacheTrimStrategy;
        Config config;
        CacheKeyFactory instance;
        FileCacheFactory diskStorageCacheFactory;
        ImageCacheStatsTracker instance2;
        DiskCacheConfig defaultMainDiskCacheConfig;
        MemoryTrimmableRegistry instance3;
        NetworkFetcher httpUrlConnectionNetworkFetcher;
        PoolFactory poolFactory;
        ProgressiveJpegConfig simpleProgressiveJpegConfig;
        Set hashSet;
        ExecutorSupplier defaultExecutorSupplier;
        this.mImagePipelineExperiments = ImagePipelineConfig$Builder.access$100(imagePipelineConfig$Builder).build();
        if (ImagePipelineConfig$Builder.access$200(imagePipelineConfig$Builder) == null) {
            defaultBitmapMemoryCacheParamsSupplier = new DefaultBitmapMemoryCacheParamsSupplier((ActivityManager) ImagePipelineConfig$Builder.access$300(imagePipelineConfig$Builder).getSystemService("activity"));
        } else {
            defaultBitmapMemoryCacheParamsSupplier = ImagePipelineConfig$Builder.access$200(imagePipelineConfig$Builder);
        }
        this.mBitmapMemoryCacheParamsSupplier = defaultBitmapMemoryCacheParamsSupplier;
        if (ImagePipelineConfig$Builder.access$400(imagePipelineConfig$Builder) == null) {
            bitmapMemoryCacheTrimStrategy = new BitmapMemoryCacheTrimStrategy();
        } else {
            bitmapMemoryCacheTrimStrategy = ImagePipelineConfig$Builder.access$400(imagePipelineConfig$Builder);
        }
        this.mBitmapMemoryCacheTrimStrategy = bitmapMemoryCacheTrimStrategy;
        if (ImagePipelineConfig$Builder.access$500(imagePipelineConfig$Builder) == null) {
            config = Config.ARGB_8888;
        } else {
            config = ImagePipelineConfig$Builder.access$500(imagePipelineConfig$Builder);
        }
        this.mBitmapConfig = config;
        if (ImagePipelineConfig$Builder.access$600(imagePipelineConfig$Builder) == null) {
            instance = DefaultCacheKeyFactory.getInstance();
        } else {
            instance = ImagePipelineConfig$Builder.access$600(imagePipelineConfig$Builder);
        }
        this.mCacheKeyFactory = instance;
        this.mContext = (Context) Preconditions.checkNotNull(ImagePipelineConfig$Builder.access$300(imagePipelineConfig$Builder));
        if (ImagePipelineConfig$Builder.access$700(imagePipelineConfig$Builder) == null) {
            diskStorageCacheFactory = new DiskStorageCacheFactory(new DynamicDefaultDiskStorageFactory());
        } else {
            diskStorageCacheFactory = ImagePipelineConfig$Builder.access$700(imagePipelineConfig$Builder);
        }
        this.mFileCacheFactory = diskStorageCacheFactory;
        this.mDownsampleEnabled = ImagePipelineConfig$Builder.access$800(imagePipelineConfig$Builder);
        if (ImagePipelineConfig$Builder.access$900(imagePipelineConfig$Builder) == null) {
            defaultBitmapMemoryCacheParamsSupplier = new DefaultEncodedMemoryCacheParamsSupplier();
        } else {
            defaultBitmapMemoryCacheParamsSupplier = ImagePipelineConfig$Builder.access$900(imagePipelineConfig$Builder);
        }
        this.mEncodedMemoryCacheParamsSupplier = defaultBitmapMemoryCacheParamsSupplier;
        if (ImagePipelineConfig$Builder.access$1000(imagePipelineConfig$Builder) == null) {
            instance2 = NoOpImageCacheStatsTracker.getInstance();
        } else {
            instance2 = ImagePipelineConfig$Builder.access$1000(imagePipelineConfig$Builder);
        }
        this.mImageCacheStatsTracker = instance2;
        this.mImageDecoder = ImagePipelineConfig$Builder.access$1100(imagePipelineConfig$Builder);
        if (ImagePipelineConfig$Builder.access$1200(imagePipelineConfig$Builder) == null) {
            defaultBitmapMemoryCacheParamsSupplier = new ImagePipelineConfig$1(this);
        } else {
            defaultBitmapMemoryCacheParamsSupplier = ImagePipelineConfig$Builder.access$1200(imagePipelineConfig$Builder);
        }
        this.mIsPrefetchEnabledSupplier = defaultBitmapMemoryCacheParamsSupplier;
        if (ImagePipelineConfig$Builder.access$1300(imagePipelineConfig$Builder) == null) {
            defaultMainDiskCacheConfig = getDefaultMainDiskCacheConfig(ImagePipelineConfig$Builder.access$300(imagePipelineConfig$Builder));
        } else {
            defaultMainDiskCacheConfig = ImagePipelineConfig$Builder.access$1300(imagePipelineConfig$Builder);
        }
        this.mMainDiskCacheConfig = defaultMainDiskCacheConfig;
        if (ImagePipelineConfig$Builder.access$1400(imagePipelineConfig$Builder) == null) {
            instance3 = NoOpMemoryTrimmableRegistry.getInstance();
        } else {
            instance3 = ImagePipelineConfig$Builder.access$1400(imagePipelineConfig$Builder);
        }
        this.mMemoryTrimmableRegistry = instance3;
        if (ImagePipelineConfig$Builder.access$1500(imagePipelineConfig$Builder) == null) {
            httpUrlConnectionNetworkFetcher = new HttpUrlConnectionNetworkFetcher();
        } else {
            httpUrlConnectionNetworkFetcher = ImagePipelineConfig$Builder.access$1500(imagePipelineConfig$Builder);
        }
        this.mNetworkFetcher = httpUrlConnectionNetworkFetcher;
        this.mPlatformBitmapFactory = ImagePipelineConfig$Builder.access$1600(imagePipelineConfig$Builder);
        if (ImagePipelineConfig$Builder.access$1700(imagePipelineConfig$Builder) == null) {
            poolFactory = new PoolFactory(PoolConfig.newBuilder().build());
        } else {
            poolFactory = ImagePipelineConfig$Builder.access$1700(imagePipelineConfig$Builder);
        }
        this.mPoolFactory = poolFactory;
        if (ImagePipelineConfig$Builder.access$1800(imagePipelineConfig$Builder) == null) {
            simpleProgressiveJpegConfig = new SimpleProgressiveJpegConfig();
        } else {
            simpleProgressiveJpegConfig = ImagePipelineConfig$Builder.access$1800(imagePipelineConfig$Builder);
        }
        this.mProgressiveJpegConfig = simpleProgressiveJpegConfig;
        if (ImagePipelineConfig$Builder.access$1900(imagePipelineConfig$Builder) == null) {
            hashSet = new HashSet();
        } else {
            hashSet = ImagePipelineConfig$Builder.access$1900(imagePipelineConfig$Builder);
        }
        this.mRequestListeners = hashSet;
        this.mResizeAndRotateEnabledForNetwork = ImagePipelineConfig$Builder.access$2000(imagePipelineConfig$Builder);
        if (ImagePipelineConfig$Builder.access$2100(imagePipelineConfig$Builder) == null) {
            defaultMainDiskCacheConfig = this.mMainDiskCacheConfig;
        } else {
            defaultMainDiskCacheConfig = ImagePipelineConfig$Builder.access$2100(imagePipelineConfig$Builder);
        }
        this.mSmallImageDiskCacheConfig = defaultMainDiskCacheConfig;
        this.mImageDecoderConfig = ImagePipelineConfig$Builder.access$2200(imagePipelineConfig$Builder);
        int flexByteArrayPoolMaxNumThreads = this.mPoolFactory.getFlexByteArrayPoolMaxNumThreads();
        if (ImagePipelineConfig$Builder.access$2300(imagePipelineConfig$Builder) == null) {
            defaultExecutorSupplier = new DefaultExecutorSupplier(flexByteArrayPoolMaxNumThreads);
        } else {
            defaultExecutorSupplier = ImagePipelineConfig$Builder.access$2300(imagePipelineConfig$Builder);
        }
        this.mExecutorSupplier = defaultExecutorSupplier;
        WebpBitmapFactory webpBitmapFactory = this.mImagePipelineExperiments.getWebpBitmapFactory();
        if (webpBitmapFactory != null) {
            setWebpBitmapFactory(webpBitmapFactory, this.mImagePipelineExperiments, new HoneycombBitmapCreator(getPoolFactory()));
        } else if (this.mImagePipelineExperiments.isWebpSupportEnabled() && WebpSupportStatus.sIsWebpSupportRequired) {
            webpBitmapFactory = WebpSupportStatus.loadWebpBitmapFactoryIfExists();
            if (webpBitmapFactory != null) {
                setWebpBitmapFactory(webpBitmapFactory, this.mImagePipelineExperiments, new HoneycombBitmapCreator(getPoolFactory()));
            }
        }
    }

    private static void setWebpBitmapFactory(WebpBitmapFactory webpBitmapFactory, ImagePipelineExperiments imagePipelineExperiments, BitmapCreator bitmapCreator) {
        WebpSupportStatus.sWebpBitmapFactory = webpBitmapFactory;
        WebpErrorLogger webpErrorLogger = imagePipelineExperiments.getWebpErrorLogger();
        if (webpErrorLogger != null) {
            webpBitmapFactory.setWebpErrorLogger(webpErrorLogger);
        }
        if (bitmapCreator != null) {
            webpBitmapFactory.setBitmapCreator(bitmapCreator);
        }
    }

    private static DiskCacheConfig getDefaultMainDiskCacheConfig(Context context) {
        return DiskCacheConfig.newBuilder(context).build();
    }

    @VisibleForTesting
    static void resetDefaultRequestConfig() {
        sDefaultImageRequestConfig = new ImagePipelineConfig$DefaultImageRequestConfig(null);
    }

    public Config getBitmapConfig() {
        return this.mBitmapConfig;
    }

    public Supplier<MemoryCacheParams> getBitmapMemoryCacheParamsSupplier() {
        return this.mBitmapMemoryCacheParamsSupplier;
    }

    public CountingMemoryCache$CacheTrimStrategy getBitmapMemoryCacheTrimStrategy() {
        return this.mBitmapMemoryCacheTrimStrategy;
    }

    public CacheKeyFactory getCacheKeyFactory() {
        return this.mCacheKeyFactory;
    }

    public Context getContext() {
        return this.mContext;
    }

    public static ImagePipelineConfig$DefaultImageRequestConfig getDefaultImageRequestConfig() {
        return sDefaultImageRequestConfig;
    }

    public FileCacheFactory getFileCacheFactory() {
        return this.mFileCacheFactory;
    }

    public boolean isDownsampleEnabled() {
        return this.mDownsampleEnabled;
    }

    public Supplier<MemoryCacheParams> getEncodedMemoryCacheParamsSupplier() {
        return this.mEncodedMemoryCacheParamsSupplier;
    }

    public ExecutorSupplier getExecutorSupplier() {
        return this.mExecutorSupplier;
    }

    public ImageCacheStatsTracker getImageCacheStatsTracker() {
        return this.mImageCacheStatsTracker;
    }

    @Nullable
    public ImageDecoder getImageDecoder() {
        return this.mImageDecoder;
    }

    public Supplier<Boolean> getIsPrefetchEnabledSupplier() {
        return this.mIsPrefetchEnabledSupplier;
    }

    public DiskCacheConfig getMainDiskCacheConfig() {
        return this.mMainDiskCacheConfig;
    }

    public MemoryTrimmableRegistry getMemoryTrimmableRegistry() {
        return this.mMemoryTrimmableRegistry;
    }

    public NetworkFetcher getNetworkFetcher() {
        return this.mNetworkFetcher;
    }

    @Nullable
    public PlatformBitmapFactory getPlatformBitmapFactory() {
        return this.mPlatformBitmapFactory;
    }

    public PoolFactory getPoolFactory() {
        return this.mPoolFactory;
    }

    public ProgressiveJpegConfig getProgressiveJpegConfig() {
        return this.mProgressiveJpegConfig;
    }

    public Set<RequestListener> getRequestListeners() {
        return Collections.unmodifiableSet(this.mRequestListeners);
    }

    public boolean isResizeAndRotateEnabledForNetwork() {
        return this.mResizeAndRotateEnabledForNetwork;
    }

    public DiskCacheConfig getSmallImageDiskCacheConfig() {
        return this.mSmallImageDiskCacheConfig;
    }

    @Nullable
    public ImageDecoderConfig getImageDecoderConfig() {
        return this.mImageDecoderConfig;
    }

    public ImagePipelineExperiments getExperiments() {
        return this.mImagePipelineExperiments;
    }

    public static ImagePipelineConfig$Builder newBuilder(Context context) {
        return new ImagePipelineConfig$Builder(context, null);
    }
}
