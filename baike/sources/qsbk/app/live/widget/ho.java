package qsbk.app.live.widget;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import java.math.BigDecimal;
import qsbk.app.core.utils.FormatUtils;

class ho implements AnimatorUpdateListener {
    final /* synthetic */ NumberAnimTextView a;

    ho(NumberAnimTextView numberAnimTextView) {
        this.a = numberAnimTextView;
    }

    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        this.a.setText(this.a.d + FormatUtils.formatCoupon(((BigDecimal) valueAnimator.getAnimatedValue()).longValue()) + this.a.e);
    }
}
