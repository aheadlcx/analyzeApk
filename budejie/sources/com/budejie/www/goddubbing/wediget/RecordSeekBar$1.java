package com.budejie.www.goddubbing.wediget;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;

class RecordSeekBar$1 implements AnimatorUpdateListener {
    final /* synthetic */ RecordSeekBar a;

    RecordSeekBar$1(RecordSeekBar recordSeekBar) {
        this.a = recordSeekBar;
    }

    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        this.a.j = ((Integer) valueAnimator.getAnimatedValue()).intValue();
        this.a.e = (float) this.a.j;
        this.a.invalidate();
        if (this.a.j == RecordSeekBar.a(this.a) && RecordSeekBar.b(this.a) != null) {
            RecordSeekBar.b(this.a).c();
        }
    }
}
