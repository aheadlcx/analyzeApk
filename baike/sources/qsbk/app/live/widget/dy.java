package qsbk.app.live.widget;

import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import qsbk.app.live.widget.GiftLayout.GiftInfo;

class dy implements AnimationListener {
    final /* synthetic */ GiftInfo a;
    final /* synthetic */ Animation b;
    final /* synthetic */ GiftLayout c;

    dy(GiftLayout giftLayout, GiftInfo giftInfo, Animation animation) {
        this.c = giftLayout;
        this.a = giftInfo;
        this.b = animation;
    }

    public void onAnimationStart(Animation animation) {
    }

    public void onAnimationEnd(Animation animation) {
        this.a.startAnimation(this.b);
    }

    public void onAnimationRepeat(Animation animation) {
    }
}
