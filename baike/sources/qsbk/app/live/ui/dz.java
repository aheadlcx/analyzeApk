package qsbk.app.live.ui;

import android.animation.AnimatorSet;

class dz implements Runnable {
    final /* synthetic */ AnimatorSet a;
    final /* synthetic */ AnimatorSet b;
    final /* synthetic */ LivePullActivity c;

    dz(LivePullActivity livePullActivity, AnimatorSet animatorSet, AnimatorSet animatorSet2) {
        this.c = livePullActivity;
        this.a = animatorSet;
        this.b = animatorSet2;
    }

    public void run() {
        this.a.start();
        this.b.start();
    }
}
