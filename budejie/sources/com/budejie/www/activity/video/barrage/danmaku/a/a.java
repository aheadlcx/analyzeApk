package com.budejie.www.activity.video.barrage.danmaku.a;

import com.budejie.www.activity.video.barrage.danmaku.model.android.DanmakuContext;
import com.budejie.www.activity.video.barrage.danmaku.model.e;
import com.budejie.www.activity.video.barrage.danmaku.model.k;
import com.budejie.www.activity.video.barrage.danmaku.model.l;

public abstract class a {
    protected c<?> a;
    protected e b;
    protected int c;
    protected int d;
    protected float e;
    protected float f;
    protected l g;
    protected DanmakuContext h;
    private k i;

    protected abstract k e();

    public a a(l lVar) {
        this.g = lVar;
        this.c = lVar.d();
        this.d = lVar.e();
        this.e = lVar.f();
        this.f = lVar.h();
        this.h.t.a((float) this.c, (float) this.d, a());
        this.h.t.b();
        return this;
    }

    protected float a() {
        return 1.0f / (this.e - 0.6f);
    }

    public l b() {
        return this.g;
    }

    public a a(c<?> cVar) {
        this.a = cVar;
        return this;
    }

    public a a(e eVar) {
        this.b = eVar;
        return this;
    }

    public k c() {
        if (this.i != null) {
            return this.i;
        }
        this.h.t.a();
        this.i = e();
        d();
        this.h.t.b();
        return this.i;
    }

    protected void d() {
        if (this.a != null) {
            this.a.a();
        }
        this.a = null;
    }

    public void f() {
        d();
    }

    public a a(DanmakuContext danmakuContext) {
        this.h = danmakuContext;
        return this;
    }
}
