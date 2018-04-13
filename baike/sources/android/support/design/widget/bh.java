package android.support.design.widget;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;

class bh implements AnimatorUpdateListener {
    final /* synthetic */ TextInputLayout a;

    bh(TextInputLayout textInputLayout) {
        this.a = textInputLayout;
    }

    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        this.a.d.b(((Float) valueAnimator.getAnimatedValue()).floatValue());
    }
}
