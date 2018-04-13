package cn.xiaochuankeji.tieba.background.c;

import cn.htjyb.b.a.d;
import cn.xiaochuankeji.tieba.background.beans.Member;
import cn.xiaochuankeji.tieba.background.utils.d.a;
import com.sina.weibo.sdk.component.WidgetRequestParam;
import org.json.JSONException;
import org.json.JSONObject;

public class b extends d<Member> {
    protected String a;
    private int b;
    private int c;

    protected /* synthetic */ Object parseItem(JSONObject jSONObject) {
        return a(jSONObject);
    }

    public void a(String str) {
        this.a = str;
    }

    public boolean hasMore() {
        return this.c < this.b;
    }

    protected String getQueryUrl() {
        return a.b("/search/user");
    }

    protected Member a(JSONObject jSONObject) {
        return new Member(jSONObject);
    }

    protected long getQueryMoreOffset() {
        return (long) this.c;
    }

    protected cn.htjyb.netlib.b getHttpEngine() {
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
