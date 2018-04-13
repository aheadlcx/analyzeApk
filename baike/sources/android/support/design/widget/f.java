package android.support.design.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

class f extends AnimatorListenerAdapter {
    final /* synthetic */ int a;
    final /* synthetic */ BaseTransientBottomBar b;

    f(BaseTransientBottomBar baseTransientBottomBar, int i) {
        this.b = baseTransientBottomBar;
        this.a = i;
    }

    public void onAnimationStart(Animator animator) {
        this.b.g.animateContentOut(0, 180);
    }

    public void onAnimationEnd(Animator animator) {
        this.b.c(this.a);
    }
}
