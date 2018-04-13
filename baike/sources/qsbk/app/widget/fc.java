package qsbk.app.widget;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.ValueAnimator;

class fc implements AnimatorListener {
    final /* synthetic */ ValueAnimator a;
    final /* synthetic */ TransitionDraweeView b;

    fc(TransitionDraweeView transitionDraweeView, ValueAnimator valueAnimator) {
        this.b = transitionDraweeView;
        this.a = valueAnimator;
    }

    public void onAnimationStart(Animator animator) {
        if (this.b.h != null) {
            this.b.h.onAnimationStart(animator);
        }
    }

    public void onAnimationEnd(Animator animator) {
        if (this.b.h != null) {
            this.b.h.onAnimationEnd(animator);
        }
        this.a.removeAllUpdateListeners();
        this.a.removeAllListeners();
    }

    public void onAnimationCancel(Animator animator) {
        if (this.b.h != null) {
            this.b.h.onAnimationCancel(animator);
        }
    }

    public void onAnimationRepeat(Animator animator) {
        if (this.b.h != null) {
            this.b.h.onAnimationRepeat(animator);
        }
    }
}
