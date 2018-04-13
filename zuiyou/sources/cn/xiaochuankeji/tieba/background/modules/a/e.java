package cn.xiaochuankeji.tieba.background.modules.a;

import cn.htjyb.netlib.d;
import cn.htjyb.netlib.f;
import org.json.JSONException;
import org.json.JSONObject;

public class e implements cn.htjyb.netlib.d.a {
    private d a;
    private a b;
    private String c;
    private String d;
    private String e;
    private int f;

    public interface a {
        void a(boolean z, String str);
    }

    public e(String str, String str2, String str3, int i, a aVar) {
        this.c = str;
        this.d = str2;
        this.e = str3;
        this.b = aVar;
        this.f = i;
    }

    public void a() {
        JSONObject jSONObject = new JSONObject();
        cn.xiaochuankeji.tieba.background.utils.d.a.a(jSONObject);
        try {
            jSONObject.put("token", cn.xiaochuankeji.tieba.background.a.g().a());
            jSONObject.put("phone", this.c);
            jSONObject.put("code", this.d);
            jSONObject.put("region_code", this.f);
            jSONObject.put("pw", cn.htjyb.c.d.e(this.e));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        this.a = new f(cn.xiaochuankeji.tieba.background.utils.d.a.b("/account/bind_phone"), cn.xiaochuankeji.tieba.background.a.d(), jSONObject, this);
        this.a.b();
    }

    public void b() {
        this.a.c();
    }

    public void onTaskFinish(d dVar) {
        if (dVar.c.a) {
            a(dVar.c.c);
            c();
            if (this.b != null) {
                this.b.a(true, null);
            }
        } else if (this.b != null) {
            this.b.a(false, dVar.c.c());
        }
    }

    private void a(JSONObject jSONObject) {
        b i = cn.xiaochuankeji.tieba.background.a.i();
        i.q().setPhone(this.c);
        i.q().setIsBind(1);
        i.a(cn.htjyb.c.d.e(this.e));
    }

    private void c() {
        cn.xiaochuankeji.tieba.background.a.i().t();
    }
}
