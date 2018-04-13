package cz.msebera.android.httpclient.impl.conn;

import cz.msebera.android.httpclient.annotation.GuardedBy;
import cz.msebera.android.httpclient.annotation.ThreadSafe;
import cz.msebera.android.httpclient.conn.ClientConnectionManager;
import cz.msebera.android.httpclient.conn.ClientConnectionOperator;
import cz.msebera.android.httpclient.conn.ClientConnectionRequest;
import cz.msebera.android.httpclient.conn.ManagedClientConnection;
import cz.msebera.android.httpclient.conn.routing.HttpRoute;
import cz.msebera.android.httpclient.conn.routing.RouteTracker;
import cz.msebera.android.httpclient.conn.scheme.SchemeRegistry;
import cz.msebera.android.httpclient.extras.HttpClientAndroidLog;
import cz.msebera.android.httpclient.params.HttpParams;
import cz.msebera.android.httpclient.util.Args;
import cz.msebera.android.httpclient.util.Asserts;
import java.util.concurrent.TimeUnit;

@ThreadSafe
@Deprecated
public class SingleClientConnManager implements ClientConnectionManager {
    public static final String MISUSE_MESSAGE = "Invalid use of SingleClientConnManager: connection still allocated.\nMake sure to release the connection before allocating another one.";
    protected final SchemeRegistry a;
    protected final ClientConnectionOperator b;
    protected final boolean c;
    @GuardedBy("this")
    protected volatile SingleClientConnManager$PoolEntry d;
    @GuardedBy("this")
    protected volatile SingleClientConnManager$ConnAdapter e;
    @GuardedBy("this")
    protected volatile long f;
    @GuardedBy("this")
    protected volatile long g;
    protected volatile boolean h;
    public HttpClientAndroidLog log;

    @Deprecated
    public SingleClientConnManager(HttpParams httpParams, SchemeRegistry schemeRegistry) {
        this(schemeRegistry);
    }

    public SingleClientConnManager(SchemeRegistry schemeRegistry) {
        this.log = new HttpClientAndroidLog(getClass());
        Args.notNull(schemeRegistry, "Scheme registry");
        this.a = schemeRegistry;
        this.b = a(schemeRegistry);
        this.d = new SingleClientConnManager$PoolEntry(this);
        this.e = null;
        this.f = -1;
        this.c = false;
        this.h = false;
    }

    public SingleClientConnManager() {
        this(SchemeRegistryFactory.createDefault());
    }

    protected void finalize() throws Throwable {
        try {
            shutdown();
        } finally {
            super.finalize();
        }
    }

    public SchemeRegistry getSchemeRegistry() {
        return this.a;
    }

    protected ClientConnectionOperator a(SchemeRegistry schemeRegistry) {
        return new DefaultClientConnectionOperator(schemeRegistry);
    }

    protected final void a() throws IllegalStateException {
        Asserts.check(!this.h, "Manager is shut down");
    }

    public final ClientConnectionRequest requestConnection(HttpRoute httpRoute, Object obj) {
        return new o(this, httpRoute, obj);
    }

    public ManagedClientConnection getConnection(HttpRoute httpRoute, Object obj) {
        ManagedClientConnection managedClientConnection;
        Object obj2 = 1;
        Object obj3 = null;
        Args.notNull(httpRoute, "Route");
        a();
        if (this.log.isDebugEnabled()) {
            this.log.debug("Get connection for route " + httpRoute);
        }
        synchronized (this) {
            Asserts.check(this.e == null, MISUSE_MESSAGE);
            closeExpiredConnections();
            if (this.d.b.isOpen()) {
                RouteTracker routeTracker = this.d.e;
                Object obj4 = (routeTracker == null || !routeTracker.toRoute().equals(httpRoute)) ? 1 : null;
                Object obj5 = obj4;
                obj4 = null;
                obj3 = obj5;
            } else {
                int i = 1;
            }
            if (obj3 != null) {
                try {
                    this.d.c();
                } catch (Throwable e) {
                    this.log.debug("Problem shutting down connection.", e);
                }
            } else {
                obj2 = obj4;
            }
            if (obj2 != null) {
                this.d = new SingleClientConnManager$PoolEntry(this);
            }
            this.e = new SingleClientConnManager$ConnAdapter(this, this.d, httpRoute);
            managedClientConnection = this.e;
        }
        return managedClientConnection;
    }

    public void releaseConnection(ManagedClientConnection managedClientConnection, long j, TimeUnit timeUnit) {
        Args.check(managedClientConnection instanceof SingleClientConnManager$ConnAdapter, "Connection class mismatch, connection not obtained from this manager");
        a();
        if (this.log.isDebugEnabled()) {
            this.log.debug("Releasing connection " + managedClientConnection);
        }
        SingleClientConnManager$ConnAdapter singleClientConnManager$ConnAdapter = (SingleClientConnManager$ConnAdapter) managedClientConnection;
        synchronized (singleClientConnManager$ConnAdapter) {
            if (singleClientConnManager$ConnAdapter.a == null) {
                return;
            }
            Asserts.check(singleClientConnManager$ConnAdapter.c() == this, "Connection not obtained from this manager");
            try {
                if (singleClientConnManager$ConnAdapter.isOpen() && (this.c || !singleClientConnManager$ConnAdapter.isMarkedReusable())) {
                    if (this.log.isDebugEnabled()) {
                        this.log.debug("Released connection open but not reusable.");
                    }
                    singleClientConnManager$ConnAdapter.shutdown();
                }
                singleClientConnManager$ConnAdapter.a();
                synchronized (this) {
                    this.e = null;
                    this.f = System.currentTimeMillis();
                    if (j > 0) {
                        this.g = timeUnit.toMillis(j) + this.f;
                    } else {
                        this.g = Long.MAX_VALUE;
                    }
                }
            } catch (Throwable e) {
                if (this.log.isDebugEnabled()) {
                    this.log.debug("Exception shutting down released connection.", e);
                }
                singleClientConnManager$ConnAdapter.a();
                synchronized (this) {
                    this.e = null;
                    this.f = System.currentTimeMillis();
                    if (j > 0) {
                        this.g = timeUnit.toMillis(j) + this.f;
                    } else {
                        this.g = Long.MAX_VALUE;
                    }
                }
            } catch (Throwable th) {
                singleClientConnManager$ConnAdapter.a();
                synchronized (this) {
                    this.e = null;
                    this.f = System.currentTimeMillis();
                    if (j > 0) {
                        this.g = timeUnit.toMillis(j) + this.f;
                    } else {
                        this.g = Long.MAX_VALUE;
                    }
                }
            }
            return;
        }
    }

    public void closeExpiredConnections() {
        if (System.currentTimeMillis() >= this.g) {
            closeIdleConnections(0, TimeUnit.MILLISECONDS);
        }
    }

    public void closeIdleConnections(long j, TimeUnit timeUnit) {
        a();
        Args.notNull(timeUnit, "Time unit");
        synchronized (this) {
            if (this.e == null && this.d.b.isOpen()) {
                if (this.f <= System.currentTimeMillis() - timeUnit.toMillis(j)) {
                    try {
                        this.d.b();
                    } catch (Throwable e) {
                        this.log.debug("Problem closing idle connection.", e);
                    }
                }
            }
        }
    }

    public void shutdown() {
        this.h = true;
        synchronized (this) {
            try {
                if (this.d != null) {
                    this.d.c();
                }
                this.d = null;
                this.e = null;
            } catch (Throwable e) {
                this.log.debug("Problem while shutting down manager.", e);
                this.d = null;
                this.e = null;
            } catch (Throwable th) {
                this.d = null;
                this.e = null;
            }
        }
    }
}
