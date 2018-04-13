package cn.xiaochuankeji.tieba.ui.topic.deleteList;

import cn.htjyb.netlib.b;
import cn.xiaochuankeji.tieba.background.data.post.AbstractPost;
import cn.xiaochuankeji.tieba.background.data.post.Post;
import cn.xiaochuankeji.tieba.background.post.m;
import org.json.JSONException;
import org.json.JSONObject;

public class a extends m {
    private final long a;
    private final long b;
    private int c;
    private int d;
    private long e;

    protected /* synthetic */ AbstractPost parseItem(JSONObject jSONObject) {
        return a(jSONObject);
    }

    /* renamed from: parseItem */
    protected /* synthetic */ Object m2parseItem(JSONObject jSONObject) {
        return a(jSONObject);
    }

    public a(long j, long j2) {
        this.a = j;
        this.b = j2;
    }

    protected b getHttpEngine() {
        return cn.xiaochuankeji.tieba.background.a.d();
    }

    protected String getQueryUrl() {
        return cn.xiaochuankeji.tieba.background.utils.d.a.b("/topic/admin_delete_posts");
    }

    protected Post a(JSONObject jSONObject) {
        return new Post(jSONObject);
    }

    protected void fillJSONObjectHeaderInfo(JSONObject jSONObject) {
        cn.xiaochuankeji.tieba.background.utils.d.a.a(jSONObject);
        try {
            jSONObject.put("mid", this.b);
            jSONObject.put("tid", this.a);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    protected void handleQuerySuccResult(JSONObject jSONObject) {
        super.handleQuerySuccResult(jSONObject);
        this.c = jSONObject.optInt("more");
        this.d = jSONObject.optInt("offset");
        this.e = System.currentTimeMillis();
    }

    public boolean hasMore() {
        return this.c == 1;
    }

    protected long getQueryMoreOffset() {
        return (long) this.d;
    }
}
