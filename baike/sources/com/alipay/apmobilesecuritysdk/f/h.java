package com.alipay.apmobilesecuritysdk.f;

import android.content.Context;
import android.content.SharedPreferences.Editor;
import com.alipay.b.a.a.a.a.b;
import com.alipay.b.a.a.d.a;
import com.alipay.b.a.a.d.d;
import java.util.UUID;

public class h {
    private static String a = "";

    public static long a(Context context) {
        long j = 86400000;
        String a = a.a(context, "vkeyid_settings", "update_time_interval");
        if (com.alipay.b.a.a.a.a.b(a)) {
            try {
                j = Long.parseLong(a);
            } catch (Exception e) {
            }
        }
        return j;
    }

    public static void a(Context context, String str) {
        a(context, "update_time_interval", str);
    }

    public static void a(Context context, String str, long j) {
        a.a(context, "vkeyid_settings", "vkey_valid" + str, String.valueOf(j));
    }

    private static void a(Context context, String str, String str2) {
        a.a(context, "vkeyid_settings", str, str2);
    }

    public static void a(Context context, boolean z) {
        a(context, "log_switch", z ? "1" : "0");
    }

    public static String b(Context context) {
        return a.a(context, "vkeyid_settings", "last_machine_boot_time");
    }

    public static void b(Context context, String str) {
        a(context, "last_machine_boot_time", str);
    }

    public static String c(Context context) {
        return a.a(context, "vkeyid_settings", "last_apdid_env");
    }

    public static void c(Context context, String str) {
        a(context, "last_apdid_env", str);
    }

    public static void d(Context context, String str) {
        a(context, "agent_switch", str);
    }

    public static boolean d(Context context) {
        String a = a.a(context, "vkeyid_settings", "log_switch");
        return a != null && "1".equals(a);
    }

    public static String e(Context context) {
        return a.a(context, "vkeyid_settings", "dynamic_key");
    }

    public static void e(Context context, String str) {
        a(context, "dynamic_key", str);
    }

    public static String f(Context context) {
        String a;
        synchronized (h.class) {
            if (com.alipay.b.a.a.a.a.a(a)) {
                a = d.a(context, "alipay_vkey_random", "random", "");
                a = a;
                if (com.alipay.b.a.a.a.a.a(a)) {
                    a = b.a(UUID.randomUUID().toString());
                    a = "alipay_vkey_random";
                    String str = "random";
                    String str2 = a;
                    if (str2 != null) {
                        Editor edit = context.getSharedPreferences(a, 0).edit();
                        if (edit != null) {
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

    public static void f(Context context, String str) {
        a(context, "webrtc_url", str);
    }

    public static long g(Context context, String str) {
        long j = 0;
        try {
            String a = a.a(context, "vkeyid_settings", "vkey_valid" + str);
            if (!com.alipay.b.a.a.a.a.a(a)) {
                j = Long.parseLong(a);
            }
        } catch (Throwable th) {
        }
        return j;
    }
}
