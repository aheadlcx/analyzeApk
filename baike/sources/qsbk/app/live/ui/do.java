package qsbk.app.live.ui;

import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.ImageView;

class do implements AnimationListener {
    final /* synthetic */ ImageView a;
    final /* synthetic */ LivePullActivity b;

    do(LivePullActivity livePullActivity, ImageView imageView) {
        this.b = livePullActivity;
        this.a = imageView;
    }

    public void onAnimationStart(Animation animation) {
    }

    public void onAnimationRepeat(Animation animation) {
    }

    public void onAnimationEnd(Animation animation) {
        this.b.postDelayed(new dp(this));
    }
}
