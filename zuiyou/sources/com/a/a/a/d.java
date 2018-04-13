package com.a.a.a;

import android.graphics.Bitmap;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class d extends WebViewClient {
    protected c b;

    public d(c cVar) {
        this.b = cVar;
    }

    public final boolean shouldOverrideUrlLoading(WebView webView, String str) {
        try {
            str = URLDecoder.decode(str, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if (str.startsWith("zuiyou://return/")) {
            this.b.a(str);
            return true;
        } else if (str.startsWith("zuiyou://")) {
            this.b.a();
            return true;
        } else {
            try {
                return a(webView, str);
            } catch (Exception e2) {
                return false;
            }
        }
    }

    public final WebResourceResponse shouldInterceptRequest(WebView webView, WebResourceRequest webResourceRequest) {
        return super.shouldInterceptRequest(webView, webResourceRequest);
    }

    public final WebResourceResponse shouldInterceptRequest(WebView webView, String str) {
        return super.shouldInterceptRequest(webView, str);
    }

    protected boolean a(WebView webView, String str) throws Exception {
        return false;
    }

    public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
        super.onPageStarted(webView, str, bitmap);
    }

    public void onPageFinished(WebView webView, String str) {
        super.onPageFinished(webView, str);
        b.a(webView, "ZuiyouJSBridge.min.js");
        if (this.b.getStartupMessage() != null) {
            for (g a : this.b.getStartupMessage()) {
                this.b.a(a);
            }
            this.b.setStartupMessage(null);
        }
    }

    public void onReceivedError(WebView webView, int i, String str, String str2) {
        super.onReceivedError(webView, i, str, str2);
    }
}
