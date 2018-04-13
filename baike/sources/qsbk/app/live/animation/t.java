package qsbk.app.live.animation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.widget.ImageView;

class t extends AnimatorListenerAdapter {
    final /* synthetic */ s a;

    t(s sVar) {
        this.a = sVar;
    }

    public void onAnimationEnd(Animator animator) {
        super.onAnimationEnd(animator);
        if (this.a.a instanceof ImageView) {
            this.a.i.a((ImageView) this.a.a);
        }
    }
}
