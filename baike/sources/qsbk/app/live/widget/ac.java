package qsbk.app.live.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;

class ac extends AnimatorListenerAdapter {
    final /* synthetic */ AnimatorSet a;
    final /* synthetic */ ab b;

    ac(ab abVar, AnimatorSet animatorSet) {
        this.b = abVar;
        this.a = animatorSet;
    }

    public void onAnimationStart(Animator animator) {
        this.b.c.e.setVisibility(0);
    }

    public void onAnimationEnd(Animator animator) {
        if (this.b.c.r < 1) {
            this.b.c.postDelayed(new ad(this), 400);
            this.b.c.r = this.b.c.r + 1;
        }
    }
}
