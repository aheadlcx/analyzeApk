package qsbk.app.live.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;

class gx extends AnimatorListenerAdapter {
    final /* synthetic */ ObjectAnimator a;
    final /* synthetic */ LevelUpView b;

    gx(LevelUpView levelUpView, ObjectAnimator objectAnimator) {
        this.b = levelUpView;
        this.a = objectAnimator;
    }

    public void onAnimationStart(Animator animator) {
        this.b.a.setVisibility(0);
    }

    public void onAnimationEnd(Animator animator) {
        this.b.postDelayed(new gy(this), 2000);
    }
}
