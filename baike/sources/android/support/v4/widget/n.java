package android.support.v4.widget;

import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;

class n implements AnimationListener {
    final /* synthetic */ SwipeRefreshLayout a;

    n(SwipeRefreshLayout swipeRefreshLayout) {
        this.a = swipeRefreshLayout;
    }

    public void onAnimationStart(Animation animation) {
    }

    public void onAnimationRepeat(Animation animation) {
    }

    public void onAnimationEnd(Animation animation) {
        if (this.a.b) {
            this.a.j.setAlpha(255);
            this.a.j.start();
            if (this.a.k && this.a.a != null) {
                this.a.a.onRefresh();
            }
            this.a.c = this.a.e.getTop();
            return;
        }
        this.a.a();
    }
}
