package com.sprite.ads.web;

import android.content.Intent;
import android.net.Uri;
import android.webkit.DownloadListener;

class SpriteWebView$3 implements DownloadListener {
    final /* synthetic */ SpriteWebView a;

    SpriteWebView$3(SpriteWebView spriteWebView) {
        this.a = spriteWebView;
    }

    public void onDownloadStart(String str, String str2, String str3, String str4, long j) {
        this.a.getContext().startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
    }
}
