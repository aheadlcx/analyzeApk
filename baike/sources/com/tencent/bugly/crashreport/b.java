package com.tencent.bugly.crashreport;

import android.webkit.WebSettings;
import android.webkit.WebView;
import com.tencent.bugly.crashreport.CrashReport.WebViewInterface;
import com.tencent.bugly.crashreport.crash.h5.H5JavaScriptInterface;

final class b implements WebViewInterface {
    private /* synthetic */ WebView a;

    b(WebView webView) {
        this.a = webView;
    }

    public final String getUrl() {
        return this.a.getUrl();
    }

    public final void setJavaScriptEnabled(boolean z) {
        WebSettings settings = this.a.getSettings();
        if (!settings.getJavaScriptEnabled()) {
            settings.setJavaScriptEnabled(true);
        }
    }

    public final void loadUrl(String str) {
        this.a.loadUrl(str);
    }

    public final void addJavascriptInterface(H5JavaScriptInterface h5JavaScriptInterface, String str) {
        this.a.addJavascriptInterface(h5JavaScriptInterface, str);
    }

    public final CharSequence getContentDescription() {
        return this.a.getContentDescription();
    }
}
