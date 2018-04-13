package cn.xiaochuankeji.tieba.background.review;

import cn.htjyb.b.a.c;
import cn.xiaochuankeji.tieba.background.utils.d.a;
import org.json.JSONException;
import org.json.JSONObject;

public class e implements c {
    private long a;
    private long b = 0;
    private boolean c = false;

    public e(long j, boolean z) {
        this.a = j;
        this.c = z;
    }

    public void a(long j) {
        this.b = j;
    }

    public String a() {
        return a.b("/review/new_reviews");
    }

    public void a(JSONObject jSONObject) throws JSONException {
        jSONObject.put("pid", this.a);
        jSONObject.put("t", this.b);
    }

    public void b(JSONObject jSONObject) {
        boolean z = true;
        if (jSONObject.optInt("more", 0) != 1) {
            z = false;
        }
        this.c = z;
        this.b = jSONObject.optLong("t");
    }

    public boolean b() {
        return this.c;
    }

    public void c() {
        this.c = false;
        this.b = 0;
    }
}
