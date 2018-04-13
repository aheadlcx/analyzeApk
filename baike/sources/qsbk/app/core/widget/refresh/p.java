package qsbk.app.core.widget.refresh;

import android.view.animation.Animation;
import android.view.animation.Transformation;

class p extends Animation {
    final /* synthetic */ SwipeRefreshLayoutBottom a;

    p(SwipeRefreshLayoutBottom swipeRefreshLayoutBottom) {
        this.a = swipeRefreshLayoutBottom;
    }

    public void applyTransformation(float f, Transformation transformation) {
        SwipeRefreshLayoutBottom.a(this.a, 1.0f - f);
    }
}
