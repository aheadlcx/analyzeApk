package qsbk.app.core.widget.refresh;

import android.view.animation.Animation;
import android.view.animation.Transformation;

class t extends Animation {
    final /* synthetic */ SwipeRefreshLayoutBottom a;

    t(SwipeRefreshLayoutBottom swipeRefreshLayoutBottom) {
        this.a = swipeRefreshLayoutBottom;
    }

    public void applyTransformation(float f, Transformation transformation) {
        SwipeRefreshLayoutBottom.b(this.a, f);
    }
}
