package com.baidu.mobstat;

import org.json.JSONObject;

public class be {
    public boolean a = false;
    public String b = "";
    public boolean c = false;

    public be(JSONObject jSONObject) {
        try {
            this.a = jSONObject.getBoolean("SDK_BPLUS_SERVICE");
        } catch (Throwable e) {
            bd.b(e);
        }
        try {
            this.b = jSONObject.getString("SDK_PRODUCT_LY");
        } catch (Throwable e2) {
            bd.b(e2);
        }
        try {
            this.c = jSONObject.getBoolean("SDK_LOCAL_SERVER");
        } catch (Throwable e22) {
            bd.b(e22);
        }
    }

    public JSONObject a() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("SDK_BPLUS_SERVICE", this.a);
        } catch (Throwable e) {
            bd.b(e);
        }
        try {
            jSONObject.put("SDK_PRODUCT_LY", this.b);
        } catch (Throwable e2) {
            bd.b(e2);
        }
        try {
            jSONObject.put("SDK_LOCAL_SERVER", this.c);
        } catch (Throwable e22) {
            bd.b(e22);
        }
        return jSONObject;
    }
}
