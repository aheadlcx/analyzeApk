package in.srain.cube.views.ptr.util;

import android.util.Log;

public class PtrCLog {
    public static final int LEVEL_DEBUG = 1;
    public static final int LEVEL_ERROR = 4;
    public static final int LEVEL_FATAL = 5;
    public static final int LEVEL_INFO = 2;
    public static final int LEVEL_VERBOSE = 0;
    public static final int LEVEL_WARNING = 3;
    private static int a = 0;

    public static void setLogLevel(int i) {
        a = i;
    }

    public static void v(String str, String str2) {
        if (a <= 0) {
            Log.v(str, str2);
        }
    }

    public static void v(String str, String str2, Throwable th) {
        if (a <= 0) {
            Log.v(str, str2, th);
        }
    }

    public static void v(String str, String str2, Object... objArr) {
        if (a <= 0) {
            if (objArr.length > 0) {
                str2 = String.format(str2, objArr);
            }
            Log.v(str, str2);
        }
    }

    public static void d(String str, String str2) {
        if (a <= 1) {
            Log.d(str, str2);
        }
    }

    public static void d(String str, String str2, Object... objArr) {
        if (a <= 1) {
            if (objArr.length > 0) {
                str2 = String.format(str2, objArr);
            }
            Log.d(str, str2);
        }
    }

    public static void d(String str, String str2, Throwable th) {
        if (a <= 1) {
            Log.d(str, str2, th);
        }
    }

    public static void i(String str, String str2) {
        if (a <= 2) {
            Log.i(str, str2);
        }
    }

    public static void i(String str, String str2, Object... objArr) {
        if (a <= 2) {
            if (objArr.length > 0) {
                str2 = String.format(str2, objArr);
            }
            Log.i(str, str2);
        }
    }

    public static void i(String str, String str2, Throwable th) {
        if (a <= 2) {
            Log.i(str, str2, th);
        }
    }

    public static void w(String str, String str2) {
        if (a <= 3) {
            Log.w(str, str2);
        }
    }

    public static void w(String str, String str2, Object... objArr) {
        if (a <= 3) {
            if (objArr.length > 0) {
                str2 = String.format(str2, objArr);
            }
            Log.w(str, str2);
        }
    }

    public static void w(String str, String str2, Throwable th) {
        if (a <= 3) {
            Log.w(str, str2, th);
        }
    }

    public static void e(String str, String str2) {
        if (a <= 4) {
            Log.e(str, str2);
        }
    }

    public static void e(String str, String str2, Object... objArr) {
        if (a <= 4) {
            if (objArr.length > 0) {
                str2 = String.format(str2, objArr);
            }
            Log.e(str, str2);
        }
    }

    public static void e(String str, String str2, Throwable th) {
        if (a <= 4) {
            Log.e(str, str2, th);
        }
    }

    public static void f(String str, String str2) {
        if (a <= 5) {
            Log.wtf(str, str2);
        }
    }

    public static void f(String str, String str2, Object... objArr) {
        if (a <= 5) {
            if (objArr.length > 0) {
                str2 = String.format(str2, objArr);
            }
            Log.wtf(str, str2);
        }
    }

    public static void f(String str, String str2, Throwable th) {
        if (a <= 5) {
            Log.wtf(str, str2, th);
        }
    }
}
