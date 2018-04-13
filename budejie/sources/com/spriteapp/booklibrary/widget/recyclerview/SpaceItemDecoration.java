package com.spriteapp.booklibrary.widget.recyclerview;

import android.graphics.Rect;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ItemDecoration;
import android.support.v7.widget.RecyclerView.State;
import android.view.View;

public class SpaceItemDecoration extends ItemDecoration {
    private static final String TAG = "SpaceItemDecoration";
    private int bottomSpace;
    private int space;

    public SpaceItemDecoration(int i) {
        this.space = i;
    }

    public SpaceItemDecoration(int i, int i2) {
        this.space = i;
        this.bottomSpace = i2;
    }

    public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, State state) {
        int spanCount = ((GridLayoutManager) recyclerView.getLayoutManager()).getSpanCount();
        rect.left = this.space;
        int childAdapterPosition = recyclerView.getChildAdapterPosition(view);
        if (childAdapterPosition < spanCount) {
            rect.top = this.bottomSpace;
        } else {
            rect.top = 0;
        }
        int itemCount = recyclerView.getAdapter().getItemCount();
        if (itemCount > spanCount) {
            spanCount = itemCount % spanCount;
            switch (spanCount) {
                case 0:
                    if (childAdapterPosition >= itemCount - 1) {
                        rect.bottom = this.bottomSpace;
                        return;
                    }
                    return;
                case 1:
                case 2:
                    if (childAdapterPosition >= itemCount - spanCount) {
                        rect.bottom = this.bottomSpace;
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    }
}
