package android.support.v4.widget;

import android.view.animation.Animation;
import android.view.animation.Transformation;

class o extends Animation {
    final /* synthetic */ SwipeRefreshLayout a;

    o(SwipeRefreshLayout swipeRefreshLayout) {
        this.a = swipeRefreshLayout;
    }

    public void applyTransformation(float f, Transformation transformation) {
        this.a.setAnimationProgress(f);
    }
}
