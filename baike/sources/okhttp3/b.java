package okhttp3;

import java.io.IOException;
import okhttp3.internal.cache.CacheRequest;
import okhttp3.internal.cache.CacheStrategy;
import okhttp3.internal.cache.InternalCache;

class b implements InternalCache {
    final /* synthetic */ Cache a;

    b(Cache cache) {
        this.a = cache;
    }

    public Response get(Request request) throws IOException {
        return this.a.a(request);
    }

    public CacheRequest put(Response response) throws IOException {
        return this.a.a(response);
    }

    public void remove(Request request) throws IOException {
        this.a.b(request);
    }

    public void update(Response response, Response response2) {
        this.a.a(response, response2);
    }

    public void trackConditionalCacheHit() {
        this.a.a();
    }

    public void trackResponse(CacheStrategy cacheStrategy) {
        this.a.a(cacheStrategy);
    }
}
