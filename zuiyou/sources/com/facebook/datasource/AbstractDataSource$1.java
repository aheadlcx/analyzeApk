package com.facebook.datasource;

class AbstractDataSource$1 implements Runnable {
    final /* synthetic */ boolean a;
    final /* synthetic */ d b;
    final /* synthetic */ boolean c;
    final /* synthetic */ AbstractDataSource d;

    AbstractDataSource$1(AbstractDataSource abstractDataSource, boolean z, d dVar, boolean z2) {
        this.d = abstractDataSource;
        this.a = z;
        this.b = dVar;
        this.c = z2;
    }

    public void run() {
        if (this.a) {
            this.b.e(this.d);
        } else if (this.c) {
            this.b.f(this.d);
        } else {
            this.b.d(this.d);
        }
    }
}
