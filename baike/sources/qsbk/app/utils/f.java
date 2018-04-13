package qsbk.app.utils;

import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.http.SimpleCallBack;
import qsbk.app.model.CircleTopic;
import qsbk.app.utils.CircleTopicManager.CallBack;

class f implements SimpleCallBack {
    final /* synthetic */ CallBack a;
    final /* synthetic */ CircleTopicManager b;

    f(CircleTopicManager circleTopicManager, CallBack callBack) {
        this.b = circleTopicManager;
        this.a = callBack;
    }

    public void onSuccess(JSONObject jSONObject) {
        try {
            JSONObject jSONObject2 = jSONObject.getJSONObject("production");
            if (this.b.lruTopics != null && this.b.lruTopics.size() > 0) {
                Iterator it = this.b.lruTopics.iterator();
                while (it.hasNext()) {
                    CircleTopic circleTopic = (CircleTopic) it.next();
                    JSONObject optJSONObject = jSONObject2.optJSONObject(circleTopic.id);
                    if (optJSONObject != null) {
                        try {
                            circleTopic.articleCount = optJSONObject.getInt("total");
                            circleTopic.todayCount = optJSONObject.getInt("today");
                        } catch (JSONException e) {
                        }
                    }
                }
                CircleTopicManager.notifyTopicUpdate(this.b.lruTopics);
            }
            CircleTopicManager.saveTopicsToCache(2, this.b.lruTopics, Integer.MAX_VALUE);
            this.a.onSuccess(this.b.lruTopics);
        } catch (JSONException e2) {
            e2.printStackTrace();
            this.a.onFailure(-1, "数据加载失败");
        }
    }

    public void onFailure(int i, String str) {
        this.a.onFailure(i, str);
    }
}
