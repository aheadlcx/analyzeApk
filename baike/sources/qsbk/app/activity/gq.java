package qsbk.app.activity;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.graphics.Color;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import qsbk.app.widget.TransitionDraweeView.SimpleAnimationListener;

class gq extends SimpleAnimationListener {
    final /* synthetic */ CircleArticleImageViewer a;

    gq(CircleArticleImageViewer circleArticleImageViewer) {
        this.a = circleArticleImageViewer;
    }

    public void onAnimationStart(Animator animator) {
    }

    public void onAnimationEnd(Animator animator) {
        ((GenericDraweeHierarchy) this.a.t.getHierarchy()).setActualImageScaleType(this.a.h);
        this.a.b.setVisibility(0);
        this.a.onPageSelected(this.a.d);
        this.a.t.setOnEnterAnimListener(null);
    }

    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        super.onAnimationUpdate(valueAnimator);
        float animatedFraction = valueAnimator.getAnimatedFraction();
        this.a.t.setBackgroundColor(Color.argb((int) ((255.0f * animatedFraction) + 0.5f), 0, 0, 0));
        this.a.l.setAlpha(animatedFraction);
    }
}
