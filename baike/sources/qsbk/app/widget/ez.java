package qsbk.app.widget;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import com.facebook.drawee.drawable.ScalingUtils.InterpolatingScaleType;
import com.facebook.drawee.generic.GenericDraweeHierarchy;

class ez implements AnimatorUpdateListener {
    final /* synthetic */ InterpolatingScaleType a;
    final /* synthetic */ TransitionDraweeView b;

    ez(TransitionDraweeView transitionDraweeView, InterpolatingScaleType interpolatingScaleType) {
        this.b = transitionDraweeView;
        this.a = interpolatingScaleType;
    }

    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        if (this.b.g != null) {
            this.b.g.onAnimationUpdate(valueAnimator);
        }
        float animatedFraction = valueAnimator.getAnimatedFraction();
        this.b.setValidRect(this.b.a(animatedFraction));
        this.a.setValue(animatedFraction);
        ((GenericDraweeHierarchy) this.b.getHierarchy()).setActualImageScaleType(this.a);
        this.b.invalidate();
    }
}
