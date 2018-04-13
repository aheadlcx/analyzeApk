package qsbk.app.utils;

import com.umeng.commonsdk.proguard.g;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;
import qsbk.app.http.SimpleCallBack;
import qsbk.app.model.Live;
import qsbk.app.model.LiveRecommend;

final class an implements SimpleCallBack {
    final /* synthetic */ boolean a;

    an(boolean z) {
        this.a = z;
    }

    public void onSuccess(JSONObject jSONObject) {
        try {
            ArrayList arrayList = new ArrayList();
            if (jSONObject != null) {
                JSONArray optJSONArray = jSONObject.optJSONArray("lives");
                if (optJSONArray.length() != 0) {
                    for (int i = 0; i < optJSONArray.length(); i++) {
                        Live parseJson = Live.parseJson(optJSONArray.optJSONObject(i));
                        if (parseJson != null) {
                            arrayList.add(parseJson);
                        }
                    }
                    long currentTimeMillis = System.currentTimeMillis();
                    long optLong = jSONObject.optLong(g.az);
                    LiveRecommendManager.save(arrayList, currentTimeMillis, optLong);
                    LiveRecommend liveRecommend = new LiveRecommend();
                    liveRecommend.lives = arrayList;
                    liveRecommend.lastUpdateTime = currentTimeMillis;
                    liveRecommend.interval = optLong;
                    LiveRecommendManager.LIVE_RECOMMEND = liveRecommend;
                }
            }
        } catch (Exception e) {
            if (this.a) {
                LiveRecommendManager.loadFromCache(true);
            }
            e.printStackTrace();
        }
    }

    public void onFailure(int i, String str) {
        if (this.a) {
            LiveRecommendManager.loadFromCache(true);
        }
    }
}
