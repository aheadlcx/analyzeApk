package okhttp3.internal.b;

import cn.v6.sixrooms.room.gift.BoxingVoteBean;
import java.io.IOException;
import java.net.ProtocolException;
import okhttp3.aa;
import okhttp3.internal.c;
import okhttp3.internal.connection.f;
import okhttp3.t;
import okhttp3.t.a;
import okhttp3.y;
import okio.d;
import okio.k;

public final class b implements t {
    private final boolean a;

    public b(boolean z) {
        this.a = z;
    }

    public aa intercept(a aVar) throws IOException {
        c c = ((g) aVar).c();
        f b = ((g) aVar).b();
        y a = aVar.a();
        long currentTimeMillis = System.currentTimeMillis();
        c.a(a);
        aa.a aVar2 = null;
        if (f.c(a.b()) && a.d() != null) {
            if ("100-continue".equalsIgnoreCase(a.a("Expect"))) {
                c.a();
                aVar2 = c.a(true);
            }
            if (aVar2 == null) {
                d a2 = k.a(c.a(a, a.d().b()));
                a.d().a(a2);
                a2.close();
            }
        }
        c.b();
        if (aVar2 == null) {
            aVar2 = c.a(false);
        }
        aa a3 = aVar2.a(a).a(b.b().d()).a(currentTimeMillis).b(System.currentTimeMillis()).a();
        int c2 = a3.c();
        if (this.a && c2 == 101) {
            a3 = a3.i().a(c.c).a();
        } else {
            a3 = a3.i().a(c.a(a3)).a();
        }
        if (BoxingVoteBean.BOXING_VOTE_STATE_CLOSE.equalsIgnoreCase(a3.a().a("Connection")) || BoxingVoteBean.BOXING_VOTE_STATE_CLOSE.equalsIgnoreCase(a3.a("Connection"))) {
            b.d();
        }
        if ((c2 != 204 && c2 != 205) || a3.h().b() <= 0) {
            return a3;
        }
        throw new ProtocolException("HTTP " + c2 + " had non-zero Content-Length: " + a3.h().b());
    }
}
