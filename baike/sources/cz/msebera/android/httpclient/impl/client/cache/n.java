package cz.msebera.android.httpclient.impl.client.cache;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.HeaderElement;
import cz.msebera.android.httpclient.HttpHost;
import cz.msebera.android.httpclient.HttpRequest;
import cz.msebera.android.httpclient.HttpStatus;
import cz.msebera.android.httpclient.annotation.Immutable;
import cz.msebera.android.httpclient.client.cache.HeaderConstants;
import cz.msebera.android.httpclient.client.cache.HttpCacheEntry;
import cz.msebera.android.httpclient.client.utils.DateUtils;
import cz.msebera.android.httpclient.extras.HttpClientAndroidLog;
import java.util.Date;

@Immutable
class n {
    private final boolean a;
    private final boolean b;
    private final float c;
    private final long d;
    private final k e;
    public HttpClientAndroidLog log = new HttpClientAndroidLog(getClass());

    n(k kVar, CacheConfig cacheConfig) {
        this.e = kVar;
        this.a = cacheConfig.isSharedCache();
        this.b = cacheConfig.isHeuristicCachingEnabled();
        this.c = cacheConfig.getHeuristicCoefficient();
        this.d = cacheConfig.getHeuristicDefaultLifetime();
    }

    private boolean a(HttpCacheEntry httpCacheEntry, HttpRequest httpRequest, Date date) {
        if (this.e.isResponseFresh(httpCacheEntry, date)) {
            return true;
        }
        if (this.b) {
            if (this.e.isResponseHeuristicallyFresh(httpCacheEntry, date, this.c, this.d)) {
                return true;
            }
        }
        if (a(httpCacheEntry)) {
            return false;
        }
        long a = a(httpRequest);
        if (a == -1) {
            return false;
        }
        return a > this.e.getStalenessSecs(httpCacheEntry, date);
    }

    private boolean a(HttpCacheEntry httpCacheEntry) {
        if (this.e.mustRevalidate(httpCacheEntry)) {
            return true;
        }
        if (!this.a) {
            return false;
        }
        if (this.e.proxyRevalidate(httpCacheEntry) || this.e.hasCacheControlDirective(httpCacheEntry, "s-maxage")) {
            return true;
        }
        return false;
    }

    private long a(HttpRequest httpRequest) {
        long j = -1;
        Header[] headers = httpRequest.getHeaders("Cache-Control");
        int length = headers.length;
        int i = 0;
        while (i < length) {
            long j2 = j;
            for (HeaderElement headerElement : headers[i].getElements()) {
                if (HeaderConstants.CACHE_CONTROL_MAX_STALE.equals(headerElement.getName())) {
                    if ((headerElement.getValue() == null || "".equals(headerElement.getValue().trim())) && j2 == -1) {
                        j2 = Long.MAX_VALUE;
                    } else {
                        try {
                            j = Long.parseLong(headerElement.getValue());
                            if (j < 0) {
                                j = 0;
                            }
                            if (j2 == -1 || j < j2) {
                                j2 = j;
                            }
                        } catch (NumberFormatException e) {
                            j2 = 0;
                        }
                    }
                }
            }
            i++;
            j = j2;
        }
        return j;
    }

    public boolean canCachedResponseBeUsed(HttpHost httpHost, HttpRequest httpRequest, HttpCacheEntry httpCacheEntry, Date date) {
        if (!a(httpCacheEntry, httpRequest, date)) {
            this.log.trace("Cache entry was not fresh enough");
            return false;
        } else if (b(httpRequest) && !this.e.d(httpCacheEntry)) {
            this.log.debug("Cache entry Content-Length and header information do not match");
            return false;
        } else if (c(httpRequest)) {
            this.log.debug("Request contained conditional headers we don't handle");
            return false;
        } else if (!isConditional(httpRequest) && httpCacheEntry.getStatusCode() == 304) {
            return false;
        } else {
            if (isConditional(httpRequest) && !allConditionalsMatch(httpRequest, httpCacheEntry, date)) {
                return false;
            }
            if (a(httpRequest, httpCacheEntry)) {
                this.log.debug("HEAD response caching enabled but the cache entry does not contain a request method, entity or a 204 response");
                return false;
            }
            for (Header elements : httpRequest.getHeaders("Cache-Control")) {
                HeaderElement[] elements2 = elements.getElements();
                int length = elements2.length;
                int i = 0;
                while (i < length) {
                    HeaderElement headerElement = elements2[i];
                    if (HeaderConstants.CACHE_CONTROL_NO_CACHE.equals(headerElement.getName())) {
                        this.log.trace("Response contained NO CACHE directive, cache was not suitable");
                        return false;
                    } else if (HeaderConstants.CACHE_CONTROL_NO_STORE.equals(headerElement.getName())) {
                        this.log.trace("Response contained NO STORE directive, cache was not suitable");
                        return false;
                    } else {
                        if ("max-age".equals(headerElement.getName())) {
                            try {
                                if (this.e.getCurrentAgeSecs(httpCacheEntry, date) > ((long) Integer.parseInt(headerElement.getValue()))) {
                                    this.log.trace("Response from cache was NOT suitable due to max age");
                                    return false;
                                }
                            } catch (NumberFormatException e) {
                                this.log.debug("Response from cache was malformed" + e.getMessage());
                                return false;
                            }
                        }
                        if (HeaderConstants.CACHE_CONTROL_MAX_STALE.equals(headerElement.getName())) {
                            try {
                                if (this.e.getFreshnessLifetimeSecs(httpCacheEntry) > ((long) Integer.parseInt(headerElement.getValue()))) {
                                    this.log.trace("Response from cache was not suitable due to Max stale freshness");
                                    return false;
                                }
                            } catch (NumberFormatException e2) {
                                this.log.debug("Response from cache was malformed: " + e2.getMessage());
                                return false;
                            }
                        }
                        if (HeaderConstants.CACHE_CONTROL_MIN_FRESH.equals(headerElement.getName())) {
                            try {
                                long parseLong = Long.parseLong(headerElement.getValue());
                                if (parseLong < 0) {
                                    return false;
                                }
                                if (this.e.getFreshnessLifetimeSecs(httpCacheEntry) - this.e.getCurrentAgeSecs(httpCacheEntry, date) < parseLong) {
                                    this.log.trace("Response from cache was not suitable due to min fresh freshness requirement");
                                    return false;
                                }
                            } catch (NumberFormatException e22) {
                                this.log.debug("Response from cache was malformed: " + e22.getMessage());
                                return false;
                            }
                        }
                        i++;
                    }
                }
            }
            this.log.trace("Response from cache was suitable");
            return true;
        }
    }

    private boolean b(HttpRequest httpRequest) {
        return httpRequest.getRequestLine().getMethod().equals("GET");
    }

    private boolean b(HttpCacheEntry httpCacheEntry) {
        return httpCacheEntry.getStatusCode() != HttpStatus.SC_NO_CONTENT;
    }

    private boolean c(HttpCacheEntry httpCacheEntry) {
        return httpCacheEntry.getRequestMethod() == null && httpCacheEntry.getResource() == null;
    }

    private boolean a(HttpRequest httpRequest, HttpCacheEntry httpCacheEntry) {
        return b(httpRequest) && c(httpCacheEntry) && b(httpCacheEntry);
    }

    public boolean isConditional(HttpRequest httpRequest) {
        return d(httpRequest) || e(httpRequest);
    }

    public boolean allConditionalsMatch(HttpRequest httpRequest, HttpCacheEntry httpCacheEntry, Date date) {
        boolean d = d(httpRequest);
        boolean e = e(httpRequest);
        boolean z = d && b(httpRequest, httpCacheEntry);
        boolean z2;
        if (e && a(httpRequest, httpCacheEntry, date)) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (d && e && (!z || !r2)) {
            return false;
        }
        if (d && !z) {
            return false;
        }
        if (!e || r2) {
            return true;
        }
        return false;
    }

    private boolean c(HttpRequest httpRequest) {
        return (httpRequest.getFirstHeader("If-Range") == null && httpRequest.getFirstHeader("If-Match") == null && !a(httpRequest, "If-Unmodified-Since")) ? false : true;
    }

    private boolean d(HttpRequest httpRequest) {
        return httpRequest.containsHeader("If-None-Match");
    }

    private boolean e(HttpRequest httpRequest) {
        return a(httpRequest, "If-Modified-Since");
    }

    private boolean b(HttpRequest httpRequest, HttpCacheEntry httpCacheEntry) {
        Header firstHeader = httpCacheEntry.getFirstHeader("ETag");
        Object value = firstHeader != null ? firstHeader.getValue() : null;
        Header[] headers = httpRequest.getHeaders("If-None-Match");
        if (headers != null) {
            for (Header elements : headers) {
                for (Object obj : elements.getElements()) {
                    String obj2 = obj.toString();
                    if (("*".equals(obj2) && value != null) || obj2.equals(value)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean a(HttpRequest httpRequest, HttpCacheEntry httpCacheEntry, Date date) {
        Date parseDate;
        Header firstHeader = httpCacheEntry.getFirstHeader("Last-Modified");
        if (firstHeader != null) {
            parseDate = DateUtils.parseDate(firstHeader.getValue());
        } else {
            parseDate = null;
        }
        if (parseDate == null) {
            return false;
        }
        for (Header value : httpRequest.getHeaders("If-Modified-Since")) {
            Date parseDate2 = DateUtils.parseDate(value.getValue());
            if (parseDate2 != null && (parseDate2.after(date) || parseDate.after(parseDate2))) {
                return false;
            }
        }
        return true;
    }

    private boolean a(HttpRequest httpRequest, String str) {
        Header[] headers = httpRequest.getHeaders(str);
        if (0 >= headers.length || DateUtils.parseDate(headers[0].getValue()) == null) {
            return false;
        }
        return true;
    }
}
