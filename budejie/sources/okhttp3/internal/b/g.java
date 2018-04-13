package okhttp3.internal.b;

import java.io.IOException;
import java.util.List;
import okhttp3.aa;
import okhttp3.i;
import okhttp3.internal.connection.f;
import okhttp3.s;
import okhttp3.t;
import okhttp3.t.a;
import okhttp3.y;

public final class g implements a {
    private final List<t> a;
    private final f b;
    private final c c;
    private final i d;
    private final int e;
    private final y f;
    private int g;

    public g(List<t> list, f fVar, c cVar, i iVar, int i, y yVar) {
        this.a = list;
        this.d = iVar;
        this.b = fVar;
        this.c = cVar;
        this.e = i;
        this.f = yVar;
    }

    public f b() {
        return this.b;
    }

    public c c() {
        return this.c;
    }

    public y a() {
        return this.f;
    }

    public aa a(y yVar) throws IOException {
        return a(yVar, this.b, this.c, this.d);
    }

    public aa a(y yVar, f fVar, c cVar, i iVar) throws IOException {
        if (this.e >= this.a.size()) {
            throw new AssertionError();
        }
        this.g++;
        if (this.c != null && !a(yVar.a())) {
            throw new IllegalStateException("network interceptor " + this.a.get(this.e - 1) + " must retain the same host and port");
        } else if (this.c == null || this.g <= 1) {
            Object gVar = new g(this.a, fVar, cVar, iVar, this.e + 1, yVar);
            t tVar = (t) this.a.get(this.e);
            aa intercept = tVar.intercept(gVar);
            if (cVar != null && this.e + 1 < this.a.size() && gVar.g != 1) {
                throw new IllegalStateException("network interceptor " + tVar + " must call proceed() exactly once");
            } else if (intercept != null) {
                return intercept;
            } else {
                throw new NullPointerException("interceptor " + tVar + " returned null");
            }
        } else {
            throw new IllegalStateException("network interceptor " + this.a.get(this.e - 1) + " must call proceed() exactly once");
        }
    }

    private boolean a(s sVar) {
        return sVar.f().equals(this.d.a().a().a().f()) && sVar.g() == this.d.a().a().a().g();
    }
}
