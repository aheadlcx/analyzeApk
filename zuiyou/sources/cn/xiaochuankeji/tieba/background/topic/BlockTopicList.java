package cn.xiaochuankeji.tieba.background.topic;

import cn.htjyb.b.a.e;
import cn.htjyb.netlib.b;
import cn.xiaochuankeji.tieba.background.a;
import org.json.JSONObject;

public class BlockTopicList extends e<Topic> {
    protected b getHttpEngine() {
        return a.d();
    }

    protected String getQueryUrl() {
        return cn.xiaochuankeji.tieba.background.utils.d.a.b("/my/blocked_topics");
    }

    protected Topic parseItem(JSONObject jSONObject) {
        return new Topic(jSONObject);
    }

    protected void fillJSONObjectHeaderInfo(JSONObject jSONObject) {
        cn.xiaochuankeji.tieba.background.utils.d.a.a(jSONObject);
    }
}
