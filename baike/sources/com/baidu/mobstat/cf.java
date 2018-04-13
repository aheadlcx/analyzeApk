package com.baidu.mobstat;

import android.text.TextUtils;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class cf {
    private volatile long a = 0;
    private volatile long b = 0;
    private volatile long c = 0;
    private volatile long d = 0;
    private volatile long e = 0;
    private volatile int f = 0;
    private List<cg> g = new ArrayList();

    public cf() {
        long currentTimeMillis = System.currentTimeMillis();
        this.a = currentTimeMillis;
        this.e = currentTimeMillis;
    }

    public void a() {
        long currentTimeMillis = System.currentTimeMillis();
        c(currentTimeMillis);
        this.b = 0;
        this.c = 0;
        this.d = 0;
        this.e = currentTimeMillis;
        this.f = 0;
        this.f = 0;
        this.g.clear();
    }

    public void a(long j) {
        this.c = j;
    }

    public void b(long j) {
        this.d = j;
    }

    public void a(int i) {
        this.f = i;
    }

    public void a(cg cgVar) {
        a(this.g, cgVar);
    }

    private void a(List<cg> list, cg cgVar) {
        if (list != null && cgVar != null) {
            int size = list.size();
            if (size == 0) {
                list.add(cgVar);
                return;
            }
            cg cgVar2 = (cg) list.get(size - 1);
            if (TextUtils.isEmpty(cgVar2.a) || TextUtils.isEmpty(cgVar.a)) {
                list.add(cgVar);
            } else if (!cgVar2.a.equals(cgVar.a) || cgVar2.f == cgVar.f) {
                list.add(cgVar);
            } else if (cgVar2.f) {
                cgVar2.a(cgVar);
            }
        }
    }

    public void c(long j) {
        this.a = j;
    }

    public long b() {
        return this.a;
    }

    public long c() {
        return this.b;
    }

    public void d(long j) {
        this.b = j;
    }

    public JSONObject d() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("s", this.a);
            jSONObject.put(Config.SESSTION_END_TIME, this.b);
            jSONObject.put("i", this.e);
            jSONObject.put("c", 1);
            jSONObject.put(Config.SESSTION_TRACK_START_TIME, this.c);
            jSONObject.put(Config.SESSTION_TRACK_END_TIME, this.d);
            jSONObject.put(Config.SESSTION_TRIGGER_CATEGORY, this.f);
            JSONArray jSONArray = new JSONArray();
            for (int i = 0; i < this.g.size(); i++) {
                jSONArray.put(a((cg) this.g.get(i), this.a));
            }
            jSONObject.put("p", jSONArray);
        } catch (JSONException e) {
            db.a("StatSession.constructJSONObject() failed");
        }
        return jSONObject;
    }

    public static JSONObject a(cg cgVar, long j) {
        long j2 = 0;
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("n", cgVar.a());
            jSONObject.put("d", cgVar.c());
            long d = cgVar.d() - j;
            String str = "ps";
            if (d >= 0) {
                j2 = d;
            }
            jSONObject.put(str, j2);
            jSONObject.put("t", cgVar.b());
            jSONObject.put("at", cgVar.f() ? 1 : 0);
            JSONObject e = cgVar.e();
            if (!(e == null || e.length() == 0)) {
                jSONObject.put("ext", e);
            }
        } catch (Throwable e2) {
            db.b(e2);
        }
        return jSONObject;
    }
}
