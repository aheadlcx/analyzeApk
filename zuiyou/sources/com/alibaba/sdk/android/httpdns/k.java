package com.alibaba.sdk.android.httpdns;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import java.util.ArrayList;

final class k extends BroadcastReceiver {
    k() {
    }

    public void onReceive(Context context, Intent intent) {
        try {
            if (!isInitialStickyBroadcast() && intent.getAction().equals("android.net.conn.CONNECTIVITY_CHANGE")) {
                String d = j.c();
                if (!(d == "None_Network" || d.equalsIgnoreCase(j.e))) {
                    f.d("[BroadcastReceiver.onReceive] - Network state changed");
                    ArrayList a = a.a().a();
                    a.a().clear();
                    if (j.b && HttpDns.instance != null) {
                        f.d("[BroadcastReceiver.onReceive] - refresh host");
                        HttpDns.instance.setPreResolveHosts(a);
                    }
                }
                j.e = d;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
