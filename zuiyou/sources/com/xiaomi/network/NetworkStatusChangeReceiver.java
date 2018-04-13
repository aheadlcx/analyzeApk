package com.xiaomi.network;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.xiaomi.channel.commonutils.logger.b;
import com.xiaomi.channel.commonutils.network.d;

public class NetworkStatusChangeReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        if (d.e(context)) {
            try {
                context.startService(new Intent(context, HostRefreshService.class));
            } catch (Throwable e) {
                b.a(e);
            }
        }
    }
}
