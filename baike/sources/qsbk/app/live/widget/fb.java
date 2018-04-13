package qsbk.app.live.widget;

class fb implements Runnable {
    final /* synthetic */ GuessBigOrSmallGameView a;

    fb(GuessBigOrSmallGameView guessBigOrSmallGameView) {
        this.a = guessBigOrSmallGameView;
    }

    public void run() {
        this.a.ad.setVisibility(8);
    }
}
