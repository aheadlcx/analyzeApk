package com.tencent.smtt.sdk;

import android.webkit.WebStorage;
import com.tencent.smtt.sdk.WebStorage.QuotaUpdater;

class SystemWebChromeClient$f implements QuotaUpdater {
    WebStorage.QuotaUpdater a;
    final /* synthetic */ SystemWebChromeClient b;

    SystemWebChromeClient$f(SystemWebChromeClient systemWebChromeClient, WebStorage.QuotaUpdater quotaUpdater) {
        this.b = systemWebChromeClient;
        this.a = quotaUpdater;
    }

    public void updateQuota(long j) {
        this.a.updateQuota(j);
    }
}
