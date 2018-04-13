package cz.msebera.android.httpclient.impl.client.cache;

import cz.msebera.android.httpclient.client.cache.HttpCacheInvalidator;
import cz.msebera.android.httpclient.client.cache.HttpCacheStorage;
import cz.msebera.android.httpclient.client.cache.ResourceFactory;
import cz.msebera.android.httpclient.impl.client.HttpClientBuilder;
import cz.msebera.android.httpclient.impl.execchain.ClientExecChain;
import java.io.File;

public class CachingHttpClientBuilder extends HttpClientBuilder {
    private ResourceFactory a;
    private HttpCacheStorage b;
    private File c;
    private CacheConfig d;
    private SchedulingStrategy e;
    private HttpCacheInvalidator f;
    private boolean g = true;

    public static CachingHttpClientBuilder create() {
        return new CachingHttpClientBuilder();
    }

    protected CachingHttpClientBuilder() {
    }

    public final CachingHttpClientBuilder setResourceFactory(ResourceFactory resourceFactory) {
        this.a = resourceFactory;
        return this;
    }

    public final CachingHttpClientBuilder setHttpCacheStorage(HttpCacheStorage httpCacheStorage) {
        this.b = httpCacheStorage;
        return this;
    }

    public final CachingHttpClientBuilder setCacheDir(File file) {
        this.c = file;
        return this;
    }

    public final CachingHttpClientBuilder setCacheConfig(CacheConfig cacheConfig) {
        this.d = cacheConfig;
        return this;
    }

    public final CachingHttpClientBuilder setSchedulingStrategy(SchedulingStrategy schedulingStrategy) {
        this.e = schedulingStrategy;
        return this;
    }

    public final CachingHttpClientBuilder setHttpCacheInvalidator(HttpCacheInvalidator httpCacheInvalidator) {
        this.f = httpCacheInvalidator;
        return this;
    }

    public CachingHttpClientBuilder setDeleteCache(boolean z) {
        this.g = z;
        return this;
    }

    protected ClientExecChain a(ClientExecChain clientExecChain) {
        CacheConfig cacheConfig = this.d != null ? this.d : CacheConfig.DEFAULT;
        ResourceFactory resourceFactory = this.a;
        if (resourceFactory == null) {
            if (this.c == null) {
                resourceFactory = new HeapResourceFactory();
            } else {
                resourceFactory = new FileResourceFactory(this.c);
            }
        }
        HttpCacheStorage httpCacheStorage = this.b;
        if (httpCacheStorage == null) {
            if (this.c == null) {
                httpCacheStorage = new BasicHttpCacheStorage(cacheConfig);
            } else {
                httpCacheStorage = new ManagedHttpCacheStorage(cacheConfig);
                if (this.g) {
                    a(new o(this, httpCacheStorage));
                } else {
                    a(httpCacheStorage);
                }
            }
        }
        a a = a(cacheConfig);
        i iVar = new i();
        HttpCacheInvalidator httpCacheInvalidator = this.f;
        if (httpCacheInvalidator == null) {
            httpCacheInvalidator = new h(iVar, httpCacheStorage);
        }
        return new CachingExec(clientExecChain, new b(resourceFactory, httpCacheStorage, cacheConfig, iVar, httpCacheInvalidator), cacheConfig, a);
    }

    private a a(CacheConfig cacheConfig) {
        if (cacheConfig.getAsynchronousWorkersMax() <= 0) {
            return null;
        }
        Object aVar = new a(b(cacheConfig));
        a(aVar);
        return aVar;
    }

    private SchedulingStrategy b(CacheConfig cacheConfig) {
        return this.e != null ? this.e : new ImmediateSchedulingStrategy(cacheConfig);
    }
}
