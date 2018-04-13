package qsbk.app.live.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;

class gr extends AnimatorListenerAdapter {
    final /* synthetic */ ObjectAnimator a;
    final /* synthetic */ LevelUpView b;

    gr(LevelUpView levelUpView, ObjectAnimator objectAnimator) {
        this.b = levelUpView;
        this.a = objectAnimator;
    }

    public void onAnimationEnd(Animator animator) {
        this.a.start();
        this.b.f.play();
    }
}
