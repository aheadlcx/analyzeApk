package com.umeng.update.net;

import org.json.JSONObject;
import u.upd.h;

public class i extends h {
    public a a;

    public enum a {
        SUCCESS,
        FAIL
    }

    public i(JSONObject jSONObject) {
        super(jSONObject);
        if ("ok".equalsIgnoreCase(jSONObject.optString("status")) || "ok".equalsIgnoreCase(jSONObject.optString("success"))) {
            this.a = a.SUCCESS;
        } else {
            this.a = a.FAIL;
        }
    }
}
