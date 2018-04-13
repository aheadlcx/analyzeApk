package com.alipay.sdk.app.statistic;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.os.Build.VERSION;
import android.text.TextUtils;
import com.ali.auth.third.login.LoginConstants;
import com.alibaba.baichuan.android.trade.constants.AlibcConstants;
import com.alipay.sdk.cons.a;
import com.alipay.sdk.tid.b;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class c {
    public static final String A = "BindWaitTimeoutEx";
    public static final String B = "CheckClientExistEx";
    public static final String C = "CheckClientSignEx";
    public static final String D = "GetInstalledAppEx";
    public static final String E = "partner";
    public static final String F = "out_trade_no";
    public static final String G = "trade_no";
    public static final String a = "net";
    public static final String b = "biz";
    public static final String c = "cp";
    public static final String d = "auth";
    public static final String e = "third";
    public static final String f = "FormatResultEx";
    public static final String g = "GetApdidEx";
    public static final String h = "GetApdidNull";
    public static final String i = "GetApdidTimeout";
    public static final String j = "GetUtdidEx";
    public static final String k = "GetPackageInfoEx";
    public static final String l = "NotIncludeSignatures";
    public static final String m = "GetInstalledPackagesEx";
    public static final String n = "GetPublicKeyFromSignEx";
    public static final String o = "H5PayNetworkError";
    public static final String p = "H5AuthNetworkError";
    public static final String q = "SSLError";
    public static final String r = "H5PayDataAnalysisError";
    public static final String s = "H5AuthDataAnalysisError";
    public static final String t = "PublicKeyUnmatch";
    public static final String u = "ClientBindFailed";
    public static final String v = "TriDesEncryptError";
    public static final String w = "TriDesDecryptError";
    public static final String x = "ClientBindException";
    public static final String y = "SaveTradeTokenError";
    public static final String z = "ClientBindServiceFailed";
    String H;
    String I;
    String J;
    String K;
    String L;
    String M;
    String N;
    String O;
    String P = "";
    String Q;

    public c(Context context) {
        String format = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss").format(new Date());
        this.H = String.format("123456789,%s", new Object[]{format});
        this.J = a(context);
        format = a(a.e);
        String a = a(a.f);
        this.K = String.format("android,3,%s,%s,com.alipay.mcpay,5.0,-,-,-", new Object[]{format, a});
        format = a(b.a().a);
        a = a(com.alipay.sdk.sys.b.a().c());
        this.L = String.format("%s,%s,-,-,-", new Object[]{format, a});
        format = a(com.alipay.sdk.util.a.d(context));
        a = AlibcConstants.PF_ANDROID;
        String a2 = a(VERSION.RELEASE);
        String a3 = a(Build.MODEL);
        String a4 = a(com.alipay.sdk.util.a.a(context).a());
        String a5 = a(com.alipay.sdk.util.a.b(context).p);
        String a6 = a(com.alipay.sdk.util.a.a(context).b());
        this.M = String.format("%s,%s,%s,%s,%s,%s,%s,%s,%s,-", new Object[]{format, a, a2, a3, "-", a4, a5, "gw", a6});
        this.N = "-";
        this.O = "-";
        this.Q = "-";
    }

    private boolean a() {
        return TextUtils.isEmpty(this.P);
    }

    public final void a(String str, String str2, Throwable th) {
        a(str, str2, a(th));
    }

    private void a(String str, String str2, Throwable th, String str3) {
        a(str, str2, a(th), str3);
    }

    public final void a(String str, String str2, String str3, String str4) {
        String str5 = "";
        if (!TextUtils.isEmpty(this.P)) {
            str5 = str5 + "^";
        }
        this.P += (str5 + String.format("%s,%s,%s,%s", new Object[]{str, str2, a(str3), str4}));
    }

    public final void a(String str, String str2, String str3) {
        a(str, str2, str3, "-");
    }

    static String a(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        return str.replace("[", "【").replace("]", "】").replace("(", "（").replace(")", "）").replace(",", "，").replace("-", LoginConstants.EQUAL).replace("^", "~");
    }

    static String a(Throwable th) {
        if (th == null) {
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer();
        try {
            stringBuffer.append(th.getClass().getName()).append(":");
            stringBuffer.append(th.getMessage());
            stringBuffer.append(" 》 ");
            StackTraceElement[] stackTrace = th.getStackTrace();
            if (stackTrace != null) {
                for (StackTraceElement stackTraceElement : stackTrace) {
                    stringBuffer.append(stackTraceElement.toString() + " 》 ");
                }
            }
        } catch (Throwable th2) {
        }
        return stringBuffer.toString();
    }

    private String b(String str) {
        String str2 = null;
        if (TextUtils.isEmpty(this.P)) {
            return "";
        }
        String str3;
        String[] split = str.split("&");
        if (split != null) {
            str3 = null;
            for (String split2 : split) {
                String[] split3 = split2.split(LoginConstants.EQUAL);
                if (split3 != null && split3.length == 2) {
                    if (split3[0].equalsIgnoreCase(E)) {
                        split3[1].replace("\"", "");
                    } else if (split3[0].equalsIgnoreCase(F)) {
                        str3 = split3[1].replace("\"", "");
                    } else if (split3[0].equalsIgnoreCase(G)) {
                        str2 = split3[1].replace("\"", "");
                    }
                }
            }
        } else {
            str3 = null;
        }
        str2 = a(str2);
        String a = a(a(str3));
        this.I = String.format("%s,%s,-,%s,-,-,-", new Object[]{str2, str3, a});
        return String.format("[(%s),(%s),(%s),(%s),(%s),(%s),(%s),(%s),(%s),(%s)]", new Object[]{this.H, this.I, this.J, this.K, this.L, this.M, this.N, this.O, this.P, this.Q});
    }

    @SuppressLint({"SimpleDateFormat"})
    private static String b() {
        String format = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss").format(new Date());
        return String.format("123456789,%s", new Object[]{format});
    }

    private static String c(String str) {
        String str2;
        String str3 = null;
        String[] split = str.split("&");
        if (split != null) {
            str2 = null;
            for (String split2 : split) {
                String[] split3 = split2.split(LoginConstants.EQUAL);
                if (split3 != null && split3.length == 2) {
                    if (split3[0].equalsIgnoreCase(E)) {
                        split3[1].replace("\"", "");
                    } else if (split3[0].equalsIgnoreCase(F)) {
                        str2 = split3[1].replace("\"", "");
                    } else if (split3[0].equalsIgnoreCase(G)) {
                        str3 = split3[1].replace("\"", "");
                    }
                }
            }
        } else {
            str2 = null;
        }
        str3 = a(str3);
        String a = a(a(str2));
        return String.format("%s,%s,-,%s,-,-,-", new Object[]{str3, a(str2), a});
    }

    private static String a(Context context) {
        String str = "-";
        String str2 = "-";
        if (context != null) {
            try {
                Context applicationContext = context.getApplicationContext();
                str = applicationContext.getPackageName();
                str2 = applicationContext.getPackageManager().getPackageInfo(str, 0).versionName;
            } catch (Throwable th) {
            }
        }
        return String.format("%s,%s,-,-,-", new Object[]{str, str2});
    }

    private static String c() {
        String a = a(a.e);
        String a2 = a(a.f);
        return String.format("android,3,%s,%s,com.alipay.mcpay,5.0,-,-,-", new Object[]{a, a2});
    }

    private static String d() {
        String a = a(b.a().a);
        String a2 = a(com.alipay.sdk.sys.b.a().c());
        return String.format("%s,%s,-,-,-", new Object[]{a, a2});
    }

    private static String b(Context context) {
        String a = a(com.alipay.sdk.util.a.d(context));
        String str = AlibcConstants.PF_ANDROID;
        String a2 = a(VERSION.RELEASE);
        String a3 = a(Build.MODEL);
        String a4 = a(com.alipay.sdk.util.a.a(context).a());
        String a5 = a(com.alipay.sdk.util.a.b(context).p);
        String a6 = a(com.alipay.sdk.util.a.a(context).b());
        return String.format("%s,%s,%s,%s,%s,%s,%s,%s,%s,-", new Object[]{a, str, a2, a3, "-", a4, a5, "gw", a6});
    }
}
