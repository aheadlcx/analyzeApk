package com.lnyp.recyclerview;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

public class EndlessRecyclerOnScrollListener extends OnScrollListener {
    private int[] a;
    protected LayoutManagerType b;
    private int c;
    private int d = 0;

    public enum LayoutManagerType {
        LinearLayout,
        StaggeredGridLayout,
        GridLayout
    }

    public void onScrolled(RecyclerView recyclerView, int i, int i2) {
        super.onScrolled(recyclerView, i, i2);
        LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (this.b == null) {
            if (layoutManager instanceof LinearLayoutManager) {
                this.b = LayoutManagerType.LinearLayout;
            } else if (layoutManager instanceof GridLayoutManager) {
                this.b = LayoutManagerType.GridLayout;
            } else if (layoutManager instanceof StaggeredGridLayoutManager) {
                this.b = LayoutManagerType.StaggeredGridLayout;
            } else {
                throw new RuntimeException("Unsupported LayoutManager used. Valid ones are LinearLayoutManager, GridLayoutManager and StaggeredGridLayoutManager");
            }
        }
        switch (this.b) {
            case LinearLayout:
                this.c = ((LinearLayoutManager) layoutManager).findLastVisibleItemPosition();
                return;
            case GridLayout:
                this.c = ((GridLayoutManager) layoutManager).findLastVisibleItemPosition();
                return;
            case StaggeredGridLayout:
                StaggeredGridLayoutManager staggeredGridLayoutManager = (StaggeredGridLayoutManager) layoutManager;
                if (this.a == null) {
                    this.a = new int[staggeredGridLayoutManager.getSpanCount()];
                }
                staggeredGridLayoutManager.findLastVisibleItemPositions(this.a);
                this.c = a(this.a);
                return;
            default:
                return;
        }
    }

    public void onScrollStateChanged(RecyclerView recyclerView, int i) {
        super.onScrollStateChanged(recyclerView, i);
        this.d = i;
        LayoutManager layoutManager = recyclerView.getLayoutManager();
        int childCount = layoutManager.getChildCount();
        int itemCount = layoutManager.getItemCount();
        if (childCount > 0 && this.d == 0 && this.c >= itemCount - 1) {
            a((View) recyclerView);
        }
    }

    private int a(int[] iArr) {
        int i = iArr[0];
        int length = iArr.length;
        int i2 = 0;
        while (i2 < length) {
            int i3 = iArr[i2];
            if (i3 <= i) {
                i3 = i;
            }
            i2++;
            i = i3;
        }
        return i;
    }

    public void a(View view) {
    }
}
