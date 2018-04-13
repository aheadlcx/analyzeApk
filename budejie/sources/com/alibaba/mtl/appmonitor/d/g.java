package com.alibaba.mtl.appmonitor.d;

import com.alibaba.mtl.appmonitor.a.f;
import com.alibaba.mtl.appmonitor.f.b;
import com.alibaba.mtl.log.e.i;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

class g extends a<JSONObject> {
    private f e;
    protected Map<String, h> o;
    protected int q = -1;

    public g(f fVar, int i) {
        super(i);
        this.e = fVar;
        this.o = Collections.synchronizedMap(new HashMap());
    }

    public boolean a(int i, String str, String str2, Map<String, String> map) {
        if (this.o != null) {
            h hVar = (h) this.o.get(str);
            if (hVar != null) {
                return hVar.a(i, str2, map);
            }
        }
        if (i < this.n) {
            return true;
        }
        return false;
    }

    public void b(JSONObject jSONObject) {
        a(jSONObject);
        c(jSONObject);
        this.o.clear();
        try {
            JSONArray optJSONArray = jSONObject.optJSONArray("metrics");
            if (optJSONArray != null) {
                for (int i = 0; i < optJSONArray.length(); i++) {
                    JSONObject jSONObject2 = optJSONArray.getJSONObject(i);
                    String optString = jSONObject2.optString("module");
                    if (b.c(optString)) {
                        h hVar = (h) this.o.get(optString);
                        if (hVar == null) {
                            hVar = new h(optString, this.n);
                            this.o.put(optString, hVar);
                        }
                        hVar.b(jSONObject2);
                    }
                }
            }
        } catch (Exception e) {
        }
    }

    protected void c(JSONObject jSONObject) {
        i.a("EventTypeSampling", "[updateEventTypeTriggerCount]", this, jSONObject);
        if (jSONObject != null) {
            try {
                int optInt = jSONObject.optInt("cacheCount");
                if (optInt > 0 && this.e != null) {
                    this.e.b(optInt);
                }
            } catch (Throwable th) {
                i.a("EventTypeSampling", "updateTriggerCount", th);
            }
        }
    }

    public void setSampling(int i) {
        this.n = i;
    }
}
