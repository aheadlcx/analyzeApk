package com.handmark.pulltorefresh.library;

import android.webkit.WebChromeClient;
import android.webkit.WebView;

final class h extends WebChromeClient {
    final /* synthetic */ PullToRefreshWebView a;

    h(PullToRefreshWebView pullToRefreshWebView) {
        this.a = pullToRefreshWebView;
    }

    public final void onProgressChanged(WebView webView, int i) {
        if (i == 100) {
            this.a.onRefreshComplete();
        }
    }
}
