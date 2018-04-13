package okhttp3.internal.b;

import okhttp3.ab;
import okhttp3.r;
import okhttp3.u;
import okio.e;
import org.apache.http.entity.mime.MIME;

public final class h extends ab {
    private final r a;
    private final e b;

    public h(r rVar, e eVar) {
        this.a = rVar;
        this.b = eVar;
    }

    public u a() {
        String a = this.a.a(MIME.CONTENT_TYPE);
        return a != null ? u.a(a) : null;
    }

    public long b() {
        return e.a(this.a);
    }

    public e c() {
        return this.b;
    }
}
