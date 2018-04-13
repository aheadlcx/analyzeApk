package com.budejie.www.activity.phonenumber;

import android.content.Context;
import android.telephony.TelephonyManager;
import com.umeng.analytics.pro.x;

public class i {
    private static final String[] a = new String[]{x.g, "data1", "photo_id", "contact_id"};

    public static String a(Context context) {
        return ((TelephonyManager) context.getSystemService("phone")).getLine1Number();
    }
}
