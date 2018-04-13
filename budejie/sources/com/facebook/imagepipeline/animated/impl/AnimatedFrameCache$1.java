package com.facebook.imagepipeline.animated.impl;

import com.facebook.cache.common.CacheKey;
import com.facebook.imagepipeline.cache.CountingMemoryCache$EntryStateObserver;

class AnimatedFrameCache$1 implements CountingMemoryCache$EntryStateObserver<CacheKey> {
    final /* synthetic */ AnimatedFrameCache this$0;

    AnimatedFrameCache$1(AnimatedFrameCache animatedFrameCache) {
        this.this$0 = animatedFrameCache;
    }

    public void onExclusivityChanged(CacheKey cacheKey, boolean z) {
        this.this$0.onReusabilityChange(cacheKey, z);
    }
}
