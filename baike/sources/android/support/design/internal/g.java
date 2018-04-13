package android.support.design.internal;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.widget.TextView;

class g implements AnimatorUpdateListener {
    final /* synthetic */ TextView a;
    final /* synthetic */ TextScale b;

    g(TextScale textScale, TextView textView) {
        this.b = textScale;
        this.a = textView;
    }

    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
        this.a.setScaleX(floatValue);
        this.a.setScaleY(floatValue);
    }
}
