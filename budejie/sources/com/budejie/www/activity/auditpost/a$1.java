package com.budejie.www.activity.auditpost;

import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

class a$1 extends WebViewClient {
    final /* synthetic */ a b;

    a$1(a aVar) {
        this.b = aVar;
    }

    public boolean shouldOverrideUrlLoading(WebView webView, String str) {
        webView.loadUrl(str);
        return true;
    }
}
