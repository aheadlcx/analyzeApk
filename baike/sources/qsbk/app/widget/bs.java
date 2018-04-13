package qsbk.app.widget;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;

class bs implements AnimatorUpdateListener {
    final /* synthetic */ ExpandableLayout a;

    bs(ExpandableLayout expandableLayout) {
        this.a = expandableLayout;
    }

    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        this.a.setExpansion(((Float) valueAnimator.getAnimatedValue()).floatValue());
    }
}
