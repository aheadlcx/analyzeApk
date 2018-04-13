package com.alibaba.mtl.log.e;

import android.os.Process;
import android.util.Log;

public class i {
    private static boolean I = false;
    private static boolean J = false;
    private static String af = "UTAnalytics:";

    public static boolean m() {
        return I;
    }

    public static boolean n() {
        return J;
    }

    public static void d(boolean z) {
        J = z;
    }

    public static void a(String str, Object... objArr) {
        if (J) {
            String str2 = af + str;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("pid:").append(Process.myPid()).append(" ");
            if (objArr != null) {
                for (int i = 0; i < objArr.length; i++) {
                    if (objArr[i] != null) {
                        String obj = objArr[i].toString();
                        if (obj.endsWith(":") || obj.endsWith(": ")) {
                            stringBuilder.append(obj);
                        } else {
                            stringBuilder.append(obj).append(",");
                        }
                    }
                }
            }
            Log.d(str2, stringBuilder.toString());
        }
    }

    public static void a(String str, Object obj, Throwable th) {
        if (n() || m()) {
            Log.w(str + af, obj + "", th);
        }
    }

    public static void a(String str, Object obj) {
        if (n() || m()) {
            Log.w(str + af, obj + "");
        }
    }

    public static void a(String str, String... strArr) {
        if (J) {
            String str2 = af + str;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("pid:").append(Process.myPid()).append(" ");
            if (strArr != null) {
                for (int i = 0; i < strArr.length; i++) {
                    if (strArr[i] != null) {
                        String str3 = strArr[i];
                        if (str3.endsWith(":") || str3.endsWith(": ")) {
                            stringBuilder.append(str3);
                        } else {
                            stringBuilder.append(str3).append(",");
                        }
                    }
                }
            }
            Log.i(str2, stringBuilder.toString());
        }
    }
}
