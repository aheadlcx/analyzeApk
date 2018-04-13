package com.facebook.imagepipeline.postprocessors;

import android.graphics.Bitmap;
import com.facebook.cache.common.CacheKey;
import com.facebook.cache.common.SimpleCacheKey;
import com.facebook.imagepipeline.nativecode.NativeRoundingFilter;
import com.facebook.imagepipeline.request.BasePostprocessor;
import javax.annotation.Nullable;

public class RoundAsCirclePostprocessor extends BasePostprocessor {
    @Nullable
    private CacheKey mCacheKey;

    public void process(Bitmap bitmap) {
        NativeRoundingFilter.toCircle(bitmap);
    }

    @Nullable
    public CacheKey getPostprocessorCacheKey() {
        if (this.mCacheKey == null) {
            this.mCacheKey = new SimpleCacheKey("RoundAsCirclePostprocessor");
        }
        return this.mCacheKey;
    }
}
