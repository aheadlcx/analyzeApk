package com.airbnb.lottie;

import com.meizu.cloud.pushsdk.notification.model.NotifyType;
import com.meizu.cloud.pushsdk.pushtracer.constant.Parameters;
import org.json.JSONObject;

class be {
    private final b a;
    private final b b;
    private final b c;

    static class a {
        static be a(JSONObject jSONObject, ai aiVar) {
            return new be(a.a(jSONObject.optJSONObject(NotifyType.SOUND), aiVar, false), a.a(jSONObject.optJSONObject(Parameters.EVENT), aiVar, false), a.a(jSONObject.optJSONObject("o"), aiVar, false));
        }
    }

    private be(b bVar, b bVar2, b bVar3) {
        this.a = bVar;
        this.b = bVar2;
        this.c = bVar3;
    }

    b a() {
        return this.b;
    }

    b b() {
        return this.a;
    }

    b c() {
        return this.c;
    }

    public String toString() {
        return "Trim Path: {start: " + this.a + ", end: " + this.b + ", offset: " + this.c + "}";
    }
}
