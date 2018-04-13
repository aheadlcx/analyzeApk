package qsbk.app.core.widget.refresh;

import android.view.animation.Animation;
import android.view.animation.Transformation;

class k extends Animation {
    final /* synthetic */ SwipeRefreshLayoutBoth a;

    k(SwipeRefreshLayoutBoth swipeRefreshLayoutBoth) {
        this.a = swipeRefreshLayoutBoth;
    }

    public void applyTransformation(float f, Transformation transformation) {
        SwipeRefreshLayoutBoth.b(this.a, f);
    }
}
