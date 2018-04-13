package qsbk.app.activity;

import android.support.v4.app.NotificationCompat;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.http.HttpCallBack;
import qsbk.app.model.CircleTopicCategory;

class je implements HttpCallBack {
    final /* synthetic */ CircleTopicCategoriesActivity a;

    je(CircleTopicCategoriesActivity circleTopicCategoriesActivity) {
        this.a = circleTopicCategoriesActivity;
    }

    public void onSuccess(String str, JSONObject jSONObject) {
        try {
            if (jSONObject.getInt(NotificationCompat.CATEGORY_ERROR) == 0) {
                JSONArray optJSONArray = jSONObject.optJSONArray("categories");
                if (optJSONArray != null && optJSONArray.length() > 0) {
                    this.a.d = CircleTopicCategory.parseJsonArray(optJSONArray);
                    this.a.f();
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void onFailure(String str, String str2) {
    }
}
