package cz.msebera.android.httpclient.impl.client.cache;

import android.support.v4.media.session.PlaybackStateCompat;
import cz.msebera.android.httpclient.util.Args;

public class CacheConfig implements Cloneable {
    public static final CacheConfig DEFAULT = new CacheConfig$Builder().build();
    public static final boolean DEFAULT_303_CACHING_ENABLED = false;
    public static final int DEFAULT_ASYNCHRONOUS_WORKERS_CORE = 1;
    public static final int DEFAULT_ASYNCHRONOUS_WORKERS_MAX = 1;
    public static final int DEFAULT_ASYNCHRONOUS_WORKER_IDLE_LIFETIME_SECS = 60;
    public static final boolean DEFAULT_HEURISTIC_CACHING_ENABLED = false;
    public static final float DEFAULT_HEURISTIC_COEFFICIENT = 0.1f;
    public static final long DEFAULT_HEURISTIC_LIFETIME = 0;
    public static final int DEFAULT_MAX_CACHE_ENTRIES = 1000;
    public static final int DEFAULT_MAX_OBJECT_SIZE_BYTES = 8192;
    public static final int DEFAULT_MAX_UPDATE_RETRIES = 1;
    public static final int DEFAULT_REVALIDATION_QUEUE_SIZE = 100;
    public static final boolean DEFAULT_WEAK_ETAG_ON_PUTDELETE_ALLOWED = false;
    private long a;
    private int b;
    private int c;
    private boolean d;
    private boolean e;
    private boolean f;
    private float g;
    private long h;
    private boolean i;
    private int j;
    private int k;
    private int l;
    private int m;
    private boolean n;

    protected /* synthetic */ Object clone() throws CloneNotSupportedException {
        return a();
    }

    @Deprecated
    public CacheConfig() {
        this.a = PlaybackStateCompat.ACTION_PLAY_FROM_URI;
        this.b = 1000;
        this.c = 1;
        this.d = false;
        this.e = false;
        this.f = false;
        this.g = 0.1f;
        this.h = 0;
        this.i = true;
        this.j = 1;
        this.k = 1;
        this.l = 60;
        this.m = 100;
    }

    CacheConfig(long j, int i, int i2, boolean z, boolean z2, boolean z3, float f, long j2, boolean z4, int i3, int i4, int i5, int i6, boolean z5) {
        this.a = j;
        this.b = i;
        this.c = i2;
        this.d = z;
        this.e = z2;
        this.f = z3;
        this.g = f;
        this.h = j2;
        this.i = z4;
        this.j = i3;
        this.k = i4;
        this.l = i5;
        this.m = i6;
    }

    @Deprecated
    public int getMaxObjectSizeBytes() {
        return this.a > 2147483647L ? Integer.MAX_VALUE : (int) this.a;
    }

    @Deprecated
    public void setMaxObjectSizeBytes(int i) {
        if (i > Integer.MAX_VALUE) {
            this.a = 2147483647L;
        } else {
            this.a = (long) i;
        }
    }

    public long getMaxObjectSize() {
        return this.a;
    }

    @Deprecated
    public void setMaxObjectSize(long j) {
        this.a = j;
    }

    public boolean isNeverCacheHTTP10ResponsesWithQuery() {
        return this.n;
    }

    public int getMaxCacheEntries() {
        return this.b;
    }

    @Deprecated
    public void setMaxCacheEntries(int i) {
        this.b = i;
    }

    public int getMaxUpdateRetries() {
        return this.c;
    }

    @Deprecated
    public void setMaxUpdateRetries(int i) {
        this.c = i;
    }

    public boolean is303CachingEnabled() {
        return this.d;
    }

    public boolean isWeakETagOnPutDeleteAllowed() {
        return this.e;
    }

    public boolean isHeuristicCachingEnabled() {
        return this.f;
    }

    @Deprecated
    public void setHeuristicCachingEnabled(boolean z) {
        this.f = z;
    }

    public float getHeuristicCoefficient() {
        return this.g;
    }

    @Deprecated
    public void setHeuristicCoefficient(float f) {
        this.g = f;
    }

    public long getHeuristicDefaultLifetime() {
        return this.h;
    }

    @Deprecated
    public void setHeuristicDefaultLifetime(long j) {
        this.h = j;
    }

    public boolean isSharedCache() {
        return this.i;
    }

    @Deprecated
    public void setSharedCache(boolean z) {
        this.i = z;
    }

    public int getAsynchronousWorkersMax() {
        return this.j;
    }

    @Deprecated
    public void setAsynchronousWorkersMax(int i) {
        this.j = i;
    }

    public int getAsynchronousWorkersCore() {
        return this.k;
    }

    @Deprecated
    public void setAsynchronousWorkersCore(int i) {
        this.k = i;
    }

    public int getAsynchronousWorkerIdleLifetimeSecs() {
        return this.l;
    }

    @Deprecated
    public void setAsynchronousWorkerIdleLifetimeSecs(int i) {
        this.l = i;
    }

    public int getRevalidationQueueSize() {
        return this.m;
    }

    @Deprecated
    public void setRevalidationQueueSize(int i) {
        this.m = i;
    }

    protected CacheConfig a() throws CloneNotSupportedException {
        return (CacheConfig) super.clone();
    }

    public static CacheConfig$Builder custom() {
        return new CacheConfig$Builder();
    }

    public static CacheConfig$Builder copy(CacheConfig cacheConfig) {
        Args.notNull(cacheConfig, "Cache config");
        return new CacheConfig$Builder().setMaxObjectSize(cacheConfig.getMaxObjectSize()).setMaxCacheEntries(cacheConfig.getMaxCacheEntries()).setMaxUpdateRetries(cacheConfig.getMaxUpdateRetries()).setHeuristicCachingEnabled(cacheConfig.isHeuristicCachingEnabled()).setHeuristicCoefficient(cacheConfig.getHeuristicCoefficient()).setHeuristicDefaultLifetime(cacheConfig.getHeuristicDefaultLifetime()).setSharedCache(cacheConfig.isSharedCache()).setAsynchronousWorkersMax(cacheConfig.getAsynchronousWorkersMax()).setAsynchronousWorkersCore(cacheConfig.getAsynchronousWorkersCore()).setAsynchronousWorkerIdleLifetimeSecs(cacheConfig.getAsynchronousWorkerIdleLifetimeSecs()).setRevalidationQueueSize(cacheConfig.getRevalidationQueueSize()).setNeverCacheHTTP10ResponsesWithQueryString(cacheConfig.isNeverCacheHTTP10ResponsesWithQuery());
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[maxObjectSize=").append(this.a).append(", maxCacheEntries=").append(this.b).append(", maxUpdateRetries=").append(this.c).append(", 303CachingEnabled=").append(this.d).append(", weakETagOnPutDeleteAllowed=").append(this.e).append(", heuristicCachingEnabled=").append(this.f).append(", heuristicCoefficient=").append(this.g).append(", heuristicDefaultLifetime=").append(this.h).append(", isSharedCache=").append(this.i).append(", asynchronousWorkersMax=").append(this.j).append(", asynchronousWorkersCore=").append(this.k).append(", asynchronousWorkerIdleLifetimeSecs=").append(this.l).append(", revalidationQueueSize=").append(this.m).append(", neverCacheHTTP10ResponsesWithQuery=").append(this.n).append("]");
        return stringBuilder.toString();
    }
}
