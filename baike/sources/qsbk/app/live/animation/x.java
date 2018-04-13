package qsbk.app.live.animation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

class x extends AnimatorListenerAdapter {
    final /* synthetic */ w a;

    x(w wVar) {
        this.a = wVar;
    }

    public void onAnimationEnd(Animator animator) {
        super.onAnimationEnd(animator);
        this.a.j.a(this.a.a);
    }
}
