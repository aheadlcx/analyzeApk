package qsbk.app.nearby.api;

import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.http.SimpleCallBack;
import qsbk.app.model.CircleTopic;
import qsbk.app.utils.HttpClient;
import qsbk.app.utils.ToastAndDialog;

class n implements SimpleCallBack {
    final /* synthetic */ PersonalListener a;

    n(PersonalListener personalListener) {
        this.a = personalListener;
    }

    public void onSuccess(JSONObject jSONObject) {
        try {
            int i = jSONObject.getInt("total");
            ArrayList arrayList = new ArrayList();
            JSONArray jSONArray = jSONObject.getJSONArray("data");
            int length = jSONArray.length();
            for (int i2 = 0; i2 < length; i2++) {
                JSONObject optJSONObject = jSONArray.optJSONObject(i2);
                if (optJSONObject != null) {
                    arrayList.add(CircleTopic.parseJson(optJSONObject));
                }
            }
            this.a.f.getPersonalTopicSucc(arrayList, i);
        } catch (JSONException e) {
            e.printStackTrace();
            if (!this.a.l) {
                ToastAndDialog.makeNegativeToast(this.a.k, "数据解析出错", Integer.valueOf(1)).show();
                this.a.l = true;
            }
            this.a.f.getPersonalTopicFailed();
        } catch (Exception e2) {
            e2.printStackTrace();
            if (!this.a.l) {
                ToastAndDialog.makeNegativeToast(this.a.k, HttpClient.getLocalErrorStr(), Integer.valueOf(1)).show();
                this.a.l = true;
            }
            this.a.f.getPersonalTopicFailed();
        }
    }

    public void onFailure(int i, String str) {
        this.a.f.getPersonalTopicFailed();
        if (!this.a.l) {
            this.a.l = true;
            ToastAndDialog.makeNegativeToast(this.a.k, str, Integer.valueOf(1)).show();
        }
    }
}
