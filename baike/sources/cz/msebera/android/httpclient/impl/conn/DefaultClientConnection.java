package cz.msebera.android.httpclient.impl.conn;

import cz.msebera.android.httpclient.HttpException;
import cz.msebera.android.httpclient.HttpHost;
import cz.msebera.android.httpclient.HttpRequest;
import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.HttpResponseFactory;
import cz.msebera.android.httpclient.annotation.NotThreadSafe;
import cz.msebera.android.httpclient.conn.ManagedHttpClientConnection;
import cz.msebera.android.httpclient.conn.OperatedClientConnection;
import cz.msebera.android.httpclient.extras.HttpClientAndroidLog;
import cz.msebera.android.httpclient.impl.SocketHttpClientConnection;
import cz.msebera.android.httpclient.io.HttpMessageParser;
import cz.msebera.android.httpclient.io.SessionInputBuffer;
import cz.msebera.android.httpclient.io.SessionOutputBuffer;
import cz.msebera.android.httpclient.params.BasicHttpParams;
import cz.msebera.android.httpclient.params.HttpParams;
import cz.msebera.android.httpclient.params.HttpProtocolParams;
import cz.msebera.android.httpclient.protocol.HttpContext;
import cz.msebera.android.httpclient.util.Args;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;

@NotThreadSafe
@Deprecated
public class DefaultClientConnection extends SocketHttpClientConnection implements ManagedHttpClientConnection, OperatedClientConnection, HttpContext {
    private volatile Socket a;
    private HttpHost b;
    private boolean c;
    private volatile boolean d;
    private final Map<String, Object> e = new HashMap();
    public HttpClientAndroidLog headerLog = new HttpClientAndroidLog("cz.msebera.android.httpclient.headers");
    public HttpClientAndroidLog log = new HttpClientAndroidLog(getClass());
    public HttpClientAndroidLog wireLog = new HttpClientAndroidLog("cz.msebera.android.httpclient.wire");

    public String getId() {
        return null;
    }

    public final HttpHost getTargetHost() {
        return this.b;
    }

    public final boolean isSecure() {
        return this.c;
    }

    public final Socket getSocket() {
        return this.a;
    }

    public SSLSession getSSLSession() {
        if (this.a instanceof SSLSocket) {
            return ((SSLSocket) this.a).getSession();
        }
        return null;
    }

    public void opening(Socket socket, HttpHost httpHost) throws IOException {
        g();
        this.a = socket;
        this.b = httpHost;
        if (this.d) {
            socket.close();
            throw new InterruptedIOException("Connection already shutdown");
        }
    }

    public void openCompleted(boolean z, HttpParams httpParams) throws IOException {
        Args.notNull(httpParams, "Parameters");
        g();
        this.c = z;
        bind(this.a, httpParams);
    }

    public void shutdown() throws IOException {
        this.d = true;
        try {
            super.shutdown();
            if (this.log.isDebugEnabled()) {
                this.log.debug("Connection " + this + " shut down");
            }
            Socket socket = this.a;
            if (socket != null) {
                socket.close();
            }
        } catch (Throwable e) {
            this.log.debug("I/O error shutting down connection", e);
        }
    }

    public void close() throws IOException {
        try {
            super.close();
            if (this.log.isDebugEnabled()) {
                this.log.debug("Connection " + this + " closed");
            }
        } catch (Throwable e) {
            this.log.debug("I/O error closing connection", e);
        }
    }

    protected SessionInputBuffer a(Socket socket, int i, HttpParams httpParams) throws IOException {
        if (i <= 0) {
            i = 8192;
        }
        SessionInputBuffer a = super.a(socket, i, httpParams);
        if (this.wireLog.isDebugEnabled()) {
            return new LoggingSessionInputBuffer(a, new Wire(this.wireLog), HttpProtocolParams.getHttpElementCharset(httpParams));
        }
        return a;
    }

    protected SessionOutputBuffer b(Socket socket, int i, HttpParams httpParams) throws IOException {
        if (i <= 0) {
            i = 8192;
        }
        SessionOutputBuffer b = super.b(socket, i, httpParams);
        if (this.wireLog.isDebugEnabled()) {
            return new LoggingSessionOutputBuffer(b, new Wire(this.wireLog), HttpProtocolParams.getHttpElementCharset(httpParams));
        }
        return b;
    }

    protected HttpMessageParser<HttpResponse> a(SessionInputBuffer sessionInputBuffer, HttpResponseFactory httpResponseFactory, HttpParams httpParams) {
        return new DefaultHttpResponseParser(sessionInputBuffer, null, httpResponseFactory, httpParams);
    }

    public void bind(Socket socket) throws IOException {
        bind(socket, new BasicHttpParams());
    }

    public void update(Socket socket, HttpHost httpHost, boolean z, HttpParams httpParams) throws IOException {
        a();
        Args.notNull(httpHost, "Target host");
        Args.notNull(httpParams, "Parameters");
        if (socket != null) {
            this.a = socket;
            bind(socket, httpParams);
        }
        this.b = httpHost;
        this.c = z;
    }

    public HttpResponse receiveResponseHeader() throws HttpException, IOException {
        HttpResponse receiveResponseHeader = super.receiveResponseHeader();
        if (this.log.isDebugEnabled()) {
            this.log.debug("Receiving response: " + receiveResponseHeader.getStatusLine());
        }
        if (this.headerLog.isDebugEnabled()) {
            this.headerLog.debug("<< " + receiveResponseHeader.getStatusLine().toString());
            for (Object obj : receiveResponseHeader.getAllHeaders()) {
                this.headerLog.debug("<< " + obj.toString());
            }
        }
        return receiveResponseHeader;
    }

    public void sendRequestHeader(HttpRequest httpRequest) throws HttpException, IOException {
        if (this.log.isDebugEnabled()) {
            this.log.debug("Sending request: " + httpRequest.getRequestLine());
        }
        super.sendRequestHeader(httpRequest);
        if (this.headerLog.isDebugEnabled()) {
            this.headerLog.debug(">> " + httpRequest.getRequestLine().toString());
            for (Object obj : httpRequest.getAllHeaders()) {
                this.headerLog.debug(">> " + obj.toString());
            }
        }
    }

    public Object getAttribute(String str) {
        return this.e.get(str);
    }

    public Object removeAttribute(String str) {
        return this.e.remove(str);
    }

    public void setAttribute(String str, Object obj) {
        this.e.put(str, obj);
    }
}
