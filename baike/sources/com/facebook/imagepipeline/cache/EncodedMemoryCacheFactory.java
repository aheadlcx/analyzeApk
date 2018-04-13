package com.facebook.imagepipeline.cache;

import com.facebook.cache.common.CacheKey;
import com.facebook.common.memory.PooledByteBuffer;

public class EncodedMemoryCacheFactory {

    /* renamed from: com.facebook.imagepipeline.cache.EncodedMemoryCacheFactory$1 */
    final class AnonymousClass1 implements MemoryCacheTracker<CacheKey> {
        final /* synthetic */ ImageCacheStatsTracker val$imageCacheStatsTracker;

        AnonymousClass1(ImageCacheStatsTracker imageCacheStatsTracker) {
            this.val$imageCacheStatsTracker = imageCacheStatsTracker;
        }

        public void onCacheHit(CacheKey cacheKey) {
            this.val$imageCacheStatsTracker.onMemoryCacheHit(cacheKey);
        }

        public void onCacheMiss() {
            this.val$imageCacheStatsTracker.onMemoryCacheMiss();
        }

        public void onCachePut() {
            this.val$imageCacheStatsTracker.onMemoryCachePut();
        }
    }

    public static MemoryCache<CacheKey, PooledByteBuffer> get(CountingMemoryCache<CacheKey, PooledByteBuffer> countingMemoryCache, ImageCacheStatsTracker imageCacheStatsTracker) {
        imageCacheStatsTracker.registerEncodedMemoryCache(countingMemoryCache);
        return new InstrumentedMemoryCache(countingMemoryCache, new AnonymousClass1(imageCacheStatsTracker));
    }
}
