package cn.xiaochuankeji.tieba.background.topic;

import cn.xiaochuankeji.tieba.background.topic.TopicQueryList.TopicType;
import com.sina.weibo.sdk.component.WidgetRequestParam;
import org.json.JSONException;
import org.json.JSONObject;

public class TopicSearcher extends TopicQueryList {
    private boolean _hasSame;
    private String _searchKey;

    public TopicSearcher() {
        super(TopicType.kSearcher);
    }

    public void setSearchKey(String str) {
        this._searchKey = str;
    }

    public boolean hasSame() {
        return this._hasSame;
    }

    protected void fillQueryBody(JSONObject jSONObject) throws JSONException {
        super.fillQueryBody(jSONObject);
        jSONObject.put(WidgetRequestParam.REQ_PARAM_COMMENT_TOPIC, this._searchKey);
    }

    protected void handleQuerySuccResult(JSONObject jSONObject) {
        boolean z = true;
        super.handleQuerySuccResult(jSONObject);
        if (1 != jSONObject.optInt("has_same")) {
            z = false;
        }
        this._hasSame = z;
    }
}
