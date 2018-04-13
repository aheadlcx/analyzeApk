package qsbk.app.live.animation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

class p extends AnimatorListenerAdapter {
    final /* synthetic */ o a;

    p(o oVar) {
        this.a = oVar;
    }

    public void onAnimationEnd(Animator animator) {
        super.onAnimationEnd(animator);
        this.a.e.a(this.a.a);
    }
}
