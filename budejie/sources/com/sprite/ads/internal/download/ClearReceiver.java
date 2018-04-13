package com.sprite.ads.internal.download;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.sprite.ads.internal.log.ADLog;
import com.sprite.ads.internal.net.a;

public class ClearReceiver extends BroadcastReceiver {
    private static int a = 8;

    private String a(Intent intent) {
        String dataString = intent.getDataString();
        return (dataString == null || dataString.length() <= a) ? "" : dataString.substring(a);
    }

    private void b(Intent intent) {
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

    public void onReceive(Context context, Intent intent) {
        ADLog.d("onReceive:" + intent.getAction());
        String action = intent.getAction();
        if ("android.intent.action.PACKAGE_ADDED".equals(action)) {
            b(intent);
        } else if ("android.intent.action.PACKAGE_REPLACED".equals(action)) {
            try {
                if (context.getPackageName().equals(a(intent))) {
                    ADLog.d("clearCache:" + context.getPackageName());
                    a.c();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
