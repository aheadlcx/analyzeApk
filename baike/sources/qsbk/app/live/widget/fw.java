package qsbk.app.live.widget;

import android.animation.ObjectAnimator;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.RelativeLayout;
import qsbk.app.core.utils.WindowUtils;
import qsbk.app.core.widget.FrameAnimationView;
import qsbk.app.live.R;

class fw implements Runnable {
    final /* synthetic */ int a;
    final /* synthetic */ int b;
    final /* synthetic */ long c;
    final /* synthetic */ FrameAnimationView d;
    final /* synthetic */ LargeGiftLayout e;

    fw(LargeGiftLayout largeGiftLayout, int i, int i2, long j, FrameAnimationView frameAnimationView) {
        this.e = largeGiftLayout;
        this.a = i;
        this.b = i2;
        this.c = j;
        this.d = frameAnimationView;
    }

    public void run() {
        View inflate = View.inflate(this.e.b, R.layout.luckegg_number, null);
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams.topMargin = ((this.a + this.b) / 2) + WindowUtils.dp2Px(5);
        this.e.addView(inflate, layoutParams);
        NumberAnimTextView numberAnimTextView = (NumberAnimTextView) inflate.findViewById(R.id.tv_luckyegg_num);
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(inflate, View.ALPHA, new float[]{0.0f, 1.0f});
        ofFloat.setDuration(200);
        ofFloat.start();
        ofFloat.addListener(new fx(this, numberAnimTextView, inflate));
    }
}
