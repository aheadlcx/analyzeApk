package com.budejie.www.activity.posts;

import android.graphics.Bitmap;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

class c$1 extends WebViewClient {
    final /* synthetic */ c b;

    c$1(c cVar) {
        this.b = cVar;
    }

    public boolean shouldOverrideUrlLoading(WebView webView, String str) {
        webView.loadUrl(str);
        return true;
    }

    public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
        super.onPageStarted(webView, str, bitmap);
    }

    public void onPageFinished(WebView webView, String str) {
        try {
            if (!webView.canGoBack() || c.a(this.b)) {
                if (!c.c(this.b).canGoBack() && c.a(this.b)) {
                    c.b(this.b).setVisibility(8);
                    c.a(this.b, false);
                }
                super.onPageFinished(webView, str);
            }
            c.b(this.b).setVisibility(0);
            c.a(this.b, true);
            super.onPageFinished(webView, str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
