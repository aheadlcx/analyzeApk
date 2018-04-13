package com.airbnb.lottie;

import java.util.List;
import org.json.JSONObject;

class c extends m<Integer, Integer> {

    static final class a {
        static c a(ai aiVar, Integer num) {
            return new c(aiVar, num);
        }

        static c a(JSONObject jSONObject, ai aiVar, boolean z, boolean z2) {
            a a = l.a(jSONObject, z ? aiVar.h() : 1.0f, aiVar, b.a).a();
            Integer num = (Integer) a.b;
            if (z2 && a.b != null) {
                Integer valueOf = Integer.valueOf((((Integer) a.b).intValue() * 255) / 100);
                int size = a.a.size();
                for (int i = 0; i < size; i++) {
                    af afVar = (af) a.a.get(i);
                    afVar.a = Integer.valueOf((((Integer) afVar.a).intValue() * 255) / 100);
                    afVar.b = Integer.valueOf((((Integer) afVar.b).intValue() * 255) / 100);
                }
                num = valueOf;
            }
            return new c(a.a, aiVar, num);
        }
    }

    private static class b implements com.airbnb.lottie.k.a<Integer> {
        private static final b a = new b();

        public /* synthetic */ Object b(Object obj, float f) {
            return a(obj, f);
        }

        private b() {
        }

        public Integer a(Object obj, float f) {
            return Integer.valueOf(Math.round(ae.a(obj) * f));
        }
    }

    public /* synthetic */ n b() {
        return a();
    }

    public /* synthetic */ Object d() {
        return c();
    }

    private c(ai aiVar, Integer num) {
        super(aiVar, num);
    }

    private c(List<af<Integer>> list, ai aiVar, Integer num) {
        super(list, aiVar, num);
    }

    public ag<Integer> a() {
        if (e()) {
            return new ac(this.a);
        }
        return new bg(this.c);
    }

    public Integer c() {
        return (Integer) this.c;
    }
}
