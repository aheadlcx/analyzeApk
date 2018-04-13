package com.marshalchen.ultimaterecyclerview.layoutmanagers;

import android.content.Context;
import android.graphics.PointF;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSmoothScroller;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Recycler;
import android.support.v7.widget.RecyclerView.SmoothScroller;
import android.support.v7.widget.RecyclerView.State;
import android.view.View;

public class ScrollSmoothLineaerLayoutManager extends LinearLayoutManager {
    private final int a;

    private class a extends LinearSmoothScroller {
        final /* synthetic */ ScrollSmoothLineaerLayoutManager a;
        private final float b;
        private final float c;

        public a(ScrollSmoothLineaerLayoutManager scrollSmoothLineaerLayoutManager, Context context, int i, int i2) {
            this.a = scrollSmoothLineaerLayoutManager;
            super(context);
            this.b = (float) i;
            this.c = i < 10000 ? (float) ((int) (calculateSpeedPerPixel(context.getResources().getDisplayMetrics()) * ((float) Math.abs(i)))) : (float) i2;
        }

        public PointF computeScrollVectorForPosition(int i) {
            return this.a.computeScrollVectorForPosition(i);
        }

        protected int calculateTimeForScrolling(int i) {
            return (int) ((((float) i) / this.b) * this.c);
        }
    }

    public ScrollSmoothLineaerLayoutManager(Context context, int i, boolean z, int i2) {
        super(context, i, z);
        this.a = i2;
    }

    public void onLayoutChildren(Recycler recycler, State state) {
        try {
            super.onLayoutChildren(recycler, state);
        } catch (Exception e) {
        }
    }

    public boolean supportsPredictiveItemAnimations() {
        return false;
    }

    public void smoothScrollToPosition(RecyclerView recyclerView, State state, int i) {
        View childAt = recyclerView.getChildAt(0);
        int abs = Math.abs(childAt.getHeight() * (recyclerView.getChildLayoutPosition(childAt) - i));
        if (abs == 0) {
            abs = (int) Math.abs(childAt.getY());
        }
        SmoothScroller aVar = new a(this, recyclerView.getContext(), abs, this.a);
        aVar.setTargetPosition(i);
        startSmoothScroll(aVar);
    }
}
