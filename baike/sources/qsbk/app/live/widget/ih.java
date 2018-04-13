package qsbk.app.live.widget;

import qsbk.app.live.R;

class ih implements Runnable {
    final /* synthetic */ RollTableGameView a;

    ih(RollTableGameView rollTableGameView) {
        this.a = rollTableGameView;
    }

    public void run() {
        this.a.a(R.string.live_game_rolltable_ready);
    }
}
