package android.support.v4.view;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.view.View;

class q implements AnimatorUpdateListener {
    final /* synthetic */ ViewPropertyAnimatorUpdateListener a;
    final /* synthetic */ View b;
    final /* synthetic */ ViewPropertyAnimatorCompat c;

    q(ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, ViewPropertyAnimatorUpdateListener viewPropertyAnimatorUpdateListener, View view) {
        this.c = viewPropertyAnimatorCompat;
        this.a = viewPropertyAnimatorUpdateListener;
        this.b = view;
    }

    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        this.a.onAnimationUpdate(this.b);
    }
}
