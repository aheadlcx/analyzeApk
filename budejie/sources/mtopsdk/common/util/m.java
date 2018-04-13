package mtopsdk.common.util;

import android.util.Log;
import java.util.HashMap;
import java.util.Map;

public class m {
    private static boolean a = true;
    private static boolean b = true;
    private static TBSdkLog$LogEnable c = TBSdkLog$LogEnable.DebugEnable;
    private static Map d = new HashMap(5);

    static {
        for (TBSdkLog$LogEnable tBSdkLog$LogEnable : TBSdkLog$LogEnable.values()) {
            d.put(tBSdkLog$LogEnable.getLogEnable(), tBSdkLog$LogEnable);
        }
    }

    private static String a(String str, String... strArr) {
        StringBuilder stringBuilder = new StringBuilder();
        if (str != null) {
            stringBuilder.append("[seq:").append(str).append("]|");
        }
        if (strArr != null) {
            for (int i = 0; i < strArr.length; i++) {
                stringBuilder.append(strArr[i]);
                if (i < strArr.length - 1) {
                    stringBuilder.append(",");
                }
            }
        }
        return stringBuilder.toString();
    }

    public static void a(String str, String str2) {
        a(str, null, str2);
    }

    public static void a(String str, String str2, String str3) {
        if (a(TBSdkLog$LogEnable.DebugEnable) && a) {
            Log.d(str, a(str2, str3));
        }
    }

    public static void a(String str, String str2, String str3, Throwable th) {
        if (a(TBSdkLog$LogEnable.WarnEnable) && a) {
            Log.w(str, a(str2, str3), th);
        }
    }

    public static void a(String str, String str2, Throwable th) {
        a(str, null, str2, th);
    }

    public static void a(boolean z) {
        a = z;
        Log.d("mtopsdk.TBSdkLog", "[setPrintLog] printLog=" + z);
    }

    public static boolean a(TBSdkLog$LogEnable tBSdkLog$LogEnable) {
        return tBSdkLog$LogEnable.ordinal() >= c.ordinal();
    }

    public static void b(String str, String str2) {
        b(str, null, str2);
    }

    public static void b(String str, String str2, String str3) {
        if (a(TBSdkLog$LogEnable.InfoEnable) && a) {
            Log.i(str, a(str2, str3));
        }
    }

    public static void b(String str, String str2, String str3, Throwable th) {
        if (a(TBSdkLog$LogEnable.ErrorEnable) && a) {
            Log.e(str, a(str2, str3), th);
        }
    }

    public static void b(String str, String str2, Throwable th) {
        b(str, null, str2, th);
    }

    public static void b(boolean z) {
        b = z;
        Log.d("mtopsdk.TBSdkLog", "[setTLogEnabled] tLogEnabled=" + z);
    }

    public static void c(String str, String str2) {
        c(str, null, str2);
    }

    public static void c(String str, String str2, String str3) {
        if (a(TBSdkLog$LogEnable.WarnEnable) && a) {
            Log.w(str, a(str2, str3));
        }
    }

    public static void d(String str, String str2) {
        d(str, null, str2);
    }

    public static void d(String str, String str2, String str3) {
        if (a(TBSdkLog$LogEnable.ErrorEnable) && a) {
            Log.e(str, a(str2, str3));
        }
    }
}
