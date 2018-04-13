package qsbk.app.live.ui;

import android.animation.ObjectAnimator;

class cr implements Runnable {
    final /* synthetic */ ObjectAnimator a;
    final /* synthetic */ ObjectAnimator b;
    final /* synthetic */ LiveBaseActivity c;

    cr(LiveBaseActivity liveBaseActivity, ObjectAnimator objectAnimator, ObjectAnimator objectAnimator2) {
        this.c = liveBaseActivity;
        this.a = objectAnimator;
        this.b = objectAnimator2;
    }

    public void run() {
        this.a.start();
        this.b.start();
    }
}
