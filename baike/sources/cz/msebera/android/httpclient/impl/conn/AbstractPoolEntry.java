package cz.msebera.android.httpclient.impl.conn;

import cz.msebera.android.httpclient.HttpHost;
import cz.msebera.android.httpclient.conn.ClientConnectionOperator;
import cz.msebera.android.httpclient.conn.OperatedClientConnection;
import cz.msebera.android.httpclient.conn.routing.HttpRoute;
import cz.msebera.android.httpclient.conn.routing.RouteTracker;
import cz.msebera.android.httpclient.params.HttpParams;
import cz.msebera.android.httpclient.protocol.HttpContext;
import cz.msebera.android.httpclient.util.Args;
import cz.msebera.android.httpclient.util.Asserts;
import java.io.IOException;
import java.io.InterruptedIOException;

@Deprecated
public abstract class AbstractPoolEntry {
    protected final ClientConnectionOperator a;
    protected final OperatedClientConnection b;
    protected volatile HttpRoute c;
    protected volatile Object d;
    protected volatile RouteTracker e = null;

    protected AbstractPoolEntry(ClientConnectionOperator clientConnectionOperator, HttpRoute httpRoute) {
        Args.notNull(clientConnectionOperator, "Connection operator");
        this.a = clientConnectionOperator;
        this.b = clientConnectionOperator.createConnection();
        this.c = httpRoute;
    }

    public Object getState() {
        return this.d;
    }

    public void setState(Object obj) {
        this.d = obj;
    }

    public void open(HttpRoute httpRoute, HttpContext httpContext, HttpParams httpParams) throws IOException {
        HttpHost httpHost;
        Args.notNull(httpRoute, "Route");
        Args.notNull(httpParams, "HTTP parameters");
        if (this.e != null) {
            Asserts.check(!this.e.isConnected(), "Connection already open");
        }
        this.e = new RouteTracker(httpRoute);
        HttpHost proxyHost = httpRoute.getProxyHost();
        ClientConnectionOperator clientConnectionOperator = this.a;
        OperatedClientConnection operatedClientConnection = this.b;
        if (proxyHost != null) {
            httpHost = proxyHost;
        } else {
            httpHost = httpRoute.getTargetHost();
        }
        clientConnectionOperator.openConnection(operatedClientConnection, httpHost, httpRoute.getLocalAddress(), httpContext, httpParams);
        RouteTracker routeTracker = this.e;
        if (routeTracker == null) {
            throw new InterruptedIOException("Request aborted");
        } else if (proxyHost == null) {
            routeTracker.connectTarget(this.b.isSecure());
        } else {
            routeTracker.connectProxy(proxyHost, this.b.isSecure());
        }
    }

    public void tunnelTarget(boolean z, HttpParams httpParams) throws IOException {
        Args.notNull(httpParams, "HTTP parameters");
        Asserts.notNull(this.e, "Route tracker");
        Asserts.check(this.e.isConnected(), "Connection not open");
        Asserts.check(!this.e.isTunnelled(), "Connection is already tunnelled");
        this.b.update(null, this.e.getTargetHost(), z, httpParams);
        this.e.tunnelTarget(z);
    }

    public void tunnelProxy(HttpHost httpHost, boolean z, HttpParams httpParams) throws IOException {
        Args.notNull(httpHost, "Next proxy");
        Args.notNull(httpParams, "Parameters");
        Asserts.notNull(this.e, "Route tracker");
        Asserts.check(this.e.isConnected(), "Connection not open");
        this.b.update(null, httpHost, z, httpParams);
        this.e.tunnelProxy(httpHost, z);
    }

    public void layerProtocol(HttpContext httpContext, HttpParams httpParams) throws IOException {
        Args.notNull(httpParams, "HTTP parameters");
        Asserts.notNull(this.e, "Route tracker");
        Asserts.check(this.e.isConnected(), "Connection not open");
        Asserts.check(this.e.isTunnelled(), "Protocol layering without a tunnel not supported");
        Asserts.check(!this.e.isLayered(), "Multiple protocol layering not supported");
        this.a.updateSecureConnection(this.b, this.e.getTargetHost(), httpContext, httpParams);
        this.e.layerProtocol(this.b.isSecure());
    }

    protected void a() {
        this.e = null;
        this.d = null;
    }
}
