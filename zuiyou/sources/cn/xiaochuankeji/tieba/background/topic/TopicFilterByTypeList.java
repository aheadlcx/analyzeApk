package cn.xiaochuankeji.tieba.background.topic;

import cn.htjyb.b.a.d;
import cn.htjyb.netlib.b;
import cn.xiaochuankeji.tieba.background.a;
import org.json.JSONException;
import org.json.JSONObject;

public class TopicFilterByTypeList extends d<Topic> {
    private int mCategoryId;

    public TopicFilterByTypeList(int i) {
        this.mCategoryId = i;
    }

    public void setCategoryId(int i) {
        this.mCategoryId = i;
    }

    protected b getHttpEngine() {
        return a.d();
    }

    protected String getQueryUrl() {
        return cn.xiaochuankeji.tieba.background.utils.d.a.b("/cate/topics");
    }

    protected void fillQueryBody(JSONObject jSONObject) throws JSONException {
        super.fillQueryBody(jSONObject);
        com.izuiyou.a.a.b.e("category:" + this.mCategoryId);
        jSONObject.put("cid", this.mCategoryId);
    }

    protected Topic parseItem(JSONObject jSONObject) {
        return new Topic(jSONObject);
    }

    protected void fillJSONObjectHeaderInfo(JSONObject jSONObject) {
        cn.xiaochuankeji.tieba.background.utils.d.a.a(jSONObject);
    }
}
