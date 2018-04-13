package okhttp3.internal.a;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import okhttp3.Protocol;
import okhttp3.aa;
import okhttp3.internal.b.e;
import okhttp3.internal.b.f;
import okhttp3.internal.b.h;
import okhttp3.internal.c;
import okhttp3.s;
import okhttp3.s$a;
import okhttp3.t;
import okhttp3.y;
import okio.Buffer;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.Okio;
import okio.Sink;
import okio.Source;
import okio.Timeout;

public final class a implements t {
    final e a;

    public a(e eVar) {
        this.a = eVar;
    }

    public aa intercept(okhttp3.t.a aVar) throws IOException {
        aa a;
        aa aaVar = null;
        if (this.a != null) {
            a = this.a.a(aVar.a());
        } else {
            a = aaVar;
        }
        c a2 = new c$a(System.currentTimeMillis(), aVar.a(), a).a();
        y yVar = a2.a;
        aa aaVar2 = a2.b;
        if (this.a != null) {
            this.a.a(a2);
        }
        if (a != null && aaVar2 == null) {
            c.a(a.g());
        }
        if (yVar == null && aaVar2 == null) {
            return new okhttp3.aa.a().a(aVar.a()).a(Protocol.HTTP_1_1).a(504).a("Unsatisfiable Request (only-if-cached)").a(c.c).a(-1).b(System.currentTimeMillis()).a();
        }
        if (yVar == null) {
            return aaVar2.h().b(a(aaVar2)).a();
        }
        try {
            aaVar = aVar.a(yVar);
            if (aaVar2 != null) {
                if (aaVar.b() == 304) {
                    a = aaVar2.h().a(a(aaVar2.f(), aaVar.f())).a(aaVar.l()).b(aaVar.m()).b(a(aaVar2)).a(a(aaVar)).a();
                    aaVar.g().close();
                    this.a.a();
                    this.a.a(aaVar2, a);
                    return a;
                }
                c.a(aaVar2.g());
            }
            a = aaVar.h().b(a(aaVar2)).a(a(aaVar)).a();
            if (this.a == null) {
                return a;
            }
            if (e.b(a) && c.a(a, yVar)) {
                return a(this.a.a(a), a);
            }
            if (!f.a(yVar.b())) {
                return a;
            }
            try {
                this.a.b(yVar);
                return a;
            } catch (IOException e) {
                return a;
            }
        } finally {
            if (aaVar == null && a != null) {
                c.a(a.g());
            }
        }
    }

    private static aa a(aa aaVar) {
        if (aaVar == null || aaVar.g() == null) {
            return aaVar;
        }
        return aaVar.h().a(null).a();
    }

    private aa a(final b bVar, aa aaVar) throws IOException {
        if (bVar == null) {
            return aaVar;
        }
        Sink a = bVar.a();
        if (a == null) {
            return aaVar;
        }
        final BufferedSource source = aaVar.g().source();
        final BufferedSink buffer = Okio.buffer(a);
        return aaVar.h().a(new h(aaVar.f(), Okio.buffer(new Source(this) {
            boolean a;
            final /* synthetic */ a e;

            public long read(Buffer buffer, long j) throws IOException {
                try {
                    long read = source.read(buffer, j);
                    if (read == -1) {
                        if (!this.a) {
                            this.a = true;
                            buffer.close();
                        }
                        return -1;
                    }
                    buffer.copyTo(buffer.buffer(), buffer.size() - read, read);
                    buffer.emitCompleteSegments();
                    return read;
                } catch (IOException e) {
                    if (!this.a) {
                        this.a = true;
                        bVar.b();
                    }
                    throw e;
                }
            }

            public Timeout timeout() {
                return source.timeout();
            }

            public void close() throws IOException {
                if (!(this.a || c.a((Source) this, 100, TimeUnit.MILLISECONDS))) {
                    this.a = true;
                    bVar.b();
                }
                source.close();
            }
        }))).a();
    }

    private static s a(s sVar, s sVar2) {
        int i;
        int i2 = 0;
        s$a s_a = new s$a();
        int a = sVar.a();
        for (i = 0; i < a; i++) {
            String a2 = sVar.a(i);
            String b = sVar.b(i);
            if (!("Warning".equalsIgnoreCase(a2) && b.startsWith("1")) && (!a(a2) || sVar2.a(a2) == null)) {
                okhttp3.internal.a.a.a(s_a, a2, b);
            }
        }
        i = sVar2.a();
        while (i2 < i) {
            String a3 = sVar2.a(i2);
            if (!"Content-Length".equalsIgnoreCase(a3) && a(a3)) {
                okhttp3.internal.a.a.a(s_a, a3, sVar2.b(i2));
            }
            i2++;
        }
        return s_a.a();
    }

    static boolean a(String str) {
        if ("Connection".equalsIgnoreCase(str) || "Keep-Alive".equalsIgnoreCase(str) || "Proxy-Authenticate".equalsIgnoreCase(str) || "Proxy-Authorization".equalsIgnoreCase(str) || "TE".equalsIgnoreCase(str) || "Trailers".equalsIgnoreCase(str) || "Transfer-Encoding".equalsIgnoreCase(str) || "Upgrade".equalsIgnoreCase(str)) {
            return false;
        }
        return true;
    }
}
