package com.umeng.update.util;

import android.content.Context;
import java.io.File;
import u.upd.m;

public class DeltaUpdate {
    private static boolean a = false;
    private static final String b = "bspatch";

    public static native int bspatch(String str, String str2, String str3);

    public static boolean a() {
        return a;
    }

    public static int a(String str, String str2, String str3) {
        return bspatch(str, str2, str3);
    }

    public static String a(Context context) {
        return context.getApplicationInfo().sourceDir;
    }

    public static String b(Context context) {
        String a = a(context);
        if (new File(a).exists()) {
            return m.a(new File(a));
        }
        return "";
    }

    static {
        try {
            System.loadLibrary(b);
            a = true;
        } catch (UnsatisfiedLinkError e) {
            a = false;
        }
    }
}
