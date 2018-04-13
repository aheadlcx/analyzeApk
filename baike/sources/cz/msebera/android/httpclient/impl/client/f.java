package cz.msebera.android.httpclient.impl.client;

import java.io.Closeable;
import java.io.IOException;

class f implements Closeable {
    final /* synthetic */ IdleConnectionEvictor a;
    final /* synthetic */ HttpClientBuilder b;

    f(HttpClientBuilder httpClientBuilder, IdleConnectionEvictor idleConnectionEvictor) {
        this.b = httpClientBuilder;
        this.a = idleConnectionEvictor;
    }

    public void close() throws IOException {
        this.a.shutdown();
    }
}
