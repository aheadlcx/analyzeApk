package qsbk.app.live.widget;

import qsbk.app.live.model.LiveMessage;
import qsbk.app.live.widget.GameGuessHelpDialog.ClickListenner;

class er implements ClickListenner {
    final /* synthetic */ GuessBigOrSmallGameView a;

    er(GuessBigOrSmallGameView guessBigOrSmallGameView) {
        this.a = guessBigOrSmallGameView;
    }

    public void clickListenner(int i, LiveMessage liveMessage) {
        this.a.share(i, liveMessage);
    }
}
