package qsbk.app.widget;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.ValueAnimator;

class fa implements AnimatorListener {
    final /* synthetic */ ValueAnimator a;
    final /* synthetic */ TransitionDraweeView b;

    fa(TransitionDraweeView transitionDraweeView, ValueAnimator valueAnimator) {
        this.b = transitionDraweeView;
        this.a = valueAnimator;
    }

    public void onAnimationStart(Animator animator) {
        if (this.b.g != null) {
            this.b.g.onAnimationStart(animator);
        }
    }

    public void onAnimationEnd(Animator animator) {
        if (this.b.g != null) {
            this.b.g.onAnimationEnd(animator);
        }
        this.a.removeAllUpdateListeners();
        this.a.removeAllListeners();
    }

    public void onAnimationCancel(Animator animator) {
        if (this.b.g != null) {
            this.b.g.onAnimationCancel(animator);
        }
    }

    public void onAnimationRepeat(Animator animator) {
        if (this.b.g != null) {
            this.b.g.onAnimationRepeat(animator);
        }
    }
}
