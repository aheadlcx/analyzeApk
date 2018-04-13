package qsbk.app.core.widget.refresh;

import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;

class e implements AnimationListener {
    final /* synthetic */ SwipeRefreshLayoutBoth a;

    e(SwipeRefreshLayoutBoth swipeRefreshLayoutBoth) {
        this.a = swipeRefreshLayoutBoth;
    }

    public void onAnimationStart(Animation animation) {
    }

    public void onAnimationRepeat(Animation animation) {
    }

    public void onAnimationEnd(Animation animation) {
        if (SwipeRefreshLayoutBoth.a(this.a)) {
            SwipeRefreshLayoutBoth.b(this.a).setAlpha(255);
            SwipeRefreshLayoutBoth.b(this.a).start();
            if (SwipeRefreshLayoutBoth.c(this.a) && SwipeRefreshLayoutBoth.d(this.a) != null) {
                SwipeRefreshLayoutBoth.d(this.a).onRefresh(SwipeRefreshLayoutBoth.e(this.a));
            }
        } else {
            SwipeRefreshLayoutBoth.b(this.a).stop();
            SwipeRefreshLayoutBoth.f(this.a).setVisibility(8);
            SwipeRefreshLayoutBoth.a(this.a, 255);
            if (SwipeRefreshLayoutBoth.g(this.a)) {
                SwipeRefreshLayoutBoth.a(this.a, 0.0f);
            } else {
                SwipeRefreshLayoutBoth.a(this.a, this.a.b - SwipeRefreshLayoutBoth.h(this.a), true);
            }
        }
        SwipeRefreshLayoutBoth.b(this.a, SwipeRefreshLayoutBoth.f(this.a).getTop());
    }
}
