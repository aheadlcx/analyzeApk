package cn.v6.sixrooms.utils;

final class z implements Runnable {
    final /* synthetic */ y a;

    z(y yVar) {
        this.a = yVar;
    }

    public final void run() {
        this.a.c.onLoadingComplete(this.a.b, this.a.a, this.a.d);
    }
}
