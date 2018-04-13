package cz.msebera.android.httpclient.impl.conn.tsccm;

import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import cz.msebera.android.httpclient.conn.ClientConnectionOperator;
import cz.msebera.android.httpclient.conn.ConnectionPoolTimeoutException;
import cz.msebera.android.httpclient.conn.OperatedClientConnection;
import cz.msebera.android.httpclient.conn.params.ConnManagerParams;
import cz.msebera.android.httpclient.conn.params.ConnPerRoute;
import cz.msebera.android.httpclient.conn.routing.HttpRoute;
import cz.msebera.android.httpclient.extras.HttpClientAndroidLog;
import cz.msebera.android.httpclient.params.HttpParams;
import cz.msebera.android.httpclient.util.Args;
import cz.msebera.android.httpclient.util.Asserts;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

@Deprecated
public class ConnPoolByRoute extends AbstractConnPool {
    protected final ClientConnectionOperator e;
    protected final ConnPerRoute f;
    protected final Set<BasicPoolEntry> g;
    protected final Queue<BasicPoolEntry> h;
    protected final Queue<WaitingThread> i;
    protected final Map<HttpRoute, RouteSpecificPool> j;
    protected volatile boolean k;
    protected volatile int l;
    public HttpClientAndroidLog log;
    protected volatile int m;
    private final Lock n;
    private final long o;
    private final TimeUnit p;

    public ConnPoolByRoute(ClientConnectionOperator clientConnectionOperator, ConnPerRoute connPerRoute, int i) {
        this(clientConnectionOperator, connPerRoute, i, -1, TimeUnit.MILLISECONDS);
    }

    public ConnPoolByRoute(ClientConnectionOperator clientConnectionOperator, ConnPerRoute connPerRoute, int i, long j, TimeUnit timeUnit) {
        this.log = new HttpClientAndroidLog(getClass());
        Args.notNull(clientConnectionOperator, "Connection operator");
        Args.notNull(connPerRoute, "Connections per route");
        this.n = this.a;
        this.g = this.b;
        this.e = clientConnectionOperator;
        this.f = connPerRoute;
        this.l = i;
        this.h = a();
        this.i = b();
        this.j = c();
        this.o = j;
        this.p = timeUnit;
    }

    @Deprecated
    public ConnPoolByRoute(ClientConnectionOperator clientConnectionOperator, HttpParams httpParams) {
        this(clientConnectionOperator, ConnManagerParams.getMaxConnectionsPerRoute(httpParams), ConnManagerParams.getMaxTotalConnections(httpParams));
    }

    protected Queue<BasicPoolEntry> a() {
        return new LinkedList();
    }

    protected Queue<WaitingThread> b() {
        return new LinkedList();
    }

    protected Map<HttpRoute, RouteSpecificPool> c() {
        return new HashMap();
    }

    protected RouteSpecificPool a(HttpRoute httpRoute) {
        return new RouteSpecificPool(httpRoute, this.f);
    }

    protected WaitingThread a(Condition condition, RouteSpecificPool routeSpecificPool) {
        return new WaitingThread(condition, routeSpecificPool);
    }

    private void b(BasicPoolEntry basicPoolEntry) {
        OperatedClientConnection b = basicPoolEntry.b();
        if (b != null) {
            try {
                b.close();
            } catch (Throwable e) {
                this.log.debug("I/O error closing connection", e);
            }
        }
    }

    protected RouteSpecificPool a(HttpRoute httpRoute, boolean z) {
        this.n.lock();
        try {
            RouteSpecificPool routeSpecificPool = (RouteSpecificPool) this.j.get(httpRoute);
            if (routeSpecificPool == null && z) {
                routeSpecificPool = a(httpRoute);
                this.j.put(httpRoute, routeSpecificPool);
            }
            this.n.unlock();
            return routeSpecificPool;
        } catch (Throwable th) {
            this.n.unlock();
        }
    }

    public int getConnectionsInPool(HttpRoute httpRoute) {
        int i = 0;
        this.n.lock();
        try {
            RouteSpecificPool a = a(httpRoute, false);
            if (a != null) {
                i = a.getEntryCount();
            }
            this.n.unlock();
            return i;
        } catch (Throwable th) {
            this.n.unlock();
        }
    }

    public int getConnectionsInPool() {
        this.n.lock();
        try {
            int i = this.m;
            return i;
        } finally {
            this.n.unlock();
        }
    }

    public PoolEntryRequest requestPoolEntry(HttpRoute httpRoute, Object obj) {
        return new a(this, new WaitingThreadAborter(), httpRoute, obj);
    }

    protected BasicPoolEntry a(HttpRoute httpRoute, Object obj, long j, TimeUnit timeUnit, WaitingThreadAborter waitingThreadAborter) throws ConnectionPoolTimeoutException, InterruptedException {
        Date date = null;
        if (j > 0) {
            date = new Date(System.currentTimeMillis() + timeUnit.toMillis(j));
        }
        BasicPoolEntry basicPoolEntry = null;
        this.n.lock();
        RouteSpecificPool a = a(httpRoute, true);
        WaitingThread waitingThread = null;
        while (basicPoolEntry == null) {
            Asserts.check(!this.k, "Connection pool shut down");
            if (this.log.isDebugEnabled()) {
                this.log.debug("[" + httpRoute + "] total kept alive: " + this.h.size() + ", total issued: " + this.g.size() + ", total allocated: " + this.m + " out of " + this.l);
            }
            basicPoolEntry = a(a, obj);
            if (basicPoolEntry != null) {
                break;
            }
            try {
                Object obj2 = a.getCapacity() > 0 ? 1 : null;
                if (this.log.isDebugEnabled()) {
                    this.log.debug("Available capacity: " + a.getCapacity() + " out of " + a.getMaxEntries() + " [" + httpRoute + "][" + obj + "]");
                }
                if (obj2 != null && this.m < this.l) {
                    basicPoolEntry = a(a, this.e);
                } else if (obj2 == null || this.h.isEmpty()) {
                    if (this.log.isDebugEnabled()) {
                        this.log.debug("Need to wait for connection [" + httpRoute + "][" + obj + "]");
                    }
                    if (waitingThread == null) {
                        waitingThread = a(this.n.newCondition(), a);
                        waitingThreadAborter.setWaitingThread(waitingThread);
                    }
                    a.queueThread(waitingThread);
                    this.i.add(waitingThread);
                    boolean await = waitingThread.await(date);
                    a.removeThread(waitingThread);
                    this.i.remove(waitingThread);
                    if (!(await || date == null || date.getTime() > System.currentTimeMillis())) {
                        throw new ConnectionPoolTimeoutException("Timeout waiting for connection from pool");
                    }
                } else {
                    d();
                    a = a(httpRoute, true);
                    basicPoolEntry = a(a, this.e);
                }
            } catch (Throwable th) {
                this.n.unlock();
            }
        }
        this.n.unlock();
        return basicPoolEntry;
    }

    public void freeEntry(BasicPoolEntry basicPoolEntry, boolean z, long j, TimeUnit timeUnit) {
        HttpRoute c = basicPoolEntry.c();
        if (this.log.isDebugEnabled()) {
            this.log.debug("Releasing connection [" + c + "][" + basicPoolEntry.getState() + "]");
        }
        this.n.lock();
        try {
            if (this.k) {
                b(basicPoolEntry);
                return;
            }
            this.g.remove(basicPoolEntry);
            RouteSpecificPool a = a(c, true);
            if (!z || a.getCapacity() < 0) {
                b(basicPoolEntry);
                a.dropEntry();
                this.m--;
            } else {
                if (this.log.isDebugEnabled()) {
                    String str;
                    if (j > 0) {
                        str = "for " + j + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + timeUnit;
                    } else {
                        str = "indefinitely";
                    }
                    this.log.debug("Pooling connection [" + c + "][" + basicPoolEntry.getState() + "]; keep alive " + str);
                }
                a.freeEntry(basicPoolEntry);
                basicPoolEntry.updateExpiry(j, timeUnit);
                this.h.add(basicPoolEntry);
            }
            a(a);
            this.n.unlock();
        } finally {
            this.n.unlock();
        }
    }

    protected BasicPoolEntry a(RouteSpecificPool routeSpecificPool, Object obj) {
        BasicPoolEntry basicPoolEntry = null;
        this.n.lock();
        Object obj2 = null;
        while (obj2 == null) {
            try {
                basicPoolEntry = routeSpecificPool.allocEntry(obj);
                if (basicPoolEntry != null) {
                    if (this.log.isDebugEnabled()) {
                        this.log.debug("Getting free connection [" + routeSpecificPool.getRoute() + "][" + obj + "]");
                    }
                    this.h.remove(basicPoolEntry);
                    if (basicPoolEntry.isExpired(System.currentTimeMillis())) {
                        if (this.log.isDebugEnabled()) {
                            this.log.debug("Closing expired free connection [" + routeSpecificPool.getRoute() + "][" + obj + "]");
                        }
                        b(basicPoolEntry);
                        routeSpecificPool.dropEntry();
                        this.m--;
                    } else {
                        this.g.add(basicPoolEntry);
                        obj2 = 1;
                    }
                } else if (this.log.isDebugEnabled()) {
                    this.log.debug("No free connections [" + routeSpecificPool.getRoute() + "][" + obj + "]");
                    obj2 = 1;
                } else {
                    obj2 = 1;
                }
            } catch (Throwable th) {
                this.n.unlock();
            }
        }
        this.n.unlock();
        return basicPoolEntry;
    }

    protected BasicPoolEntry a(RouteSpecificPool routeSpecificPool, ClientConnectionOperator clientConnectionOperator) {
        if (this.log.isDebugEnabled()) {
            this.log.debug("Creating new connection [" + routeSpecificPool.getRoute() + "]");
        }
        BasicPoolEntry basicPoolEntry = new BasicPoolEntry(clientConnectionOperator, routeSpecificPool.getRoute(), this.o, this.p);
        this.n.lock();
        try {
            routeSpecificPool.createdEntry(basicPoolEntry);
            this.m++;
            this.g.add(basicPoolEntry);
            return basicPoolEntry;
        } finally {
            basicPoolEntry = this.n;
            basicPoolEntry.unlock();
        }
    }

    protected void a(BasicPoolEntry basicPoolEntry) {
        HttpRoute c = basicPoolEntry.c();
        if (this.log.isDebugEnabled()) {
            this.log.debug("Deleting connection [" + c + "][" + basicPoolEntry.getState() + "]");
        }
        this.n.lock();
        try {
            b(basicPoolEntry);
            RouteSpecificPool a = a(c, true);
            a.deleteEntry(basicPoolEntry);
            this.m--;
            if (a.isUnused()) {
                this.j.remove(c);
            }
            this.n.unlock();
        } catch (Throwable th) {
            this.n.unlock();
        }
    }

    protected void d() {
        this.n.lock();
        try {
            BasicPoolEntry basicPoolEntry = (BasicPoolEntry) this.h.remove();
            if (basicPoolEntry != null) {
                a(basicPoolEntry);
            } else if (this.log.isDebugEnabled()) {
                this.log.debug("No free connection to delete");
            }
            this.n.unlock();
        } catch (Throwable th) {
            this.n.unlock();
        }
    }

    protected void a(RouteSpecificPool routeSpecificPool) {
        WaitingThread waitingThread = null;
        this.n.lock();
        if (routeSpecificPool != null) {
            try {
                if (routeSpecificPool.hasThread()) {
                    if (this.log.isDebugEnabled()) {
                        this.log.debug("Notifying thread waiting on pool [" + routeSpecificPool.getRoute() + "]");
                    }
                    waitingThread = routeSpecificPool.nextThread();
                    if (waitingThread != null) {
                        waitingThread.wakeup();
                    }
                    this.n.unlock();
                }
            } catch (Throwable th) {
                this.n.unlock();
            }
        }
        if (!this.i.isEmpty()) {
            if (this.log.isDebugEnabled()) {
                this.log.debug("Notifying thread waiting on any pool");
            }
            waitingThread = (WaitingThread) this.i.remove();
        } else if (this.log.isDebugEnabled()) {
            this.log.debug("Notifying no-one, there are no waiting threads");
        }
        if (waitingThread != null) {
            waitingThread.wakeup();
        }
        this.n.unlock();
    }

    public void deleteClosedConnections() {
        this.n.lock();
        try {
            Iterator it = this.h.iterator();
            while (it.hasNext()) {
                BasicPoolEntry basicPoolEntry = (BasicPoolEntry) it.next();
                if (!basicPoolEntry.b().isOpen()) {
                    it.remove();
                    a(basicPoolEntry);
                }
            }
        } finally {
            this.n.unlock();
        }
    }

    public void closeIdleConnections(long j, TimeUnit timeUnit) {
        Args.notNull(timeUnit, "Time unit");
        if (j <= 0) {
            j = 0;
        }
        if (this.log.isDebugEnabled()) {
            this.log.debug("Closing connections idle longer than " + j + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + timeUnit);
        }
        long currentTimeMillis = System.currentTimeMillis() - timeUnit.toMillis(j);
        this.n.lock();
        try {
            Iterator it = this.h.iterator();
            while (it.hasNext()) {
                BasicPoolEntry basicPoolEntry = (BasicPoolEntry) it.next();
                if (basicPoolEntry.getUpdated() <= currentTimeMillis) {
                    if (this.log.isDebugEnabled()) {
                        this.log.debug("Closing connection last used @ " + new Date(basicPoolEntry.getUpdated()));
                    }
                    it.remove();
                    a(basicPoolEntry);
                }
            }
        } finally {
            this.n.unlock();
        }
    }

    public void closeExpiredConnections() {
        this.log.debug("Closing expired connections");
        long currentTimeMillis = System.currentTimeMillis();
        this.n.lock();
        try {
            Iterator it = this.h.iterator();
            while (it.hasNext()) {
                BasicPoolEntry basicPoolEntry = (BasicPoolEntry) it.next();
                if (basicPoolEntry.isExpired(currentTimeMillis)) {
                    if (this.log.isDebugEnabled()) {
                        this.log.debug("Closing connection expired @ " + new Date(basicPoolEntry.getExpiry()));
                    }
                    it.remove();
                    a(basicPoolEntry);
                }
            }
        } finally {
            this.n.unlock();
        }
    }

    public void shutdown() {
        this.n.lock();
        try {
            if (!this.k) {
                BasicPoolEntry basicPoolEntry;
                this.k = true;
                Iterator it = this.g.iterator();
                while (it.hasNext()) {
                    basicPoolEntry = (BasicPoolEntry) it.next();
                    it.remove();
                    b(basicPoolEntry);
                }
                it = this.h.iterator();
                while (it.hasNext()) {
                    basicPoolEntry = (BasicPoolEntry) it.next();
                    it.remove();
                    if (this.log.isDebugEnabled()) {
                        this.log.debug("Closing connection [" + basicPoolEntry.c() + "][" + basicPoolEntry.getState() + "]");
                    }
                    b(basicPoolEntry);
                }
                it = this.i.iterator();
                while (it.hasNext()) {
                    WaitingThread waitingThread = (WaitingThread) it.next();
                    it.remove();
                    waitingThread.wakeup();
                }
                this.j.clear();
                this.n.unlock();
            }
        } finally {
            this.n.unlock();
        }
    }

    public void setMaxTotalConnections(int i) {
        this.n.lock();
        try {
            this.l = i;
        } finally {
            this.n.unlock();
        }
    }

    public int getMaxTotalConnections() {
        return this.l;
    }
}
