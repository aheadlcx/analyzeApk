package com.iflytek.cloud.thirdparty;

import android.util.Log;
import com.iflytek.cae.jni.CAEJni;

public class as {
    private static String a = "CAELog";
    private static boolean b = false;
    private static boolean c = true;

    public static void a(String str) {
        if (c) {
            Log.d(a, str);
        }
    }

    public static void a(boolean z) {
        c = z;
        if (CAEJni.a()) {
            CAEJni.DebugLog(z);
        }
    }

    public static boolean a() {
        return c;
    }

    public static void b(String str) {
        Log.e(a, str);
    }
}
