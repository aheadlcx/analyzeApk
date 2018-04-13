package com.airbnb.lottie;

import org.json.JSONObject;

class aa {
    private final int a;
    private final int b;
    private final String c;
    private final String d;

    static class a {
        static aa a(JSONObject jSONObject) {
            return new aa(jSONObject.optInt("w"), jSONObject.optInt("h"), jSONObject.optString("id"), jSONObject.optString("p"));
        }
    }

    private aa(int i, int i2, String str, String str2) {
        this.a = i;
        this.b = i2;
        this.c = str;
        this.d = str2;
    }

    String a() {
        return this.c;
    }

    String b() {
        return this.d;
    }
}
