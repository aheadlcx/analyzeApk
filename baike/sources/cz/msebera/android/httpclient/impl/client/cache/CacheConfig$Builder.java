package cz.msebera.android.httpclient.impl.client.cache;

import android.support.v4.media.session.PlaybackStateCompat;

public class CacheConfig$Builder {
    private long a = PlaybackStateCompat.ACTION_PLAY_FROM_URI;
    private int b = 1000;
    private int c = 1;
    private boolean d = false;
    private boolean e = false;
    private boolean f = false;
    private float g = 0.1f;
    private long h = 0;
    private boolean i = true;
    private int j = 1;
    private int k = 1;
    private int l = 60;
    private int m = 100;
    private boolean n;

    CacheConfig$Builder() {
    }

    public CacheConfig$Builder setMaxObjectSize(long j) {
        this.a = j;
        return this;
    }

    public CacheConfig$Builder setMaxCacheEntries(int i) {
        this.b = i;
        return this;
    }

    public CacheConfig$Builder setMaxUpdateRetries(int i) {
        this.c = i;
        return this;
    }

    public CacheConfig$Builder setAllow303Caching(boolean z) {
        this.d = z;
        return this;
    }

    public CacheConfig$Builder setWeakETagOnPutDeleteAllowed(boolean z) {
        this.e = z;
        return this;
    }

    public CacheConfig$Builder setHeuristicCachingEnabled(boolean z) {
        this.f = z;
        return this;
    }

    public CacheConfig$Builder setHeuristicCoefficient(float f) {
        this.g = f;
        return this;
    }

    public CacheConfig$Builder setHeuristicDefaultLifetime(long j) {
        this.h = j;
        return this;
    }

    public CacheConfig$Builder setSharedCache(boolean z) {
        this.i = z;
        return this;
    }

    public CacheConfig$Builder setAsynchronousWorkersMax(int i) {
        this.j = i;
        return this;
    }

    public CacheConfig$Builder setAsynchronousWorkersCore(int i) {
        this.k = i;
        return this;
    }

    public CacheConfig$Builder setAsynchronousWorkerIdleLifetimeSecs(int i) {
        this.l = i;
        return this;
    }

    public CacheConfig$Builder setRevalidationQueueSize(int i) {
        this.m = i;
        return this;
    }

    public CacheConfig$Builder setNeverCacheHTTP10ResponsesWithQueryString(boolean z) {
        this.n = z;
        return this;
    }

    public CacheConfig build() {
        return new CacheConfig(this.a, this.b, this.c, this.d, this.e, this.f, this.g, this.h, this.i, this.j, this.k, this.l, this.m, this.n);
    }
}
