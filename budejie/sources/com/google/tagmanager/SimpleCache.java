package com.google.tagmanager;

import com.google.tagmanager.CacheFactory.CacheSizeManager;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

class SimpleCache<K, V> implements Cache<K, V> {
    private final Map<K, V> mHashMap = new HashMap();
    private final int mMaxSize;
    private final CacheSizeManager<K, V> mSizeManager;
    private int mTotalSize;

    SimpleCache(int i, CacheSizeManager<K, V> cacheSizeManager) {
        this.mMaxSize = i;
        this.mSizeManager = cacheSizeManager;
    }

    public synchronized void put(K k, V v) {
        if (k == null || v == null) {
            throw new NullPointerException("key == null || value == null");
        }
        this.mTotalSize += this.mSizeManager.sizeOf(k, v);
        if (this.mTotalSize > this.mMaxSize) {
            Iterator it = this.mHashMap.entrySet().iterator();
            while (it.hasNext()) {
                Entry entry = (Entry) it.next();
                this.mTotalSize -= this.mSizeManager.sizeOf(entry.getKey(), entry.getValue());
                it.remove();
                if (this.mTotalSize <= this.mMaxSize) {
                    break;
                }
            }
        }
        this.mHashMap.put(k, v);
    }

    public synchronized V get(K k) {
        return this.mHashMap.get(k);
    }
}
