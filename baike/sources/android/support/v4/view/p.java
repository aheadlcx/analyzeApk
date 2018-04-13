package android.support.v4.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;

class p extends AnimatorListenerAdapter {
    final /* synthetic */ ViewPropertyAnimatorListener a;
    final /* synthetic */ View b;
    final /* synthetic */ ViewPropertyAnimatorCompat c;

    p(ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, ViewPropertyAnimatorListener viewPropertyAnimatorListener, View view) {
        this.c = viewPropertyAnimatorCompat;
        this.a = viewPropertyAnimatorListener;
        this.b = view;
    }

    public void onAnimationCancel(Animator animator) {
        this.a.onAnimationCancel(this.b);
    }

    public void onAnimationEnd(Animator animator) {
        this.a.onAnimationEnd(this.b);
    }

    public void onAnimationStart(Animator animator) {
        this.a.onAnimationStart(this.b);
    }
}
