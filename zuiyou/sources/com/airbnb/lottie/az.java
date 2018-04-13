package com.airbnb.lottie;

import android.support.annotation.Nullable;
import android.util.Log;
import com.meizu.cloud.pushsdk.notification.model.AdvanceSetting;
import com.meizu.cloud.pushsdk.notification.model.TimeDisplaySetting;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

class az {
    private final String a;
    private final List<Object> b;

    static class a {
        private static az b(JSONObject jSONObject, ai aiVar) {
            JSONArray optJSONArray = jSONObject.optJSONArray(AdvanceSetting.NETWORK_TYPE);
            String optString = jSONObject.optString("nm");
            List arrayList = new ArrayList();
            for (int i = 0; i < optJSONArray.length(); i++) {
                Object a = az.a(optJSONArray.optJSONObject(i), aiVar);
                if (a != null) {
                    arrayList.add(a);
                }
            }
            return new az(optString, arrayList);
        }
    }

    @Nullable
    static Object a(JSONObject jSONObject, ai aiVar) {
        String optString = jSONObject.optString("ty");
        Object obj = -1;
        switch (optString.hashCode()) {
            case 3239:
                if (optString.equals("el")) {
                    obj = 5;
                    break;
                }
                break;
            case 3270:
                if (optString.equals("fl")) {
                    obj = 2;
                    break;
                }
                break;
            case 3307:
                if (optString.equals("gr")) {
                    obj = null;
                    break;
                }
                break;
            case 3633:
                if (optString.equals("rc")) {
                    obj = 6;
                    break;
                }
                break;
            case 3669:
                if (optString.equals("sh")) {
                    obj = 4;
                    break;
                }
                break;
            case 3679:
                if (optString.equals("sr")) {
                    obj = 8;
                    break;
                }
                break;
            case 3681:
                if (optString.equals(TimeDisplaySetting.START_SHOW_TIME)) {
                    obj = 1;
                    break;
                }
                break;
            case 3705:
                if (optString.equals("tm")) {
                    obj = 7;
                    break;
                }
                break;
            case 3710:
                if (optString.equals("tr")) {
                    obj = 3;
                    break;
                }
                break;
        }
        switch (obj) {
            case null:
                return a.b(jSONObject, aiVar);
            case 1:
                return a.a(jSONObject, aiVar);
            case 2:
                return a.a(jSONObject, aiVar);
            case 3:
                return a.a(jSONObject, aiVar);
            case 4:
                return a.a(jSONObject, aiVar);
            case 5:
                return a.a(jSONObject, aiVar);
            case 6:
                return a.a(jSONObject, aiVar);
            case 7:
                return a.a(jSONObject, aiVar);
            case 8:
                return a.a(jSONObject, aiVar);
            default:
                Log.w("LOTTIE", "Unknown shape type " + optString);
                return null;
        }
    }

    private az(String str, List<Object> list) {
        this.a = str;
        this.b = list;
    }

    List<Object> a() {
        return this.b;
    }

    public String toString() {
        return "ShapeGroup{name='" + this.a + "' Shapes: " + Arrays.toString(this.b.toArray()) + '}';
    }
}
