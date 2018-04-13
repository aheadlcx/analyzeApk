package com.tencent.smtt.export.external.interfaces;

public class IX5WebViewBase$WebViewTransport {
    private IX5WebViewBase mWebview;

    public synchronized IX5WebViewBase getWebView() {
        return this.mWebview;
    }

    public synchronized void setWebView(IX5WebViewBase iX5WebViewBase) {
        this.mWebview = iX5WebViewBase;
    }
}
