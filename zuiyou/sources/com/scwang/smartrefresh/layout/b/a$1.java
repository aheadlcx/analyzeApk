package com.scwang.smartrefresh.layout.b;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;

class a$1 implements AnimatorUpdateListener {
    final /* synthetic */ a a;

    a$1(a aVar) {
        this.a = aVar;
    }

    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        a.a(this.a).setWaveHeight(((Integer) valueAnimator.getAnimatedValue()).intValue() / 2);
        a.a(this.a).invalidate();
    }
}
