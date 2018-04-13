package cn.xiaochuankeji.tieba.background.modules.a;

import android.text.TextUtils;
import cn.htjyb.netlib.d;
import cn.htjyb.netlib.f;
import org.json.JSONException;
import org.json.JSONObject;

public class h implements cn.htjyb.netlib.d.a {
    private d a;
    private a b;
    private int c;
    private String d;
    private long e;

    public interface a {
        void a(boolean z, String str);
    }

    public h(int i, String str, long j, a aVar) {
        this.c = i;
        this.d = str;
        this.b = aVar;
        this.e = j;
    }

    public void a() {
        JSONObject jSONObject = new JSONObject();
        cn.xiaochuankeji.tieba.background.utils.d.a.a(jSONObject);
        try {
            jSONObject.put("token", cn.xiaochuankeji.tieba.background.a.g().a());
            if (!TextUtils.isEmpty(this.d)) {
                jSONObject.put("sign", this.d);
            }
            if (this.c > 0) {
                jSONObject.put("gender", this.c);
            }
            if (this.e != 0) {
                jSONObject.put("birth", this.e);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        this.a = new f(cn.xiaochuankeji.tieba.background.utils.d.a.b("/account/update"), cn.xiaochuankeji.tieba.background.a.d(), jSONObject, this);
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
        long optLong = jSONObject.optLong("mid");
        b i = cn.xiaochuankeji.tieba.background.a.i();
        i.a(optLong);
        i.a(jSONObject);
    }

    private void c() {
        cn.xiaochuankeji.tieba.background.a.i().t();
    }
}
