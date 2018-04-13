package okhttp3.internal.b;

import java.io.IOException;
import java.net.ProtocolException;
import okhttp3.aa;
import okhttp3.internal.connection.c;
import okhttp3.internal.connection.f;
import okhttp3.t;
import okhttp3.t.a;
import okhttp3.y;
import okio.BufferedSink;
import okio.Okio;

public final class b implements t {
    private final boolean a;

    public b(boolean z) {
        this.a = z;
    }

    public aa intercept(a aVar) throws IOException {
        aa.a aVar2;
        g gVar = (g) aVar;
        c d = gVar.d();
        f c = gVar.c();
        c cVar = (c) gVar.b();
        y a = gVar.a();
        long currentTimeMillis = System.currentTimeMillis();
        d.a(a);
        aa.a aVar3 = null;
        if (!f.c(a.b()) || a.d() == null) {
            aVar2 = null;
        } else {
            if ("100-continue".equalsIgnoreCase(a.a("Expect"))) {
                d.a();
                aVar3 = d.a(true);
            }
            if (aVar3 == null) {
                BufferedSink buffer = Okio.buffer(d.a(a, a.d().contentLength()));
                a.d().writeTo(buffer);
                buffer.close();
                aVar2 = aVar3;
            } else {
                if (!cVar.e()) {
                    c.d();
                }
                aVar2 = aVar3;
            }
        }
        d.b();
        if (aVar2 == null) {
            aVar2 = d.a(false);
        }
        aa a2 = aVar2.a(a).a(c.b().d()).a(currentTimeMillis).b(System.currentTimeMillis()).a();
        int b = a2.b();
        if (this.a && b == 101) {
            a2 = a2.h().a(okhttp3.internal.c.c).a();
        } else {
            a2 = a2.h().a(d.a(a2)).a();
        }
        if ("close".equalsIgnoreCase(a2.a().a("Connection")) || "close".equalsIgnoreCase(a2.a("Connection"))) {
            c.d();
        }
        if ((b != 204 && b != 205) || a2.g().contentLength() <= 0) {
            return a2;
        }
        throw new ProtocolException("HTTP " + b + " had non-zero Content-Length: " + a2.g().contentLength());
    }
}
