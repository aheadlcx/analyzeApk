package cn.v6.sixrooms.room;

final class g implements Runnable {
    final /* synthetic */ BecomeGodBean a;
    final /* synthetic */ c b;

    g(c cVar, BecomeGodBean becomeGodBean) {
        this.b = cVar;
        this.a = becomeGodBean;
    }

    public final void run() {
        this.b.a.processBecomeGod(this.a);
    }
}
