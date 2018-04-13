package qsbk.app.fragments;

import android.support.v4.app.NotificationCompat;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.http.HttpCallBack;
import qsbk.app.model.CircleTopicBanner;

class bb implements HttpCallBack {
    final /* synthetic */ CircleTopicsFragment a;

    bb(CircleTopicsFragment circleTopicsFragment) {
        this.a = circleTopicsFragment;
    }

    public void onSuccess(String str, JSONObject jSONObject) {
        try {
            if (jSONObject.getInt(NotificationCompat.CATEGORY_ERROR) == 0) {
                JSONArray optJSONArray = jSONObject.optJSONArray("banners");
                CircleTopicsFragment.r(this.a).clear();
                CircleTopicsFragment.r(this.a).addAll(CircleTopicBanner.parseJsonArray(optJSONArray));
                CircleTopicsFragment.d(this.a, true);
                CircleTopicsFragment.i(this.a);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void onFailure(String str, String str2) {
    }
}
