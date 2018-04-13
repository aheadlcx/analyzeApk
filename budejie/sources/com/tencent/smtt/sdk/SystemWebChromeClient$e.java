package com.tencent.smtt.sdk;

import com.tencent.smtt.export.external.interfaces.JsResult;

class SystemWebChromeClient$e implements JsResult {
    android.webkit.JsResult a;
    final /* synthetic */ SystemWebChromeClient b;

    SystemWebChromeClient$e(SystemWebChromeClient systemWebChromeClient, android.webkit.JsResult jsResult) {
        this.b = systemWebChromeClient;
        this.a = jsResult;
    }

    public void cancel() {
        this.a.cancel();
    }

    public void confirm() {
        this.a.confirm();
    }
}
