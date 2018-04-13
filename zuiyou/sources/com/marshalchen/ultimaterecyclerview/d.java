package com.marshalchen.ultimaterecyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.util.SparseArray;
import android.view.View;
import com.marshalchen.ultimaterecyclerview.b.c;
import com.marshalchen.ultimaterecyclerview.swipe.SwipeLayout;
import com.marshalchen.ultimaterecyclerview.swipe.SwipeLayout$b;
import com.marshalchen.ultimaterecyclerview.swipe.SwipeLayout$f;

public class d<T> extends ViewHolder {
    private SparseArray<SparseArray<View>> a = new SparseArray();
    private View b;
    public SwipeLayout k = null;
    public SwipeLayout$b l = null;
    public SwipeLayout$f m = null;
    public int n = -1;

    public d(View view) {
        super(view);
        this.k = (SwipeLayout) view.findViewById(c.recyclerview_swipe);
        this.b = view;
    }

    public View d() {
        return this.b;
    }

    public Context e() {
        return this.b.getContext();
    }
}
