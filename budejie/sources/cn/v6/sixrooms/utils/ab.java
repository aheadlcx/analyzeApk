package cn.v6.sixrooms.utils;

final class ab implements Runnable {
    final /* synthetic */ aa a;

    ab(aa aaVar) {
        this.a = aaVar;
    }

    public final void run() {
        this.a.d.onLoadingComplete(this.a.b, this.a.a, this.a.e);
    }
}
