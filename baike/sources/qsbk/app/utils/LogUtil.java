package qsbk.app.utils;

import android.util.Log;

public class LogUtil {
    public static String getClassName(int i) {
        return Thread.currentThread().getStackTrace()[i].getClassName();
    }

    public static void d(String str, int i) {
        if (DebugUtil.DEBUG) {
            Log.d(getClassName(i + 4), str);
        }
    }

    public static void d(String str) {
        d(str, 1);
    }

    public static void w(String str) {
        if (DebugUtil.DEBUG) {
            Log.w(getClassName(4), str);
        }
    }

    public static void e(String str) {
        if (DebugUtil.DEBUG) {
            Log.e(getClassName(4), str);
        }
    }
}
