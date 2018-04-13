package cz.msebera.android.httpclient.impl.client.cache;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.HeaderElement;
import cz.msebera.android.httpclient.HttpException;
import cz.msebera.android.httpclient.HttpHost;
import cz.msebera.android.httpclient.HttpMessage;
import cz.msebera.android.httpclient.HttpRequest;
import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.HttpStatus;
import cz.msebera.android.httpclient.HttpVersion;
import cz.msebera.android.httpclient.ProtocolException;
import cz.msebera.android.httpclient.ProtocolVersion;
import cz.msebera.android.httpclient.RequestLine;
import cz.msebera.android.httpclient.annotation.ThreadSafe;
import cz.msebera.android.httpclient.client.cache.CacheResponseStatus;
import cz.msebera.android.httpclient.client.cache.HeaderConstants;
import cz.msebera.android.httpclient.client.cache.HttpCacheContext;
import cz.msebera.android.httpclient.client.cache.HttpCacheEntry;
import cz.msebera.android.httpclient.client.cache.HttpCacheStorage;
import cz.msebera.android.httpclient.client.cache.ResourceFactory;
import cz.msebera.android.httpclient.client.methods.CloseableHttpResponse;
import cz.msebera.android.httpclient.client.methods.HttpExecutionAware;
import cz.msebera.android.httpclient.client.methods.HttpRequestWrapper;
import cz.msebera.android.httpclient.client.protocol.HttpClientContext;
import cz.msebera.android.httpclient.client.utils.DateUtils;
import cz.msebera.android.httpclient.client.utils.URIUtils;
import cz.msebera.android.httpclient.conn.routing.HttpRoute;
import cz.msebera.android.httpclient.extras.HttpClientAndroidLog;
import cz.msebera.android.httpclient.impl.execchain.ClientExecChain;
import cz.msebera.android.httpclient.message.BasicHttpResponse;
import cz.msebera.android.httpclient.protocol.HttpContext;
import cz.msebera.android.httpclient.util.Args;
import cz.msebera.android.httpclient.util.VersionInfo;
import java.io.IOException;
import java.net.URI;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@ThreadSafe
public class CachingExec implements ClientExecChain {
    private final AtomicLong a;
    private final AtomicLong b;
    private final AtomicLong c;
    private final Map<ProtocolVersion, String> d;
    private final CacheConfig e;
    private final ClientExecChain f;
    private final r g;
    private final k h;
    private final m i;
    private final l j;
    private final n k;
    private final q l;
    public HttpClientAndroidLog log;
    private final aa m;
    private final v n;
    private final z o;
    private final a p;

    public CachingExec(ClientExecChain clientExecChain, r rVar, CacheConfig cacheConfig) {
        this(clientExecChain, rVar, cacheConfig, null);
    }

    public CachingExec(ClientExecChain clientExecChain, r rVar, CacheConfig cacheConfig, a aVar) {
        this.a = new AtomicLong();
        this.b = new AtomicLong();
        this.c = new AtomicLong();
        this.d = new HashMap(4);
        this.log = new HttpClientAndroidLog(getClass());
        Args.notNull(clientExecChain, "HTTP backend");
        Args.notNull(rVar, "HttpCache");
        if (cacheConfig == null) {
            cacheConfig = CacheConfig.DEFAULT;
        }
        this.e = cacheConfig;
        this.f = clientExecChain;
        this.g = rVar;
        this.h = new k();
        this.i = new m(this.h);
        this.j = new l();
        this.k = new n(this.h, this.e);
        this.l = new q();
        this.m = new aa();
        this.n = new v(this.e.isWeakETagOnPutDeleteAllowed());
        this.o = new z(this.e.getMaxObjectSize(), this.e.isSharedCache(), this.e.isNeverCacheHTTP10ResponsesWithQuery(), this.e.is303CachingEnabled());
        this.p = aVar;
    }

    public CachingExec(ClientExecChain clientExecChain, ResourceFactory resourceFactory, HttpCacheStorage httpCacheStorage, CacheConfig cacheConfig) {
        this(clientExecChain, new b(resourceFactory, httpCacheStorage, cacheConfig), cacheConfig);
    }

    public CachingExec(ClientExecChain clientExecChain) {
        this(clientExecChain, new b(), CacheConfig.DEFAULT);
    }

    public long getCacheHits() {
        return this.a.get();
    }

    public long getCacheMisses() {
        return this.b.get();
    }

    public long getCacheUpdates() {
        return this.c.get();
    }

    public CloseableHttpResponse execute(HttpRoute httpRoute, HttpRequestWrapper httpRequestWrapper) throws IOException, HttpException {
        return execute(httpRoute, httpRequestWrapper, HttpClientContext.create(), null);
    }

    public CloseableHttpResponse execute(HttpRoute httpRoute, HttpRequestWrapper httpRequestWrapper, HttpClientContext httpClientContext) throws IOException, HttpException {
        return execute(httpRoute, httpRequestWrapper, httpClientContext, null);
    }

    public CloseableHttpResponse execute(HttpRoute httpRoute, HttpRequestWrapper httpRequestWrapper, HttpClientContext httpClientContext, HttpExecutionAware httpExecutionAware) throws IOException, HttpException {
        HttpHost targetHost = httpClientContext.getTargetHost();
        String a = a(httpRequestWrapper.getOriginal());
        a((HttpContext) httpClientContext, CacheResponseStatus.CACHE_MISS);
        if (a((HttpRequest) httpRequestWrapper)) {
            a((HttpContext) httpClientContext, CacheResponseStatus.CACHE_MODULE_RESPONSE);
            return u.enhanceResponse(new t());
        }
        HttpResponse a2 = a(httpRequestWrapper, (HttpContext) httpClientContext);
        if (a2 != null) {
            return u.enhanceResponse(a2);
        }
        this.n.makeRequestCompliant(httpRequestWrapper);
        httpRequestWrapper.addHeader("Via", a);
        e(httpClientContext.getTargetHost(), httpRequestWrapper);
        if (this.j.isServableFromCache(httpRequestWrapper)) {
            HttpCacheEntry a3 = a(targetHost, httpRequestWrapper);
            if (a3 != null) {
                return b(httpRoute, httpRequestWrapper, httpClientContext, httpExecutionAware, a3);
            }
            this.log.debug("Cache miss");
            return b(httpRoute, httpRequestWrapper, httpClientContext, httpExecutionAware);
        }
        this.log.debug("Request is not servable from cache");
        return a(httpRoute, httpRequestWrapper, httpClientContext, httpExecutionAware);
    }

    private CloseableHttpResponse b(HttpRoute httpRoute, HttpRequestWrapper httpRequestWrapper, HttpClientContext httpClientContext, HttpExecutionAware httpExecutionAware, HttpCacheEntry httpCacheEntry) throws IOException, HttpException {
        CloseableHttpResponse a;
        HttpHost targetHost = httpClientContext.getTargetHost();
        d(targetHost, httpRequestWrapper);
        Date a2 = a();
        if (this.k.canCachedResponseBeUsed(targetHost, httpRequestWrapper, httpCacheEntry, a2)) {
            this.log.debug("Cache hit");
            a = a(httpRequestWrapper, (HttpContext) httpClientContext, httpCacheEntry, a2);
        } else if (!a(httpRequestWrapper)) {
            this.log.debug("Cache entry not suitable but only-if-cached requested");
            a = b(httpClientContext);
        } else if (httpCacheEntry.getStatusCode() != 304 || this.k.isConditional(httpRequestWrapper)) {
            this.log.debug("Revalidating cache entry");
            return a(httpRoute, httpRequestWrapper, httpClientContext, httpExecutionAware, httpCacheEntry, a2);
        } else {
            this.log.debug("Cache entry not usable; calling backend");
            return a(httpRoute, httpRequestWrapper, httpClientContext, httpExecutionAware);
        }
        httpClientContext.setAttribute("http.route", httpRoute);
        httpClientContext.setAttribute("http.target_host", targetHost);
        httpClientContext.setAttribute("http.request", httpRequestWrapper);
        httpClientContext.setAttribute("http.response", a);
        httpClientContext.setAttribute("http.request_sent", Boolean.TRUE);
        return a;
    }

    private CloseableHttpResponse a(HttpRoute httpRoute, HttpRequestWrapper httpRequestWrapper, HttpClientContext httpClientContext, HttpExecutionAware httpExecutionAware, HttpCacheEntry httpCacheEntry, Date date) throws HttpException {
        try {
            if (this.p == null || a(httpRequestWrapper, httpCacheEntry, date) || !this.h.mayReturnStaleWhileRevalidating(httpCacheEntry, date)) {
                return a(httpRoute, httpRequestWrapper, httpClientContext, httpExecutionAware, httpCacheEntry);
            }
            this.log.trace("Serving stale with asynchronous revalidation");
            CloseableHttpResponse a = a(httpRequestWrapper, (HttpContext) httpClientContext, httpCacheEntry, date);
            this.p.revalidateCacheEntry(this, httpRoute, httpRequestWrapper, httpClientContext, httpExecutionAware, httpCacheEntry);
            return a;
        } catch (IOException e) {
            return b(httpRequestWrapper, (HttpContext) httpClientContext, httpCacheEntry, date);
        }
    }

    private CloseableHttpResponse b(HttpRoute httpRoute, HttpRequestWrapper httpRequestWrapper, HttpClientContext httpClientContext, HttpExecutionAware httpExecutionAware) throws IOException, HttpException {
        HttpHost targetHost = httpClientContext.getTargetHost();
        c(targetHost, httpRequestWrapper);
        if (!a(httpRequestWrapper)) {
            return u.enhanceResponse(new BasicHttpResponse(HttpVersion.HTTP_1_1, (int) HttpStatus.SC_GATEWAY_TIMEOUT, "Gateway Timeout"));
        }
        Map b = b(targetHost, httpRequestWrapper);
        if (b == null || b.isEmpty()) {
            return a(httpRoute, httpRequestWrapper, httpClientContext, httpExecutionAware);
        }
        return a(httpRoute, httpRequestWrapper, httpClientContext, httpExecutionAware, b);
    }

    private HttpCacheEntry a(HttpHost httpHost, HttpRequestWrapper httpRequestWrapper) {
        HttpCacheEntry httpCacheEntry = null;
        try {
            httpCacheEntry = this.g.getCacheEntry(httpHost, httpRequestWrapper);
        } catch (Throwable e) {
            this.log.warn("Unable to retrieve entries from cache", e);
        }
        return httpCacheEntry;
    }

    private HttpResponse a(HttpRequestWrapper httpRequestWrapper, HttpContext httpContext) {
        HttpResponse httpResponse = null;
        for (x xVar : this.n.requestIsFatallyNonCompliant(httpRequestWrapper)) {
            a(httpContext, CacheResponseStatus.CACHE_MODULE_RESPONSE);
            httpResponse = this.n.getErrorForRequest(xVar);
        }
        return httpResponse;
    }

    private Map<String, ae> b(HttpHost httpHost, HttpRequestWrapper httpRequestWrapper) {
        Map<String, ae> map = null;
        try {
            map = this.g.getVariantCacheEntriesWithEtags(httpHost, httpRequestWrapper);
        } catch (Throwable e) {
            this.log.warn("Unable to retrieve variant entries from cache", e);
        }
        return map;
    }

    private void c(HttpHost httpHost, HttpRequestWrapper httpRequestWrapper) {
        this.b.getAndIncrement();
        if (this.log.isTraceEnabled()) {
            this.log.trace("Cache miss [host: " + httpHost + "; uri: " + httpRequestWrapper.getRequestLine().getUri() + "]");
        }
    }

    private void d(HttpHost httpHost, HttpRequestWrapper httpRequestWrapper) {
        this.a.getAndIncrement();
        if (this.log.isTraceEnabled()) {
            this.log.trace("Cache hit [host: " + httpHost + "; uri: " + httpRequestWrapper.getRequestLine().getUri() + "]");
        }
    }

    private void a(HttpContext httpContext) {
        this.c.getAndIncrement();
        a(httpContext, CacheResponseStatus.VALIDATED);
    }

    private void e(HttpHost httpHost, HttpRequestWrapper httpRequestWrapper) {
        try {
            this.g.flushInvalidatedCacheEntriesFor(httpHost, httpRequestWrapper);
        } catch (Throwable e) {
            this.log.warn("Unable to flush invalidated entries from cache", e);
        }
    }

    private CloseableHttpResponse a(HttpRequestWrapper httpRequestWrapper, HttpContext httpContext, HttpCacheEntry httpCacheEntry, Date date) {
        CloseableHttpResponse a;
        if (httpRequestWrapper.containsHeader("If-None-Match") || httpRequestWrapper.containsHeader("If-Modified-Since")) {
            a = this.i.a(httpCacheEntry);
        } else {
            a = this.i.a(httpRequestWrapper, httpCacheEntry);
        }
        a(httpContext, CacheResponseStatus.CACHE_HIT);
        if (this.h.getStalenessSecs(httpCacheEntry, date) > 0) {
            a.addHeader("Warning", "110 localhost \"Response is stale\"");
        }
        return a;
    }

    private CloseableHttpResponse b(HttpRequestWrapper httpRequestWrapper, HttpContext httpContext, HttpCacheEntry httpCacheEntry, Date date) {
        if (a(httpRequestWrapper, httpCacheEntry, date)) {
            return b(httpContext);
        }
        return a(httpRequestWrapper, httpContext, httpCacheEntry);
    }

    private CloseableHttpResponse b(HttpContext httpContext) {
        a(httpContext, CacheResponseStatus.CACHE_MODULE_RESPONSE);
        return u.enhanceResponse(new BasicHttpResponse(HttpVersion.HTTP_1_1, (int) HttpStatus.SC_GATEWAY_TIMEOUT, "Gateway Timeout"));
    }

    private CloseableHttpResponse a(HttpRequestWrapper httpRequestWrapper, HttpContext httpContext, HttpCacheEntry httpCacheEntry) {
        CloseableHttpResponse a = this.i.a(httpRequestWrapper, httpCacheEntry);
        a(httpContext, CacheResponseStatus.CACHE_HIT);
        a.addHeader("Warning", "111 localhost \"Revalidation failed\"");
        return a;
    }

    private boolean a(HttpRequestWrapper httpRequestWrapper, HttpCacheEntry httpCacheEntry, Date date) {
        return this.h.mustRevalidate(httpCacheEntry) || ((this.e.isSharedCache() && this.h.proxyRevalidate(httpCacheEntry)) || b(httpRequestWrapper, httpCacheEntry, date));
    }

    private boolean a(HttpRequestWrapper httpRequestWrapper) {
        for (Header elements : httpRequestWrapper.getHeaders("Cache-Control")) {
            for (HeaderElement name : elements.getElements()) {
                if ("only-if-cached".equals(name.getName())) {
                    this.log.trace("Request marked only-if-cached");
                    return false;
                }
            }
        }
        return true;
    }

    private boolean b(HttpRequestWrapper httpRequestWrapper, HttpCacheEntry httpCacheEntry, Date date) {
        for (Header elements : httpRequestWrapper.getHeaders("Cache-Control")) {
            for (HeaderElement headerElement : elements.getElements()) {
                if (HeaderConstants.CACHE_CONTROL_MAX_STALE.equals(headerElement.getName())) {
                    try {
                        if (this.h.getCurrentAgeSecs(httpCacheEntry, date) - this.h.getFreshnessLifetimeSecs(httpCacheEntry) > ((long) Integer.parseInt(headerElement.getValue()))) {
                            return true;
                        }
                    } catch (NumberFormatException e) {
                        return true;
                    }
                } else if (HeaderConstants.CACHE_CONTROL_MIN_FRESH.equals(headerElement.getName()) || "max-age".equals(headerElement.getName())) {
                    return true;
                }
            }
        }
        return false;
    }

    private String a(HttpMessage httpMessage) {
        ProtocolVersion protocolVersion = httpMessage.getProtocolVersion();
        String str = (String) this.d.get(protocolVersion);
        if (str == null) {
            VersionInfo loadVersionInfo = VersionInfo.loadVersionInfo("cz.msebera.android.httpclient.client", getClass().getClassLoader());
            str = loadVersionInfo != null ? loadVersionInfo.getRelease() : VersionInfo.UNAVAILABLE;
            int major = protocolVersion.getMajor();
            int minor = protocolVersion.getMinor();
            if ("http".equalsIgnoreCase(protocolVersion.getProtocol())) {
                str = String.format("%d.%d localhost (Apache-HttpClient/%s (cache))", new Object[]{Integer.valueOf(major), Integer.valueOf(minor), str});
            } else {
                str = String.format("%s/%d.%d localhost (Apache-HttpClient/%s (cache))", new Object[]{protocolVersion.getProtocol(), Integer.valueOf(major), Integer.valueOf(minor), str});
            }
            this.d.put(protocolVersion, str);
        }
        return str;
    }

    private void a(HttpContext httpContext, CacheResponseStatus cacheResponseStatus) {
        if (httpContext != null) {
            httpContext.setAttribute(HttpCacheContext.CACHE_RESPONSE_STATUS, cacheResponseStatus);
        }
    }

    public boolean supportsRangeAndContentRangeHeaders() {
        return false;
    }

    Date a() {
        return new Date();
    }

    boolean a(HttpRequest httpRequest) {
        RequestLine requestLine = httpRequest.getRequestLine();
        if ("OPTIONS".equals(requestLine.getMethod()) && "*".equals(requestLine.getUri()) && "0".equals(httpRequest.getFirstHeader("Max-Forwards").getValue())) {
            return true;
        }
        return false;
    }

    CloseableHttpResponse a(HttpRoute httpRoute, HttpRequestWrapper httpRequestWrapper, HttpClientContext httpClientContext, HttpExecutionAware httpExecutionAware) throws IOException, HttpException {
        Date a = a();
        this.log.trace("Calling the backend");
        CloseableHttpResponse execute = this.f.execute(httpRoute, httpRequestWrapper, httpClientContext, httpExecutionAware);
        try {
            execute.addHeader("Via", a((HttpMessage) execute));
            return a(httpRequestWrapper, httpClientContext, a, a(), execute);
        } catch (IOException e) {
            execute.close();
            throw e;
        } catch (RuntimeException e2) {
            execute.close();
            throw e2;
        }
    }

    private boolean a(HttpResponse httpResponse, HttpCacheEntry httpCacheEntry) {
        Header firstHeader = httpCacheEntry.getFirstHeader("Date");
        Header firstHeader2 = httpResponse.getFirstHeader("Date");
        if (firstHeader == null || firstHeader2 == null) {
            return false;
        }
        Date parseDate = DateUtils.parseDate(firstHeader.getValue());
        Date parseDate2 = DateUtils.parseDate(firstHeader2.getValue());
        if (parseDate == null || parseDate2 == null || !parseDate2.before(parseDate)) {
            return false;
        }
        return true;
    }

    CloseableHttpResponse a(HttpRoute httpRoute, HttpRequestWrapper httpRequestWrapper, HttpClientContext httpClientContext, HttpExecutionAware httpExecutionAware, Map<String, ae> map) throws IOException, HttpException {
        HttpRequestWrapper buildConditionalRequestFromVariants = this.l.buildConditionalRequestFromVariants(httpRequestWrapper, map);
        Date a = a();
        HttpResponse execute = this.f.execute(httpRoute, buildConditionalRequestFromVariants, httpClientContext, httpExecutionAware);
        try {
            Date a2 = a();
            execute.addHeader("Via", a((HttpMessage) execute));
            if (execute.getStatusLine().getStatusCode() != 304) {
                return a(httpRequestWrapper, httpClientContext, a, a2, (CloseableHttpResponse) execute);
            }
            Header firstHeader = execute.getFirstHeader("ETag");
            if (firstHeader == null) {
                this.log.warn("304 response did not contain ETag");
                s.a(execute.getEntity());
                execute.close();
                return a(httpRoute, httpRequestWrapper, httpClientContext, httpExecutionAware);
            }
            ae aeVar = (ae) map.get(firstHeader.getValue());
            if (aeVar == null) {
                this.log.debug("304 response did not contain ETag matching one sent in If-None-Match");
                s.a(execute.getEntity());
                execute.close();
                return a(httpRoute, httpRequestWrapper, httpClientContext, httpExecutionAware);
            }
            HttpCacheEntry entry = aeVar.getEntry();
            if (a(execute, entry)) {
                s.a(execute.getEntity());
                execute.close();
                return c(httpRoute, httpRequestWrapper, httpClientContext, httpExecutionAware, entry);
            }
            a((HttpContext) httpClientContext);
            HttpCacheEntry a3 = a(httpClientContext.getTargetHost(), buildConditionalRequestFromVariants, a, a2, execute, aeVar, entry);
            execute.close();
            CloseableHttpResponse a4 = this.i.a(httpRequestWrapper, a3);
            a(httpClientContext.getTargetHost(), httpRequestWrapper, aeVar);
            if (a(httpRequestWrapper, a3)) {
                return this.i.a(a3);
            }
            return a4;
        } catch (IOException e) {
            execute.close();
            throw e;
        } catch (RuntimeException e2) {
            execute.close();
            throw e2;
        }
    }

    private CloseableHttpResponse c(HttpRoute httpRoute, HttpRequestWrapper httpRequestWrapper, HttpClientContext httpClientContext, HttpExecutionAware httpExecutionAware, HttpCacheEntry httpCacheEntry) throws IOException, HttpException {
        return a(httpRoute, this.l.buildUnconditionalRequest(httpRequestWrapper, httpCacheEntry), httpClientContext, httpExecutionAware);
    }

    private HttpCacheEntry a(HttpHost httpHost, HttpRequestWrapper httpRequestWrapper, Date date, Date date2, CloseableHttpResponse closeableHttpResponse, ae aeVar, HttpCacheEntry httpCacheEntry) throws IOException {
        try {
            httpCacheEntry = this.g.updateVariantCacheEntry(httpHost, httpRequestWrapper, httpCacheEntry, closeableHttpResponse, date, date2, aeVar.getCacheKey());
        } catch (Throwable e) {
            this.log.warn("Could not update cache entry", e);
        } finally {
            closeableHttpResponse.close();
        }
        return httpCacheEntry;
    }

    private void a(HttpHost httpHost, HttpRequestWrapper httpRequestWrapper, ae aeVar) {
        try {
            this.g.reuseVariantEntryFor(httpHost, httpRequestWrapper, aeVar);
        } catch (Throwable e) {
            this.log.warn("Could not update cache entry to reuse variant", e);
        }
    }

    private boolean a(HttpRequestWrapper httpRequestWrapper, HttpCacheEntry httpCacheEntry) {
        return this.k.isConditional(httpRequestWrapper) && this.k.allConditionalsMatch(httpRequestWrapper, httpCacheEntry, new Date());
    }

    CloseableHttpResponse a(HttpRoute httpRoute, HttpRequestWrapper httpRequestWrapper, HttpClientContext httpClientContext, HttpExecutionAware httpExecutionAware, HttpCacheEntry httpCacheEntry) throws IOException, HttpException {
        HttpRequestWrapper buildConditionalRequest = this.l.buildConditionalRequest(httpRequestWrapper, httpCacheEntry);
        URI uri = buildConditionalRequest.getURI();
        if (uri != null) {
            try {
                buildConditionalRequest.setURI(URIUtils.rewriteURIForRoute(uri, httpRoute));
            } catch (Throwable e) {
                throw new ProtocolException("Invalid URI: " + uri, e);
            }
        }
        Date a = a();
        HttpMessage execute = this.f.execute(httpRoute, buildConditionalRequest, httpClientContext, httpExecutionAware);
        Date a2 = a();
        if (a((HttpResponse) execute, httpCacheEntry)) {
            execute.close();
            HttpRequestWrapper buildUnconditionalRequest = this.l.buildUnconditionalRequest(httpRequestWrapper, httpCacheEntry);
            a = a();
            execute = this.f.execute(httpRoute, buildUnconditionalRequest, httpClientContext, httpExecutionAware);
            a2 = a();
        }
        execute.addHeader("Via", a(execute));
        int statusCode = execute.getStatusLine().getStatusCode();
        if (statusCode == 304 || statusCode == 200) {
            a((HttpContext) httpClientContext);
        }
        if (statusCode == 304) {
            HttpCacheEntry updateCacheEntry = this.g.updateCacheEntry(httpClientContext.getTargetHost(), httpRequestWrapper, httpCacheEntry, execute, a, a2);
            if (this.k.isConditional(httpRequestWrapper)) {
                if (this.k.allConditionalsMatch(httpRequestWrapper, updateCacheEntry, new Date())) {
                    return this.i.a(updateCacheEntry);
                }
            }
            return this.i.a(httpRequestWrapper, updateCacheEntry);
        }
        if (a(statusCode)) {
            if (!a(httpRequestWrapper, httpCacheEntry, a()) && this.h.mayReturnStaleIfError(httpRequestWrapper, httpCacheEntry, a2)) {
                try {
                    CloseableHttpResponse a3 = this.i.a(httpRequestWrapper, httpCacheEntry);
                    a3.addHeader("Warning", "110 localhost \"Response is stale\"");
                    return a3;
                } finally {
                    execute.close();
                }
            }
        }
        return a(buildConditionalRequest, httpClientContext, a, a2, (CloseableHttpResponse) execute);
    }

    private boolean a(int i) {
        return i == 500 || i == 502 || i == 503 || i == HttpStatus.SC_GATEWAY_TIMEOUT;
    }

    CloseableHttpResponse a(HttpRequestWrapper httpRequestWrapper, HttpClientContext httpClientContext, Date date, Date date2, CloseableHttpResponse closeableHttpResponse) throws IOException {
        this.log.trace("Handling Backend response");
        this.m.ensureProtocolCompliance(httpRequestWrapper, closeableHttpResponse);
        HttpHost targetHost = httpClientContext.getTargetHost();
        boolean isResponseCacheable = this.o.isResponseCacheable((HttpRequest) httpRequestWrapper, (HttpResponse) closeableHttpResponse);
        this.g.flushInvalidatedCacheEntriesFor(targetHost, httpRequestWrapper, closeableHttpResponse);
        if (isResponseCacheable && !a(targetHost, httpRequestWrapper, (HttpResponse) closeableHttpResponse)) {
            a((HttpRequest) httpRequestWrapper, (HttpResponse) closeableHttpResponse);
            return this.g.cacheAndReturnResponse(targetHost, (HttpRequest) httpRequestWrapper, closeableHttpResponse, date, date2);
        } else if (isResponseCacheable) {
            return closeableHttpResponse;
        } else {
            try {
                this.g.flushCacheEntriesFor(targetHost, httpRequestWrapper);
                return closeableHttpResponse;
            } catch (Throwable e) {
                this.log.warn("Unable to flush invalid cache entries", e);
                return closeableHttpResponse;
            }
        }
    }

    private void a(HttpRequest httpRequest, HttpResponse httpResponse) {
        if (httpResponse.getStatusLine().getStatusCode() == 304) {
            Header firstHeader = httpRequest.getFirstHeader("If-Modified-Since");
            if (firstHeader != null) {
                httpResponse.addHeader("Last-Modified", firstHeader.getValue());
            }
        }
    }

    private boolean a(HttpHost httpHost, HttpRequestWrapper httpRequestWrapper, HttpResponse httpResponse) {
        HttpCacheEntry httpCacheEntry = null;
        try {
            httpCacheEntry = this.g.getCacheEntry(httpHost, httpRequestWrapper);
        } catch (IOException e) {
        }
        if (httpCacheEntry == null) {
            return false;
        }
        Header firstHeader = httpCacheEntry.getFirstHeader("Date");
        if (firstHeader == null) {
            return false;
        }
        Header firstHeader2 = httpResponse.getFirstHeader("Date");
        if (firstHeader2 == null) {
            return false;
        }
        Date parseDate = DateUtils.parseDate(firstHeader.getValue());
        Date parseDate2 = DateUtils.parseDate(firstHeader2.getValue());
        if (parseDate == null || parseDate2 == null) {
            return false;
        }
        return parseDate2.before(parseDate);
    }
}
