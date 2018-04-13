package cz.msebera.android.httpclient.impl.conn;

import cz.msebera.android.httpclient.conn.OperatedClientConnection;
import cz.msebera.android.httpclient.conn.routing.HttpRoute;
import cz.msebera.android.httpclient.conn.routing.RouteTracker;
import cz.msebera.android.httpclient.extras.HttpClientAndroidLog;
import cz.msebera.android.httpclient.pool.PoolEntry;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Deprecated
class g extends PoolEntry<HttpRoute, OperatedClientConnection> {
    private final RouteTracker a;
    public HttpClientAndroidLog log;

    public g(HttpClientAndroidLog httpClientAndroidLog, String str, HttpRoute httpRoute, OperatedClientConnection operatedClientConnection, long j, TimeUnit timeUnit) {
        super(str, httpRoute, operatedClientConnection, j, timeUnit);
        this.log = httpClientAndroidLog;
        this.a = new RouteTracker(httpRoute);
    }

    public boolean isExpired(long j) {
        boolean isExpired = super.isExpired(j);
        if (isExpired && this.log.isDebugEnabled()) {
            this.log.debug("Connection " + this + " expired @ " + new Date(getExpiry()));
        }
        return isExpired;
    }

    RouteTracker a() {
        return this.a;
    }

    HttpRoute b() {
        return (HttpRoute) getRoute();
    }

    HttpRoute c() {
        return this.a.toRoute();
    }

    public boolean isClosed() {
        return !((OperatedClientConnection) getConnection()).isOpen();
    }

    public void close() {
        try {
            ((OperatedClientConnection) getConnection()).close();
        } catch (Throwable e) {
            this.log.debug("I/O error closing connection", e);
        }
    }
}
