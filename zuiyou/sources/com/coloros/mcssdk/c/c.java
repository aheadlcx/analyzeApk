package com.coloros.mcssdk.c;

import android.util.Log;

public final class c {
    private static String a = "MCS";
    private static boolean b = false;
    private static boolean c = false;
    private static boolean d = true;
    private static boolean e = true;
    private static boolean f = true;
    private static String g = "-->";
    private static boolean h = true;

    public static void a(String str) {
        if (d && h) {
            Log.d("com.coloros.mcssdk---", a + g + str);
        }
    }

    public static void b(String str) {
        if (f && h) {
            Log.e("com.coloros.mcssdk---", a + g + str);
        }
    }
}
