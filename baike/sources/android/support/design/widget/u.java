package android.support.design.widget;

import android.support.v4.math.MathUtils;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper.Callback;
import android.view.View;

class u extends Callback {
    final /* synthetic */ BottomSheetBehavior a;

    u(BottomSheetBehavior bottomSheetBehavior) {
        this.a = bottomSheetBehavior;
    }

    public boolean tryCaptureView(View view, int i) {
        if (this.a.d == 1 || this.a.j) {
            return false;
        }
        if (this.a.d == 3 && this.a.i == i) {
            View view2 = (View) this.a.h.get();
            if (view2 != null && view2.canScrollVertically(-1)) {
                return false;
            }
        }
        boolean z = this.a.g != null && this.a.g.get() == view;
        return z;
    }

    public void onViewPositionChanged(View view, int i, int i2, int i3, int i4) {
        this.a.b(i2);
    }

    public void onViewDragStateChanged(int i) {
        if (i == 1) {
            this.a.a(1);
        }
    }

    public void onViewReleased(View view, float f, float f2) {
        int i;
        int i2 = 3;
        if (f2 < 0.0f) {
            i = this.a.a;
        } else if (this.a.c && this.a.a(view, f2)) {
            i = this.a.f;
            i2 = 5;
        } else if (f2 == 0.0f) {
            int top = view.getTop();
            if (Math.abs(top - this.a.a) < Math.abs(top - this.a.b)) {
                i = this.a.a;
            } else {
                i = this.a.b;
                i2 = 4;
            }
        } else {
            i = this.a.b;
            i2 = 4;
        }
        if (this.a.e.settleCapturedViewAt(view.getLeft(), i)) {
            this.a.a(2);
            ViewCompat.postOnAnimation(view, new a(this.a, view, i2));
            return;
        }
        this.a.a(i2);
    }

    public int clampViewPositionVertical(View view, int i, int i2) {
        return MathUtils.clamp(i, this.a.a, this.a.c ? this.a.f : this.a.b);
    }

    public int clampViewPositionHorizontal(View view, int i, int i2) {
        return view.getLeft();
    }

    public int getViewVerticalDragRange(View view) {
        if (this.a.c) {
            return this.a.f - this.a.a;
        }
        return this.a.b - this.a.a;
    }
}
