package cn.xiaochuankeji.tieba.background.post;

import cn.htjyb.b.a.d;
import cn.htjyb.netlib.b;
import cn.xiaochuan.base.BaseApplication;
import cn.xiaochuankeji.tieba.background.a;
import cn.xiaochuankeji.tieba.background.data.Comment;
import cn.xiaochuankeji.tieba.background.utils.h;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class j extends d<Comment> {
    private long a;
    private boolean b;
    private boolean c = false;

    protected /* synthetic */ Object parseItem(JSONObject jSONObject) {
        return a(jSONObject);
    }

    public j(long j) {
        this.a = j;
    }

    public void a(boolean z) {
        this.b = z;
    }

    protected b getHttpEngine() {
        return a.d();
    }

    protected String getQueryUrl() {
        return cn.xiaochuankeji.tieba.background.utils.d.a.b("/review/hot_reviews");
    }

    protected Comment a(JSONObject jSONObject) {
        return new Comment(jSONObject);
    }

    protected void fillJSONObjectHeaderInfo(JSONObject jSONObject) {
        cn.xiaochuankeji.tieba.background.utils.d.a.a(jSONObject);
        h.a(BaseApplication.getAppContext(), "zy_event_postdetail_page", "热门评论请求次数");
    }

    public void a(Comment comment) {
        this._items.remove(comment);
    }

    public void b(Comment comment) {
        this._items.add(0, comment);
        notifyListUpdate();
    }

    protected void fillQueryBody(JSONObject jSONObject) throws JSONException {
        super.fillQueryBody(jSONObject);
        jSONObject.put("pid", this.a);
    }

    public boolean hasMore() {
        if (this._total == 0) {
            return this.b;
        }
        return !this.c;
    }

    public void refresh() {
    }

    protected void handleQuerySuccResult(JSONObject jSONObject) {
        boolean z;
        int i = 0;
        if (0 == this._offset) {
            if (this._items.isEmpty()) {
                z = false;
            } else {
                z = true;
            }
            this._items.clear();
        } else {
            z = false;
        }
        this._total = jSONObject.optInt("total");
        this._offset = (long) jSONObject.optInt("offset");
        JSONArray optJSONArray = jSONObject.optJSONArray("list");
        if (optJSONArray == null || optJSONArray.length() == 0 || this._offset >= ((long) this._total)) {
            this.c = true;
        }
        if (optJSONArray != null) {
            while (i < optJSONArray.length()) {
                JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                if (optJSONObject != null) {
                    Comment a = a(optJSONObject);
                    if (!(a == null || c(a))) {
                        this._items.add(a);
                        z = true;
                    }
                }
                i++;
            }
        }
        if (z) {
            notifyListUpdate();
        }
    }

    private boolean c(Comment comment) {
        if (this._items.size() == 0) {
            return false;
        }
        for (int i = 0; i < itemCount(); i++) {
            Comment comment2 = (Comment) itemAt(i);
            if (comment2 != null && comment2._id == comment._id) {
                return true;
            }
        }
        return false;
    }
}
