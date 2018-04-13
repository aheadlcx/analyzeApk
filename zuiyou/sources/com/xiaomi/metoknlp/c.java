package com.xiaomi.metoknlp;

import com.xiaomi.metoknlp.a.b;
import com.xiaomi.metoknlp.a.e;
import com.xiaomi.metoknlp.a.f;
import java.util.HashMap;
import java.util.Map;

public final class c {
    private static String a = null;

    private static String a() {
        String b = e.b();
        String c = e.c();
        String g = e.g();
        int e = e.e();
        int f = e.f();
        if (b == null || b.isEmpty() || c == null || c.isEmpty()) {
            return null;
        }
        if (a == null) {
            String a = e.a();
            if (a == null || a.isEmpty()) {
                return null;
            }
            a = e.a(a);
            if (a != null) {
                a = e.a(a);
            }
            if (a == null) {
                return null;
            }
        }
        if (e < 0 || f < 0) {
            e = 999;
            f = 99;
        }
        return String.format("%s__%s__%d__%d__%s", new Object[]{b, c, Integer.valueOf(e), Integer.valueOf(f), g});
    }

    public static String a(String str, String str2) {
        String str3 = null;
        String a = f.a();
        String a2 = a();
        if (a2 != null) {
            a = new StringBuffer(a).append("/base/profile").append("/").append("metoknlpsdk").append("/").append(str).append("/").append(a2).append("__").append(str2).toString();
            Map b = b();
            try {
                str3 = b.a(a, b);
            } catch (Exception e) {
            }
            b.clear();
        }
        return str3;
    }

    private static Map b() {
        String a = a();
        Map hashMap = new HashMap();
        hashMap.put("CCPVER", a);
        hashMap.put("CCPINF", a);
        return hashMap;
    }
}
