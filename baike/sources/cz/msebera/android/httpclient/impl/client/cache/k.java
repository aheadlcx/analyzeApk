package cz.msebera.android.httpclient.impl.client.cache;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.HeaderElement;
import cz.msebera.android.httpclient.HttpRequest;
import cz.msebera.android.httpclient.annotation.Immutable;
import cz.msebera.android.httpclient.client.cache.HeaderConstants;
import cz.msebera.android.httpclient.client.cache.HttpCacheEntry;
import cz.msebera.android.httpclient.client.utils.DateUtils;
import java.util.Date;

@Immutable
class k {
    public static final long MAX_AGE = 2147483648L;

    k() {
    }

    public long getCurrentAgeSecs(HttpCacheEntry httpCacheEntry, Date date) {
        return i(httpCacheEntry) + a(httpCacheEntry, date);
    }

    public long getFreshnessLifetimeSecs(HttpCacheEntry httpCacheEntry) {
        long j = j(httpCacheEntry);
        if (j > -1) {
            return j;
        }
        Date date = httpCacheEntry.getDate();
        if (date == null) {
            return 0;
        }
        Date k = k(httpCacheEntry);
        if (k == null) {
            return 0;
        }
        return (k.getTime() - date.getTime()) / 1000;
    }

    public boolean isResponseFresh(HttpCacheEntry httpCacheEntry, Date date) {
        return getCurrentAgeSecs(httpCacheEntry, date) < getFreshnessLifetimeSecs(httpCacheEntry);
    }

    public boolean isResponseHeuristicallyFresh(HttpCacheEntry httpCacheEntry, Date date, float f, long j) {
        return getCurrentAgeSecs(httpCacheEntry, date) < getHeuristicFreshnessLifetimeSecs(httpCacheEntry, f, j);
    }

    public long getHeuristicFreshnessLifetimeSecs(HttpCacheEntry httpCacheEntry, float f, long j) {
        Date date = httpCacheEntry.getDate();
        Date a = a(httpCacheEntry);
        if (date == null || a == null) {
            return j;
        }
        long time = date.getTime() - a.getTime();
        if (time < 0) {
            return 0;
        }
        return (long) (((float) (time / 1000)) * f);
    }

    public boolean isRevalidatable(HttpCacheEntry httpCacheEntry) {
        return (httpCacheEntry.getFirstHeader("ETag") == null && httpCacheEntry.getFirstHeader("Last-Modified") == null) ? false : true;
    }

    public boolean mustRevalidate(HttpCacheEntry httpCacheEntry) {
        return hasCacheControlDirective(httpCacheEntry, HeaderConstants.CACHE_CONTROL_MUST_REVALIDATE);
    }

    public boolean proxyRevalidate(HttpCacheEntry httpCacheEntry) {
        return hasCacheControlDirective(httpCacheEntry, HeaderConstants.CACHE_CONTROL_PROXY_REVALIDATE);
    }

    public boolean mayReturnStaleWhileRevalidating(HttpCacheEntry httpCacheEntry, Date date) {
        for (Header elements : httpCacheEntry.getHeaders("Cache-Control")) {
            for (HeaderElement headerElement : elements.getElements()) {
                if (HeaderConstants.STALE_WHILE_REVALIDATE.equalsIgnoreCase(headerElement.getName())) {
                    try {
                        if (getStalenessSecs(httpCacheEntry, date) <= ((long) Integer.parseInt(headerElement.getValue()))) {
                            return true;
                        }
                    } catch (NumberFormatException e) {
                    }
                }
            }
        }
        return false;
    }

    public boolean mayReturnStaleIfError(HttpRequest httpRequest, HttpCacheEntry httpCacheEntry, Date date) {
        long stalenessSecs = getStalenessSecs(httpCacheEntry, date);
        return a(httpRequest.getHeaders("Cache-Control"), stalenessSecs) || a(httpCacheEntry.getHeaders("Cache-Control"), stalenessSecs);
    }

    private boolean a(Header[] headerArr, long j) {
        boolean z = false;
        for (Header elements : headerArr) {
            for (HeaderElement headerElement : elements.getElements()) {
                if (HeaderConstants.STALE_IF_ERROR.equals(headerElement.getName())) {
                    try {
                        if (j <= ((long) Integer.parseInt(headerElement.getValue()))) {
                            z = true;
                            break;
                        }
                    } catch (NumberFormatException e) {
                    }
                }
            }
        }
        return z;
    }

    protected Date a(HttpCacheEntry httpCacheEntry) {
        Header firstHeader = httpCacheEntry.getFirstHeader("Last-Modified");
        if (firstHeader == null) {
            return null;
        }
        return DateUtils.parseDate(firstHeader.getValue());
    }

    protected long b(HttpCacheEntry httpCacheEntry) {
        long j = -1;
        Header firstHeader = httpCacheEntry.getFirstHeader("Content-Length");
        if (firstHeader != null) {
            try {
                j = Long.parseLong(firstHeader.getValue());
            } catch (NumberFormatException e) {
            }
        }
        return j;
    }

    protected boolean c(HttpCacheEntry httpCacheEntry) {
        return httpCacheEntry.getFirstHeader("Content-Length") != null;
    }

    protected boolean d(HttpCacheEntry httpCacheEntry) {
        return !c(httpCacheEntry) || (httpCacheEntry.getResource() != null && b(httpCacheEntry) == httpCacheEntry.getResource().length());
    }

    protected long e(HttpCacheEntry httpCacheEntry) {
        Date date = httpCacheEntry.getDate();
        if (date == null) {
            return MAX_AGE;
        }
        long time = httpCacheEntry.getResponseDate().getTime() - date.getTime();
        if (time >= 0) {
            return time / 1000;
        }
        return 0;
    }

    protected long f(HttpCacheEntry httpCacheEntry) {
        Header[] headers = httpCacheEntry.getHeaders("Age");
        int length = headers.length;
        int i = 0;
        long j = 0;
        while (i < length) {
            long parseLong;
            try {
                parseLong = Long.parseLong(headers[i].getValue());
                if (parseLong < 0) {
                    parseLong = MAX_AGE;
                }
            } catch (NumberFormatException e) {
                parseLong = MAX_AGE;
            }
            if (parseLong <= j) {
                parseLong = j;
            }
            i++;
            j = parseLong;
        }
        return j;
    }

    protected long g(HttpCacheEntry httpCacheEntry) {
        long e = e(httpCacheEntry);
        long f = f(httpCacheEntry);
        return e > f ? e : f;
    }

    protected long h(HttpCacheEntry httpCacheEntry) {
        return (httpCacheEntry.getResponseDate().getTime() - httpCacheEntry.getRequestDate().getTime()) / 1000;
    }

    protected long i(HttpCacheEntry httpCacheEntry) {
        return g(httpCacheEntry) + h(httpCacheEntry);
    }

    protected long a(HttpCacheEntry httpCacheEntry, Date date) {
        return (date.getTime() - httpCacheEntry.getResponseDate().getTime()) / 1000;
    }

    protected long j(HttpCacheEntry httpCacheEntry) {
        long j = -1;
        for (Header elements : httpCacheEntry.getHeaders("Cache-Control")) {
            for (HeaderElement headerElement : elements.getElements()) {
                if ("max-age".equals(headerElement.getName()) || "s-maxage".equals(headerElement.getName())) {
                    try {
                        long parseLong = Long.parseLong(headerElement.getValue());
                        if (j == -1 || parseLong < j) {
                            j = parseLong;
                        }
                    } catch (NumberFormatException e) {
                        j = 0;
                    }
                }
            }
        }
        return j;
    }

    protected Date k(HttpCacheEntry httpCacheEntry) {
        Header firstHeader = httpCacheEntry.getFirstHeader("Expires");
        if (firstHeader == null) {
            return null;
        }
        return DateUtils.parseDate(firstHeader.getValue());
    }

    public boolean hasCacheControlDirective(HttpCacheEntry httpCacheEntry, String str) {
        for (Header elements : httpCacheEntry.getHeaders("Cache-Control")) {
            for (HeaderElement name : elements.getElements()) {
                if (str.equalsIgnoreCase(name.getName())) {
                    return true;
                }
            }
        }
        return false;
    }

    public long getStalenessSecs(HttpCacheEntry httpCacheEntry, Date date) {
        long currentAgeSecs = getCurrentAgeSecs(httpCacheEntry, date);
        long freshnessLifetimeSecs = getFreshnessLifetimeSecs(httpCacheEntry);
        if (currentAgeSecs <= freshnessLifetimeSecs) {
            return 0;
        }
        return currentAgeSecs - freshnessLifetimeSecs;
    }
}
