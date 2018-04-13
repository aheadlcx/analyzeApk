package qsbk.app.live.ui.family;

import qsbk.app.core.widget.refresh.SwipeRefreshLayoutBoth$OnRefreshListener;
import qsbk.app.core.widget.refresh.SwipeRefreshLayoutBoth$SwipeRefreshLayoutDirection;

class av implements SwipeRefreshLayoutBoth$OnRefreshListener {
    final /* synthetic */ FamilyMessageActivity a;

    av(FamilyMessageActivity familyMessageActivity) {
        this.a = familyMessageActivity;
    }

    public void onRefresh(SwipeRefreshLayoutBoth$SwipeRefreshLayoutDirection swipeRefreshLayoutBoth$SwipeRefreshLayoutDirection) {
        if (swipeRefreshLayoutBoth$SwipeRefreshLayoutDirection == SwipeRefreshLayoutBoth$SwipeRefreshLayoutDirection.TOP) {
            this.a.h = 1;
            this.a.i = 0;
            this.a.c();
        } else if (swipeRefreshLayoutBoth$SwipeRefreshLayoutDirection == SwipeRefreshLayoutBoth$SwipeRefreshLayoutDirection.BOTTOM && !this.a.e) {
            this.a.a();
        }
    }
}
