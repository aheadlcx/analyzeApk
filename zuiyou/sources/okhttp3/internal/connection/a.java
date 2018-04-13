package okhttp3.internal.connection;

import com.tencent.connect.common.Constants;
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
        f c = gVar.c();
        return gVar.a(a, c, c.a(this.a, !a.b().equals(Constants.HTTP_GET)), c.b());
    }
}
