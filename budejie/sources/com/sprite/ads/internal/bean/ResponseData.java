package com.sprite.ads.internal.bean;

import com.umeng.analytics.a;
import org.json.JSONObject;

public class ResponseData implements JsonInterface {
    public ResponseBody body;
    public ResponseHead head;
    public String respData;

    public ResponseData(String str) {
        this.respData = str;
    }

    public JSONObject getRespData() {
        return new JSONObject(this.respData);
    }

    public void jsonToObject(JSONObject jSONObject) {
    }

    public ResponseBody parseResponseBody(JSONObject jSONObject) {
        this.body = new ResponseBody(jSONObject.getJSONObject(a.z));
        return this.body;
    }

    public ResponseHead parseResponseHead(JSONObject jSONObject) {
        this.head = new ResponseHead(jSONObject.getJSONObject("head"));
        return this.head;
    }
}
