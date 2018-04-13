package cz.msebera.android.httpclient.impl.client.cache;

import java.io.Closeable;
import java.io.IOException;

class o implements Closeable {
    final /* synthetic */ ManagedHttpCacheStorage a;
    final /* synthetic */ CachingHttpClientBuilder b;

    o(CachingHttpClientBuilder cachingHttpClientBuilder, ManagedHttpCacheStorage managedHttpCacheStorage) {
        this.b = cachingHttpClientBuilder;
        this.a = managedHttpCacheStorage;
    }

    public void close() throws IOException {
        this.a.shutdown();
    }
}
