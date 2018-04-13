package com.facebook.imagepipeline.cache;

import com.facebook.imagepipeline.cache.CountingMemoryCache.Entry;

class CountingMemoryCache$2 implements ValueDescriptor<Entry<K, V>> {
    final /* synthetic */ CountingMemoryCache this$0;
    final /* synthetic */ ValueDescriptor val$evictableValueDescriptor;

    CountingMemoryCache$2(CountingMemoryCache countingMemoryCache, ValueDescriptor valueDescriptor) {
        this.this$0 = countingMemoryCache;
        this.val$evictableValueDescriptor = valueDescriptor;
    }

    public int getSizeInBytes(Entry<K, V> entry) {
        return this.val$evictableValueDescriptor.getSizeInBytes(entry.valueRef.get());
    }
}
