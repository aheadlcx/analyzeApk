package android.support.v7.widget.helper;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;

class h implements AnimatorUpdateListener {
    final /* synthetic */ b a;

    h(b bVar) {
        this.a = bVar;
    }

    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        this.a.setFraction(valueAnimator.getAnimatedFraction());
    }
}
