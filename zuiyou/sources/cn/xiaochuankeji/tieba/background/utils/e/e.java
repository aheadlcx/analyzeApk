package cn.xiaochuankeji.tieba.background.utils.e;

import com.tencent.tauth.AuthActivity;
import org.json.JSONException;
import org.json.JSONObject;

public class e extends b {
    public String a;
    public long b;
    public long c;
    public int d;
    public long i;
    public String j;
    public long k = 0;
    public long l;
    public String m;
    public long n;
    public int o = 0;
    public int p;

    protected JSONObject a() {
        JSONObject a = super.a();
        try {
            if (this.c > 0) {
                a.put(AuthActivity.ACTION_KEY, "play");
            } else {
                a.put(AuthActivity.ACTION_KEY, "noplay");
            }
            a.put("otype", "video");
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("owner", this.a);
            if (this.c > 0) {
                jSONObject.put("playdur", this.b / 1000);
                jSONObject.put("videodur", this.c / 1000);
                jSONObject.put("lagcount", this.d);
                jSONObject.put("buffertime", this.i);
            } else {
                jSONObject.put("waittime", this.i);
            }
            jSONObject.put("url_type", this.j);
            jSONObject.put("videouri", this.m);
            jSONObject.put("version", 1);
            jSONObject.put("rstatus", this.p);
            if (0 != this.k) {
                jSONObject.put("pid", this.k);
            }
            if (0 != this.l) {
                jSONObject.put("prid", this.l);
            }
            if (0 != this.h) {
                jSONObject.put("tid", this.h);
            }
            if (this.n != 0) {
                jSONObject.put("entryId", this.n);
            }
            jSONObject.put("ugcvideo", this.o);
            a.put("data", jSONObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return a;
    }
}
