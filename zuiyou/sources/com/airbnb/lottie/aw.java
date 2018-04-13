package com.airbnb.lottie;

import org.json.JSONArray;

class aw {
    private final float a;
    private final float b;

    static class a implements com.airbnb.lottie.k.a<aw> {
        static final a a = new a();

        public /* synthetic */ Object b(Object obj, float f) {
            return a(obj, f);
        }

        private a() {
        }

        public aw a(Object obj, float f) {
            JSONArray jSONArray = (JSONArray) obj;
            return new aw((((float) jSONArray.optDouble(0, 1.0d)) / 100.0f) * f, (((float) jSONArray.optDouble(1, 1.0d)) / 100.0f) * f);
        }
    }

    aw(float f, float f2) {
        this.a = f;
        this.b = f2;
    }

    aw() {
        this(1.0f, 1.0f);
    }

    float a() {
        return this.a;
    }

    float b() {
        return this.b;
    }

    public String toString() {
        return a() + "x" + b();
    }
}
