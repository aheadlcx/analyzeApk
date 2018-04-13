package cn.xiaochuankeji.tieba.background.topic;

import cn.htjyb.b.a.d;
import cn.htjyb.netlib.b;
import cn.xiaochuankeji.tieba.background.a;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;

public class RecommendTopicWhenPublishList extends d<Topic> {
    private long mCategoryId;
    private int mMore;
    private long mOffset;

    public void setCategoryId(long j) {
        this.mCategoryId = j;
    }

    public void clear() {
        this.mOffset = 0;
        this.mMore = 0;
        this.mCategoryId = 0;
        super.clear();
    }

    public void init(ArrayList<Topic> arrayList, long j, int i) {
        if (arrayList != null && arrayList.size() > 0) {
            this._items.clear();
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                this._items.add((Topic) it.next());
            }
        }
        this.mOffset = j;
        this.mMore = i;
        notifyListUpdate();
    }

    protected b getHttpEngine() {
        return a.d();
    }

    protected String getQueryUrl() {
        return cn.xiaochuankeji.tieba.background.utils.d.a.b("/topic/get_list_by_mcid");
    }

    protected Topic parseItem(JSONObject jSONObject) {
        return new Topic(jSONObject);
    }

    protected void fillJSONObjectHeaderInfo(JSONObject jSONObject) {
        cn.xiaochuankeji.tieba.background.utils.d.a.a(jSONObject);
        try {
            jSONObject.put("mcid", this.mCategoryId);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public long getCId() {
        return this.mCategoryId;
    }

    protected void handleQuerySuccResult(JSONObject jSONObject) {
        super.handleQuerySuccResult(jSONObject);
        this.mOffset = jSONObject.optLong("offset");
        this.mMore = jSONObject.optInt("more");
    }

    public boolean hasMore() {
        return this.mMore == 1;
    }

    public long getQueryMoreOffset() {
        return this.mOffset;
    }
}
