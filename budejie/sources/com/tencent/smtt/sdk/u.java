package com.tencent.smtt.sdk;

import android.webkit.ValueCallback;

class u implements ValueCallback<String[]> {
    final /* synthetic */ ValueCallback a;
    final /* synthetic */ SystemWebChromeClient b;

    u(SystemWebChromeClient systemWebChromeClient, ValueCallback valueCallback) {
        this.b = systemWebChromeClient;
        this.a = valueCallback;
    }

    public void a(String[] strArr) {
        this.a.onReceiveValue(strArr);
    }

    public /* synthetic */ void onReceiveValue(Object obj) {
        a((String[]) obj);
    }
}
