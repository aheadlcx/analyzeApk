package com.facebook.imagepipeline.core;

import android.net.Uri;
import com.android.internal.util.Predicate;
import com.facebook.cache.common.CacheKey;

class ImagePipeline$6 implements Predicate<CacheKey> {
    final /* synthetic */ ImagePipeline this$0;
    final /* synthetic */ Uri val$uri;

    ImagePipeline$6(ImagePipeline imagePipeline, Uri uri) {
        this.this$0 = imagePipeline;
        this.val$uri = uri;
    }

    public boolean apply(CacheKey cacheKey) {
        return cacheKey.containsUri(this.val$uri);
    }
}
