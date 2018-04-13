package cn.xiaochuankeji.tieba.background.favorite;

import cn.xiaochuankeji.tieba.background.post.m;
import cn.xiaochuankeji.tieba.background.utils.d.a;
import org.json.JSONException;
import org.json.JSONObject;

public class e extends m {
    private boolean a = false;
    private long b = 0;
    private long c;

    public e(long j) {
        this.c = j;
    }

    protected void handleQuerySuccResult(JSONObject jSONObject) {
        boolean z = true;
        super.handleQuerySuccResult(jSONObject);
        if (jSONObject.optInt("more") != 1) {
            z = false;
        }
        this.a = z;
        this.b = (long) jSONObject.optInt("t");
    }

    protected String getQueryUrl() {
        return a.b("/favor/posts");
    }

    protected void fillQueryBody(JSONObject jSONObject) throws JSONException {
        if (isQueryMore()) {
            jSONObject.put("t", this.b);
        }
        jSONObject.put("id", this.c);
    }

    public boolean hasMore() {
        return this.a;
    }
}
