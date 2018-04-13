package com.facebook.imagepipeline.cache;

import com.facebook.common.references.ResourceReleaser;
import com.facebook.imagepipeline.cache.CountingMemoryCache.Entry;

class CountingMemoryCache$3 implements ResourceReleaser<V> {
    final /* synthetic */ CountingMemoryCache this$0;
    final /* synthetic */ Entry val$entry;

    CountingMemoryCache$3(CountingMemoryCache countingMemoryCache, Entry entry) {
        this.this$0 = countingMemoryCache;
        this.val$entry = entry;
    }

    public void release(V v) {
        CountingMemoryCache.access$000(this.this$0, this.val$entry);
    }
}
