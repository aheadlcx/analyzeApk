package qsbk.app.widget;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import com.facebook.drawee.drawable.ScalingUtils.InterpolatingScaleType;
import com.facebook.drawee.generic.GenericDraweeHierarchy;

class fb implements AnimatorUpdateListener {
    final /* synthetic */ InterpolatingScaleType a;
    final /* synthetic */ TransitionDraweeView b;

    fb(TransitionDraweeView transitionDraweeView, InterpolatingScaleType interpolatingScaleType) {
        this.b = transitionDraweeView;
        this.a = interpolatingScaleType;
    }

    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        if (this.b.h != null) {
            this.b.h.onAnimationUpdate(valueAnimator);
        }
        float animatedFraction = valueAnimator.getAnimatedFraction();
        this.a.setValue(1.0f - animatedFraction);
        this.b.setValidRect(this.b.b(animatedFraction));
        ((GenericDraweeHierarchy) this.b.getHierarchy()).setActualImageScaleType(this.a);
        if (this.b.k) {
            this.b.setAlpha(1.0f - animatedFraction);
        }
        this.b.invalidate();
    }
}
