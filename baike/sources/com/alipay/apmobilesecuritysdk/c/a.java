package com.alipay.apmobilesecuritysdk.c;

import android.content.Context;
import android.os.Build;
import com.alipay.b.a.a.e.d;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public final class a {
    public static synchronized void a(Context context, String str, String str2, String str3) {
        synchronized (a.class) {
            com.alipay.b.a.a.e.a b = b(context, str, str2, str3);
            d.a(context.getFilesDir().getAbsolutePath() + "/log/ap", new SimpleDateFormat("yyyyMMdd").format(Calendar.getInstance().getTime()) + ".log", b.toString());
        }
    }

    public static synchronized void a(String str) {
        synchronized (a.class) {
            d.a(str);
        }
    }

    public static synchronized void a(Throwable th) {
        synchronized (a.class) {
            d.a(th);
        }
    }

    private static com.alipay.b.a.a.e.a b(Context context, String str, String str2, String str3) {
        String str4 = "";
        try {
            str4 = context.getPackageName();
        } catch (Throwable th) {
        }
        return new com.alipay.b.a.a.e.a(Build.MODEL, str4, "security-sdk-token", "3.2.2-20160830", str, str2, str3);
    }
}
