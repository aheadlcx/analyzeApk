package cz.msebera.android.httpclient.impl.client.cache;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.client.cache.HttpCacheEntry;
import cz.msebera.android.httpclient.client.methods.HttpExecutionAware;
import cz.msebera.android.httpclient.client.methods.HttpRequestWrapper;
import cz.msebera.android.httpclient.client.protocol.HttpClientContext;
import cz.msebera.android.httpclient.conn.routing.HttpRoute;
import cz.msebera.android.httpclient.extras.HttpClientAndroidLog;

public class AsynchronousValidationRequest implements Runnable {
    private final a a;
    private final CachingExec b;
    private final HttpRoute c;
    private final HttpRequestWrapper d;
    private final HttpClientContext e;
    private final HttpExecutionAware f;
    private final HttpCacheEntry g;
    private final String h;
    private final int i;
    public HttpClientAndroidLog log = new HttpClientAndroidLog(getClass());

    AsynchronousValidationRequest(a aVar, CachingExec cachingExec, HttpRoute httpRoute, HttpRequestWrapper httpRequestWrapper, HttpClientContext httpClientContext, HttpExecutionAware httpExecutionAware, HttpCacheEntry httpCacheEntry, String str, int i) {
        this.a = aVar;
        this.b = cachingExec;
        this.c = httpRoute;
        this.d = httpRequestWrapper;
        this.e = httpClientContext;
        this.f = httpExecutionAware;
        this.g = httpCacheEntry;
        this.h = str;
        this.i = i;
    }

    public void run() {
        try {
            if (a()) {
                this.a.b(this.h);
            } else {
                this.a.c(this.h);
            }
            this.a.a(this.h);
        } catch (Throwable th) {
            this.a.a(this.h);
        }
    }

    private boolean a() {
        HttpResponse a;
        try {
            a = this.b.a(this.c, this.d, this.e, this.f, this.g);
            boolean z = a(a.getStatusLine().getStatusCode()) && a(a);
            a.close();
            return z;
        } catch (Throwable e) {
            this.log.debug("Asynchronous revalidation failed due to I/O error", e);
            return false;
        } catch (Throwable e2) {
            this.log.error("HTTP protocol exception during asynchronous revalidation", e2);
            return false;
        } catch (RuntimeException e3) {
            this.log.error("RuntimeException thrown during asynchronous revalidation: " + e3);
            return false;
        } catch (Throwable th) {
            a.close();
        }
    }

    private boolean a(int i) {
        return i < 500;
    }

    private boolean a(HttpResponse httpResponse) {
        Header[] headers = httpResponse.getHeaders("Warning");
        if (headers != null) {
            for (Header value : headers) {
                String value2 = value.getValue();
                if (value2.startsWith("110") || value2.startsWith("111")) {
                    return false;
                }
            }
        }
        return true;
    }

    public String getIdentifier() {
        return this.h;
    }

    public int getConsecutiveFailedAttempts() {
        return this.i;
    }
}
