package cz.msebera.android.httpclient.impl.conn.tsccm;

import cz.msebera.android.httpclient.annotation.GuardedBy;
import cz.msebera.android.httpclient.conn.ConnectionPoolTimeoutException;
import cz.msebera.android.httpclient.conn.OperatedClientConnection;
import cz.msebera.android.httpclient.conn.routing.HttpRoute;
import cz.msebera.android.httpclient.extras.HttpClientAndroidLog;
import cz.msebera.android.httpclient.impl.conn.IdleConnectionHandler;
import cz.msebera.android.httpclient.util.Args;
import java.lang.ref.Reference;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Deprecated
public abstract class AbstractConnPool {
    protected final Lock a = new ReentrantLock();
    @GuardedBy("poolLock")
    protected Set<BasicPoolEntry> b = new HashSet();
    protected volatile boolean c;
    protected IdleConnectionHandler d = new IdleConnectionHandler();
    public HttpClientAndroidLog log = new HttpClientAndroidLog(getClass());

    public abstract void deleteClosedConnections();

    public abstract void freeEntry(BasicPoolEntry basicPoolEntry, boolean z, long j, TimeUnit timeUnit);

    public abstract PoolEntryRequest requestPoolEntry(HttpRoute httpRoute, Object obj);

    protected AbstractConnPool() {
    }

    public void enableConnectionGC() throws IllegalStateException {
    }

    public final BasicPoolEntry getEntry(HttpRoute httpRoute, Object obj, long j, TimeUnit timeUnit) throws ConnectionPoolTimeoutException, InterruptedException {
        return requestPoolEntry(httpRoute, obj).getPoolEntry(j, timeUnit);
    }

    public void handleReference(Reference<?> reference) {
    }

    public void closeIdleConnections(long j, TimeUnit timeUnit) {
        Args.notNull(timeUnit, "Time unit");
        this.a.lock();
        try {
            this.d.closeIdleConnections(timeUnit.toMillis(j));
        } finally {
            this.a.unlock();
        }
    }

    public void closeExpiredConnections() {
        this.a.lock();
        try {
            this.d.closeExpiredConnections();
        } finally {
            this.a.unlock();
        }
    }

    public void shutdown() {
        this.a.lock();
        try {
            if (!this.c) {
                Iterator it = this.b.iterator();
                while (it.hasNext()) {
                    BasicPoolEntry basicPoolEntry = (BasicPoolEntry) it.next();
                    it.remove();
                    a(basicPoolEntry.b());
                }
                this.d.removeAll();
                this.c = true;
                this.a.unlock();
            }
        } finally {
            this.a.unlock();
        }
    }

    protected void a(OperatedClientConnection operatedClientConnection) {
        if (operatedClientConnection != null) {
            try {
                operatedClientConnection.close();
            } catch (Throwable e) {
                this.log.debug("I/O error closing connection", e);
            }
        }
    }
}
