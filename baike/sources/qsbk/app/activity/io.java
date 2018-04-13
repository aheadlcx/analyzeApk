package qsbk.app.activity;

import java.util.ArrayList;
import java.util.Collection;
import org.json.JSONObject;
import qsbk.app.http.SimpleCallBack;
import qsbk.app.model.CircleTopic;
import qsbk.app.utils.CircleTopicManager;

class io implements SimpleCallBack {
    final /* synthetic */ CircleTopicActivity a;

    io(CircleTopicActivity circleTopicActivity) {
        this.a = circleTopicActivity;
    }

    public void onSuccess(JSONObject jSONObject) {
        if (!this.a.isFinishing()) {
            this.a.a.hide();
            try {
                JSONObject optJSONObject = jSONObject.optJSONObject("topic");
                if (optJSONObject != null) {
                    CircleTopic parseJson = CircleTopic.parseJson(optJSONObject);
                    if (parseJson != null) {
                        this.a.g = parseJson;
                        Collection arrayList = new ArrayList(1);
                        arrayList.add(this.a.g);
                        CircleTopicManager.notifyTopicUpdate(arrayList);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            this.a.f();
        }
    }

    public void onFailure(int i, String str) {
        if (!this.a.isFinishing()) {
            this.a.a.show((CharSequence) "加载失败，点击重试", true);
        }
    }
}
