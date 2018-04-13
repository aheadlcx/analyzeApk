package qsbk.app.activity;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import org.json.JSONArray;
import org.json.JSONObject;
import qsbk.app.http.HttpCallBack;

class ej implements HttpCallBack {
    final /* synthetic */ Calendar a;
    final /* synthetic */ CheckInActivity b;

    ej(CheckInActivity checkInActivity, Calendar calendar) {
        this.b = checkInActivity;
        this.a = calendar;
    }

    public void onSuccess(String str, JSONObject jSONObject) {
        if (!this.b.isFinishing()) {
            JSONArray optJSONArray = jSONObject.optJSONArray("signs");
            Collection arrayList = new ArrayList();
            if (optJSONArray != null && optJSONArray.length() > 0) {
                for (int i = 0; i < optJSONArray.length(); i++) {
                    try {
                        arrayList.add(this.b.b.format(this.b.b.parse(optJSONArray.optString(i))));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
                this.b.g.addAll(arrayList);
                this.b.G.onUpdate();
            }
            this.b.f.add(this.b.c.format(this.a.getTime()));
        }
    }

    public void onFailure(String str, String str2) {
    }
}
