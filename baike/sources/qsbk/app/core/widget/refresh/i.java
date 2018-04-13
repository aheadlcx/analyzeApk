package qsbk.app.core.widget.refresh;

import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;

class i implements AnimationListener {
    final /* synthetic */ SwipeRefreshLayoutBoth a;

    i(SwipeRefreshLayoutBoth swipeRefreshLayoutBoth) {
        this.a = swipeRefreshLayoutBoth;
    }

    public void onAnimationStart(Animation animation) {
    }

    public void onAnimationEnd(Animation animation) {
        if (!SwipeRefreshLayoutBoth.g(this.a)) {
            SwipeRefreshLayoutBoth.a(this.a, null);
        }
    }

    public void onAnimationRepeat(Animation animation) {
    }
}
