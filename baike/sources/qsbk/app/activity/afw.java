package qsbk.app.activity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.http.HttpCallBack;
import qsbk.app.model.CircleArticle;

class afw implements HttpCallBack {
    final /* synthetic */ VideoImmersionCircleActivity a;

    afw(VideoImmersionCircleActivity videoImmersionCircleActivity) {
        this.a = videoImmersionCircleActivity;
    }

    public void onSuccess(String str, JSONObject jSONObject) {
        this.a.hideLoading();
        try {
            JSONArray jSONArray = jSONObject.getJSONArray("data");
            Object obj = (jSONObject.optBoolean("has_more") || jSONObject.optInt("has_more", 0) != 0) ? 1 : null;
            this.a.k.loadMoreDone(true);
            int size = this.a.m.size();
            int length = jSONArray.length();
            for (int i = 0; i < length; i++) {
                Object parseJson = CircleArticle.parseJson(jSONArray.optJSONObject(i));
                if (!(parseJson == null || this.a.m.contains(parseJson) || !(parseJson instanceof CircleArticle) || ((CircleArticle) parseJson).video == null)) {
                    this.a.m.add(parseJson);
                }
            }
            this.a.a(size);
            this.a.j = this.a.j + 1;
            this.a.o.notifyDataSetChanged();
            this.a.checkToPlay();
            if (obj == null) {
                this.a.k.setLoadMoreEnable(false);
            } else {
                this.a.k.setLoadMoreEnable(true);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            onFailure("", "数据加载失败");
        }
    }

    public void onFailure(String str, String str2) {
        this.a.k.loadMoreDone(false);
    }
}
