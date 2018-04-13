package cz.msebera.android.httpclient.impl.client.cache;

import cz.msebera.android.httpclient.HttpRequest;
import cz.msebera.android.httpclient.client.cache.HttpCacheEntry;
import cz.msebera.android.httpclient.client.cache.HttpCacheUpdateCallback;
import java.io.IOException;

class d implements HttpCacheUpdateCallback {
    final /* synthetic */ HttpRequest a;
    final /* synthetic */ HttpCacheEntry b;
    final /* synthetic */ String c;
    final /* synthetic */ String d;
    final /* synthetic */ b e;

    d(b bVar, HttpRequest httpRequest, HttpCacheEntry httpCacheEntry, String str, String str2) {
        this.e = bVar;
        this.a = httpRequest;
        this.b = httpCacheEntry;
        this.c = str;
        this.d = str2;
    }

    public HttpCacheEntry update(HttpCacheEntry httpCacheEntry) throws IOException {
        return this.e.a(this.a.getRequestLine().getUri(), httpCacheEntry, this.b, this.c, this.d);
    }
}
