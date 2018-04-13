package com.airbnb.lottie;

import android.graphics.Color;
import android.support.annotation.Nullable;
import com.meizu.cloud.pushsdk.constants.PushConstants;
import com.meizu.cloud.pushsdk.notification.model.TimeDisplaySetting;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONObject;

class Layer {
    private static final String a = Layer.class.getSimpleName();
    private final List<Object> b;
    private final ai c;
    private final String d;
    private final long e;
    private final LayerType f;
    private final long g;
    @Nullable
    private final String h;
    private final List<Mask> i;
    private final j j;
    private final int k;
    private final int l;
    private final int m;
    private final float n;
    private final float o;
    private final int p;
    private final int q;
    private final List<af<Float>> r;
    private final MatteType s;

    enum LayerType {
        PreComp,
        Solid,
        Image,
        Null,
        Shape,
        Text,
        Unknown
    }

    enum MatteType {
        None,
        Add,
        Invert,
        Unknown
    }

    static class a {
        static Layer a(JSONObject jSONObject, ai aiVar) {
            LayerType layerType;
            List list;
            String optString = jSONObject.optString("nm");
            String optString2 = jSONObject.optString("refId");
            long optLong = jSONObject.optLong("ind");
            int i = 0;
            int i2 = 0;
            int i3 = 0;
            int i4 = 0;
            int i5 = 0;
            int optInt = jSONObject.optInt("ty", -1);
            if (optInt < LayerType.Unknown.ordinal()) {
                layerType = LayerType.values()[optInt];
            } else {
                layerType = LayerType.Unknown;
            }
            long optLong2 = jSONObject.optLong("parent", -1);
            if (layerType == LayerType.Solid) {
                i = (int) (((float) jSONObject.optInt("sw")) * aiVar.h());
                i2 = (int) (((float) jSONObject.optInt("sh")) * aiVar.h());
                i3 = Color.parseColor(jSONObject.optString("sc"));
            }
            j a = a.a(jSONObject.optJSONObject("ks"), aiVar);
            MatteType matteType = MatteType.values()[jSONObject.optInt(PushConstants.PUSH_NOTIFICATION_CREATE_TIMES_TAMP)];
            ArrayList arrayList = new ArrayList();
            List arrayList2 = new ArrayList();
            List arrayList3 = new ArrayList();
            JSONArray optJSONArray = jSONObject.optJSONArray("masksProperties");
            if (optJSONArray != null) {
                for (optInt = 0; optInt < optJSONArray.length(); optInt++) {
                    arrayList2.add(a.a(optJSONArray.optJSONObject(optInt), aiVar));
                }
            }
            optJSONArray = jSONObject.optJSONArray("shapes");
            if (optJSONArray != null) {
                for (optInt = 0; optInt < optJSONArray.length(); optInt++) {
                    Object a2 = az.a(optJSONArray.optJSONObject(optInt), aiVar);
                    if (a2 != null) {
                        arrayList.add(a2);
                    }
                }
            }
            float optDouble = (float) jSONObject.optDouble("sr", 1.0d);
            float optDouble2 = ((float) jSONObject.optDouble(TimeDisplaySetting.START_SHOW_TIME)) / aiVar.g();
            if (layerType == LayerType.PreComp) {
                i4 = (int) (((float) jSONObject.optInt("w")) * aiVar.h());
                i5 = (int) (((float) jSONObject.optInt("h")) * aiVar.h());
            }
            float optLong3 = (float) jSONObject.optLong("ip");
            float optLong4 = (float) jSONObject.optLong("op");
            if (optLong3 > 0.0f) {
                list = arrayList3;
                list.add(new af(aiVar, Float.valueOf(0.0f), Float.valueOf(0.0f), null, 0.0f, Float.valueOf(optLong3)));
            }
            if (optLong4 <= 0.0f) {
                optLong4 = (float) (aiVar.c() + 1);
            }
            list = arrayList3;
            list.add(new af(aiVar, Float.valueOf(1.0f), Float.valueOf(1.0f), null, optLong3, Float.valueOf(optLong4)));
            if (optLong4 <= aiVar.g()) {
                list = arrayList3;
                list.add(new af(aiVar, Float.valueOf(0.0f), Float.valueOf(0.0f), null, optLong4, Float.valueOf((float) aiVar.c())));
            }
            return new Layer(arrayList, aiVar, optString, optLong, layerType, optLong2, optString2, arrayList2, a, i, i2, i3, optDouble, optDouble2, i4, i5, arrayList3, matteType);
        }
    }

    private Layer(List<Object> list, ai aiVar, String str, long j, LayerType layerType, long j2, @Nullable String str2, List<Mask> list2, j jVar, int i, int i2, int i3, float f, float f2, int i4, int i5, List<af<Float>> list3, MatteType matteType) {
        this.b = list;
        this.c = aiVar;
        this.d = str;
        this.e = j;
        this.f = layerType;
        this.g = j2;
        this.h = str2;
        this.i = list2;
        this.j = jVar;
        this.k = i;
        this.l = i2;
        this.m = i3;
        this.n = f;
        this.o = f2;
        this.p = i4;
        this.q = i5;
        this.r = list3;
        this.s = matteType;
    }

    float a() {
        return this.n;
    }

    List<af<Float>> b() {
        return this.r;
    }

    long c() {
        return this.e;
    }

    String d() {
        return this.d;
    }

    @Nullable
    String e() {
        return this.h;
    }

    int f() {
        return this.p;
    }

    int g() {
        return this.q;
    }

    List<Mask> h() {
        return this.i;
    }

    LayerType i() {
        return this.f;
    }

    MatteType j() {
        return this.s;
    }

    long k() {
        return this.g;
    }

    List<Object> l() {
        return this.b;
    }

    j m() {
        return this.j;
    }

    int n() {
        return this.m;
    }

    int o() {
        return this.l;
    }

    int p() {
        return this.k;
    }

    public String toString() {
        return a("");
    }

    String a(String str) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(str).append(d()).append("\n");
        Layer a = this.c.a(k());
        if (a != null) {
            stringBuilder.append("\t\tParents: ").append(a.d());
            a = this.c.a(a.k());
            while (a != null) {
                stringBuilder.append("->").append(a.d());
                a = this.c.a(a.k());
            }
            stringBuilder.append(str).append("\n");
        }
        if (!h().isEmpty()) {
            stringBuilder.append(str).append("\tMasks: ").append(h().size()).append("\n");
        }
        if (!(p() == 0 || o() == 0)) {
            stringBuilder.append(str).append("\tBackground: ").append(String.format(Locale.US, "%dx%d %X\n", new Object[]{Integer.valueOf(p()), Integer.valueOf(o()), Integer.valueOf(n())}));
        }
        if (!this.b.isEmpty()) {
            stringBuilder.append(str).append("\tShapes:\n");
            for (Object append : this.b) {
                stringBuilder.append(str).append("\t\t").append(append).append("\n");
            }
        }
        return stringBuilder.toString();
    }
}
