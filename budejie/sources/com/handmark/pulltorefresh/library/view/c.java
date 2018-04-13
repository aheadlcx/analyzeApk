package com.handmark.pulltorefresh.library.view;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnScrollListener;

final class c extends OnScrollListener {
    final /* synthetic */ SixRoomPullToRefreshRecyclerView a;

    c(SixRoomPullToRefreshRecyclerView sixRoomPullToRefreshRecyclerView) {
        this.a = sixRoomPullToRefreshRecyclerView;
    }

    public final void onScrollStateChanged(RecyclerView recyclerView, int i) {
        super.onScrollStateChanged(recyclerView, i);
        if (i == 0 && this.a.e + 1 == this.a.d.getItemCount() && this.a.d.isScrollEnabled() && this.a.g.a() == 1) {
            SixRoomPullToRefreshRecyclerView.b(this.a);
        }
    }

    public final void onScrolled(RecyclerView recyclerView, int i, int i2) {
        super.onScrolled(recyclerView, i, i2);
        if (this.a.c != null) {
            this.a.e = this.a.c.findLastVisibleItemPosition();
        }
    }
}
