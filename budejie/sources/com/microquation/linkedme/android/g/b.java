package com.microquation.linkedme.android.g;

import android.app.AppOpsManager;
import android.content.Context;

class b {
    public static int a(Context context, String str, String str2) {
        return ((AppOpsManager) context.getSystemService(AppOpsManager.class)).noteProxyOp(str, str2);
    }

    public static String a(String str) {
        return AppOpsManager.permissionToOp(str);
    }
}
