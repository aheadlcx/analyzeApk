package qsbk.app.live.widget;

import qsbk.app.live.R;

class cp implements Runnable {
    final /* synthetic */ GameView a;

    cp(GameView gameView) {
        this.a = gameView;
    }

    public void run() {
        this.a.a(R.string.live_game_start, new cq(this), 1600);
    }
}
