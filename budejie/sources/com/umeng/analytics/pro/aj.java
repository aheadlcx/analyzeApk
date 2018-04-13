package com.umeng.analytics.pro;

import android.os.Build;
import android.os.Build.VERSION;

public class aj extends y {
    private static final String a = "serial";

    public aj() {
        super(a);
    }

    public String f() {
        if (VERSION.SDK_INT >= 9) {
            return Build.SERIAL;
        }
        return null;
    }
}
