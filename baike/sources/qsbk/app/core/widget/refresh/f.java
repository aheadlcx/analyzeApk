package qsbk.app.core.widget.refresh;

import android.view.animation.Animation;
import android.view.animation.Transformation;

class f extends Animation {
    final /* synthetic */ SwipeRefreshLayoutBoth a;

    f(SwipeRefreshLayoutBoth swipeRefreshLayoutBoth) {
        this.a = swipeRefreshLayoutBoth;
    }

    public void applyTransformation(float f, Transformation transformation) {
        SwipeRefreshLayoutBoth.a(this.a, f);
    }
}
