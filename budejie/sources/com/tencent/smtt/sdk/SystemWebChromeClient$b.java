package com.tencent.smtt.sdk;

import android.webkit.WebChromeClient;
import com.tencent.smtt.export.external.interfaces.IX5WebChromeClient.CustomViewCallback;

class SystemWebChromeClient$b implements CustomViewCallback {
    WebChromeClient.CustomViewCallback a;
    final /* synthetic */ SystemWebChromeClient b;

    SystemWebChromeClient$b(SystemWebChromeClient systemWebChromeClient, WebChromeClient.CustomViewCallback customViewCallback) {
        this.b = systemWebChromeClient;
        this.a = customViewCallback;
    }

    public void onCustomViewHidden() {
        this.a.onCustomViewHidden();
    }
}
