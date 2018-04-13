package cn.xiaochuankeji.tieba.background.topic;

import cn.htjyb.b.a.d;
import cn.htjyb.netlib.b;
import cn.xiaochuankeji.tieba.background.a;
import org.json.JSONException;
import org.json.JSONObject;

public class TopicFollowedByUserList extends d<Topic> {
    private long mMid;

    public TopicFollowedByUserList(long j) {
        this.mMid = j;
    }

    protected b getHttpEngine() {
        return a.d();
    }

    protected String getQueryUrl() {
        return cn.xiaochuankeji.tieba.background.utils.d.a.b("/user/atted_topics");
    }

    protected void fillQueryBody(JSONObject jSONObject) throws JSONException {
        super.fillQueryBody(jSONObject);
        jSONObject.put("mid", this.mMid);
    }

    protected Topic parseItem(JSONObject jSONObject) {
        return new Topic(jSONObject);
    }

    protected void fillJSONObjectHeaderInfo(JSONObject jSONObject) {
        cn.xiaochuankeji.tieba.background.utils.d.a.a(jSONObject);
    }
}
