package cn.xiaochuankeji.tieba.background.c;

import cn.htjyb.netlib.b;
import cn.xiaochuankeji.tieba.background.data.post.AbstractPost;
import cn.xiaochuankeji.tieba.background.data.post.Post;
import cn.xiaochuankeji.tieba.background.post.m;
import cn.xiaochuankeji.tieba.background.utils.d.a;
import com.sina.weibo.sdk.component.WidgetRequestParam;
import org.json.JSONException;
import org.json.JSONObject;

public class c extends m {
    protected String a;
    private int b;
    private int c;

    protected /* synthetic */ AbstractPost parseItem(JSONObject jSONObject) {
        return a(jSONObject);
    }

    /* renamed from: parseItem */
    protected /* synthetic */ Object m0parseItem(JSONObject jSONObject) {
        return a(jSONObject);
    }

    public void a(String str) {
        this.a = str;
    }

    public boolean hasMore() {
        return this.c < this.b;
    }

    protected String getQueryUrl() {
        return a.b("/search/post");
    }

    protected Post a(JSONObject jSONObject) {
        return new Post(jSONObject);
    }

    protected b getHttpEngine() {
        return cn.xiaochuankeji.tieba.background.a.d();
    }

    protected void fillJSONObjectHeaderInfo(JSONObject jSONObject) {
        a.a(jSONObject);
        try {
            jSONObject.put(WidgetRequestParam.REQ_PARAM_COMMENT_TOPIC, this.a);
            jSONObject.put("offset", this.c);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    protected void handleQuerySuccResult(JSONObject jSONObject) {
        super.handleQuerySuccResult(jSONObject);
        this.b = jSONObject.optInt("total");
        this.c = jSONObject.optInt("offset");
    }

    public void clear() {
        this.b = 0;
        this.c = 0;
        this.a = null;
        super.clear();
    }
}
