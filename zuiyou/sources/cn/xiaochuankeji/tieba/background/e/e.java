package cn.xiaochuankeji.tieba.background.e;

import cn.xiaochuan.base.BaseApplication;
import cn.xiaochuankeji.tieba.background.data.post.Post;
import cn.xiaochuankeji.tieba.background.post.m;
import cn.xiaochuankeji.tieba.background.utils.d.a;
import cn.xiaochuankeji.tieba.background.utils.h;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class e extends m {
    private boolean a = false;
    private boolean b = false;

    protected String getQueryUrl() {
        return a.b("/attention/atted_posts");
    }

    public boolean hasMore() {
        return this.a;
    }

    protected void handleQuerySuccResult(JSONObject jSONObject) {
        boolean z = true;
        int i = 0;
        if (jSONObject.optInt("more", 0) != 1) {
            z = false;
        }
        this.a = z;
        if (!isQueryMore()) {
            this._items.clear();
        }
        JSONArray optJSONArray = jSONObject.optJSONArray("list");
        if (optJSONArray != null) {
            while (i < optJSONArray.length()) {
                JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                if (optJSONObject != null) {
                    Post post = (Post) parseItem(optJSONObject);
                    if (!(post == null || a(post._ID))) {
                        this._items.add(post);
                    }
                }
                i++;
            }
        }
        notifyListUpdate();
    }

    private boolean a(long j) {
        for (int i = 0; i < itemCount(); i++) {
            if (((Post) itemAt(i))._ID == j) {
                return true;
            }
        }
        return false;
    }

    protected void fillQueryBody(JSONObject jSONObject) throws JSONException {
    }

    public void refresh() {
        this.b = true;
        super.refresh();
    }

    public void queryMore() {
        this.b = false;
        super.queryMore();
    }

    protected void fillJSONObjectHeaderInfo(JSONObject jSONObject) {
        super.fillJSONObjectHeaderInfo(jSONObject);
        if (!this.b && itemCount() > 0) {
            try {
                jSONObject.put("end_t", ((Post) itemAt(itemCount() - 1))._createTime);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        if (this.mIsQueryMore) {
            h.a(BaseApplication.getAppContext(), "zy_event_follow_tab_friend_moment", "分页请求次数");
        }
    }
}
