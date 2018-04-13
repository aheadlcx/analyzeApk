package com.alibaba.mtl.appmonitor.d;

import com.alibaba.mtl.appmonitor.f.b;
import com.alibaba.mtl.log.e.i;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

public class f extends g {
    String TAG = "AlarmSampling";
    private int o = 0;
    private int p = 0;

    public /* bridge */ /* synthetic */ boolean a(int i, String str, String str2, Map map) {
        return super.a(i, str, str2, map);
    }

    public f(com.alibaba.mtl.appmonitor.a.f fVar, int i) {
        super(fVar, i);
        this.o = i;
        this.p = i;
    }

    public boolean a(int i, String str, String str2, Boolean bool, Map<String, String> map) {
        boolean z = true;
        i.a(this.TAG, "samplingSeed:", Integer.valueOf(i), "isSuccess:", bool, "successSampling:", Integer.valueOf(this.o), "failSampling:" + this.p);
        if (this.o != null) {
            h hVar = (h) this.o.get(str);
            if (hVar != null && (hVar instanceof d)) {
                return ((d) hVar).a(i, str2, bool, map);
            }
        }
        if (bool.booleanValue()) {
            return i < this.o;
        } else {
            if (i >= this.p) {
                z = false;
            }
            return z;
        }
    }

    public void b(JSONObject jSONObject) {
        a(jSONObject);
        c(jSONObject);
        this.o.clear();
        try {
            JSONArray jSONArray = jSONObject.getJSONArray("metrics");
            if (jSONArray != null) {
                for (int i = 0; i < jSONArray.length(); i++) {
                    JSONObject jSONObject2 = jSONArray.getJSONObject(i);
                    String string = jSONObject2.getString("module");
                    if (b.c(string)) {
                        h hVar = (h) this.o.get(string);
                        if (hVar == null) {
                            hVar = new d(string, this.o, this.p);
                            this.o.put(string, hVar);
                        }
                        hVar.b(jSONObject2);
                    }
                }
            }
        } catch (Exception e) {
        }
    }

    protected void a(JSONObject jSONObject) {
        super.a(jSONObject);
        this.o = this.n;
        this.p = this.n;
        try {
            Integer valueOf = Integer.valueOf(jSONObject.getInt("successSampling"));
            if (valueOf != null) {
                this.o = valueOf.intValue();
            }
            valueOf = Integer.valueOf(jSONObject.getInt("failSampling"));
            if (valueOf != null) {
                this.p = valueOf.intValue();
            }
        } catch (Exception e) {
        }
    }

    public void setSampling(int i) {
        super.setSampling(i);
        this.o = i;
        this.p = i;
    }
}
