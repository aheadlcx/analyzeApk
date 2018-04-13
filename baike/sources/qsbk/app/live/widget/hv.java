package qsbk.app.live.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;

class hv extends AnimatorListenerAdapter {
    final /* synthetic */ AnimatorSet a;
    final /* synthetic */ hu b;

    hv(hu huVar, AnimatorSet animatorSet) {
        this.b = huVar;
        this.a = animatorSet;
    }

    public void onAnimationStart(Animator animator) {
        this.b.b.d.setVisibility(0);
    }

    public void onAnimationEnd(Animator animator) {
        if (this.b.b.j < 1) {
            this.b.b.postDelayed(new hw(this), 400);
            this.b.b.j = this.b.b.j + 1;
        }
    }
}
