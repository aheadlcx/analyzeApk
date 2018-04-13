package com.tencent.mars.xlog;

import android.content.Context;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Looper;
import android.os.Process;
import android.widget.Toast;

public class Log {
    public static final int LEVEL_DEBUG = 1;
    public static final int LEVEL_ERROR = 4;
    public static final int LEVEL_FATAL = 5;
    public static final int LEVEL_INFO = 2;
    public static final int LEVEL_NONE = 6;
    public static final int LEVEL_VERBOSE = 0;
    public static final int LEVEL_WARNING = 3;
    private static final String SYS_INFO;
    private static final String TAG = "mars.xlog.log";
    private static LogImp debugLog = new LogImp() {
        private Handler handler = new Handler(Looper.getMainLooper());

        public void logV(String str, String str2, String str3, int i, int i2, long j, long j2, String str4) {
            if (Log.level <= 0) {
                android.util.Log.v(str, str4);
            }
        }

        public void logI(String str, String str2, String str3, int i, int i2, long j, long j2, String str4) {
            if (Log.level <= 2) {
                android.util.Log.i(str, str4);
            }
        }

        public void logD(String str, String str2, String str3, int i, int i2, long j, long j2, String str4) {
            if (Log.level <= 1) {
                android.util.Log.d(str, str4);
            }
        }

        public void logW(String str, String str2, String str3, int i, int i2, long j, long j2, String str4) {
            if (Log.level <= 3) {
                android.util.Log.w(str, str4);
            }
        }

        public void logE(String str, String str2, String str3, int i, int i2, long j, long j2, String str4) {
            if (Log.level <= 4) {
                android.util.Log.e(str, str4);
            }
        }

        public void logF(String str, String str2, String str3, int i, int i2, long j, long j2, final String str4) {
            if (Log.level <= 5) {
                android.util.Log.e(str, str4);
                if (Log.toastSupportContext != null) {
                    this.handler.post(new Runnable(this) {
                        final /* synthetic */ AnonymousClass1 b;

                        public void run() {
                            Toast.makeText(Log.toastSupportContext, str4, 1).show();
                        }
                    });
                }
            }
        }

        public int getLogLevel() {
            return Log.level;
        }

        public void appenderClose() {
        }

        public void appenderFlush(boolean z) {
        }
    };
    private static int level = 6;
    private static LogImp logImp = debugLog;
    public static Context toastSupportContext = null;

    public interface LogImp {
        void appenderClose();

        void appenderFlush(boolean z);

        int getLogLevel();

        void logD(String str, String str2, String str3, int i, int i2, long j, long j2, String str4);

        void logE(String str, String str2, String str3, int i, int i2, long j, long j2, String str4);

        void logF(String str, String str2, String str3, int i, int i2, long j, long j2, String str4);

        void logI(String str, String str2, String str3, int i, int i2, long j, long j2, String str4);

        void logV(String str, String str2, String str3, int i, int i2, long j, long j2, String str4);

        void logW(String str, String str2, String str3, int i, int i2, long j, long j2, String str4);
    }

    static {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            stringBuilder.append("VERSION.RELEASE:[" + VERSION.RELEASE);
            stringBuilder.append("] VERSION.CODENAME:[" + VERSION.CODENAME);
            stringBuilder.append("] VERSION.INCREMENTAL:[" + VERSION.INCREMENTAL);
            stringBuilder.append("] BOARD:[" + Build.BOARD);
            stringBuilder.append("] DEVICE:[" + Build.DEVICE);
            stringBuilder.append("] DISPLAY:[" + Build.DISPLAY);
            stringBuilder.append("] FINGERPRINT:[" + Build.FINGERPRINT);
            stringBuilder.append("] HOST:[" + Build.HOST);
            stringBuilder.append("] MANUFACTURER:[" + Build.MANUFACTURER);
            stringBuilder.append("] MODEL:[" + Build.MODEL);
            stringBuilder.append("] PRODUCT:[" + Build.PRODUCT);
            stringBuilder.append("] TAGS:[" + Build.TAGS);
            stringBuilder.append("] TYPE:[" + Build.TYPE);
            stringBuilder.append("] USER:[" + Build.USER + "]");
        } catch (Throwable th) {
            th.printStackTrace();
        }
        SYS_INFO = stringBuilder.toString();
    }

    public static void setLogImp(LogImp logImp) {
        logImp = logImp;
    }

    public static LogImp getImpl() {
        return logImp;
    }

    public static void appenderClose() {
        if (logImp != null) {
            logImp.appenderClose();
        }
    }

    public static void appenderFlush(boolean z) {
        if (logImp != null) {
            logImp.appenderFlush(z);
        }
    }

    public static int getLogLevel() {
        if (logImp != null) {
            return logImp.getLogLevel();
        }
        return 6;
    }

    public static void setLevel(int i, boolean z) {
        level = i;
        android.util.Log.w(TAG, "new log level: " + i);
        if (z) {
            Xlog.setLogLevel(i);
        }
    }

    public static void f(String str, String str2) {
        f(str, str2, (Object[]) null);
    }

    public static void e(String str, String str2) {
        e(str, str2, (Object[]) null);
    }

    public static void w(String str, String str2) {
        w(str, str2, (Object[]) null);
    }

    public static void i(String str, String str2) {
        i(str, str2, (Object[]) null);
    }

    public static void d(String str, String str2) {
        d(str, str2, (Object[]) null);
    }

    public static void v(String str, String str2) {
        v(str, str2, (Object[]) null);
    }

    public static void f(String str, String str2, Object... objArr) {
        if (logImp != null) {
            logImp.logF(str, "", "", 0, Process.myPid(), Thread.currentThread().getId(), Looper.getMainLooper().getThread().getId(), objArr == null ? str2 : String.format(str2, objArr));
        }
    }

    public static void e(String str, String str2, Object... objArr) {
        if (logImp != null) {
            String format = objArr == null ? str2 : String.format(str2, objArr);
            if (format == null) {
                format = "";
            }
            logImp.logE(str, "", "", 0, Process.myPid(), Thread.currentThread().getId(), Looper.getMainLooper().getThread().getId(), format);
        }
    }

    public static void w(String str, String str2, Object... objArr) {
        if (logImp != null) {
            String format = objArr == null ? str2 : String.format(str2, objArr);
            if (format == null) {
                format = "";
            }
            logImp.logW(str, "", "", 0, Process.myPid(), Thread.currentThread().getId(), Looper.getMainLooper().getThread().getId(), format);
        }
    }

    public static void i(String str, String str2, Object... objArr) {
        if (logImp != null) {
            String format = objArr == null ? str2 : String.format(str2, objArr);
            if (format == null) {
                format = "";
            }
            logImp.logI(str, "", "", 0, Process.myPid(), Thread.currentThread().getId(), Looper.getMainLooper().getThread().getId(), format);
        }
    }

    public static void d(String str, String str2, Object... objArr) {
        if (logImp != null) {
            String format = objArr == null ? str2 : String.format(str2, objArr);
            if (format == null) {
                format = "";
            }
            logImp.logD(str, "", "", 0, Process.myPid(), Thread.currentThread().getId(), Looper.getMainLooper().getThread().getId(), format);
        }
    }

    public static void v(String str, String str2, Object... objArr) {
        if (logImp != null) {
            String format = objArr == null ? str2 : String.format(str2, objArr);
            if (format == null) {
                format = "";
            }
            logImp.logV(str, "", "", 0, Process.myPid(), Thread.currentThread().getId(), Looper.getMainLooper().getThread().getId(), format);
        }
    }

    public static void printErrStackTrace(String str, Throwable th, String str2, Object... objArr) {
        if (logImp != null) {
            String format = objArr == null ? str2 : String.format(str2, objArr);
            if (format == null) {
                format = "";
            }
            String str3 = format + "  " + android.util.Log.getStackTraceString(th);
            logImp.logE(str, "", "", 0, Process.myPid(), Thread.currentThread().getId(), Looper.getMainLooper().getThread().getId(), str3);
        }
    }

    public static String getSysInfo() {
        return SYS_INFO;
    }
}
