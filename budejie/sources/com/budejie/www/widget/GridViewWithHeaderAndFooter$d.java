package com.budejie.www.widget;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;

class GridViewWithHeaderAndFooter$d implements OnItemClickListener, OnItemLongClickListener {
    final /* synthetic */ GridViewWithHeaderAndFooter a;

    private GridViewWithHeaderAndFooter$d(GridViewWithHeaderAndFooter gridViewWithHeaderAndFooter) {
        this.a = gridViewWithHeaderAndFooter;
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        if (GridViewWithHeaderAndFooter.a(this.a) != null) {
            int headerViewCount = i - (this.a.getHeaderViewCount() * GridViewWithHeaderAndFooter.b(this.a));
            if (headerViewCount >= 0) {
                GridViewWithHeaderAndFooter.a(this.a).onItemClick(adapterView, view, headerViewCount, j);
            }
        }
    }

    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long j) {
        if (GridViewWithHeaderAndFooter.c(this.a) != null) {
            int headerViewCount = i - (this.a.getHeaderViewCount() * GridViewWithHeaderAndFooter.b(this.a));
            if (headerViewCount >= 0) {
                GridViewWithHeaderAndFooter.c(this.a).onItemLongClick(adapterView, view, headerViewCount, j);
            }
        }
        return true;
    }
}
