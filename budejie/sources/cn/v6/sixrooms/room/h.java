package cn.v6.sixrooms.room;

final class h implements Runnable {
    final /* synthetic */ SuperFireworksBean a;
    final /* synthetic */ c b;

    h(c cVar, SuperFireworksBean superFireworksBean) {
        this.b = cVar;
        this.a = superFireworksBean;
    }

    public final void run() {
        this.b.a.processSuperFireworks(this.a);
    }
}
