package okhttp3.internal.ws;

import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import com.tencent.connect.common.Constants;
import cz.msebera.android.httpclient.HttpHeaders;
import java.io.Closeable;
import java.io.IOException;
import java.net.ProtocolException;
import java.util.ArrayDeque;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import okhttp3.Call;
import okhttp3.EventListener;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okhttp3.internal.Internal;
import okhttp3.internal.Util;
import okhttp3.internal.ws.WebSocketReader.FrameCallback;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.ByteString;

public final class RealWebSocket implements WebSocket, FrameCallback {
    static final /* synthetic */ boolean d = (!RealWebSocket.class.desiredAssertionStatus());
    private static final List<Protocol> e = Collections.singletonList(Protocol.HTTP_1_1);
    final WebSocketListener a;
    int b;
    int c;
    private final Request f;
    private final Random g;
    private final String h;
    private Call i;
    private final Runnable j;
    private WebSocketReader k;
    private c l;
    private ScheduledExecutorService m;
    private Streams n;
    private final ArrayDeque<ByteString> o = new ArrayDeque();
    private final ArrayDeque<Object> p = new ArrayDeque();
    private long q;
    private boolean r;
    private ScheduledFuture<?> s;
    private int t = -1;
    private String u;
    private boolean v;

    public static abstract class Streams implements Closeable {
        public final boolean client;
        public final BufferedSink sink;
        public final BufferedSource source;

        public Streams(boolean z, BufferedSource bufferedSource, BufferedSink bufferedSink) {
            this.client = z;
            this.source = bufferedSource;
            this.sink = bufferedSink;
        }
    }

    final class a implements Runnable {
        final /* synthetic */ RealWebSocket a;

        a(RealWebSocket realWebSocket) {
            this.a = realWebSocket;
        }

        public void run() {
            this.a.cancel();
        }
    }

    static final class b {
        final int a;
        final ByteString b;
        final long c;

        b(int i, ByteString byteString, long j) {
            this.a = i;
            this.b = byteString;
            this.c = j;
        }
    }

    static final class c {
        final int a;
        final ByteString b;

        c(int i, ByteString byteString) {
            this.a = i;
            this.b = byteString;
        }
    }

    private final class d implements Runnable {
        final /* synthetic */ RealWebSocket a;

        d(RealWebSocket realWebSocket) {
            this.a = realWebSocket;
        }

        public void run() {
            this.a.b();
        }
    }

    public RealWebSocket(Request request, WebSocketListener webSocketListener, Random random) {
        if ("GET".equals(request.method())) {
            this.f = request;
            this.a = webSocketListener;
            this.g = random;
            byte[] bArr = new byte[16];
            random.nextBytes(bArr);
            this.h = ByteString.of(bArr).base64();
            this.j = new a(this);
            return;
        }
        throw new IllegalArgumentException("Request must be GET: " + request.method());
    }

    public Request request() {
        return this.f;
    }

    public synchronized long queueSize() {
        return this.q;
    }

    public void cancel() {
        this.i.cancel();
    }

    public void connect(OkHttpClient okHttpClient) {
        OkHttpClient build = okHttpClient.newBuilder().eventListener(EventListener.NONE).protocols(e).build();
        int pingIntervalMillis = build.pingIntervalMillis();
        Request build2 = this.f.newBuilder().header(HttpHeaders.UPGRADE, "websocket").header("Connection", HttpHeaders.UPGRADE).header("Sec-WebSocket-Key", this.h).header("Sec-WebSocket-Version", Constants.VIA_REPORT_TYPE_JOININ_GROUP).build();
        this.i = Internal.instance.newWebSocketCall(build, build2);
        this.i.enqueue(new b(this, build2, pingIntervalMillis));
    }

    void a(Response response) throws ProtocolException {
        if (response.code() != 101) {
            throw new ProtocolException("Expected HTTP 101 response but was '" + response.code() + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + response.message() + "'");
        }
        String header = response.header("Connection");
        if (HttpHeaders.UPGRADE.equalsIgnoreCase(header)) {
            header = response.header(HttpHeaders.UPGRADE);
            if ("websocket".equalsIgnoreCase(header)) {
                header = response.header("Sec-WebSocket-Accept");
                String base64 = ByteString.encodeUtf8(this.h + "258EAFA5-E914-47DA-95CA-C5AB0DC85B11").sha1().base64();
                if (!base64.equals(header)) {
                    throw new ProtocolException("Expected 'Sec-WebSocket-Accept' header value '" + base64 + "' but was '" + header + "'");
                }
                return;
            }
            throw new ProtocolException("Expected 'Upgrade' header value 'websocket' but was '" + header + "'");
        }
        throw new ProtocolException("Expected 'Connection' header value 'Upgrade' but was '" + header + "'");
    }

    public void initReaderAndWriter(String str, long j, Streams streams) throws IOException {
        synchronized (this) {
            this.n = streams;
            this.l = new c(streams.client, streams.sink, this.g);
            this.m = new ScheduledThreadPoolExecutor(1, Util.threadFactory(str, false));
            if (j != 0) {
                this.m.scheduleAtFixedRate(new d(this), j, j, TimeUnit.MILLISECONDS);
            }
            if (!this.p.isEmpty()) {
                c();
            }
        }
        this.k = new WebSocketReader(streams.client, streams.source, this);
    }

    public void loopReader() throws IOException {
        while (this.t == -1) {
            this.k.a();
        }
    }

    public void onReadMessage(String str) throws IOException {
        this.a.onMessage((WebSocket) this, str);
    }

    public void onReadMessage(ByteString byteString) throws IOException {
        this.a.onMessage((WebSocket) this, byteString);
    }

    public synchronized void onReadPing(ByteString byteString) {
        if (!(this.v || (this.r && this.p.isEmpty()))) {
            this.o.add(byteString);
            c();
            this.b++;
        }
    }

    public synchronized void onReadPong(ByteString byteString) {
        this.c++;
    }

    public void onReadClose(int i, String str) {
        Closeable closeable;
        if (i == -1) {
            throw new IllegalArgumentException();
        }
        synchronized (this) {
            if (this.t != -1) {
                throw new IllegalStateException("already closed");
            }
            this.t = i;
            this.u = str;
            if (this.r && this.p.isEmpty()) {
                Streams streams = this.n;
                this.n = null;
                if (this.s != null) {
                    this.s.cancel(false);
                }
                this.m.shutdown();
                closeable = streams;
            } else {
                closeable = null;
            }
        }
        try {
            this.a.onClosing(this, i, str);
            if (closeable != null) {
                this.a.onClosed(this, i, str);
            }
            Util.closeQuietly(closeable);
        } catch (Throwable th) {
            Util.closeQuietly(closeable);
        }
    }

    public boolean send(String str) {
        if (str != null) {
            return a(ByteString.encodeUtf8(str), 1);
        }
        throw new NullPointerException("text == null");
    }

    public boolean send(ByteString byteString) {
        if (byteString != null) {
            return a(byteString, 2);
        }
        throw new NullPointerException("bytes == null");
    }

    private synchronized boolean a(ByteString byteString, int i) {
        boolean z = false;
        synchronized (this) {
            if (!(this.v || this.r)) {
                if (this.q + ((long) byteString.size()) > 16777216) {
                    close(1001, null);
                } else {
                    this.q += (long) byteString.size();
                    this.p.add(new c(i, byteString));
                    c();
                    z = true;
                }
            }
        }
        return z;
    }

    public boolean close(int i, String str) {
        return a(i, str, 60000);
    }

    synchronized boolean a(int i, String str, long j) {
        boolean z = true;
        synchronized (this) {
            WebSocketProtocol.b(i);
            ByteString byteString = null;
            if (str != null) {
                byteString = ByteString.encodeUtf8(str);
                if (((long) byteString.size()) > 123) {
                    throw new IllegalArgumentException("reason.size() > 123: " + str);
                }
            }
            if (this.v || this.r) {
                z = false;
            } else {
                this.r = true;
                this.p.add(new b(i, byteString, j));
                c();
            }
        }
        return z;
    }

    private void c() {
        if (!d && !Thread.holdsLock(this)) {
            throw new AssertionError();
        } else if (this.m != null) {
            this.m.execute(this.j);
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    boolean a() throws java.io.IOException {
        /*
        r15 = this;
        r3 = 0;
        r8 = -1;
        r5 = 0;
        monitor-enter(r15);
        r2 = r15.v;	 Catch:{ all -> 0x0060 }
        if (r2 == 0) goto L_0x000b;
    L_0x0008:
        monitor-exit(r15);	 Catch:{ all -> 0x0060 }
        r2 = r3;
    L_0x000a:
        return r2;
    L_0x000b:
        r9 = r15.l;	 Catch:{ all -> 0x0060 }
        r2 = r15.o;	 Catch:{ all -> 0x0060 }
        r2 = r2.poll();	 Catch:{ all -> 0x0060 }
        r2 = (okio.ByteString) r2;	 Catch:{ all -> 0x0060 }
        if (r2 != 0) goto L_0x00ba;
    L_0x0017:
        r4 = r15.p;	 Catch:{ all -> 0x0060 }
        r4 = r4.poll();	 Catch:{ all -> 0x0060 }
        r6 = r4 instanceof okhttp3.internal.ws.RealWebSocket.b;	 Catch:{ all -> 0x0060 }
        if (r6 == 0) goto L_0x005b;
    L_0x0021:
        r7 = r15.t;	 Catch:{ all -> 0x0060 }
        r6 = r15.u;	 Catch:{ all -> 0x0060 }
        if (r7 == r8) goto L_0x0041;
    L_0x0027:
        r3 = r15.n;	 Catch:{ all -> 0x0060 }
        r5 = 0;
        r15.n = r5;	 Catch:{ all -> 0x0060 }
        r5 = r15.m;	 Catch:{ all -> 0x0060 }
        r5.shutdown();	 Catch:{ all -> 0x0060 }
        r5 = r6;
        r6 = r7;
        r14 = r3;
        r3 = r4;
        r4 = r14;
    L_0x0036:
        monitor-exit(r15);	 Catch:{ all -> 0x0060 }
        if (r2 == 0) goto L_0x0063;
    L_0x0039:
        r9.b(r2);	 Catch:{ all -> 0x0095 }
    L_0x003c:
        r2 = 1;
        okhttp3.internal.Util.closeQuietly(r4);
        goto L_0x000a;
    L_0x0041:
        r8 = r15.m;	 Catch:{ all -> 0x0060 }
        r10 = new okhttp3.internal.ws.RealWebSocket$a;	 Catch:{ all -> 0x0060 }
        r10.<init>(r15);	 Catch:{ all -> 0x0060 }
        r0 = r4;
        r0 = (okhttp3.internal.ws.RealWebSocket.b) r0;	 Catch:{ all -> 0x0060 }
        r3 = r0;
        r12 = r3.c;	 Catch:{ all -> 0x0060 }
        r3 = java.util.concurrent.TimeUnit.MILLISECONDS;	 Catch:{ all -> 0x0060 }
        r3 = r8.schedule(r10, r12, r3);	 Catch:{ all -> 0x0060 }
        r15.s = r3;	 Catch:{ all -> 0x0060 }
        r3 = r4;
        r4 = r5;
        r5 = r6;
        r6 = r7;
        goto L_0x0036;
    L_0x005b:
        if (r4 != 0) goto L_0x00b5;
    L_0x005d:
        monitor-exit(r15);	 Catch:{ all -> 0x0060 }
        r2 = r3;
        goto L_0x000a;
    L_0x0060:
        r2 = move-exception;
        monitor-exit(r15);	 Catch:{ all -> 0x0060 }
        throw r2;
    L_0x0063:
        r2 = r3 instanceof okhttp3.internal.ws.RealWebSocket.c;	 Catch:{ all -> 0x0095 }
        if (r2 == 0) goto L_0x009a;
    L_0x0067:
        r0 = r3;
        r0 = (okhttp3.internal.ws.RealWebSocket.c) r0;	 Catch:{ all -> 0x0095 }
        r2 = r0;
        r2 = r2.b;	 Catch:{ all -> 0x0095 }
        r3 = (okhttp3.internal.ws.RealWebSocket.c) r3;	 Catch:{ all -> 0x0095 }
        r3 = r3.a;	 Catch:{ all -> 0x0095 }
        r5 = r2.size();	 Catch:{ all -> 0x0095 }
        r6 = (long) r5;	 Catch:{ all -> 0x0095 }
        r3 = r9.a(r3, r6);	 Catch:{ all -> 0x0095 }
        r3 = okio.Okio.buffer(r3);	 Catch:{ all -> 0x0095 }
        r3.write(r2);	 Catch:{ all -> 0x0095 }
        r3.close();	 Catch:{ all -> 0x0095 }
        monitor-enter(r15);	 Catch:{ all -> 0x0095 }
        r6 = r15.q;	 Catch:{ all -> 0x0092 }
        r2 = r2.size();	 Catch:{ all -> 0x0092 }
        r2 = (long) r2;	 Catch:{ all -> 0x0092 }
        r2 = r6 - r2;
        r15.q = r2;	 Catch:{ all -> 0x0092 }
        monitor-exit(r15);	 Catch:{ all -> 0x0092 }
        goto L_0x003c;
    L_0x0092:
        r2 = move-exception;
        monitor-exit(r15);	 Catch:{ all -> 0x0092 }
        throw r2;	 Catch:{ all -> 0x0095 }
    L_0x0095:
        r2 = move-exception;
        okhttp3.internal.Util.closeQuietly(r4);
        throw r2;
    L_0x009a:
        r2 = r3 instanceof okhttp3.internal.ws.RealWebSocket.b;	 Catch:{ all -> 0x0095 }
        if (r2 == 0) goto L_0x00af;
    L_0x009e:
        r3 = (okhttp3.internal.ws.RealWebSocket.b) r3;	 Catch:{ all -> 0x0095 }
        r2 = r3.a;	 Catch:{ all -> 0x0095 }
        r3 = r3.b;	 Catch:{ all -> 0x0095 }
        r9.a(r2, r3);	 Catch:{ all -> 0x0095 }
        if (r4 == 0) goto L_0x003c;
    L_0x00a9:
        r2 = r15.a;	 Catch:{ all -> 0x0095 }
        r2.onClosed(r15, r6, r5);	 Catch:{ all -> 0x0095 }
        goto L_0x003c;
    L_0x00af:
        r2 = new java.lang.AssertionError;	 Catch:{ all -> 0x0095 }
        r2.<init>();	 Catch:{ all -> 0x0095 }
        throw r2;	 Catch:{ all -> 0x0095 }
    L_0x00b5:
        r6 = r8;
        r3 = r4;
        r4 = r5;
        goto L_0x0036;
    L_0x00ba:
        r4 = r5;
        r6 = r8;
        r3 = r5;
        goto L_0x0036;
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.ws.RealWebSocket.a():boolean");
    }

    void b() {
        synchronized (this) {
            if (this.v) {
                return;
            }
            c cVar = this.l;
            try {
                cVar.a(ByteString.EMPTY);
            } catch (Exception e) {
                failWebSocket(e, null);
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void failWebSocket(java.lang.Exception r4, @javax.annotation.Nullable okhttp3.Response r5) {
        /*
        r3 = this;
        monitor-enter(r3);
        r0 = r3.v;	 Catch:{ all -> 0x002c }
        if (r0 == 0) goto L_0x0007;
    L_0x0005:
        monitor-exit(r3);	 Catch:{ all -> 0x002c }
    L_0x0006:
        return;
    L_0x0007:
        r0 = 1;
        r3.v = r0;	 Catch:{ all -> 0x002c }
        r1 = r3.n;	 Catch:{ all -> 0x002c }
        r0 = 0;
        r3.n = r0;	 Catch:{ all -> 0x002c }
        r0 = r3.s;	 Catch:{ all -> 0x002c }
        if (r0 == 0) goto L_0x0019;
    L_0x0013:
        r0 = r3.s;	 Catch:{ all -> 0x002c }
        r2 = 0;
        r0.cancel(r2);	 Catch:{ all -> 0x002c }
    L_0x0019:
        r0 = r3.m;	 Catch:{ all -> 0x002c }
        if (r0 == 0) goto L_0x0022;
    L_0x001d:
        r0 = r3.m;	 Catch:{ all -> 0x002c }
        r0.shutdown();	 Catch:{ all -> 0x002c }
    L_0x0022:
        monitor-exit(r3);	 Catch:{ all -> 0x002c }
        r0 = r3.a;	 Catch:{ all -> 0x002f }
        r0.onFailure(r3, r4, r5);	 Catch:{ all -> 0x002f }
        okhttp3.internal.Util.closeQuietly(r1);
        goto L_0x0006;
    L_0x002c:
        r0 = move-exception;
        monitor-exit(r3);	 Catch:{ all -> 0x002c }
        throw r0;
    L_0x002f:
        r0 = move-exception;
        okhttp3.internal.Util.closeQuietly(r1);
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.ws.RealWebSocket.failWebSocket(java.lang.Exception, okhttp3.Response):void");
    }
}
