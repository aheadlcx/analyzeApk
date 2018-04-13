package com.ishumei.a;

import com.alibaba.baichuan.android.trade.constants.AlibcConstants;
import com.ishumei.d.a;
import com.ishumei.d.h;
import com.ishumei.d.j;
import com.ishumei.d.k;
import com.ishumei.d.l;
import com.ishumei.f.d;
import com.ishumei.f.e;
import java.util.HashMap;
import java.util.Map;

public class b implements d {
    private static String a;
    private static String b;
    private static String c;
    private static String d;
    private static String e;
    private static String f;
    private static String g;
    private static String h;
    private static String i;
    private static String j;
    private static String k;
    private static String l;
    private static String m;
    private static String n;
    private static b o = null;

    static {
        try {
            a = d.g("8f8d96899e9c86");
            b = d.g("8d8b868f9a");
            c = d.g("8b9e8d989a8ba08c9b94");
            d = d.g("9e9d8b929e9c");
            e = d.g("9e878f908c9a9b");
            f = d.g("9e96919990");
            g = d.g("908c");
            h = d.g("96929a96");
            i = d.g("96929a96ce");
            j = d.g("96929a96cd");
            k = d.g("9e9b969b");
            l = d.g("8c91");
            m = d.g("9e9d8b929e9c");
            n = d.g("8c92969b");
        } catch (Exception e) {
        }
    }

    public static b a() {
        if (o == null) {
            synchronized (b.class) {
                if (o == null) {
                    o = new b();
                }
            }
        }
        return o;
    }

    public Map<String, Object> a(int i) {
        Map<String, Object> hashMap = new HashMap();
        try {
            hashMap.put(b, "core");
            hashMap.put(a, (i & 1) == 1 ? "md5" : "none");
            hashMap.put(g, AlibcConstants.PF_ANDROID);
            f.a().d();
            hashMap.put(n, f.a().c());
            if ((i & 1) == 1) {
                hashMap.put(h, e.f(l.a().c()));
                hashMap.put(i, e.f(l.a().a(1)));
                hashMap.put(j, e.f(l.a().a(2)));
                hashMap.put(k, e.f(j.a().b()));
                hashMap.put(l, e.f(k.a().a(d.g("8d90d18c9a8d969e939190"))));
                hashMap.put(m, e.f(h.a().d()));
                hashMap.put(d, e.f(a.a().b()));
            } else {
                hashMap.put(h, l.a().c());
                hashMap.put(i, l.a().a(1));
                hashMap.put(j, l.a().a(2));
                hashMap.put(k, j.a().b());
                hashMap.put(l, k.a().a(d.g("8d90d18c9a8d969e939190")));
                hashMap.put(m, h.a().d());
                hashMap.put(d, a.a().b());
            }
            if ((i & 2) == 2) {
                a.a().a(hashMap, f, false);
            }
        } catch (Exception e) {
        }
        return hashMap;
    }
}
