package cz.msebera.android.httpclient.impl.client.cache;

import cz.msebera.android.httpclient.client.cache.HttpCacheEntry;
import cz.msebera.android.httpclient.client.methods.HttpExecutionAware;
import cz.msebera.android.httpclient.client.methods.HttpRequestWrapper;
import cz.msebera.android.httpclient.client.protocol.HttpClientContext;
import cz.msebera.android.httpclient.conn.routing.HttpRoute;
import cz.msebera.android.httpclient.extras.HttpClientAndroidLog;
import java.io.Closeable;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.RejectedExecutionException;

class a implements Closeable {
    private final SchedulingStrategy a;
    private final Set<String> b;
    private final i c;
    private final FailureCache d;
    public HttpClientAndroidLog log;

    public a(CacheConfig cacheConfig) {
        this(new ImmediateSchedulingStrategy(cacheConfig));
    }

    a(SchedulingStrategy schedulingStrategy) {
        this.log = new HttpClientAndroidLog(getClass());
        this.a = schedulingStrategy;
        this.b = new HashSet();
        this.c = new i();
        this.d = new DefaultFailureCache();
    }

    public void close() throws IOException {
        this.a.close();
    }

    public synchronized void revalidateCacheEntry(CachingExec cachingExec, HttpRoute httpRoute, HttpRequestWrapper httpRequestWrapper, HttpClientContext httpClientContext, HttpExecutionAware httpExecutionAware, HttpCacheEntry httpCacheEntry) {
        String variantURI = this.c.getVariantURI(httpClientContext.getTargetHost(), httpRequestWrapper, httpCacheEntry);
        if (!this.b.contains(variantURI)) {
            try {
                this.a.schedule(new AsynchronousValidationRequest(this, cachingExec, httpRoute, httpRequestWrapper, httpClientContext, httpExecutionAware, httpCacheEntry, variantURI, this.d.getErrorCount(variantURI)));
                this.b.add(variantURI);
            } catch (RejectedExecutionException e) {
                this.log.debug("Revalidation for [" + variantURI + "] not scheduled: " + e);
            }
        }
    }

    synchronized void a(String str) {
        this.b.remove(str);
    }

    void b(String str) {
        this.d.resetErrorCount(str);
    }

    void c(String str) {
        this.d.increaseErrorCount(str);
    }
}
