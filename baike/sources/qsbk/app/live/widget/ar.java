package qsbk.app.live.widget;

import qsbk.app.live.R;

class ar implements Runnable {
    final /* synthetic */ FanfanleGameView a;

    ar(FanfanleGameView fanfanleGameView) {
        this.a = fanfanleGameView;
    }

    public void run() {
        this.a.a(R.string.live_game_ready, true);
    }
}
