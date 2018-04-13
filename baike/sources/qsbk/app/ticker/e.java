package qsbk.app.ticker;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

class e extends AnimatorListenerAdapter {
    final /* synthetic */ TickerView a;

    e(TickerView tickerView) {
        this.a = tickerView;
    }

    public void onAnimationEnd(Animator animator) {
        this.a.d.a();
        this.a.a();
    }
}
