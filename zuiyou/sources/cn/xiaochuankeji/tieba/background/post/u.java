package cn.xiaochuankeji.tieba.background.post;

import android.text.TextUtils;
import cn.htjyb.b.a.d;
import cn.htjyb.netlib.b;
import cn.xiaochuankeji.tieba.background.a;
import cn.xiaochuankeji.tieba.background.beans.Member;
import org.json.JSONException;
import org.json.JSONObject;

public class u extends d<Member> {
    private long a;
    private int b;
    private int c;
    private String d;

    protected /* synthetic */ Object parseItem(JSONObject jSONObject) {
        return a(jSONObject);
    }

    public u(long j, int i) {
        this.a = j;
        this.b = i;
    }

    protected b getHttpEngine() {
        return a.d();
    }

    protected String getQueryUrl() {
        if (1 == this.b) {
            return cn.xiaochuankeji.tieba.background.utils.d.a.b("/ugcvideo/post/list_like");
        }
        return cn.xiaochuankeji.tieba.background.utils.d.a.b("/ugcvideo/review/list_like");
    }

    protected Member a(JSONObject jSONObject) {
        return new Member(jSONObject);
    }

    protected void fillJSONObjectHeaderInfo(JSONObject jSONObject) {
        cn.xiaochuankeji.tieba.background.utils.d.a.a(jSONObject);
        try {
            jSONObject.put(1 == this.b ? "pid" : "rid", this.a);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    protected void fillQueryBody(JSONObject jSONObject) throws JSONException {
        if (!isQueryMore()) {
            this.d = null;
        } else if (!TextUtils.isEmpty(this.d)) {
            jSONObject.put("offset", this.d);
        }
    }

    protected void handleQuerySuccResult(JSONObject jSONObject) {
        super.handleQuerySuccResult(jSONObject);
        this.c = jSONObject.optInt("more");
        this.d = jSONObject.optString("offset");
    }

    protected long getQueryMoreOffset() {
        return 1;
    }

    public boolean hasMore() {
        return this.c == 1;
    }
}
