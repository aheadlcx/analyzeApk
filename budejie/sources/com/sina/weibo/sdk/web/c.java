package com.sina.weibo.sdk.web;

import android.text.TextUtils;
import com.sina.weibo.sdk.auth.d;
import java.util.HashMap;
import java.util.Map;

public class c {
    private static c a;
    private Map<String, d> b = new HashMap();

    private c() {
    }

    public static synchronized c a() {
        c cVar;
        synchronized (c.class) {
            if (a == null) {
                a = new c();
            }
            cVar = a;
        }
        return cVar;
    }

    public synchronized d a(String str) {
        d dVar;
        if (TextUtils.isEmpty(str)) {
            dVar = null;
        } else {
            dVar = (d) this.b.get(str);
        }
        return dVar;
    }

    public synchronized void a(String str, d dVar) {
        if (!(TextUtils.isEmpty(str) || dVar == null)) {
            this.b.put(str, dVar);
        }
    }

    public synchronized void b(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.b.remove(str);
        }
    }

    public String b() {
        return String.valueOf(System.currentTimeMillis());
    }
}
