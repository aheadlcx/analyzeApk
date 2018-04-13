package com.airbnb.lottie;

import com.meizu.cloud.pushsdk.notification.model.NotifyType;
import org.json.JSONObject;

class p {
    private final z a;
    private final f b;

    static class a {
        static p a(JSONObject jSONObject, ai aiVar) {
            return new p(e.a(jSONObject.optJSONObject("p"), aiVar), a.a(jSONObject.optJSONObject(NotifyType.SOUND), aiVar));
        }
    }

    private p(z zVar, f fVar) {
        this.a = zVar;
        this.b = fVar;
    }

    public z a() {
        return this.a;
    }

    public f b() {
        return this.b;
    }
}
