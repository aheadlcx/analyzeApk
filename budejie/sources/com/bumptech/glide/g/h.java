package com.bumptech.glide.g;

public class h implements c, d {
    private c a;
    private c b;
    private d c;

    public h() {
        this(null);
    }

    public h(d dVar) {
        this.c = dVar;
    }

    public void a(c cVar, c cVar2) {
        this.a = cVar;
        this.b = cVar2;
    }

    public boolean a(c cVar) {
        return j() && (cVar.equals(this.a) || !this.a.h());
    }

    private boolean j() {
        return this.c == null || this.c.a(this);
    }

    public boolean b(c cVar) {
        return k() && cVar.equals(this.a) && !c();
    }

    private boolean k() {
        return this.c == null || this.c.b(this);
    }

    public boolean c() {
        return l() || h();
    }

    public void c(c cVar) {
        if (!cVar.equals(this.b)) {
            if (this.c != null) {
                this.c.c(this);
            }
            if (!this.b.g()) {
                this.b.d();
            }
        }
    }

    private boolean l() {
        return this.c != null && this.c.c();
    }

    public void b() {
        if (!this.b.f()) {
            this.b.b();
        }
        if (!this.a.f()) {
            this.a.b();
        }
    }

    public void e() {
        this.a.e();
        this.b.e();
    }

    public void d() {
        this.b.d();
        this.a.d();
    }

    public boolean f() {
        return this.a.f();
    }

    public boolean g() {
        return this.a.g() || this.b.g();
    }

    public boolean h() {
        return this.a.h() || this.b.h();
    }

    public boolean i() {
        return this.a.i();
    }

    public void a() {
        this.a.a();
        this.b.a();
    }
}
