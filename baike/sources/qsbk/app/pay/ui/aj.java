package qsbk.app.pay.ui;

import qsbk.app.core.widget.refresh.SwipeRefreshLayoutBoth$OnRefreshListener;
import qsbk.app.core.widget.refresh.SwipeRefreshLayoutBoth$SwipeRefreshLayoutDirection;

class aj implements SwipeRefreshLayoutBoth$OnRefreshListener {
    final /* synthetic */ WithdrawRecordFragment a;

    aj(WithdrawRecordFragment withdrawRecordFragment) {
        this.a = withdrawRecordFragment;
    }

    public void onRefresh(SwipeRefreshLayoutBoth$SwipeRefreshLayoutDirection swipeRefreshLayoutBoth$SwipeRefreshLayoutDirection) {
        if (swipeRefreshLayoutBoth$SwipeRefreshLayoutDirection == SwipeRefreshLayoutBoth$SwipeRefreshLayoutDirection.TOP) {
            this.a.h = 1;
            this.a.b();
        } else if (swipeRefreshLayoutBoth$SwipeRefreshLayoutDirection == SwipeRefreshLayoutBoth$SwipeRefreshLayoutDirection.BOTTOM && !this.a.i) {
            this.a.c();
        }
    }
}
