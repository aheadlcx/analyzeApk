package android.support.v4.widget;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;

class c implements AnimatorListener {
    final /* synthetic */ a a;
    final /* synthetic */ CircularProgressDrawable b;

    c(CircularProgressDrawable circularProgressDrawable, a aVar) {
        this.b = circularProgressDrawable;
        this.a = aVar;
    }

    public void onAnimationStart(Animator animator) {
        this.b.h = 0.0f;
    }

    public void onAnimationEnd(Animator animator) {
    }

    public void onAnimationCancel(Animator animator) {
    }

    public void onAnimationRepeat(Animator animator) {
        this.b.a(1.0f, this.a, true);
        this.a.u();
        this.a.h();
        if (this.b.i) {
            this.b.i = false;
            animator.cancel();
            animator.setDuration(1332);
            animator.start();
            this.a.a(false);
            return;
        }
        this.b.h = this.b.h + 1.0f;
    }
}
