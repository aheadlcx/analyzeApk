package okhttp3.internal.http2;

import android.support.v4.media.session.PlaybackStateCompat;
import java.io.EOFException;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;
import okio.AsyncTimeout;
import okio.Buffer;
import okio.BufferedSource;
import okio.Sink;
import okio.Source;
import okio.Timeout;

public final class Http2Stream {
    static final /* synthetic */ boolean i = (!Http2Stream.class.desiredAssertionStatus());
    long a = 0;
    long b;
    final int c;
    final Http2Connection d;
    final a e;
    final c f = new c(this);
    final c g = new c(this);
    ErrorCode h = null;
    private final List<Header> j;
    private List<Header> k;
    private boolean l;
    private final b m;

    final class a implements Sink {
        static final /* synthetic */ boolean c = (!Http2Stream.class.desiredAssertionStatus());
        boolean a;
        boolean b;
        final /* synthetic */ Http2Stream d;
        private final Buffer e = new Buffer();

        a(Http2Stream http2Stream) {
            this.d = http2Stream;
        }

        public void write(Buffer buffer, long j) throws IOException {
            if (c || !Thread.holdsLock(this.d)) {
                this.e.write(buffer, j);
                while (this.e.size() >= PlaybackStateCompat.ACTION_PREPARE) {
                    a(false);
                }
                return;
            }
            throw new AssertionError();
        }

        private void a(boolean z) throws IOException {
            synchronized (this.d) {
                this.d.g.enter();
                while (this.d.b <= 0 && !this.b && !this.a && this.d.h == null) {
                    try {
                        this.d.d();
                    } catch (Throwable th) {
                        this.d.g.exitAndThrowIfTimedOut();
                    }
                }
                this.d.g.exitAndThrowIfTimedOut();
                this.d.c();
                long min = Math.min(this.d.b, this.e.size());
                Http2Stream http2Stream = this.d;
                http2Stream.b -= min;
            }
            this.d.g.enter();
            try {
                Http2Connection http2Connection = this.d.d;
                int i = this.d.c;
                boolean z2 = z && min == this.e.size();
                http2Connection.writeData(i, z2, this.e, min);
            } finally {
                this.d.g.exitAndThrowIfTimedOut();
            }
        }

        public void flush() throws IOException {
            if (c || !Thread.holdsLock(this.d)) {
                synchronized (this.d) {
                    this.d.c();
                }
                while (this.e.size() > 0) {
                    a(false);
                    this.d.d.flush();
                }
                return;
            }
            throw new AssertionError();
        }

        public Timeout timeout() {
            return this.d.g;
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void close() throws java.io.IOException {
            /*
            r6 = this;
            r4 = 0;
            r2 = 1;
            r0 = c;
            if (r0 != 0) goto L_0x0015;
        L_0x0007:
            r0 = r6.d;
            r0 = java.lang.Thread.holdsLock(r0);
            if (r0 == 0) goto L_0x0015;
        L_0x000f:
            r0 = new java.lang.AssertionError;
            r0.<init>();
            throw r0;
        L_0x0015:
            r1 = r6.d;
            monitor-enter(r1);
            r0 = r6.a;	 Catch:{ all -> 0x003f }
            if (r0 == 0) goto L_0x001e;
        L_0x001c:
            monitor-exit(r1);	 Catch:{ all -> 0x003f }
        L_0x001d:
            return;
        L_0x001e:
            monitor-exit(r1);	 Catch:{ all -> 0x003f }
            r0 = r6.d;
            r0 = r0.e;
            r0 = r0.b;
            if (r0 != 0) goto L_0x004e;
        L_0x0027:
            r0 = r6.e;
            r0 = r0.size();
            r0 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1));
            if (r0 <= 0) goto L_0x0042;
        L_0x0031:
            r0 = r6.e;
            r0 = r0.size();
            r0 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1));
            if (r0 <= 0) goto L_0x004e;
        L_0x003b:
            r6.a(r2);
            goto L_0x0031;
        L_0x003f:
            r0 = move-exception;
            monitor-exit(r1);	 Catch:{ all -> 0x003f }
            throw r0;
        L_0x0042:
            r0 = r6.d;
            r0 = r0.d;
            r1 = r6.d;
            r1 = r1.c;
            r3 = 0;
            r0.writeData(r1, r2, r3, r4);
        L_0x004e:
            r1 = r6.d;
            monitor-enter(r1);
            r0 = 1;
            r6.a = r0;	 Catch:{ all -> 0x0062 }
            monitor-exit(r1);	 Catch:{ all -> 0x0062 }
            r0 = r6.d;
            r0 = r0.d;
            r0.flush();
            r0 = r6.d;
            r0.b();
            goto L_0x001d;
        L_0x0062:
            r0 = move-exception;
            monitor-exit(r1);	 Catch:{ all -> 0x0062 }
            throw r0;
            */
            throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.http2.Http2Stream.a.close():void");
        }
    }

    private final class b implements Source {
        static final /* synthetic */ boolean c = (!Http2Stream.class.desiredAssertionStatus());
        boolean a;
        boolean b;
        final /* synthetic */ Http2Stream d;
        private final Buffer e = new Buffer();
        private final Buffer f = new Buffer();
        private final long g;

        b(Http2Stream http2Stream, long j) {
            this.d = http2Stream;
            this.g = j;
        }

        public long read(Buffer buffer, long j) throws IOException {
            if (j < 0) {
                throw new IllegalArgumentException("byteCount < 0: " + j);
            }
            long j2;
            synchronized (this.d) {
                a();
                b();
                if (this.f.size() == 0) {
                    j2 = -1;
                } else {
                    j2 = this.f.read(buffer, Math.min(j, this.f.size()));
                    Http2Stream http2Stream = this.d;
                    http2Stream.a += j2;
                    if (this.d.a >= ((long) (this.d.d.l.d() / 2))) {
                        this.d.d.a(this.d.c, this.d.a);
                        this.d.a = 0;
                    }
                    synchronized (this.d.d) {
                        Http2Connection http2Connection = this.d.d;
                        http2Connection.j += j2;
                        if (this.d.d.j >= ((long) (this.d.d.l.d() / 2))) {
                            this.d.d.a(0, this.d.d.j);
                            this.d.d.j = 0;
                        }
                    }
                }
            }
            return j2;
        }

        private void a() throws IOException {
            this.d.f.enter();
            while (this.f.size() == 0 && !this.b && !this.a && this.d.h == null) {
                try {
                    this.d.d();
                } catch (Throwable th) {
                    this.d.f.exitAndThrowIfTimedOut();
                }
            }
            this.d.f.exitAndThrowIfTimedOut();
        }

        void a(BufferedSource bufferedSource, long j) throws IOException {
            if (c || !Thread.holdsLock(this.d)) {
                while (j > 0) {
                    boolean z;
                    Object obj;
                    synchronized (this.d) {
                        z = this.b;
                        obj = this.f.size() + j > this.g ? 1 : null;
                    }
                    if (obj != null) {
                        bufferedSource.skip(j);
                        this.d.closeLater(ErrorCode.FLOW_CONTROL_ERROR);
                        return;
                    } else if (z) {
                        bufferedSource.skip(j);
                        return;
                    } else {
                        long read = bufferedSource.read(this.e, j);
                        if (read == -1) {
                            throw new EOFException();
                        }
                        j -= read;
                        synchronized (this.d) {
                            if (this.f.size() == 0) {
                                obj = 1;
                            } else {
                                obj = null;
                            }
                            this.f.writeAll(this.e);
                            if (obj != null) {
                                this.d.notifyAll();
                            }
                        }
                    }
                }
                return;
            }
            throw new AssertionError();
        }

        public Timeout timeout() {
            return this.d.f;
        }

        public void close() throws IOException {
            synchronized (this.d) {
                this.a = true;
                this.f.clear();
                this.d.notifyAll();
            }
            this.d.b();
        }

        private void b() throws IOException {
            if (this.a) {
                throw new IOException("stream closed");
            } else if (this.d.h != null) {
                throw new StreamResetException(this.d.h);
            }
        }
    }

    class c extends AsyncTimeout {
        final /* synthetic */ Http2Stream a;

        c(Http2Stream http2Stream) {
            this.a = http2Stream;
        }

        protected void a() {
            this.a.closeLater(ErrorCode.CANCEL);
        }

        protected IOException a(IOException iOException) {
            IOException socketTimeoutException = new SocketTimeoutException(com.alipay.sdk.data.a.f);
            if (iOException != null) {
                socketTimeoutException.initCause(iOException);
            }
            return socketTimeoutException;
        }

        public void exitAndThrowIfTimedOut() throws IOException {
            if (exit()) {
                throw a(null);
            }
        }
    }

    Http2Stream(int i, Http2Connection http2Connection, boolean z, boolean z2, List<Header> list) {
        if (http2Connection == null) {
            throw new NullPointerException("connection == null");
        } else if (list == null) {
            throw new NullPointerException("requestHeaders == null");
        } else {
            this.c = i;
            this.d = http2Connection;
            this.b = (long) http2Connection.m.d();
            this.m = new b(this, (long) http2Connection.l.d());
            this.e = new a(this);
            this.m.b = z2;
            this.e.b = z;
            this.j = list;
        }
    }

    public int getId() {
        return this.c;
    }

    public synchronized boolean isOpen() {
        boolean z = false;
        synchronized (this) {
            if (this.h == null) {
                if (!((this.m.b || this.m.a) && ((this.e.b || this.e.a) && this.l))) {
                    z = true;
                }
            }
        }
        return z;
    }

    public boolean isLocallyInitiated() {
        boolean z;
        if ((this.c & 1) == 1) {
            z = true;
        } else {
            z = false;
        }
        return this.d.b == z;
    }

    public Http2Connection getConnection() {
        return this.d;
    }

    public List<Header> getRequestHeaders() {
        return this.j;
    }

    public synchronized List<Header> takeResponseHeaders() throws IOException {
        List<Header> list;
        if (isLocallyInitiated()) {
            this.f.enter();
            while (this.k == null && this.h == null) {
                try {
                    d();
                } catch (Throwable th) {
                    this.f.exitAndThrowIfTimedOut();
                }
            }
            this.f.exitAndThrowIfTimedOut();
            list = this.k;
            if (list != null) {
                this.k = null;
            } else {
                throw new StreamResetException(this.h);
            }
        }
        throw new IllegalStateException("servers cannot read response headers");
        return list;
    }

    public synchronized ErrorCode getErrorCode() {
        return this.h;
    }

    public void sendResponseHeaders(List<Header> list, boolean z) throws IOException {
        boolean z2 = true;
        if (!i && Thread.holdsLock(this)) {
            throw new AssertionError();
        } else if (list == null) {
            throw new NullPointerException("responseHeaders == null");
        } else {
            synchronized (this) {
                this.l = true;
                if (z) {
                    z2 = false;
                } else {
                    this.e.b = true;
                }
            }
            this.d.a(this.c, z2, (List) list);
            if (z2) {
                this.d.flush();
            }
        }
    }

    public Timeout readTimeout() {
        return this.f;
    }

    public Timeout writeTimeout() {
        return this.g;
    }

    public Source getSource() {
        return this.m;
    }

    public Sink getSink() {
        synchronized (this) {
            if (this.l || isLocallyInitiated()) {
            } else {
                throw new IllegalStateException("reply before requesting the sink");
            }
        }
        return this.e;
    }

    public void close(ErrorCode errorCode) throws IOException {
        if (b(errorCode)) {
            this.d.b(this.c, errorCode);
        }
    }

    public void closeLater(ErrorCode errorCode) {
        if (b(errorCode)) {
            this.d.a(this.c, errorCode);
        }
    }

    private boolean b(ErrorCode errorCode) {
        if (i || !Thread.holdsLock(this)) {
            synchronized (this) {
                if (this.h != null) {
                    return false;
                } else if (this.m.b && this.e.b) {
                    return false;
                } else {
                    this.h = errorCode;
                    notifyAll();
                    this.d.b(this.c);
                    return true;
                }
            }
        }
        throw new AssertionError();
    }

    void a(List<Header> list) {
        boolean z = true;
        if (i || !Thread.holdsLock(this)) {
            synchronized (this) {
                this.l = true;
                if (this.k == null) {
                    this.k = list;
                    z = isOpen();
                    notifyAll();
                } else {
                    List arrayList = new ArrayList();
                    arrayList.addAll(this.k);
                    arrayList.add(null);
                    arrayList.addAll(list);
                    this.k = arrayList;
                }
            }
            if (!z) {
                this.d.b(this.c);
                return;
            }
            return;
        }
        throw new AssertionError();
    }

    void a(BufferedSource bufferedSource, int i) throws IOException {
        if (i || !Thread.holdsLock(this)) {
            this.m.a(bufferedSource, (long) i);
            return;
        }
        throw new AssertionError();
    }

    void a() {
        if (i || !Thread.holdsLock(this)) {
            boolean isOpen;
            synchronized (this) {
                this.m.b = true;
                isOpen = isOpen();
                notifyAll();
            }
            if (!isOpen) {
                this.d.b(this.c);
                return;
            }
            return;
        }
        throw new AssertionError();
    }

    synchronized void a(ErrorCode errorCode) {
        if (this.h == null) {
            this.h = errorCode;
            notifyAll();
        }
    }

    void b() throws IOException {
        if (i || !Thread.holdsLock(this)) {
            Object obj;
            boolean isOpen;
            synchronized (this) {
                obj = (!this.m.b && this.m.a && (this.e.b || this.e.a)) ? 1 : null;
                isOpen = isOpen();
            }
            if (obj != null) {
                close(ErrorCode.CANCEL);
                return;
            } else if (!isOpen) {
                this.d.b(this.c);
                return;
            } else {
                return;
            }
        }
        throw new AssertionError();
    }

    void a(long j) {
        this.b += j;
        if (j > 0) {
            notifyAll();
        }
    }

    void c() throws IOException {
        if (this.e.a) {
            throw new IOException("stream closed");
        } else if (this.e.b) {
            throw new IOException("stream finished");
        } else if (this.h != null) {
            throw new StreamResetException(this.h);
        }
    }

    void d() throws InterruptedIOException {
        try {
            wait();
        } catch (InterruptedException e) {
            throw new InterruptedIOException();
        }
    }
}
