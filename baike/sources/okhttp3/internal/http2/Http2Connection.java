package okhttp3.internal.http2;

import android.support.v4.internal.view.SupportMenu;
import java.io.Closeable;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import okhttp3.Protocol;
import okhttp3.internal.NamedRunnable;
import okhttp3.internal.Util;
import okio.Buffer;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.ByteString;
import okio.Okio;

public final class Http2Connection implements Closeable {
    static final ExecutorService a = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60, TimeUnit.SECONDS, new SynchronousQueue(), Util.threadFactory("OkHttp Http2Connection", true));
    static final /* synthetic */ boolean s;
    final boolean b;
    final Listener c;
    final Map<Integer, Http2Stream> d = new LinkedHashMap();
    final String e;
    int f;
    int g;
    boolean h;
    final PushObserver i;
    long j = 0;
    long k;
    Settings l = new Settings();
    final Settings m = new Settings();
    boolean n = false;
    final Socket o;
    final n p;
    final a q;
    final Set<Integer> r = new LinkedHashSet();
    private final ExecutorService t;
    private Map<Integer, p> u;
    private int v;

    public static abstract class Listener {
        public static final Listener REFUSE_INCOMING_STREAMS = new i();

        public abstract void onStream(Http2Stream http2Stream) throws IOException;

        public void onSettings(Http2Connection http2Connection) {
        }
    }

    public static class Builder {
        Socket a;
        String b;
        BufferedSource c;
        BufferedSink d;
        Listener e = Listener.REFUSE_INCOMING_STREAMS;
        PushObserver f = PushObserver.CANCEL;
        boolean g;

        public Builder(boolean z) {
            this.g = z;
        }

        public Builder socket(Socket socket) throws IOException {
            return socket(socket, ((InetSocketAddress) socket.getRemoteSocketAddress()).getHostName(), Okio.buffer(Okio.source(socket)), Okio.buffer(Okio.sink(socket)));
        }

        public Builder socket(Socket socket, String str, BufferedSource bufferedSource, BufferedSink bufferedSink) {
            this.a = socket;
            this.b = str;
            this.c = bufferedSource;
            this.d = bufferedSink;
            return this;
        }

        public Builder listener(Listener listener) {
            this.e = listener;
            return this;
        }

        public Builder pushObserver(PushObserver pushObserver) {
            this.f = pushObserver;
            return this;
        }

        public Http2Connection build() {
            return new Http2Connection(this);
        }
    }

    class a extends NamedRunnable implements b {
        final m a;
        final /* synthetic */ Http2Connection c;

        a(Http2Connection http2Connection, m mVar) {
            this.c = http2Connection;
            super("OkHttp %s", http2Connection.e);
            this.a = mVar;
        }

        protected void execute() {
            ErrorCode errorCode;
            Throwable th;
            ErrorCode errorCode2 = ErrorCode.INTERNAL_ERROR;
            ErrorCode errorCode3 = ErrorCode.INTERNAL_ERROR;
            try {
                this.a.readConnectionPreface(this);
                do {
                } while (this.a.nextFrame(false, this));
                try {
                    this.c.a(ErrorCode.NO_ERROR, ErrorCode.CANCEL);
                } catch (IOException e) {
                }
                Util.closeQuietly(this.a);
            } catch (IOException e2) {
                errorCode = ErrorCode.PROTOCOL_ERROR;
                try {
                    this.c.a(errorCode, ErrorCode.PROTOCOL_ERROR);
                } catch (IOException e3) {
                }
                Util.closeQuietly(this.a);
            } catch (Throwable th2) {
                th = th2;
                this.c.a(errorCode, errorCode3);
                Util.closeQuietly(this.a);
                throw th;
            }
        }

        public void data(boolean z, int i, BufferedSource bufferedSource, int i2) throws IOException {
            if (this.c.d(i)) {
                this.c.a(i, bufferedSource, i2, z);
                return;
            }
            Http2Stream a = this.c.a(i);
            if (a == null) {
                this.c.a(i, ErrorCode.PROTOCOL_ERROR);
                bufferedSource.skip((long) i2);
                return;
            }
            a.a(bufferedSource, i2);
            if (z) {
                a.a();
            }
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void headers(boolean r9, int r10, int r11, java.util.List<okhttp3.internal.http2.Header> r12) {
            /*
            r8 = this;
            r0 = r8.c;
            r0 = r0.d(r10);
            if (r0 == 0) goto L_0x000e;
        L_0x0008:
            r0 = r8.c;
            r0.a(r10, r12, r9);
        L_0x000d:
            return;
        L_0x000e:
            r6 = r8.c;
            monitor-enter(r6);
            r0 = r8.c;	 Catch:{ all -> 0x0021 }
            r0 = r0.a(r10);	 Catch:{ all -> 0x0021 }
            if (r0 != 0) goto L_0x0071;
        L_0x0019:
            r0 = r8.c;	 Catch:{ all -> 0x0021 }
            r0 = r0.h;	 Catch:{ all -> 0x0021 }
            if (r0 == 0) goto L_0x0024;
        L_0x001f:
            monitor-exit(r6);	 Catch:{ all -> 0x0021 }
            goto L_0x000d;
        L_0x0021:
            r0 = move-exception;
            monitor-exit(r6);	 Catch:{ all -> 0x0021 }
            throw r0;
        L_0x0024:
            r0 = r8.c;	 Catch:{ all -> 0x0021 }
            r0 = r0.f;	 Catch:{ all -> 0x0021 }
            if (r10 > r0) goto L_0x002c;
        L_0x002a:
            monitor-exit(r6);	 Catch:{ all -> 0x0021 }
            goto L_0x000d;
        L_0x002c:
            r0 = r10 % 2;
            r1 = r8.c;	 Catch:{ all -> 0x0021 }
            r1 = r1.g;	 Catch:{ all -> 0x0021 }
            r1 = r1 % 2;
            if (r0 != r1) goto L_0x0038;
        L_0x0036:
            monitor-exit(r6);	 Catch:{ all -> 0x0021 }
            goto L_0x000d;
        L_0x0038:
            r0 = new okhttp3.internal.http2.Http2Stream;	 Catch:{ all -> 0x0021 }
            r2 = r8.c;	 Catch:{ all -> 0x0021 }
            r3 = 0;
            r1 = r10;
            r4 = r9;
            r5 = r12;
            r0.<init>(r1, r2, r3, r4, r5);	 Catch:{ all -> 0x0021 }
            r1 = r8.c;	 Catch:{ all -> 0x0021 }
            r1.f = r10;	 Catch:{ all -> 0x0021 }
            r1 = r8.c;	 Catch:{ all -> 0x0021 }
            r1 = r1.d;	 Catch:{ all -> 0x0021 }
            r2 = java.lang.Integer.valueOf(r10);	 Catch:{ all -> 0x0021 }
            r1.put(r2, r0);	 Catch:{ all -> 0x0021 }
            r1 = okhttp3.internal.http2.Http2Connection.a;	 Catch:{ all -> 0x0021 }
            r2 = new okhttp3.internal.http2.j;	 Catch:{ all -> 0x0021 }
            r3 = "OkHttp %s stream %d";
            r4 = 2;
            r4 = new java.lang.Object[r4];	 Catch:{ all -> 0x0021 }
            r5 = 0;
            r7 = r8.c;	 Catch:{ all -> 0x0021 }
            r7 = r7.e;	 Catch:{ all -> 0x0021 }
            r4[r5] = r7;	 Catch:{ all -> 0x0021 }
            r5 = 1;
            r7 = java.lang.Integer.valueOf(r10);	 Catch:{ all -> 0x0021 }
            r4[r5] = r7;	 Catch:{ all -> 0x0021 }
            r2.<init>(r8, r3, r4, r0);	 Catch:{ all -> 0x0021 }
            r1.execute(r2);	 Catch:{ all -> 0x0021 }
            monitor-exit(r6);	 Catch:{ all -> 0x0021 }
            goto L_0x000d;
        L_0x0071:
            monitor-exit(r6);	 Catch:{ all -> 0x0021 }
            r0.a(r12);
            if (r9 == 0) goto L_0x000d;
        L_0x0077:
            r0.a();
            goto L_0x000d;
            */
            throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.http2.Http2Connection.a.headers(boolean, int, int, java.util.List):void");
        }

        public void rstStream(int i, ErrorCode errorCode) {
            if (this.c.d(i)) {
                this.c.c(i, errorCode);
                return;
            }
            Http2Stream b = this.c.b(i);
            if (b != null) {
                b.a(errorCode);
            }
        }

        public void settings(boolean z, Settings settings) {
            Http2Stream[] http2StreamArr;
            long j;
            synchronized (this.c) {
                int d = this.c.m.d();
                if (z) {
                    this.c.m.a();
                }
                this.c.m.a(settings);
                a(settings);
                int d2 = this.c.m.d();
                if (d2 == -1 || d2 == d) {
                    http2StreamArr = null;
                    j = 0;
                } else {
                    long j2 = (long) (d2 - d);
                    if (!this.c.n) {
                        this.c.a(j2);
                        this.c.n = true;
                    }
                    if (this.c.d.isEmpty()) {
                        j = j2;
                        http2StreamArr = null;
                    } else {
                        j = j2;
                        http2StreamArr = (Http2Stream[]) this.c.d.values().toArray(new Http2Stream[this.c.d.size()]);
                    }
                }
                Http2Connection.a.execute(new k(this, "OkHttp %s settings", this.c.e));
            }
            if (http2StreamArr != null && j != 0) {
                for (Http2Stream http2Stream : http2StreamArr) {
                    synchronized (http2Stream) {
                        http2Stream.a(j);
                    }
                }
            }
        }

        private void a(Settings settings) {
            Http2Connection.a.execute(new l(this, "OkHttp %s ACK Settings", new Object[]{this.c.e}, settings));
        }

        public void ackSettings() {
        }

        public void ping(boolean z, int i, int i2) {
            if (z) {
                p c = this.c.c(i);
                if (c != null) {
                    c.b();
                    return;
                }
                return;
            }
            this.c.a(true, i, i2, null);
        }

        public void goAway(int i, ErrorCode errorCode, ByteString byteString) {
            if (byteString.size() > 0) {
            }
            synchronized (this.c) {
                Http2Stream[] http2StreamArr = (Http2Stream[]) this.c.d.values().toArray(new Http2Stream[this.c.d.size()]);
                this.c.h = true;
            }
            for (Http2Stream http2Stream : http2StreamArr) {
                if (http2Stream.getId() > i && http2Stream.isLocallyInitiated()) {
                    http2Stream.a(ErrorCode.REFUSED_STREAM);
                    this.c.b(http2Stream.getId());
                }
            }
        }

        public void windowUpdate(int i, long j) {
            if (i == 0) {
                synchronized (this.c) {
                    Http2Connection http2Connection = this.c;
                    http2Connection.k += j;
                    this.c.notifyAll();
                }
                return;
            }
            Http2Stream a = this.c.a(i);
            if (a != null) {
                synchronized (a) {
                    a.a(j);
                }
            }
        }

        public void priority(int i, int i2, int i3, boolean z) {
        }

        public void pushPromise(int i, int i2, List<Header> list) {
            this.c.a(i2, (List) list);
        }

        public void alternateService(int i, String str, ByteString byteString, String str2, int i2, long j) {
        }
    }

    static {
        boolean z;
        if (Http2Connection.class.desiredAssertionStatus()) {
            z = false;
        } else {
            z = true;
        }
        s = z;
    }

    Http2Connection(Builder builder) {
        int i = 2;
        this.i = builder.f;
        this.b = builder.g;
        this.c = builder.e;
        this.g = builder.g ? 1 : 2;
        if (builder.g) {
            this.g += 2;
        }
        if (builder.g) {
            i = 1;
        }
        this.v = i;
        if (builder.g) {
            this.l.a(7, 16777216);
        }
        this.e = builder.b;
        this.t = new ThreadPoolExecutor(0, 1, 60, TimeUnit.SECONDS, new LinkedBlockingQueue(), Util.threadFactory(Util.format("OkHttp %s Push Observer", new Object[]{this.e}), true));
        this.m.a(7, SupportMenu.USER_MASK);
        this.m.a(5, 16384);
        this.k = (long) this.m.d();
        this.o = builder.a;
        this.p = new n(builder.d, this.b);
        this.q = new a(this, new m(builder.c, this.b));
    }

    public Protocol getProtocol() {
        return Protocol.HTTP_2;
    }

    public synchronized int openStreamCount() {
        return this.d.size();
    }

    synchronized Http2Stream a(int i) {
        return (Http2Stream) this.d.get(Integer.valueOf(i));
    }

    synchronized Http2Stream b(int i) {
        Http2Stream http2Stream;
        http2Stream = (Http2Stream) this.d.remove(Integer.valueOf(i));
        notifyAll();
        return http2Stream;
    }

    public synchronized int maxConcurrentStreams() {
        return this.m.c(Integer.MAX_VALUE);
    }

    public Http2Stream pushStream(int i, List<Header> list, boolean z) throws IOException {
        if (!this.b) {
            return b(i, list, z);
        }
        throw new IllegalStateException("Client cannot push requests.");
    }

    public Http2Stream newStream(List<Header> list, boolean z) throws IOException {
        return b(0, list, z);
    }

    private Http2Stream b(int i, List<Header> list, boolean z) throws IOException {
        Http2Stream http2Stream;
        Object obj = null;
        boolean z2 = !z;
        synchronized (this.p) {
            synchronized (this) {
                if (this.h) {
                    throw new ConnectionShutdownException();
                }
                int i2 = this.g;
                this.g += 2;
                http2Stream = new Http2Stream(i2, this, z2, false, list);
                if (!z || this.k == 0 || http2Stream.b == 0) {
                    obj = 1;
                }
                if (http2Stream.isOpen()) {
                    this.d.put(Integer.valueOf(i2), http2Stream);
                }
            }
            if (i == 0) {
                this.p.synStream(z2, i2, i, list);
            } else if (this.b) {
                throw new IllegalArgumentException("client streams shouldn't have associated stream IDs");
            } else {
                this.p.pushPromise(i, i2, list);
            }
        }
        if (obj != null) {
            this.p.flush();
        }
        return http2Stream;
    }

    void a(int i, boolean z, List<Header> list) throws IOException {
        this.p.synReply(z, i, list);
    }

    public void writeData(int i, boolean z, Buffer buffer, long j) throws IOException {
        if (j == 0) {
            this.p.data(z, i, buffer, 0);
            return;
        }
        while (j > 0) {
            int min;
            boolean z2;
            synchronized (this) {
                while (this.k <= 0) {
                    try {
                        if (this.d.containsKey(Integer.valueOf(i))) {
                            wait();
                        } else {
                            throw new IOException("stream closed");
                        }
                    } catch (InterruptedException e) {
                        throw new InterruptedIOException();
                    }
                }
                min = Math.min((int) Math.min(j, this.k), this.p.maxDataLength());
                this.k -= (long) min;
            }
            j -= (long) min;
            n nVar = this.p;
            if (z && j == 0) {
                z2 = true;
            } else {
                z2 = false;
            }
            nVar.data(z2, i, buffer, min);
        }
    }

    void a(long j) {
        this.k += j;
        if (j > 0) {
            notifyAll();
        }
    }

    void a(int i, ErrorCode errorCode) {
        a.execute(new b(this, "OkHttp %s stream %d", new Object[]{this.e, Integer.valueOf(i)}, i, errorCode));
    }

    void b(int i, ErrorCode errorCode) throws IOException {
        this.p.rstStream(i, errorCode);
    }

    void a(int i, long j) {
        a.execute(new c(this, "OkHttp Window Update %s stream %d", new Object[]{this.e, Integer.valueOf(i)}, i, j));
    }

    public p ping() throws IOException {
        int i;
        p pVar = new p();
        synchronized (this) {
            if (this.h) {
                throw new ConnectionShutdownException();
            }
            i = this.v;
            this.v += 2;
            if (this.u == null) {
                this.u = new LinkedHashMap();
            }
            this.u.put(Integer.valueOf(i), pVar);
        }
        b(false, i, 1330343787, pVar);
        return pVar;
    }

    void a(boolean z, int i, int i2, p pVar) {
        a.execute(new d(this, "OkHttp %s ping %08x%08x", new Object[]{this.e, Integer.valueOf(i), Integer.valueOf(i2)}, z, i, i2, pVar));
    }

    void b(boolean z, int i, int i2, p pVar) throws IOException {
        synchronized (this.p) {
            if (pVar != null) {
                pVar.a();
            }
            this.p.ping(z, i, i2);
        }
    }

    synchronized p c(int i) {
        return this.u != null ? (p) this.u.remove(Integer.valueOf(i)) : null;
    }

    public void flush() throws IOException {
        this.p.flush();
    }

    public void shutdown(ErrorCode errorCode) throws IOException {
        synchronized (this.p) {
            synchronized (this) {
                if (this.h) {
                    return;
                }
                this.h = true;
                int i = this.f;
                this.p.goAway(i, errorCode, Util.EMPTY_BYTE_ARRAY);
            }
        }
    }

    public void close() throws IOException {
        a(ErrorCode.NO_ERROR, ErrorCode.CANCEL);
    }

    void a(ErrorCode errorCode, ErrorCode errorCode2) throws IOException {
        if (s || !Thread.holdsLock(this)) {
            IOException iOException;
            IOException e;
            Http2Stream[] http2StreamArr;
            p[] pVarArr;
            try {
                shutdown(errorCode);
                iOException = null;
            } catch (IOException e2) {
                iOException = e2;
            }
            synchronized (this) {
                if (this.d.isEmpty()) {
                    http2StreamArr = null;
                } else {
                    Http2Stream[] http2StreamArr2 = (Http2Stream[]) this.d.values().toArray(new Http2Stream[this.d.size()]);
                    this.d.clear();
                    http2StreamArr = http2StreamArr2;
                }
                if (this.u != null) {
                    p[] pVarArr2 = (p[]) this.u.values().toArray(new p[this.u.size()]);
                    this.u = null;
                    pVarArr = pVarArr2;
                } else {
                    pVarArr = null;
                }
            }
            if (http2StreamArr != null) {
                e2 = iOException;
                for (Http2Stream close : http2StreamArr) {
                    try {
                        close.close(errorCode2);
                    } catch (IOException iOException2) {
                        if (e2 != null) {
                            e2 = iOException2;
                        }
                    }
                }
                iOException2 = e2;
            }
            if (pVarArr != null) {
                for (p c : pVarArr) {
                    c.c();
                }
            }
            try {
                this.p.close();
                e2 = iOException2;
            } catch (IOException e3) {
                e2 = e3;
                if (iOException2 != null) {
                    e2 = iOException2;
                }
            }
            try {
                this.o.close();
            } catch (IOException e4) {
                e2 = e4;
            }
            if (e2 != null) {
                throw e2;
            }
            return;
        }
        throw new AssertionError();
    }

    public void start() throws IOException {
        a(true);
    }

    void a(boolean z) throws IOException {
        if (z) {
            this.p.connectionPreface();
            this.p.settings(this.l);
            int d = this.l.d();
            if (d != SupportMenu.USER_MASK) {
                this.p.windowUpdate(0, (long) (d - SupportMenu.USER_MASK));
            }
        }
        new Thread(this.q).start();
    }

    public void setSettings(Settings settings) throws IOException {
        synchronized (this.p) {
            synchronized (this) {
                if (this.h) {
                    throw new ConnectionShutdownException();
                }
                this.l.a(settings);
                this.p.settings(settings);
            }
        }
    }

    public synchronized boolean isShutdown() {
        return this.h;
    }

    boolean d(int i) {
        return i != 0 && (i & 1) == 0;
    }

    void a(int i, List<Header> list) {
        synchronized (this) {
            if (this.r.contains(Integer.valueOf(i))) {
                a(i, ErrorCode.PROTOCOL_ERROR);
                return;
            }
            this.r.add(Integer.valueOf(i));
            this.t.execute(new e(this, "OkHttp %s Push Request[%s]", new Object[]{this.e, Integer.valueOf(i)}, i, list));
        }
    }

    void a(int i, List<Header> list, boolean z) {
        this.t.execute(new f(this, "OkHttp %s Push Headers[%s]", new Object[]{this.e, Integer.valueOf(i)}, i, list, z));
    }

    void a(int i, BufferedSource bufferedSource, int i2, boolean z) throws IOException {
        Buffer buffer = new Buffer();
        bufferedSource.require((long) i2);
        bufferedSource.read(buffer, (long) i2);
        if (buffer.size() != ((long) i2)) {
            throw new IOException(buffer.size() + " != " + i2);
        }
        this.t.execute(new g(this, "OkHttp %s Push Data[%s]", new Object[]{this.e, Integer.valueOf(i)}, i, buffer, i2, z));
    }

    void c(int i, ErrorCode errorCode) {
        this.t.execute(new h(this, "OkHttp %s Push Reset[%s]", new Object[]{this.e, Integer.valueOf(i)}, i, errorCode));
    }
}
