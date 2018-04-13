package com.alibaba.mtl.appmonitor.a;

import com.alibaba.mtl.appmonitor.c.a;
import com.alibaba.mtl.appmonitor.c.b;
import com.alibaba.mtl.appmonitor.c.e;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class d implements b {
    public int e;
    public String o;
    public String p;
    public String s;

    public JSONObject a() {
        JSONObject jSONObject = (JSONObject) a.a().a(e.class, new Object[0]);
        try {
            jSONObject.put("page", this.o);
            jSONObject.put("monitorPoint", this.p);
            if (this.s != null) {
                jSONObject.put("arg", this.s);
            }
        } catch (JSONException e) {
        }
        return jSONObject;
    }

    public void clean() {
        this.e = 0;
        this.o = null;
        this.p = null;
        this.s = null;
    }

    public void fill(Object... objArr) {
        this.e = ((Integer) objArr[0]).intValue();
        this.o = (String) objArr[1];
        this.p = (String) objArr[2];
        if (objArr.length > 3 && objArr[3] != null) {
            this.s = (String) objArr[3];
        }
    }
}
