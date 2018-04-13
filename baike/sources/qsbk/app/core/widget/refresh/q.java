package qsbk.app.core.widget.refresh;

import android.view.animation.Animation;
import android.view.animation.Transformation;

class q extends Animation {
    final /* synthetic */ int a;
    final /* synthetic */ int b;
    final /* synthetic */ SwipeRefreshLayoutBottom c;

    q(SwipeRefreshLayoutBottom swipeRefreshLayoutBottom, int i, int i2) {
        this.c = swipeRefreshLayoutBottom;
        this.a = i;
        this.b = i2;
    }

    public void applyTransformation(float f, Transformation transformation) {
        SwipeRefreshLayoutBottom.b(this.c).setAlpha((int) (((float) this.a) + (((float) (this.b - this.a)) * f)));
    }
}
