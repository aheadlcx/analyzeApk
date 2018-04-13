package com.tencent.smtt.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build;
import android.os.Build.VERSION;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import com.ali.auth.third.login.LoginConstants;
import com.tencent.connect.common.Constants;
import com.tencent.smtt.sdk.TbsConfig;
import com.tencent.smtt.sdk.WebView;

public class w {
    private static String a = null;
    private static String b = "GA";
    private static String c = "GE";
    private static String d = "9422";
    private static String e = "0";
    private static String f = "";
    private static boolean g = false;
    private static boolean h = false;
    private static boolean i = false;

    private static String a() {
        return " " + Build.MODEL.replaceAll("[ |\\/|\\_|\\&|\\|]", "") + " ";
    }

    public static String a(Context context) {
        if (!TextUtils.isEmpty(a)) {
            return a;
        }
        a = a(context, String.valueOf(WebView.getTbsSDKVersion(context)), "0", b, c, d, e, f, g);
        return a;
    }

    private static String a(Context context, String str, String str2, String str3, String str4, String str5, String str6, String str7, boolean z) {
        String str8;
        String str9;
        NameNotFoundException e;
        String a;
        Object str10;
        String str11 = "PHONE";
        StringBuilder stringBuilder = new StringBuilder();
        String str12 = b(context) + "*" + c(context);
        try {
            ApplicationInfo applicationInfo = context.getApplicationContext().getApplicationInfo();
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(applicationInfo.packageName, 0);
            str8 = applicationInfo.packageName;
            try {
                if (TextUtils.isEmpty(str7)) {
                    str7 = packageInfo.versionName;
                }
                str9 = str8;
            } catch (NameNotFoundException e2) {
                e = e2;
                e.printStackTrace();
                str7 = null;
                str9 = str8;
                a = a(str9);
                if ("QB".equals(a)) {
                    if (d(context)) {
                        str8 = "PAD";
                    }
                    str8 = str11;
                } else {
                    if (z) {
                        str8 = "PAD";
                    }
                    str8 = str11;
                }
                stringBuilder.append("QV").append(LoginConstants.EQUAL).append("3");
                a(stringBuilder, "PL", "ADR");
                a(stringBuilder, "PR", a);
                a(stringBuilder, "PP", str9);
                a(stringBuilder, "PPVN", str7);
                if (!TextUtils.isEmpty(str)) {
                    a(stringBuilder, "TBSVC", str);
                }
                a(stringBuilder, "CO", "SYS");
                if (!TextUtils.isEmpty(str2)) {
                    a(stringBuilder, "COVC", str2);
                }
                a(stringBuilder, "PB", str4);
                a(stringBuilder, "VE", str3);
                a(stringBuilder, "DE", str8);
                str9 = "CHID";
                if (TextUtils.isEmpty(str6)) {
                    str6 = "0";
                }
                a(stringBuilder, str9, str6);
                a(stringBuilder, "LCID", str5);
                str8 = a();
                str10 = new String(str8.getBytes("UTF-8"), "ISO8859-1");
                if (!TextUtils.isEmpty(str10)) {
                    a(stringBuilder, "MO", str10);
                }
                a(stringBuilder, "RL", str12);
                str8 = VERSION.RELEASE;
                str10 = new String(str8.getBytes("UTF-8"), "ISO8859-1");
                if (!TextUtils.isEmpty(str10)) {
                    a(stringBuilder, "OS", str10);
                }
                a(stringBuilder, "API", VERSION.SDK_INT + "");
                return stringBuilder.toString();
            }
        } catch (NameNotFoundException e3) {
            NameNotFoundException nameNotFoundException = e3;
            str8 = null;
            e = nameNotFoundException;
            e.printStackTrace();
            str7 = null;
            str9 = str8;
            a = a(str9);
            if ("QB".equals(a)) {
                if (z) {
                    str8 = "PAD";
                }
                str8 = str11;
            } else {
                if (d(context)) {
                    str8 = "PAD";
                }
                str8 = str11;
            }
            stringBuilder.append("QV").append(LoginConstants.EQUAL).append("3");
            a(stringBuilder, "PL", "ADR");
            a(stringBuilder, "PR", a);
            a(stringBuilder, "PP", str9);
            a(stringBuilder, "PPVN", str7);
            if (TextUtils.isEmpty(str)) {
                a(stringBuilder, "TBSVC", str);
            }
            a(stringBuilder, "CO", "SYS");
            if (TextUtils.isEmpty(str2)) {
                a(stringBuilder, "COVC", str2);
            }
            a(stringBuilder, "PB", str4);
            a(stringBuilder, "VE", str3);
            a(stringBuilder, "DE", str8);
            str9 = "CHID";
            if (TextUtils.isEmpty(str6)) {
                str6 = "0";
            }
            a(stringBuilder, str9, str6);
            a(stringBuilder, "LCID", str5);
            str8 = a();
            str10 = new String(str8.getBytes("UTF-8"), "ISO8859-1");
            if (TextUtils.isEmpty(str10)) {
                a(stringBuilder, "MO", str10);
            }
            a(stringBuilder, "RL", str12);
            str8 = VERSION.RELEASE;
            str10 = new String(str8.getBytes("UTF-8"), "ISO8859-1");
            if (TextUtils.isEmpty(str10)) {
                a(stringBuilder, "OS", str10);
            }
            a(stringBuilder, "API", VERSION.SDK_INT + "");
            return stringBuilder.toString();
        }
        a = a(str9);
        if ("QB".equals(a)) {
            if (z) {
                str8 = "PAD";
            }
            str8 = str11;
        } else {
            if (d(context)) {
                str8 = "PAD";
            }
            str8 = str11;
        }
        stringBuilder.append("QV").append(LoginConstants.EQUAL).append("3");
        a(stringBuilder, "PL", "ADR");
        a(stringBuilder, "PR", a);
        a(stringBuilder, "PP", str9);
        a(stringBuilder, "PPVN", str7);
        if (TextUtils.isEmpty(str)) {
            a(stringBuilder, "TBSVC", str);
        }
        a(stringBuilder, "CO", "SYS");
        if (TextUtils.isEmpty(str2)) {
            a(stringBuilder, "COVC", str2);
        }
        a(stringBuilder, "PB", str4);
        a(stringBuilder, "VE", str3);
        a(stringBuilder, "DE", str8);
        str9 = "CHID";
        if (TextUtils.isEmpty(str6)) {
            str6 = "0";
        }
        a(stringBuilder, str9, str6);
        a(stringBuilder, "LCID", str5);
        str8 = a();
        try {
            str10 = new String(str8.getBytes("UTF-8"), "ISO8859-1");
        } catch (Exception e4) {
            str9 = str8;
        }
        if (TextUtils.isEmpty(str10)) {
            a(stringBuilder, "MO", str10);
        }
        a(stringBuilder, "RL", str12);
        str8 = VERSION.RELEASE;
        try {
            str10 = new String(str8.getBytes("UTF-8"), "ISO8859-1");
        } catch (Exception e5) {
            str9 = str8;
        }
        if (TextUtils.isEmpty(str10)) {
            a(stringBuilder, "OS", str10);
        }
        a(stringBuilder, "API", VERSION.SDK_INT + "");
        return stringBuilder.toString();
    }

    private static String a(String str) {
        return str.equals("com.tencent.mm") ? "WX" : str.equals("com.tencent.mobileqq") ? Constants.SOURCE_QQ : str.equals("com.qzone") ? "QZ" : str.equals(TbsConfig.APP_QB) ? "QB" : "TRD";
    }

    private static void a(StringBuilder stringBuilder, String str, String str2) {
        stringBuilder.append("&").append(str).append(LoginConstants.EQUAL).append(str2);
    }

    private static int b(Context context) {
        try {
            return ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getWidth();
        } catch (Exception e) {
            return -1;
        }
    }

    private static int c(Context context) {
        try {
            return ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getHeight();
        } catch (Exception e) {
            return -1;
        }
    }

    private static boolean d(Context context) {
        if (h) {
            return i;
        }
        i = (Math.min(b(context), c(context)) * 160) / e(context) >= 700;
        h = true;
        return i;
    }

    private static int e(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService("window");
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.densityDpi;
    }
}
