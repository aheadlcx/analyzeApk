package cz.msebera.android.httpclient.impl.client.cache;

import cz.msebera.android.httpclient.HttpRequest;
import cz.msebera.android.httpclient.client.cache.HttpCacheEntry;
import cz.msebera.android.httpclient.client.cache.HttpCacheUpdateCallback;
import java.io.IOException;

class c implements HttpCacheUpdateCallback {
    final /* synthetic */ HttpRequest a;
    final /* synthetic */ HttpCacheEntry b;
    final /* synthetic */ String c;
    final /* synthetic */ b d;

    c(b bVar, HttpRequest httpRequest, HttpCacheEntry httpCacheEntry, String str) {
        this.d = bVar;
        this.a = httpRequest;
        this.b = httpCacheEntry;
        this.c = str;
    }

    public HttpCacheEntry update(HttpCacheEntry httpCacheEntry) throws IOException {
        return this.d.a(this.a.getRequestLine().getUri(), httpCacheEntry, this.b, this.d.b.getVariantKey(this.a, this.b), this.c);
    }
}
