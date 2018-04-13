package cz.msebera.android.httpclient.impl.conn;

import cz.msebera.android.httpclient.HttpClientConnection;
import cz.msebera.android.httpclient.conn.ConnectionRequest;
import cz.msebera.android.httpclient.conn.routing.HttpRoute;
import java.util.concurrent.TimeUnit;

class b implements ConnectionRequest {
    final /* synthetic */ HttpRoute a;
    final /* synthetic */ Object b;
    final /* synthetic */ BasicHttpClientConnectionManager c;

    b(BasicHttpClientConnectionManager basicHttpClientConnectionManager, HttpRoute httpRoute, Object obj) {
        this.c = basicHttpClientConnectionManager;
        this.a = httpRoute;
        this.b = obj;
    }

    public boolean cancel() {
        return false;
    }

    public HttpClientConnection get(long j, TimeUnit timeUnit) {
        return this.c.a(this.a, this.b);
    }
}
