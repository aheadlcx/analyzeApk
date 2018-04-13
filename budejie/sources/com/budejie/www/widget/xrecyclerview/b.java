package com.budejie.www.widget.xrecyclerview;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ItemDecoration;
import android.support.v7.widget.RecyclerView.State;
import android.support.v7.widget.StaggeredGridLayoutManager.LayoutParams;
import android.util.Log;
import android.view.View;

public class b extends ItemDecoration {
    private int a;
    private int b;
    private int c;

    public b(int i, int i2) {
        this.a = i;
        this.b = i2;
    }

    public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, State state) {
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        if (recyclerView instanceof XRecyclerView) {
            this.c = ((XRecyclerView) recyclerView).getHeaderViewCount();
            Log.d("SpaceItemDecoration", "getItemOffsets: --->" + this.c);
        }
        int spanIndex = layoutParams.getSpanIndex();
        int childAdapterPosition = recyclerView.getChildAdapterPosition(view);
        if (spanIndex == 0) {
            rect.left = childAdapterPosition < this.c ? 0 : this.a;
        } else {
            rect.left = this.a;
            rect.right = this.a;
        }
        if (this.c < 1) {
            rect.bottom = this.b == 0 ? this.a : this.b;
        } else if (childAdapterPosition < this.c - 1) {
            rect.bottom = 0;
        } else if (childAdapterPosition == this.c - 1) {
            rect.bottom = this.a;
        } else {
            rect.bottom = this.b == 0 ? this.a : this.b;
        }
    }
}
