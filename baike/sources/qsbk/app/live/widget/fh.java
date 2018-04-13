package qsbk.app.live.widget;

import qsbk.app.live.model.LiveEnterMessage;

class fh implements Runnable {
    final /* synthetic */ HighLevelUserEnterView a;

    fh(HighLevelUserEnterView highLevelUserEnterView) {
        this.a = highLevelUserEnterView;
    }

    public void run() {
        this.a.removeCallbacks(this);
        if (this.a.b.size() > 0) {
            this.a.setViewContentAndStartAnim((LiveEnterMessage) this.a.b.remove(0));
        }
    }
}
