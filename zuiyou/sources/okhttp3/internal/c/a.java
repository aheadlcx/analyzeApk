package okhttp3.internal.c;

import com.iflytek.speech.VoiceWakeuperAidl;
import java.io.IOException;
import java.net.ProtocolException;
import java.util.concurrent.TimeUnit;
import okhttp3.HttpUrl;
import okhttp3.aa;
import okhttp3.ab;
import okhttp3.internal.b.h;
import okhttp3.internal.b.i;
import okhttp3.internal.b.k;
import okhttp3.s;
import okhttp3.s$a;
import okhttp3.w;
import okhttp3.y;
import okio.Buffer;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.ForwardingTimeout;
import okio.Okio;
import okio.Sink;
import okio.Source;
import okio.Timeout;

public final class a implements okhttp3.internal.b.c {
    final w a;
    final okhttp3.internal.connection.f b;
    final BufferedSource c;
    final BufferedSink d;
    int e = 0;

    private abstract class a implements Source {
        protected final ForwardingTimeout a;
        protected boolean b;
        final /* synthetic */ a c;

        private a(a aVar) {
            this.c = aVar;
            this.a = new ForwardingTimeout(this.c.c.timeout());
        }

        public Timeout timeout() {
            return this.a;
        }

        protected final void a(boolean z) throws IOException {
            if (this.c.e != 6) {
                if (this.c.e != 5) {
                    throw new IllegalStateException("state: " + this.c.e);
                }
                this.c.a(this.a);
                this.c.e = 6;
                if (this.c.b != null) {
                    this.c.b.a(!z, this.c);
                }
            }
        }
    }

    private final class b implements Sink {
        final /* synthetic */ a a;
        private final ForwardingTimeout b = new ForwardingTimeout(this.a.d.timeout());
        private boolean c;

        b(a aVar) {
            this.a = aVar;
        }

        public Timeout timeout() {
            return this.b;
        }

        public void write(Buffer buffer, long j) throws IOException {
            if (this.c) {
                throw new IllegalStateException("closed");
            } else if (j != 0) {
                this.a.d.writeHexadecimalUnsignedLong(j);
                this.a.d.writeUtf8("\r\n");
                this.a.d.write(buffer, j);
                this.a.d.writeUtf8("\r\n");
            }
        }

        public synchronized void flush() throws IOException {
            if (!this.c) {
                this.a.d.flush();
            }
        }

        public synchronized void close() throws IOException {
            if (!this.c) {
                this.c = true;
                this.a.d.writeUtf8("0\r\n\r\n");
                this.a.a(this.b);
                this.a.e = 3;
            }
        }
    }

    private class c extends a {
        final /* synthetic */ a d;
        private final HttpUrl e;
        private long f = -1;
        private boolean g = true;

        c(a aVar, HttpUrl httpUrl) {
            this.d = aVar;
            super();
            this.e = httpUrl;
        }

        public long read(Buffer buffer, long j) throws IOException {
            if (j < 0) {
                throw new IllegalArgumentException("byteCount < 0: " + j);
            } else if (this.b) {
                throw new IllegalStateException("closed");
            } else if (!this.g) {
                return -1;
            } else {
                if (this.f == 0 || this.f == -1) {
                    a();
                    if (!this.g) {
                        return -1;
                    }
                }
                long read = this.d.c.read(buffer, Math.min(j, this.f));
                if (read == -1) {
                    a(false);
                    throw new ProtocolException("unexpected end of stream");
                }
                this.f -= read;
                return read;
            }
        }

        private void a() throws IOException {
            if (this.f != -1) {
                this.d.c.readUtf8LineStrict();
            }
            try {
                this.f = this.d.c.readHexadecimalUnsignedLong();
                String trim = this.d.c.readUtf8LineStrict().trim();
                if (this.f < 0 || !(trim.isEmpty() || trim.startsWith(VoiceWakeuperAidl.PARAMS_SEPARATE))) {
                    throw new ProtocolException("expected chunk size and optional extensions but was \"" + this.f + trim + "\"");
                } else if (this.f == 0) {
                    this.g = false;
                    okhttp3.internal.b.e.a(this.d.a.f(), this.e, this.d.d());
                    a(true);
                }
            } catch (NumberFormatException e) {
                throw new ProtocolException(e.getMessage());
            }
        }

        public void close() throws IOException {
            if (!this.b) {
                if (this.g && !okhttp3.internal.c.a((Source) this, 100, TimeUnit.MILLISECONDS)) {
                    a(false);
                }
                this.b = true;
            }
        }
    }

    private final class d implements Sink {
        final /* synthetic */ a a;
        private final ForwardingTimeout b = new ForwardingTimeout(this.a.d.timeout());
        private boolean c;
        private long d;

        d(a aVar, long j) {
            this.a = aVar;
            this.d = j;
        }

        public Timeout timeout() {
            return this.b;
        }

        public void write(Buffer buffer, long j) throws IOException {
            if (this.c) {
                throw new IllegalStateException("closed");
            }
            okhttp3.internal.c.a(buffer.size(), 0, j);
            if (j > this.d) {
                throw new ProtocolException("expected " + this.d + " bytes but received " + j);
            }
            this.a.d.write(buffer, j);
            this.d -= j;
        }

        public void flush() throws IOException {
            if (!this.c) {
                this.a.d.flush();
            }
        }

        public void close() throws IOException {
            if (!this.c) {
                this.c = true;
                if (this.d > 0) {
                    throw new ProtocolException("unexpected end of stream");
                }
                this.a.a(this.b);
                this.a.e = 3;
            }
        }
    }

    private class e extends a {
        final /* synthetic */ a d;
        private long e;

        e(a aVar, long j) throws IOException {
            this.d = aVar;
            super();
            this.e = j;
            if (this.e == 0) {
                a(true);
            }
        }

        public long read(Buffer buffer, long j) throws IOException {
            if (j < 0) {
                throw new IllegalArgumentException("byteCount < 0: " + j);
            } else if (this.b) {
                throw new IllegalStateException("closed");
            } else if (this.e == 0) {
                return -1;
            } else {
                long read = this.d.c.read(buffer, Math.min(this.e, j));
                if (read == -1) {
                    a(false);
                    throw new ProtocolException("unexpected end of stream");
                }
                this.e -= read;
                if (this.e == 0) {
                    a(true);
                }
                return read;
            }
        }

        public void close() throws IOException {
            if (!this.b) {
                if (!(this.e == 0 || okhttp3.internal.c.a((Source) this, 100, TimeUnit.MILLISECONDS))) {
                    a(false);
                }
                this.b = true;
            }
        }
    }

    private class f extends a {
        final /* synthetic */ a d;
        private boolean e;

        f(a aVar) {
            this.d = aVar;
            super();
        }

        public long read(Buffer buffer, long j) throws IOException {
            if (j < 0) {
                throw new IllegalArgumentException("byteCount < 0: " + j);
            } else if (this.b) {
                throw new IllegalStateException("closed");
            } else if (this.e) {
                return -1;
            } else {
                long read = this.d.c.read(buffer, j);
                if (read != -1) {
                    return read;
                }
                this.e = true;
                a(true);
                return -1;
            }
        }

        public void close() throws IOException {
            if (!this.b) {
                if (!this.e) {
                    a(false);
                }
                this.b = true;
            }
        }
    }

    public a(w wVar, okhttp3.internal.connection.f fVar, BufferedSource bufferedSource, BufferedSink bufferedSink) {
        this.a = wVar;
        this.b = fVar;
        this.c = bufferedSource;
        this.d = bufferedSink;
    }

    public Sink a(y yVar, long j) {
        if ("chunked".equalsIgnoreCase(yVar.a("Transfer-Encoding"))) {
            return e();
        }
        if (j != -1) {
            return a(j);
        }
        throw new IllegalStateException("Cannot stream a request body without chunked encoding or a known content length!");
    }

    public void c() {
        okhttp3.internal.connection.c b = this.b.b();
        if (b != null) {
            b.b();
        }
    }

    public void a(y yVar) throws IOException {
        a(yVar.c(), i.a(yVar, this.b.b().a().b().type()));
    }

    public ab a(aa aaVar) throws IOException {
        return new h(aaVar.f(), Okio.buffer(b(aaVar)));
    }

    private Source b(aa aaVar) throws IOException {
        if (!okhttp3.internal.b.e.b(aaVar)) {
            return b(0);
        }
        if ("chunked".equalsIgnoreCase(aaVar.a("Transfer-Encoding"))) {
            return a(aaVar.a().a());
        }
        long a = okhttp3.internal.b.e.a(aaVar);
        if (a != -1) {
            return b(a);
        }
        return f();
    }

    public void a() throws IOException {
        this.d.flush();
    }

    public void b() throws IOException {
        this.d.flush();
    }

    public void a(s sVar, String str) throws IOException {
        if (this.e != 0) {
            throw new IllegalStateException("state: " + this.e);
        }
        this.d.writeUtf8(str).writeUtf8("\r\n");
        int a = sVar.a();
        for (int i = 0; i < a; i++) {
            this.d.writeUtf8(sVar.a(i)).writeUtf8(": ").writeUtf8(sVar.b(i)).writeUtf8("\r\n");
        }
        this.d.writeUtf8("\r\n");
        this.e = 1;
    }

    public okhttp3.aa.a a(boolean z) throws IOException {
        if (this.e == 1 || this.e == 3) {
            try {
                k a = k.a(this.c.readUtf8LineStrict());
                okhttp3.aa.a a2 = new okhttp3.aa.a().a(a.a).a(a.b).a(a.c).a(d());
                if (z && a.b == 100) {
                    return null;
                }
                this.e = 4;
                return a2;
            } catch (Throwable e) {
                IOException iOException = new IOException("unexpected end of stream on " + this.b);
                iOException.initCause(e);
                throw iOException;
            }
        }
        throw new IllegalStateException("state: " + this.e);
    }

    public s d() throws IOException {
        s$a s_a = new s$a();
        while (true) {
            String readUtf8LineStrict = this.c.readUtf8LineStrict();
            if (readUtf8LineStrict.length() == 0) {
                return s_a.a();
            }
            okhttp3.internal.a.a.a(s_a, readUtf8LineStrict);
        }
    }

    public Sink e() {
        if (this.e != 1) {
            throw new IllegalStateException("state: " + this.e);
        }
        this.e = 2;
        return new b(this);
    }

    public Sink a(long j) {
        if (this.e != 1) {
            throw new IllegalStateException("state: " + this.e);
        }
        this.e = 2;
        return new d(this, j);
    }

    public Source b(long j) throws IOException {
        if (this.e != 4) {
            throw new IllegalStateException("state: " + this.e);
        }
        this.e = 5;
        return new e(this, j);
    }

    public Source a(HttpUrl httpUrl) throws IOException {
        if (this.e != 4) {
            throw new IllegalStateException("state: " + this.e);
        }
        this.e = 5;
        return new c(this, httpUrl);
    }

    public Source f() throws IOException {
        if (this.e != 4) {
            throw new IllegalStateException("state: " + this.e);
        } else if (this.b == null) {
            throw new IllegalStateException("streamAllocation == null");
        } else {
            this.e = 5;
            this.b.d();
            return new f(this);
        }
    }

    void a(ForwardingTimeout forwardingTimeout) {
        Timeout delegate = forwardingTimeout.delegate();
        forwardingTimeout.setDelegate(Timeout.NONE);
        delegate.clearDeadline();
        delegate.clearTimeout();
    }
}
