package cz.msebera.android.httpclient.impl.client.cache;

import cz.msebera.android.httpclient.client.cache.HttpCacheEntry;

class ae {
    private final String a;
    private final String b;
    private final HttpCacheEntry c;

    public ae(String str, String str2, HttpCacheEntry httpCacheEntry) {
        this.a = str;
        this.b = str2;
        this.c = httpCacheEntry;
    }

    public String getVariantKey() {
        return this.a;
    }

    public String getCacheKey() {
        return this.b;
    }

    public HttpCacheEntry getEntry() {
        return this.c;
    }
}
