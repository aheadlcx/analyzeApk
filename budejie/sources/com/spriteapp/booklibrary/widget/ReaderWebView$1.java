package com.spriteapp.booklibrary.widget;

import android.graphics.Bitmap;
import android.net.http.SslError;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.spriteapp.booklibrary.util.WebViewUtil;

class ReaderWebView$1 extends WebViewClient {
    final /* synthetic */ ReaderWebView this$0;
    final /* synthetic */ WebViewClient val$client;

    ReaderWebView$1(ReaderWebView readerWebView, WebViewClient webViewClient) {
        this.this$0 = readerWebView;
        this.val$client = webViewClient;
    }

    public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
        sslErrorHandler.proceed();
    }

    public boolean shouldOverrideUrlLoading(WebView webView, String str) {
        if (this.val$client != null) {
            this.val$client.shouldOverrideUrlLoading(webView, str);
        }
        return WebViewUtil.getInstance().getJumpUrl(this.this$0.getContext(), str);
    }

    public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
        if (this.val$client != null) {
            this.val$client.onPageStarted(webView, str, bitmap);
        }
    }

    public void onPageFinished(WebView webView, String str) {
        if (this.val$client != null) {
            this.val$client.onPageFinished(webView, str);
        }
    }
}
