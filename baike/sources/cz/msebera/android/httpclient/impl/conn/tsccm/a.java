package cz.msebera.android.httpclient.impl.conn.tsccm;

import cz.msebera.android.httpclient.conn.ConnectionPoolTimeoutException;
import cz.msebera.android.httpclient.conn.routing.HttpRoute;
import java.util.concurrent.TimeUnit;

class a implements PoolEntryRequest {
    final /* synthetic */ WaitingThreadAborter a;
    final /* synthetic */ HttpRoute b;
    final /* synthetic */ Object c;
    final /* synthetic */ ConnPoolByRoute d;

    a(ConnPoolByRoute connPoolByRoute, WaitingThreadAborter waitingThreadAborter, HttpRoute httpRoute, Object obj) {
        this.d = connPoolByRoute;
        this.a = waitingThreadAborter;
        this.b = httpRoute;
        this.c = obj;
    }

    public void abortRequest() {
        ConnPoolByRoute.a(this.d).lock();
        try {
            this.a.abort();
        } finally {
            ConnPoolByRoute.a(this.d).unlock();
        }
    }

    public BasicPoolEntry getPoolEntry(long j, TimeUnit timeUnit) throws InterruptedException, ConnectionPoolTimeoutException {
        return this.d.a(this.b, this.c, j, timeUnit, this.a);
    }
}
