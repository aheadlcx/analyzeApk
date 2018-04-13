package com.b.a.a.a;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Build.VERSION;
import android.telephony.TelephonyManager;

public final class a {
    public static String a(Context context) {
        StringBuffer stringBuffer = new StringBuffer("1.0");
        String a = com.b.a.b.a.a.a(context);
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
        if (telephonyManager == null) {
            com.b.a.b.a.a.h();
            return null;
        }
        String b;
        Configuration configuration = context.getResources().getConfiguration();
        String str = "";
        if (!(configuration == null || configuration.locale == null)) {
            str = configuration.locale.toString();
        }
        String str2 = "";
        try {
            b = com.b.a.b.a.a.b(telephonyManager.getDeviceId());
        } catch (SecurityException e) {
            e.printStackTrace();
            b = str2;
        }
        str2 = com.b.a.b.a.a.e(context);
        if (com.b.a.b.a.a.f(context)) {
            stringBuffer.append(",").append("Android" + VERSION.RELEASE).append(",").append(str).append(",").append(Build.MODEL).append(",").append(Build.DISPLAY).append(",").append(str2).append(",").append(b).append(",").append(a).append(",").append(com.b.a.b.a.a.b(context));
            com.b.a.b.a.a.h();
        } else {
            stringBuffer.append(",,,,,").append(str2).append(",").append(b).append(",").append(a).append(",");
            com.b.a.b.a.a.h();
        }
        return stringBuffer.toString();
    }
}
