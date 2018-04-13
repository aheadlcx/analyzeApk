package com.budejie.www.widget.xrecyclerview;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.GridLayoutManager.SpanSizeLookup;

class XRecyclerView$1 extends SpanSizeLookup {
    final /* synthetic */ GridLayoutManager a;
    final /* synthetic */ XRecyclerView b;

    XRecyclerView$1(XRecyclerView xRecyclerView, GridLayoutManager gridLayoutManager) {
        this.b = xRecyclerView;
        this.a = gridLayoutManager;
    }

    public int getSpanSize(int i) {
        return (XRecyclerView.a(this.b).a(i) || XRecyclerView.a(this.b).b(i) || XRecyclerView.a(this.b).c(i)) ? this.a.getSpanCount() : 1;
    }
}
