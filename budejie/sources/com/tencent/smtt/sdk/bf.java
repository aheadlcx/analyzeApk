package com.tencent.smtt.sdk;

import android.graphics.Picture;
import android.webkit.WebView;
import android.webkit.WebView.PictureListener;

class bf implements PictureListener {
    final /* synthetic */ WebView$PictureListener a;
    final /* synthetic */ WebView b;

    bf(WebView webView, WebView$PictureListener webView$PictureListener) {
        this.b = webView;
        this.a = webView$PictureListener;
    }

    public void onNewPicture(WebView webView, Picture picture) {
        this.b.a(webView);
        this.a.onNewPicture(this.b, picture);
    }
}
