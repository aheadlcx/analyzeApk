package qsbk.app.core.widget.refresh;

import android.view.animation.Animation;
import android.view.animation.Transformation;

class u extends Animation {
    final /* synthetic */ SwipeRefreshLayoutBottom a;

    u(SwipeRefreshLayoutBottom swipeRefreshLayoutBottom) {
        this.a = swipeRefreshLayoutBottom;
    }

    public void applyTransformation(float f, Transformation transformation) {
        SwipeRefreshLayoutBottom.a(this.a, SwipeRefreshLayoutBottom.j(this.a) + ((-SwipeRefreshLayoutBottom.j(this.a)) * f));
        SwipeRefreshLayoutBottom.b(this.a, f);
    }
}
