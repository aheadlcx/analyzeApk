package android.support.design.widget;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;

class ba implements AnimatorUpdateListener {
    final /* synthetic */ TabLayout a;

    ba(TabLayout tabLayout) {
        this.a = tabLayout;
    }

    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        this.a.scrollTo(((Integer) valueAnimator.getAnimatedValue()).intValue(), 0);
    }
}
