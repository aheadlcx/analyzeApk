package com.marshalchen.ultimaterecyclerview.grid;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ItemDecoration;
import android.support.v7.widget.RecyclerView.State;
import android.view.View;

public class a extends ItemDecoration {
    private int a;
    private int b;
    private boolean c;

    public a(int i, int i2, boolean z) {
        this.a = i;
        this.b = i2;
        this.c = z;
    }

    public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, State state) {
        int childAdapterPosition = recyclerView.getChildAdapterPosition(view);
        int i = childAdapterPosition % this.a;
        if (this.c) {
            rect.left = this.b - ((this.b * i) / this.a);
            rect.right = ((i + 1) * this.b) / this.a;
            if (childAdapterPosition < this.a) {
                rect.top = this.b;
            }
            rect.bottom = this.b;
            return;
        }
        rect.left = (this.b * i) / this.a;
        rect.right = this.b - (((i + 1) * this.b) / this.a);
        if (childAdapterPosition >= this.a) {
            rect.top = this.b;
        }
    }
}
