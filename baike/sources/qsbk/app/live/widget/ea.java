package qsbk.app.live.widget;

import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import qsbk.app.live.model.LiveGiftMessage;
import qsbk.app.live.widget.GiftLayout.GiftInfo;

class ea implements AnimationListener {
    final /* synthetic */ GiftInfo a;
    final /* synthetic */ GiftLayout b;

    ea(GiftLayout giftLayout, GiftInfo giftInfo) {
        this.b = giftLayout;
        this.a = giftInfo;
    }

    public void onAnimationStart(Animation animation) {
    }

    public void onAnimationEnd(Animation animation) {
        this.a.setVisibility(4);
        this.a.isAvailable = true;
        this.a.i.stop();
        this.a.i.setVisibility(4);
        if (this.b.d != null && this.b.d.size() > 0) {
            LiveGiftMessage liveGiftMessage;
            synchronized (this) {
                liveGiftMessage = (LiveGiftMessage) this.b.d.remove(0);
            }
            if (liveGiftMessage != null) {
                this.b.addGift(liveGiftMessage);
            }
        }
    }

    public void onAnimationRepeat(Animation animation) {
    }
}
