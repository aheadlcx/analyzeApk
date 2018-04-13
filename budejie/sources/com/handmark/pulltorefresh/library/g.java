package com.handmark.pulltorefresh.library;

import android.webkit.WebView;

final class g implements PullToRefreshBase$OnRefreshListener<WebView> {
    g() {
    }

    public final void onRefresh(PullToRefreshBase<WebView> pullToRefreshBase) {
        ((WebView) pullToRefreshBase.getRefreshableView()).reload();
    }
}
