package com.airbnb.lottie;

import android.support.annotation.Nullable;
import java.util.Collections;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

class l<T> {
    @Nullable
    private final JSONObject a;
    private final float b;
    private final ai c;
    private final com.airbnb.lottie.k.a<T> d;

    static class a<T> {
        final List<af<T>> a;
        @Nullable
        final T b;

        a(List<af<T>> list, @Nullable T t) {
            this.a = list;
            this.b = t;
        }
    }

    private l(@Nullable JSONObject jSONObject, float f, ai aiVar, com.airbnb.lottie.k.a<T> aVar) {
        this.a = jSONObject;
        this.b = f;
        this.c = aiVar;
        this.d = aVar;
    }

    static <T> l<T> a(@Nullable JSONObject jSONObject, float f, ai aiVar, com.airbnb.lottie.k.a<T> aVar) {
        return new l(jSONObject, f, aiVar, aVar);
    }

    a<T> a() {
        List b = b();
        return new a(b, a(b));
    }

    private List<af<T>> b() {
        if (this.a == null) {
            return Collections.emptyList();
        }
        Object opt = this.a.opt("k");
        if (a(opt)) {
            return a.a((JSONArray) opt, this.c, this.b, this.d);
        }
        return Collections.emptyList();
    }

    @Nullable
    private T a(List<af<T>> list) {
        if (this.a == null) {
            return null;
        }
        if (list.isEmpty()) {
            return this.d.b(this.a.opt("k"), this.b);
        }
        return ((af) list.get(0)).a;
    }

    private static boolean a(Object obj) {
        if (!(obj instanceof JSONArray)) {
            return false;
        }
        Object opt = ((JSONArray) obj).opt(0);
        boolean z = (opt instanceof JSONObject) && ((JSONObject) opt).has("t");
        return z;
    }
}
