package cn.xiaochuankeji.tieba.background.utils.e;

import android.support.v4.app.NotificationCompat;
import com.tencent.open.SocialConstants;
import com.tencent.tauth.AuthActivity;
import org.json.JSONException;
import org.json.JSONObject;

public class c extends b {
    public String a;
    public int b;
    public int c;
    public String d;
    public String i;
    public String j;
    public int k;
    public long l;
    public long m;
    public int n;

    protected JSONObject a() {
        JSONObject a = super.a();
        try {
            a.put(AuthActivity.ACTION_KEY, "play");
            a.put("otype", "error");
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("owner", this.a);
            jSONObject.put("type", this.b);
            jSONObject.put(NotificationCompat.CATEGORY_STATUS, this.c);
            jSONObject.put(SocialConstants.PARAM_APP_DESC, this.d);
            jSONObject.put("videouri", this.i);
            jSONObject.put("url_type", this.j);
            if (this.k != 0) {
                jSONObject.put("click", this.k);
            }
            if (0 != this.h) {
                jSONObject.put("tid", this.h);
            }
            jSONObject.put("pid", this.l);
            jSONObject.put("prid", this.m);
            jSONObject.put("rstatus", this.n);
            a.put("data", jSONObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return a;
    }
}
