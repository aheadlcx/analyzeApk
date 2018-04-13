package qsbk.app.live.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;

class gw extends AnimatorListenerAdapter {
    final /* synthetic */ ObjectAnimator a;
    final /* synthetic */ ObjectAnimator b;
    final /* synthetic */ LevelUpView c;

    gw(LevelUpView levelUpView, ObjectAnimator objectAnimator, ObjectAnimator objectAnimator2) {
        this.c = levelUpView;
        this.a = objectAnimator;
        this.b = objectAnimator2;
    }

    public void onAnimationEnd(Animator animator) {
        this.a.start();
        this.b.start();
    }
}
