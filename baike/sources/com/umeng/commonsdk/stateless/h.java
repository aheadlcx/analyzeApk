package com.umeng.commonsdk.stateless;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.umeng.commonsdk.proguard.b;
import com.umeng.commonsdk.statistics.common.e;

final class h extends BroadcastReceiver {
    h() {
    }

    public void onReceive(Context context, Intent intent) {
        if (context != null && intent != null) {
            try {
                if (intent.getAction() != null && intent.getAction().equals("android.net.conn.CONNECTIVITY_CHANGE")) {
                    d.b = context.getApplicationContext();
                    if (d.b != null) {
                        ConnectivityManager connectivityManager = (ConnectivityManager) d.b.getSystemService("connectivity");
                        if (connectivityManager != null) {
                            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
                            if (activeNetworkInfo == null || !activeNetworkInfo.isAvailable()) {
                                e.a("walle", "[stateless] net reveiver disconnected --->>>");
                                d.g = false;
                                return;
                            }
                            d.g = true;
                            e.a("walle", "[stateless] net reveiver ok --->>>");
                            d.b(273);
                        }
                    }
                }
            } catch (Throwable th) {
                b.a(context, th);
            }
        }
    }
}
