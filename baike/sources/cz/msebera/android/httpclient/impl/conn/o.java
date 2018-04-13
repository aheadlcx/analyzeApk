package cz.msebera.android.httpclient.impl.conn;

import cz.msebera.android.httpclient.conn.ClientConnectionRequest;
import cz.msebera.android.httpclient.conn.ManagedClientConnection;
import cz.msebera.android.httpclient.conn.routing.HttpRoute;
import java.util.concurrent.TimeUnit;

class o implements ClientConnectionRequest {
    final /* synthetic */ HttpRoute a;
    final /* synthetic */ Object b;
    final /* synthetic */ SingleClientConnManager c;

    o(SingleClientConnManager singleClientConnManager, HttpRoute httpRoute, Object obj) {
        this.c = singleClientConnManager;
        this.a = httpRoute;
        this.b = obj;
    }

    public void abortRequest() {
    }

    public ManagedClientConnection getConnection(long j, TimeUnit timeUnit) {
        return this.c.getConnection(this.a, this.b);
    }
}
