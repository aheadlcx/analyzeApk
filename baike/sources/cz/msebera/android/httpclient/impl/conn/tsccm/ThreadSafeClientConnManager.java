package cz.msebera.android.httpclient.impl.conn.tsccm;

import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import cz.msebera.android.httpclient.annotation.ThreadSafe;
import cz.msebera.android.httpclient.conn.ClientConnectionManager;
import cz.msebera.android.httpclient.conn.ClientConnectionOperator;
import cz.msebera.android.httpclient.conn.ClientConnectionRequest;
import cz.msebera.android.httpclient.conn.ManagedClientConnection;
import cz.msebera.android.httpclient.conn.params.ConnPerRouteBean;
import cz.msebera.android.httpclient.conn.routing.HttpRoute;
import cz.msebera.android.httpclient.conn.scheme.SchemeRegistry;
import cz.msebera.android.httpclient.extras.HttpClientAndroidLog;
import cz.msebera.android.httpclient.impl.conn.DefaultClientConnectionOperator;
import cz.msebera.android.httpclient.impl.conn.SchemeRegistryFactory;
import cz.msebera.android.httpclient.params.HttpParams;
import cz.msebera.android.httpclient.util.Args;
import cz.msebera.android.httpclient.util.Asserts;
import java.util.concurrent.TimeUnit;

@ThreadSafe
@Deprecated
public class ThreadSafeClientConnManager implements ClientConnectionManager {
    protected final SchemeRegistry a;
    protected final AbstractConnPool b;
    protected final ConnPoolByRoute c;
    protected final ClientConnectionOperator d;
    protected final ConnPerRouteBean e;
    public HttpClientAndroidLog log;

    public ThreadSafeClientConnManager(SchemeRegistry schemeRegistry) {
        this(schemeRegistry, -1, TimeUnit.MILLISECONDS);
    }

    public ThreadSafeClientConnManager() {
        this(SchemeRegistryFactory.createDefault());
    }

    public ThreadSafeClientConnManager(SchemeRegistry schemeRegistry, long j, TimeUnit timeUnit) {
        this(schemeRegistry, j, timeUnit, new ConnPerRouteBean());
    }

    public ThreadSafeClientConnManager(SchemeRegistry schemeRegistry, long j, TimeUnit timeUnit, ConnPerRouteBean connPerRouteBean) {
        Args.notNull(schemeRegistry, "Scheme registry");
        this.log = new HttpClientAndroidLog(getClass());
        this.a = schemeRegistry;
        this.e = connPerRouteBean;
        this.d = a(schemeRegistry);
        this.c = a(j, timeUnit);
        this.b = this.c;
    }

    @Deprecated
    public ThreadSafeClientConnManager(HttpParams httpParams, SchemeRegistry schemeRegistry) {
        Args.notNull(schemeRegistry, "Scheme registry");
        this.log = new HttpClientAndroidLog(getClass());
        this.a = schemeRegistry;
        this.e = new ConnPerRouteBean();
        this.d = a(schemeRegistry);
        this.c = (ConnPoolByRoute) a(httpParams);
        this.b = this.c;
    }

    protected void finalize() throws Throwable {
        try {
            shutdown();
        } finally {
            super.finalize();
        }
    }

    @Deprecated
    protected AbstractConnPool a(HttpParams httpParams) {
        return new ConnPoolByRoute(this.d, httpParams);
    }

    protected ConnPoolByRoute a(long j, TimeUnit timeUnit) {
        return new ConnPoolByRoute(this.d, this.e, 20, j, timeUnit);
    }

    protected ClientConnectionOperator a(SchemeRegistry schemeRegistry) {
        return new DefaultClientConnectionOperator(schemeRegistry);
    }

    public SchemeRegistry getSchemeRegistry() {
        return this.a;
    }

    public ClientConnectionRequest requestConnection(HttpRoute httpRoute, Object obj) {
        return new c(this, this.c.requestPoolEntry(httpRoute, obj), httpRoute);
    }

    public void releaseConnection(ManagedClientConnection managedClientConnection, long j, TimeUnit timeUnit) {
        boolean isMarkedReusable;
        Args.check(managedClientConnection instanceof BasicPooledConnAdapter, "Connection class mismatch, connection not obtained from this manager");
        BasicPooledConnAdapter basicPooledConnAdapter = (BasicPooledConnAdapter) managedClientConnection;
        if (basicPooledConnAdapter.e() != null) {
            Asserts.check(basicPooledConnAdapter.c() == this, "Connection not obtained from this manager");
        }
        synchronized (basicPooledConnAdapter) {
            BasicPoolEntry basicPoolEntry = (BasicPoolEntry) basicPooledConnAdapter.e();
            if (basicPoolEntry == null) {
                return;
            }
            try {
                if (basicPooledConnAdapter.isOpen() && !basicPooledConnAdapter.isMarkedReusable()) {
                    basicPooledConnAdapter.shutdown();
                }
                isMarkedReusable = basicPooledConnAdapter.isMarkedReusable();
                if (this.log.isDebugEnabled()) {
                    if (isMarkedReusable) {
                        this.log.debug("Released connection is reusable.");
                    } else {
                        this.log.debug("Released connection is not reusable.");
                    }
                }
                basicPooledConnAdapter.a();
                this.c.freeEntry(basicPoolEntry, isMarkedReusable, j, timeUnit);
            } catch (Throwable e) {
                if (this.log.isDebugEnabled()) {
                    this.log.debug("Exception shutting down released connection.", e);
                }
                isMarkedReusable = basicPooledConnAdapter.isMarkedReusable();
                if (this.log.isDebugEnabled()) {
                    if (isMarkedReusable) {
                        this.log.debug("Released connection is reusable.");
                    } else {
                        this.log.debug("Released connection is not reusable.");
                    }
                }
                basicPooledConnAdapter.a();
                this.c.freeEntry(basicPoolEntry, isMarkedReusable, j, timeUnit);
            } catch (Throwable th) {
                isMarkedReusable = basicPooledConnAdapter.isMarkedReusable();
                if (this.log.isDebugEnabled()) {
                    if (isMarkedReusable) {
                        this.log.debug("Released connection is reusable.");
                    } else {
                        this.log.debug("Released connection is not reusable.");
                    }
                }
                basicPooledConnAdapter.a();
                this.c.freeEntry(basicPoolEntry, isMarkedReusable, j, timeUnit);
            }
        }
    }

    public void shutdown() {
        this.log.debug("Shutting down");
        this.c.shutdown();
    }

    public int getConnectionsInPool(HttpRoute httpRoute) {
        return this.c.getConnectionsInPool(httpRoute);
    }

    public int getConnectionsInPool() {
        return this.c.getConnectionsInPool();
    }

    public void closeIdleConnections(long j, TimeUnit timeUnit) {
        if (this.log.isDebugEnabled()) {
            this.log.debug("Closing connections idle longer than " + j + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + timeUnit);
        }
        this.c.closeIdleConnections(j, timeUnit);
    }

    public void closeExpiredConnections() {
        this.log.debug("Closing expired connections");
        this.c.closeExpiredConnections();
    }

    public int getMaxTotal() {
        return this.c.getMaxTotalConnections();
    }

    public void setMaxTotal(int i) {
        this.c.setMaxTotalConnections(i);
    }

    public int getDefaultMaxPerRoute() {
        return this.e.getDefaultMaxPerRoute();
    }

    public void setDefaultMaxPerRoute(int i) {
        this.e.setDefaultMaxPerRoute(i);
    }

    public int getMaxForRoute(HttpRoute httpRoute) {
        return this.e.getMaxForRoute(httpRoute);
    }

    public void setMaxForRoute(HttpRoute httpRoute, int i) {
        this.e.setMaxForRoute(httpRoute, i);
    }
}
