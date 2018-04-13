package com.umeng.analytics.c;

import android.os.Build;
import android.os.Build.VERSION;

public class l extends a {
    private static final String a = "serial";

    public l() {
        super(a);
    }

    public String f() {
        if (VERSION.SDK_INT >= 9) {
            return Build.SERIAL;
        }
        return null;
    }
}
