package cz.msebera.android.httpclient.impl.client.cache;

import cz.msebera.android.httpclient.client.cache.HttpCacheEntry;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

final class j extends LinkedHashMap<String, HttpCacheEntry> {
    private final int a;

    j(int i) {
        super(20, 0.75f, true);
        this.a = i;
    }

    protected boolean removeEldestEntry(Entry<String, HttpCacheEntry> entry) {
        return size() > this.a;
    }
}
