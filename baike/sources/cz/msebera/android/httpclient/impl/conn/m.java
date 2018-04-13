package cz.msebera.android.httpclient.impl.conn;

import cz.msebera.android.httpclient.HttpClientConnection;
import cz.msebera.android.httpclient.conn.ConnectionPoolTimeoutException;
import cz.msebera.android.httpclient.conn.ConnectionRequest;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

class m implements ConnectionRequest {
    final /* synthetic */ Future a;
    final /* synthetic */ PoolingHttpClientConnectionManager b;

    m(PoolingHttpClientConnectionManager poolingHttpClientConnectionManager, Future future) {
        this.b = poolingHttpClientConnectionManager;
        this.a = future;
    }

    public boolean cancel() {
        return this.a.cancel(true);
    }

    public HttpClientConnection get(long j, TimeUnit timeUnit) throws InterruptedException, ExecutionException, ConnectionPoolTimeoutException {
        return this.b.a(this.a, j, timeUnit);
    }
}
