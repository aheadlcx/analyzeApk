package android.support.design.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

class an extends AnimatorListenerAdapter {
    final /* synthetic */ boolean a;
    final /* synthetic */ c b;
    final /* synthetic */ al c;

    an(al alVar, boolean z, c cVar) {
        this.c = alVar;
        this.a = z;
        this.b = cVar;
    }

    public void onAnimationStart(Animator animator) {
        this.c.n.a(0, this.a);
    }

    public void onAnimationEnd(Animator animator) {
        this.c.b = 0;
        if (this.b != null) {
            this.b.onShown();
        }
    }
}
