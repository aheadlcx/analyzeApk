package cn.v6.sixrooms.ui.phone;

import android.graphics.Bitmap;
import android.webkit.WebView;

final class ac extends a {
    final /* synthetic */ EventActivity a;

    ac(EventActivity eventActivity) {
        this.a = eventActivity;
        super();
    }

    public final void onPageFinished(WebView webView, String str) {
        super.onPageFinished(webView, str);
        this.a.c.setVisibility(8);
    }

    public final void onPageStarted(WebView webView, String str, Bitmap bitmap) {
        this.a.c.setVisibility(0);
        super.onPageStarted(webView, str, bitmap);
    }

    public final void onReceivedError(WebView webView, int i, String str, String str2) {
        this.a.d.setVisibility(0);
        super.onReceivedError(webView, i, str, str2);
    }
}
