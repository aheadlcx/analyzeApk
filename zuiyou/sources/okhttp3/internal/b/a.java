package okhttp3.internal.b;

import java.io.IOException;
import java.util.List;
import okhttp3.aa;
import okhttp3.internal.c;
import okhttp3.internal.d;
import okhttp3.l;
import okhttp3.m;
import okhttp3.s;
import okhttp3.t;
import okhttp3.u;
import okhttp3.y;
import okhttp3.y$a;
import okhttp3.z;
import okio.GzipSource;
import okio.Okio;
import okio.Source;

public final class a implements t {
    private final m a;

    public a(m mVar) {
        this.a = mVar;
    }

    public aa intercept(okhttp3.t.a aVar) throws IOException {
        boolean z = false;
        y a = aVar.a();
        y$a f = a.f();
        z d = a.d();
        if (d != null) {
            u contentType = d.contentType();
            if (contentType != null) {
                f.a("Content-Type", contentType.toString());
            }
            long contentLength = d.contentLength();
            if (contentLength != -1) {
                f.a("Content-Length", Long.toString(contentLength));
                f.b("Transfer-Encoding");
            } else {
                f.a("Transfer-Encoding", "chunked");
                f.b("Content-Length");
            }
        }
        if (a.a("Host") == null) {
            f.a("Host", c.a(a.a(), false));
        }
        if (a.a("Connection") == null) {
            f.a("Connection", "Keep-Alive");
        }
        if (a.a("Accept-Encoding") == null && a.a("Range") == null) {
            z = true;
            f.a("Accept-Encoding", "gzip");
        }
        List a2 = this.a.a(a.a());
        if (!a2.isEmpty()) {
            f.a("Cookie", a(a2));
        }
        if (a.a("User-Agent") == null) {
            f.a("User-Agent", d.a());
        }
        aa a3 = aVar.a(f.d());
        e.a(this.a, a.a(), a3.f());
        okhttp3.aa.a a4 = a3.h().a(a);
        if (z && "gzip".equalsIgnoreCase(a3.a("Content-Encoding")) && e.b(a3)) {
            Source gzipSource = new GzipSource(a3.g().source());
            s a5 = a3.f().b().b("Content-Encoding").b("Content-Length").a();
            a4.a(a5);
            a4.a(new h(a5, Okio.buffer(gzipSource)));
        }
        return a4.a();
    }

    private String a(List<l> list) {
        StringBuilder stringBuilder = new StringBuilder();
        int size = list.size();
        for (int i = 0; i < size; i++) {
            if (i > 0) {
                stringBuilder.append("; ");
            }
            l lVar = (l) list.get(i);
            stringBuilder.append(lVar.a()).append('=').append(lVar.b());
        }
        return stringBuilder.toString();
    }
}
