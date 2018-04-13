package com.tencent.bugly.beta.tinker;

import android.util.Log;
import com.tencent.tinker.lib.util.TinkerLog.TinkerLogImp;

public class TinkerLogger implements TinkerLogImp {
    public static final int LEVEL_DEBUG = 1;
    public static final int LEVEL_ERROR = 4;
    public static final int LEVEL_INFO = 2;
    public static final int LEVEL_NONE = 5;
    public static final int LEVEL_VERBOSE = 0;
    public static final int LEVEL_WARNING = 3;
    private static final String TAG = "Tinker.TinkerLogger";
    private static int level = 0;

    public static int getLogLevel() {
        return level;
    }

    public static void setLevel(int i) {
        level = i;
        Log.w(TAG, "new log level: " + i);
    }

    public void v(String str, String str2, Object... objArr) {
        if (level <= 0) {
            if (objArr != null) {
                str2 = String.format(str2, objArr);
            }
            Log.v(str, str2);
        }
    }

    public void i(String str, String str2, Object... objArr) {
        if (level <= 2) {
            if (objArr != null) {
                str2 = String.format(str2, objArr);
            }
            Log.i(str, str2);
        }
    }

    public void w(String str, String str2, Object... objArr) {
        if (level <= 3) {
            if (objArr != null) {
                str2 = String.format(str2, objArr);
            }
            Log.w(str, str2);
        }
    }

    public void d(String str, String str2, Object... objArr) {
        if (level <= 1) {
            if (objArr != null) {
                str2 = String.format(str2, objArr);
            }
            Log.d(str, str2);
        }
    }

    public void e(String str, String str2, Object... objArr) {
        if (level <= 4) {
            if (objArr != null) {
                str2 = String.format(str2, objArr);
            }
            Log.e(str, str2);
        }
    }

    public void printErrStackTrace(String str, Throwable th, String str2, Object... objArr) {
        String format = objArr == null ? str2 : String.format(str2, objArr);
        if (format == null) {
            format = "";
        }
        Log.e(str, format + "  " + Log.getStackTraceString(th));
    }
}
