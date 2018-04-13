package com.spriteapp.booklibrary.widget.recyclerview;

import android.graphics.Rect;
import android.support.v7.widget.GridLayoutManager.LayoutParams;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ItemDecoration;
import android.support.v7.widget.RecyclerView.State;
import android.view.View;

public class StoreShelfItemDecoration extends ItemDecoration {
    private static final String TAG = "StoreShelfItemDecoratio";
    private int bottomSpace;
    private int space;

    public StoreShelfItemDecoration(int i) {
        this.space = i;
    }

    public StoreShelfItemDecoration(int i, int i2) {
        this.space = i;
        this.bottomSpace = i2;
    }

    public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, State state) {
        int spanIndex = ((LayoutParams) view.getLayoutParams()).getSpanIndex();
        if (spanIndex == 1) {
            rect.left = (int) (((float) this.space) * 0.45f);
        } else if (spanIndex == 2) {
            rect.left = this.space / 2;
        }
        rect.bottom = this.bottomSpace;
    }
}
