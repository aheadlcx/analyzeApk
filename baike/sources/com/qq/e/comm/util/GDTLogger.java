package com.qq.e.comm.util;

import android.util.Log;
import com.qq.e.comm.services.a;

public class GDTLogger {
    public static final boolean DEBUG_ENABLE = false;

    public static void d(String str) {
    }

    public static void e(String str) {
        Log.e("gdt_ad_mob", str);
    }

    public static void e(String str, Throwable th) {
        if (th == null) {
            Log.e("gdt_ad_mob", str);
        } else {
            Log.e("gdt_ad_mob", str, th);
        }
    }

    public static void i(String str) {
        Log.i("gdt_ad_mob", str);
    }

    public static void report(String str) {
        report(str, null);
    }

    public static void report(String str, Throwable th) {
        e(str, th);
        a.a();
        a.a(str, th);
    }

    public static void w(String str) {
        Log.e("gdt_ad_mob", str);
    }

    public static void w(String str, Throwable th) {
        if (th == null) {
            Log.w("gdt_ad_mob", str);
        } else {
            Log.w("gdt_ad_mob", str, th);
        }
    }
}
