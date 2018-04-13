package qsbk.app.utils;

import com.umeng.commonsdk.proguard.g;
import java.util.Collection;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.core.utils.ACache;
import qsbk.app.http.SimpleCallBack;
import qsbk.app.model.CircleTopicBanner;
import qsbk.app.utils.CircleTopicManager.CallBack;

class d implements SimpleCallBack {
    final /* synthetic */ CallBack a;
    final /* synthetic */ CircleTopicManager b;

    d(CircleTopicManager circleTopicManager, CallBack callBack) {
        this.b = circleTopicManager;
        this.a = callBack;
    }

    public void onSuccess(JSONObject jSONObject) {
        try {
            Collection parseTopics = CircleTopicManager.parseTopics(jSONObject);
            JSONArray optJSONArray = jSONObject.optJSONArray("banner");
            if (optJSONArray != null && optJSONArray.length() > 0) {
                CircleTopicManager.banners.clear();
                CircleTopicManager.banners.addAll(CircleTopicBanner.parseJsonArray(optJSONArray));
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("banner", optJSONArray);
                CircleTopicManager.b(CircleTopicManager.b(), jSONObject2);
            }
            CircleTopicManager.saveTopicsToCache(3, parseTopics, jSONObject.optInt("updated_at", (int) (System.currentTimeMillis() / 1000)) + jSONObject.optInt(g.az, ACache.TIME_DAY));
            this.a.onSuccess(parseTopics);
        } catch (JSONException e) {
            e.printStackTrace();
            this.a.onFailure(-1, "数据加载失败");
        } finally {
            this.b.c = false;
        }
    }

    public void onFailure(int i, String str) {
        this.a.onFailure(i, str);
        this.b.c = false;
    }
}
