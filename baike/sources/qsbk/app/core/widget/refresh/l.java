package qsbk.app.core.widget.refresh;

import android.view.animation.Animation;
import android.view.animation.Transformation;

class l extends Animation {
    final /* synthetic */ SwipeRefreshLayoutBoth a;

    l(SwipeRefreshLayoutBoth swipeRefreshLayoutBoth) {
        this.a = swipeRefreshLayoutBoth;
    }

    public void applyTransformation(float f, Transformation transformation) {
        SwipeRefreshLayoutBoth.a(this.a, SwipeRefreshLayoutBoth.k(this.a) + ((-SwipeRefreshLayoutBoth.k(this.a)) * f));
        SwipeRefreshLayoutBoth.b(this.a, f);
    }
}
