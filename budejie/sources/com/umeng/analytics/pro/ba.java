package com.umeng.analytics.pro;

import android.content.Context;
import android.content.SharedPreferences;

public class ba {
    private static final String a = "umeng_general_config";

    private ba() {
    }

    public static SharedPreferences a(Context context, String str) {
        return context.getSharedPreferences(str, 0);
    }

    public static SharedPreferences a(Context context) {
        return context.getSharedPreferences(a, 0);
    }
}
