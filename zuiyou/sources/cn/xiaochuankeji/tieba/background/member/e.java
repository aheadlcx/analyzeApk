package cn.xiaochuankeji.tieba.background.member;

import cn.xiaochuankeji.tieba.background.post.m;
import cn.xiaochuankeji.tieba.background.utils.d.a;
import org.json.JSONException;
import org.json.JSONObject;

public class e extends m {
    private boolean a;
    private long b;

    public e() {
        this.a = !this._items.isEmpty();
    }

    protected void handleQuerySuccResult(JSONObject jSONObject) {
        boolean z = true;
        super.handleQuerySuccResult(jSONObject);
        if (jSONObject.optInt("more", 0) != 1) {
            z = false;
        }
        this.a = z;
        this.b = jSONObject.optLong("t");
    }

    protected String getQueryUrl() {
        return a.b("/my/posts");
    }

    public boolean hasMore() {
        return this.a;
    }

    protected void fillQueryBody(JSONObject jSONObject) throws JSONException {
        super.fillQueryBody(jSONObject);
        a.a(jSONObject);
        jSONObject.put("t", this.b);
    }
}
