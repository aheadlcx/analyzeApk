package com.tencent.tinker.lib.util;

import android.util.Log;

public class TinkerLog {
    private static final String TAG = "Tinker.TinkerLog";
    private static TinkerLogImp debugLog = new TinkerLogImp() {
        public void v(String str, String str2, Object... objArr) {
            if (!(objArr == null || objArr.length == 0)) {
                str2 = String.format(str2, objArr);
            }
            Log.v(str, str2);
        }

        public void i(String str, String str2, Object... objArr) {
            if (!(objArr == null || objArr.length == 0)) {
                str2 = String.format(str2, objArr);
            }
            Log.i(str, str2);
        }

        public void d(String str, String str2, Object... objArr) {
            if (!(objArr == null || objArr.length == 0)) {
                str2 = String.format(str2, objArr);
            }
            Log.d(str, str2);
        }

        public void w(String str, String str2, Object... objArr) {
            if (!(objArr == null || objArr.length == 0)) {
                str2 = String.format(str2, objArr);
            }
            Log.w(str, str2);
        }

        public void e(String str, String str2, Object... objArr) {
            if (!(objArr == null || objArr.length == 0)) {
                str2 = String.format(str2, objArr);
            }
            Log.e(str, str2);
        }

        public void printErrStackTrace(String str, Throwable th, String str2, Object... objArr) {
            String format = (objArr == null || objArr.length == 0) ? str2 : String.format(str2, objArr);
            if (format == null) {
                format = "";
            }
            Log.e(str, format + "  " + Log.getStackTraceString(th));
        }
    };
    private static TinkerLogImp tinkerLogImp = debugLog;

    public interface TinkerLogImp {
        void d(String str, String str2, Object... objArr);

        void e(String str, String str2, Object... objArr);

        void i(String str, String str2, Object... objArr);

        void printErrStackTrace(String str, Throwable th, String str2, Object... objArr);

        void v(String str, String str2, Object... objArr);

        void w(String str, String str2, Object... objArr);
    }

    public static void setTinkerLogImp(TinkerLogImp tinkerLogImp) {
        tinkerLogImp = tinkerLogImp;
    }

    public static TinkerLogImp getImpl() {
        return tinkerLogImp;
    }

    public static void v(String str, String str2, Object... objArr) {
        if (tinkerLogImp != null) {
            tinkerLogImp.v(str, str2, objArr);
        }
    }

    public static void e(String str, String str2, Object... objArr) {
        if (tinkerLogImp != null) {
            tinkerLogImp.e(str, str2, objArr);
        }
    }

    public static void w(String str, String str2, Object... objArr) {
        if (tinkerLogImp != null) {
            tinkerLogImp.w(str, str2, objArr);
        }
    }

    public static void i(String str, String str2, Object... objArr) {
        if (tinkerLogImp != null) {
            tinkerLogImp.i(str, str2, objArr);
        }
    }

    public static void d(String str, String str2, Object... objArr) {
        if (tinkerLogImp != null) {
            tinkerLogImp.d(str, str2, objArr);
        }
    }

    public static void printErrStackTrace(String str, Throwable th, String str2, Object... objArr) {
        if (tinkerLogImp != null) {
            tinkerLogImp.printErrStackTrace(str, th, str2, objArr);
        }
    }
}
