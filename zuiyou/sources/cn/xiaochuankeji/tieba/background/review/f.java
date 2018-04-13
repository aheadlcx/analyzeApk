package cn.xiaochuankeji.tieba.background.review;

import cn.htjyb.b.a.c;
import cn.xiaochuankeji.tieba.background.utils.d.a;
import org.json.JSONException;
import org.json.JSONObject;

public class f implements c {
    private final long a;
    private final long b;
    private final String c;
    private boolean d = false;
    private long e;

    public f(long j, long j2, String str, boolean z, long j3) {
        this.a = j;
        this.b = j2;
        this.c = str;
        this.d = z;
        this.e = j3;
    }

    public String a() {
        return a.b("/post/query_preview");
    }

    public void a(JSONObject jSONObject) throws JSONException {
        jSONObject.put("pid", this.a);
        jSONObject.put("rid", this.b);
        jSONObject.put("type", this.c);
        jSONObject.put("offset", this.e);
    }

    public void b(JSONObject jSONObject) {
        boolean z = true;
        if (jSONObject.optInt("more", 0) != 1) {
            z = false;
        }
        this.d = z;
        this.e = jSONObject.optLong("offset");
    }

    public boolean b() {
        return this.d;
    }

    public void c() {
        this.d = false;
        this.e = 0;
    }
}
