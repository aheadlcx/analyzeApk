package qsbk.app.live.widget;

import qsbk.app.live.model.GameRole;

class fe implements Runnable {
    final /* synthetic */ GameRole a;
    final /* synthetic */ HLNBGameView b;

    fe(HLNBGameView hLNBGameView, GameRole gameRole) {
        this.b = hLNBGameView;
        this.a = gameRole;
    }

    public void run() {
        this.b.b(this.a.getRoleId());
    }
}
