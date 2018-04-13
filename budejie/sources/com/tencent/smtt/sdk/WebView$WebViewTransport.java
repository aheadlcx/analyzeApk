package com.tencent.smtt.sdk;

public class WebView$WebViewTransport {
    final /* synthetic */ WebView a;
    private WebView b;

    public WebView$WebViewTransport(WebView webView) {
        this.a = webView;
    }

    public synchronized WebView getWebView() {
        return this.b;
    }

    public synchronized void setWebView(WebView webView) {
        this.b = webView;
    }
}
