package com.airbnb.lottie;

import android.graphics.PointF;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

class e implements z {
    private final List<an> a;
    private PointF b;

    private static class a implements com.airbnb.lottie.k.a<PointF> {
        private static final com.airbnb.lottie.k.a<PointF> a = new a();

        public /* synthetic */ Object b(Object obj, float f) {
            return a(obj, f);
        }

        private a() {
        }

        public PointF a(Object obj, float f) {
            return ae.a((JSONArray) obj, f);
        }
    }

    static z a(JSONObject jSONObject, ai aiVar) {
        if (jSONObject.has("k")) {
            return new e(jSONObject.opt("k"), aiVar);
        }
        return new i(a.a(jSONObject.optJSONObject("x"), aiVar), a.a(jSONObject.optJSONObject("y"), aiVar));
    }

    e() {
        this.a = new ArrayList();
        this.b = new PointF(0.0f, 0.0f);
    }

    e(Object obj, ai aiVar) {
        this.a = new ArrayList();
        if (a(obj)) {
            JSONArray jSONArray = (JSONArray) obj;
            int length = jSONArray.length();
            for (int i = 0; i < length; i++) {
                this.a.add(a.a(jSONArray.optJSONObject(i), aiVar, a.a));
            }
            af.a(this.a);
            return;
        }
        this.b = ae.a((JSONArray) obj, aiVar.h());
    }

    private boolean a(Object obj) {
        if (!(obj instanceof JSONArray)) {
            return false;
        }
        Object opt = ((JSONArray) obj).opt(0);
        boolean z = (opt instanceof JSONObject) && ((JSONObject) opt).has("t");
        return z;
    }

    public n<?, PointF> b() {
        if (a()) {
            return new ao(this.a);
        }
        return new bg(this.b);
    }

    public boolean a() {
        return !this.a.isEmpty();
    }

    public String toString() {
        return "initialPoint=" + this.b;
    }
}
