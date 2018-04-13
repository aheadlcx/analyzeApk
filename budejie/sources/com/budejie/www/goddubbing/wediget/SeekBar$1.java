package com.budejie.www.goddubbing.wediget;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import com.budejie.www.util.an;

class SeekBar$1 implements AnimatorUpdateListener {
    final /* synthetic */ SeekBar a;

    SeekBar$1(SeekBar seekBar) {
        this.a = seekBar;
    }

    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        this.a.j = ((Integer) valueAnimator.getAnimatedValue()).intValue();
        this.a.e = (float) this.a.j;
        this.a.invalidate();
        if (this.a.j == an.x(this.a.getContext()) && SeekBar.a(this.a) != null) {
            SeekBar.a(this.a).b();
        }
    }
}
