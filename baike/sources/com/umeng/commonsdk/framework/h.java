package com.umeng.commonsdk.framework;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import com.umeng.commonsdk.proguard.b;
import com.umeng.commonsdk.statistics.common.e;

final class h extends BroadcastReceiver {
    h() {
    }

    public void onReceive(Context context, Intent intent) {
        new Thread(new i(this, context)).start();
        if (intent.getAction().equals("android.net.conn.CONNECTIVITY_CHANGE")) {
            Context a = c.a();
            g.e = (ConnectivityManager) a.getSystemService("connectivity");
            try {
                g.f = g.e.getActiveNetworkInfo();
                if (g.f == null || !g.f.isAvailable()) {
                    e.c("--->>> network disconnected.");
                    g.j = false;
                    return;
                }
                e.c("--->>> network isAvailable, check if there are any files to send.");
                g.j = true;
                g.c(273);
                if (g.f.getType() == 1) {
                    e.c("--->>> wifi connection available, send uop packet now.");
                    new Thread(new j(this, context)).start();
                }
            } catch (Throwable th) {
                b.a(a, th);
            }
        }
    }
}
