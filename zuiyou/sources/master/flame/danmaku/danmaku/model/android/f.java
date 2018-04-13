package master.flame.danmaku.danmaku.model.android;

import master.flame.danmaku.danmaku.model.a.c;
import master.flame.danmaku.danmaku.model.n;

public class f implements c<f>, n<g> {
    private final g a = new g();
    private int b = 0;
    private f c;
    private boolean d;
    private int e = 0;

    public /* synthetic */ Object a() {
        return h();
    }

    public /* synthetic */ Object l() {
        return i();
    }

    public void a(int i, int i2, int i3, boolean z, int i4) {
        this.a.a(i, i2, i3, z, i4);
        this.b = this.a.b.getRowBytes() * this.a.b.getHeight();
    }

    public g h() {
        if (this.a.b == null) {
            return null;
        }
        return this.a;
    }

    public void b() {
        if (this.a != null) {
            this.a.a();
        }
        this.b = 0;
        this.e = 0;
    }

    public int c() {
        return this.b;
    }

    public void a(f fVar) {
        this.c = fVar;
    }

    public f i() {
        return this.c;
    }

    public boolean j() {
        return this.d;
    }

    public void a(boolean z) {
        this.d = z;
    }

    public synchronized boolean f() {
        return this.e > 0;
    }

    public synchronized void k() {
        this.e++;
    }

    public synchronized void g() {
        this.e--;
    }

    public int d() {
        return this.a.e;
    }

    public int e() {
        return this.a.f;
    }
}
