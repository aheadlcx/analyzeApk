package com.sina.weibo.sdk.cmd;

import com.sina.weibo.sdk.exception.WeiboException;
import org.json.JSONObject;

class c extends e {
    private String a;
    private String b;
    private String c;

    public c(String str) throws WeiboException {
        super(str);
    }

    public c(JSONObject jSONObject) {
        super(jSONObject);
    }

    public void initFromJsonObj(JSONObject jSONObject) {
        super.initFromJsonObj(jSONObject);
        this.a = jSONObject.optString("package");
        this.b = jSONObject.optString("scheme");
        this.c = jSONObject.optString("url");
    }

    public String getAppPackage() {
        return this.a;
    }

    public void setAppPackage(String str) {
        this.a = str;
    }

    public String getScheme() {
        return this.b;
    }

    public void setScheme(String str) {
        this.b = str;
    }

    public String getUrl() {
        return this.c;
    }

    public void setUrl(String str) {
        this.c = str;
    }
}
