package qsbk.app.live.widget;

import android.animation.ObjectAnimator;
import android.view.View;

class fy implements Runnable {
    final /* synthetic */ fx a;

    fy(fx fxVar) {
        this.a = fxVar;
    }

    public void run() {
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.a.b, View.ALPHA, new float[]{1.0f, 0.0f});
        ofFloat.setDuration(200);
        ofFloat.start();
        ofFloat.addListener(new fz(this));
    }
}
