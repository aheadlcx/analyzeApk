package com.umeng.analytics.pro;

import android.content.Context;
import com.umeng.analytics.b;
import com.umeng.commonsdk.framework.UMEnvelopeBuild;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class l {
    private final long a;

    private static class a {
        public static final l a = new l();
    }

    private l() {
        this.a = 60000;
    }

    public static l a() {
        return a.a;
    }

    public int a(Context context) {
        return Integer.valueOf(UMEnvelopeBuild.imprintProperty(context, "defcon", String.valueOf(0))).intValue();
    }

    public void a(JSONObject jSONObject, Context context) {
        int a = a(context);
        if (a == 1) {
            jSONObject.remove(b.Y);
            g.a(context).f();
        } else if (a == 2) {
            jSONObject.remove(b.Y);
            jSONObject.remove("session");
            jSONObject.remove(b.ah);
            try {
                jSONObject.put("session", c());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            g.a(context).f();
        } else if (a == 3) {
            jSONObject.remove(b.Y);
            jSONObject.remove("session");
            jSONObject.remove(b.ah);
            g.a(context).f();
        }
    }

    public void b(JSONObject jSONObject, Context context) {
        int a = a(context);
        if (a == 1) {
            jSONObject.remove(b.J);
            jSONObject.remove(b.N);
            jSONObject.remove(b.O);
            g.a(context).a(false, true);
        } else if (a == 2) {
            jSONObject.remove(b.n);
            try {
                jSONObject.put(b.n, b());
            } catch (Exception e) {
            }
            jSONObject.remove(b.J);
            jSONObject.remove(b.N);
            jSONObject.remove(b.O);
            g.a(context).a(false, true);
        } else if (a == 3) {
            jSONObject.remove(b.n);
            jSONObject.remove(b.J);
            jSONObject.remove(b.N);
            jSONObject.remove(b.O);
            g.a(context).a(false, true);
        }
    }

    public JSONObject b() {
        JSONObject jSONObject = new JSONObject();
        try {
            long currentTimeMillis = System.currentTimeMillis();
            jSONObject.put("id", o.a().d());
            jSONObject.put(b.p, currentTimeMillis);
            jSONObject.put(b.q, currentTimeMillis + 60000);
            jSONObject.put("duration", 60000);
        } catch (Throwable th) {
        }
        return jSONObject;
    }

    private JSONArray c() {
        JSONArray jSONArray = new JSONArray();
        try {
            long currentTimeMillis = System.currentTimeMillis();
            JSONObject jSONObject = new JSONObject();
            JSONObject i = b.a().i();
            if (i.length() > 0) {
                jSONObject.put(b.ab, i);
            }
            jSONObject.put(b.ad, o.a().d());
            jSONObject.put(b.ae, currentTimeMillis);
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put(b.af, o.a().d());
            jSONObject2.put(b.ag, currentTimeMillis + 60000);
            if (i.length() > 0) {
                jSONObject2.put(b.ab, i);
            }
            jSONArray.put(jSONObject).put(jSONObject2);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONArray;
    }
}
