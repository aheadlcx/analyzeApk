package com.baidu.mobstat;

import android.os.Build.VERSION;

public final class bb {
    public static boolean a = true;
    public static final String b = (VERSION.SDK_INT < 9 ? "http://datax.baidu.com/xs.gif" : "https://datax.baidu.com/xs.gif");
    public static final String c;

    static {
        String str;
        if (VERSION.SDK_INT < 9) {
            str = "http://dxp.baidu.com/upgrade";
        } else {
            str = "https://dxp.baidu.com/upgrade";
        }
        c = str;
    }
}
