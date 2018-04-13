package com.alipay.b.a.a.d;

import android.os.Environment;
import com.alipay.b.a.a.a.b;
import java.io.File;

public final class c {
    public static String a(String str) {
        try {
            if (a()) {
                String absolutePath = Environment.getExternalStorageDirectory().getAbsolutePath();
                if (new File(absolutePath, str).exists()) {
                    return b.a(absolutePath, str);
                }
            }
        } catch (Exception e) {
        }
        return null;
    }

    public static boolean a() {
        String externalStorageState = Environment.getExternalStorageState();
        return externalStorageState != null && externalStorageState.length() > 0 && ((externalStorageState.equals("mounted") || externalStorageState.equals("mounted_ro")) && Environment.getExternalStorageDirectory() != null);
    }
}
