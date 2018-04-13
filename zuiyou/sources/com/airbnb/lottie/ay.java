package com.airbnb.lottie;

import android.support.annotation.Nullable;
import org.json.JSONObject;

class ay {
    private final boolean a;
    @Nullable
    private final a b;
    @Nullable
    private final c c;

    static class a {
        static ay a(JSONObject jSONObject, ai aiVar) {
            a a;
            c a2;
            JSONObject optJSONObject = jSONObject.optJSONObject("c");
            if (optJSONObject != null) {
                a = a.a(optJSONObject, aiVar);
            } else {
                a = null;
            }
            JSONObject optJSONObject2 = jSONObject.optJSONObject("o");
            if (optJSONObject2 != null) {
                a2 = a.a(optJSONObject2, aiVar, false, true);
            } else {
                a2 = null;
            }
            return new ay(jSONObject.optBoolean("fillEnabled"), a, a2);
        }
    }

    private ay(boolean z, @Nullable a aVar, @Nullable c cVar) {
        this.a = z;
        this.b = aVar;
        this.c = cVar;
    }

    @Nullable
    public a a() {
        return this.b;
    }

    @Nullable
    public c b() {
        return this.c;
    }

    public String toString() {
        return "ShapeFill{color=" + Integer.toHexString(((Integer) this.b.d()).intValue()) + ", fillEnabled=" + this.a + ", opacity=" + this.c.c() + '}';
    }
}
