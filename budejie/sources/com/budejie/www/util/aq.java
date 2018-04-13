package com.budejie.www.util;

import android.app.Activity;
import android.os.Build.VERSION;
import android.view.Window;

public class aq {
    public static void a(Activity activity, int i) {
        try {
            if (VERSION.SDK_INT >= 21) {
                Window window = activity.getWindow();
                window.addFlags(Integer.MIN_VALUE);
                window.setStatusBarColor(activity.getResources().getColor(i));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
