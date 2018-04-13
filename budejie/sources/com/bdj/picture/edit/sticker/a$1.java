package com.bdj.picture.edit.sticker;

import android.util.Log;
import com.bdj.picture.edit.network.a;
import com.bdj.picture.edit.network.b;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class a$1 implements a {
    final /* synthetic */ a a;

    a$1(a aVar) {
        this.a = aVar;
    }

    public void a(JSONObject jSONObject, b bVar) {
        try {
            int intValue = com.bdj.picture.edit.util.b.b(jSONObject, "total_category").intValue();
            Log.e("", "ljj-->onsuccess: total_category:" + intValue);
            if (intValue <= 0) {
                a.a(this.a).a();
                a.a(this.a).a(true);
                return;
            }
            JSONObject jSONObject2 = jSONObject.getJSONObject("stickers");
            JSONArray jSONArray = jSONObject.getJSONArray("categories");
            Object arrayList = new ArrayList();
            int length = jSONArray.length();
            for (int i = 0; i < length; i++) {
                String string = jSONArray.getString(i);
                if (jSONObject2.has(string)) {
                    arrayList.add(StickerActivity.a(jSONObject2.getJSONArray(string), null));
                }
            }
            if (a.b(this.a) == 1) {
                a.c(this.a).clear();
                a.c(this.a).addAll(arrayList);
                a.d(this.a).a(arrayList);
                a.a(this.a).a();
                if (intValue <= a.c(this.a).size()) {
                    a.a(this.a).setPullLoadEnable(false);
                    return;
                }
                return;
            }
            a.c(this.a).addAll(arrayList);
            a.d(this.a).b(arrayList);
            if (intValue <= a.c(this.a).size()) {
                a.a(this.a).a(true);
                a.a(this.a).setPullLoadEnable(false);
                return;
            }
            a.a(this.a).a(false);
        } catch (JSONException e) {
            Log.e("", "ljj-->onsuccess: " + e.toString());
            e.printStackTrace();
            a.a(this.a).a();
            a.a(this.a).a(false);
        }
    }

    public void a(int i, String str, JSONObject jSONObject, b bVar) {
        a.a(this.a).a();
        a.a(this.a).a(false);
    }

    public void a(String str, b bVar) {
        a.a(this.a).a();
        a.a(this.a).a(false);
    }

    public void a(b bVar) {
    }
}
