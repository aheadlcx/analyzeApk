package com.handmark.pulltorefresh.library;

final class c implements Runnable {
    final /* synthetic */ PullToRefreshBase a;

    c(PullToRefreshBase pullToRefreshBase) {
        this.a = pullToRefreshBase;
    }

    public final void run() {
        this.a.requestLayout();
    }
}
