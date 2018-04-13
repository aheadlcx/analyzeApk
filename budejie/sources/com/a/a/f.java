package com.a.a;

import android.webkit.WebChromeClient;
import android.webkit.WebView;

final class f extends WebChromeClient {
    final /* synthetic */ d a;

    f(d dVar) {
        this.a = dVar;
    }

    public final void onProgressChanged(WebView webView, int i) {
        super.onProgressChanged(webView, i);
        if (i == 100) {
            this.a.g.getLayoutParams().height = -2;
            this.a.g.setLayoutParams(this.a.g.getLayoutParams());
        }
    }
}
