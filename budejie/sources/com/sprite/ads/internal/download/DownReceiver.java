package com.sprite.ads.internal.download;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.sprite.ads.internal.log.ADLog;

public class DownReceiver extends BroadcastReceiver {
    private static int a = 8;

    private void a(Intent intent) {
        String dataString = intent.getDataString();
        if (dataString != null && dataString.length() > a) {
            String substring = dataString.substring(a);
            c.a();
            if (c.b.containsKey(substring)) {
                c.a();
                DownTask downTask = (DownTask) c.b.get(substring);
                if (downTask != null) {
                    downTask.b();
                    c.a();
                    c.b.remove(substring);
                }
            }
        }
    }

    private void b(Intent intent) {
        intent.getIntExtra("downId", -1);
        String stringExtra = intent.getStringExtra("downloadUrl");
        ADLog.d("stop download :" + stringExtra);
        c.a();
        DownTask downTask = (DownTask) c.a.get(stringExtra);
        if (downTask != null && downTask.isAlive()) {
            downTask.a();
        }
    }

    public void onReceive(Context context, Intent intent) {
        ADLog.d("onReceive:" + intent.getAction());
        String action = intent.getAction();
        if ("ad.stop.down".equals(action)) {
            b(intent);
        } else if ("android.intent.action.PACKAGE_ADDED".equals(action)) {
            a(intent);
        }
    }
}
