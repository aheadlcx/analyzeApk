package com.flyco.dialog.b;

import android.content.Context;
import android.os.Build;
import android.os.Build.VERSION;
import android.text.TextUtils;

public class b {
    public static int a(Context context) {
        int i = 0;
        int identifier = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (identifier > 0) {
            i = context.getResources().getDimensionPixelSize(identifier);
        }
        if (a()) {
            return i * 2;
        }
        return i;
    }

    public static boolean a() {
        if (!"4.4.4".equals(VERSION.RELEASE)) {
            return false;
        }
        Object obj = VERSION.INCREMENTAL;
        String str = Build.DISPLAY;
        if (TextUtils.isEmpty(obj)) {
            return str.contains("Flyme OS 4");
        }
        return obj.contains("Flyme_OS_4");
    }
}
