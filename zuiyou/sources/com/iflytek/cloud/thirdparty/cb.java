package com.iflytek.cloud.thirdparty;

import android.util.Log;
import com.iflytek.msc.MSC;

public class cb {
    private static String a = "MscSpeechLog";
    private static a b = a.normal;
    private static boolean c = true;
    private static boolean d = false;

    public enum a {
        all,
        detail,
        normal,
        low,
        none
    }

    public static void a(a aVar) {
        b = aVar;
        c();
    }

    public static void a(String str) {
        a(a, str);
    }

    public static void a(String str, String str2) {
        if (e()) {
            Log.d(str, str2);
        }
    }

    public static void a(Throwable th) {
        if (d() && th != null) {
            th.printStackTrace();
        }
    }

    public static void a(boolean z) {
        c = z;
        c();
    }

    public static boolean a() {
        return c;
    }

    public static a b() {
        return b;
    }

    public static void b(String str) {
        b(a, str);
    }

    public static void b(String str, String str2) {
        if (f()) {
            Log.i(str, str2);
        }
    }

    public static void b(Throwable th) {
        if (g()) {
            th.printStackTrace();
        }
    }

    public static void c() {
        try {
            if (MSC.isLoaded()) {
                boolean z = a() && e();
                MSC.DebugLog(z);
                MSC.SetLogLevel(b.ordinal());
            }
        } catch (Throwable th) {
            a("updateJniLogStatus exception: " + th.getLocalizedMessage());
        }
    }

    public static void c(String str) {
        c(a, str);
    }

    public static void c(String str, String str2) {
        if (d()) {
            Log.e(str, str2);
        }
    }

    public static void d(String str) {
        d(a, str);
    }

    public static void d(String str, String str2) {
        if (g()) {
            Log.d(str, str2);
        }
    }

    private static boolean d() {
        return a() && a.none != b();
    }

    public static void e(String str) {
        e(a, str);
    }

    public static void e(String str, String str2) {
        if (d()) {
            Log.w(str, str2);
        }
    }

    private static boolean e() {
        return a() && b().ordinal() <= a.normal.ordinal();
    }

    private static boolean f() {
        return a() && b().ordinal() <= a.detail.ordinal();
    }

    private static boolean g() {
        return d && a();
    }
}
