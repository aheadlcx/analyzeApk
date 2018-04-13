package com.liulishuo.filedownloader.e;

import com.liulishuo.filedownloader.a;
import com.liulishuo.filedownloader.i;

public abstract class c extends i {
    private final b a;

    protected abstract a a(a aVar);

    public c(b bVar) {
        if (bVar == null) {
            throw new IllegalArgumentException("helper must not be null!");
        }
        this.a = bVar;
    }

    public void b(a aVar) {
        if (!d(aVar)) {
            a a = a(aVar);
            if (a != null) {
                this.a.a(a);
            }
        }
    }

    public void c(a aVar) {
        if (!d(aVar)) {
            this.a.a(aVar.d(), aVar.q());
            a b = this.a.b(aVar.d());
            if (!a(aVar, b) && b != null) {
                b.a();
            }
        }
    }

    public void i(a aVar) {
        if (!d(aVar)) {
            this.a.a(aVar.d(), aVar.q());
        }
    }

    public void d(a aVar, int i, int i2) {
        if (!d(aVar)) {
            this.a.a(aVar.d(), aVar.m(), aVar.o());
        }
    }

    protected boolean a(a aVar, a aVar2) {
        return false;
    }

    protected boolean d(a aVar) {
        return false;
    }

    protected void a(a aVar, int i, int i2) {
        b(aVar);
        i(aVar);
    }

    protected void f(a aVar) {
        super.f(aVar);
        i(aVar);
    }

    protected void b(a aVar, int i, int i2) {
        d(aVar, i, i2);
    }

    protected void a(a aVar, Throwable th, int i, int i2) {
        super.a(aVar, th, i, i2);
        i(aVar);
    }

    protected void g(a aVar) {
    }

    protected void e(a aVar) {
        c(aVar);
    }

    protected void c(a aVar, int i, int i2) {
        c(aVar);
    }

    protected void a(a aVar, Throwable th) {
        c(aVar);
    }

    protected void h(a aVar) {
    }
}
