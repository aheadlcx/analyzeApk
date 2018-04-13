package android.support.design.widget;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;

class bb implements AnimatorUpdateListener {
    final /* synthetic */ int a;
    final /* synthetic */ int b;
    final /* synthetic */ int c;
    final /* synthetic */ int d;
    final /* synthetic */ c e;

    bb(c cVar, int i, int i2, int i3, int i4) {
        this.e = cVar;
        this.a = i;
        this.b = i2;
        this.c = i3;
        this.d = i4;
    }

    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        float animatedFraction = valueAnimator.getAnimatedFraction();
        this.e.a(a.a(this.a, this.b, animatedFraction), a.a(this.c, this.d, animatedFraction));
    }
}
