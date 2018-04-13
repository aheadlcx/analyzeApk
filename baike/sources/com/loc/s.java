package com.loc;

import android.text.TextUtils;
import com.alipay.sdk.util.h;
import cz.msebera.android.httpclient.client.config.CookieSpecs;
import java.util.HashMap;
import java.util.Map;

@ag(a = "a")
public class s {
    @ah(a = "a1", b = 6)
    private String a;
    @ah(a = "a2", b = 6)
    private String b;
    @ah(a = "a6", b = 2)
    private int c;
    @ah(a = "a3", b = 6)
    private String d;
    @ah(a = "a4", b = 6)
    private String e;
    @ah(a = "a5", b = 6)
    private String f;
    private String g;
    private String h;
    private String i;
    private String j;
    private String k;
    private String[] l;

    private s() {
        this.c = 1;
        this.l = null;
    }

    private s(s$a s_a) {
        int i = 1;
        this.c = 1;
        this.l = null;
        this.g = s$a.a(s_a);
        this.h = s$a.b(s_a);
        this.j = s$a.c(s_a);
        this.i = s$a.d(s_a);
        if (!s$a.e(s_a)) {
            i = 0;
        }
        this.c = i;
        this.k = s$a.f(s_a);
        this.l = s$a.g(s_a);
        this.b = t.b(this.h);
        this.a = t.b(this.j);
        this.d = t.b(this.i);
        this.e = t.b(a(this.l));
        this.f = t.b(this.k);
    }

    public static String a(String str) {
        Map hashMap = new HashMap();
        hashMap.put("a1", t.b(str));
        return af.a(hashMap);
    }

    private static String a(String[] strArr) {
        String str = null;
        if (strArr != null) {
            try {
                StringBuilder stringBuilder = new StringBuilder();
                for (String append : strArr) {
                    stringBuilder.append(append).append(h.b);
                }
                str = stringBuilder.toString();
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
        return str;
    }

    private static String[] b(String str) {
        try {
            return str.split(h.b);
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    public static String g() {
        return "a6=1";
    }

    public final String a() {
        if (TextUtils.isEmpty(this.j) && !TextUtils.isEmpty(this.a)) {
            this.j = t.c(this.a);
        }
        return this.j;
    }

    public final void a(boolean z) {
        this.c = z ? 1 : 0;
    }

    public final String b() {
        return this.g;
    }

    public final String c() {
        if (TextUtils.isEmpty(this.h) && !TextUtils.isEmpty(this.b)) {
            this.h = t.c(this.b);
        }
        return this.h;
    }

    public final String d() {
        if (TextUtils.isEmpty(this.k) && !TextUtils.isEmpty(this.f)) {
            this.k = t.c(this.f);
        }
        if (TextUtils.isEmpty(this.k)) {
            this.k = CookieSpecs.STANDARD;
        }
        return this.k;
    }

    public final boolean e() {
        return this.c == 1;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        return hashCode() == ((s) obj).hashCode();
    }

    public final String[] f() {
        if ((this.l == null || this.l.length == 0) && !TextUtils.isEmpty(this.e)) {
            this.l = b(t.c(this.e));
        }
        return (String[]) this.l.clone();
    }

    public int hashCode() {
        v vVar = new v();
        vVar.a(this.j).a(this.g).a(this.h).a(this.l);
        return vVar.a();
    }
}
