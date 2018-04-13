package qsbk.app.live.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;

class fp extends AnimatorListenerAdapter {
    final /* synthetic */ AnimatorSet a;
    final /* synthetic */ fo b;

    fp(fo foVar, AnimatorSet animatorSet) {
        this.b = foVar;
        this.a = animatorSet;
    }

    public void onAnimationStart(Animator animator) {
        this.b.c.g.setVisibility(0);
    }

    public void onAnimationEnd(Animator animator) {
        if (this.b.c.w < 1) {
            this.b.c.postDelayed(new fq(this), 400);
            this.b.c.w = this.b.c.w + 1;
        }
    }
}
