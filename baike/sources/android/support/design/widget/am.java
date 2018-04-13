package android.support.design.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

class am extends AnimatorListenerAdapter {
    final /* synthetic */ boolean a;
    final /* synthetic */ c b;
    final /* synthetic */ al c;
    private boolean d;

    am(al alVar, boolean z, c cVar) {
        this.c = alVar;
        this.a = z;
        this.b = cVar;
    }

    public void onAnimationStart(Animator animator) {
        this.c.n.a(0, this.a);
        this.d = false;
    }

    public void onAnimationCancel(Animator animator) {
        this.d = true;
    }

    public void onAnimationEnd(Animator animator) {
        this.c.b = 0;
        if (!this.d) {
            this.c.n.a(this.a ? 8 : 4, this.a);
            if (this.b != null) {
                this.b.onHidden();
            }
        }
    }
}
