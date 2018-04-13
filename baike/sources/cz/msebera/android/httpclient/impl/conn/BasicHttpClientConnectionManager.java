package cz.msebera.android.httpclient.impl.conn;

import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import cz.msebera.android.httpclient.HttpClientConnection;
import cz.msebera.android.httpclient.HttpHost;
import cz.msebera.android.httpclient.annotation.GuardedBy;
import cz.msebera.android.httpclient.annotation.ThreadSafe;
import cz.msebera.android.httpclient.config.ConnectionConfig;
import cz.msebera.android.httpclient.config.Lookup;
import cz.msebera.android.httpclient.config.Registry;
import cz.msebera.android.httpclient.config.RegistryBuilder;
import cz.msebera.android.httpclient.config.SocketConfig;
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
import cz.msebera.android.httpclient.protocol.HttpContext;
import cz.msebera.android.httpclient.util.Args;
import cz.msebera.android.httpclient.util.Asserts;
import cz.msebera.android.httpclient.util.LangUtils;
import java.io.Closeable;
import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

@ThreadSafe
public class BasicHttpClientConnectionManager implements HttpClientConnectionManager, Closeable {
    private final HttpClientConnectionOperator a;
    private final HttpConnectionFactory<HttpRoute, ManagedHttpClientConnection> b;
    @GuardedBy("this")
    private ManagedHttpClientConnection c;
    @GuardedBy("this")
    private HttpRoute d;
    @GuardedBy("this")
    private Object e;
    @GuardedBy("this")
    private long f;
    @GuardedBy("this")
    private long g;
    @GuardedBy("this")
    private boolean h;
    @GuardedBy("this")
    private SocketConfig i;
    @GuardedBy("this")
    private ConnectionConfig j;
    private final AtomicBoolean k;
    public HttpClientAndroidLog log;

    private static Registry<ConnectionSocketFactory> a() {
        return RegistryBuilder.create().register("http", PlainConnectionSocketFactory.getSocketFactory()).register("https", SSLConnectionSocketFactory.getSocketFactory()).build();
    }

    public BasicHttpClientConnectionManager(Lookup<ConnectionSocketFactory> lookup, HttpConnectionFactory<HttpRoute, ManagedHttpClientConnection> httpConnectionFactory, SchemePortResolver schemePortResolver, DnsResolver dnsResolver) {
        this(new DefaultHttpClientConnectionOperator(lookup, schemePortResolver, dnsResolver), (HttpConnectionFactory) httpConnectionFactory);
    }

    public BasicHttpClientConnectionManager(HttpClientConnectionOperator httpClientConnectionOperator, HttpConnectionFactory<HttpRoute, ManagedHttpClientConnection> httpConnectionFactory) {
        this.log = new HttpClientAndroidLog(getClass());
        this.a = (HttpClientConnectionOperator) Args.notNull(httpClientConnectionOperator, "Connection operator");
        if (httpConnectionFactory == null) {
            httpConnectionFactory = ManagedHttpClientConnectionFactory.INSTANCE;
        }
        this.b = httpConnectionFactory;
        this.g = Long.MAX_VALUE;
        this.i = SocketConfig.DEFAULT;
        this.j = ConnectionConfig.DEFAULT;
        this.k = new AtomicBoolean(false);
    }

    public BasicHttpClientConnectionManager(Lookup<ConnectionSocketFactory> lookup, HttpConnectionFactory<HttpRoute, ManagedHttpClientConnection> httpConnectionFactory) {
        this(lookup, httpConnectionFactory, null, null);
    }

    public BasicHttpClientConnectionManager(Lookup<ConnectionSocketFactory> lookup) {
        this(lookup, null, null, null);
    }

    public BasicHttpClientConnectionManager() {
        this(a(), null, null, null);
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

    public synchronized SocketConfig getSocketConfig() {
        return this.i;
    }

    public synchronized void setSocketConfig(SocketConfig socketConfig) {
        if (socketConfig == null) {
            socketConfig = SocketConfig.DEFAULT;
        }
        this.i = socketConfig;
    }

    public synchronized ConnectionConfig getConnectionConfig() {
        return this.j;
    }

    public synchronized void setConnectionConfig(ConnectionConfig connectionConfig) {
        if (connectionConfig == null) {
            connectionConfig = ConnectionConfig.DEFAULT;
        }
        this.j = connectionConfig;
    }

    public final ConnectionRequest requestConnection(HttpRoute httpRoute, Object obj) {
        Args.notNull(httpRoute, "Route");
        return new b(this, httpRoute, obj);
    }

    private void b() {
        if (this.c != null) {
            this.log.debug("Closing connection");
            try {
                this.c.close();
            } catch (Throwable e) {
                if (this.log.isDebugEnabled()) {
                    this.log.debug("I/O exception closing connection", e);
                }
            }
            this.c = null;
        }
    }

    private void c() {
        if (this.c != null) {
            this.log.debug("Shutting down connection");
            try {
                this.c.shutdown();
            } catch (Throwable e) {
                if (this.log.isDebugEnabled()) {
                    this.log.debug("I/O exception shutting down connection", e);
                }
            }
            this.c = null;
        }
    }

    private void d() {
        if (this.c != null && System.currentTimeMillis() >= this.g) {
            if (this.log.isDebugEnabled()) {
                this.log.debug("Connection expired @ " + new Date(this.g));
            }
            b();
        }
    }

    synchronized HttpClientConnection a(HttpRoute httpRoute, Object obj) {
        HttpClientConnection httpClientConnection;
        boolean z = true;
        synchronized (this) {
            Asserts.check(!this.k.get(), "Connection manager has been shut down");
            if (this.log.isDebugEnabled()) {
                this.log.debug("Get connection for route " + httpRoute);
            }
            if (this.h) {
                z = false;
            }
            Asserts.check(z, "Connection is still allocated");
            if (!(LangUtils.equals(this.d, (Object) httpRoute) && LangUtils.equals(this.e, obj))) {
                b();
            }
            this.d = httpRoute;
            this.e = obj;
            d();
            if (this.c == null) {
                this.c = (ManagedHttpClientConnection) this.b.create(httpRoute, this.j);
            }
            this.h = true;
            httpClientConnection = this.c;
        }
        return httpClientConnection;
    }

    public synchronized void releaseConnection(HttpClientConnection httpClientConnection, Object obj, long j, TimeUnit timeUnit) {
        boolean z = false;
        synchronized (this) {
            Args.notNull(httpClientConnection, "Connection");
            if (httpClientConnection == this.c) {
                z = true;
            }
            Asserts.check(z, "Connection not obtained from this manager");
            if (this.log.isDebugEnabled()) {
                this.log.debug("Releasing connection " + httpClientConnection);
            }
            if (!this.k.get()) {
                try {
                    this.f = System.currentTimeMillis();
                    ManagedHttpClientConnection isOpen = this.c.isOpen();
                    if (isOpen == null) {
                        this.c = isOpen;
                        this.d = null;
                        this.c = null;
                        this.g = Long.MAX_VALUE;
                        this.h = false;
                    } else {
                        this.e = obj;
                        if (this.log.isDebugEnabled()) {
                            String str;
                            if (j > 0) {
                                str = "for " + j + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + timeUnit;
                            } else {
                                str = "indefinitely";
                            }
                            this.log.debug("Connection can be kept alive " + str);
                        }
                        if (j > 0) {
                            this.g = this.f + timeUnit.toMillis(j);
                        } else {
                            this.g = Long.MAX_VALUE;
                        }
                        this.h = false;
                    }
                } finally {
                    this.h = false;
                }
            }
        }
    }

    public void connect(HttpClientConnection httpClientConnection, HttpRoute httpRoute, int i, HttpContext httpContext) throws IOException {
        HttpHost proxyHost;
        Args.notNull(httpClientConnection, "Connection");
        Args.notNull(httpRoute, "HTTP route");
        Asserts.check(httpClientConnection == this.c, "Connection not obtained from this manager");
        if (httpRoute.getProxyHost() != null) {
            proxyHost = httpRoute.getProxyHost();
        } else {
            proxyHost = httpRoute.getTargetHost();
        }
        this.a.connect(this.c, proxyHost, httpRoute.getLocalSocketAddress(), i, this.i, httpContext);
    }

    public void upgrade(HttpClientConnection httpClientConnection, HttpRoute httpRoute, HttpContext httpContext) throws IOException {
        Args.notNull(httpClientConnection, "Connection");
        Args.notNull(httpRoute, "HTTP route");
        Asserts.check(httpClientConnection == this.c, "Connection not obtained from this manager");
        this.a.upgrade(this.c, httpRoute.getTargetHost(), httpContext);
    }

    public void routeComplete(HttpClientConnection httpClientConnection, HttpRoute httpRoute, HttpContext httpContext) throws IOException {
    }

    public synchronized void closeExpiredConnections() {
        if (!this.k.get()) {
            if (!this.h) {
                d();
            }
        }
    }

    public synchronized void closeIdleConnections(long j, TimeUnit timeUnit) {
        long j2 = 0;
        synchronized (this) {
            Args.notNull(timeUnit, "Time unit");
            if (!this.k.get()) {
                if (!this.h) {
                    long toMillis = timeUnit.toMillis(j);
                    if (toMillis >= 0) {
                        j2 = toMillis;
                    }
                    if (this.f <= System.currentTimeMillis() - j2) {
                        b();
                    }
                }
            }
        }
    }

    public synchronized void shutdown() {
        if (this.k.compareAndSet(false, true)) {
            c();
        }
    }
}
