package qsbk.app.live.ui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

class dr extends AnimatorListenerAdapter {
    final /* synthetic */ LivePullActivity a;

    dr(LivePullActivity livePullActivity) {
        this.a = livePullActivity;
    }

    public void onAnimationEnd(Animator animator) {
        this.a.bR.setVisibility(8);
        this.a.bE.setVisibility(8);
    }
}
