package qsbk.app.core.widget.refresh;

import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;

class r implements AnimationListener {
    final /* synthetic */ SwipeRefreshLayoutBottom a;

    r(SwipeRefreshLayoutBottom swipeRefreshLayoutBottom) {
        this.a = swipeRefreshLayoutBottom;
    }

    public void onAnimationStart(Animation animation) {
    }

    public void onAnimationEnd(Animation animation) {
        if (!SwipeRefreshLayoutBottom.f(this.a)) {
            SwipeRefreshLayoutBottom.a(this.a, null);
        }
    }

    public void onAnimationRepeat(Animation animation) {
    }
}
