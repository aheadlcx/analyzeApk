package qsbk.app.core.widget.refresh;

import android.view.animation.Animation;
import android.view.animation.Transformation;

class h extends Animation {
    final /* synthetic */ int a;
    final /* synthetic */ int b;
    final /* synthetic */ SwipeRefreshLayoutBoth c;

    h(SwipeRefreshLayoutBoth swipeRefreshLayoutBoth, int i, int i2) {
        this.c = swipeRefreshLayoutBoth;
        this.a = i;
        this.b = i2;
    }

    public void applyTransformation(float f, Transformation transformation) {
        SwipeRefreshLayoutBoth.b(this.c).setAlpha((int) (((float) this.a) + (((float) (this.b - this.a)) * f)));
    }
}
