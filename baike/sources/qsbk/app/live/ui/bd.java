package qsbk.app.live.ui;

import qsbk.app.live.model.LiveMessage;
import qsbk.app.live.widget.GameGuessHelpDialog.ClickListenner;

class bd implements ClickListenner {
    final /* synthetic */ bc a;

    bd(bc bcVar) {
        this.a = bcVar;
    }

    public void clickListenner(int i, LiveMessage liveMessage) {
        this.a.b.share(i, liveMessage);
    }
}
