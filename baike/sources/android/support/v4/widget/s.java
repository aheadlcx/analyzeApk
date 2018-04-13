package android.support.v4.widget;

import android.view.animation.Animation;
import android.view.animation.Transformation;

class s extends Animation {
    final /* synthetic */ SwipeRefreshLayout a;

    s(SwipeRefreshLayout swipeRefreshLayout) {
        this.a = swipeRefreshLayout;
    }

    public void applyTransformation(float f, Transformation transformation) {
        int i;
        if (this.a.l) {
            i = this.a.i;
        } else {
            i = this.a.i - Math.abs(this.a.h);
        }
        this.a.setTargetOffsetTopAndBottom((((int) (((float) (i - this.a.f)) * f)) + this.a.f) - this.a.e.getTop());
        this.a.j.setArrowScale(1.0f - f);
    }
}
