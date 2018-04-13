package qsbk.app.live.widget;

import qsbk.app.live.model.LiveGameDataMessage;

class dc implements Runnable {
    final /* synthetic */ LiveGameDataMessage a;
    final /* synthetic */ GameView b;

    dc(GameView gameView, LiveGameDataMessage liveGameDataMessage) {
        this.b = gameView;
        this.a = liveGameDataMessage;
    }

    public void run() {
        this.b.a(this.a);
    }
}
