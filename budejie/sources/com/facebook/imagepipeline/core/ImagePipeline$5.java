package com.facebook.imagepipeline.core;

import bolts.f;
import bolts.g;
import com.facebook.cache.common.CacheKey;

class ImagePipeline$5 implements f<Boolean, g<Boolean>> {
    final /* synthetic */ ImagePipeline this$0;
    final /* synthetic */ CacheKey val$cacheKey;

    ImagePipeline$5(ImagePipeline imagePipeline, CacheKey cacheKey) {
        this.this$0 = imagePipeline;
        this.val$cacheKey = cacheKey;
    }

    public g<Boolean> then(g<Boolean> gVar) throws Exception {
        if (gVar.c() || gVar.d() || !((Boolean) gVar.e()).booleanValue()) {
            return ImagePipeline.access$000(this.this$0).contains(this.val$cacheKey);
        }
        return g.a(Boolean.valueOf(true));
    }
}
