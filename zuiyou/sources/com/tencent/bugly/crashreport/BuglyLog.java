package com.tencent.bugly.crashreport;

import android.support.media.ExifInterface;
import android.util.Log;
import com.tencent.bugly.b;
import com.tencent.bugly.proguard.ao;

public class BuglyLog {
    public static void v(String str, String str2) {
        if (str == null) {
            str = "";
        }
        if (str2 == null) {
            str2 = "null";
        }
        if (b.c) {
            Log.v(str, str2);
        }
        ao.a(ExifInterface.GPS_MEASUREMENT_INTERRUPTED, str, str2);
    }

    public static void d(String str, String str2) {
        if (str == null) {
            str = "";
        }
        if (str2 == null) {
            str2 = "null";
        }
        if (b.c) {
            Log.d(str, str2);
        }
        ao.a("D", str, str2);
    }

    public static void i(String str, String str2) {
        if (str == null) {
            str = "";
        }
        if (str2 == null) {
            str2 = "null";
        }
        if (b.c) {
            Log.i(str, str2);
        }
        ao.a("I", str, str2);
    }

    public static void w(String str, String str2) {
        if (str == null) {
            str = "";
        }
        if (str2 == null) {
            str2 = "null";
        }
        if (b.c) {
            Log.w(str, str2);
        }
        ao.a(ExifInterface.LONGITUDE_WEST, str, str2);
    }

    public static void e(String str, String str2) {
        if (str == null) {
            str = "";
        }
        if (str2 == null) {
            str2 = "null";
        }
        if (b.c) {
            Log.e(str, str2);
        }
        ao.a(ExifInterface.LONGITUDE_EAST, str, str2);
    }

    public static void e(String str, String str2, Throwable th) {
        if (str == null) {
            str = "";
        }
        if (str2 == null) {
            str2 = "null";
        }
        if (b.c) {
            Log.e(str, str2, th);
        }
        ao.a(ExifInterface.LONGITUDE_EAST, str, th);
    }

    public static void setCache(int i) {
        ao.a(i);
    }
}
