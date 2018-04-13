package qsbk.app.live.widget;

import android.animation.AnimatorSet;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import qsbk.app.live.widget.GiftLayout.GiftInfo;

class dz implements AnimationListener {
    final /* synthetic */ AnimatorSet a;
    final /* synthetic */ GiftInfo b;
    final /* synthetic */ AnimationSet c;
    final /* synthetic */ GiftLayout d;

    dz(GiftLayout giftLayout, AnimatorSet animatorSet, GiftInfo giftInfo, AnimationSet animationSet) {
        this.d = giftLayout;
        this.a = animatorSet;
        this.b = giftInfo;
        this.c = animationSet;
    }

    public void onAnimationStart(Animation animation) {
        if (!this.d.e) {
            if (this.d.h == null || !this.d.h.isMessageOverloadOrLowDevice()) {
                this.a.start();
            }
            this.b.e.setVisibility(0);
        }
    }

    public void onAnimationEnd(Animation animation) {
        this.b.startAnimation(this.c);
    }

    public void onAnimationRepeat(Animation animation) {
    }
}
