package cz.msebera.android.httpclient.client.cache;

import java.io.IOException;

public interface HttpCacheUpdateCallback {
    HttpCacheEntry update(HttpCacheEntry httpCacheEntry) throws IOException;
}
