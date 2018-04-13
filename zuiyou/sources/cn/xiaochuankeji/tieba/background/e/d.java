package cn.xiaochuankeji.tieba.background.e;

import cn.htjyb.b.a.e;
import cn.htjyb.netlib.b;
import cn.xiaochuankeji.tieba.background.a;
import cn.xiaochuankeji.tieba.background.beans.Member;
import org.json.JSONException;
import org.json.JSONObject;

public class d extends e<Member> {
    private long a;

    protected /* synthetic */ Object parseItem(JSONObject jSONObject) {
        return a(jSONObject);
    }

    public d(long j) {
        this.a = j;
    }

    protected b getHttpEngine() {
        return a.d();
    }

    protected String getQueryUrl() {
        if (a.g().c() == this.a) {
            return cn.xiaochuankeji.tieba.background.utils.d.a.b("/attention/my_atts");
        }
        return cn.xiaochuankeji.tieba.background.utils.d.a.b("/attention/user_atts");
    }

    protected Member a(JSONObject jSONObject) {
        return new Member(jSONObject);
    }

    protected void fillJSONObjectHeaderInfo(JSONObject jSONObject) {
        cn.xiaochuankeji.tieba.background.utils.d.a.a(jSONObject);
    }

    protected void fillQueryBody(JSONObject jSONObject) throws JSONException {
        super.fillQueryBody(jSONObject);
        jSONObject.put("mid", this.a);
    }
}
