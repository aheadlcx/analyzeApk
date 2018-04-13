package com.ali.auth.third.core.trace;

import android.content.Context;
import android.util.Log;
import com.ali.auth.third.core.config.ConfigManager;
import com.ali.auth.third.core.context.KernelContext;
import com.ali.auth.third.core.message.Message;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SDKLogger {
    private static final String TAG_PREFIX = "AuthSDK_";

    public static void i(String str, String str2) {
        try {
            if (isDebugEnabled()) {
                Log.i(TAG_PREFIX + str, str2 + appendCurrentTime());
            }
        } catch (Throwable th) {
        }
    }

    public static void d(String str, String str2) {
        try {
            if (isDebugEnabled()) {
                Log.d(TAG_PREFIX + str, str2 + appendCurrentTime());
            }
        } catch (Throwable th) {
        }
    }

    public static void d(String str, String str2, Throwable th) {
        try {
            if (isDebugEnabled()) {
                Log.d(TAG_PREFIX + str, str2 + appendCurrentTime(), th);
            }
        } catch (Throwable th2) {
        }
    }

    public static void w(String str, String str2) {
        try {
            if (isDebugEnabled()) {
                Log.w(TAG_PREFIX + str, str2 + appendCurrentTime());
            }
        } catch (Throwable th) {
        }
    }

    public static void e(String str, String str2, Throwable th) {
        try {
            if (isDebugEnabled()) {
                Log.e(TAG_PREFIX + str, str2 + appendCurrentTime(), th);
            }
        } catch (Throwable th2) {
        }
    }

    public static void e(String str, String str2) {
        try {
            if (isDebugEnabled()) {
                Log.e(TAG_PREFIX + str, str2 + appendCurrentTime());
            }
        } catch (Throwable th) {
        }
    }

    public static boolean isDebugEnabled() {
        return ConfigManager.DEBUG || isApkDebugable(KernelContext.getApplicationContext());
    }

    public static boolean isApkDebugable(Context context) {
        try {
            if ((context.getApplicationInfo().flags & 2) != 0) {
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    public static void log(String str, Message message, Throwable th) {
        if (isDebugEnabled()) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("***********************************************************\n");
            stringBuilder.append("错误编码 = ").append(message.code).append("\n");
            stringBuilder.append("错误消息 = ").append(message.message).append("\n");
            stringBuilder.append("解决建议 = ").append(message.action).append("\n");
            if (th != null) {
                stringBuilder.append("错误堆栈 = ").append(Log.getStackTraceString(th)).append("\n");
            }
            stringBuilder.append("***********************************************************\n");
            String str2 = message.type;
            if ("D".equals(str2)) {
                d(str, stringBuilder.toString());
            } else if ("E".equals(str2)) {
                e(str, stringBuilder.toString());
            } else if ("W".equals(str2)) {
                w(str, stringBuilder.toString());
            } else {
                i(str, stringBuilder.toString());
            }
        }
    }

    public static void log(String str, Message message) {
        log(str, message, null);
    }

    private static String appendCurrentTime() {
        StringBuilder stringBuilder = new StringBuilder("\n");
        stringBuilder.append("time =");
        stringBuilder.append(getDateStringByMill());
        return stringBuilder.toString();
    }

    private static String getDateStringByMill() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS").format(new Date(System.currentTimeMillis()));
    }
}
