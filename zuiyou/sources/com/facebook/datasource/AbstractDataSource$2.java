package com.facebook.datasource;

class AbstractDataSource$2 implements Runnable {
    final /* synthetic */ d a;
    final /* synthetic */ AbstractDataSource b;

    AbstractDataSource$2(AbstractDataSource abstractDataSource, d dVar) {
        this.b = abstractDataSource;
        this.a = dVar;
    }

    public void run() {
        this.a.a(this.b);
    }
}
