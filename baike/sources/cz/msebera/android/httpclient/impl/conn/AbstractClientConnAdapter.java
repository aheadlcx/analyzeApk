package cz.msebera.android.httpclient.impl.conn;

import cz.msebera.android.httpclient.HttpConnectionMetrics;
import cz.msebera.android.httpclient.HttpEntityEnclosingRequest;
import cz.msebera.android.httpclient.HttpException;
import cz.msebera.android.httpclient.HttpRequest;
import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.annotation.NotThreadSafe;
import cz.msebera.android.httpclient.conn.ClientConnectionManager;
import cz.msebera.android.httpclient.conn.ManagedClientConnection;
import cz.msebera.android.httpclient.conn.OperatedClientConnection;
import cz.msebera.android.httpclient.protocol.HttpContext;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;

@NotThreadSafe
@Deprecated
public abstract class AbstractClientConnAdapter implements ManagedClientConnection, HttpContext {
    private final ClientConnectionManager a;
    private volatile OperatedClientConnection b;
    private volatile boolean c = false;
    private volatile boolean d = false;
    private volatile long e = Long.MAX_VALUE;

    protected AbstractClientConnAdapter(ClientConnectionManager clientConnectionManager, OperatedClientConnection operatedClientConnection) {
        this.a = clientConnectionManager;
        this.b = operatedClientConnection;
    }

    protected synchronized void a() {
        this.b = null;
        this.e = Long.MAX_VALUE;
    }

    protected OperatedClientConnection b() {
        return this.b;
    }

    protected ClientConnectionManager c() {
        return this.a;
    }

    protected boolean d() {
        return this.d;
    }

    protected final void a(OperatedClientConnection operatedClientConnection) throws ConnectionShutdownException {
        if (d() || operatedClientConnection == null) {
            throw new ConnectionShutdownException();
        }
    }

    public boolean isOpen() {
        OperatedClientConnection b = b();
        if (b == null) {
            return false;
        }
        return b.isOpen();
    }

    public boolean isStale() {
        if (d()) {
            return true;
        }
        OperatedClientConnection b = b();
        if (b != null) {
            return b.isStale();
        }
        return true;
    }

    public void setSocketTimeout(int i) {
        OperatedClientConnection b = b();
        a(b);
        b.setSocketTimeout(i);
    }

    public int getSocketTimeout() {
        OperatedClientConnection b = b();
        a(b);
        return b.getSocketTimeout();
    }

    public HttpConnectionMetrics getMetrics() {
        OperatedClientConnection b = b();
        a(b);
        return b.getMetrics();
    }

    public void flush() throws IOException {
        OperatedClientConnection b = b();
        a(b);
        b.flush();
    }

    public boolean isResponseAvailable(int i) throws IOException {
        OperatedClientConnection b = b();
        a(b);
        return b.isResponseAvailable(i);
    }

    public void receiveResponseEntity(HttpResponse httpResponse) throws HttpException, IOException {
        OperatedClientConnection b = b();
        a(b);
        unmarkReusable();
        b.receiveResponseEntity(httpResponse);
    }

    public HttpResponse receiveResponseHeader() throws HttpException, IOException {
        OperatedClientConnection b = b();
        a(b);
        unmarkReusable();
        return b.receiveResponseHeader();
    }

    public void sendRequestEntity(HttpEntityEnclosingRequest httpEntityEnclosingRequest) throws HttpException, IOException {
        OperatedClientConnection b = b();
        a(b);
        unmarkReusable();
        b.sendRequestEntity(httpEntityEnclosingRequest);
    }

    public void sendRequestHeader(HttpRequest httpRequest) throws HttpException, IOException {
        OperatedClientConnection b = b();
        a(b);
        unmarkReusable();
        b.sendRequestHeader(httpRequest);
    }

    public InetAddress getLocalAddress() {
        OperatedClientConnection b = b();
        a(b);
        return b.getLocalAddress();
    }

    public int getLocalPort() {
        OperatedClientConnection b = b();
        a(b);
        return b.getLocalPort();
    }

    public InetAddress getRemoteAddress() {
        OperatedClientConnection b = b();
        a(b);
        return b.getRemoteAddress();
    }

    public int getRemotePort() {
        OperatedClientConnection b = b();
        a(b);
        return b.getRemotePort();
    }

    public boolean isSecure() {
        OperatedClientConnection b = b();
        a(b);
        return b.isSecure();
    }

    public void bind(Socket socket) throws IOException {
        throw new UnsupportedOperationException();
    }

    public Socket getSocket() {
        OperatedClientConnection b = b();
        a(b);
        if (isOpen()) {
            return b.getSocket();
        }
        return null;
    }

    public SSLSession getSSLSession() {
        OperatedClientConnection b = b();
        a(b);
        if (!isOpen()) {
            return null;
        }
        SSLSession session;
        Socket socket = b.getSocket();
        if (socket instanceof SSLSocket) {
            session = ((SSLSocket) socket).getSession();
        } else {
            session = null;
        }
        return session;
    }

    public void markReusable() {
        this.c = true;
    }

    public void unmarkReusable() {
        this.c = false;
    }

    public boolean isMarkedReusable() {
        return this.c;
    }

    public void setIdleDuration(long j, TimeUnit timeUnit) {
        if (j > 0) {
            this.e = timeUnit.toMillis(j);
        } else {
            this.e = -1;
        }
    }

    public synchronized void releaseConnection() {
        if (!this.d) {
            this.d = true;
            this.a.releaseConnection(this, this.e, TimeUnit.MILLISECONDS);
        }
    }

    public synchronized void abortConnection() {
        if (!this.d) {
            this.d = true;
            unmarkReusable();
            try {
                shutdown();
            } catch (IOException e) {
            }
            this.a.releaseConnection(this, this.e, TimeUnit.MILLISECONDS);
        }
    }

    public Object getAttribute(String str) {
        OperatedClientConnection b = b();
        a(b);
        if (b instanceof HttpContext) {
            return ((HttpContext) b).getAttribute(str);
        }
        return null;
    }

    public Object removeAttribute(String str) {
        OperatedClientConnection b = b();
        a(b);
        if (b instanceof HttpContext) {
            return ((HttpContext) b).removeAttribute(str);
        }
        return null;
    }

    public void setAttribute(String str, Object obj) {
        OperatedClientConnection b = b();
        a(b);
        if (b instanceof HttpContext) {
            ((HttpContext) b).setAttribute(str, obj);
        }
    }
}
