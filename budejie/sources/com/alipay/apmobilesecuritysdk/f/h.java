package com.alipay.apmobilesecuritysdk.f;

import android.content.Context;
import android.content.SharedPreferences.Editor;
import com.alipay.b.a.a.a.a;
import com.alipay.b.a.a.a.a.b;
import com.alipay.b.a.a.d.c;
import java.util.UUID;

public final class h {
    private static String a = "";

    public static String a(Context context) {
        try {
            String a = c.a(context, "vkeyid_settings", "last_apdid_env", "");
            if (a.a(a)) {
                return "";
            }
            a = com.alipay.b.a.a.a.a.c.b(com.alipay.b.a.a.a.a.c.a(), a);
            return a.a(a) ? "" : a;
        } catch (Throwable th) {
            return "";
        }
    }

    public static void a(Context context, String str) {
        try {
            Editor edit = context.getSharedPreferences("vkeyid_settings", 0).edit();
            if (edit != null) {
                edit.putString("last_apdid_env", com.alipay.b.a.a.a.a.c.a(com.alipay.b.a.a.a.a.c.a(), str));
                edit.commit();
            }
        } catch (Throwable th) {
        }
    }

    public static void a(Context context, String str, long j) {
        try {
            Editor edit = context.getSharedPreferences("vkeyid_settings", 0).edit();
            if (edit != null) {
                edit.putString("vkey_valid" + str, com.alipay.b.a.a.a.a.c.a(com.alipay.b.a.a.a.a.c.a(), String.valueOf(j)));
                edit.commit();
            }
        } catch (Throwable th) {
        }
    }

    public static void a(Context context, boolean z) {
        try {
            Editor edit = context.getSharedPreferences("vkeyid_settings", 0).edit();
            if (edit != null) {
                edit.putString("log_switch", z ? com.alipay.b.a.a.a.a.c.a(com.alipay.b.a.a.a.a.c.a(), "1") : com.alipay.b.a.a.a.a.c.a(com.alipay.b.a.a.a.a.c.a(), "0"));
                edit.commit();
            }
        } catch (Throwable th) {
        }
    }

    public static void b(Context context, String str) {
        try {
            Editor edit = context.getSharedPreferences("vkeyid_settings", 0).edit();
            if (edit != null) {
                edit.putString("agent_switch", com.alipay.b.a.a.a.a.c.a(com.alipay.b.a.a.a.a.c.a(), str));
                edit.commit();
            }
        } catch (Throwable th) {
        }
    }

    public static boolean b(Context context) {
        boolean z = false;
        try {
            String a = c.a(context, "vkeyid_settings", "log_switch", "");
            if (!a.a(a)) {
                a = com.alipay.b.a.a.a.a.c.b(com.alipay.b.a.a.a.a.c.a(), a);
                if (!a.a(a)) {
                    z = a.equals("1");
                }
            }
        } catch (Throwable th) {
        }
        return z;
    }

    public static long c(Context context, String str) {
        long j = 0;
        try {
            String string = context.getSharedPreferences("vkeyid_settings", 0).getString("vkey_valid" + str, "");
            if (!a.a(string)) {
                string = com.alipay.b.a.a.a.a.c.b(com.alipay.b.a.a.a.a.c.a(), string);
                if (!a.a(string)) {
                    j = Long.parseLong(string);
                }
            }
        } catch (Throwable th) {
        }
        return j;
    }

    public static synchronized String c(Context context) {
        String a;
        synchronized (h.class) {
            if (a.a(a)) {
                a = c.a(context, "alipay_vkey_random", "random", "");
                a = a;
                if (a.a(a)) {
                    a = b.a(UUID.randomUUID().toString());
                    a = "alipay_vkey_random";
                    String str = "random";
                    String str2 = a;
                    if (str2 != null) {
                        Editor edit = context.getSharedPreferences(a, 0).edit();
                        if (edit != null) {
                            edit.clear();
                            edit.putString(str, str2);
                            edit.commit();
                        }
                    }
                }
            }
            a = a;
        }
        return a;
    }
}
