package com.google.tagmanager;

import android.annotation.TargetApi;
import android.util.LruCache;
import com.google.tagmanager.CacheFactory.CacheSizeManager;

@TargetApi(12)
class LRUCache<K, V> implements Cache<K, V> {
    private LruCache<K, V> lruCache;

    LRUCache(int i, final CacheSizeManager<K, V> cacheSizeManager) {
        this.lruCache = new LruCache<K, V>(i) {
            protected int sizeOf(K k, V v) {
                return cacheSizeManager.sizeOf(k, v);
            }
        };
    }

    public void put(K k, V v) {
        this.lruCache.put(k, v);
    }

    public V get(K k) {
        return this.lruCache.get(k);
    }
}
