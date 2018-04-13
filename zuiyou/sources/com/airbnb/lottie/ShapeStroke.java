package com.airbnb.lottie;

import android.support.annotation.Nullable;
import com.meizu.cloud.pushsdk.notification.model.NotifyType;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

class ShapeStroke {
    @Nullable
    private final b a;
    private final List<b> b;
    private final a c;
    private final c d;
    private final b e;
    private final LineCapType f;
    private final LineJoinType g;

    enum LineCapType {
        Butt,
        Round,
        Unknown
    }

    enum LineJoinType {
        Miter,
        Round,
        Bevel
    }

    static class a {
        static ShapeStroke a(JSONObject jSONObject, ai aiVar) {
            List arrayList = new ArrayList();
            a a = a.a(jSONObject.optJSONObject("c"), aiVar);
            b a2 = a.a(jSONObject.optJSONObject("w"), aiVar);
            c a3 = a.a(jSONObject.optJSONObject("o"), aiVar, false, true);
            LineCapType lineCapType = LineCapType.values()[jSONObject.optInt("lc") - 1];
            LineJoinType lineJoinType = LineJoinType.values()[jSONObject.optInt("lj") - 1];
            b bVar = null;
            if (jSONObject.has("d")) {
                JSONArray optJSONArray = jSONObject.optJSONArray("d");
                b bVar2 = null;
                for (int i = 0; i < optJSONArray.length(); i++) {
                    JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                    String optString = optJSONObject.optString("n");
                    if (optString.equals("o")) {
                        bVar2 = a.a(optJSONObject.optJSONObject(NotifyType.VIBRATE), aiVar);
                    } else if (optString.equals("d") || optString.equals("g")) {
                        arrayList.add(a.a(optJSONObject.optJSONObject(NotifyType.VIBRATE), aiVar));
                    }
                }
                if (arrayList.size() == 1) {
                    arrayList.add(arrayList.get(0));
                }
                bVar = bVar2;
            }
            return new ShapeStroke(bVar, arrayList, a, a3, a2, lineCapType, lineJoinType);
        }
    }

    private ShapeStroke(@Nullable b bVar, List<b> list, a aVar, c cVar, b bVar2, LineCapType lineCapType, LineJoinType lineJoinType) {
        this.a = bVar;
        this.b = list;
        this.c = aVar;
        this.d = cVar;
        this.e = bVar2;
        this.f = lineCapType;
        this.g = lineJoinType;
    }

    a a() {
        return this.c;
    }

    c b() {
        return this.d;
    }

    b c() {
        return this.e;
    }

    List<b> d() {
        return this.b;
    }

    b e() {
        return this.a;
    }

    LineCapType f() {
        return this.f;
    }

    LineJoinType g() {
        return this.g;
    }
}
