package qsbk.app.live.widget;

import qsbk.app.live.model.LiveGameDataMessage;
import qsbk.app.live.model.LiveGameMessage;

class al implements Runnable {
    final /* synthetic */ LiveGameMessage a;
    final /* synthetic */ FanfanleGameView b;

    al(FanfanleGameView fanfanleGameView, LiveGameMessage liveGameMessage) {
        this.b = fanfanleGameView;
        this.a = liveGameMessage;
    }

    public void run() {
        this.b.a(((LiveGameDataMessage) this.a).getCountDownDuration() - 2);
        this.b.g((LiveGameDataMessage) this.a);
    }
}
