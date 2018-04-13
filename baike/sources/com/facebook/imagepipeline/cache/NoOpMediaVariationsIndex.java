package com.facebook.imagepipeline.cache;

import bolts.Task;
import com.facebook.cache.common.CacheKey;
import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.request.ImageRequest.CacheChoice;
import com.facebook.imagepipeline.request.MediaVariations;
import com.facebook.imagepipeline.request.MediaVariations.Builder;

public class NoOpMediaVariationsIndex implements MediaVariationsIndex {
    public Task<MediaVariations> getCachedVariants(String str, Builder builder) {
        return Task.forResult(null);
    }

    public void saveCachedVariant(String str, CacheChoice cacheChoice, CacheKey cacheKey, EncodedImage encodedImage) {
    }
}
