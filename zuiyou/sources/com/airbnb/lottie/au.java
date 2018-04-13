package com.airbnb.lottie;

import com.meizu.cloud.pushsdk.notification.model.NotifyType;
import org.json.JSONObject;

class au {
    private final z a;
    private final f b;
    private final b c;

    static class a {
        static au a(JSONObject jSONObject, ai aiVar) {
            return new au(e.a(jSONObject.optJSONObject("p"), aiVar), a.a(jSONObject.optJSONObject(NotifyType.SOUND), aiVar), a.a(jSONObject.optJSONObject("r"), aiVar));
        }
    }

    private au(z zVar, f fVar, b bVar) {
        this.a = zVar;
        this.b = fVar;
        this.c = bVar;
    }

    b a() {
        return this.c;
    }

    f b() {
        return this.b;
    }

    z c() {
        return this.a;
    }

    public String toString() {
        return "RectangleShape{cornerRadius=" + this.c.c() + ", position=" + this.a + ", size=" + this.b + '}';
    }
}
