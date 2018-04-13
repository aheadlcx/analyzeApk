package qsbk.app.live.ui.fragment;

class c implements Runnable {
    final /* synthetic */ GiftRankFragment a;

    c(GiftRankFragment giftRankFragment) {
        this.a = giftRankFragment;
    }

    public void run() {
        this.a.a.setRefreshing(true);
        this.a.f = 1;
        this.a.b();
    }
}
