package com.tencent.smtt.sdk;

import android.webkit.WebView.FindListener;
import com.tencent.smtt.export.external.interfaces.IX5WebViewBase$FindListener;

class bd implements FindListener {
    final /* synthetic */ IX5WebViewBase$FindListener a;
    final /* synthetic */ WebView b;

    bd(WebView webView, IX5WebViewBase$FindListener iX5WebViewBase$FindListener) {
        this.b = webView;
        this.a = iX5WebViewBase$FindListener;
    }

    public void onFindResultReceived(int i, int i2, boolean z) {
        this.a.onFindResultReceived(i, i2, z);
    }
}
