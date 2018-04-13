package qsbk.app.live.widget;

import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import qsbk.app.live.widget.GlobalGiftView.MarqueeItem;

class el implements AnimationListener {
    final /* synthetic */ MarqueeItem a;

    el(MarqueeItem marqueeItem) {
        this.a = marqueeItem;
    }

    public void onAnimationStart(Animation animation) {
        this.a.setVisibility(0);
        this.a.startTime = System.currentTimeMillis();
    }

    public void onAnimationEnd(Animation animation) {
        this.a.setVisibility(4);
        this.a.a();
    }

    public void onAnimationRepeat(Animation animation) {
    }
}
