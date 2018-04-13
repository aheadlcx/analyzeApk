package com.alipay.sdk.app.statistic;

import android.content.Context;
import android.text.TextUtils;

public final class a {
    public static final String a = "alipay_cashier_statistic_record";
    private static c b;

    public static void a(Context context) {
        if (b == null) {
            b = new c(context);
        }
    }

    public static synchronized void a(Context context, String str) {
        String str2 = null;
        synchronized (a.class) {
            if (b != null) {
                c cVar = b;
                if (TextUtils.isEmpty(cVar.Q)) {
                    str2 = "";
                } else {
                    String str3;
                    String[] split = str.split(com.alipay.sdk.sys.a.b);
                    if (split != null) {
                        str3 = null;
                        for (String split2 : split) {
                            String[] split3 = split2.split("=");
                            if (split3 != null && split3.length == 2) {
                                if (split3[0].equalsIgnoreCase(c.F)) {
                                    split3[1].replace("\"", "");
                                } else if (split3[0].equalsIgnoreCase(c.G)) {
                                    str3 = split3[1].replace("\"", "");
                                } else if (split3[0].equalsIgnoreCase(c.H)) {
                                    str2 = split3[1].replace("\"", "");
                                }
                            }
                        }
                    } else {
                        str3 = null;
                    }
                    str2 = c.a(str2);
                    String a = c.a(c.a(str3));
                    cVar.J = String.format("%s,%s,-,%s,-,-,-", new Object[]{str2, str3, a});
                    str2 = String.format("[(%s),(%s),(%s),(%s),(%s),(%s),(%s),(%s),(%s),(%s)]", new Object[]{cVar.I, cVar.J, cVar.K, cVar.L, cVar.M, cVar.N, cVar.O, cVar.P, cVar.Q, cVar.R});
                }
                new Thread(new b(context, str2)).start();
                b = null;
            }
        }
    }

    public static void a(String str, Throwable th) {
        if (b != null && th.getClass() != null) {
            b.a(str, th.getClass().getSimpleName(), th);
        }
    }

    public static void a(String str, String str2, Throwable th) {
        if (b != null) {
            b.a(str, str2, th);
        }
    }

    public static void a(String str, String str2, String str3) {
        if (b != null) {
            b.a(str, str2, str3);
        }
    }
}
