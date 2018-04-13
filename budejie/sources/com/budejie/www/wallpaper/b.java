package com.budejie.www.wallpaper;

import android.graphics.Rect;
import android.support.v7.widget.GridLayoutManager.LayoutParams;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.State;
import android.view.View;
import com.budejie.www.widget.xrecyclerview.a;

public class b extends a {
    public b(int i, int i2) {
        super(i, i2);
    }

    public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, State state) {
        if (view.getLayoutParams() instanceof LayoutParams) {
            int spanIndex = ((LayoutParams) view.getLayoutParams()).getSpanIndex();
            if (spanIndex == 1) {
                rect.left = this.a / 2;
            } else if (spanIndex == 2) {
                rect.left = this.a;
            }
            rect.bottom = this.b;
        }
    }
}
