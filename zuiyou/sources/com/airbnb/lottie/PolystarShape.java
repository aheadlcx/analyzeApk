package com.airbnb.lottie;

import com.umeng.analytics.b.g;
import org.json.JSONObject;

class PolystarShape {
    private final Type a;
    private final b b;
    private final z c;
    private final b d;
    private final b e;
    private final b f;
    private final b g;
    private final b h;

    enum Type {
        Star(1),
        Polygon(2);
        
        private final int value;

        private Type(int i) {
            this.value = i;
        }

        static Type forValue(int i) {
            for (Type type : values()) {
                if (type.value == i) {
                    return type;
                }
            }
            return null;
        }
    }

    static class a {
        static PolystarShape a(JSONObject jSONObject, ai aiVar) {
            b a;
            b a2;
            Type forValue = Type.forValue(jSONObject.optInt("sy"));
            b a3 = a.a(jSONObject.optJSONObject("pt"), aiVar, false);
            z a4 = e.a(jSONObject.optJSONObject("p"), aiVar);
            b a5 = a.a(jSONObject.optJSONObject("r"), aiVar, false);
            b a6 = a.a(jSONObject.optJSONObject("or"), aiVar);
            b a7 = a.a(jSONObject.optJSONObject(g.p), aiVar, false);
            if (forValue == Type.Star) {
                a = a.a(jSONObject.optJSONObject("ir"), aiVar);
                a2 = a.a(jSONObject.optJSONObject("is"), aiVar, false);
            } else {
                a2 = null;
                a = null;
            }
            return new PolystarShape(forValue, a3, a4, a5, a, a6, a2, a7);
        }
    }

    private PolystarShape(Type type, b bVar, z zVar, b bVar2, b bVar3, b bVar4, b bVar5, b bVar6) {
        this.a = type;
        this.b = bVar;
        this.c = zVar;
        this.d = bVar2;
        this.e = bVar3;
        this.f = bVar4;
        this.g = bVar5;
        this.h = bVar6;
    }

    Type a() {
        return this.a;
    }

    b b() {
        return this.b;
    }

    z c() {
        return this.c;
    }

    b d() {
        return this.d;
    }

    b e() {
        return this.e;
    }

    b f() {
        return this.f;
    }

    b g() {
        return this.g;
    }

    b h() {
        return this.h;
    }
}
