package com.umeng.commonsdk.internal.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.baidu.mobstat.Config;
import com.umeng.commonsdk.framework.UMWorkDispatch;
import com.umeng.commonsdk.internal.a;
import com.umeng.commonsdk.internal.b;
import com.umeng.commonsdk.statistics.common.e;
import org.json.JSONObject;

class n extends BroadcastReceiver {
    final /* synthetic */ c a;

    n(c cVar) {
        this.a = cVar;
    }

    public void onReceive(Context context, Intent intent) {
        int i = -1;
        int i2 = 1;
        if (intent.getAction().equals("android.intent.action.BATTERY_CHANGED")) {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("le", intent.getIntExtra("level", 0));
            } catch (Exception e) {
            }
            try {
                try {
                    jSONObject.put("vol", intent.getIntExtra("voltage", 0));
                } catch (Exception e2) {
                }
                try {
                    jSONObject.put("temp", intent.getIntExtra("temperature", 0));
                    jSONObject.put("ts", System.currentTimeMillis());
                } catch (Exception e3) {
                }
                switch (intent.getIntExtra("status", 0)) {
                    case 2:
                        i = 1;
                        break;
                    case 4:
                        i = 0;
                        break;
                    case 5:
                        i = 2;
                        break;
                }
                try {
                    jSONObject.put(Config.STAT_SDK_TYPE, i);
                } catch (Exception e4) {
                }
                switch (intent.getIntExtra("plugged", 0)) {
                    case 1:
                        break;
                    case 2:
                        i2 = 2;
                        break;
                    default:
                        i2 = 0;
                        break;
                }
                try {
                    jSONObject.put(Config.EXCEPTION_CRASH_TYPE, i2);
                    jSONObject.put("ts", System.currentTimeMillis());
                } catch (Exception e5) {
                }
                e.a("BatteryUtils", jSONObject.toString());
                UMWorkDispatch.sendEvent(context, a.g, b.a(c.b).a(), jSONObject.toString());
                this.a.c();
            } catch (Throwable th) {
                com.umeng.commonsdk.proguard.b.a(c.b, th);
            }
        }
    }
}
