package cz.msebera.android.httpclient.impl;

import cz.msebera.android.httpclient.ConnectionClosedException;
import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.HttpConnection;
import cz.msebera.android.httpclient.HttpConnectionMetrics;
import cz.msebera.android.httpclient.HttpEntity;
import cz.msebera.android.httpclient.HttpException;
import cz.msebera.android.httpclient.HttpInetConnection;
import cz.msebera.android.httpclient.HttpMessage;
import cz.msebera.android.httpclient.annotation.NotThreadSafe;
import cz.msebera.android.httpclient.config.MessageConstraints;
import cz.msebera.android.httpclient.entity.BasicHttpEntity;
import cz.msebera.android.httpclient.entity.ContentLengthStrategy;
import cz.msebera.android.httpclient.impl.entity.LaxContentLengthStrategy;
import cz.msebera.android.httpclient.impl.entity.StrictContentLengthStrategy;
import cz.msebera.android.httpclient.impl.io.ChunkedInputStream;
import cz.msebera.android.httpclient.impl.io.ChunkedOutputStream;
import cz.msebera.android.httpclient.impl.io.ContentLengthInputStream;
import cz.msebera.android.httpclient.impl.io.ContentLengthOutputStream;
import cz.msebera.android.httpclient.impl.io.EmptyInputStream;
import cz.msebera.android.httpclient.impl.io.HttpTransportMetricsImpl;
import cz.msebera.android.httpclient.impl.io.IdentityInputStream;
import cz.msebera.android.httpclient.impl.io.IdentityOutputStream;
import cz.msebera.android.httpclient.impl.io.SessionInputBufferImpl;
import cz.msebera.android.httpclient.impl.io.SessionOutputBufferImpl;
import cz.msebera.android.httpclient.io.HttpTransportMetrics;
import cz.msebera.android.httpclient.io.SessionInputBuffer;
import cz.msebera.android.httpclient.io.SessionOutputBuffer;
import cz.msebera.android.httpclient.util.Args;
import cz.msebera.android.httpclient.util.NetUtils;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.util.concurrent.atomic.AtomicReference;

@NotThreadSafe
public class BHttpConnectionBase implements HttpConnection, HttpInetConnection {
    private final SessionInputBufferImpl a;
    private final SessionOutputBufferImpl b;
    private final MessageConstraints c;
    private final HttpConnectionMetricsImpl d;
    private final ContentLengthStrategy e;
    private final ContentLengthStrategy f;
    private final AtomicReference<Socket> g;

    protected BHttpConnectionBase(int i, int i2, CharsetDecoder charsetDecoder, CharsetEncoder charsetEncoder, MessageConstraints messageConstraints, ContentLengthStrategy contentLengthStrategy, ContentLengthStrategy contentLengthStrategy2) {
        Args.positive(i, "Buffer size");
        HttpTransportMetrics httpTransportMetricsImpl = new HttpTransportMetricsImpl();
        HttpTransportMetrics httpTransportMetricsImpl2 = new HttpTransportMetricsImpl();
        this.a = new SessionInputBufferImpl(httpTransportMetricsImpl, i, -1, messageConstraints != null ? messageConstraints : MessageConstraints.DEFAULT, charsetDecoder);
        this.b = new SessionOutputBufferImpl(httpTransportMetricsImpl2, i, i2, charsetEncoder);
        this.c = messageConstraints;
        this.d = new HttpConnectionMetricsImpl(httpTransportMetricsImpl, httpTransportMetricsImpl2);
        if (contentLengthStrategy == null) {
            contentLengthStrategy = LaxContentLengthStrategy.INSTANCE;
        }
        this.e = contentLengthStrategy;
        if (contentLengthStrategy2 == null) {
            contentLengthStrategy2 = StrictContentLengthStrategy.INSTANCE;
        }
        this.f = contentLengthStrategy2;
        this.g = new AtomicReference();
    }

    protected void a() throws IOException {
        Socket socket = (Socket) this.g.get();
        if (socket == null) {
            throw new ConnectionClosedException("Connection is closed");
        }
        if (!this.a.isBound()) {
            this.a.bind(a(socket));
        }
        if (!this.b.isBound()) {
            this.b.bind(b(socket));
        }
    }

    protected InputStream a(Socket socket) throws IOException {
        return socket.getInputStream();
    }

    protected OutputStream b(Socket socket) throws IOException {
        return socket.getOutputStream();
    }

    protected void bind(Socket socket) throws IOException {
        Args.notNull(socket, "Socket");
        this.g.set(socket);
        this.a.bind(null);
        this.b.bind(null);
    }

    protected SessionInputBuffer b() {
        return this.a;
    }

    protected SessionOutputBuffer c() {
        return this.b;
    }

    protected void d() throws IOException {
        this.b.flush();
    }

    public boolean isOpen() {
        return this.g.get() != null;
    }

    protected Socket getSocket() {
        return (Socket) this.g.get();
    }

    protected OutputStream a(long j, SessionOutputBuffer sessionOutputBuffer) {
        if (j == -2) {
            return new ChunkedOutputStream(2048, sessionOutputBuffer);
        }
        if (j == -1) {
            return new IdentityOutputStream(sessionOutputBuffer);
        }
        return new ContentLengthOutputStream(sessionOutputBuffer, j);
    }

    protected OutputStream a(HttpMessage httpMessage) throws HttpException {
        return a(this.f.determineLength(httpMessage), this.b);
    }

    protected InputStream a(long j, SessionInputBuffer sessionInputBuffer) {
        if (j == -2) {
            return new ChunkedInputStream(sessionInputBuffer, this.c);
        }
        if (j == -1) {
            return new IdentityInputStream(sessionInputBuffer);
        }
        if (j == 0) {
            return EmptyInputStream.INSTANCE;
        }
        return new ContentLengthInputStream(sessionInputBuffer, j);
    }

    protected HttpEntity b(HttpMessage httpMessage) throws HttpException {
        BasicHttpEntity basicHttpEntity = new BasicHttpEntity();
        long determineLength = this.e.determineLength(httpMessage);
        InputStream a = a(determineLength, this.a);
        if (determineLength == -2) {
            basicHttpEntity.setChunked(true);
            basicHttpEntity.setContentLength(-1);
            basicHttpEntity.setContent(a);
        } else if (determineLength == -1) {
            basicHttpEntity.setChunked(false);
            basicHttpEntity.setContentLength(-1);
            basicHttpEntity.setContent(a);
        } else {
            basicHttpEntity.setChunked(false);
            basicHttpEntity.setContentLength(determineLength);
            basicHttpEntity.setContent(a);
        }
        Header firstHeader = httpMessage.getFirstHeader("Content-Type");
        if (firstHeader != null) {
            basicHttpEntity.setContentType(firstHeader);
        }
        firstHeader = httpMessage.getFirstHeader("Content-Encoding");
        if (firstHeader != null) {
            basicHttpEntity.setContentEncoding(firstHeader);
        }
        return basicHttpEntity;
    }

    public InetAddress getLocalAddress() {
        Socket socket = (Socket) this.g.get();
        return socket != null ? socket.getLocalAddress() : null;
    }

    public int getLocalPort() {
        Socket socket = (Socket) this.g.get();
        return socket != null ? socket.getLocalPort() : -1;
    }

    public InetAddress getRemoteAddress() {
        Socket socket = (Socket) this.g.get();
        return socket != null ? socket.getInetAddress() : null;
    }

    public int getRemotePort() {
        Socket socket = (Socket) this.g.get();
        return socket != null ? socket.getPort() : -1;
    }

    public void setSocketTimeout(int i) {
        Socket socket = (Socket) this.g.get();
        if (socket != null) {
            try {
                socket.setSoTimeout(i);
            } catch (SocketException e) {
            }
        }
    }

    public int getSocketTimeout() {
        Socket socket = (Socket) this.g.get();
        if (socket == null) {
            return -1;
        }
        try {
            return socket.getSoTimeout();
        } catch (SocketException e) {
            return -1;
        }
    }

    public void shutdown() throws IOException {
        Socket socket = (Socket) this.g.getAndSet(null);
        if (socket != null) {
            try {
                socket.setSoLinger(true, 0);
            } catch (IOException e) {
            } finally {
                socket.close();
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void close() throws java.io.IOException {
        /*
        r2 = this;
        r0 = r2.g;
        r1 = 0;
        r0 = r0.getAndSet(r1);
        r0 = (java.net.Socket) r0;
        if (r0 == 0) goto L_0x001e;
    L_0x000b:
        r1 = r2.a;	 Catch:{ all -> 0x001f }
        r1.clear();	 Catch:{ all -> 0x001f }
        r1 = r2.b;	 Catch:{ all -> 0x001f }
        r1.flush();	 Catch:{ all -> 0x001f }
        r0.shutdownOutput();	 Catch:{ IOException -> 0x0024, UnsupportedOperationException -> 0x0028 }
    L_0x0018:
        r0.shutdownInput();	 Catch:{ IOException -> 0x0026, UnsupportedOperationException -> 0x0028 }
    L_0x001b:
        r0.close();
    L_0x001e:
        return;
    L_0x001f:
        r1 = move-exception;
        r0.close();
        throw r1;
    L_0x0024:
        r1 = move-exception;
        goto L_0x0018;
    L_0x0026:
        r1 = move-exception;
        goto L_0x001b;
    L_0x0028:
        r1 = move-exception;
        goto L_0x001b;
        */
        throw new UnsupportedOperationException("Method not decompiled: cz.msebera.android.httpclient.impl.BHttpConnectionBase.close():void");
    }

    private int b(int i) throws IOException {
        Socket socket = (Socket) this.g.get();
        int soTimeout = socket.getSoTimeout();
        try {
            socket.setSoTimeout(i);
            int fillBuffer = this.a.fillBuffer();
            return fillBuffer;
        } finally {
            socket.setSoTimeout(soTimeout);
        }
    }

    protected boolean a(int i) throws IOException {
        if (this.a.hasBufferedData()) {
            return true;
        }
        b(i);
        return this.a.hasBufferedData();
    }

    public boolean isStale() {
        if (!isOpen()) {
            return true;
        }
        try {
            if (b(1) >= 0) {
                return false;
            }
            return true;
        } catch (SocketTimeoutException e) {
            return false;
        } catch (IOException e2) {
            return true;
        }
    }

    protected void e() {
        this.d.incrementRequestCount();
    }

    protected void f() {
        this.d.incrementResponseCount();
    }

    public HttpConnectionMetrics getMetrics() {
        return this.d;
    }

    public String toString() {
        Socket socket = (Socket) this.g.get();
        if (socket == null) {
            return "[Not bound]";
        }
        StringBuilder stringBuilder = new StringBuilder();
        SocketAddress remoteSocketAddress = socket.getRemoteSocketAddress();
        SocketAddress localSocketAddress = socket.getLocalSocketAddress();
        if (!(remoteSocketAddress == null || localSocketAddress == null)) {
            NetUtils.formatAddress(stringBuilder, localSocketAddress);
            stringBuilder.append("<->");
            NetUtils.formatAddress(stringBuilder, remoteSocketAddress);
        }
        return stringBuilder.toString();
    }
}
