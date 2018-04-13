package com.alibaba.mtl.appmonitor.a;

import org.json.JSONObject;

public class b extends d {
    public int count;
    public double e;

    public synchronized void a(double d) {
        this.e += d;
        this.count++;
    }

    public synchronized JSONObject a() {
        JSONObject a;
        a = super.a();
        try {
            a.put("count", this.count);
            a.put("value", this.e);
        } catch (Exception e) {
        }
        return a;
    }

    public synchronized void fill(Object... objArr) {
        super.fill(objArr);
        this.e = 0.0d;
        this.count = 0;
    }
}
