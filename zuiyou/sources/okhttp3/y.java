package okhttp3;

import javax.annotation.Nullable;

public final class y {
    final HttpUrl a;
    final String b;
    final s c;
    @Nullable
    final z d;
    final Object e;
    private volatile d f;

    y(y$a y_a) {
        Object obj;
        this.a = y_a.a;
        this.b = y_a.b;
        this.c = y_a.c.a();
        this.d = y_a.d;
        if (y_a.e != null) {
            obj = y_a.e;
        } else {
            y yVar = this;
        }
        this.e = obj;
    }

    public HttpUrl a() {
        return this.a;
    }

    public String b() {
        return this.b;
    }

    public s c() {
        return this.c;
    }

    public String a(String str) {
        return this.c.a(str);
    }

    @Nullable
    public z d() {
        return this.d;
    }

    public Object e() {
        return this.e;
    }

    public y$a f() {
        return new y$a(this);
    }

    public d g() {
        d dVar = this.f;
        if (dVar != null) {
            return dVar;
        }
        dVar = d.a(this.c);
        this.f = dVar;
        return dVar;
    }

    public boolean h() {
        return this.a.c();
    }

    public String toString() {
        return "Request{method=" + this.b + ", url=" + this.a + ", tag=" + (this.e != this ? this.e : null) + '}';
    }
}
