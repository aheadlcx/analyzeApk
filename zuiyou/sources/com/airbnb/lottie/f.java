package com.airbnb.lottie;

import android.graphics.PointF;
import java.util.List;
import org.json.JSONObject;

class f extends m<PointF, PointF> {

    static final class a {
        static f a(JSONObject jSONObject, ai aiVar) {
            a a = l.a(jSONObject, aiVar.h(), aiVar, ap.a).a();
            return new f(a.a, aiVar, (PointF) a.b);
        }
    }

    public /* synthetic */ n b() {
        return a();
    }

    private f(List<af<PointF>> list, ai aiVar, PointF pointF) {
        super(list, aiVar, pointF);
    }

    public ag<PointF> a() {
        if (e()) {
            return new aq(this.a);
        }
        return new bg(this.c);
    }
}
