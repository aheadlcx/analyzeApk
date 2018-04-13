package android.support.v7.widget;

import android.content.Context;
import android.support.v7.widget.RecyclerView.SmoothScroller.Action;
import android.support.v7.widget.RecyclerView.State;
import android.util.DisplayMetrics;
import android.view.View;

class bi extends LinearSmoothScroller {
    final /* synthetic */ PagerSnapHelper f;

    bi(PagerSnapHelper pagerSnapHelper, Context context) {
        this.f = pagerSnapHelper;
        super(context);
    }

    protected void a(View view, State state, Action action) {
        int[] calculateDistanceToFinalSnap = this.f.calculateDistanceToFinalSnap(this.f.a.getLayoutManager(), view);
        int i = calculateDistanceToFinalSnap[0];
        int i2 = calculateDistanceToFinalSnap[1];
        int a = a(Math.max(Math.abs(i), Math.abs(i2)));
        if (a > 0) {
            action.update(i, i2, a, this.b);
        }
    }

    protected float a(DisplayMetrics displayMetrics) {
        return 100.0f / ((float) displayMetrics.densityDpi);
    }

    protected int b(int i) {
        return Math.min(100, super.b(i));
    }
}
