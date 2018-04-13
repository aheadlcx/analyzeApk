package cz.msebera.android.httpclient.impl.conn;

import cz.msebera.android.httpclient.HttpClientConnection;
import cz.msebera.android.httpclient.HttpConnectionMetrics;
import cz.msebera.android.httpclient.HttpEntityEnclosingRequest;
import cz.msebera.android.httpclient.HttpException;
import cz.msebera.android.httpclient.HttpRequest;
import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.annotation.NotThreadSafe;
import cz.msebera.android.httpclient.conn.ManagedHttpClientConnection;
import cz.msebera.android.httpclient.protocol.HttpContext;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import javax.net.ssl.SSLSession;

@NotThreadSafe
class e implements ManagedHttpClientConnection, HttpContext {
    private volatile d a;

    e(d dVar) {
        this.a = dVar;
    }

    d a() {
        return this.a;
    }

    d b() {
        d dVar = this.a;
        this.a = null;
        return dVar;
    }

    ManagedHttpClientConnection c() {
        d dVar = this.a;
        if (dVar == null) {
            return null;
        }
        return (ManagedHttpClientConnection) dVar.getConnection();
    }

    ManagedHttpClientConnection d() {
        ManagedHttpClientConnection c = c();
        if (c != null) {
            return c;
        }
        throw new ConnectionShutdownException();
    }

    public void close() throws IOException {
        d dVar = this.a;
        if (dVar != null) {
            dVar.closeConnection();
        }
    }

    public void shutdown() throws IOException {
        d dVar = this.a;
        if (dVar != null) {
            dVar.shutdownConnection();
        }
    }

    public boolean isOpen() {
        d dVar = this.a;
        if (dVar == null || dVar.isClosed()) {
            return false;
        }
        return true;
    }

    public boolean isStale() {
        HttpClientConnection c = c();
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

    public String getId() {
        return d().getId();
    }

    public void bind(Socket socket) throws IOException {
        d().bind(socket);
    }

    public Socket getSocket() {
        return d().getSocket();
    }

    public SSLSession getSSLSession() {
        return d().getSSLSession();
    }

    public boolean isResponseAvailable(int i) throws IOException {
        return d().isResponseAvailable(i);
    }

    public void sendRequestHeader(HttpRequest httpRequest) throws HttpException, IOException {
        d().sendRequestHeader(httpRequest);
    }

    public void sendRequestEntity(HttpEntityEnclosingRequest httpEntityEnclosingRequest) throws HttpException, IOException {
        d().sendRequestEntity(httpEntityEnclosingRequest);
    }

    public HttpResponse receiveResponseHeader() throws HttpException, IOException {
        return d().receiveResponseHeader();
    }

    public void receiveResponseEntity(HttpResponse httpResponse) throws HttpException, IOException {
        d().receiveResponseEntity(httpResponse);
    }

    public void flush() throws IOException {
        d().flush();
    }

    public HttpConnectionMetrics getMetrics() {
        return d().getMetrics();
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

    public Object getAttribute(String str) {
        ManagedHttpClientConnection d = d();
        if (d instanceof HttpContext) {
            return ((HttpContext) d).getAttribute(str);
        }
        return null;
    }

    public void setAttribute(String str, Object obj) {
        ManagedHttpClientConnection d = d();
        if (d instanceof HttpContext) {
            ((HttpContext) d).setAttribute(str, obj);
        }
    }

    public Object removeAttribute(String str) {
        ManagedHttpClientConnection d = d();
        if (d instanceof HttpContext) {
            return ((HttpContext) d).removeAttribute(str);
        }
        return null;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("CPoolProxy{");
        ManagedHttpClientConnection c = c();
        if (c != null) {
            stringBuilder.append(c);
        } else {
            stringBuilder.append("detached");
        }
        stringBuilder.append('}');
        return stringBuilder.toString();
    }

    public static HttpClientConnection newProxy(d dVar) {
        return new e(dVar);
    }

    private static e a(HttpClientConnection httpClientConnection) {
        if (e.class.isInstance(httpClientConnection)) {
            return (e) e.class.cast(httpClientConnection);
        }
        throw new IllegalStateException("Unexpected connection proxy class: " + httpClientConnection.getClass());
    }

    public static d getPoolEntry(HttpClientConnection httpClientConnection) {
        d a = a(httpClientConnection).a();
        if (a != null) {
            return a;
        }
        throw new ConnectionShutdownException();
    }

    public static d detach(HttpClientConnection httpClientConnection) {
        return a(httpClientConnection).b();
    }
}
