package cn.xiaochuankeji.tieba.background.member;

import cn.xiaochuankeji.tieba.background.a;
import cn.xiaochuankeji.tieba.background.data.post.AbstractPost;
import cn.xiaochuankeji.tieba.background.data.post.Post;
import cn.xiaochuankeji.tieba.background.post.m;
import com.izuiyou.a.a.b;
import org.json.JSONException;
import org.json.JSONObject;

public class c extends m {
    private boolean a;
    private long b;
    private long c;

    protected /* synthetic */ AbstractPost parseItem(JSONObject jSONObject) {
        return a(jSONObject);
    }

    /* renamed from: parseItem */
    protected /* synthetic */ Object m1parseItem(JSONObject jSONObject) {
        return a(jSONObject);
    }

    public c(long j) {
        this.c = j;
        this.a = !this._items.isEmpty();
    }

    protected void handleQuerySuccResult(JSONObject jSONObject) {
        boolean z = true;
        super.handleQuerySuccResult(jSONObject);
        if (jSONObject.optInt("more", 0) != 1) {
            z = false;
        }
        this.a = z;
        b.e("more设为:" + this.a);
        this.b = jSONObject.optLong("t");
    }

    protected cn.htjyb.netlib.b getHttpEngine() {
        return a.d();
    }

    protected String getQueryUrl() {
        return cn.xiaochuankeji.tieba.background.utils.d.a.b("/user/posts");
    }

    protected Post a(JSONObject jSONObject) {
        return new Post(jSONObject);
    }

    protected void fillJSONObjectHeaderInfo(JSONObject jSONObject) {
        cn.xiaochuankeji.tieba.background.utils.d.a.a(jSONObject);
    }

    public boolean hasMore() {
        return this.a;
    }

    protected void fillQueryBody(JSONObject jSONObject) throws JSONException {
        super.fillQueryBody(jSONObject);
        jSONObject.put("token", a.g().a());
        jSONObject.put("mid", this.c);
        jSONObject.put("t", this.b);
    }
}
