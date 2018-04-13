package cz.msebera.android.httpclient.impl.client.cache;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.HttpHost;
import cz.msebera.android.httpclient.HttpRequest;
import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.HttpStatus;
import cz.msebera.android.httpclient.HttpVersion;
import cz.msebera.android.httpclient.client.cache.HttpCacheEntry;
import cz.msebera.android.httpclient.client.cache.HttpCacheInvalidator;
import cz.msebera.android.httpclient.client.cache.HttpCacheStorage;
import cz.msebera.android.httpclient.client.cache.Resource;
import cz.msebera.android.httpclient.client.cache.ResourceFactory;
import cz.msebera.android.httpclient.client.methods.CloseableHttpResponse;
import cz.msebera.android.httpclient.client.methods.HttpRequestWrapper;
import cz.msebera.android.httpclient.entity.ByteArrayEntity;
import cz.msebera.android.httpclient.extras.HttpClientAndroidLog;
import cz.msebera.android.httpclient.message.BasicHttpResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

class b implements r {
    private static final Set<String> a = new HashSet(Arrays.asList(new String[]{"HEAD", "GET", "OPTIONS", "TRACE"}));
    private final i b;
    private final ResourceFactory c;
    private final long d;
    private final g e;
    private final m f;
    private final HttpCacheInvalidator g;
    private final HttpCacheStorage h;
    public HttpClientAndroidLog log;

    public b(ResourceFactory resourceFactory, HttpCacheStorage httpCacheStorage, CacheConfig cacheConfig, i iVar, HttpCacheInvalidator httpCacheInvalidator) {
        this.log = new HttpClientAndroidLog(getClass());
        this.c = resourceFactory;
        this.b = iVar;
        this.e = new g(resourceFactory);
        this.d = cacheConfig.getMaxObjectSize();
        this.f = new m();
        this.h = httpCacheStorage;
        this.g = httpCacheInvalidator;
    }

    public b(ResourceFactory resourceFactory, HttpCacheStorage httpCacheStorage, CacheConfig cacheConfig, i iVar) {
        this(resourceFactory, httpCacheStorage, cacheConfig, iVar, new h(iVar, httpCacheStorage));
    }

    public b(ResourceFactory resourceFactory, HttpCacheStorage httpCacheStorage, CacheConfig cacheConfig) {
        this(resourceFactory, httpCacheStorage, cacheConfig, new i());
    }

    public b(CacheConfig cacheConfig) {
        this(new HeapResourceFactory(), new BasicHttpCacheStorage(cacheConfig), cacheConfig);
    }

    public b() {
        this(CacheConfig.DEFAULT);
    }

    public void flushCacheEntriesFor(HttpHost httpHost, HttpRequest httpRequest) throws IOException {
        if (!a.contains(httpRequest.getRequestLine().getMethod())) {
            this.h.removeEntry(this.b.getURI(httpHost, httpRequest));
        }
    }

    public void flushInvalidatedCacheEntriesFor(HttpHost httpHost, HttpRequest httpRequest, HttpResponse httpResponse) {
        if (!a.contains(httpRequest.getRequestLine().getMethod())) {
            this.g.flushInvalidatedCacheEntries(httpHost, httpRequest, httpResponse);
        }
    }

    void a(HttpHost httpHost, HttpRequest httpRequest, HttpCacheEntry httpCacheEntry) throws IOException {
        if (httpCacheEntry.hasVariants()) {
            c(httpHost, httpRequest, httpCacheEntry);
        } else {
            b(httpHost, httpRequest, httpCacheEntry);
        }
    }

    void b(HttpHost httpHost, HttpRequest httpRequest, HttpCacheEntry httpCacheEntry) throws IOException {
        this.h.putEntry(this.b.getURI(httpHost, httpRequest), httpCacheEntry);
    }

    void c(HttpHost httpHost, HttpRequest httpRequest, HttpCacheEntry httpCacheEntry) throws IOException {
        String uri = this.b.getURI(httpHost, httpRequest);
        String variantURI = this.b.getVariantURI(httpHost, httpRequest, httpCacheEntry);
        this.h.putEntry(variantURI, httpCacheEntry);
        try {
            this.h.updateEntry(uri, new c(this, httpRequest, httpCacheEntry, variantURI));
        } catch (Throwable e) {
            this.log.warn("Could not update key [" + uri + "]", e);
        }
    }

    public void reuseVariantEntryFor(HttpHost httpHost, HttpRequest httpRequest, ae aeVar) throws IOException {
        String uri = this.b.getURI(httpHost, httpRequest);
        HttpCacheEntry entry = aeVar.getEntry();
        try {
            this.h.updateEntry(uri, new d(this, httpRequest, entry, this.b.getVariantKey(httpRequest, entry), aeVar.getCacheKey()));
        } catch (Throwable e) {
            this.log.warn("Could not update key [" + uri + "]", e);
        }
    }

    boolean a(HttpResponse httpResponse, Resource resource) {
        int statusCode = httpResponse.getStatusLine().getStatusCode();
        if (statusCode != 200 && statusCode != HttpStatus.SC_PARTIAL_CONTENT) {
            return false;
        }
        Header firstHeader = httpResponse.getFirstHeader("Content-Length");
        if (firstHeader == null) {
            return false;
        }
        try {
            statusCode = Integer.parseInt(firstHeader.getValue());
            if (resource == null || resource.length() >= ((long) statusCode)) {
                return false;
            }
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    CloseableHttpResponse b(HttpResponse httpResponse, Resource resource) {
        Integer valueOf = Integer.valueOf(httpResponse.getFirstHeader("Content-Length").getValue());
        HttpResponse basicHttpResponse = new BasicHttpResponse(HttpVersion.HTTP_1_1, 502, "Bad Gateway");
        basicHttpResponse.setHeader("Content-Type", "text/plain;charset=UTF-8");
        byte[] bytes = String.format("Received incomplete response with Content-Length %d but actual body length %d", new Object[]{valueOf, Long.valueOf(resource.length())}).getBytes();
        basicHttpResponse.setHeader("Content-Length", Integer.toString(bytes.length));
        basicHttpResponse.setEntity(new ByteArrayEntity(bytes));
        return u.enhanceResponse(basicHttpResponse);
    }

    HttpCacheEntry a(String str, HttpCacheEntry httpCacheEntry, HttpCacheEntry httpCacheEntry2, String str2, String str3) throws IOException {
        if (httpCacheEntry != null) {
            httpCacheEntry2 = httpCacheEntry;
        }
        Resource resource = null;
        if (httpCacheEntry2.getResource() != null) {
            resource = this.c.copy(str, httpCacheEntry2.getResource());
        }
        Map hashMap = new HashMap(httpCacheEntry2.getVariantMap());
        hashMap.put(str2, str3);
        return new HttpCacheEntry(httpCacheEntry2.getRequestDate(), httpCacheEntry2.getResponseDate(), httpCacheEntry2.getStatusLine(), httpCacheEntry2.getAllHeaders(), resource, hashMap, httpCacheEntry2.getRequestMethod());
    }

    public HttpCacheEntry updateCacheEntry(HttpHost httpHost, HttpRequest httpRequest, HttpCacheEntry httpCacheEntry, HttpResponse httpResponse, Date date, Date date2) throws IOException {
        HttpCacheEntry updateCacheEntry = this.e.updateCacheEntry(httpRequest.getRequestLine().getUri(), httpCacheEntry, date, date2, httpResponse);
        a(httpHost, httpRequest, updateCacheEntry);
        return updateCacheEntry;
    }

    public HttpCacheEntry updateVariantCacheEntry(HttpHost httpHost, HttpRequest httpRequest, HttpCacheEntry httpCacheEntry, HttpResponse httpResponse, Date date, Date date2, String str) throws IOException {
        HttpCacheEntry updateCacheEntry = this.e.updateCacheEntry(httpRequest.getRequestLine().getUri(), httpCacheEntry, date, date2, httpResponse);
        this.h.putEntry(str, updateCacheEntry);
        return updateCacheEntry;
    }

    public HttpResponse cacheAndReturnResponse(HttpHost httpHost, HttpRequest httpRequest, HttpResponse httpResponse, Date date, Date date2) throws IOException {
        return cacheAndReturnResponse(httpHost, httpRequest, u.enhanceResponse(httpResponse), date, date2);
    }

    public CloseableHttpResponse cacheAndReturnResponse(HttpHost httpHost, HttpRequest httpRequest, CloseableHttpResponse closeableHttpResponse, Date date, Date date2) throws IOException {
        Object obj;
        Throwable th;
        ac a = a(httpRequest, closeableHttpResponse);
        try {
            CloseableHttpResponse d;
            a.a();
            if (a.b()) {
                obj = null;
                try {
                    d = a.d();
                } catch (Throwable th2) {
                    th = th2;
                    if (obj != null) {
                        closeableHttpResponse.close();
                    }
                    throw th;
                }
            }
            Resource c = a.c();
            if (a((HttpResponse) closeableHttpResponse, c)) {
                d = b(closeableHttpResponse, c);
                closeableHttpResponse.close();
            } else {
                HttpCacheEntry httpCacheEntry = new HttpCacheEntry(date, date2, closeableHttpResponse.getStatusLine(), closeableHttpResponse.getAllHeaders(), c, httpRequest.getRequestLine().getMethod());
                a(httpHost, httpRequest, httpCacheEntry);
                d = this.f.a(HttpRequestWrapper.wrap(httpRequest, httpHost), httpCacheEntry);
                closeableHttpResponse.close();
            }
            return d;
        } catch (Throwable th3) {
            th = th3;
            obj = 1;
            if (obj != null) {
                closeableHttpResponse.close();
            }
            throw th;
        }
    }

    ac a(HttpRequest httpRequest, CloseableHttpResponse closeableHttpResponse) {
        return new ac(this.c, this.d, httpRequest, closeableHttpResponse);
    }

    public HttpCacheEntry getCacheEntry(HttpHost httpHost, HttpRequest httpRequest) throws IOException {
        HttpCacheEntry entry = this.h.getEntry(this.b.getURI(httpHost, httpRequest));
        if (entry == null) {
            return null;
        }
        if (!entry.hasVariants()) {
            return entry;
        }
        String str = (String) entry.getVariantMap().get(this.b.getVariantKey(httpRequest, entry));
        if (str == null) {
            return null;
        }
        return this.h.getEntry(str);
    }

    public void flushInvalidatedCacheEntriesFor(HttpHost httpHost, HttpRequest httpRequest) throws IOException {
        this.g.flushInvalidatedCacheEntries(httpHost, httpRequest);
    }

    public Map<String, ae> getVariantCacheEntriesWithEtags(HttpHost httpHost, HttpRequest httpRequest) throws IOException {
        Map<String, ae> hashMap = new HashMap();
        HttpCacheEntry entry = this.h.getEntry(this.b.getURI(httpHost, httpRequest));
        if (entry == null || !entry.hasVariants()) {
            return hashMap;
        }
        for (Entry entry2 : entry.getVariantMap().entrySet()) {
            a((String) entry2.getKey(), (String) entry2.getValue(), (Map) hashMap);
        }
        return hashMap;
    }

    private void a(String str, String str2, Map<String, ae> map) throws IOException {
        HttpCacheEntry entry = this.h.getEntry(str2);
        if (entry != null) {
            Header firstHeader = entry.getFirstHeader("ETag");
            if (firstHeader != null) {
                map.put(firstHeader.getValue(), new ae(str, str2, entry));
            }
        }
    }
}
