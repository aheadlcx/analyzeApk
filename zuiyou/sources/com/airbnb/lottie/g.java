package com.airbnb.lottie;

import java.util.List;
import org.json.JSONObject;

class g extends m<aw, aw> {

    static final class a {
        static g a(JSONObject jSONObject, ai aiVar, boolean z) {
            a a = l.a(jSONObject, z ? aiVar.h() : 1.0f, aiVar, a.a).a();
            return new g(a.a, aiVar, (aw) a.b);
        }

        static g a(ai aiVar) {
            return new g(aiVar);
        }
    }

    public /* synthetic */ n b() {
        return a();
    }

    private g(ai aiVar) {
        super(aiVar, new aw());
    }

    private g(List<af<aw>> list, ai aiVar, aw awVar) {
        super(list, aiVar, awVar);
    }

    public ag<aw> a() {
        if (e()) {
            return new av(this.a);
        }
        return new bg(this.c);
    }
}
