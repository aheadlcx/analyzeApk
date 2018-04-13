package qsbk.app.ticker;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;

class d implements AnimatorUpdateListener {
    final /* synthetic */ TickerView a;

    d(TickerView tickerView) {
        this.a = tickerView;
    }

    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        this.a.d.a(valueAnimator.getAnimatedFraction());
        this.a.a();
        this.a.invalidate();
    }
}
