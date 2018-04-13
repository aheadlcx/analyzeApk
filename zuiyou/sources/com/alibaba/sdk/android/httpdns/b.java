package com.alibaba.sdk.android.httpdns;

import org.json.JSONArray;
import org.json.JSONObject;

class b {
    private long a;
    /* renamed from: a */
    private String f4a;
    /* renamed from: a */
    private String[] f5a;
    private long b;

    b(String str) {
        JSONObject jSONObject = new JSONObject(str);
        this.f4a = jSONObject.getString("host");
        JSONArray jSONArray = jSONObject.getJSONArray("ips");
        int length = jSONArray.length();
        this.f5a = new String[length];
        for (int i = 0; i < length; i++) {
            this.f5a[i] = jSONArray.getString(i);
        }
        this.a = jSONObject.getLong("ttl");
        this.b = System.currentTimeMillis() / 1000;
    }

    long a() {
        return this.a;
    }

    /* renamed from: a */
    boolean m7a() {
        return b() + a() < System.currentTimeMillis() / 1000;
    }

    /* renamed from: a */
    String[] m8a() {
        return this.f5a;
    }

    long b() {
        return this.b;
    }

    public String toString() {
        String str = "host: " + this.f4a + " ip cnt: " + this.f5a.length + " ttl: " + this.a;
        for (String str2 : this.f5a) {
            str = str + "\n ip: " + str2;
        }
        return str;
    }
}
