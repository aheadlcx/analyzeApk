package com.airbnb.lottie;

import android.graphics.Path;
import java.util.List;
import org.json.JSONObject;

class h extends m<ax, Path> {
    private final Path d;

    static final class a {
        static h a(JSONObject jSONObject, ai aiVar) {
            a a = l.a(jSONObject, aiVar.h(), aiVar, a.a).a();
            return new h(a.a, aiVar, (ax) a.b);
        }
    }

    private h(List<af<ax>> list, ai aiVar, ax axVar) {
        super(list, aiVar, axVar);
        this.d = new Path();
    }

    public n<?, Path> b() {
        if (e()) {
            return new ba(this.a);
        }
        return new bg(a((ax) this.c));
    }

    Path a(ax axVar) {
        this.d.reset();
        al.a(axVar, this.d);
        return this.d;
    }
}
