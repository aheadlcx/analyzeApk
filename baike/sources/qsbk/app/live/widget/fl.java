package qsbk.app.live.widget;

import qsbk.app.live.model.LiveEnterMessage;

class fl implements Runnable {
    final /* synthetic */ LiveEnterMessage a;
    final /* synthetic */ HighLevelUserEnterView b;

    fl(HighLevelUserEnterView highLevelUserEnterView, LiveEnterMessage liveEnterMessage) {
        this.b = highLevelUserEnterView;
        this.a = liveEnterMessage;
    }

    public void run() {
        this.b.startEnterAnim(this.a);
    }
}
