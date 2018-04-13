package cz.msebera.android.httpclient.impl.conn;

import cz.msebera.android.httpclient.annotation.ThreadSafe;
import cz.msebera.android.httpclient.conn.ManagedHttpClientConnection;
import cz.msebera.android.httpclient.conn.routing.HttpRoute;
import cz.msebera.android.httpclient.extras.HttpClientAndroidLog;
import cz.msebera.android.httpclient.pool.AbstractConnPool;
import cz.msebera.android.httpclient.pool.ConnFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

@ThreadSafe
class c extends AbstractConnPool<HttpRoute, ManagedHttpClientConnection, d> {
    private static final AtomicLong a = new AtomicLong();
    private final long b;
    private final TimeUnit c;
    public HttpClientAndroidLog log = new HttpClientAndroidLog(c.class);

    public c(ConnFactory<HttpRoute, ManagedHttpClientConnection> connFactory, int i, int i2, long j, TimeUnit timeUnit) {
        super(connFactory, i, i2);
        this.b = j;
        this.c = timeUnit;
    }

    protected d a(HttpRoute httpRoute, ManagedHttpClientConnection managedHttpClientConnection) {
        return new d(this.log, Long.toString(a.getAndIncrement()), httpRoute, managedHttpClientConnection, this.b, this.c);
    }

    protected boolean a(d dVar) {
        return !((ManagedHttpClientConnection) dVar.getConnection()).isStale();
    }
}
