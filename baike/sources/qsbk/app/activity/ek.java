package qsbk.app.activity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.http.HttpCallBack;
import qsbk.app.model.Fortune;

class ek implements HttpCallBack {
    final /* synthetic */ CheckInActivity a;

    ek(CheckInActivity checkInActivity) {
        this.a = checkInActivity;
    }

    public void onSuccess(String str, JSONObject jSONObject) {
        int i = 0;
        try {
            int i2;
            JSONArray optJSONArray = jSONObject.optJSONArray("fortune");
            if (optJSONArray != null && optJSONArray.length() > 0) {
                for (i2 = 0; i2 < optJSONArray.length(); i2++) {
                    Fortune fortune = new Fortune(optJSONArray.getJSONObject(i2));
                    if (fortune.isRateType()) {
                        this.a.m.add(fortune);
                    } else {
                        this.a.n.add(fortune);
                    }
                }
            }
            this.a.i = jSONObject.optString("astrology");
            if (jSONObject.has("yj")) {
                JSONObject optJSONObject = jSONObject.optJSONObject("yj");
                JSONArray optJSONArray2 = optJSONObject.optJSONArray("avoid");
                if (optJSONArray2 != null && optJSONArray2.length() > 0) {
                    this.a.l = new String[optJSONArray2.length()];
                    for (i2 = 0; i2 < optJSONArray2.length(); i2++) {
                        this.a.l[i2] = optJSONArray2.optString(i2);
                    }
                }
                JSONArray optJSONArray3 = optJSONObject.optJSONArray("fit");
                if (optJSONArray3 != null && optJSONArray3.length() > 0) {
                    this.a.k = new String[optJSONArray3.length()];
                    while (i < optJSONArray3.length()) {
                        this.a.k[i] = optJSONArray3.optString(i);
                        i++;
                    }
                }
            }
            this.a.j = jSONObject.optString("title");
            if (jSONObject.has("options")) {
                JSONArray optJSONArray4 = jSONObject.optJSONArray("options");
                if (optJSONArray4 != null && optJSONArray4.length() > 1) {
                    JSONObject optJSONObject2 = optJSONArray4.optJSONObject(0);
                    this.a.q = optJSONObject2.optString("name");
                    this.a.o = optJSONObject2.optBoolean("voted");
                    this.a.O = optJSONObject2.optInt("count");
                    JSONObject optJSONObject3 = optJSONArray4.optJSONObject(1);
                    this.a.r = optJSONObject3.optString("name");
                    this.a.p = optJSONObject3.optBoolean("voted");
                    this.a.P = optJSONObject3.optInt("count");
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        this.a.n();
    }

    public void onFailure(String str, String str2) {
    }
}
