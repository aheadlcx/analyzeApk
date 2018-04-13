package qsbk.app.live.ui;

import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;

class k implements AnimationListener {
    final /* synthetic */ boolean a;
    final /* synthetic */ LiveBaseActivity b;

    k(LiveBaseActivity liveBaseActivity, boolean z) {
        this.b = liveBaseActivity;
        this.a = z;
    }

    public void onAnimationStart(Animation animation) {
    }

    public void onAnimationEnd(Animation animation) {
        this.b.l.setVisibility(this.a ? 0 : 4);
    }

    public void onAnimationRepeat(Animation animation) {
    }
}
