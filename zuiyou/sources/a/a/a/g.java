package a.a.a;

import a.a.a.b.b;
import a.a.a.b.f;
import a.a.a.b.j;
import a.a.a.d.a;
import java.io.ByteArrayOutputStream;

public class g {
    private final ByteArrayOutputStream a;
    private final a b;
    private f c;

    public g() {
        this(new b.a());
    }

    public g(j jVar) {
        this.a = new ByteArrayOutputStream();
        this.b = new a(this.a);
        this.c = jVar.a(this.b);
    }

    public byte[] a(d dVar) throws j {
        this.a.reset();
        dVar.b(this.c);
        return this.a.toByteArray();
    }
}
