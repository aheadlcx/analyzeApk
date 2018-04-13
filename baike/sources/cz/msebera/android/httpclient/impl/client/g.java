package cz.msebera.android.httpclient.impl.client;

import cz.msebera.android.httpclient.conn.HttpClientConnectionManager;
import java.io.Closeable;
import java.io.IOException;

class g implements Closeable {
    final /* synthetic */ HttpClientConnectionManager a;
    final /* synthetic */ HttpClientBuilder b;

    g(HttpClientBuilder httpClientBuilder, HttpClientConnectionManager httpClientConnectionManager) {
        this.b = httpClientBuilder;
        this.a = httpClientConnectionManager;
    }

    public void close() throws IOException {
        this.a.shutdown();
    }
}
