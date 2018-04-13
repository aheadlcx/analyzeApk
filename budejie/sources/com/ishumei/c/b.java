package com.ishumei.c;

public abstract class b<T> implements Runnable {
    public int c = -1;
    public boolean d = false;
    public boolean e = false;
    public long f = 0;
    public boolean g = false;
    public T h = null;

    public b(boolean z, int i) {
        this.d = z;
        this.c = i;
        this.e = false;
        this.f = 0;
        this.g = false;
    }

    public b(boolean z, int i, boolean z2, long j, boolean z3) {
        this.d = z;
        this.c = i;
        this.e = z2;
        this.f = j;
        this.g = z3;
    }

    public final void a() {
        if (this.d) {
            a.b().a(this, this.c, this.e, this.f, this.g);
        } else {
            run();
        }
    }

    public final void a(T t) {
        this.h = t;
    }
}
