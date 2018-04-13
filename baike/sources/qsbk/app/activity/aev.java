package qsbk.app.activity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.exception.QiushibaikeException;
import qsbk.app.http.HttpCallBack;
import qsbk.app.model.Article;

class aev implements HttpCallBack {
    final /* synthetic */ VideoImmersionActivity a;

    aev(VideoImmersionActivity videoImmersionActivity) {
        this.a = videoImmersionActivity;
    }

    public void onSuccess(String str, JSONObject jSONObject) {
        Article article;
        this.a.hideLoading();
        JSONArray jSONArray = jSONObject.getJSONArray("items");
        Object obj = (jSONObject.optBoolean("has_more") || jSONObject.optInt("has_more", 0) != 0) ? 1 : null;
        this.a.m.loadMoreDone(true);
        int size = this.a.o.size();
        int length = jSONArray.length();
        if (this.a.o.isEmpty() || !(this.a.o.get(0) instanceof Article)) {
            article = null;
        } else {
            article = (Article) this.a.o.get(0);
        }
        for (int i = 0; i < length; i++) {
            Article article2;
            try {
                article2 = new Article(jSONArray.optJSONObject(i));
            } catch (QiushibaikeException e) {
                e.printStackTrace();
                article2 = null;
            }
            if (article2 != null) {
                if (article == null) {
                    try {
                        if (!this.a.o.contains(article2)) {
                            this.a.o.add(article2);
                        }
                    } catch (JSONException e2) {
                        e2.printStackTrace();
                        onFailure("", "数据加载失败");
                        return;
                    }
                }
                if (!(article == null || this.a.o.contains(article2) || article.id.equals(article2.id))) {
                    this.a.o.add(article2);
                }
            }
        }
        this.a.a(size);
        this.a.l = this.a.l + 1;
        this.a.q.notifyDataSetChanged();
        this.a.checkToPlay();
        if (obj == null) {
            this.a.m.setLoadMoreEnable(false);
        } else {
            this.a.m.setLoadMoreEnable(true);
        }
    }

    public void onFailure(String str, String str2) {
        this.a.m.loadMoreDone(false);
    }
}
