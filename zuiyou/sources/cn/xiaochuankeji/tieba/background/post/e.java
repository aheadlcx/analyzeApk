package cn.xiaochuankeji.tieba.background.post;

import cn.htjyb.b.a.d;
import cn.htjyb.netlib.b;
import cn.xiaochuankeji.tieba.background.a;
import cn.xiaochuankeji.tieba.background.beans.Member;
import org.json.JSONException;
import org.json.JSONObject;

public class e extends d<Member> {
    private long a;
    private long b;
    private boolean c;

    protected /* synthetic */ Object parseItem(JSONObject jSONObject) {
        return a(jSONObject);
    }

    public e(long j, long j2) {
        this.a = j;
        this.b = j2;
        if (0 == this.b) {
            this.c = true;
        } else {
            this.c = false;
        }
    }

    protected b getHttpEngine() {
        return a.d();
    }

    protected String getQueryUrl() {
        if (this.c) {
            return cn.xiaochuankeji.tieba.background.utils.d.a.b("/post/likedusers_new");
        }
        return cn.xiaochuankeji.tieba.background.utils.d.a.b("/review/likedusers_new");
    }

    protected Member a(JSONObject jSONObject) {
        return new Member(jSONObject);
    }

    protected void fillJSONObjectHeaderInfo(JSONObject jSONObject) {
        cn.xiaochuankeji.tieba.background.utils.d.a.a(jSONObject);
    }

    protected void fillQueryBody(JSONObject jSONObject) throws JSONException {
        super.fillQueryBody(jSONObject);
        jSONObject.put("pid", this.a);
        if (!this.c) {
            jSONObject.put("rid", this.b);
        }
    }

    protected void handleQuerySuccResult(JSONObject jSONObject) {
        super.handleQuerySuccResult(jSONObject);
    }
}
