package com.facebook.imagepipeline.cache;

import bolts.g;
import com.facebook.cache.common.CacheKey;
import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.request.ImageRequest$CacheChoice;
import com.facebook.imagepipeline.request.MediaVariations;
import com.facebook.imagepipeline.request.MediaVariations.Builder;

public class NoOpMediaVariationsIndex implements MediaVariationsIndex {
    public g<MediaVariations> getCachedVariants(String str, Builder builder) {
        return g.a(null);
    }

    public void saveCachedVariant(String str, ImageRequest$CacheChoice imageRequest$CacheChoice, CacheKey cacheKey, EncodedImage encodedImage) {
    }
}
