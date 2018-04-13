package qsbk.app.live.ui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

class dd extends AnimatorListenerAdapter {
    final /* synthetic */ LiveBaseActivity a;

    dd(LiveBaseActivity liveBaseActivity) {
        this.a = liveBaseActivity;
    }

    public void onAnimationEnd(Animator animator) {
        LiveBaseActivity.E(this.a);
    }
}
