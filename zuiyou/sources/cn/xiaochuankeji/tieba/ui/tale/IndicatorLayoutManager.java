package cn.xiaochuankeji.tieba.ui.tale;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSmoothScroller;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.SmoothScroller;
import android.support.v7.widget.RecyclerView.State;
import android.util.AttributeSet;
import android.util.DisplayMetrics;

public class IndicatorLayoutManager extends LinearLayoutManager {
    public IndicatorLayoutManager(Context context) {
        super(context);
    }

    public IndicatorLayoutManager(Context context, int i, boolean z) {
        super(context, i, z);
    }

    public IndicatorLayoutManager(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
    }

    public void smoothScrollToPosition(RecyclerView recyclerView, State state, int i) {
        SmoothScroller anonymousClass1 = new LinearSmoothScroller(this, recyclerView.getContext()) {
            final /* synthetic */ IndicatorLayoutManager a;

            protected float calculateSpeedPerPixel(DisplayMetrics displayMetrics) {
                return (float) (1280 / displayMetrics.densityDpi);
            }
        };
        anonymousClass1.setTargetPosition(i);
        startSmoothScroll(anonymousClass1);
    }
}
