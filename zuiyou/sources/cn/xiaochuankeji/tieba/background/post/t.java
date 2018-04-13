package cn.xiaochuankeji.tieba.background.post;

import cn.htjyb.b.a.d;
import cn.htjyb.netlib.b;
import cn.xiaochuankeji.tieba.background.a;
import cn.xiaochuankeji.tieba.background.beans.Member;
import org.json.JSONException;
import org.json.JSONObject;

public class t extends d<Member> {
    private long a;
    private boolean b;
    private String c;
    private int d;
    private String e;

    protected /* synthetic */ Object parseItem(JSONObject jSONObject) {
        return a(jSONObject);
    }

    public t(long j, String str, boolean z) {
        this.a = j;
        this.c = str;
        this.b = z;
    }

    protected b getHttpEngine() {
        return a.d();
    }

    protected String getQueryUrl() {
        return cn.xiaochuankeji.tieba.background.utils.d.a.b("/tale/article/liked_users");
    }

    protected Member a(JSONObject jSONObject) {
        return new Member(jSONObject);
    }

    protected void fillJSONObjectHeaderInfo(JSONObject jSONObject) {
        cn.xiaochuankeji.tieba.background.utils.d.a.a(jSONObject);
        try {
            jSONObject.put("id", this.a);
            jSONObject.put("from", this.c);
            jSONObject.put("cursor", this.e);
            jSONObject.put("value", this.b ? 1 : -1);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    protected void fillQueryBody(JSONObject jSONObject) throws JSONException {
        jSONObject.put("cursor", this.e);
    }

    protected void handleQuerySuccResult(JSONObject jSONObject) {
        super.handleQuerySuccResult(jSONObject);
        this.d = jSONObject.optInt("more");
        this.e = jSONObject.optString("cursor");
    }

    protected long getQueryMoreOffset() {
        return 1;
    }

    public boolean hasMore() {
        return this.d == 1;
    }
}
