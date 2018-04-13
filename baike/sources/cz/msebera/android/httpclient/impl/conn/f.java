package cz.msebera.android.httpclient.impl.conn;

import cz.msebera.android.httpclient.conn.ClientConnectionOperator;
import cz.msebera.android.httpclient.conn.OperatedClientConnection;
import cz.msebera.android.httpclient.conn.routing.HttpRoute;
import cz.msebera.android.httpclient.extras.HttpClientAndroidLog;
import cz.msebera.android.httpclient.pool.AbstractConnPool;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

@Deprecated
class f extends AbstractConnPool<HttpRoute, OperatedClientConnection, g> {
    private static final AtomicLong a = new AtomicLong();
    private final long b;
    private final TimeUnit c;
    public HttpClientAndroidLog log;

    public f(HttpClientAndroidLog httpClientAndroidLog, ClientConnectionOperator clientConnectionOperator, int i, int i2, long j, TimeUnit timeUnit) {
        super(new f$a(clientConnectionOperator), i, i2);
        this.log = httpClientAndroidLog;
        this.b = j;
        this.c = timeUnit;
    }

    protected g a(HttpRoute httpRoute, OperatedClientConnection operatedClientConnection) {
        return new g(this.log, Long.toString(a.getAndIncrement()), httpRoute, operatedClientConnection, this.b, this.c);
    }
}
