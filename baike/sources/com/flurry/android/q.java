package com.flurry.android;

import android.webkit.WebView;
import android.webkit.WebViewClient;

final class q extends WebViewClient {
    private /* synthetic */ CatalogActivity a;

    q(CatalogActivity catalogActivity) {
        this.a = catalogActivity;
    }

    public final boolean shouldOverrideUrlLoading(WebView webView, String str) {
        if (str == null) {
            return false;
        }
        if (this.a.f != null) {
            this.a.f.a(new f((byte) 6, this.a.e.k()));
        }
        this.a.e.a(webView.getContext(), this.a.f, str);
        return true;
    }

    public final void onReceivedError(WebView webView, int i, String str, String str2) {
        ah.c("FlurryAgent", "Failed to load url: " + str2 + " with an errorCode of " + i);
        webView.loadData("Cannot find Android Market information. <p>Please check your network", "text/html", "UTF-8");
    }

    public final void onPageFinished(WebView webView, String str) {
        try {
            p a = this.a.f;
            f fVar = new f((byte) 5, this.a.e.k());
            long j = this.a.f.c;
            a.d.add(fVar);
            a.c = j;
        } catch (Exception e) {
        }
    }
}
