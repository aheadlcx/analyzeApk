package qsbk.app.live.widget;

class eq implements Runnable {
    final /* synthetic */ GuessBigOrSmallGameView a;

    eq(GuessBigOrSmallGameView guessBigOrSmallGameView) {
        this.a = guessBigOrSmallGameView;
    }

    public void run() {
        this.a.ad.setVisibility(8);
    }
}
