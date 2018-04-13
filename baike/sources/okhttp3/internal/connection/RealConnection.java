package okhttp3.internal.connection;

import com.baidu.mobads.interfaces.utils.IXAdSystemUtils;
import com.baidu.mobstat.Config;
import cz.msebera.android.httpclient.protocol.HTTP;
import java.io.IOException;
import java.lang.ref.Reference;
import java.net.ConnectException;
import java.net.Proxy;
import java.net.Proxy.Type;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSocket;
import okhttp3.Address;
import okhttp3.Call;
import okhttp3.CertificatePinner;
import okhttp3.Connection;
import okhttp3.ConnectionPool;
import okhttp3.ConnectionSpec;
import okhttp3.EventListener;
import okhttp3.Handshake;
import okhttp3.HttpUrl;
import okhttp3.Interceptor$Chain;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Request$Builder;
import okhttp3.Response;
import okhttp3.Route;
import okhttp3.internal.Internal;
import okhttp3.internal.Util;
import okhttp3.internal.Version;
import okhttp3.internal.http.HttpCodec;
import okhttp3.internal.http.HttpHeaders;
import okhttp3.internal.http1.Http1Codec;
import okhttp3.internal.http2.ErrorCode;
import okhttp3.internal.http2.Http2Codec;
import okhttp3.internal.http2.Http2Connection;
import okhttp3.internal.http2.Http2Connection.Builder;
import okhttp3.internal.http2.Http2Connection.Listener;
import okhttp3.internal.http2.Http2Stream;
import okhttp3.internal.platform.Platform;
import okhttp3.internal.tls.OkHostnameVerifier;
import okhttp3.internal.ws.RealWebSocket.Streams;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.Okio;
import okio.Source;

public final class RealConnection extends Listener implements Connection {
    private final ConnectionPool a;
    public int allocationLimit = 1;
    public final List<Reference<StreamAllocation>> allocations = new ArrayList();
    private final Route b;
    private Socket c;
    private Socket d;
    private Handshake e;
    private Protocol f;
    private Http2Connection g;
    private BufferedSource h;
    private BufferedSink i;
    public long idleAtNanos = Long.MAX_VALUE;
    public boolean noNewStreams;
    public int successCount;

    public RealConnection(ConnectionPool connectionPool, Route route) {
        this.a = connectionPool;
        this.b = route;
    }

    public static RealConnection testConnection(ConnectionPool connectionPool, Route route, Socket socket, long j) {
        RealConnection realConnection = new RealConnection(connectionPool, route);
        realConnection.d = socket;
        realConnection.idleAtNanos = j;
        return realConnection;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void connect(int r10, int r11, int r12, boolean r13, okhttp3.Call r14, okhttp3.EventListener r15) {
        /*
        r9 = this;
        r7 = 0;
        r0 = r9.f;
        if (r0 == 0) goto L_0x000d;
    L_0x0005:
        r0 = new java.lang.IllegalStateException;
        r1 = "already connected";
        r0.<init>(r1);
        throw r0;
    L_0x000d:
        r0 = r9.b;
        r0 = r0.address();
        r0 = r0.connectionSpecs();
        r8 = new okhttp3.internal.connection.ConnectionSpecSelector;
        r8.<init>(r0);
        r1 = r9.b;
        r1 = r1.address();
        r1 = r1.sslSocketFactory();
        if (r1 != 0) goto L_0x0114;
    L_0x0028:
        r1 = okhttp3.ConnectionSpec.CLEARTEXT;
        r0 = r0.contains(r1);
        if (r0 != 0) goto L_0x003d;
    L_0x0030:
        r0 = new okhttp3.internal.connection.RouteException;
        r1 = new java.net.UnknownServiceException;
        r2 = "CLEARTEXT communication not enabled for client";
        r1.<init>(r2);
        r0.<init>(r1);
        throw r0;
    L_0x003d:
        r0 = r9.b;
        r0 = r0.address();
        r0 = r0.url();
        r0 = r0.host();
        r1 = okhttp3.internal.platform.Platform.get();
        r1 = r1.isCleartextTrafficPermitted(r0);
        if (r1 != 0) goto L_0x0114;
    L_0x0055:
        r1 = new okhttp3.internal.connection.RouteException;
        r2 = new java.net.UnknownServiceException;
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r4 = "CLEARTEXT communication to ";
        r3 = r3.append(r4);
        r0 = r3.append(r0);
        r3 = " not permitted by network security policy";
        r0 = r0.append(r3);
        r0 = r0.toString();
        r2.<init>(r0);
        r1.<init>(r2);
        throw r1;
    L_0x0079:
        r6 = r0;
    L_0x007a:
        r0 = r9.b;	 Catch:{ IOException -> 0x00c0 }
        r0 = r0.requiresTunnel();	 Catch:{ IOException -> 0x00c0 }
        if (r0 == 0) goto L_0x00a8;
    L_0x0082:
        r0 = r9;
        r1 = r10;
        r2 = r11;
        r3 = r12;
        r4 = r14;
        r5 = r15;
        r0.a(r1, r2, r3, r4, r5);	 Catch:{ IOException -> 0x00c0 }
        r0 = r9.c;	 Catch:{ IOException -> 0x00c0 }
        if (r0 != 0) goto L_0x00ab;
    L_0x008f:
        r0 = r9.b;
        r0 = r0.requiresTunnel();
        if (r0 == 0) goto L_0x0100;
    L_0x0097:
        r0 = r9.c;
        if (r0 != 0) goto L_0x0100;
    L_0x009b:
        r0 = new java.net.ProtocolException;
        r1 = "Too many tunnel connections attempted: 21";
        r0.<init>(r1);
        r1 = new okhttp3.internal.connection.RouteException;
        r1.<init>(r0);
        throw r1;
    L_0x00a8:
        r9.a(r10, r11, r14, r15);	 Catch:{ IOException -> 0x00c0 }
    L_0x00ab:
        r9.a(r8, r14, r15);	 Catch:{ IOException -> 0x00c0 }
        r0 = r9.b;	 Catch:{ IOException -> 0x00c0 }
        r0 = r0.socketAddress();	 Catch:{ IOException -> 0x00c0 }
        r1 = r9.b;	 Catch:{ IOException -> 0x00c0 }
        r1 = r1.proxy();	 Catch:{ IOException -> 0x00c0 }
        r2 = r9.f;	 Catch:{ IOException -> 0x00c0 }
        r15.connectEnd(r14, r0, r1, r2);	 Catch:{ IOException -> 0x00c0 }
        goto L_0x008f;
    L_0x00c0:
        r5 = move-exception;
        r0 = r9.d;
        okhttp3.internal.Util.closeQuietly(r0);
        r0 = r9.c;
        okhttp3.internal.Util.closeQuietly(r0);
        r9.d = r7;
        r9.c = r7;
        r9.h = r7;
        r9.i = r7;
        r9.e = r7;
        r9.f = r7;
        r9.g = r7;
        r0 = r9.b;
        r2 = r0.socketAddress();
        r0 = r9.b;
        r3 = r0.proxy();
        r0 = r15;
        r1 = r14;
        r4 = r7;
        r0.connectFailed(r1, r2, r3, r4, r5);
        if (r6 != 0) goto L_0x00fb;
    L_0x00ed:
        r0 = new okhttp3.internal.connection.RouteException;
        r0.<init>(r5);
    L_0x00f2:
        if (r13 == 0) goto L_0x00fa;
    L_0x00f4:
        r1 = r8.connectionFailed(r5);
        if (r1 != 0) goto L_0x0079;
    L_0x00fa:
        throw r0;
    L_0x00fb:
        r6.addConnectException(r5);
        r0 = r6;
        goto L_0x00f2;
    L_0x0100:
        r0 = r9.g;
        if (r0 == 0) goto L_0x0110;
    L_0x0104:
        r1 = r9.a;
        monitor-enter(r1);
        r0 = r9.g;	 Catch:{ all -> 0x0111 }
        r0 = r0.maxConcurrentStreams();	 Catch:{ all -> 0x0111 }
        r9.allocationLimit = r0;	 Catch:{ all -> 0x0111 }
        monitor-exit(r1);	 Catch:{ all -> 0x0111 }
    L_0x0110:
        return;
    L_0x0111:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x0111 }
        throw r0;
    L_0x0114:
        r6 = r7;
        goto L_0x007a;
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.connection.RealConnection.connect(int, int, int, boolean, okhttp3.Call, okhttp3.EventListener):void");
    }

    private void a(int i, int i2, int i3, Call call, EventListener eventListener) throws IOException {
        Request a = a();
        HttpUrl url = a.url();
        int i4 = 0;
        while (i4 < 21) {
            a(i, i2, call, eventListener);
            a = a(i2, i3, a, url);
            if (a != null) {
                Util.closeQuietly(this.c);
                this.c = null;
                this.i = null;
                this.h = null;
                eventListener.connectEnd(call, this.b.socketAddress(), this.b.proxy(), null);
                i4++;
            } else {
                return;
            }
        }
    }

    private void a(int i, int i2, Call call, EventListener eventListener) throws IOException {
        Socket createSocket;
        Proxy proxy = this.b.proxy();
        Address address = this.b.address();
        if (proxy.type() == Type.DIRECT || proxy.type() == Type.HTTP) {
            createSocket = address.socketFactory().createSocket();
        } else {
            createSocket = new Socket(proxy);
        }
        this.c = createSocket;
        eventListener.connectStart(call, this.b.socketAddress(), proxy);
        this.c.setSoTimeout(i2);
        try {
            Platform.get().connectSocket(this.c, this.b.socketAddress(), i);
            try {
                this.h = Okio.buffer(Okio.source(this.c));
                this.i = Okio.buffer(Okio.sink(this.c));
            } catch (Throwable e) {
                if ("throw with null exception".equals(e.getMessage())) {
                    throw new IOException(e);
                }
            }
        } catch (Throwable e2) {
            ConnectException connectException = new ConnectException("Failed to connect to " + this.b.socketAddress());
            connectException.initCause(e2);
            throw connectException;
        }
    }

    private void a(ConnectionSpecSelector connectionSpecSelector, Call call, EventListener eventListener) throws IOException {
        if (this.b.address().sslSocketFactory() == null) {
            this.f = Protocol.HTTP_1_1;
            this.d = this.c;
            return;
        }
        eventListener.secureConnectStart(call);
        a(connectionSpecSelector);
        eventListener.secureConnectEnd(call, this.e);
        if (this.f == Protocol.HTTP_2) {
            this.d.setSoTimeout(0);
            this.g = new Builder(true).socket(this.d, this.b.address().url().host(), this.h, this.i).listener(this).build();
            this.g.start();
        }
    }

    private void a(ConnectionSpecSelector connectionSpecSelector) throws IOException {
        Throwable th;
        Socket socket;
        Throwable th2;
        String str = null;
        Address address = this.b.address();
        try {
            Socket socket2 = (SSLSocket) address.sslSocketFactory().createSocket(this.c, address.url().host(), address.url().port(), true);
            try {
                ConnectionSpec configureSecureSocket = connectionSpecSelector.configureSecureSocket(socket2);
                if (configureSecureSocket.supportsTlsExtensions()) {
                    Platform.get().configureTlsExtensions(socket2, address.url().host(), address.protocols());
                }
                socket2.startHandshake();
                Handshake handshake = Handshake.get(socket2.getSession());
                if (address.hostnameVerifier().verify(address.url().host(), socket2.getSession())) {
                    Protocol protocol;
                    address.certificatePinner().check(address.url().host(), handshake.peerCertificates());
                    if (configureSecureSocket.supportsTlsExtensions()) {
                        str = Platform.get().getSelectedProtocol(socket2);
                    }
                    this.d = socket2;
                    this.h = Okio.buffer(Okio.source(this.d));
                    this.i = Okio.buffer(Okio.sink(this.d));
                    this.e = handshake;
                    if (str != null) {
                        protocol = Protocol.get(str);
                    } else {
                        protocol = Protocol.HTTP_1_1;
                    }
                    this.f = protocol;
                    if (socket2 != null) {
                        Platform.get().afterHandshake(socket2);
                        return;
                    }
                    return;
                }
                X509Certificate x509Certificate = (X509Certificate) handshake.peerCertificates().get(0);
                throw new SSLPeerUnverifiedException("Hostname " + address.url().host() + " not verified:\n    certificate: " + CertificatePinner.pin(x509Certificate) + "\n    DN: " + x509Certificate.getSubjectDN().getName() + "\n    subjectAltNames: " + OkHostnameVerifier.allSubjectAltNames(x509Certificate));
            } catch (Throwable e) {
                th = e;
                socket = socket2;
                th2 = th;
                try {
                    if (Util.isAndroidGetsocknameError(th2)) {
                        throw new IOException(th2);
                    }
                    throw th2;
                } catch (Throwable th3) {
                    th2 = th3;
                }
            } catch (Throwable e2) {
                th = e2;
                socket = socket2;
                th2 = th;
                if (socket != null) {
                    Platform.get().afterHandshake(socket);
                }
                Util.closeQuietly(socket);
                throw th2;
            }
        } catch (AssertionError e3) {
            th2 = e3;
            if (Util.isAndroidGetsocknameError(th2)) {
                throw new IOException(th2);
            }
            throw th2;
        }
    }

    private Request a(int i, int i2, Request request, HttpUrl httpUrl) throws IOException {
        String str = "CONNECT " + Util.hostHeader(httpUrl, true) + " HTTP/1.1";
        Response build;
        do {
            Http1Codec http1Codec = new Http1Codec(null, null, this.h, this.i);
            this.h.timeout().timeout((long) i, TimeUnit.MILLISECONDS);
            this.i.timeout().timeout((long) i2, TimeUnit.MILLISECONDS);
            http1Codec.writeRequest(request.headers(), str);
            http1Codec.finishRequest();
            build = http1Codec.readResponseHeaders(false).request(request).build();
            long contentLength = HttpHeaders.contentLength(build);
            if (contentLength == -1) {
                contentLength = 0;
            }
            Source newFixedLengthSource = http1Codec.newFixedLengthSource(contentLength);
            Util.skipAll(newFixedLengthSource, Integer.MAX_VALUE, TimeUnit.MILLISECONDS);
            newFixedLengthSource.close();
            switch (build.code()) {
                case 200:
                    if (this.h.buffer().exhausted() && this.i.buffer().exhausted()) {
                        return null;
                    }
                    throw new IOException("TLS tunnel buffered too many bytes!");
                case 407:
                    request = this.b.address().proxyAuthenticator().authenticate(this.b, build);
                    if (request != null) {
                        break;
                    }
                    throw new IOException("Failed to authenticate with proxy");
                default:
                    throw new IOException("Unexpected response code for CONNECT: " + build.code());
            }
        } while (!"close".equalsIgnoreCase(build.header("Connection")));
        return request;
    }

    private Request a() {
        return new Request$Builder().url(this.b.address().url()).header("Host", Util.hostHeader(this.b.address().url(), true)).header("Proxy-Connection", HTTP.CONN_KEEP_ALIVE).header("User-Agent", Version.userAgent()).build();
    }

    public boolean isEligible(Address address, @Nullable Route route) {
        if (this.allocations.size() >= this.allocationLimit || this.noNewStreams || !Internal.instance.equalsNonHost(this.b.address(), address)) {
            return false;
        }
        if (address.url().host().equals(route().address().url().host())) {
            return true;
        }
        if (this.g == null || route == null || route.proxy().type() != Type.DIRECT || this.b.proxy().type() != Type.DIRECT || !this.b.socketAddress().equals(route.socketAddress()) || route.address().hostnameVerifier() != OkHostnameVerifier.INSTANCE || !supportsUrl(address.url())) {
            return false;
        }
        try {
            address.certificatePinner().check(address.url().host(), handshake().peerCertificates());
            return true;
        } catch (SSLPeerUnverifiedException e) {
            return false;
        }
    }

    public boolean supportsUrl(HttpUrl httpUrl) {
        if (httpUrl.port() != this.b.address().url().port()) {
            return false;
        }
        if (httpUrl.host().equals(this.b.address().url().host())) {
            return true;
        }
        boolean z = this.e != null && OkHostnameVerifier.INSTANCE.verify(httpUrl.host(), (X509Certificate) this.e.peerCertificates().get(0));
        return z;
    }

    public HttpCodec newCodec(OkHttpClient okHttpClient, Interceptor$Chain interceptor$Chain, StreamAllocation streamAllocation) throws SocketException {
        if (this.g != null) {
            return new Http2Codec(okHttpClient, interceptor$Chain, streamAllocation, this.g);
        }
        this.d.setSoTimeout(interceptor$Chain.readTimeoutMillis());
        this.h.timeout().timeout((long) interceptor$Chain.readTimeoutMillis(), TimeUnit.MILLISECONDS);
        this.i.timeout().timeout((long) interceptor$Chain.writeTimeoutMillis(), TimeUnit.MILLISECONDS);
        return new Http1Codec(okHttpClient, streamAllocation, this.h, this.i);
    }

    public Streams newWebSocketStreams(StreamAllocation streamAllocation) {
        return new a(this, true, this.h, this.i, streamAllocation);
    }

    public Route route() {
        return this.b;
    }

    public void cancel() {
        Util.closeQuietly(this.c);
    }

    public Socket socket() {
        return this.d;
    }

    public boolean isHealthy(boolean z) {
        if (this.d.isClosed() || this.d.isInputShutdown() || this.d.isOutputShutdown()) {
            return false;
        }
        if (this.g != null) {
            if (this.g.isShutdown()) {
                return false;
            }
            return true;
        } else if (!z) {
            return true;
        } else {
            int soTimeout;
            try {
                soTimeout = this.d.getSoTimeout();
                this.d.setSoTimeout(1);
                if (this.h.exhausted()) {
                    this.d.setSoTimeout(soTimeout);
                    return false;
                }
                this.d.setSoTimeout(soTimeout);
                return true;
            } catch (SocketTimeoutException e) {
                return true;
            } catch (IOException e2) {
                return false;
            } catch (Throwable th) {
                this.d.setSoTimeout(soTimeout);
            }
        }
    }

    public void onStream(Http2Stream http2Stream) throws IOException {
        http2Stream.close(ErrorCode.REFUSED_STREAM);
    }

    public void onSettings(Http2Connection http2Connection) {
        synchronized (this.a) {
            this.allocationLimit = http2Connection.maxConcurrentStreams();
        }
    }

    public Handshake handshake() {
        return this.e;
    }

    public boolean isMultiplexed() {
        return this.g != null;
    }

    public Protocol protocol() {
        return this.f;
    }

    public String toString() {
        Object cipherSuite;
        StringBuilder append = new StringBuilder().append("Connection{").append(this.b.address().url().host()).append(Config.TRACE_TODAY_VISIT_SPLIT).append(this.b.address().url().port()).append(", proxy=").append(this.b.proxy()).append(" hostAddress=").append(this.b.socketAddress()).append(" cipherSuite=");
        if (this.e != null) {
            cipherSuite = this.e.cipherSuite();
        } else {
            cipherSuite = IXAdSystemUtils.NT_NONE;
        }
        return append.append(cipherSuite).append(" protocol=").append(this.f).append('}').toString();
    }
}
