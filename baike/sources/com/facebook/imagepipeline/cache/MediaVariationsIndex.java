package com.facebook.imagepipeline.cache;

import bolts.Task;
import com.facebook.cache.common.CacheKey;
import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.request.ImageRequest.CacheChoice;
import com.facebook.imagepipeline.request.MediaVariations;
import com.facebook.imagepipeline.request.MediaVariations.Builder;

public interface MediaVariationsIndex {
    Task<MediaVariations> getCachedVariants(String str, Builder builder);

    void saveCachedVariant(String str, CacheChoice cacheChoice, CacheKey cacheKey, EncodedImage encodedImage);
}
