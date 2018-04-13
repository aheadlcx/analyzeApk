package android.support.design.widget;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.support.v4.view.ViewCompat;

class g implements AnimatorUpdateListener {
    final /* synthetic */ BaseTransientBottomBar a;
    private int b = 0;

    g(BaseTransientBottomBar baseTransientBottomBar) {
        this.a = baseTransientBottomBar;
    }

    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        int intValue = ((Integer) valueAnimator.getAnimatedValue()).intValue();
        if (BaseTransientBottomBar.d) {
            ViewCompat.offsetTopAndBottom(this.a.b, intValue - this.b);
        } else {
            this.a.b.setTranslationY((float) intValue);
        }
        this.b = intValue;
    }
}
