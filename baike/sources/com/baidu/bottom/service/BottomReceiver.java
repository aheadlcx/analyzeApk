package com.baidu.bottom.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import com.baidu.mobstat.at;
import com.baidu.mobstat.bd;
import com.baidu.mobstat.dd;
import com.baidu.mobstat.n;
import com.sina.weibo.sdk.statistic.i;

public class BottomReceiver extends BroadcastReceiver {
    private static dd a;
    private static long b;
    private static long c;

    public void onReceive(Context context, Intent intent) {
        if (a != null) {
            bd.a("Bottom has alread analyzed.");
            return;
        }
        dd ddVar = new dd();
        if (ddVar.a()) {
            a = ddVar;
            new at(this, context, intent, ddVar).start();
        }
    }

    private void a(Context context, Intent intent) {
        String action = intent.getAction();
        long currentTimeMillis = System.currentTimeMillis();
        if (Math.abs(c - currentTimeMillis) <= i.MIN_UPLOAD_INTERVAL) {
            return;
        }
        if ("android.net.wifi.STATE_CHANGE".equals(action) || "android.net.wifi.WIFI_STATE_CHANGED".equals(action) || "android.net.conn.CONNECTIVITY_CHANGE".equals(action) || "android.net.wifi.SCAN_RESULTS".equals(action)) {
            c = currentTimeMillis;
            n.a(context);
        }
    }

    private void b(Context context, Intent intent) {
        String action = intent.getAction();
        if ("android.intent.action.PACKAGE_ADDED".equals(action) || "android.intent.action.PACKAGE_REMOVED".equals(action) || "android.intent.action.PACKAGE_REPLACED".equals(action)) {
            Object obj = null;
            Uri data = intent.getData();
            if (data != null) {
                obj = data.getSchemeSpecificPart();
            }
            if (!TextUtils.isEmpty(obj)) {
                n.a(context, action, obj);
            }
        }
    }
}
