package com.plattysoft.leonids;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;

class b implements AnimatorUpdateListener {
    final /* synthetic */ ParticleSystem a;

    b(ParticleSystem particleSystem) {
        this.a = particleSystem;
    }

    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        this.a.b((long) ((Integer) valueAnimator.getAnimatedValue()).intValue());
    }
}
