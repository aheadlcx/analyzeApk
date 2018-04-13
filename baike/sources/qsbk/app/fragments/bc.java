package qsbk.app.fragments;

import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.http.HttpCallBack;
import qsbk.app.model.CircleTopicCategory;

class bc implements HttpCallBack {
    final /* synthetic */ CircleTopicsFragment a;

    bc(CircleTopicsFragment circleTopicsFragment) {
        this.a = circleTopicsFragment;
    }

    public void onSuccess(String str, JSONObject jSONObject) {
        try {
            if (this.a.getActivity() != null && !this.a.getActivity().isFinishing() && jSONObject.getInt(NotificationCompat.CATEGORY_ERROR) == 0) {
                CircleTopicsFragment.b(this.a, jSONObject.optString("search"));
                if (!TextUtils.isEmpty(CircleTopicsFragment.j(this.a))) {
                    CircleTopicsFragment.a(this.a).setText(this.a.getHintString(CircleTopicsFragment.j(this.a)));
                }
                CircleTopicsFragment.s(this.a).clear();
                JSONArray optJSONArray = jSONObject.optJSONArray("categories");
                if (optJSONArray != null && optJSONArray.length() > 0) {
                    CircleTopicsFragment.s(this.a).addAll(CircleTopicCategory.parseJsonArray(optJSONArray));
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void onFailure(String str, String str2) {
    }
}
