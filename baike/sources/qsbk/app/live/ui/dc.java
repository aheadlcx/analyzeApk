package qsbk.app.live.ui;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;

class dc implements AnimatorUpdateListener {
    final /* synthetic */ LiveBaseActivity a;

    dc(LiveBaseActivity liveBaseActivity) {
        this.a = liveBaseActivity;
    }

    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        this.a.getWindow().getDecorView().getBackground().setAlpha(((Integer) valueAnimator.getAnimatedValue()).intValue());
    }
}
