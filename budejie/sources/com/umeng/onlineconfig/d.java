package com.umeng.onlineconfig;

import android.content.Context;
import android.content.SharedPreferences;

public class d {
    private static d a = null;
    private static Context b = null;
    private static String c = null;
    private static final String d = "onlineconfig_agent_online_setting_";

    public d(Context context) {
        b = context.getApplicationContext();
        c = context.getPackageName();
    }

    public static synchronized d a(Context context) {
        d dVar;
        synchronized (d.class) {
            if (a == null) {
                a = new d(context);
            }
            dVar = a;
        }
        return dVar;
    }

    public SharedPreferences a() {
        return b.getSharedPreferences(d + c, 0);
    }
}
