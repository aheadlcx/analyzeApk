package qsbk.app.core.widget;

import qsbk.app.core.widget.refresh.SwipeRefreshLayoutBoth$OnRefreshListener;
import qsbk.app.core.widget.refresh.SwipeRefreshLayoutBoth$SwipeRefreshLayoutDirection;

class t implements SwipeRefreshLayoutBoth$OnRefreshListener {
    final /* synthetic */ RefreshRecyclerView a;

    t(RefreshRecyclerView refreshRecyclerView) {
        this.a = refreshRecyclerView;
    }

    public void onRefresh(SwipeRefreshLayoutBoth$SwipeRefreshLayoutDirection swipeRefreshLayoutBoth$SwipeRefreshLayoutDirection) {
        if (swipeRefreshLayoutBoth$SwipeRefreshLayoutDirection == SwipeRefreshLayoutBoth$SwipeRefreshLayoutDirection.TOP) {
            this.a.c();
            this.a.d();
        } else if (swipeRefreshLayoutBoth$SwipeRefreshLayoutDirection == SwipeRefreshLayoutBoth$SwipeRefreshLayoutDirection.BOTTOM && !this.a.d) {
            this.a.a();
        }
    }
}
