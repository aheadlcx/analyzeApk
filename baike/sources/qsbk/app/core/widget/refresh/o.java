package qsbk.app.core.widget.refresh;

import android.view.animation.Animation;
import android.view.animation.Transformation;

class o extends Animation {
    final /* synthetic */ SwipeRefreshLayoutBottom a;

    o(SwipeRefreshLayoutBottom swipeRefreshLayoutBottom) {
        this.a = swipeRefreshLayoutBottom;
    }

    public void applyTransformation(float f, Transformation transformation) {
        SwipeRefreshLayoutBottom.a(this.a, f);
    }
}
