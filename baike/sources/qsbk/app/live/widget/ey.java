package qsbk.app.live.widget;

import qsbk.app.live.R;

class ey implements Runnable {
    final /* synthetic */ GuessBigOrSmallGameView a;

    ey(GuessBigOrSmallGameView guessBigOrSmallGameView) {
        this.a = guessBigOrSmallGameView;
    }

    public void run() {
        this.a.a(R.string.live_game_start, new ez(this), 1600);
    }
}
