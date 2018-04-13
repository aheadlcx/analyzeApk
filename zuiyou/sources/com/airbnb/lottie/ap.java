package com.airbnb.lottie;

import android.graphics.PointF;
import com.airbnb.lottie.k.a;
import org.json.JSONArray;
import org.json.JSONObject;

class ap implements a<PointF> {
    static final ap a = new ap();

    public /* synthetic */ Object b(Object obj, float f) {
        return a(obj, f);
    }

    private ap() {
    }

    public PointF a(Object obj, float f) {
        if (obj instanceof JSONArray) {
            return ae.a((JSONArray) obj, f);
        }
        if (obj instanceof JSONObject) {
            return ae.a((JSONObject) obj, f);
        }
        throw new IllegalArgumentException("Unable to parse point from " + obj);
    }
}
