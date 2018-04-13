package com.sina.weibo.sdk.web.a;

import android.net.http.SslError;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public abstract class b extends WebViewClient {
    protected com.sina.weibo.sdk.web.b.b a;
    protected com.sina.weibo.sdk.web.b b;

    public b(com.sina.weibo.sdk.web.b bVar, com.sina.weibo.sdk.web.b.b bVar2) {
        this.b = bVar;
        this.a = bVar2;
    }

    public boolean shouldOverrideUrlLoading(WebView webView, String str) {
        if (this.b != null) {
            this.b.a(webView, str);
        }
        return super.shouldOverrideUrlLoading(webView, str);
    }

    public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
        super.onReceivedSslError(webView, sslErrorHandler, sslError);
    }

    public void a() {
    }

    public boolean b() {
        return false;
    }
}
