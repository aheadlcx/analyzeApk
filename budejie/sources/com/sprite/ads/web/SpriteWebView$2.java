package com.sprite.ads.web;

import android.graphics.Bitmap;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.facebook.common.util.UriUtil;
import com.sprite.ads.internal.log.ADLog;

class SpriteWebView$2 extends WebViewClient {
    final /* synthetic */ SpriteWebView a;

    SpriteWebView$2(SpriteWebView spriteWebView) {
        this.a = spriteWebView;
    }

    public void onPageFinished(WebView webView, String str) {
        if (SpriteWebView.d(this.a) != null) {
            SpriteWebView.d(this.a).onPageFinished(webView, str);
        }
    }

    public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
    }

    public boolean shouldOverrideUrlLoading(WebView webView, String str) {
        if (str.startsWith(UriUtil.HTTP_SCHEME) || str.startsWith("https")) {
            return super.shouldOverrideUrlLoading(webView, str);
        }
        this.a.a(str);
        ADLog.d("open ACTION_VIEW url : " + str);
        return true;
    }
}
