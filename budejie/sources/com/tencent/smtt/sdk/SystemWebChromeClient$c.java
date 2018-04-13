package com.tencent.smtt.sdk;

import android.webkit.GeolocationPermissions.Callback;
import com.tencent.smtt.export.external.interfaces.GeolocationPermissionsCallback;

class SystemWebChromeClient$c implements GeolocationPermissionsCallback {
    Callback a;
    final /* synthetic */ SystemWebChromeClient b;

    SystemWebChromeClient$c(SystemWebChromeClient systemWebChromeClient, Callback callback) {
        this.b = systemWebChromeClient;
        this.a = callback;
    }

    public void invoke(String str, boolean z, boolean z2) {
        this.a.invoke(str, z, z2);
    }
}
