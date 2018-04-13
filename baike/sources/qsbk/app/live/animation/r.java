package qsbk.app.live.animation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

class r extends AnimatorListenerAdapter {
    final /* synthetic */ q a;

    r(q qVar) {
        this.a = qVar;
    }

    public void onAnimationEnd(Animator animator) {
        super.onAnimationEnd(animator);
        this.a.k.a(this.a.a);
    }
}
