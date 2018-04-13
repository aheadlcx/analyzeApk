package master.flame.danmaku.danmaku.a;

import master.flame.danmaku.danmaku.model.android.DanmakuContext;
import master.flame.danmaku.danmaku.model.f;
import master.flame.danmaku.danmaku.model.l;
import master.flame.danmaku.danmaku.model.m;

public abstract class a {
    private l a;
    protected b<?> b;
    protected f c;
    protected int d;
    protected int e;
    protected float f;
    protected float g;
    protected m h;
    protected DanmakuContext i;
    protected a j;

    public interface a {
    }

    protected abstract l a();

    public a a(m mVar) {
        this.h = mVar;
        this.d = mVar.e();
        this.e = mVar.f();
        this.f = mVar.g();
        this.g = mVar.i();
        this.i.v.a((float) this.d, (float) this.e, b());
        this.i.v.c();
        return this;
    }

    public a a(a aVar) {
        this.j = aVar;
        return this;
    }

    protected float b() {
        return 1.0f / (this.f - 0.6f);
    }

    public a a(f fVar) {
        this.c = fVar;
        return this;
    }

    public l c() {
        if (this.a != null) {
            return this.a;
        }
        this.i.v.b();
        this.a = a();
        d();
        this.i.v.c();
        return this.a;
    }

    protected void d() {
        if (this.b != null) {
            this.b.a();
        }
        this.b = null;
    }

    public void e() {
        d();
    }

    public a a(DanmakuContext danmakuContext) {
        if (!(this.i == null || this.i == danmakuContext)) {
            this.a = null;
        }
        this.i = danmakuContext;
        return this;
    }
}
