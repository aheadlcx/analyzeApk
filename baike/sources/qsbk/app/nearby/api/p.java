package qsbk.app.nearby.api;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.http.SimpleCallBack;
import qsbk.app.model.Medal;
import qsbk.app.utils.HttpClient;
import qsbk.app.utils.ToastAndDialog;

class p implements SimpleCallBack {
    final /* synthetic */ PersonalListener a;

    p(PersonalListener personalListener) {
        this.a = personalListener;
    }

    public void onSuccess(JSONObject jSONObject) {
        try {
            int optInt = jSONObject.optInt("total");
            JSONArray optJSONArray = jSONObject.optJSONArray("medals");
            if (optJSONArray == null || optJSONArray.length() <= 0) {
                this.a.h.getPersonalMedalSucc(null, 0);
                return;
            }
            Medal[] medalArr = new Medal[optJSONArray.length()];
            for (int i = 0; i < optJSONArray.length(); i++) {
                JSONObject jSONObject2 = optJSONArray.getJSONObject(i);
                Medal medal = new Medal();
                medal.total = jSONObject2.optInt("total");
                medal.current = jSONObject2.optInt("current");
                medal.icon = jSONObject2.optString("icon");
                medal.name = jSONObject2.optString("name");
                medal.describe = jSONObject2.optString("description");
                medalArr[i] = medal;
            }
            if (optInt < medalArr.length) {
                optInt = medalArr.length;
            }
            this.a.h.getPersonalMedalSucc(medalArr, optInt);
        } catch (JSONException e) {
            ToastAndDialog.makeNegativeToast(this.a.k, "数据解析出错", Integer.valueOf(1)).show();
            this.a.h.getPersonalMedalFailed();
        } catch (Exception e2) {
            e2.printStackTrace();
            ToastAndDialog.makeNegativeToast(this.a.k, HttpClient.getLocalErrorStr(), Integer.valueOf(1)).show();
            this.a.h.getPersonalMedalFailed();
        }
    }

    public void onFailure(int i, String str) {
        this.a.h.getPersonalMedalFailed();
        if (!this.a.l) {
            ToastAndDialog.makeNegativeToast(this.a.k, str, Integer.valueOf(0)).show();
        }
    }
}
