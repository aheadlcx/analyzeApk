package com.umeng.commonsdk.internal.utils;

import android.content.Context;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.umeng.commonsdk.statistics.common.e;

public class b {
    private static boolean b = false;
    private static Context c = null;
    PhoneStateListener a;
    private TelephonyManager d;

    private static class a {
        private static final b a = new b(b.c);
    }

    private b(Context context) {
        this.a = new m(this);
        if (context != null) {
            try {
                this.d = (TelephonyManager) context.getSystemService("phone");
            } catch (Throwable th) {
            }
        }
    }

    public static b a(Context context) {
        if (c == null && context != null) {
            c = context.getApplicationContext();
        }
        return a.a;
    }

    public synchronized boolean a() {
        return b;
    }

    private String e() {
        try {
            String simOperator = ((TelephonyManager) c.getSystemService("phone")).getSimOperator();
            if (!TextUtils.isEmpty(simOperator)) {
                if (simOperator.equals("46000") || simOperator.equals("46002")) {
                    return "中国移动";
                }
                if (simOperator.equals("46001")) {
                    return "中国联通";
                }
                if (simOperator.equals("46003")) {
                    return "中国电信";
                }
            }
        } catch (Throwable th) {
            com.umeng.commonsdk.proguard.b.a(c, th);
        }
        return null;
    }

    public synchronized void b() {
        e.c("BaseStationUtils", "base station registerListener");
        try {
            if (this.d != null) {
                this.d.listen(this.a, 256);
            }
            b = true;
        } catch (Throwable th) {
            com.umeng.commonsdk.proguard.b.a(c, th);
        }
    }

    public synchronized void c() {
        e.c("BaseStationUtils", "base station unRegisterListener");
        try {
            if (this.d != null) {
                this.d.listen(this.a, 0);
            }
            b = false;
        } catch (Throwable th) {
            com.umeng.commonsdk.proguard.b.a(c, th);
        }
    }
}
