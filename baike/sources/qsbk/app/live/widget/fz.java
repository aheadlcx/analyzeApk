package qsbk.app.live.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

class fz extends AnimatorListenerAdapter {
    final /* synthetic */ fy a;

    fz(fy fyVar) {
        this.a = fyVar;
    }

    public void onAnimationEnd(Animator animator) {
        this.a.a.c.e.removeView(this.a.a.b);
        this.a.a.c.e.removeView(this.a.a.c.d);
    }
}
