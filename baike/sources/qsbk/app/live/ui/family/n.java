package qsbk.app.live.ui.family;

import qsbk.app.core.widget.refresh.SwipeRefreshLayoutBoth$OnRefreshListener;
import qsbk.app.core.widget.refresh.SwipeRefreshLayoutBoth$SwipeRefreshLayoutDirection;

class n implements SwipeRefreshLayoutBoth$OnRefreshListener {
    final /* synthetic */ FamilyDetailActivity a;

    n(FamilyDetailActivity familyDetailActivity) {
        this.a = familyDetailActivity;
    }

    public void onRefresh(SwipeRefreshLayoutBoth$SwipeRefreshLayoutDirection swipeRefreshLayoutBoth$SwipeRefreshLayoutDirection) {
        if (swipeRefreshLayoutBoth$SwipeRefreshLayoutDirection == SwipeRefreshLayoutBoth$SwipeRefreshLayoutDirection.TOP) {
            this.a.a();
        } else {
            this.a.Y.setRefreshing(false);
        }
    }
}
