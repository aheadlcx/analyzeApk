package com.alibaba.mtl.appmonitor.d;

import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

public class c extends a<JSONObject> {
    private Map<String, b> n = new HashMap();

    public c(int i) {
        super(i);
    }

    public void b(JSONObject jSONObject) {
        a(jSONObject);
    }

    public Boolean a(int i, Map<String, String> map) {
        if (map == null || this.n == null) {
            return null;
        }
        for (String str : this.n.keySet()) {
            if (!((b) this.n.get(str)).b((String) map.get(str))) {
                return null;
            }
        }
        return Boolean.valueOf(a(i));
    }
}
