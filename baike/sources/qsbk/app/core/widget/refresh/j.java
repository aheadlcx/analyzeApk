package qsbk.app.core.widget.refresh;

import android.view.animation.Animation;
import android.view.animation.Transformation;

class j extends Animation {
    final /* synthetic */ SwipeRefreshLayoutBoth a;

    j(SwipeRefreshLayoutBoth swipeRefreshLayoutBoth) {
        this.a = swipeRefreshLayoutBoth;
    }

    public void applyTransformation(float f, Transformation transformation) {
        int measuredHeight;
        if (!SwipeRefreshLayoutBoth.i(this.a)) {
            switch (m.a[SwipeRefreshLayoutBoth.e(this.a).ordinal()]) {
                case 1:
                    measuredHeight = this.a.getMeasuredHeight() - ((int) SwipeRefreshLayoutBoth.j(this.a));
                    break;
                default:
                    measuredHeight = (int) (SwipeRefreshLayoutBoth.j(this.a) - ((float) Math.abs(this.a.b)));
                    break;
            }
        }
        measuredHeight = (int) SwipeRefreshLayoutBoth.j(this.a);
        SwipeRefreshLayoutBoth.a(this.a, (((int) (((float) (measuredHeight - this.a.a)) * f)) + this.a.a) - SwipeRefreshLayoutBoth.f(this.a).getTop(), false);
    }
}
