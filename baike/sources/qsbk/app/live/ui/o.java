package qsbk.app.live.ui;

import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;

class o implements AnimationListener {
    final /* synthetic */ LiveBaseActivity a;

    o(LiveBaseActivity liveBaseActivity) {
        this.a = liveBaseActivity;
    }

    public void onAnimationStart(Animation animation) {
    }

    public void onAnimationEnd(Animation animation) {
        this.a.k.setVisibility(8);
    }

    public void onAnimationRepeat(Animation animation) {
    }
}
