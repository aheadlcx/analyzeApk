package com.airbnb.lottie;

import android.graphics.Path;
import android.graphics.PointF;
import android.support.annotation.Nullable;
import android.view.animation.Interpolator;
import com.meizu.cloud.pushsdk.pushtracer.constant.Parameters;
import org.json.JSONArray;
import org.json.JSONObject;

class an extends af<PointF> {
    @Nullable
    private Path f;

    static class a {
        static an a(JSONObject jSONObject, ai aiVar, com.airbnb.lottie.k.a<PointF> aVar) {
            PointF pointF;
            PointF pointF2;
            af a = a.a(jSONObject, aiVar, aiVar.h(), (com.airbnb.lottie.k.a) aVar);
            JSONArray optJSONArray = jSONObject.optJSONArray(Parameters.TASK_ID);
            JSONArray optJSONArray2 = jSONObject.optJSONArray("to");
            if (optJSONArray == null || optJSONArray2 == null) {
                pointF = null;
                pointF2 = null;
            } else {
                PointF a2 = ae.a(optJSONArray2, aiVar.h());
                pointF = ae.a(optJSONArray, aiVar.h());
                pointF2 = a2;
            }
            an anVar = new an(aiVar, (PointF) a.a, (PointF) a.b, a.c, a.d, a.e);
            if (!(a.b == null || ((PointF) a.a).equals(a.b))) {
                anVar.f = bi.a((PointF) a.a, (PointF) a.b, pointF2, pointF);
            }
            return anVar;
        }
    }

    private an(ai aiVar, @Nullable PointF pointF, @Nullable PointF pointF2, @Nullable Interpolator interpolator, float f, @Nullable Float f2) {
        super(aiVar, pointF, pointF2, interpolator, f, f2);
    }

    @Nullable
    Path e() {
        return this.f;
    }
}
