package qsbk.app.activity;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.graphics.Color;
import qsbk.app.widget.TransitionDraweeView.SimpleAnimationListener;

class gr extends SimpleAnimationListener {
    final /* synthetic */ Runnable a;
    final /* synthetic */ CircleArticleImageViewer b;

    gr(CircleArticleImageViewer circleArticleImageViewer, Runnable runnable) {
        this.b = circleArticleImageViewer;
        this.a = runnable;
    }

    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        super.onAnimationUpdate(valueAnimator);
        this.b.t.setBackgroundColor(Color.argb((int) (((1.0f - valueAnimator.getAnimatedFraction()) * 255.0f) + 0.5f), 0, 0, 0));
    }

    public void onAnimationEnd(Animator animator) {
        this.a.run();
    }
}
