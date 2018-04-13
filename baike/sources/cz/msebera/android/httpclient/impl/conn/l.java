package cz.msebera.android.httpclient.impl.conn;

import cz.msebera.android.httpclient.conn.ClientConnectionRequest;
import cz.msebera.android.httpclient.conn.ConnectionPoolTimeoutException;
import cz.msebera.android.httpclient.conn.ManagedClientConnection;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

class l implements ClientConnectionRequest {
    final /* synthetic */ Future a;
    final /* synthetic */ PoolingClientConnectionManager b;

    l(PoolingClientConnectionManager poolingClientConnectionManager, Future future) {
        this.b = poolingClientConnectionManager;
        this.a = future;
    }

    public void abortRequest() {
        this.a.cancel(true);
    }

    public ManagedClientConnection getConnection(long j, TimeUnit timeUnit) throws InterruptedException, ConnectionPoolTimeoutException {
        return this.b.a(this.a, j, timeUnit);
    }
}
