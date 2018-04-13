package qsbk.app.utils;

import org.json.JSONArray;
import org.json.JSONObject;
import qsbk.app.http.SimpleCallBack;
import qsbk.app.model.CircleArticle;

class ao implements SimpleCallBack {
    final /* synthetic */ QiushiCircleVideoManager a;

    ao(QiushiCircleVideoManager qiushiCircleVideoManager) {
        this.a = qiushiCircleVideoManager;
    }

    public void onSuccess(JSONObject jSONObject) {
        JSONArray optJSONArray = jSONObject.optJSONArray("data");
        if (optJSONArray.length() > 0) {
            int length = optJSONArray.length();
            for (int i = 0; i < length; i++) {
                Object parseJson = CircleArticle.parseJson(optJSONArray.optJSONObject(i));
                if (parseJson != null && (parseJson instanceof CircleArticle)) {
                    QiushiCircleVideoManager.add((CircleArticle) parseJson);
                }
            }
        }
    }

    public void onFailure(int i, String str) {
    }
}
