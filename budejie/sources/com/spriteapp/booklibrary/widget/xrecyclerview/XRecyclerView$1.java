package com.spriteapp.booklibrary.widget.xrecyclerview;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.GridLayoutManager.SpanSizeLookup;

class XRecyclerView$1 extends SpanSizeLookup {
    final /* synthetic */ XRecyclerView this$0;
    final /* synthetic */ GridLayoutManager val$gridManager;

    XRecyclerView$1(XRecyclerView xRecyclerView, GridLayoutManager gridLayoutManager) {
        this.this$0 = xRecyclerView;
        this.val$gridManager = gridLayoutManager;
    }

    public int getSpanSize(int i) {
        return (XRecyclerView.access$100(this.this$0).isHeader(i) || XRecyclerView.access$100(this.this$0).isFooter(i) || XRecyclerView.access$100(this.this$0).isRefreshHeader(i)) ? this.val$gridManager.getSpanCount() : 1;
    }
}
