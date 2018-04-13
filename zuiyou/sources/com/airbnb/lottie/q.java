package com.airbnb.lottie;

import android.graphics.Color;
import android.support.v4.view.ViewCompat;
import com.airbnb.lottie.k.a;
import org.json.JSONArray;

class q implements a<Integer> {
    static final q a = new q();

    q() {
    }

    public /* synthetic */ Object b(Object obj, float f) {
        return a(obj, f);
    }

    public Integer a(Object obj, float f) {
        JSONArray jSONArray = (JSONArray) obj;
        if (jSONArray.length() != 4) {
            return Integer.valueOf(ViewCompat.MEASURED_STATE_MASK);
        }
        int i = 1;
        for (int i2 = 0; i2 < jSONArray.length(); i2++) {
            if (jSONArray.optDouble(i2) > 1.0d) {
                i = 0;
            }
        }
        float f2 = i != 0 ? 255.0f : 1.0f;
        return Integer.valueOf(Color.argb((int) (jSONArray.optDouble(3) * ((double) f2)), (int) (jSONArray.optDouble(0) * ((double) f2)), (int) (jSONArray.optDouble(1) * ((double) f2)), (int) (jSONArray.optDouble(2) * ((double) f2))));
    }
}
