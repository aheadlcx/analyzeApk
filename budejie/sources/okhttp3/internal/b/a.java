package okhttp3.internal.b;

import java.io.IOException;
import java.util.List;
import okhttp3.aa;
import okhttp3.internal.c;
import okhttp3.internal.d;
import okhttp3.l;
import okhttp3.m;
import okhttp3.t;
import okhttp3.u;
import okhttp3.y;
import okhttp3.z;
import okio.i;
import okio.k;
import okio.r;
import org.apache.http.entity.mime.MIME;

public final class a implements t {
    private final m a;

    public a(m mVar) {
        this.a = mVar;
    }

    public aa intercept(okhttp3.t.a aVar) throws IOException {
        boolean z = false;
        y a = aVar.a();
        okhttp3.y.a e = a.e();
        z d = a.d();
        if (d != null) {
            u a2 = d.a();
            if (a2 != null) {
                e.a(MIME.CONTENT_TYPE, a2.toString());
            }
            long b = d.b();
            if (b != -1) {
                e.a("Content-Length", Long.toString(b));
                e.b("Transfer-Encoding");
            } else {
                e.a("Transfer-Encoding", "chunked");
                e.b("Content-Length");
            }
        }
        if (a.a("Host") == null) {
            e.a("Host", c.a(a.a(), false));
        }
        if (a.a("Connection") == null) {
            e.a("Connection", "Keep-Alive");
        }
        if (a.a("Accept-Encoding") == null && a.a("Range") == null) {
            z = true;
            e.a("Accept-Encoding", "gzip");
        }
        List a3 = this.a.a(a.a());
        if (!a3.isEmpty()) {
            e.a("Cookie", a(a3));
        }
        if (a.a("User-Agent") == null) {
            e.a("User-Agent", d.a());
        }
        aa a4 = aVar.a(e.b());
        e.a(this.a, a.a(), a4.g());
        okhttp3.aa.a a5 = a4.i().a(a);
        if (z && "gzip".equalsIgnoreCase(a4.a("Content-Encoding")) && e.d(a4)) {
            r iVar = new i(a4.h().c());
            okhttp3.r a6 = a4.g().b().b("Content-Encoding").b("Content-Length").a();
            a5.a(a6);
            a5.a(new h(a6, k.a(iVar)));
        }
        return a5.a();
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
