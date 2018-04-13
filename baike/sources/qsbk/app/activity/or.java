package qsbk.app.activity;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.graphics.Color;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import qsbk.app.widget.TransitionDraweeView.SimpleAnimationListener;

class or extends SimpleAnimationListener {
    final /* synthetic */ ImageViewer a;

    or(ImageViewer imageViewer) {
        this.a = imageViewer;
    }

    public void onAnimationStart(Animator animator) {
    }

    public void onAnimationEnd(Animator animator) {
        ((GenericDraweeHierarchy) this.a.k.getHierarchy()).setActualImageScaleType(this.a.g);
        this.a.d.setVisibility(0);
        this.a.k.setOnEnterAnimListener(null);
        this.a.k.setVisibility(4);
        this.a.onPageSelected(this.a.b);
    }

    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        super.onAnimationUpdate(valueAnimator);
        this.a.k.setBackgroundColor(Color.argb((int) ((valueAnimator.getAnimatedFraction() * 255.0f) + 0.5f), 0, 0, 0));
    }
}
