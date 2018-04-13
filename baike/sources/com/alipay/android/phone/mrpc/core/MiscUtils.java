package com.alipay.android.phone.mrpc.core;

import android.content.Context;

public final class MiscUtils {
    public static final String RC_PACKAGE_NAME = "com.eg.android.AlipayGphoneRC";
    private static Boolean a = null;

    public static final boolean isDebugger(Context context) {
        if (a != null) {
            return a.booleanValue();
        }
        try {
            Boolean valueOf = Boolean.valueOf((context.getPackageManager().getApplicationInfo(context.getPackageName(), 0).flags & 2) != 0);
            a = valueOf;
            return valueOf.booleanValue();
        } catch (Exception e) {
            return false;
        }
    }
}
