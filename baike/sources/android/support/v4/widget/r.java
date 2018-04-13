package android.support.v4.widget;

import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;

class r implements AnimationListener {
    final /* synthetic */ SwipeRefreshLayout a;

    r(SwipeRefreshLayout swipeRefreshLayout) {
        this.a = swipeRefreshLayout;
    }

    public void onAnimationStart(Animation animation) {
    }

    public void onAnimationEnd(Animation animation) {
        if (!this.a.d) {
            this.a.a(null);
        }
    }

    public void onAnimationRepeat(Animation animation) {
    }
}
