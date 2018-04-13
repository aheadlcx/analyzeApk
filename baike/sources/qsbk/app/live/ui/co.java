package qsbk.app.live.ui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;

class co extends AnimatorListenerAdapter {
    final /* synthetic */ ObjectAnimator a;
    final /* synthetic */ LiveBaseActivity b;

    co(LiveBaseActivity liveBaseActivity, ObjectAnimator objectAnimator) {
        this.b = liveBaseActivity;
        this.a = objectAnimator;
    }

    public void onAnimationEnd(Animator animator) {
        this.a.start();
    }
}
