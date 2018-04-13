package com.scwang.smartrefresh.layout.b;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;

class a$3 implements AnimatorUpdateListener {
    final /* synthetic */ a a;

    a$3(a aVar) {
        this.a = aVar;
    }

    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        a.b(this.a).setAlpha(((Float) valueAnimator.getAnimatedValue()).floatValue());
    }
}
