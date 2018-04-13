package com.airbnb.lottie;

import org.json.JSONObject;

class bd {
    private final String a;
    private final int b;
    private final h c;

    static class a {
        static bd a(JSONObject jSONObject, ai aiVar) {
            return new bd(jSONObject.optString("nm"), jSONObject.optInt("ind"), a.a(jSONObject.optJSONObject("ks"), aiVar));
        }
    }

    private bd(String str, int i, h hVar) {
        this.a = str;
        this.b = i;
        this.c = hVar;
    }

    h a() {
        return this.c;
    }

    public String toString() {
        return "ShapePath{name=" + this.a + ", index=" + this.b + ", hasAnimation=" + this.c.e() + '}';
    }
}
