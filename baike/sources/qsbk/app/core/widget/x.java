package qsbk.app.core.widget;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.graphics.Color;

class x implements AnimatorUpdateListener {
    final /* synthetic */ SwitchButton a;

    x(SwitchButton switchButton) {
        this.a = switchButton;
    }

    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        this.a.j = ((Integer) valueAnimator.getAnimatedValue()).intValue();
        float a = ((float) ((this.a.j - this.a.c) - this.a.e)) / ((float) ((this.a.a - (this.a.c * 2)) - (this.a.e * 2)));
        this.a.i = Color.argb(255, (int) (((float) Color.red(this.a.h)) + ((((float) Color.red(this.a.g)) - ((float) Color.red(this.a.h))) * a)), (int) (((float) Color.green(this.a.h)) + ((((float) Color.green(this.a.g)) - ((float) Color.green(this.a.h))) * a)), (int) ((a * (((float) Color.blue(this.a.g)) - ((float) Color.blue(this.a.h)))) + ((float) Color.blue(this.a.h))));
        this.a.invalidate();
    }
}
