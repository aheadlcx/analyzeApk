package android.support.v4.widget;

import android.view.animation.Animation;
import android.view.animation.Transformation;

class p extends Animation {
    final /* synthetic */ SwipeRefreshLayout a;

    p(SwipeRefreshLayout swipeRefreshLayout) {
        this.a = swipeRefreshLayout;
    }

    public void applyTransformation(float f, Transformation transformation) {
        this.a.setAnimationProgress(1.0f - f);
    }
}
