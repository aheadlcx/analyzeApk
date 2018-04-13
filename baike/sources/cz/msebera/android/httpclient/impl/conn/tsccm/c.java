package cz.msebera.android.httpclient.impl.conn.tsccm;

import cz.msebera.android.httpclient.conn.ClientConnectionRequest;
import cz.msebera.android.httpclient.conn.ConnectionPoolTimeoutException;
import cz.msebera.android.httpclient.conn.ManagedClientConnection;
import cz.msebera.android.httpclient.conn.routing.HttpRoute;
import cz.msebera.android.httpclient.util.Args;
import java.util.concurrent.TimeUnit;

class c implements ClientConnectionRequest {
    final /* synthetic */ PoolEntryRequest a;
    final /* synthetic */ HttpRoute b;
    final /* synthetic */ ThreadSafeClientConnManager c;

    c(ThreadSafeClientConnManager threadSafeClientConnManager, PoolEntryRequest poolEntryRequest, HttpRoute httpRoute) {
        this.c = threadSafeClientConnManager;
        this.a = poolEntryRequest;
        this.b = httpRoute;
    }

    public void abortRequest() {
        this.a.abortRequest();
    }

    public ManagedClientConnection getConnection(long j, TimeUnit timeUnit) throws InterruptedException, ConnectionPoolTimeoutException {
        Args.notNull(this.b, "Route");
        if (this.c.log.isDebugEnabled()) {
            this.c.log.debug("Get connection: " + this.b + ", timeout = " + j);
        }
        return new BasicPooledConnAdapter(this.c, this.a.getPoolEntry(j, timeUnit));
    }
}
