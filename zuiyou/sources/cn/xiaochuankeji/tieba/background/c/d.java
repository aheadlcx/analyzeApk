package cn.xiaochuankeji.tieba.background.c;

import org.json.JSONObject;

public class d {
    private static String j = "id";
    private static String k = "name";
    private static String l = "gender";
    private static String m = "avatar";
    private static String n = "order";
    private static String o = "crown";
    private static String p = "post_cnt";
    private static String q = "review_cnt";
    private static String r = "value";
    private static String s = "isadm";
    public long a;
    public String b;
    public int c;
    public int d;
    public int e;
    public int f;
    public int g;
    public int h;
    public int i;
    private long t;

    public d(JSONObject jSONObject) {
        this.a = jSONObject.optLong(j);
        this.b = jSONObject.optString(k);
        this.c = jSONObject.optInt(l);
        this.t = jSONObject.optLong(m);
        this.d = jSONObject.optInt(n);
        this.e = jSONObject.optInt(o);
        this.f = jSONObject.optInt(p);
        this.g = jSONObject.optInt(q);
        this.h = jSONObject.optInt(r);
        this.i = jSONObject.optInt(s);
    }

    public long a() {
        if (this.t == 1 || this.t == 2 || this.t == 3) {
            return 0;
        }
        return this.t;
    }
}
