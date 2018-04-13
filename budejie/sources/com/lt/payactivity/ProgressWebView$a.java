package com.lt.payactivity;

import android.webkit.WebChromeClient;
import android.webkit.WebView;

public class ProgressWebView$a extends WebChromeClient {
    final /* synthetic */ ProgressWebView a;

    public ProgressWebView$a(ProgressWebView progressWebView) {
        this.a = progressWebView;
    }

    public void onProgressChanged(WebView webView, int i) {
        if (i == 100) {
            ProgressWebView.a(this.a).setVisibility(8);
        } else {
            if (ProgressWebView.a(this.a).getVisibility() == 8) {
                ProgressWebView.a(this.a).setVisibility(0);
            }
            ProgressWebView.a(this.a).setProgress(i);
        }
        super.onProgressChanged(webView, i);
    }
}
