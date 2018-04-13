package qsbk.app.live.widget;

import qsbk.app.live.model.LiveMessage;
import qsbk.app.live.widget.GameGuessHelpDialog.ClickListenner;

class es implements ClickListenner {
    final /* synthetic */ GuessBigOrSmallGameView a;

    es(GuessBigOrSmallGameView guessBigOrSmallGameView) {
        this.a = guessBigOrSmallGameView;
    }

    public void clickListenner(int i, LiveMessage liveMessage) {
        this.a.share(i, liveMessage);
    }
}
