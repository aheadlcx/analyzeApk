package okhttp3.internal.connection;

import java.io.IOException;
import okhttp3.aa;
import okhttp3.internal.b.g;
import okhttp3.t;
import okhttp3.w;
import okhttp3.y;

public final class a implements t {
    public final w a;

    public a(w wVar) {
        this.a = wVar;
    }

    public aa intercept(okhttp3.t.a aVar) throws IOException {
        g gVar = (g) aVar;
        y a = gVar.a();
        f b = gVar.b();
        return gVar.a(a, b, b.a(this.a, !a.b().equals("GET")), b.b());
    }
}
