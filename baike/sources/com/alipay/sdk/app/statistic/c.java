package com.alipay.sdk.app.statistic;

import android.content.Context;
import android.os.Build;
import android.os.Build.VERSION;
import android.text.TextUtils;
import com.alipay.sdk.cons.a;
import com.alipay.sdk.tid.b;
import com.baidu.mobstat.Config;
import com.xiaomi.mipush.sdk.Constants;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class c {
    public static final String A = "BindWaitTimeoutEx";
    public static final String B = "CheckClientExistEx";
    public static final String C = "CheckClientSignEx";
    public static final String D = "GetInstalledAppEx";
    public static final String E = "GetInstalledAppEx";
    public static final String F = "partner";
    public static final String G = "out_trade_no";
    public static final String H = "trade_no";
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
    String I;
    String J;
    String K;
    String L;
    String M;
    String N;
    String O;
    String P;
    String Q = "";
    String R;

    public c(Context context) {
        String format = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss").format(new Date());
        this.I = String.format("123456789,%s", new Object[]{format});
        this.K = a(context);
        format = a(a.f);
        String a = a(a.g);
        this.L = String.format("android,3,%s,%s,com.alipay.mcpay,5.0,-,-,-", new Object[]{format, a});
        format = a(b.a().a);
        a = a(com.alipay.sdk.sys.b.a().c());
        this.M = String.format("%s,%s,-,-,-", new Object[]{format, a});
        format = a(com.alipay.sdk.util.a.d(context));
        String a2 = a(VERSION.RELEASE);
        String a3 = a(Build.MODEL);
        String str = Constants.ACCEPT_TIME_SEPARATOR_SERVER;
        String a4 = a(com.alipay.sdk.util.a.a(context).a());
        String a5 = a(com.alipay.sdk.util.a.b(context).p);
        String a6 = a(com.alipay.sdk.util.a.a(context).b());
        this.N = String.format("%s,%s,%s,%s,%s,%s,%s,%s,%s,-", new Object[]{format, "android", a2, a3, str, a4, a5, "gw", a6});
        this.O = Constants.ACCEPT_TIME_SEPARATOR_SERVER;
        this.P = Constants.ACCEPT_TIME_SEPARATOR_SERVER;
        this.R = Constants.ACCEPT_TIME_SEPARATOR_SERVER;
    }

    public final void a(String str, String str2, Throwable th) {
        a(str, str2, a(th));
    }

    public final void a(String str, String str2, String str3, String str4) {
        String str5 = "";
        if (!TextUtils.isEmpty(this.Q)) {
            str5 = str5 + "^";
        }
        this.Q += (str5 + String.format("%s,%s,%s,%s", new Object[]{str, str2, a(str3), str4}));
    }

    public final void a(String str, String str2, String str3) {
        a(str, str2, str3, Constants.ACCEPT_TIME_SEPARATOR_SERVER);
    }

    static String a(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        return str.replace("[", "【").replace("]", "】").replace("(", "（").replace(")", "）").replace(Constants.ACCEPT_TIME_SEPARATOR_SP, "，").replace(Constants.ACCEPT_TIME_SEPARATOR_SERVER, "=").replace("^", "~");
    }

    static String a(Throwable th) {
        if (th == null) {
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer();
        try {
            stringBuffer.append(th.getClass().getName()).append(Config.TRACE_TODAY_VISIT_SPLIT);
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

    private static String a(Context context) {
        String str = Constants.ACCEPT_TIME_SEPARATOR_SERVER;
        String str2 = Constants.ACCEPT_TIME_SEPARATOR_SERVER;
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
}
