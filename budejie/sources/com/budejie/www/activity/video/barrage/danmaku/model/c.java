package com.budejie.www.activity.video.barrage.danmaku.model;

import com.budejie.www.activity.video.barrage.a;

public abstract class c {
    public int A = -1;
    public i B = null;
    private int C = 0;
    private int D = 0;
    public a a;
    public long b;
    public CharSequence c;
    public String[] d;
    public int e;
    public float f;
    public float g;
    public int h;
    public int i = 0;
    public float j = -1.0f;
    public int k = 0;
    public int l = 0;
    public byte m = (byte) 0;
    public float n = -1.0f;
    public float o = -1.0f;
    public f p;
    public int q;
    public int r;
    public m<?> s;
    public boolean t;
    public int u = 0;
    public String v;
    public boolean w;
    protected e x;
    protected int y = b.a;
    public int z = 0;

    public abstract void a(l lVar, float f, float f2);

    public abstract float[] a(l lVar, long j);

    public abstract float j();

    public abstract float k();

    public abstract float l();

    public abstract float m();

    public abstract int n();

    public long a() {
        return this.p.a;
    }

    public int a(l lVar) {
        return lVar.a(this);
    }

    public boolean b() {
        return this.n > -1.0f && this.o > -1.0f && this.D == this.B.a;
    }

    public void b(l lVar) {
        lVar.b(this);
        this.D = this.B.a;
    }

    public boolean c() {
        return (this.s == null || this.s.a() == null) ? false : true;
    }

    public boolean d() {
        return this.r == 1 && this.C == this.B.b;
    }

    public boolean e() {
        return this.x == null || a(this.x.a);
    }

    public boolean a(long j) {
        return j - this.b >= this.p.a;
    }

    public boolean f() {
        return this.x == null || b(this.x.a);
    }

    public boolean b(long j) {
        long j2 = j - this.b;
        return j2 <= 0 || j2 >= this.p.a;
    }

    public boolean g() {
        return this.x == null || this.x.a < this.b;
    }

    public boolean h() {
        if (this.A == this.B.c) {
            return true;
        }
        this.z = 0;
        return false;
    }

    public boolean i() {
        return this.A == this.B.c && this.z != 0;
    }

    public void a(boolean z) {
        if (z) {
            this.C = this.B.b;
            this.r = 1;
            return;
        }
        this.r = 0;
    }

    public e o() {
        return this.x;
    }

    public void a(e eVar) {
        this.x = eVar;
    }

    public int p() {
        return this.y;
    }
}
