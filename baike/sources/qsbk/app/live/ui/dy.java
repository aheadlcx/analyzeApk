package qsbk.app.live.ui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

class dy extends AnimatorListenerAdapter {
    final /* synthetic */ LivePullActivity a;

    dy(LivePullActivity livePullActivity) {
        this.a = livePullActivity;
    }

    public void onAnimationEnd(Animator animator) {
        this.a.bS.setVisibility(8);
    }
}
