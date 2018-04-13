package cz.msebera.android.httpclient.impl.conn;

import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import cz.msebera.android.httpclient.HttpClientConnection;
import cz.msebera.android.httpclient.HttpHost;
import cz.msebera.android.httpclient.annotation.ThreadSafe;
import cz.msebera.android.httpclient.config.ConnectionConfig;
import cz.msebera.android.httpclient.config.Registry;
import cz.msebera.android.httpclient.config.RegistryBuilder;
import cz.msebera.android.httpclient.config.SocketConfig;
import cz.msebera.android.httpclient.conn.ConnectionPoolTimeoutException;
import cz.msebera.android.httpclient.conn.ConnectionRequest;
import cz.msebera.android.httpclient.conn.DnsResolver;
import cz.msebera.android.httpclient.conn.HttpClientConnectionManager;
import cz.msebera.android.httpclient.conn.HttpClientConnectionOperator;
import cz.msebera.android.httpclient.conn.HttpConnectionFactory;
import cz.msebera.android.httpclient.conn.ManagedHttpClientConnection;
import cz.msebera.android.httpclient.conn.SchemePortResolver;
import cz.msebera.android.httpclient.conn.routing.HttpRoute;
import cz.msebera.android.httpclient.conn.socket.ConnectionSocketFactory;
import cz.msebera.android.httpclient.conn.socket.PlainConnectionSocketFactory;
import cz.msebera.android.httpclient.conn.ssl.SSLConnectionSocketFactory;
import cz.msebera.android.httpclient.extras.HttpClientAndroidLog;
import cz.msebera.android.httpclient.pool.ConnFactory;
import cz.msebera.android.httpclient.pool.ConnPoolControl;
import cz.msebera.android.httpclient.pool.PoolStats;
import cz.msebera.android.httpclient.protocol.HttpContext;
import cz.msebera.android.httpclient.util.Args;
import cz.msebera.android.httpclient.util.Asserts;
import java.io.Closeable;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicBoolean;

@ThreadSafe
public class PoolingHttpClientConnectionManager implements HttpClientConnectionManager, ConnPoolControl<HttpRoute>, Closeable {
    private final a a;
    private final c b;
    private final HttpClientConnectionOperator c;
    private final AtomicBoolean d;
    public HttpClientAndroidLog log;

    static class a {
        private final Map<HttpHost, SocketConfig> a = new ConcurrentHashMap();
        private final Map<HttpHost, ConnectionConfig> b = new ConcurrentHashMap();
        private volatile SocketConfig c;
        private volatile ConnectionConfig d;

        a() {
        }

        public SocketConfig getDefaultSocketConfig() {
            return this.c;
        }

        public void setDefaultSocketConfig(SocketConfig socketConfig) {
            this.c = socketConfig;
        }

        public ConnectionConfig getDefaultConnectionConfig() {
            return this.d;
        }

        public void setDefaultConnectionConfig(ConnectionConfig connectionConfig) {
            this.d = connectionConfig;
        }

        public SocketConfig getSocketConfig(HttpHost httpHost) {
            return (SocketConfig) this.a.get(httpHost);
        }

        public void setSocketConfig(HttpHost httpHost, SocketConfig socketConfig) {
            this.a.put(httpHost, socketConfig);
        }

        public ConnectionConfig getConnectionConfig(HttpHost httpHost) {
            return (ConnectionConfig) this.b.get(httpHost);
        }

        public void setConnectionConfig(HttpHost httpHost, ConnectionConfig connectionConfig) {
            this.b.put(httpHost, connectionConfig);
        }
    }

    static class b implements ConnFactory<HttpRoute, ManagedHttpClientConnection> {
        private final a a;
        private final HttpConnectionFactory<HttpRoute, ManagedHttpClientConnection> b;

        b(a aVar, HttpConnectionFactory<HttpRoute, ManagedHttpClientConnection> httpConnectionFactory) {
            if (aVar == null) {
                aVar = new a();
            }
            this.a = aVar;
            if (httpConnectionFactory == null) {
                httpConnectionFactory = ManagedHttpClientConnectionFactory.INSTANCE;
            }
            this.b = httpConnectionFactory;
        }

        public ManagedHttpClientConnection create(HttpRoute httpRoute) throws IOException {
            ConnectionConfig connectionConfig = null;
            if (httpRoute.getProxyHost() != null) {
                connectionConfig = this.a.getConnectionConfig(httpRoute.getProxyHost());
            }
            if (connectionConfig == null) {
                connectionConfig = this.a.getConnectionConfig(httpRoute.getTargetHost());
            }
            if (connectionConfig == null) {
                connectionConfig = this.a.getDefaultConnectionConfig();
            }
            if (connectionConfig == null) {
                connectionConfig = ConnectionConfig.DEFAULT;
            }
            return (ManagedHttpClientConnection) this.b.create(httpRoute, connectionConfig);
        }
    }

    private static Registry<ConnectionSocketFactory> a() {
        return RegistryBuilder.create().register("http", PlainConnectionSocketFactory.getSocketFactory()).register("https", SSLConnectionSocketFactory.getSocketFactory()).build();
    }

    public PoolingHttpClientConnectionManager() {
        this(a());
    }

    public PoolingHttpClientConnectionManager(long j, TimeUnit timeUnit) {
        this(a(), null, null, null, j, timeUnit);
    }

    public PoolingHttpClientConnectionManager(Registry<ConnectionSocketFactory> registry) {
        this(registry, null, null);
    }

    public PoolingHttpClientConnectionManager(Registry<ConnectionSocketFactory> registry, DnsResolver dnsResolver) {
        this(registry, null, dnsResolver);
    }

    public PoolingHttpClientConnectionManager(Registry<ConnectionSocketFactory> registry, HttpConnectionFactory<HttpRoute, ManagedHttpClientConnection> httpConnectionFactory) {
        this(registry, httpConnectionFactory, null);
    }

    public PoolingHttpClientConnectionManager(HttpConnectionFactory<HttpRoute, ManagedHttpClientConnection> httpConnectionFactory) {
        this(a(), httpConnectionFactory, null);
    }

    public PoolingHttpClientConnectionManager(Registry<ConnectionSocketFactory> registry, HttpConnectionFactory<HttpRoute, ManagedHttpClientConnection> httpConnectionFactory, DnsResolver dnsResolver) {
        this(registry, httpConnectionFactory, null, dnsResolver, -1, TimeUnit.MILLISECONDS);
    }

    public PoolingHttpClientConnectionManager(Registry<ConnectionSocketFactory> registry, HttpConnectionFactory<HttpRoute, ManagedHttpClientConnection> httpConnectionFactory, SchemePortResolver schemePortResolver, DnsResolver dnsResolver, long j, TimeUnit timeUnit) {
        this(new DefaultHttpClientConnectionOperator(registry, schemePortResolver, dnsResolver), httpConnectionFactory, j, timeUnit);
    }

    public PoolingHttpClientConnectionManager(HttpClientConnectionOperator httpClientConnectionOperator, HttpConnectionFactory<HttpRoute, ManagedHttpClientConnection> httpConnectionFactory, long j, TimeUnit timeUnit) {
        this.log = new HttpClientAndroidLog(getClass());
        this.a = new a();
        this.b = new c(new b(this.a, httpConnectionFactory), 2, 20, j, timeUnit);
        this.b.setValidateAfterInactivity(5000);
        this.c = (HttpClientConnectionOperator) Args.notNull(httpClientConnectionOperator, "HttpClientConnectionOperator");
        this.d = new AtomicBoolean(false);
    }

    protected void finalize() throws Throwable {
        try {
            shutdown();
        } finally {
            super.finalize();
        }
    }

    public void close() {
        shutdown();
    }

    private String a(HttpRoute httpRoute, Object obj) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[route: ").append(httpRoute).append("]");
        if (obj != null) {
            stringBuilder.append("[state: ").append(obj).append("]");
        }
        return stringBuilder.toString();
    }

    private String a(HttpRoute httpRoute) {
        StringBuilder stringBuilder = new StringBuilder();
        PoolStats totalStats = this.b.getTotalStats();
        PoolStats stats = this.b.getStats(httpRoute);
        stringBuilder.append("[total kept alive: ").append(totalStats.getAvailable()).append("; ");
        stringBuilder.append("route allocated: ").append(stats.getLeased() + stats.getAvailable());
        stringBuilder.append(" of ").append(stats.getMax()).append("; ");
        stringBuilder.append("total allocated: ").append(totalStats.getLeased() + totalStats.getAvailable());
        stringBuilder.append(" of ").append(totalStats.getMax()).append("]");
        return stringBuilder.toString();
    }

    private String a(d dVar) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[id: ").append(dVar.getId()).append("]");
        stringBuilder.append("[route: ").append(dVar.getRoute()).append("]");
        Object state = dVar.getState();
        if (state != null) {
            stringBuilder.append("[state: ").append(state).append("]");
        }
        return stringBuilder.toString();
    }

    public ConnectionRequest requestConnection(HttpRoute httpRoute, Object obj) {
        Args.notNull(httpRoute, "HTTP route");
        if (this.log.isDebugEnabled()) {
            this.log.debug("Connection request: " + a(httpRoute, obj) + a(httpRoute));
        }
        return new m(this, this.b.lease(httpRoute, obj, null));
    }

    protected HttpClientConnection a(Future<d> future, long j, TimeUnit timeUnit) throws InterruptedException, ExecutionException, ConnectionPoolTimeoutException {
        try {
            d dVar = (d) future.get(j, timeUnit);
            if (dVar == null || future.isCancelled()) {
                throw new InterruptedException();
            }
            Asserts.check(dVar.getConnection() != null, "Pool entry with no connection");
            if (this.log.isDebugEnabled()) {
                this.log.debug("Connection leased: " + a(dVar) + a((HttpRoute) dVar.getRoute()));
            }
            return e.newProxy(dVar);
        } catch (TimeoutException e) {
            throw new ConnectionPoolTimeoutException("Timeout waiting for connection from pool");
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void releaseConnection(cz.msebera.android.httpclient.HttpClientConnection r16, java.lang.Object r17, long r18, java.util.concurrent.TimeUnit r20) {
        /*
        r15 = this;
        r6 = 1;
        r7 = 0;
        r4 = "Managed connection";
        r0 = r16;
        cz.msebera.android.httpclient.util.Args.notNull(r0, r4);
        monitor-enter(r16);
        r8 = cz.msebera.android.httpclient.impl.conn.e.detach(r16);	 Catch:{ all -> 0x00ce }
        if (r8 != 0) goto L_0x0012;
    L_0x0010:
        monitor-exit(r16);	 Catch:{ all -> 0x00ce }
    L_0x0011:
        return;
    L_0x0012:
        r4 = r8.getConnection();	 Catch:{ all -> 0x00ce }
        r4 = (cz.msebera.android.httpclient.conn.ManagedHttpClientConnection) r4;	 Catch:{ all -> 0x00ce }
        r5 = r4.isOpen();	 Catch:{ all -> 0x00da }
        if (r5 == 0) goto L_0x0088;
    L_0x001e:
        if (r20 == 0) goto L_0x00d1;
    L_0x0020:
        r0 = r17;
        r8.setState(r0);	 Catch:{ all -> 0x00da }
        r0 = r18;
        r2 = r20;
        r8.updateExpiry(r0, r2);	 Catch:{ all -> 0x00da }
        r5 = r15.log;	 Catch:{ all -> 0x00da }
        r5 = r5.isDebugEnabled();	 Catch:{ all -> 0x00da }
        if (r5 == 0) goto L_0x0088;
    L_0x0034:
        r10 = 0;
        r5 = (r18 > r10 ? 1 : (r18 == r10 ? 0 : -1));
        if (r5 <= 0) goto L_0x00d5;
    L_0x003a:
        r5 = new java.lang.StringBuilder;	 Catch:{ all -> 0x00da }
        r5.<init>();	 Catch:{ all -> 0x00da }
        r9 = "for ";
        r5 = r5.append(r9);	 Catch:{ all -> 0x00da }
        r0 = r20;
        r1 = r18;
        r10 = r0.toMillis(r1);	 Catch:{ all -> 0x00da }
        r10 = (double) r10;	 Catch:{ all -> 0x00da }
        r12 = 4652007308841189376; // 0x408f400000000000 float:0.0 double:1000.0;
        r10 = r10 / r12;
        r5 = r5.append(r10);	 Catch:{ all -> 0x00da }
        r9 = " seconds";
        r5 = r5.append(r9);	 Catch:{ all -> 0x00da }
        r5 = r5.toString();	 Catch:{ all -> 0x00da }
    L_0x0062:
        r9 = r15.log;	 Catch:{ all -> 0x00da }
        r10 = new java.lang.StringBuilder;	 Catch:{ all -> 0x00da }
        r10.<init>();	 Catch:{ all -> 0x00da }
        r11 = "Connection ";
        r10 = r10.append(r11);	 Catch:{ all -> 0x00da }
        r11 = r15.a(r8);	 Catch:{ all -> 0x00da }
        r10 = r10.append(r11);	 Catch:{ all -> 0x00da }
        r11 = " can be kept alive ";
        r10 = r10.append(r11);	 Catch:{ all -> 0x00da }
        r5 = r10.append(r5);	 Catch:{ all -> 0x00da }
        r5 = r5.toString();	 Catch:{ all -> 0x00da }
        r9.debug(r5);	 Catch:{ all -> 0x00da }
    L_0x0088:
        r5 = r15.b;	 Catch:{ all -> 0x00ce }
        r4 = r4.isOpen();	 Catch:{ all -> 0x00ce }
        if (r4 == 0) goto L_0x00d8;
    L_0x0090:
        r4 = r8.isRouteComplete();	 Catch:{ all -> 0x00ce }
        if (r4 == 0) goto L_0x00d8;
    L_0x0096:
        r5.release(r8, r6);	 Catch:{ all -> 0x00ce }
        r4 = r15.log;	 Catch:{ all -> 0x00ce }
        r4 = r4.isDebugEnabled();	 Catch:{ all -> 0x00ce }
        if (r4 == 0) goto L_0x00cb;
    L_0x00a1:
        r5 = r15.log;	 Catch:{ all -> 0x00ce }
        r4 = new java.lang.StringBuilder;	 Catch:{ all -> 0x00ce }
        r4.<init>();	 Catch:{ all -> 0x00ce }
        r6 = "Connection released: ";
        r4 = r4.append(r6);	 Catch:{ all -> 0x00ce }
        r6 = r15.a(r8);	 Catch:{ all -> 0x00ce }
        r6 = r4.append(r6);	 Catch:{ all -> 0x00ce }
        r4 = r8.getRoute();	 Catch:{ all -> 0x00ce }
        r4 = (cz.msebera.android.httpclient.conn.routing.HttpRoute) r4;	 Catch:{ all -> 0x00ce }
        r4 = r15.a(r4);	 Catch:{ all -> 0x00ce }
        r4 = r6.append(r4);	 Catch:{ all -> 0x00ce }
        r4 = r4.toString();	 Catch:{ all -> 0x00ce }
        r5.debug(r4);	 Catch:{ all -> 0x00ce }
    L_0x00cb:
        monitor-exit(r16);	 Catch:{ all -> 0x00ce }
        goto L_0x0011;
    L_0x00ce:
        r4 = move-exception;
        monitor-exit(r16);	 Catch:{ all -> 0x00ce }
        throw r4;
    L_0x00d1:
        r20 = java.util.concurrent.TimeUnit.MILLISECONDS;	 Catch:{ all -> 0x00da }
        goto L_0x0020;
    L_0x00d5:
        r5 = "indefinitely";
        goto L_0x0062;
    L_0x00d8:
        r6 = r7;
        goto L_0x0096;
    L_0x00da:
        r5 = move-exception;
        r9 = r15.b;	 Catch:{ all -> 0x00ce }
        r4 = r4.isOpen();	 Catch:{ all -> 0x00ce }
        if (r4 == 0) goto L_0x0120;
    L_0x00e3:
        r4 = r8.isRouteComplete();	 Catch:{ all -> 0x00ce }
        if (r4 == 0) goto L_0x0120;
    L_0x00e9:
        r4 = r6;
    L_0x00ea:
        r9.release(r8, r4);	 Catch:{ all -> 0x00ce }
        r4 = r15.log;	 Catch:{ all -> 0x00ce }
        r4 = r4.isDebugEnabled();	 Catch:{ all -> 0x00ce }
        if (r4 == 0) goto L_0x011f;
    L_0x00f5:
        r6 = r15.log;	 Catch:{ all -> 0x00ce }
        r4 = new java.lang.StringBuilder;	 Catch:{ all -> 0x00ce }
        r4.<init>();	 Catch:{ all -> 0x00ce }
        r7 = "Connection released: ";
        r4 = r4.append(r7);	 Catch:{ all -> 0x00ce }
        r7 = r15.a(r8);	 Catch:{ all -> 0x00ce }
        r7 = r4.append(r7);	 Catch:{ all -> 0x00ce }
        r4 = r8.getRoute();	 Catch:{ all -> 0x00ce }
        r4 = (cz.msebera.android.httpclient.conn.routing.HttpRoute) r4;	 Catch:{ all -> 0x00ce }
        r4 = r15.a(r4);	 Catch:{ all -> 0x00ce }
        r4 = r7.append(r4);	 Catch:{ all -> 0x00ce }
        r4 = r4.toString();	 Catch:{ all -> 0x00ce }
        r6.debug(r4);	 Catch:{ all -> 0x00ce }
    L_0x011f:
        throw r5;	 Catch:{ all -> 0x00ce }
    L_0x0120:
        r4 = r7;
        goto L_0x00ea;
        */
        throw new UnsupportedOperationException("Method not decompiled: cz.msebera.android.httpclient.impl.conn.PoolingHttpClientConnectionManager.releaseConnection(cz.msebera.android.httpclient.HttpClientConnection, java.lang.Object, long, java.util.concurrent.TimeUnit):void");
    }

    public void connect(HttpClientConnection httpClientConnection, HttpRoute httpRoute, int i, HttpContext httpContext) throws IOException {
        ManagedHttpClientConnection managedHttpClientConnection;
        HttpHost proxyHost;
        Args.notNull(httpClientConnection, "Managed Connection");
        Args.notNull(httpRoute, "HTTP route");
        synchronized (httpClientConnection) {
            managedHttpClientConnection = (ManagedHttpClientConnection) e.getPoolEntry(httpClientConnection).getConnection();
        }
        if (httpRoute.getProxyHost() != null) {
            proxyHost = httpRoute.getProxyHost();
        } else {
            proxyHost = httpRoute.getTargetHost();
        }
        InetSocketAddress localSocketAddress = httpRoute.getLocalSocketAddress();
        SocketConfig socketConfig = this.a.getSocketConfig(proxyHost);
        if (socketConfig == null) {
            socketConfig = this.a.getDefaultSocketConfig();
        }
        if (socketConfig == null) {
            socketConfig = SocketConfig.DEFAULT;
        }
        this.c.connect(managedHttpClientConnection, proxyHost, localSocketAddress, i, socketConfig, httpContext);
    }

    public void upgrade(HttpClientConnection httpClientConnection, HttpRoute httpRoute, HttpContext httpContext) throws IOException {
        ManagedHttpClientConnection managedHttpClientConnection;
        Args.notNull(httpClientConnection, "Managed Connection");
        Args.notNull(httpRoute, "HTTP route");
        synchronized (httpClientConnection) {
            managedHttpClientConnection = (ManagedHttpClientConnection) e.getPoolEntry(httpClientConnection).getConnection();
        }
        this.c.upgrade(managedHttpClientConnection, httpRoute.getTargetHost(), httpContext);
    }

    public void routeComplete(HttpClientConnection httpClientConnection, HttpRoute httpRoute, HttpContext httpContext) throws IOException {
        Args.notNull(httpClientConnection, "Managed Connection");
        Args.notNull(httpRoute, "HTTP route");
        synchronized (httpClientConnection) {
            e.getPoolEntry(httpClientConnection).markRouteComplete();
        }
    }

    public void shutdown() {
        if (this.d.compareAndSet(false, true)) {
            this.log.debug("Connection manager is shutting down");
            try {
                this.b.shutdown();
            } catch (Throwable e) {
                this.log.debug("I/O exception shutting down connection manager", e);
            }
            this.log.debug("Connection manager shut down");
        }
    }

    public void closeIdleConnections(long j, TimeUnit timeUnit) {
        if (this.log.isDebugEnabled()) {
            this.log.debug("Closing connections idle longer than " + j + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + timeUnit);
        }
        this.b.closeIdle(j, timeUnit);
    }

    public void closeExpiredConnections() {
        this.log.debug("Closing expired connections");
        this.b.closeExpired();
    }

    public int getMaxTotal() {
        return this.b.getMaxTotal();
    }

    public void setMaxTotal(int i) {
        this.b.setMaxTotal(i);
    }

    public int getDefaultMaxPerRoute() {
        return this.b.getDefaultMaxPerRoute();
    }

    public void setDefaultMaxPerRoute(int i) {
        this.b.setDefaultMaxPerRoute(i);
    }

    public int getMaxPerRoute(HttpRoute httpRoute) {
        return this.b.getMaxPerRoute(httpRoute);
    }

    public void setMaxPerRoute(HttpRoute httpRoute, int i) {
        this.b.setMaxPerRoute(httpRoute, i);
    }

    public PoolStats getTotalStats() {
        return this.b.getTotalStats();
    }

    public PoolStats getStats(HttpRoute httpRoute) {
        return this.b.getStats(httpRoute);
    }

    public Set<HttpRoute> getRoutes() {
        return this.b.getRoutes();
    }

    public SocketConfig getDefaultSocketConfig() {
        return this.a.getDefaultSocketConfig();
    }

    public void setDefaultSocketConfig(SocketConfig socketConfig) {
        this.a.setDefaultSocketConfig(socketConfig);
    }

    public ConnectionConfig getDefaultConnectionConfig() {
        return this.a.getDefaultConnectionConfig();
    }

    public void setDefaultConnectionConfig(ConnectionConfig connectionConfig) {
        this.a.setDefaultConnectionConfig(connectionConfig);
    }

    public SocketConfig getSocketConfig(HttpHost httpHost) {
        return this.a.getSocketConfig(httpHost);
    }

    public void setSocketConfig(HttpHost httpHost, SocketConfig socketConfig) {
        this.a.setSocketConfig(httpHost, socketConfig);
    }

    public ConnectionConfig getConnectionConfig(HttpHost httpHost) {
        return this.a.getConnectionConfig(httpHost);
    }

    public void setConnectionConfig(HttpHost httpHost, ConnectionConfig connectionConfig) {
        this.a.setConnectionConfig(httpHost, connectionConfig);
    }

    public int getValidateAfterInactivity() {
        return this.b.getValidateAfterInactivity();
    }

    public void setValidateAfterInactivity(int i) {
        this.b.setValidateAfterInactivity(i);
    }
}
