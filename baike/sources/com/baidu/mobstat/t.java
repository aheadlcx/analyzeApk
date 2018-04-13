package com.baidu.mobstat;

import org.json.JSONObject;

class t {
    private String a;
    private String b;
    private String c;

    public t(String str, String str2, String str3) {
        if (str == null) {
            str = "";
        }
        this.a = str;
        if (str2 == null) {
            str2 = "";
        }
        this.b = str2;
        if (str3 == null) {
            str3 = "";
        }
        this.c = str3;
    }

    public JSONObject a() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("n", this.a);
            jSONObject.put("v", this.b);
            jSONObject.put("w", this.c);
            return jSONObject;
        } catch (Throwable e) {
            bd.b(e);
            return null;
        }
    }

    public String b() {
        return this.a;
    }
}
