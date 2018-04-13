package com.spriteapp.booklibrary.util;

import android.app.Activity;
import android.os.Build.VERSION;
import android.view.Window;

public class StatusBarUtil {
    public static void setWindowStatusBarColor(Activity activity, int i) {
        if (VERSION.SDK_INT >= 21) {
            Window window = activity.getWindow();
            window.addFlags(Integer.MIN_VALUE);
            window.setStatusBarColor(i);
        }
    }
}
