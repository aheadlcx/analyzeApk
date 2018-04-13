package com.tencent.smtt.sdk;

import com.tencent.smtt.export.external.interfaces.JsPromptResult;

class SystemWebChromeClient$d implements JsPromptResult {
    android.webkit.JsPromptResult a;
    final /* synthetic */ SystemWebChromeClient b;

    SystemWebChromeClient$d(SystemWebChromeClient systemWebChromeClient, android.webkit.JsPromptResult jsPromptResult) {
        this.b = systemWebChromeClient;
        this.a = jsPromptResult;
    }

    public void cancel() {
        this.a.cancel();
    }

    public void confirm() {
        this.a.confirm();
    }

    public void confirm(String str) {
        this.a.confirm(str);
    }
}
