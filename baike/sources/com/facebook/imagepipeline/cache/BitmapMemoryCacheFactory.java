package com.facebook.imagepipeline.cache;

import com.facebook.cache.common.CacheKey;
import com.facebook.imagepipeline.image.CloseableImage;

public class BitmapMemoryCacheFactory {

    /* renamed from: com.facebook.imagepipeline.cache.BitmapMemoryCacheFactory$1 */
    final class AnonymousClass1 implements MemoryCacheTracker<CacheKey> {
        final /* synthetic */ ImageCacheStatsTracker val$imageCacheStatsTracker;

        AnonymousClass1(ImageCacheStatsTracker imageCacheStatsTracker) {
            this.val$imageCacheStatsTracker = imageCacheStatsTracker;
        }

        public void onCacheHit(CacheKey cacheKey) {
            this.val$imageCacheStatsTracker.onBitmapCacheHit(cacheKey);
        }

        public void onCacheMiss() {
            this.val$imageCacheStatsTracker.onBitmapCacheMiss();
        }

        public void onCachePut() {
            this.val$imageCacheStatsTracker.onBitmapCachePut();
        }
    }

    public static MemoryCache<CacheKey, CloseableImage> get(CountingMemoryCache<CacheKey, CloseableImage> countingMemoryCache, ImageCacheStatsTracker imageCacheStatsTracker) {
        imageCacheStatsTracker.registerBitmapMemoryCache(countingMemoryCache);
        return new InstrumentedMemoryCache(countingMemoryCache, new AnonymousClass1(imageCacheStatsTracker));
    }
}
