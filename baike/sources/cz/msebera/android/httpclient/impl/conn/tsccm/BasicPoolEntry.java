package cz.msebera.android.httpclient.impl.conn.tsccm;

import cz.msebera.android.httpclient.conn.ClientConnectionOperator;
import cz.msebera.android.httpclient.conn.OperatedClientConnection;
import cz.msebera.android.httpclient.conn.routing.HttpRoute;
import cz.msebera.android.httpclient.impl.conn.AbstractPoolEntry;
import cz.msebera.android.httpclient.util.Args;
import java.lang.ref.ReferenceQueue;
import java.util.concurrent.TimeUnit;

@Deprecated
public class BasicPoolEntry extends AbstractPoolEntry {
    private final long f;
    private long g;
    private final long h;
    private long i;

    public BasicPoolEntry(ClientConnectionOperator clientConnectionOperator, HttpRoute httpRoute, ReferenceQueue<Object> referenceQueue) {
        super(clientConnectionOperator, httpRoute);
        Args.notNull(httpRoute, "HTTP route");
        this.f = System.currentTimeMillis();
        this.h = Long.MAX_VALUE;
        this.i = this.h;
    }

    public BasicPoolEntry(ClientConnectionOperator clientConnectionOperator, HttpRoute httpRoute) {
        this(clientConnectionOperator, httpRoute, -1, TimeUnit.MILLISECONDS);
    }

    public BasicPoolEntry(ClientConnectionOperator clientConnectionOperator, HttpRoute httpRoute, long j, TimeUnit timeUnit) {
        super(clientConnectionOperator, httpRoute);
        Args.notNull(httpRoute, "HTTP route");
        this.f = System.currentTimeMillis();
        if (j > 0) {
            this.h = this.f + timeUnit.toMillis(j);
        } else {
            this.h = Long.MAX_VALUE;
        }
        this.i = this.h;
    }

    protected final OperatedClientConnection b() {
        return this.b;
    }

    protected final HttpRoute c() {
        return this.c;
    }

    protected void a() {
        super.a();
    }

    public long getCreated() {
        return this.f;
    }

    public long getUpdated() {
        return this.g;
    }

    public long getExpiry() {
        return this.i;
    }

    public long getValidUntil() {
        return this.h;
    }

    public void updateExpiry(long j, TimeUnit timeUnit) {
        long toMillis;
        this.g = System.currentTimeMillis();
        if (j > 0) {
            toMillis = this.g + timeUnit.toMillis(j);
        } else {
            toMillis = Long.MAX_VALUE;
        }
        this.i = Math.min(this.h, toMillis);
    }

    public boolean isExpired(long j) {
        return j >= this.i;
    }
}
