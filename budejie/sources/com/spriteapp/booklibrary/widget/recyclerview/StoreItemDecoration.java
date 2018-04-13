package com.spriteapp.booklibrary.widget.recyclerview;

import android.graphics.Rect;
import android.support.v7.widget.GridLayoutManager.LayoutParams;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ItemDecoration;
import android.support.v7.widget.RecyclerView.State;
import android.view.View;
import com.spriteapp.booklibrary.util.ScreenUtil;

public class StoreItemDecoration extends ItemDecoration {
    private int bottomSpace;
    private int space;

    public StoreItemDecoration(int i) {
        this.space = i;
    }

    public StoreItemDecoration(int i, int i2) {
        this.space = i;
        this.bottomSpace = i2;
    }

    public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, State state) {
        if (((LayoutParams) view.getLayoutParams()).getSpanIndex() == 0) {
            rect.left = ScreenUtil.dpToPxInt(15.0f);
        } else {
            rect.left = this.space;
        }
        rect.bottom = this.bottomSpace;
    }
}
