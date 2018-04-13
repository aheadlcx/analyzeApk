package qsbk.app.fragments;

import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.QsbkApp;
import qsbk.app.http.SimpleCallBack;
import qsbk.app.image.FrescoImageloader;
import qsbk.app.model.CircleArticle;
import qsbk.app.model.CircleTopic;
import qsbk.app.utils.ToastAndDialog;

class az implements SimpleCallBack {
    final /* synthetic */ int a;
    final /* synthetic */ CircleTopicWeeklyFragment b;

    az(CircleTopicWeeklyFragment circleTopicWeeklyFragment, int i) {
        this.b = circleTopicWeeklyFragment;
        this.a = i;
    }

    public void onSuccess(JSONObject jSONObject) {
        this.b.c();
        int optInt = jSONObject.optInt(NotificationCompat.CATEGORY_ERROR, -1);
        boolean optBoolean = jSONObject.optBoolean("has_more");
        if (optInt == 0) {
            JSONObject optJSONObject;
            if (this.a == 1) {
                optJSONObject = jSONObject.optJSONObject("collection");
                this.b.s.setText(optJSONObject.optString("title"));
                this.b.r.setText(optJSONObject.optString("episode"));
                this.b.p.setText(optJSONObject.optString("title"));
                this.b.u.setText(optJSONObject.optString("introduction"));
                FrescoImageloader.displayImage(this.b.t, optJSONObject.optString("pic_url"));
                this.b.a.refreshDone();
                this.b.c.clear();
                this.b.a.setLoadMoreEnable(true);
            }
            JSONArray optJSONArray = jSONObject.optJSONArray("data");
            int i = 0;
            while (i < optJSONArray.length()) {
                try {
                    optJSONObject = optJSONArray.getJSONObject(i);
                    if (optJSONObject != null) {
                        JSONObject jSONObject2 = optJSONObject.getJSONObject("topic");
                        if (jSONObject2 != null) {
                            this.b.c.add(CircleTopic.parseJson(jSONObject2));
                        }
                        CharSequence optString = optJSONObject.optString("comment");
                        if (!TextUtils.isEmpty(optString)) {
                            this.b.c.add(optString);
                        }
                        JSONArray jSONArray = optJSONObject.getJSONArray("articles");
                        if (jSONArray != null && jSONArray.length() > 0) {
                            for (optInt = 0; optInt < jSONArray.length(); optInt++) {
                                JSONObject jSONObject3 = jSONArray.getJSONObject(optInt);
                                if (jSONObject3 != null) {
                                    this.b.c.add(CircleArticle.parseAsCircleArticle(jSONObject3));
                                }
                            }
                        }
                    }
                    i++;
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            this.b.h = this.a;
            if (optBoolean) {
                this.b.a.loadMoreDone(true);
                this.b.j.setVisibility(8);
            } else {
                this.b.a.setLoadMoreEnable(false);
                this.b.j.setVisibility(0);
            }
            this.b.f.notifyDataSetChanged();
        } else {
            onFailure(-1, "数据加载失败");
        }
        this.b.i = false;
    }

    public void onFailure(int i, String str) {
        this.b.c();
        this.b.a.refreshDone();
        this.b.a.loadMoreDone(false);
        if (!(TextUtils.isEmpty(str) || this.b.getActivity() == null)) {
            ToastAndDialog.makeNegativeToast(QsbkApp.mContext, str, Integer.valueOf(0)).show();
        }
        this.b.i = false;
    }
}
