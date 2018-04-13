package android.support.design.widget;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.support.v4.view.ViewCompat;

class p implements AnimatorUpdateListener {
    final /* synthetic */ int a;
    final /* synthetic */ BaseTransientBottomBar b;
    private int c = this.a;

    p(BaseTransientBottomBar baseTransientBottomBar, int i) {
        this.b = baseTransientBottomBar;
        this.a = i;
    }

    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        int intValue = ((Integer) valueAnimator.getAnimatedValue()).intValue();
        if (BaseTransientBottomBar.d) {
            ViewCompat.offsetTopAndBottom(this.b.b, intValue - this.c);
        } else {
            this.b.b.setTranslationY((float) intValue);
        }
        this.c = intValue;
    }
}
