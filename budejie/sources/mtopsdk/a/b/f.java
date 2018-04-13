package mtopsdk.a.b;

import java.util.Map;

public final class f {
    private b a;
    private int b = -1;
    private String c;
    private Map d;
    private g e;

    public final e a() {
        if (this.a != null) {
            return new e();
        }
        throw new IllegalStateException("request == null");
    }

    public final f a(int i) {
        this.b = i;
        return this;
    }

    public final f a(String str) {
        this.c = str;
        return this;
    }

    public final f a(Map map) {
        this.d = map;
        return this;
    }

    public final f a(b bVar) {
        this.a = bVar;
        return this;
    }

    public final f a(g gVar) {
        this.e = gVar;
        return this;
    }
}
