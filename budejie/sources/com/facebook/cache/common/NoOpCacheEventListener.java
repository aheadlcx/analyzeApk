package com.facebook.cache.common;

public class NoOpCacheEventListener implements CacheEventListener {
    private static NoOpCacheEventListener sInstance = null;

    private NoOpCacheEventListener() {
    }

    public static synchronized NoOpCacheEventListener getInstance() {
        NoOpCacheEventListener noOpCacheEventListener;
        synchronized (NoOpCacheEventListener.class) {
            if (sInstance == null) {
                sInstance = new NoOpCacheEventListener();
            }
            noOpCacheEventListener = sInstance;
        }
        return noOpCacheEventListener;
    }

    public void onHit(CacheEvent cacheEvent) {
    }

    public void onMiss(CacheEvent cacheEvent) {
    }

    public void onWriteAttempt(CacheEvent cacheEvent) {
    }

    public void onWriteSuccess(CacheEvent cacheEvent) {
    }

    public void onReadException(CacheEvent cacheEvent) {
    }

    public void onWriteException(CacheEvent cacheEvent) {
    }

    public void onEviction(CacheEvent cacheEvent) {
    }

    public void onCleared() {
    }
}
