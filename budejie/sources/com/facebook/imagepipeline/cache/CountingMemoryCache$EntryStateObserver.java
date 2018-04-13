package com.facebook.imagepipeline.cache;

public interface CountingMemoryCache$EntryStateObserver<K> {
    void onExclusivityChanged(K k, boolean z);
}
