package cz.msebera.android.httpclient.impl.client.cache;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.HttpEntity;
import cz.msebera.android.httpclient.HttpHeaders;
import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.HttpVersion;
import cz.msebera.android.httpclient.annotation.Immutable;
import cz.msebera.android.httpclient.client.cache.HttpCacheEntry;
import cz.msebera.android.httpclient.client.methods.CloseableHttpResponse;
import cz.msebera.android.httpclient.client.methods.HttpRequestWrapper;
import cz.msebera.android.httpclient.client.utils.DateUtils;
import cz.msebera.android.httpclient.message.BasicHeader;
import cz.msebera.android.httpclient.message.BasicHttpResponse;
import java.util.Date;

@Immutable
class m {
    private final k a;

    m(k kVar) {
        this.a = kVar;
    }

    m() {
        this(new k());
    }

    CloseableHttpResponse a(HttpRequestWrapper httpRequestWrapper, HttpCacheEntry httpCacheEntry) {
        Date date = new Date();
        HttpResponse basicHttpResponse = new BasicHttpResponse(HttpVersion.HTTP_1_1, httpCacheEntry.getStatusCode(), httpCacheEntry.getReasonPhrase());
        basicHttpResponse.setHeaders(httpCacheEntry.getAllHeaders());
        if (b(httpRequestWrapper, httpCacheEntry)) {
            HttpEntity fVar = new f(httpCacheEntry);
            a(basicHttpResponse, fVar);
            basicHttpResponse.setEntity(fVar);
        }
        long currentAgeSecs = this.a.getCurrentAgeSecs(httpCacheEntry, date);
        if (currentAgeSecs > 0) {
            if (currentAgeSecs >= 2147483647L) {
                basicHttpResponse.setHeader("Age", "2147483648");
            } else {
                basicHttpResponse.setHeader("Age", "" + ((int) currentAgeSecs));
            }
        }
        return u.enhanceResponse(basicHttpResponse);
    }

    CloseableHttpResponse a(HttpCacheEntry httpCacheEntry) {
        HttpResponse basicHttpResponse = new BasicHttpResponse(HttpVersion.HTTP_1_1, 304, "Not Modified");
        Header firstHeader = httpCacheEntry.getFirstHeader("Date");
        if (firstHeader == null) {
            firstHeader = new BasicHeader("Date", DateUtils.formatDate(new Date()));
        }
        basicHttpResponse.addHeader(firstHeader);
        firstHeader = httpCacheEntry.getFirstHeader("ETag");
        if (firstHeader != null) {
            basicHttpResponse.addHeader(firstHeader);
        }
        firstHeader = httpCacheEntry.getFirstHeader(HttpHeaders.CONTENT_LOCATION);
        if (firstHeader != null) {
            basicHttpResponse.addHeader(firstHeader);
        }
        firstHeader = httpCacheEntry.getFirstHeader("Expires");
        if (firstHeader != null) {
            basicHttpResponse.addHeader(firstHeader);
        }
        firstHeader = httpCacheEntry.getFirstHeader("Cache-Control");
        if (firstHeader != null) {
            basicHttpResponse.addHeader(firstHeader);
        }
        firstHeader = httpCacheEntry.getFirstHeader("Vary");
        if (firstHeader != null) {
            basicHttpResponse.addHeader(firstHeader);
        }
        return u.enhanceResponse(basicHttpResponse);
    }

    private void a(HttpResponse httpResponse, HttpEntity httpEntity) {
        if (!a(httpResponse) && httpResponse.getFirstHeader("Content-Length") == null) {
            httpResponse.setHeader(new BasicHeader("Content-Length", Long.toString(httpEntity.getContentLength())));
        }
    }

    private boolean a(HttpResponse httpResponse) {
        return httpResponse.getFirstHeader("Transfer-Encoding") != null;
    }

    private boolean b(HttpRequestWrapper httpRequestWrapper, HttpCacheEntry httpCacheEntry) {
        return httpRequestWrapper.getRequestLine().getMethod().equals("GET") && httpCacheEntry.getResource() != null;
    }
}
