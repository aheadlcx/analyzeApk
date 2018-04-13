package com.xiaomi.network;

import android.text.TextUtils;
import com.alipay.sdk.cons.c;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONObject;

class a {
    private String a;
    private final ArrayList<Fallback> b = new ArrayList();

    public a(String str) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("the host is empty");
        }
        this.a = str;
    }

    public synchronized void addFallback(Fallback fallback) {
        int i = 0;
        while (i < this.b.size()) {
            if (((Fallback) this.b.get(i)).a(fallback)) {
                this.b.set(i, fallback);
                break;
            }
            i++;
        }
        if (i >= this.b.size()) {
            this.b.add(fallback);
        }
    }

    public synchronized a fromJSON(JSONObject jSONObject) {
        this.a = jSONObject.getString(c.f);
        JSONArray jSONArray = jSONObject.getJSONArray("fbs");
        for (int i = 0; i < jSONArray.length(); i++) {
            this.b.add(new Fallback(this.a).a(jSONArray.getJSONObject(i)));
        }
        return this;
    }

    public synchronized Fallback getFallback() {
        Fallback fallback;
        for (int size = this.b.size() - 1; size >= 0; size--) {
            fallback = (Fallback) this.b.get(size);
            if (fallback.a()) {
                HostManager.getInstance().setCurrentISP(fallback.e());
                break;
            }
        }
        fallback = null;
        return fallback;
    }

    public ArrayList<Fallback> getFallbacks() {
        return this.b;
    }

    public String getHost() {
        return this.a;
    }

    public synchronized void purge(boolean z) {
        for (int size = this.b.size() - 1; size >= 0; size--) {
            Fallback fallback = (Fallback) this.b.get(size);
            if (z) {
                if (fallback.c()) {
                    this.b.remove(size);
                }
            } else if (!fallback.b()) {
                this.b.remove(size);
            }
        }
    }

    public synchronized JSONObject toJSON() {
        JSONObject jSONObject;
        jSONObject = new JSONObject();
        jSONObject.put(c.f, this.a);
        JSONArray jSONArray = new JSONArray();
        Iterator it = this.b.iterator();
        while (it.hasNext()) {
            jSONArray.put(((Fallback) it.next()).f());
        }
        jSONObject.put("fbs", jSONArray);
        return jSONObject;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.a);
        stringBuilder.append("\n");
        Iterator it = this.b.iterator();
        while (it.hasNext()) {
            stringBuilder.append((Fallback) it.next());
        }
        return stringBuilder.toString();
    }
}
