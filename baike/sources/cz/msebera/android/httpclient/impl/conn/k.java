package cz.msebera.android.httpclient.impl.conn;

import cz.msebera.android.httpclient.HttpConnectionMetrics;
import cz.msebera.android.httpclient.HttpEntityEnclosingRequest;
import cz.msebera.android.httpclient.HttpException;
import cz.msebera.android.httpclient.HttpHost;
import cz.msebera.android.httpclient.HttpRequest;
import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.annotation.NotThreadSafe;
import cz.msebera.android.httpclient.conn.ClientConnectionManager;
import cz.msebera.android.httpclient.conn.ClientConnectionOperator;
import cz.msebera.android.httpclient.conn.ManagedClientConnection;
import cz.msebera.android.httpclient.conn.OperatedClientConnection;
import cz.msebera.android.httpclient.conn.routing.HttpRoute;
import cz.msebera.android.httpclient.conn.routing.RouteTracker;
import cz.msebera.android.httpclient.params.HttpParams;
import cz.msebera.android.httpclient.protocol.HttpContext;
import cz.msebera.android.httpclient.util.Args;
import cz.msebera.android.httpclient.util.Asserts;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;

@NotThreadSafe
@Deprecated
class k implements ManagedClientConnection {
    private final ClientConnectionManager a;
    private final ClientConnectionOperator b;
    private volatile g c;
    private volatile boolean d = false;
    private volatile long e = Long.MAX_VALUE;

    k(ClientConnectionManager clientConnectionManager, ClientConnectionOperator clientConnectionOperator, g gVar) {
        Args.notNull(clientConnectionManager, "Connection manager");
        Args.notNull(clientConnectionOperator, "Connection operator");
        Args.notNull(gVar, "HTTP pool entry");
        this.a = clientConnectionManager;
        this.b = clientConnectionOperator;
        this.c = gVar;
    }

    public String getId() {
        return null;
    }

    g a() {
        return this.c;
    }

    g b() {
        g gVar = this.c;
        this.c = null;
        return gVar;
    }

    public ClientConnectionManager getManager() {
        return this.a;
    }

    private OperatedClientConnection c() {
        g gVar = this.c;
        if (gVar == null) {
            return null;
        }
        return (OperatedClientConnection) gVar.getConnection();
    }

    private OperatedClientConnection d() {
        g gVar = this.c;
        if (gVar != null) {
            return (OperatedClientConnection) gVar.getConnection();
        }
        throw new ConnectionShutdownException();
    }

    private g e() {
        g gVar = this.c;
        if (gVar != null) {
            return gVar;
        }
        throw new ConnectionShutdownException();
    }

    public void close() throws IOException {
        g gVar = this.c;
        if (gVar != null) {
            OperatedClientConnection operatedClientConnection = (OperatedClientConnection) gVar.getConnection();
            gVar.a().reset();
            operatedClientConnection.close();
        }
    }

    public void shutdown() throws IOException {
        g gVar = this.c;
        if (gVar != null) {
            OperatedClientConnection operatedClientConnection = (OperatedClientConnection) gVar.getConnection();
            gVar.a().reset();
            operatedClientConnection.shutdown();
        }
    }

    public boolean isOpen() {
        OperatedClientConnection c = c();
        if (c != null) {
            return c.isOpen();
        }
        return false;
    }

    public boolean isStale() {
        OperatedClientConnection c = c();
        if (c != null) {
            return c.isStale();
        }
        return true;
    }

    public void setSocketTimeout(int i) {
        d().setSocketTimeout(i);
    }

    public int getSocketTimeout() {
        return d().getSocketTimeout();
    }

    public HttpConnectionMetrics getMetrics() {
        return d().getMetrics();
    }

    public void flush() throws IOException {
        d().flush();
    }

    public boolean isResponseAvailable(int i) throws IOException {
        return d().isResponseAvailable(i);
    }

    public void receiveResponseEntity(HttpResponse httpResponse) throws HttpException, IOException {
        d().receiveResponseEntity(httpResponse);
    }

    public HttpResponse receiveResponseHeader() throws HttpException, IOException {
        return d().receiveResponseHeader();
    }

    public void sendRequestEntity(HttpEntityEnclosingRequest httpEntityEnclosingRequest) throws HttpException, IOException {
        d().sendRequestEntity(httpEntityEnclosingRequest);
    }

    public void sendRequestHeader(HttpRequest httpRequest) throws HttpException, IOException {
        d().sendRequestHeader(httpRequest);
    }

    public InetAddress getLocalAddress() {
        return d().getLocalAddress();
    }

    public int getLocalPort() {
        return d().getLocalPort();
    }

    public InetAddress getRemoteAddress() {
        return d().getRemoteAddress();
    }

    public int getRemotePort() {
        return d().getRemotePort();
    }

    public boolean isSecure() {
        return d().isSecure();
    }

    public void bind(Socket socket) throws IOException {
        throw new UnsupportedOperationException();
    }

    public Socket getSocket() {
        return d().getSocket();
    }

    public SSLSession getSSLSession() {
        Socket socket = d().getSocket();
        if (socket instanceof SSLSocket) {
            return ((SSLSocket) socket).getSession();
        }
        return null;
    }

    public Object getAttribute(String str) {
        OperatedClientConnection d = d();
        if (d instanceof HttpContext) {
            return ((HttpContext) d).getAttribute(str);
        }
        return null;
    }

    public Object removeAttribute(String str) {
        OperatedClientConnection d = d();
        if (d instanceof HttpContext) {
            return ((HttpContext) d).removeAttribute(str);
        }
        return null;
    }

    public void setAttribute(String str, Object obj) {
        OperatedClientConnection d = d();
        if (d instanceof HttpContext) {
            ((HttpContext) d).setAttribute(str, obj);
        }
    }

    public HttpRoute getRoute() {
        return e().c();
    }

    public void open(HttpRoute httpRoute, HttpContext httpContext, HttpParams httpParams) throws IOException {
        OperatedClientConnection operatedClientConnection;
        HttpHost httpHost;
        Args.notNull(httpRoute, "Route");
        Args.notNull(httpParams, "HTTP parameters");
        synchronized (this) {
            if (this.c == null) {
                throw new ConnectionShutdownException();
            }
            RouteTracker a = this.c.a();
            Asserts.notNull(a, "Route tracker");
            Asserts.check(!a.isConnected(), "Connection already open");
            operatedClientConnection = (OperatedClientConnection) this.c.getConnection();
        }
        HttpHost proxyHost = httpRoute.getProxyHost();
        ClientConnectionOperator clientConnectionOperator = this.b;
        if (proxyHost != null) {
            httpHost = proxyHost;
        } else {
            httpHost = httpRoute.getTargetHost();
        }
        clientConnectionOperator.openConnection(operatedClientConnection, httpHost, httpRoute.getLocalAddress(), httpContext, httpParams);
        synchronized (this) {
            if (this.c == null) {
                throw new InterruptedIOException();
            }
            a = this.c.a();
            if (proxyHost == null) {
                a.connectTarget(operatedClientConnection.isSecure());
            } else {
                a.connectProxy(proxyHost, operatedClientConnection.isSecure());
            }
        }
    }

    public void tunnelTarget(boolean z, HttpParams httpParams) throws IOException {
        HttpHost targetHost;
        OperatedClientConnection operatedClientConnection;
        Args.notNull(httpParams, "HTTP parameters");
        synchronized (this) {
            if (this.c == null) {
                throw new ConnectionShutdownException();
            }
            RouteTracker a = this.c.a();
            Asserts.notNull(a, "Route tracker");
            Asserts.check(a.isConnected(), "Connection not open");
            Asserts.check(!a.isTunnelled(), "Connection is already tunnelled");
            targetHost = a.getTargetHost();
            operatedClientConnection = (OperatedClientConnection) this.c.getConnection();
        }
        operatedClientConnection.update(null, targetHost, z, httpParams);
        synchronized (this) {
            if (this.c == null) {
                throw new InterruptedIOException();
            }
            this.c.a().tunnelTarget(z);
        }
    }

    public void tunnelProxy(HttpHost httpHost, boolean z, HttpParams httpParams) throws IOException {
        OperatedClientConnection operatedClientConnection;
        Args.notNull(httpHost, "Next proxy");
        Args.notNull(httpParams, "HTTP parameters");
        synchronized (this) {
            if (this.c == null) {
                throw new ConnectionShutdownException();
            }
            RouteTracker a = this.c.a();
            Asserts.notNull(a, "Route tracker");
            Asserts.check(a.isConnected(), "Connection not open");
            operatedClientConnection = (OperatedClientConnection) this.c.getConnection();
        }
        operatedClientConnection.update(null, httpHost, z, httpParams);
        synchronized (this) {
            if (this.c == null) {
                throw new InterruptedIOException();
            }
            this.c.a().tunnelProxy(httpHost, z);
        }
    }

    public void layerProtocol(HttpContext httpContext, HttpParams httpParams) throws IOException {
        HttpHost targetHost;
        OperatedClientConnection operatedClientConnection;
        Args.notNull(httpParams, "HTTP parameters");
        synchronized (this) {
            if (this.c == null) {
                throw new ConnectionShutdownException();
            }
            RouteTracker a = this.c.a();
            Asserts.notNull(a, "Route tracker");
            Asserts.check(a.isConnected(), "Connection not open");
            Asserts.check(a.isTunnelled(), "Protocol layering without a tunnel not supported");
            Asserts.check(!a.isLayered(), "Multiple protocol layering not supported");
            targetHost = a.getTargetHost();
            operatedClientConnection = (OperatedClientConnection) this.c.getConnection();
        }
        this.b.updateSecureConnection(operatedClientConnection, targetHost, httpContext, httpParams);
        synchronized (this) {
            if (this.c == null) {
                throw new InterruptedIOException();
            }
            this.c.a().layerProtocol(operatedClientConnection.isSecure());
        }
    }

    public Object getState() {
        return e().getState();
    }

    public void setState(Object obj) {
        e().setState(obj);
    }

    public void markReusable() {
        this.d = true;
    }

    public void unmarkReusable() {
        this.d = false;
    }

    public boolean isMarkedReusable() {
        return this.d;
    }

    public void setIdleDuration(long j, TimeUnit timeUnit) {
        if (j > 0) {
            this.e = timeUnit.toMillis(j);
        } else {
            this.e = -1;
        }
    }

    public void releaseConnection() {
        synchronized (this) {
            if (this.c == null) {
                return;
            }
            this.a.releaseConnection(this, this.e, TimeUnit.MILLISECONDS);
            this.c = null;
        }
    }

    public void abortConnection() {
        synchronized (this) {
            if (this.c == null) {
                return;
            }
            this.d = false;
            try {
                ((OperatedClientConnection) this.c.getConnection()).shutdown();
            } catch (IOException e) {
            }
            this.a.releaseConnection(this, this.e, TimeUnit.MILLISECONDS);
            this.c = null;
        }
    }
}
