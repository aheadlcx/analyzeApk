package com.ishumei.f;

import android.util.Log;

public class c {
    private static boolean a = false;
    private static int b = 5;

    private static String a(String str, Object... objArr) {
        for (int i = 0; i < objArr.length; i++) {
            if (objArr[i] instanceof String[]) {
                objArr[i] = a((String[]) objArr[i]);
            }
        }
        return "[" + Thread.currentThread().getId() + "] " + String.format(str, objArr);
    }

    private static String a(String[] strArr) {
        if (strArr.length == 0) {
            return "[]";
        }
        StringBuilder stringBuilder = new StringBuilder("[");
        int length = strArr.length - 1;
        for (int i = 0; i < length; i++) {
            stringBuilder.append(strArr[i]);
            stringBuilder.append(", ");
        }
        stringBuilder.append(strArr[length]);
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    public static void a(String str, String str2) {
        if (a && b <= 3) {
            Log.d(str, str2);
        }
    }

    public static void a(String str, String str2, Object... objArr) {
        if (a && b <= 6) {
            Log.e(str, a(str2, objArr));
        }
    }

    public static void a(Throwable th) {
        if (a) {
            th.printStackTrace();
        }
    }

    public static void b(String str, String str2) {
        if (a && b <= 4) {
            Log.i(str, str2);
        }
    }

    public static void c(String str, String str2) {
        if (a && b <= 5) {
            Log.w(str, str2);
        }
    }

    public static void d(String str, String str2) {
        if (a && b <= 6) {
            Log.e(str, str2);
        }
    }
}
