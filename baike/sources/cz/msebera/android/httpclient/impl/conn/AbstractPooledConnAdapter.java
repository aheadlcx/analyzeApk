package cz.msebera.android.httpclient.impl.conn;

import cz.msebera.android.httpclient.HttpHost;
import cz.msebera.android.httpclient.conn.ClientConnectionManager;
import cz.msebera.android.httpclient.conn.OperatedClientConnection;
import cz.msebera.android.httpclient.conn.routing.HttpRoute;
import cz.msebera.android.httpclient.params.HttpParams;
import cz.msebera.android.httpclient.protocol.HttpContext;
import java.io.IOException;

@Deprecated
public abstract class AbstractPooledConnAdapter extends AbstractClientConnAdapter {
    protected volatile AbstractPoolEntry a;

    protected AbstractPooledConnAdapter(ClientConnectionManager clientConnectionManager, AbstractPoolEntry abstractPoolEntry) {
        super(clientConnectionManager, abstractPoolEntry.b);
        this.a = abstractPoolEntry;
    }

    public String getId() {
        return null;
    }

    @Deprecated
    protected AbstractPoolEntry e() {
        return this.a;
    }

    protected void a(AbstractPoolEntry abstractPoolEntry) {
        if (d() || abstractPoolEntry == null) {
            throw new ConnectionShutdownException();
        }
    }

    protected synchronized void a() {
        this.a = null;
        super.a();
    }

    public HttpRoute getRoute() {
        AbstractPoolEntry e = e();
        a(e);
        return e.e == null ? null : e.e.toRoute();
    }

    public void open(HttpRoute httpRoute, HttpContext httpContext, HttpParams httpParams) throws IOException {
        AbstractPoolEntry e = e();
        a(e);
        e.open(httpRoute, httpContext, httpParams);
    }

    public void tunnelTarget(boolean z, HttpParams httpParams) throws IOException {
        AbstractPoolEntry e = e();
        a(e);
        e.tunnelTarget(z, httpParams);
    }

    public void tunnelProxy(HttpHost httpHost, boolean z, HttpParams httpParams) throws IOException {
        AbstractPoolEntry e = e();
        a(e);
        e.tunnelProxy(httpHost, z, httpParams);
    }

    public void layerProtocol(HttpContext httpContext, HttpParams httpParams) throws IOException {
        AbstractPoolEntry e = e();
        a(e);
        e.layerProtocol(httpContext, httpParams);
    }

    public void close() throws IOException {
        AbstractPoolEntry e = e();
        if (e != null) {
            e.a();
        }
        OperatedClientConnection b = b();
        if (b != null) {
            b.close();
        }
    }

    public void shutdown() throws IOException {
        AbstractPoolEntry e = e();
        if (e != null) {
            e.a();
        }
        OperatedClientConnection b = b();
        if (b != null) {
            b.shutdown();
        }
    }

    public Object getState() {
        AbstractPoolEntry e = e();
        a(e);
        return e.getState();
    }

    public void setState(Object obj) {
        AbstractPoolEntry e = e();
        a(e);
        e.setState(obj);
    }
}
