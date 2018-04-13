package com.xiaomi.network;

import com.alipay.sdk.cons.c;
import com.baidu.mobstat.Config;
import java.util.Iterator;
import java.util.LinkedList;
import org.json.JSONArray;
import org.json.JSONObject;

class d implements Comparable<d> {
    String a;
    protected int b;
    private final LinkedList<AccessHistory> c;
    private long d;

    public d() {
        this(null, 0);
    }

    public d(String str, int i) {
        this.c = new LinkedList();
        this.d = 0;
        this.a = str;
        this.b = i;
    }

    public int a(d dVar) {
        return dVar == null ? 1 : dVar.b - this.b;
    }

    public synchronized d a(JSONObject jSONObject) {
        this.d = jSONObject.getLong("tt");
        this.b = jSONObject.getInt("wt");
        this.a = jSONObject.getString(c.f);
        JSONArray jSONArray = jSONObject.getJSONArray("ah");
        for (int i = 0; i < jSONArray.length(); i++) {
            this.c.add(new AccessHistory().a(jSONArray.getJSONObject(i)));
        }
        return this;
    }

    public synchronized JSONObject a() {
        JSONObject jSONObject;
        jSONObject = new JSONObject();
        jSONObject.put("tt", this.d);
        jSONObject.put("wt", this.b);
        jSONObject.put(c.f, this.a);
        JSONArray jSONArray = new JSONArray();
        Iterator it = this.c.iterator();
        while (it.hasNext()) {
            jSONArray.put(((AccessHistory) it.next()).b());
        }
        jSONObject.put("ah", jSONArray);
        return jSONObject;
    }

    protected synchronized void a(AccessHistory accessHistory) {
        if (accessHistory != null) {
            this.c.add(accessHistory);
            int a = accessHistory.a();
            if (a > 0) {
                this.b += accessHistory.a();
            } else {
                int i = 0;
                int size = this.c.size() - 1;
                while (size >= 0 && ((AccessHistory) this.c.get(size)).a() < 0) {
                    i++;
                    size--;
                }
                this.b += a * i;
            }
            if (this.c.size() > 30) {
                this.b -= ((AccessHistory) this.c.remove()).a();
            }
        }
    }

    public /* synthetic */ int compareTo(Object obj) {
        return a((d) obj);
    }

    public String toString() {
        return this.a + Config.TRACE_TODAY_VISIT_SPLIT + this.b;
    }
}
