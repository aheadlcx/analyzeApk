package com.zhihu.matisse.internal.ui.widget;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ItemDecoration;
import android.support.v7.widget.RecyclerView.State;
import android.view.View;

public class MediaGridInset extends ItemDecoration {
    private boolean mIncludeEdge;
    private int mSpacing;
    private int mSpanCount;

    public MediaGridInset(int i, int i2, boolean z) {
        this.mSpanCount = i;
        this.mSpacing = i2;
        this.mIncludeEdge = z;
    }

    public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, State state) {
        int childAdapterPosition = recyclerView.getChildAdapterPosition(view);
        int i = childAdapterPosition % this.mSpanCount;
        if (this.mIncludeEdge) {
            rect.left = this.mSpacing - ((this.mSpacing * i) / this.mSpanCount);
            rect.right = ((i + 1) * this.mSpacing) / this.mSpanCount;
            if (childAdapterPosition < this.mSpanCount) {
                rect.top = this.mSpacing;
            }
            rect.bottom = this.mSpacing;
            return;
        }
        rect.left = (this.mSpacing * i) / this.mSpanCount;
        rect.right = this.mSpacing - (((i + 1) * this.mSpacing) / this.mSpanCount);
        if (childAdapterPosition >= this.mSpanCount) {
            rect.top = this.mSpacing;
        }
    }
}
