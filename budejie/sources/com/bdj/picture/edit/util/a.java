package com.bdj.picture.edit.util;

import android.app.Activity;
import android.os.Environment;

public class a {
    public static boolean a() {
        if (Environment.getExternalStorageState().equals("mounted")) {
            return true;
        }
        return false;
    }

    public static int a(Activity activity) {
        return activity.getWindowManager().getDefaultDisplay().getWidth();
    }

    public static int b(Activity activity) {
        return activity.getWindowManager().getDefaultDisplay().getHeight();
    }
}
