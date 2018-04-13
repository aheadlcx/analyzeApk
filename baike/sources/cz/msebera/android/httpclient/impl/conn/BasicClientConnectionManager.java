package cz.msebera.android.httpclient.impl.conn;

import cz.msebera.android.httpclient.HttpClientConnection;
import cz.msebera.android.httpclient.annotation.GuardedBy;
import cz.msebera.android.httpclient.annotation.ThreadSafe;
import cz.msebera.android.httpclient.conn.ClientConnectionManager;
import cz.msebera.android.httpclient.conn.ClientConnectionOperator;
import cz.msebera.android.httpclient.conn.ClientConnectionRequest;
import cz.msebera.android.httpclient.conn.ManagedClientConnection;
import cz.msebera.android.httpclient.conn.routing.HttpRoute;
import cz.msebera.android.httpclient.conn.scheme.SchemeRegistry;
import cz.msebera.android.httpclient.extras.HttpClientAndroidLog;
import cz.msebera.android.httpclient.util.Args;
import cz.msebera.android.httpclient.util.Asserts;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

@ThreadSafe
@Deprecated
public class BasicClientConnectionManager implements ClientConnectionManager {
    public static final String MISUSE_MESSAGE = "Invalid use of BasicClientConnManager: connection still allocated.\nMake sure to release the connection before allocating another one.";
    private static final AtomicLong a = new AtomicLong();
    private final SchemeRegistry b;
    private final ClientConnectionOperator c;
    @GuardedBy("this")
    private g d;
    @GuardedBy("this")
    private k e;
    @GuardedBy("this")
    private volatile boolean f;
    public HttpClientAndroidLog log;

    public BasicClientConnectionManager(SchemeRegistry schemeRegistry) {
        this.log = new HttpClientAndroidLog(getClass());
        Args.notNull(schemeRegistry, "Scheme registry");
        this.b = schemeRegistry;
        this.c = a(schemeRegistry);
    }

    public BasicClientConnectionManager() {
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
        return this.b;
    }

    protected ClientConnectionOperator a(SchemeRegistry schemeRegistry) {
        return new DefaultClientConnectionOperator(schemeRegistry);
    }

    public final ClientConnectionRequest requestConnection(HttpRoute httpRoute, Object obj) {
        return new a(this, httpRoute, obj);
    }

    private void a() {
        Asserts.check(!this.f, "Connection manager has been shut down");
    }

    ManagedClientConnection a(HttpRoute httpRoute, Object obj) {
        ManagedClientConnection managedClientConnection;
        Args.notNull(httpRoute, "Route");
        synchronized (this) {
            a();
            if (this.log.isDebugEnabled()) {
                this.log.debug("Get connection for route " + httpRoute);
            }
            Asserts.check(this.e == null, MISUSE_MESSAGE);
            if (!(this.d == null || this.d.b().equals(httpRoute))) {
                this.d.close();
                this.d = null;
            }
            if (this.d == null) {
                this.d = new g(this.log, Long.toString(a.getAndIncrement()), httpRoute, this.c.createConnection(), 0, TimeUnit.MILLISECONDS);
            }
            if (this.d.isExpired(System.currentTimeMillis())) {
                this.d.close();
                this.d.a().reset();
            }
            this.e = new k(this, this.c, this.d);
            managedClientConnection = this.e;
        }
        return managedClientConnection;
    }

    private void a(HttpClientConnection httpClientConnection) {
        try {
            httpClientConnection.shutdown();
        } catch (Throwable e) {
            if (this.log.isDebugEnabled()) {
                this.log.debug("I/O exception shutting down connection", e);
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void releaseConnection(cz.msebera.android.httpclient.conn.ManagedClientConnection r7, long r8, java.util.concurrent.TimeUnit r10) {
        /*
        r6 = this;
        r0 = r7 instanceof cz.msebera.android.httpclient.impl.conn.k;
        r1 = "Connection class mismatch, connection not obtained from this manager";
        cz.msebera.android.httpclient.util.Args.check(r0, r1);
        r0 = r7;
        r0 = (cz.msebera.android.httpclient.impl.conn.k) r0;
        monitor-enter(r0);
        r1 = r6.log;	 Catch:{ all -> 0x004a }
        r1 = r1.isDebugEnabled();	 Catch:{ all -> 0x004a }
        if (r1 == 0) goto L_0x002b;
    L_0x0013:
        r1 = r6.log;	 Catch:{ all -> 0x004a }
        r2 = new java.lang.StringBuilder;	 Catch:{ all -> 0x004a }
        r2.<init>();	 Catch:{ all -> 0x004a }
        r3 = "Releasing connection ";
        r2 = r2.append(r3);	 Catch:{ all -> 0x004a }
        r2 = r2.append(r7);	 Catch:{ all -> 0x004a }
        r2 = r2.toString();	 Catch:{ all -> 0x004a }
        r1.debug(r2);	 Catch:{ all -> 0x004a }
    L_0x002b:
        r1 = r0.a();	 Catch:{ all -> 0x004a }
        if (r1 != 0) goto L_0x0033;
    L_0x0031:
        monitor-exit(r0);	 Catch:{ all -> 0x004a }
    L_0x0032:
        return;
    L_0x0033:
        r1 = r0.getManager();	 Catch:{ all -> 0x004a }
        if (r1 != r6) goto L_0x004d;
    L_0x0039:
        r1 = 1;
    L_0x003a:
        r2 = "Connection not obtained from this manager";
        cz.msebera.android.httpclient.util.Asserts.check(r1, r2);	 Catch:{ all -> 0x004a }
        monitor-enter(r6);	 Catch:{ all -> 0x004a }
        r1 = r6.f;	 Catch:{ all -> 0x00dd }
        if (r1 == 0) goto L_0x004f;
    L_0x0044:
        r6.a(r0);	 Catch:{ all -> 0x00dd }
        monitor-exit(r6);	 Catch:{ all -> 0x00dd }
        monitor-exit(r0);	 Catch:{ all -> 0x004a }
        goto L_0x0032;
    L_0x004a:
        r1 = move-exception;
        monitor-exit(r0);	 Catch:{ all -> 0x004a }
        throw r1;
    L_0x004d:
        r1 = 0;
        goto L_0x003a;
    L_0x004f:
        r1 = r0.isOpen();	 Catch:{ all -> 0x00ca }
        if (r1 == 0) goto L_0x005e;
    L_0x0055:
        r1 = r0.isMarkedReusable();	 Catch:{ all -> 0x00ca }
        if (r1 != 0) goto L_0x005e;
    L_0x005b:
        r6.a(r0);	 Catch:{ all -> 0x00ca }
    L_0x005e:
        r1 = r0.isMarkedReusable();	 Catch:{ all -> 0x00ca }
        if (r1 == 0) goto L_0x00af;
    L_0x0064:
        r2 = r6.d;	 Catch:{ all -> 0x00ca }
        if (r10 == 0) goto L_0x00c4;
    L_0x0068:
        r1 = r10;
    L_0x0069:
        r2.updateExpiry(r8, r1);	 Catch:{ all -> 0x00ca }
        r1 = r6.log;	 Catch:{ all -> 0x00ca }
        r1 = r1.isDebugEnabled();	 Catch:{ all -> 0x00ca }
        if (r1 == 0) goto L_0x00af;
    L_0x0074:
        r2 = 0;
        r1 = (r8 > r2 ? 1 : (r8 == r2 ? 0 : -1));
        if (r1 <= 0) goto L_0x00c7;
    L_0x007a:
        r1 = new java.lang.StringBuilder;	 Catch:{ all -> 0x00ca }
        r1.<init>();	 Catch:{ all -> 0x00ca }
        r2 = "for ";
        r1 = r1.append(r2);	 Catch:{ all -> 0x00ca }
        r1 = r1.append(r8);	 Catch:{ all -> 0x00ca }
        r2 = " ";
        r1 = r1.append(r2);	 Catch:{ all -> 0x00ca }
        r1 = r1.append(r10);	 Catch:{ all -> 0x00ca }
        r1 = r1.toString();	 Catch:{ all -> 0x00ca }
    L_0x0097:
        r2 = r6.log;	 Catch:{ all -> 0x00ca }
        r3 = new java.lang.StringBuilder;	 Catch:{ all -> 0x00ca }
        r3.<init>();	 Catch:{ all -> 0x00ca }
        r4 = "Connection can be kept alive ";
        r3 = r3.append(r4);	 Catch:{ all -> 0x00ca }
        r1 = r3.append(r1);	 Catch:{ all -> 0x00ca }
        r1 = r1.toString();	 Catch:{ all -> 0x00ca }
        r2.debug(r1);	 Catch:{ all -> 0x00ca }
    L_0x00af:
        r0.b();	 Catch:{ all -> 0x00dd }
        r1 = 0;
        r6.e = r1;	 Catch:{ all -> 0x00dd }
        r1 = r6.d;	 Catch:{ all -> 0x00dd }
        r1 = r1.isClosed();	 Catch:{ all -> 0x00dd }
        if (r1 == 0) goto L_0x00c0;
    L_0x00bd:
        r1 = 0;
        r6.d = r1;	 Catch:{ all -> 0x00dd }
    L_0x00c0:
        monitor-exit(r6);	 Catch:{ all -> 0x00dd }
        monitor-exit(r0);	 Catch:{ all -> 0x004a }
        goto L_0x0032;
    L_0x00c4:
        r1 = java.util.concurrent.TimeUnit.MILLISECONDS;	 Catch:{ all -> 0x00ca }
        goto L_0x0069;
    L_0x00c7:
        r1 = "indefinitely";
        goto L_0x0097;
    L_0x00ca:
        r1 = move-exception;
        r0.b();	 Catch:{ all -> 0x00dd }
        r2 = 0;
        r6.e = r2;	 Catch:{ all -> 0x00dd }
        r2 = r6.d;	 Catch:{ all -> 0x00dd }
        r2 = r2.isClosed();	 Catch:{ all -> 0x00dd }
        if (r2 == 0) goto L_0x00dc;
    L_0x00d9:
        r2 = 0;
        r6.d = r2;	 Catch:{ all -> 0x00dd }
    L_0x00dc:
        throw r1;	 Catch:{ all -> 0x00dd }
    L_0x00dd:
        r1 = move-exception;
        monitor-exit(r6);	 Catch:{ all -> 0x00dd }
        throw r1;	 Catch:{ all -> 0x004a }
        */
        throw new UnsupportedOperationException("Method not decompiled: cz.msebera.android.httpclient.impl.conn.BasicClientConnectionManager.releaseConnection(cz.msebera.android.httpclient.conn.ManagedClientConnection, long, java.util.concurrent.TimeUnit):void");
    }

    public void closeExpiredConnections() {
        synchronized (this) {
            a();
            long currentTimeMillis = System.currentTimeMillis();
            if (this.d != null && this.d.isExpired(currentTimeMillis)) {
                this.d.close();
                this.d.a().reset();
            }
        }
    }

    public void closeIdleConnections(long j, TimeUnit timeUnit) {
        long j2 = 0;
        Args.notNull(timeUnit, "Time unit");
        synchronized (this) {
            a();
            long toMillis = timeUnit.toMillis(j);
            if (toMillis >= 0) {
                j2 = toMillis;
            }
            j2 = System.currentTimeMillis() - j2;
            if (this.d != null && this.d.getUpdated() <= j2) {
                this.d.close();
                this.d.a().reset();
            }
        }
    }

    public void shutdown() {
        synchronized (this) {
            this.f = true;
            try {
                if (this.d != null) {
                    this.d.close();
                }
                this.d = null;
                this.e = null;
            } catch (Throwable th) {
                this.d = null;
                this.e = null;
            }
        }
    }
}
