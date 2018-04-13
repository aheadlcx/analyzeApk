package com.airbnb.lottie;

import android.graphics.PointF;
import android.support.annotation.FloatRange;
import android.support.annotation.Nullable;
import android.support.v4.view.animation.PathInterpolatorCompat;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import com.meizu.cloud.pushsdk.notification.model.NotifyType;
import com.meizu.cloud.pushsdk.pushtracer.constant.Parameters;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class af<T> {
    private static final Interpolator f = new LinearInterpolator();
    @Nullable
    T a;
    @Nullable
    T b;
    @Nullable
    final Interpolator c;
    final float d;
    @Nullable
    Float e;
    private final ai g;

    static class a {
        static <T> af<T> a(JSONObject jSONObject, ai aiVar, float f, com.airbnb.lottie.k.a<T> aVar) {
            Object obj;
            Interpolator interpolator;
            Object obj2;
            int i = 1;
            float f2 = 0.0f;
            if (jSONObject.has("t")) {
                PointF pointF;
                PointF pointF2;
                Interpolator d;
                f2 = (float) jSONObject.optDouble("t", 0.0d);
                Object opt = jSONObject.opt(NotifyType.SOUND);
                if (opt != null) {
                    opt = aVar.b(opt, f);
                } else {
                    opt = null;
                }
                Object opt2 = jSONObject.opt(Parameters.EVENT);
                if (opt2 != null) {
                    opt2 = aVar.b(opt2, f);
                } else {
                    opt2 = null;
                }
                JSONObject optJSONObject = jSONObject.optJSONObject("o");
                JSONObject optJSONObject2 = jSONObject.optJSONObject("i");
                if (optJSONObject == null || optJSONObject2 == null) {
                    pointF = null;
                    pointF2 = null;
                } else {
                    pointF2 = ae.a(optJSONObject, f);
                    pointF = ae.a(optJSONObject2, f);
                }
                if (jSONObject.optInt("h", 0) != 1) {
                    i = 0;
                }
                if (i != 0) {
                    d = af.f;
                    obj = opt;
                } else if (pointF2 != null) {
                    Interpolator create = PathInterpolatorCompat.create(pointF2.x / f, pointF2.y / f, pointF.x / f, pointF.y / f);
                    obj = opt2;
                    d = create;
                } else {
                    obj = opt2;
                    d = af.f;
                }
                interpolator = d;
                obj2 = obj;
                obj = opt;
            } else {
                obj2 = aVar.b(jSONObject, f);
                interpolator = null;
                obj = obj2;
            }
            return new af(aiVar, obj, obj2, interpolator, f2, null);
        }

        static <T> List<af<T>> a(JSONArray jSONArray, ai aiVar, float f, com.airbnb.lottie.k.a<T> aVar) {
            int length = jSONArray.length();
            if (length == 0) {
                return Collections.emptyList();
            }
            List arrayList = new ArrayList();
            for (int i = 0; i < length; i++) {
                arrayList.add(a(jSONArray.optJSONObject(i), aiVar, f, (com.airbnb.lottie.k.a) aVar));
            }
            af.a(arrayList);
            return arrayList;
        }
    }

    static void a(List<? extends af<?>> list) {
        int size = list.size();
        for (int i = 0; i < size - 1; i++) {
            ((af) list.get(i)).e = Float.valueOf(((af) list.get(i + 1)).d);
        }
        af afVar = (af) list.get(size - 1);
        if (afVar.a == null) {
            list.remove(afVar);
        }
    }

    public af(ai aiVar, @Nullable T t, @Nullable T t2, @Nullable Interpolator interpolator, float f, @Nullable Float f2) {
        this.g = aiVar;
        this.a = t;
        this.b = t2;
        this.c = interpolator;
        this.d = f;
        this.e = f2;
    }

    @FloatRange(from = 0.0d, to = 1.0d)
    float a() {
        return this.d / this.g.g();
    }

    @FloatRange(from = 0.0d, to = 1.0d)
    float b() {
        return this.e == null ? 1.0f : this.e.floatValue() / this.g.g();
    }

    boolean c() {
        return this.c == null;
    }

    boolean a(@FloatRange(from = 0.0d, to = 1.0d) float f) {
        return f >= a() && f <= b();
    }

    public String toString() {
        return "Keyframe{startValue=" + this.a + ", endValue=" + this.b + ", startFrame=" + this.d + ", endFrame=" + this.e + ", interpolator=" + this.c + '}';
    }
}
