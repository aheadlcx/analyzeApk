package qsbk.app.live.debug;

import qsbk.app.core.widget.refresh.SwipeRefreshLayoutBoth$OnRefreshListener;
import qsbk.app.core.widget.refresh.SwipeRefreshLayoutBoth$SwipeRefreshLayoutDirection;

class b implements SwipeRefreshLayoutBoth$OnRefreshListener {
    final /* synthetic */ LiveDebugListFragment a;

    b(LiveDebugListFragment liveDebugListFragment) {
        this.a = liveDebugListFragment;
    }

    public void onRefresh(SwipeRefreshLayoutBoth$SwipeRefreshLayoutDirection swipeRefreshLayoutBoth$SwipeRefreshLayoutDirection) {
        if (swipeRefreshLayoutBoth$SwipeRefreshLayoutDirection == SwipeRefreshLayoutBoth$SwipeRefreshLayoutDirection.TOP) {
            this.a.d();
            this.a.g();
        } else if (swipeRefreshLayoutBoth$SwipeRefreshLayoutDirection == SwipeRefreshLayoutBoth$SwipeRefreshLayoutDirection.BOTTOM && !this.a.e) {
            this.a.h();
        }
    }
}
