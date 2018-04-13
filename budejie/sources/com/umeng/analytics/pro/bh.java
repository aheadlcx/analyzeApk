package com.umeng.analytics.pro;

import android.content.Context;
import com.umeng.analytics.pro.af.a;
import org.json.JSONObject;

public class bh implements az {
    private static final int a = 0;
    private static final int b = 1;
    private static final int c = 2;
    private static final int d = 3;
    private static final long e = 14400000;
    private static final long f = 28800000;
    private static final long g = 86400000;
    private static bh j = null;
    private int h = 0;
    private final long i = 60000;

    public static synchronized bh a(Context context) {
        bh bhVar;
        synchronized (bh.class) {
            if (j == null) {
                j = new bh();
                j.a(af.a(context).b().a(0));
            }
            bhVar = j;
        }
        return bhVar;
    }

    private bh() {
    }

    public void a(JSONObject jSONObject, Context context) {
        if (this.h == 1) {
            jSONObject.remove(x.aF);
            jSONObject.remove(x.aJ);
            jSONObject.remove(x.aK);
            jSONObject.remove(x.au);
            w.a(context).a(false, true);
            m.a(context).b(new f());
        } else if (this.h == 2) {
            jSONObject.remove(x.U);
            try {
                jSONObject.put(x.U, a());
            } catch (Exception e) {
            }
            jSONObject.remove(x.aF);
            jSONObject.remove(x.aJ);
            jSONObject.remove(x.aK);
            jSONObject.remove(x.au);
            w.a(context).a(false, true);
            m.a(context).b(new f());
        } else if (this.h == 3) {
            jSONObject.remove(x.U);
            jSONObject.remove(x.aF);
            jSONObject.remove(x.aJ);
            jSONObject.remove(x.aK);
            jSONObject.remove(x.au);
            w.a(context).a(false, true);
            m.a(context).b(new f());
        }
    }

    public JSONObject a() {
        JSONObject jSONObject = new JSONObject();
        try {
            long currentTimeMillis = System.currentTimeMillis();
            jSONObject.put("id", bd.a());
            jSONObject.put(x.W, currentTimeMillis);
            jSONObject.put(x.X, currentTimeMillis + 60000);
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

    public void a(a aVar) {
        a(aVar.a(0));
    }
}
