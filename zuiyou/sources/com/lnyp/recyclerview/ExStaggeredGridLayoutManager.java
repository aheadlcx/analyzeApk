package com.lnyp.recyclerview;

import android.support.v7.widget.GridLayoutManager.SpanSizeLookup;
import android.support.v7.widget.RecyclerView.Recycler;
import android.support.v7.widget.RecyclerView.State;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.StaggeredGridLayoutManager.LayoutParams;
import android.view.View;

public class ExStaggeredGridLayoutManager extends StaggeredGridLayoutManager {
    SpanSizeLookup a;
    private final String b = getClass().getSimpleName();

    public ExStaggeredGridLayoutManager(int i, int i2) {
        super(i, i2);
    }

    public void a(SpanSizeLookup spanSizeLookup) {
        this.a = spanSizeLookup;
    }

    public void onMeasure(Recycler recycler, State state, int i, int i2) {
        for (int i3 = 0; i3 < getItemCount(); i3++) {
            if (this.a.getSpanSize(i3) > 1) {
                try {
                    View viewForPosition = recycler.getViewForPosition(i3);
                    if (viewForPosition != null) {
                        ((LayoutParams) viewForPosition.getLayoutParams()).setFullSpan(true);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        super.onMeasure(recycler, state, i, i2);
    }
}
