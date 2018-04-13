package qsbk.app.core.widget.refresh;

import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;

class n implements AnimationListener {
    final /* synthetic */ SwipeRefreshLayoutBottom a;

    n(SwipeRefreshLayoutBottom swipeRefreshLayoutBottom) {
        this.a = swipeRefreshLayoutBottom;
    }

    public void onAnimationStart(Animation animation) {
    }

    public void onAnimationRepeat(Animation animation) {
    }

    public void onAnimationEnd(Animation animation) {
        if (SwipeRefreshLayoutBottom.a(this.a)) {
            SwipeRefreshLayoutBottom.b(this.a).setAlpha(255);
            SwipeRefreshLayoutBottom.b(this.a).start();
            if (SwipeRefreshLayoutBottom.c(this.a) && SwipeRefreshLayoutBottom.d(this.a) != null) {
                SwipeRefreshLayoutBottom.d(this.a).onRefresh();
            }
        } else {
            SwipeRefreshLayoutBottom.b(this.a).stop();
            SwipeRefreshLayoutBottom.e(this.a).setVisibility(8);
            SwipeRefreshLayoutBottom.a(this.a, 255);
            if (SwipeRefreshLayoutBottom.f(this.a)) {
                SwipeRefreshLayoutBottom.a(this.a, 0.0f);
            } else {
                SwipeRefreshLayoutBottom.a(this.a, this.a.b - SwipeRefreshLayoutBottom.g(this.a), true);
            }
        }
        SwipeRefreshLayoutBottom.b(this.a, SwipeRefreshLayoutBottom.e(this.a).getTop());
    }
}
