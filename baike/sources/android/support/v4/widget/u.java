package android.support.v4.widget;

import android.view.animation.Animation;
import android.view.animation.Transformation;

class u extends Animation {
    final /* synthetic */ SwipeRefreshLayout a;

    u(SwipeRefreshLayout swipeRefreshLayout) {
        this.a = swipeRefreshLayout;
    }

    public void applyTransformation(float f, Transformation transformation) {
        this.a.setAnimationProgress(this.a.g + ((-this.a.g) * f));
        this.a.a(f);
    }
}
