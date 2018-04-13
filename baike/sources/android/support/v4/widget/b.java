package android.support.v4.widget;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;

class b implements AnimatorUpdateListener {
    final /* synthetic */ a a;
    final /* synthetic */ CircularProgressDrawable b;

    b(CircularProgressDrawable circularProgressDrawable, a aVar) {
        this.b = circularProgressDrawable;
        this.a = aVar;
    }

    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
        this.b.a(floatValue, this.a);
        this.b.a(floatValue, this.a, false);
        this.b.invalidateSelf();
    }
}
