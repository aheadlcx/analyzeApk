package qsbk.app.core.widget.refresh;

import android.view.animation.Animation;
import android.view.animation.Transformation;

class g extends Animation {
    final /* synthetic */ SwipeRefreshLayoutBoth a;

    g(SwipeRefreshLayoutBoth swipeRefreshLayoutBoth) {
        this.a = swipeRefreshLayoutBoth;
    }

    public void applyTransformation(float f, Transformation transformation) {
        SwipeRefreshLayoutBoth.a(this.a, 1.0f - f);
    }
}
