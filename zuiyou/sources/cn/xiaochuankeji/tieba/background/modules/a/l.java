package cn.xiaochuankeji.tieba.background.modules.a;

import cn.htjyb.netlib.d;
import cn.htjyb.netlib.f;
import org.json.JSONException;
import org.json.JSONObject;

public class l implements cn.htjyb.netlib.d.a {
    d a;
    k b;
    a c;

    public interface a {
        void b(boolean z, String str);
    }

    public l(k kVar, a aVar) {
        this.b = kVar;
        this.c = aVar;
    }

    public void a() {
        JSONObject jSONObject = new JSONObject();
        try {
            long c = cn.xiaochuankeji.tieba.background.a.g().c();
            if (c > 0) {
                jSONObject.put("mid", c);
            }
            jSONObject.put("phone", this.b.a());
            jSONObject.put("code", this.b.b());
            jSONObject.put("pw", cn.htjyb.c.d.e(this.b.d()));
            jSONObject.put("name", this.b.c());
            if (this.b.a != -1) {
                jSONObject.put("gender", this.b.a);
            }
            jSONObject.put("sign", this.b.e());
            jSONObject.put("birth", this.b.f());
            jSONObject.put("region_code", this.b.g());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        cn.xiaochuankeji.tieba.background.utils.d.a.a(jSONObject);
        this.a = new f(cn.xiaochuankeji.tieba.background.utils.d.a.b("/account/register"), cn.xiaochuankeji.tieba.background.a.d(), jSONObject, this);
        this.a.b();
    }

    public void onTaskFinish(d dVar) {
        if (dVar.c.a) {
            JSONObject jSONObject = dVar.c.c;
            if (a(jSONObject)) {
                b(jSONObject);
                cn.xiaochuankeji.tieba.background.a.i().a(jSONObject);
                cn.xiaochuankeji.tieba.background.a.i().a(false, true);
                b();
                cn.xiaochuankeji.tieba.background.a.i().u();
                this.c.b(true, null);
                return;
            }
            this.c.b(false, "解析数据失败");
            return;
        }
        this.c.b(false, dVar.c.c());
    }

    private boolean a(JSONObject jSONObject) {
        if (jSONObject.optLong("mid") > 0) {
            return true;
        }
        return false;
    }

    private void b(JSONObject jSONObject) {
        long optLong = jSONObject.optLong("mid");
        String optString = jSONObject.optString("pw");
        String optString2 = jSONObject.optString("token");
        b i = cn.xiaochuankeji.tieba.background.a.i();
        i.a(optLong);
        i.a(optString);
        i.b(optString2);
    }

    private void b() {
        cn.xiaochuankeji.tieba.background.a.i().t();
    }
}
