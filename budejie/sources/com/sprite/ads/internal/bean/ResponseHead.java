package com.sprite.ads.internal.bean;

import com.sprite.ads.internal.utils.c;
import org.json.JSONObject;

public class ResponseHead implements JsonInterface {
    public String msg;
    public int status;

    public ResponseHead(JSONObject jSONObject) {
        jsonToObject(jSONObject);
    }

    public boolean isOk() {
        return this.status == 0;
    }

    public void jsonToObject(JSONObject jSONObject) {
        this.status = c.b(jSONObject, "status");
        this.msg = c.a(jSONObject, "msg");
    }
}
