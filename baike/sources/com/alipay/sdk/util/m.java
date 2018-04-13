package com.alipay.sdk.util;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.webkit.DownloadListener;
import org.eclipse.paho.client.mqttv3.internal.ClientDefaults;

final class m implements DownloadListener {
    final /* synthetic */ Context a;

    m(Context context) {
        this.a = context;
    }

    public final void onDownloadStart(String str, String str2, String str3, String str4, long j) {
        try {
            Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(str));
            intent.setFlags(ClientDefaults.MAX_MSG_SIZE);
            this.a.startActivity(intent);
        } catch (Throwable th) {
        }
    }
}
