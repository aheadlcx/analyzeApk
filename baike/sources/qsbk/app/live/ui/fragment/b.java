package qsbk.app.live.ui.fragment;

import qsbk.app.core.widget.refresh.SwipeRefreshLayoutBoth$OnRefreshListener;
import qsbk.app.core.widget.refresh.SwipeRefreshLayoutBoth$SwipeRefreshLayoutDirection;

class b implements SwipeRefreshLayoutBoth$OnRefreshListener {
    final /* synthetic */ GiftRankFragment a;

    b(GiftRankFragment giftRankFragment) {
        this.a = giftRankFragment;
    }

    public void onRefresh(SwipeRefreshLayoutBoth$SwipeRefreshLayoutDirection swipeRefreshLayoutBoth$SwipeRefreshLayoutDirection) {
        if (swipeRefreshLayoutBoth$SwipeRefreshLayoutDirection == SwipeRefreshLayoutBoth$SwipeRefreshLayoutDirection.TOP) {
            this.a.f = 1;
            this.a.b();
            this.a.f();
        } else if (swipeRefreshLayoutBoth$SwipeRefreshLayoutDirection == SwipeRefreshLayoutBoth$SwipeRefreshLayoutDirection.BOTTOM && !this.a.g) {
            this.a.c();
        }
    }
}
