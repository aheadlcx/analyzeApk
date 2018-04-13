package qsbk.app.core.widget.refresh;

import android.view.animation.Animation;
import android.view.animation.Transformation;

class s extends Animation {
    final /* synthetic */ SwipeRefreshLayoutBottom a;

    s(SwipeRefreshLayoutBottom swipeRefreshLayoutBottom) {
        this.a = swipeRefreshLayoutBottom;
    }

    public void applyTransformation(float f, Transformation transformation) {
        int i;
        if (SwipeRefreshLayoutBottom.h(this.a)) {
            i = (int) SwipeRefreshLayoutBottom.i(this.a);
        } else {
            i = this.a.getMeasuredHeight() - ((int) SwipeRefreshLayoutBottom.i(this.a));
        }
        SwipeRefreshLayoutBottom.a(this.a, (((int) (((float) (i - this.a.a)) * f)) + this.a.a) - SwipeRefreshLayoutBottom.e(this.a).getTop(), false);
    }
}
