package com.sprite.ads;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.sprite.ads.internal.log.ADLog;
import com.sprite.ads.internal.net.a;

public class SpriteAdReceiver extends BroadcastReceiver {
    private static int a = 8;

    private String a(Intent intent) {
        String dataString = intent.getDataString();
        return (dataString == null || dataString.length() <= a) ? "" : dataString.substring(a);
    }

    public void onReceive(Context context, Intent intent) {
        ADLog.d("sprite ad receiver onReceive");
        if ("android.intent.action.PACKAGE_REPLACED".equals(intent.getAction())) {
            try {
                if (context.getPackageName().equals(a(intent))) {
                    a.c();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
