package qsbk.app.live.ui.family;

import qsbk.app.core.widget.refresh.SwipeRefreshLayoutBoth$OnRefreshListener;
import qsbk.app.core.widget.refresh.SwipeRefreshLayoutBoth$SwipeRefreshLayoutDirection;

class ap implements SwipeRefreshLayoutBoth$OnRefreshListener {
    final /* synthetic */ FamilyMemberActivity a;

    ap(FamilyMemberActivity familyMemberActivity) {
        this.a = familyMemberActivity;
    }

    public void onRefresh(SwipeRefreshLayoutBoth$SwipeRefreshLayoutDirection swipeRefreshLayoutBoth$SwipeRefreshLayoutDirection) {
        if (swipeRefreshLayoutBoth$SwipeRefreshLayoutDirection == SwipeRefreshLayoutBoth$SwipeRefreshLayoutDirection.TOP) {
            this.a.g();
        } else if (!this.a.h) {
            this.a.f();
        }
    }
}
