package android.support.v4.app;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;
import android.view.ViewGroup;

class o extends AnimatorListenerAdapter {
    final /* synthetic */ ViewGroup a;
    final /* synthetic */ View b;
    final /* synthetic */ Fragment c;
    final /* synthetic */ k d;

    o(k kVar, ViewGroup viewGroup, View view, Fragment fragment) {
        this.d = kVar;
        this.a = viewGroup;
        this.b = view;
        this.c = fragment;
    }

    public void onAnimationEnd(Animator animator) {
        this.a.endViewTransition(this.b);
        Animator animator2 = this.c.getAnimator();
        this.c.setAnimator(null);
        if (animator2 != null && this.a.indexOfChild(this.b) < 0) {
            this.d.a(this.c, this.c.getStateAfterAnimating(), 0, 0, false);
        }
    }
}
