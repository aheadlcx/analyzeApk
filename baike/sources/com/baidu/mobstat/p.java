package com.baidu.mobstat;

import org.json.JSONObject;

class p {
    private String a;
    private String b;
    private String c;
    private String d;

    public p(String str, String str2, String str3, String str4) {
        if (str == null) {
            str = "";
        }
        if (str2 == null) {
            str2 = "";
        }
        if (str3 == null) {
            str3 = "";
        }
        if (str4 == null) {
            str4 = "";
        }
        this.a = str;
        this.b = str2;
        this.c = str3;
        this.d = str4;
    }

    public JSONObject a() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("n", this.a);
            jSONObject.put("v", this.b);
            jSONObject.put("c", this.c);
            jSONObject.put("a", this.d);
            return jSONObject;
        } catch (Throwable e) {
            bd.b(e);
            return null;
        }
    }
}
