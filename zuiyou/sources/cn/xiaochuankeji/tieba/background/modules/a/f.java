package cn.xiaochuankeji.tieba.background.modules.a;

import android.text.TextUtils;
import cn.htjyb.netlib.d;
import org.json.JSONException;
import org.json.JSONObject;

public class f implements cn.htjyb.netlib.d.a {
    d a;
    a b;
    String c;
    String d;
    String e;
    int f;

    public interface a {
        void b(boolean z, String str);
    }

    public f(String str, String str2, String str3, int i, a aVar) {
        this.c = str;
        this.d = str2;
        this.b = aVar;
        this.e = str3;
        this.f = i;
    }

    public void a() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("phone", this.c);
            jSONObject.put("code", this.d);
            jSONObject.put("region_code", this.f);
            jSONObject.put("pw", cn.htjyb.c.d.e(this.e));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        cn.xiaochuankeji.tieba.background.utils.d.a.a(jSONObject);
        this.a = new cn.htjyb.netlib.f(cn.xiaochuankeji.tieba.background.utils.d.a.b("/account/reset_password"), cn.xiaochuankeji.tieba.background.a.d(), jSONObject, this);
        this.a.b();
    }

    public void onTaskFinish(d dVar) {
        if (dVar.c.a) {
            JSONObject jSONObject = dVar.c.c;
            a(jSONObject);
            if (b(jSONObject)) {
                this.b.b(true, null);
                return;
            } else {
                this.b.b(false, "解析数据失败");
                return;
            }
        }
        this.b.b(false, dVar.c.c());
    }

    private void a(JSONObject jSONObject) {
        long optLong = jSONObject.optLong("mid");
        String optString = jSONObject.optString("pw", null);
        if (TextUtils.isEmpty(optString)) {
            optString = cn.htjyb.c.d.e(this.e);
        }
        String optString2 = jSONObject.optString("token");
        b i = cn.xiaochuankeji.tieba.background.a.i();
        i.a(optLong);
        i.a(optString);
        i.a(false, false);
        i.a(jSONObject);
        i.b(optString2);
    }

    private boolean b(JSONObject jSONObject) {
        if (jSONObject.optLong("mid") > 0) {
            return true;
        }
        return false;
    }
}
