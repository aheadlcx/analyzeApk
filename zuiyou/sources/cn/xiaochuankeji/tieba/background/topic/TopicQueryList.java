package cn.xiaochuankeji.tieba.background.topic;

import cn.htjyb.b.a.d;
import cn.htjyb.netlib.b;
import cn.xiaochuankeji.tieba.background.a;
import java.util.ArrayList;
import org.json.JSONException;
import org.json.JSONObject;

public class TopicQueryList extends d<Topic> {
    private TopicType _type;
    protected ArrayList<Topic> historyTopics;

    public enum TopicType {
        kSearcher,
        kRecommend,
        kAttention
    }

    public TopicQueryList(TopicType topicType) {
        this._type = topicType;
    }

    public void setHistoryList(ArrayList<Topic> arrayList) {
        this.historyTopics = arrayList;
    }

    protected b getHttpEngine() {
        return a.d();
    }

    protected void fillQueryBody(JSONObject jSONObject) throws JSONException {
        super.fillQueryBody(jSONObject);
    }

    protected String getQueryUrl() {
        switch (this._type) {
            case kSearcher:
                return cn.xiaochuankeji.tieba.background.utils.d.a.b("/search/topic");
            case kAttention:
                return cn.xiaochuankeji.tieba.background.utils.d.a.b("/topic/attention_list");
            case kRecommend:
                return cn.xiaochuankeji.tieba.background.utils.d.a.b("/topic/recommend_new");
            default:
                return null;
        }
    }

    public void removeTopics(ArrayList<Topic> arrayList) {
        this._items.removeAll(arrayList);
    }

    protected Topic parseItem(JSONObject jSONObject) {
        return new Topic(jSONObject);
    }

    protected void fillJSONObjectHeaderInfo(JSONObject jSONObject) {
        cn.xiaochuankeji.tieba.background.utils.d.a.a(jSONObject);
    }
}
