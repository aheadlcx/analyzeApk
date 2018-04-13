package qsbk.app.live.widget;

import qsbk.app.live.model.LiveMessage;

class hr implements Runnable {
    final /* synthetic */ ProTopRankView a;

    hr(ProTopRankView proTopRankView) {
        this.a = proTopRankView;
    }

    public void run() {
        this.a.removeCallbacks(this);
        if (this.a.g.size() > 0) {
            this.a.setViewContentAndStartAnim((LiveMessage) this.a.g.remove(0));
        }
    }
}
