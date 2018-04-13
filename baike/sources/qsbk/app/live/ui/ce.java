package qsbk.app.live.ui;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.widget.TextView;
import qsbk.app.core.utils.LogUtils;

class ce implements AnimatorUpdateListener {
    final /* synthetic */ TextView a;
    final /* synthetic */ LiveBaseActivity b;

    ce(LiveBaseActivity liveBaseActivity, TextView textView) {
        this.b = liveBaseActivity;
        this.a = textView;
    }

    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        CharSequence obj = valueAnimator.getAnimatedValue().toString();
        LogUtils.d(LiveBaseActivity.a, "red envelopes count down at " + obj);
        this.a.setText(obj);
    }
}
