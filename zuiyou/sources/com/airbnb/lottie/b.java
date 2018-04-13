package com.airbnb.lottie;

import java.util.List;
import org.json.JSONObject;

class b extends m<Float, Float> {

    static final class a {
        static b a(ai aiVar, Float f) {
            return new b(aiVar, f);
        }

        static b a(JSONObject jSONObject, ai aiVar) {
            return a(jSONObject, aiVar, true);
        }

        static b a(JSONObject jSONObject, ai aiVar, boolean z) {
            a a = l.a(jSONObject, z ? aiVar.h() : 1.0f, aiVar, b.a).a();
            return new b(a.a, aiVar, (Float) a.b);
        }
    }

    private static class b implements com.airbnb.lottie.k.a<Float> {
        static final b a = new b();

        public /* synthetic */ Object b(Object obj, float f) {
            return a(obj, f);
        }

        private b() {
        }

        public Float a(Object obj, float f) {
            return Float.valueOf(ae.a(obj) * f);
        }
    }

    public /* synthetic */ n b() {
        return a();
    }

    public /* synthetic */ Object d() {
        return c();
    }

    private b(ai aiVar, Float f) {
        super(aiVar, f);
    }

    private b(List<af<Float>> list, ai aiVar, Float f) {
        super(list, aiVar, f);
    }

    public ag<Float> a() {
        if (e()) {
            return new w(this.a);
        }
        return new bg(this.c);
    }

    public Float c() {
        return (Float) this.c;
    }
}
