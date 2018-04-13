package com.airbnb.lottie;

import android.graphics.PointF;
import android.support.annotation.FloatRange;
import com.meizu.cloud.pushsdk.notification.model.NotifyType;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

class ax {
    private final List<t> a;
    private PointF b;
    private boolean c;

    static class a implements com.airbnb.lottie.k.a<ax> {
        static final a a = new a();

        public /* synthetic */ Object b(Object obj, float f) {
            return a(obj, f);
        }

        private a() {
        }

        public ax a(Object obj, float f) {
            JSONObject jSONObject = null;
            if (obj instanceof JSONArray) {
                JSONObject jSONObject2;
                Object opt = ((JSONArray) obj).opt(0);
                if ((opt instanceof JSONObject) && ((JSONObject) opt).has(NotifyType.VIBRATE)) {
                    jSONObject2 = (JSONObject) opt;
                } else {
                    jSONObject2 = null;
                }
                jSONObject = jSONObject2;
            } else if ((obj instanceof JSONObject) && ((JSONObject) obj).has(NotifyType.VIBRATE)) {
                jSONObject = (JSONObject) obj;
            }
            if (jSONObject == null) {
                return null;
            }
            JSONArray optJSONArray = jSONObject.optJSONArray(NotifyType.VIBRATE);
            JSONArray optJSONArray2 = jSONObject.optJSONArray("i");
            JSONArray optJSONArray3 = jSONObject.optJSONArray("o");
            boolean optBoolean = jSONObject.optBoolean("c", false);
            if (optJSONArray == null || optJSONArray2 == null || optJSONArray3 == null || optJSONArray.length() != optJSONArray2.length() || optJSONArray.length() != optJSONArray3.length()) {
                throw new IllegalStateException("Unable to process points array or tangents. " + jSONObject);
            } else if (optJSONArray.length() == 0) {
                return new ax(new PointF(), false, Collections.emptyList());
            } else {
                int length = optJSONArray.length();
                PointF a = a(0, optJSONArray);
                a.x *= f;
                a.y *= f;
                List arrayList = new ArrayList(length);
                for (int i = 1; i < length; i++) {
                    PointF a2 = a(i, optJSONArray);
                    PointF a3 = a(i - 1, optJSONArray);
                    PointF a4 = a(i - 1, optJSONArray3);
                    PointF a5 = a(i, optJSONArray2);
                    a3 = al.a(a3, a4);
                    a4 = al.a(a2, a5);
                    a3.x *= f;
                    a3.y *= f;
                    a4.x *= f;
                    a4.y *= f;
                    a2.x *= f;
                    a2.y *= f;
                    arrayList.add(new t(a3, a4, a2));
                }
                if (optBoolean) {
                    PointF a6 = a(0, optJSONArray);
                    PointF a7 = a(length - 1, optJSONArray);
                    PointF a8 = a(length - 1, optJSONArray3);
                    PointF a9 = a(0, optJSONArray2);
                    a7 = al.a(a7, a8);
                    a8 = al.a(a6, a9);
                    if (f != 1.0f) {
                        a7.x *= f;
                        a7.y *= f;
                        a8.x *= f;
                        a8.y *= f;
                        a6.x *= f;
                        a6.y *= f;
                    }
                    arrayList.add(new t(a7, a8, a6));
                }
                return new ax(a, optBoolean, arrayList);
            }
        }

        private static PointF a(int i, JSONArray jSONArray) {
            if (i >= jSONArray.length()) {
                throw new IllegalArgumentException("Invalid index " + i + ". There are only " + jSONArray.length() + " points.");
            }
            JSONArray optJSONArray = jSONArray.optJSONArray(i);
            Object opt = optJSONArray.opt(0);
            Object opt2 = optJSONArray.opt(1);
            return new PointF(opt instanceof Double ? new Float(((Double) opt).doubleValue()).floatValue() : (float) ((Integer) opt).intValue(), opt2 instanceof Double ? new Float(((Double) opt2).doubleValue()).floatValue() : (float) ((Integer) opt2).intValue());
        }
    }

    private ax(PointF pointF, boolean z, List<t> list) {
        this.a = new ArrayList();
        this.b = pointF;
        this.c = z;
        this.a.addAll(list);
    }

    ax() {
        this.a = new ArrayList();
    }

    private void a(float f, float f2) {
        if (this.b == null) {
            this.b = new PointF();
        }
        this.b.set(f, f2);
    }

    PointF a() {
        return this.b;
    }

    boolean b() {
        return this.c;
    }

    List<t> c() {
        return this.a;
    }

    void a(ax axVar, ax axVar2, @FloatRange(from = 0.0d, to = 1.0d) float f) {
        if (this.b == null) {
            this.b = new PointF();
        }
        boolean z = axVar.b() || axVar2.b();
        this.c = z;
        if (this.a.isEmpty() || this.a.size() == axVar.c().size() || this.a.size() == axVar2.c().size()) {
            if (this.a.isEmpty()) {
                for (int size = axVar.c().size() - 1; size >= 0; size--) {
                    this.a.add(new t());
                }
            }
            PointF a = axVar.a();
            PointF a2 = axVar2.a();
            a(al.a(a.x, a2.x, f), al.a(a.y, a2.y, f));
            for (int size2 = this.a.size() - 1; size2 >= 0; size2--) {
                t tVar = (t) axVar.c().get(size2);
                t tVar2 = (t) axVar2.c().get(size2);
                PointF a3 = tVar.a();
                PointF b = tVar.b();
                PointF c = tVar.c();
                PointF a4 = tVar2.a();
                PointF b2 = tVar2.b();
                a2 = tVar2.c();
                ((t) this.a.get(size2)).a(al.a(a3.x, a4.x, f), al.a(a3.y, a4.y, f));
                ((t) this.a.get(size2)).b(al.a(b.x, b2.x, f), al.a(b.y, b2.y, f));
                ((t) this.a.get(size2)).c(al.a(c.x, a2.x, f), al.a(c.y, a2.y, f));
            }
            return;
        }
        throw new IllegalStateException("Curves must have the same number of control points. This: " + c().size() + "\tShape 1: " + axVar.c().size() + "\tShape 2: " + axVar2.c().size());
    }

    public String toString() {
        return "ShapeData{numCurves=" + this.a.size() + "closed=" + this.c + '}';
    }
}
