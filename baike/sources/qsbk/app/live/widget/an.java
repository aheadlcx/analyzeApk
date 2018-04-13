package qsbk.app.live.widget;

import qsbk.app.core.utils.FormatUtils;
import qsbk.app.live.model.GameRole;

class an implements Runnable {
    final /* synthetic */ GameRole a;
    final /* synthetic */ FanfanleGameView b;

    an(FanfanleGameView fanfanleGameView, GameRole gameRole) {
        this.b = fanfanleGameView;
        this.a = gameRole;
    }

    public void run() {
        if (this.b.ap == 0) {
            this.b.O.setText(FormatUtils.formatCoupon(this.a.getMeBet()));
        }
    }
}
