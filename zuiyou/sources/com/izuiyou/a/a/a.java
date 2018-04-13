package com.izuiyou.a.a;

import android.content.Context;
import android.os.Build.VERSION;
import android.os.Looper;
import android.os.Process;
import android.text.TextUtils;
import android.webkit.WebView;
import com.getkeepsafe.relinker.b;
import com.tencent.bugly.crashreport.CrashReport;
import com.tencent.mars.xlog.Xlog;

public final class a {
    private static Xlog a;
    private static boolean b = false;

    public static void a(Context context, String str, String str2, boolean z) {
        b = z;
        try {
            if (VERSION.SDK_INT >= 21) {
                b.a(context, "stlport_shared", new a$1(context, z, str, str2));
            }
        } catch (Throwable e) {
            a(e);
        }
    }

    public static void a(String str, Object obj) {
        a(0, str, obj);
    }

    public static void b(String str, Object obj) {
        a(1, str, obj);
    }

    public static void c(String str, Object obj) {
        a(2, str, obj);
    }

    public static void d(String str, Object obj) {
        a(4, str, obj);
    }

    private static void a(int i, String str, Object obj) {
        try {
            String[] a = a(null, obj);
            String str2 = a[0];
            String str3 = a[1];
            String str4 = a[2];
            String str5 = TextUtils.isEmpty(str) ? str2 : str;
            if (a == null) {
                b.a(i, str5, str4 + str3 + a(obj));
                return;
            }
            switch (i) {
                case 0:
                    a.logV(str5, str2, str3, Integer.valueOf(str4).intValue(), Process.myPid(), Thread.currentThread().getId(), Looper.getMainLooper().getThread().getId(), a(obj));
                    return;
                case 1:
                    a.logD(str5, str2, str3, Integer.valueOf(str4).intValue(), Process.myPid(), Thread.currentThread().getId(), Looper.getMainLooper().getThread().getId(), a(obj));
                    return;
                case 2:
                    a.logI(str5, str2, str3, Integer.valueOf(str4).intValue(), Process.myPid(), Thread.currentThread().getId(), Looper.getMainLooper().getThread().getId(), a(obj));
                    return;
                case 3:
                    a.logW(str5, str2, str3, Integer.valueOf(str4).intValue(), Process.myPid(), Thread.currentThread().getId(), Looper.getMainLooper().getThread().getId(), a(obj));
                    return;
                case 4:
                    a.logE(str5, str2, str3, Integer.valueOf(str4).intValue(), Process.myPid(), Thread.currentThread().getId(), Looper.getMainLooper().getThread().getId(), a(obj));
                    return;
                case 5:
                    a.logF(str5, str2, str3, Integer.valueOf(str4).intValue(), Process.myPid(), Thread.currentThread().getId(), Looper.getMainLooper().getThread().getId(), a(obj));
                    return;
                default:
                    return;
            }
            a(e);
        } catch (Throwable e) {
            a(e);
        }
    }

    private static String[] a(String str, Object... objArr) {
        Object obj;
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        String fileName = stackTrace[5].getFileName();
        String methodName = stackTrace[5].getMethodName();
        int lineNumber = stackTrace[5].getLineNumber();
        if (methodName == null) {
            obj = "null";
        } else {
            obj = methodName.substring(0, 1).toUpperCase() + methodName.substring(1);
        }
        return new String[]{String.valueOf(fileName), String.valueOf(methodName), String.valueOf(lineNumber), String.valueOf(obj)};
    }

    private static String a(Object... objArr) {
        int i = 0;
        if (objArr.length > 1) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("\n");
            while (i < objArr.length) {
                Object obj = objArr[i];
                if (obj == null) {
                    stringBuilder.append("Param").append("[").append(i).append("]").append(" = ").append("null").append("\n");
                } else {
                    stringBuilder.append("Param").append("[").append(i).append("]").append(" = ").append(obj.toString()).append("\n");
                }
                i++;
            }
            return stringBuilder.toString();
        }
        Object obj2 = objArr[0];
        return obj2 == null ? "null" : obj2.toString();
    }

    public static boolean a() {
        return b;
    }

    public static void a(WebView webView, boolean z) {
        CrashReport.setJavascriptMonitor(webView, z);
    }

    public static void a(Throwable th) {
        CrashReport.postCatchedException(th);
    }
}
