package android.support.design.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

class o extends AnimatorListenerAdapter {
    final /* synthetic */ BaseTransientBottomBar a;

    o(BaseTransientBottomBar baseTransientBottomBar) {
        this.a = baseTransientBottomBar;
    }

    public void onAnimationStart(Animator animator) {
        this.a.g.animateContentIn(70, 180);
    }

    public void onAnimationEnd(Animator animator) {
        this.a.c();
    }
}
