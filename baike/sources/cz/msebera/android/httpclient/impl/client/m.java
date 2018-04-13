package cz.msebera.android.httpclient.impl.client;

import cz.msebera.android.httpclient.conn.ClientConnectionManager;
import cz.msebera.android.httpclient.conn.ClientConnectionRequest;
import cz.msebera.android.httpclient.conn.ManagedClientConnection;
import cz.msebera.android.httpclient.conn.routing.HttpRoute;
import cz.msebera.android.httpclient.conn.scheme.SchemeRegistry;
import java.util.concurrent.TimeUnit;

class m implements ClientConnectionManager {
    final /* synthetic */ l a;

    m(l lVar) {
        this.a = lVar;
    }

    public void shutdown() {
        this.a.a.shutdown();
    }

    public ClientConnectionRequest requestConnection(HttpRoute httpRoute, Object obj) {
        throw new UnsupportedOperationException();
    }

    public void releaseConnection(ManagedClientConnection managedClientConnection, long j, TimeUnit timeUnit) {
        throw new UnsupportedOperationException();
    }

    public SchemeRegistry getSchemeRegistry() {
        throw new UnsupportedOperationException();
    }

    public void closeIdleConnections(long j, TimeUnit timeUnit) {
        this.a.a.closeIdleConnections(j, timeUnit);
    }

    public void closeExpiredConnections() {
        this.a.a.closeExpiredConnections();
    }
}
