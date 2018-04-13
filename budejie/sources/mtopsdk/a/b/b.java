package mtopsdk.a.b;

import java.util.Map;

public final class b {
    private final String a;
    private final String b;
    private final Map c;
    private final d d;
    private final String e;
    private final int f;
    private final int g;
    private final int h;

    private b(c cVar) {
        this.a = cVar.a;
        this.b = cVar.b;
        this.c = cVar.c;
        this.d = cVar.d;
        this.e = cVar.e;
        this.f = cVar.f;
        this.g = cVar.g;
        this.h = cVar.h;
        cVar.i;
    }

    public final String a() {
        return this.a;
    }

    public final String b() {
        return this.b;
    }

    public final Map c() {
        return this.c;
    }

    public final d d() {
        return this.d;
    }

    public final int e() {
        return this.f;
    }

    public final int f() {
        return this.g;
    }

    public final int g() {
        return this.h;
    }

    public final String toString() {
        return "Request{body=" + this.d + ", url='" + this.a + '\'' + ", method='" + this.b + '\'' + ", headers=" + this.c + ", seqNo='" + this.e + '\'' + ", connectTimeoutMills=" + this.f + ", readTimeoutMills=" + this.g + ", retryTimes=" + this.h + '}';
    }
}
