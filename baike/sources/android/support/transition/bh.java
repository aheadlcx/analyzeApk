package android.support.transition;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

class bh extends AnimatorListenerAdapter {
    final /* synthetic */ Transition a;

    bh(Transition transition) {
        this.a = transition;
    }

    public void onAnimationEnd(Animator animator) {
        this.a.c();
        animator.removeListener(this);
    }
}
