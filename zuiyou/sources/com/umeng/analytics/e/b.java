package com.umeng.analytics.e;

import android.content.Context;
import com.umeng.analytics.a.d.a;
import com.umeng.analytics.b.f;
import com.umeng.analytics.b.g;
import com.umeng.analytics.c.h;
import com.umeng.analytics.d.l;
import com.umeng.analytics.d.p;
import org.json.JSONObject;

public class b implements l {
    private static final int a = 0;
    private static final int b = 1;
    private static final int c = 2;
    private static final int d = 3;
    private static final long e = 14400000;
    private static final long f = 28800000;
    private static final long g = 86400000;
    private static b j = null;
    private int h = 0;
    private final long i = 60000;

    public static synchronized b a(Context context) {
        b bVar;
        synchronized (b.class) {
            if (j == null) {
                j = new b();
                j.a(h.a(context).b().a(0));
            }
            bVar = j;
        }
        return bVar;
    }

    private b() {
    }

    public void a(JSONObject jSONObject, Context context) {
        if (this.h == 1) {
            jSONObject.remove("error");
            jSONObject.remove(g.aJ);
            jSONObject.remove(g.aK);
            jSONObject.remove(g.au);
            f.a(context).a(false, true);
            a.a(context).b(new com.umeng.analytics.a.b.a());
        } else if (this.h == 2) {
            jSONObject.remove(g.U);
            try {
                jSONObject.put(g.U, a());
            } catch (Exception e) {
            }
            jSONObject.remove("error");
            jSONObject.remove(g.aJ);
            jSONObject.remove(g.aK);
            jSONObject.remove(g.au);
            f.a(context).a(false, true);
            a.a(context).b(new com.umeng.analytics.a.b.a());
        } else if (this.h == 3) {
            jSONObject.remove(g.U);
            jSONObject.remove("error");
            jSONObject.remove(g.aJ);
            jSONObject.remove(g.aK);
            jSONObject.remove(g.au);
            f.a(context).a(false, true);
            a.a(context).b(new com.umeng.analytics.a.b.a());
        }
    }

    public JSONObject a() {
        JSONObject jSONObject = new JSONObject();
        try {
            long currentTimeMillis = System.currentTimeMillis();
            jSONObject.put("id", p.a());
            jSONObject.put(g.W, currentTimeMillis);
            jSONObject.put(g.X, currentTimeMillis + 60000);
            jSONObject.put("duration", 60000);
        } catch (Throwable th) {
        }
        return jSONObject;
    }

    public long b() {
        switch (this.h) {
            case 1:
                return e;
            case 2:
                return f;
            case 3:
                return 86400000;
            default:
                return 0;
        }
    }

    public long c() {
        return this.h == 0 ? 0 : 300000;
    }

    public void a(int i) {
        if (i >= 0 && i <= 3) {
            this.h = i;
        }
    }

    public boolean d() {
        return this.h != 0;
    }

    public void a(h.a aVar) {
        a(aVar.a(0));
    }
}
