package qsbk.app.live.widget;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;

class io implements AnimatorUpdateListener {
    final /* synthetic */ in a;

    io(in inVar) {
        this.a = inVar;
    }

    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
        this.a.a.d.setTranslationY(floatValue);
        this.a.a.g.setTranslationY(floatValue);
    }
}
