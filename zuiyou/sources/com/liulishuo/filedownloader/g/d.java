package com.liulishuo.filedownloader.g;

import android.util.Log;

public class d {
    public static boolean a = false;

    public static void a(Object obj, Throwable th, String str, Object... objArr) {
        a(6, obj, th, str, objArr);
    }

    public static void a(Object obj, String str, Object... objArr) {
        a(6, obj, str, objArr);
    }

    public static void b(Object obj, String str, Object... objArr) {
        a(4, obj, str, objArr);
    }

    public static void c(Object obj, String str, Object... objArr) {
        a(3, obj, str, objArr);
    }

    public static void d(Object obj, String str, Object... objArr) {
        a(5, obj, str, objArr);
    }

    public static void e(Object obj, String str, Object... objArr) {
        a(2, obj, str, objArr);
    }

    private static void a(int i, Object obj, String str, Object... objArr) {
        a(i, obj, null, str, objArr);
    }

    private static void a(int i, Object obj, Throwable th, String str, Object... objArr) {
        if ((i >= 5 ? 1 : null) != null || a) {
            Log.println(i, a(obj), f.a(str, objArr));
            if (th != null) {
                th.printStackTrace();
            }
        }
    }

    private static String a(Object obj) {
        return "FileDownloader." + (obj instanceof Class ? ((Class) obj).getSimpleName() : obj.getClass().getSimpleName());
    }
}
