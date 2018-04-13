package cn.xiaochuankeji.tieba.background.topic;

import cn.xiaochuankeji.tieba.background.topic.TopicQueryList.TopicType;
import cn.xiaochuankeji.tieba.background.utils.d.a;
import org.json.JSONException;
import org.json.JSONObject;

public class TopicTabRecommendedList extends TopicQueryList {
    private boolean _more;
    private int mServerOffset;

    public TopicTabRecommendedList() {
        super(TopicType.kRecommend);
    }

    protected void fillQueryBody(JSONObject jSONObject) throws JSONException {
        a.a(jSONObject);
        jSONObject.put("offset", this._offset);
    }

    protected void handleQuerySuccResult(JSONObject jSONObject) {
        boolean z = true;
        super.handleQuerySuccResult(jSONObject);
        this.mServerOffset = jSONObject.optInt("offset");
        if (jSONObject.optInt("more", 0) != 1) {
            z = false;
        }
        this._more = z;
    }

    protected long getQueryMoreOffset() {
        return (long) this.mServerOffset;
    }

    public boolean hasMore() {
        return this._more;
    }
}
