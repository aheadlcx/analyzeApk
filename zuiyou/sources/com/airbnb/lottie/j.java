package com.airbnb.lottie;

import com.alibaba.sdk.android.oss.common.RequestParameters;
import com.meizu.cloud.pushsdk.notification.model.NotifyType;
import org.json.JSONObject;

class j {
    private final e a;
    private final z b;
    private final g c;
    private final b d;
    private final c e;

    static class a {
        static j a(ai aiVar) {
            return new j(new e(), new e(), a.a(aiVar), a.a(aiVar, Float.valueOf(0.0f)), a.a(aiVar, Integer.valueOf(255)));
        }

        static j a(JSONObject jSONObject, ai aiVar) {
            e eVar;
            z a;
            g a2;
            b a3;
            c a4;
            JSONObject optJSONObject = jSONObject.optJSONObject("a");
            if (optJSONObject != null) {
                eVar = new e(optJSONObject.opt("k"), aiVar);
            } else {
                a("anchor");
                eVar = null;
            }
            optJSONObject = jSONObject.optJSONObject("p");
            if (optJSONObject != null) {
                a = e.a(optJSONObject, aiVar);
            } else {
                a(RequestParameters.POSITION);
                a = null;
            }
            JSONObject optJSONObject2 = jSONObject.optJSONObject(NotifyType.SOUND);
            if (optJSONObject2 != null) {
                a2 = a.a(optJSONObject2, aiVar, false);
            } else {
                a("scale");
                a2 = null;
            }
            JSONObject optJSONObject3 = jSONObject.optJSONObject("r");
            if (optJSONObject3 == null) {
                optJSONObject3 = jSONObject.optJSONObject("rz");
            }
            if (optJSONObject3 != null) {
                a3 = a.a(optJSONObject3, aiVar, false);
            } else {
                a("rotation");
                a3 = null;
            }
            JSONObject optJSONObject4 = jSONObject.optJSONObject("o");
            if (optJSONObject4 != null) {
                a4 = a.a(optJSONObject4, aiVar, false, true);
            } else {
                a("opacity");
                a4 = null;
            }
            return new j(eVar, a, a2, a3, a4);
        }

        private static void a(String str) {
            throw new IllegalArgumentException("Missing transform for " + str);
        }
    }

    j(e eVar, z zVar, g gVar, b bVar, c cVar) {
        this.a = eVar;
        this.b = zVar;
        this.c = gVar;
        this.d = bVar;
        this.e = cVar;
    }

    e a() {
        return this.a;
    }

    z b() {
        return this.b;
    }

    g c() {
        return this.c;
    }

    b d() {
        return this.d;
    }

    c e() {
        return this.e;
    }

    public bh f() {
        return new bh(this);
    }
}
