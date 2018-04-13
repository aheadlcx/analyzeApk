package qsbk.app.fragments;

import android.support.v4.app.NotificationCompat;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.http.HttpCallBack;
import qsbk.app.model.QiushiTopicBanner;

class jk implements HttpCallBack {
    final /* synthetic */ QiushiTopicTabFragment a;

    jk(QiushiTopicTabFragment qiushiTopicTabFragment) {
        this.a = qiushiTopicTabFragment;
    }

    public void onSuccess(String str, JSONObject jSONObject) {
        try {
            if (jSONObject.getInt(NotificationCompat.CATEGORY_ERROR) == 0) {
                JSONArray optJSONArray = jSONObject.optJSONArray("banners");
                this.a.S.clear();
                this.a.S.addAll(QiushiTopicBanner.parseJsonArray(optJSONArray));
                this.a.H();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void onFailure(String str, String str2) {
    }
}
