package cz.msebera.android.httpclient.impl.client.cache;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.HttpHeaders;
import cz.msebera.android.httpclient.HttpHost;
import cz.msebera.android.httpclient.HttpRequest;
import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.annotation.Immutable;
import cz.msebera.android.httpclient.client.cache.HttpCacheEntry;
import cz.msebera.android.httpclient.client.cache.HttpCacheInvalidator;
import cz.msebera.android.httpclient.client.cache.HttpCacheStorage;
import cz.msebera.android.httpclient.client.utils.DateUtils;
import cz.msebera.android.httpclient.extras.HttpClientAndroidLog;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;

@Immutable
class h implements HttpCacheInvalidator {
    private final HttpCacheStorage a;
    private final i b;
    public HttpClientAndroidLog log = new HttpClientAndroidLog(getClass());

    public h(i iVar, HttpCacheStorage httpCacheStorage) {
        this.b = iVar;
        this.a = httpCacheStorage;
    }

    public void flushInvalidatedCacheEntries(HttpHost httpHost, HttpRequest httpRequest) {
        String uri = this.b.getURI(httpHost, httpRequest);
        HttpCacheEntry b = b(uri);
        if (a(httpRequest) || a(httpRequest, b)) {
            this.log.debug("Invalidating parent cache entry: " + b);
            if (b != null) {
                for (String a : b.getVariantMap().values()) {
                    a(a);
                }
                a(uri);
            }
            URL c = c(uri);
            if (c == null) {
                this.log.error("Couldn't transform request into valid URL");
                return;
            }
            Header firstHeader = httpRequest.getFirstHeader(HttpHeaders.CONTENT_LOCATION);
            if (firstHeader != null) {
                uri = firstHeader.getValue();
                if (!b(c, uri)) {
                    a(c, uri);
                }
            }
            firstHeader = httpRequest.getFirstHeader(HttpHeaders.LOCATION);
            if (firstHeader != null) {
                b(c, firstHeader.getValue());
            }
        }
    }

    private boolean a(HttpRequest httpRequest, HttpCacheEntry httpCacheEntry) {
        return b(httpRequest) && a(httpCacheEntry);
    }

    private boolean b(HttpRequest httpRequest) {
        return httpRequest.getRequestLine().getMethod().equals("GET");
    }

    private boolean a(HttpCacheEntry httpCacheEntry) {
        return httpCacheEntry != null && httpCacheEntry.getRequestMethod().equals("HEAD");
    }

    private void a(String str) {
        try {
            this.a.removeEntry(str);
        } catch (Throwable e) {
            this.log.warn("unable to flush cache entry", e);
        }
    }

    private HttpCacheEntry b(String str) {
        try {
            return this.a.getEntry(str);
        } catch (Throwable e) {
            this.log.warn("could not retrieve entry from storage", e);
            return null;
        }
    }

    protected void a(URL url, URL url2) {
        URL c = c(this.b.canonicalizeUri(url2.toString()));
        if (c != null && c.getAuthority().equalsIgnoreCase(url.getAuthority())) {
            a(c.toString());
        }
    }

    protected void a(URL url, String str) {
        URL c = c(url, str);
        if (c != null) {
            a(url, c);
        }
    }

    protected boolean b(URL url, String str) {
        URL c = c(str);
        if (c == null) {
            return false;
        }
        a(url, c);
        return true;
    }

    private URL c(String str) {
        try {
            return new URL(str);
        } catch (MalformedURLException e) {
            return null;
        }
    }

    private URL c(URL url, String str) {
        try {
            return new URL(url, str);
        } catch (MalformedURLException e) {
            return null;
        }
    }

    protected boolean a(HttpRequest httpRequest) {
        return d(httpRequest.getRequestLine().getMethod());
    }

    private boolean d(String str) {
        return ("GET".equals(str) || "HEAD".equals(str)) ? false : true;
    }

    public void flushInvalidatedCacheEntries(HttpHost httpHost, HttpRequest httpRequest, HttpResponse httpResponse) {
        int statusCode = httpResponse.getStatusLine().getStatusCode();
        if (statusCode >= 200 && statusCode <= 299) {
            URL c = c(this.b.getURI(httpHost, httpRequest));
            if (c != null) {
                URL a = a(c, httpResponse);
                if (a != null) {
                    a(c, httpResponse, a);
                }
                a = b(c, httpResponse);
                if (a != null) {
                    a(c, httpResponse, a);
                }
            }
        }
    }

    private void a(URL url, HttpResponse httpResponse, URL url2) {
        HttpCacheEntry b = b(this.b.canonicalizeUri(url2.toString()));
        if (b != null && !b(httpResponse, b) && a(httpResponse, b)) {
            a(url, url2);
        }
    }

    private URL a(URL url, HttpResponse httpResponse) {
        Header firstHeader = httpResponse.getFirstHeader(HttpHeaders.CONTENT_LOCATION);
        if (firstHeader == null) {
            return null;
        }
        String value = firstHeader.getValue();
        URL c = c(value);
        return c == null ? c(url, value) : c;
    }

    private URL b(URL url, HttpResponse httpResponse) {
        Header firstHeader = httpResponse.getFirstHeader(HttpHeaders.LOCATION);
        if (firstHeader == null) {
            return null;
        }
        String value = firstHeader.getValue();
        URL c = c(value);
        return c == null ? c(url, value) : c;
    }

    private boolean a(HttpResponse httpResponse, HttpCacheEntry httpCacheEntry) {
        Header firstHeader = httpCacheEntry.getFirstHeader("ETag");
        Header firstHeader2 = httpResponse.getFirstHeader("ETag");
        if (firstHeader == null || firstHeader2 == null || firstHeader.getValue().equals(firstHeader2.getValue())) {
            return false;
        }
        return true;
    }

    private boolean b(HttpResponse httpResponse, HttpCacheEntry httpCacheEntry) {
        Header firstHeader = httpCacheEntry.getFirstHeader("Date");
        Header firstHeader2 = httpResponse.getFirstHeader("Date");
        if (firstHeader == null || firstHeader2 == null) {
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
