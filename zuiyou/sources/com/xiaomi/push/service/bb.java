package com.xiaomi.push.service;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import java.util.HashMap;
import java.util.Map;

public class bb {
    private static volatile bb a;
    private Context b;
    private Handler c = new Handler(Looper.getMainLooper());
    private Map<String, Map<String, String>> d = new HashMap();

    private bb(Context context) {
        this.b = context;
    }

    public static bb a(Context context) {
        if (a == null) {
            synchronized (bb.class) {
                if (a == null) {
                    a = new bb(context);
                }
            }
        }
        return a;
    }

    private synchronized String a(String str, String str2) {
        String str3;
        if (this.d == null || TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            str3 = "";
        } else {
            try {
                Map map = (Map) this.d.get(str);
                str3 = map != null ? (String) map.get(str2) : "";
            } catch (Throwable th) {
                str3 = "";
            }
        }
        return str3;
    }

    private synchronized void c(String str, String str2, String str3) {
        if (this.d == null) {
            this.d = new HashMap();
        }
        Map map = (Map) this.d.get(str);
        if (map == null) {
            map = new HashMap();
        }
        map.put(str2, str3);
        this.d.put(str, map);
    }

    public synchronized void a(String str, String str2, Boolean bool) {
        c(str, str2, String.valueOf(bool));
        this.c.post(new bc(this, str, str2, bool));
    }

    public synchronized void a(String str, String str2, String str3) {
        c(str, str2, str3);
        this.c.post(new bd(this, str, str2, str3));
    }

    public synchronized boolean a(String str, String str2, boolean z) {
        try {
            Object a = a(str, str2);
            z = !TextUtils.isEmpty(a) ? Boolean.parseBoolean(a) : this.b.getSharedPreferences(str, 4).getBoolean(str2, z);
        } catch (Throwable th) {
        }
        return z;
    }

    public synchronized String b(String str, String str2, String str3) {
        String a;
        a = a(str, str2);
        if (TextUtils.isEmpty(a)) {
            a = this.b.getSharedPreferences(str, 4).getString(str2, str3);
        }
        return a;
    }
}
