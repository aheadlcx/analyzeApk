package android.support.design.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

class bc extends AnimatorListenerAdapter {
    final /* synthetic */ int a;
    final /* synthetic */ c b;

    bc(c cVar, int i) {
        this.b = cVar;
        this.a = i;
    }

    public void onAnimationEnd(Animator animator) {
        this.b.a = this.a;
        this.b.b = 0.0f;
    }
}
