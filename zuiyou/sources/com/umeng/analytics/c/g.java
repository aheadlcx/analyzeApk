package com.umeng.analytics.c;

import android.content.Context;
import android.telephony.TelephonyManager;
import com.umeng.a.d;

public class g extends a {
    private static final String a = "imei";
    private Context b;

    public g(Context context) {
        super(a);
        this.b = context;
    }

    public String f() {
        TelephonyManager telephonyManager = (TelephonyManager) this.b.getSystemService("phone");
        if (telephonyManager == null) {
        }
        try {
            if (d.a(this.b, "android.permission.READ_PHONE_STATE")) {
                return telephonyManager.getDeviceId();
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }
}
