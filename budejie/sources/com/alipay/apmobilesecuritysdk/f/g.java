package com.alipay.apmobilesecuritysdk.f;

import android.content.Context;
import android.content.SharedPreferences.Editor;
import com.alipay.b.a.a.a.a;
import com.alipay.b.a.a.d.c;

public final class g {
    public static synchronized String a(Context context, String str) {
        String a;
        synchronized (g.class) {
            a = c.a(context, "openapi_file_pri", "openApi" + str, "");
            if (a.a(a)) {
                a = "";
            } else {
                a = com.alipay.b.a.a.a.a.c.b(com.alipay.b.a.a.a.a.c.a(), a);
                if (a.a(a)) {
                    a = "";
                }
            }
        }
        return a;
    }

    public static synchronized void a() {
        synchronized (g.class) {
        }
    }

    public static synchronized void a(Context context) {
        synchronized (g.class) {
            Editor edit = context.getSharedPreferences("openapi_file_pri", 0).edit();
            if (edit != null) {
                edit.clear();
                edit.commit();
            }
        }
    }

    public static synchronized void a(Context context, String str, String str2) {
        synchronized (g.class) {
            try {
                Editor edit = context.getSharedPreferences("openapi_file_pri", 0).edit();
                if (edit != null) {
                    edit.putString("openApi" + str, com.alipay.b.a.a.a.a.c.a(com.alipay.b.a.a.a.a.c.a(), str2));
                    edit.commit();
                }
            } catch (Throwable th) {
            }
        }
    }
}
